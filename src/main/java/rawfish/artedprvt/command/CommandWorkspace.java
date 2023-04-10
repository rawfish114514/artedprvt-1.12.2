package rawfish.artedprvt.command;

import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.util.text.TextComponentString;
import rawfish.artedprvt.id.FormatCode;

import java.io.*;
import java.nio.charset.StandardCharsets;

public class CommandWorkspace extends CommandIs {
    public CommandWorkspace(String nameIn){
        name=nameIn;
    }

    public final String name;
    @Override
    public String getCommandName() {
        return name;
    }

    @Override
    public String getCommandUsage(ICommandSender sender)
    {
        return "commands.workspace.usage";
    }

    @Override
    public void processCommand(ICommandSender sender, String[] args) throws CommandException
    {
        String dir=System.getProperties().get("user.dir").toString()+"/artedprvt";//项目目录
        File file;
        file=new File(dir);
        if(file.exists()){
            throw new CommandException("目录/artedprvt已经存在");
        }
        file.mkdir();

        file=new File(dir+"/lib");
        file.mkdir();

        file=new File(dir+"/src");
        file.mkdir();

        file=new File(dir+"/src/script");
        file.mkdir();

        file=new File(dir+"/src/config.json");
        try {
            file.createNewFile();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        try {
            Writer writer=new FileWriter(file);
            writer.write(fileConfig());
            writer.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        file=new File(dir+"/src/script/main.js");
        try {
            file.createNewFile();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        try {
            Writer writer=new OutputStreamWriter(new FileOutputStream(file), StandardCharsets.UTF_8);
            writer.write(fileMainjs());
            writer.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        sender.sendMessage(new TextComponentString(FormatCode.COLOR_2+"创建成功: "+dir));
    }
    public String fileConfig(){
        return "{\n" +
                "  \"options\": [\n" +
                "    \"-pm\",\"-al\",\"-rc\"\n" +
                "  ],\n" +
                "  \"pkg\": {\n" +
                "    \"pack\": \"main\"\n" +
                "  }\n" +
                "}";
    }
    public String fileMainjs(){
        return "//Hello Minecraft!\n" +
                "//Created by Rawfishc on 2023/4/10\n" +
                "\n" +
                "var r=giveBook();\n" +
                "drawLogo();\n" +
                "\n" +
                "function drawLogo(){\n" +
                "    var a=print;\n" +
                "    a(\"§0█████§4███§0█████████\");\n" +
                "    a(\"§0█████§4██§3█§0█§0██████§f██\");\n" +
                "    a(\"§0████§4██§0█§3██§0██████§f██\");\n" +
                "    a(\"§0████§4██§0█§3██§0██████§f██\");\n" +
                "    a(\"§0███§4██§0███§3██§0███████\");\n" +
                "    a(\"§0███§4██§0███§3██§0███████\");\n" +
                "    a(\"§0███§4██§0███§3██§0███████\");\n" +
                "    a(\"§0██§4██§0█████§3██§0██████\");\n" +
                "    a(\"§0██§4███████§3██§0████§f██\");\n" +
                "    a(\"§0█§4█████████§3██§0███§f██\");\n" +
                "    a(\"§0█§4██§0███████§3██§0███§f██\");\n" +
                "    a(\"§0█§4██§0███████§3██§0████§f█\");\n" +
                "    a(\"§0§4██§0█████████§3██§0██§f█§0█\");\n" +
                "}\n" +
                "\n" +
                "function giveBook(){\n" +
                "    import(\"-net.minecraft.command.CommandBase\");\n" +
                "    import(\"-net.minecraftforge.fml.common.FMLCommonHandler\");\n" +
                "    import(\"-net.minecraft.item.ItemStack\");\n" +
                "    import(\"-net.minecraft.nbt.NBTTagCompound\");\n" +
                "    import(\"-net.minecraft.nbt.NBTTagList\");\n" +
                "    import(\"-net.minecraft.nbt.NBTTagString\");\n" +
                "    import(\"-java.lang.Integer\");\n" +
                "\n" +
                "    var entityPlayer=CommandBase.getPlayer(FMLCommonHandler.instance().getMinecraftServerInstance(),sender(),getThisPlayer().getDisplayNameString());\n" +
                "    var nbt=new NBTTagCompound();\n" +
                "        var pagesTag=buildPageArray();\n" +
                "        var authorTag=new NBTTagString(\"\\u00a73Rawfishc\\u00a77\");\n" +
                "        var titleTag=new NBTTagString(\"\\u00a7lHello Minecraft\");\n" +
                "    nbt.setTag(\"pages\",pagesTag);\n" +
                "    nbt.setTag(\"author\",authorTag);\n" +
                "    nbt.setTag(\"title\",titleTag);\n" +
                "    var itemStack = new ItemStack(Items.WRITTEN_BOOK, 1, 0);\n" +
                "    itemStack.setTagCompound(nbt);\n" +
                "    var flag=entityPlayer.inventory.addItemStackToInventory(itemStack);\n" +
                "    if(flag){\n" +
                "        entityPlayer.inventoryContainer.detectAndSendChanges();\n" +
                "    }\n" +
                "\n" +
                "    function buildPageArray(){\n" +
                "        var tag=new NBTTagList();\n" +
                "        var pageMethods=[page_0,page_1];\n" +
                "        for(var i=0;i<pageMethods.length;i++){\n" +
                "            var page=pageMethods[i]();\n" +
                "            tag.appendTag(new NBTTagString(page));\n" +
                "        }\n" +
                "        return tag;\n" +
                "    }\n" +
                "    function page_0(){\n" +
                "        return \"\\\"\\u00a7lAPF(Artedprvt Frame)\\n\\nAPF是用于Minecraft的Js运行环境。\\n\\n目的是在游戏中动态的运行代码。\\\"\";\n" +
                "    }\n" +
                "    function page_1(){\n" +
                "        return \"\\\"对于只使用脚本的玩家来说，没有任何门槛。\\n\\n如果你有Js编程基础可以编写脚本实现一些简单的功能。\"+\n" +
                "            \"\\n\\n要实现稍微复杂的功能可能需要你有较高的Js和Java水平以及Mod开发经验。\\n\\n降低开发门槛也是APF的主要目标！\\\"\";\n" +
                "    }\n" +
                "    return Math.log(1919810)/Math.log(114514);\n" +
                "}";
    }
    @Override
    public int getRequiredPermissionLevel()
    {
        return 0;
    }
}