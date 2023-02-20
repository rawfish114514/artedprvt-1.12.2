package rawfish.artedprvt.script.js;


import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 混淆类集合
 */
public class ClassCollection {
    private static final String VERSION="20171003-1.12";
    public static Map<String,ClassMember> classMap=null;
    /**
     * 加载数据
     * @param srg 源
     */
    public static synchronized void load(String srg){
        if(classMap==null){
            classMap=new HashMap<>();
            present=null;

            Reader reader=new StringReader(srg);
            StringBuilder sb=new StringBuilder();
            List<String> items=new ArrayList<>();
            try {
                while (true) {
                    int c=reader.read();
                    if (c==-1) {
                        break;
                    }
                    if(c=='\n'){
                        items.add(sb.toString());
                        sb.delete(0,sb.length());
                        continue;
                    }
                    sb.append((char)c);
                }
                reader.close();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
            for(int i=0,l=items.size();i<l;i++){
                String item=items.get(i);

                if(item.length()==0){
                    continue;
                }
                char c=item.charAt(0);
                if(c=='\t'){
                    //字段或方法
                    String[] d=item.trim().split(" ");
                    if(d.length==2){
                        //字段
                        loadFD(d[0],d[1]);
                        continue;
                    }
                    if(d.length==3){
                        //方法
                        loadMD(d[0],d[2]);
                    }
                }else{
                    //类
                    loadCL(item.substring(item.indexOf(' ')+1));
                }

            }


        }
        putExtend();
/*
        for(ClassMember m:classMap.values()){
            for(String s:m.nameTable.values()){
                System.out.println(s);
            }
        }

 */


    }


    public static ClassMember present;
    public static void loadCL(String className){
        present=new ClassMember();
        classMap.put(className.replace('/','.'),present);
    }

    public static void loadFD(String fieldName,String fieldSrg){
        if(present==null){
            return;
        }

        present.up(fieldName,fieldSrg);
    }

    public static void loadMD(String methodName,String methodSrg){
        if(present==null){
            return;
        }

        present.up(methodName,methodSrg);
    }

    public static boolean isE=false;
    //将父类和接口的成员添加到子类上
    public static void putExtend() {
        if(!isE) {
            isE=true;
            ClassLoader classLoader=ClassCollection.class.getClassLoader();
            int notClass=0;
            for (String className : classMap.keySet()) {
                ClassMember member = classMap.get(className);

                Class clas= null;
                try {
                    clas = classLoader.loadClass(className);
                } catch (ClassNotFoundException e) {
                    notClass++;
                    continue;
                }
                List<Class> cl=getClasses(clas);
                if(cl!=null) {
                    for (Class c : cl) {
                        ClassMember superMember = classMap.get(c.getName());
                        if (superMember == null) {
                            continue;
                        }
                        member.up(superMember);
                    }
                }
            }

            System.out.println("添加扩展notClass: "+notClass);
        }
    }

    //一直获取父类和接口直到Object和null
    public static List<Class> getClasses(Class clas){
        if(clas==Object.class){
            return null;
        }
        if(clas==null){
            return null;
        }
        List<Class> cl=new ArrayList<>();
        cl.add(clas);
        Class sup=clas.getSuperclass();
        List<Class> scl = getClasses(sup);
        if (scl != null) {
            cl.addAll(scl);
        }
        for(Class inf:clas.getInterfaces()){
            List<Class> icl=getClasses(inf);
            if(icl!=null){
                cl.addAll(icl);
            }
        }
        return cl;
    }
}
