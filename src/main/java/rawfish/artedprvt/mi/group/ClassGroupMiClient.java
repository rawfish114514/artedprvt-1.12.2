package rawfish.artedprvt.mi.group;

import rawfish.artedprvt.core.ClassGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * 可在客户端的类
 */
public class ClassGroupMiClient implements ClassGroup  {
    private List<Class> classes;
    public List<Class> getClasses(){
        init();
        return classes();
    }

    private void init(){
        classes=new ArrayList<Class>();
    }

    private void add(Class clas){
        classes.add(clas);
    }

    private List<Class> classes(){
        return classes;
    }

    public String getName(){
        return "mi.client";
    }
}
