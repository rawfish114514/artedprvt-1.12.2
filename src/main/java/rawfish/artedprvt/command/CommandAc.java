package rawfish.artedprvt.command;

import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.command.WrongUsageException;
import net.minecraft.util.text.TextComponentString;
import rawfish.artedprvt.script.ScriptConst;

public class CommandAc extends CommandIs {
    public CommandAc(String nameIn){
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
        return "commands.ac.usage";
    }

    @Override
    public void processCommand(ICommandSender sender, String[] args) throws CommandException
    {
        if (args.length > 0) {
            throw new WrongUsageException("commands.ac.usage");
        }
        ScriptConst.chat=!ScriptConst.chat;
        sender.sendMessage(new TextComponentString("Chat模式: "+ScriptConst.chat));
    }
    @Override
    public int getRequiredPermissionLevel()
    {
        return 0;
    }
}
