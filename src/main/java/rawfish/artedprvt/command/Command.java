package rawfish.artedprvt.command;

import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.WorldServer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public abstract class Command extends CommandBase {
    public abstract void process(List<String> args);
    public abstract List<String> complete(List<String> args);

    public String commandName;

    public Command(String commandName){
        this.commandName=commandName;
    }
    @Override
    public String getName() {
        return commandName;
    }

    @Override
    public String getUsage(ICommandSender sender) {
        return "commands."+commandName+".usage";
    }

    public static final List<String> nullTab=new ArrayList<>();

    @Override
    public void execute(MinecraftServer server, ICommandSender sender, String[] args) throws CommandException {
        //去空参数
        List<String> slist=new ArrayList<>();
        for(String arg:args){
            if(!arg.equals("")){
                slist.add(arg);
            }
        }
        process(slist);
    }

    @Override
    public List<String> getTabCompletions(MinecraftServer server, ICommandSender sender, String[] args, BlockPos pos){
        if(!(sender.getEntityWorld() instanceof WorldServer)){
            return complete(Arrays.asList(args));
        }
        return nullTab;
    }

    @Override
    public int getRequiredPermissionLevel() {
        return 0;
    }
}
