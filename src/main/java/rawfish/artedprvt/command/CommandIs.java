package rawfish.artedprvt.command;

import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.server.MinecraftServer;

public abstract class CommandIs extends CommandBase {
    @Override
    public String getName() {
        return getCommandName();
    }

    @Override
    public String getUsage(ICommandSender sender){
        return getCommandUsage(sender);
    }


    public void execute(MinecraftServer server, ICommandSender sender, String[] args) throws CommandException{
        processCommand(sender,args);
    }

    public abstract String getCommandName();

    public abstract String getCommandUsage(ICommandSender sender);

    public abstract void processCommand(ICommandSender sender, String[] args) throws CommandException;
}
