package rawfish.artedprvt.conn;

import net.minecraft.command.ICommandSender;
import net.minecraft.util.text.TextComponentString;

public class ChatOut {
    public ICommandSender sender;

    public ChatOut(ICommandSender sender){
        this.sender=sender;
    }

    public void print(String str){
        sender.sendMessage(new TextComponentString(str));
    }
}
