package rawfish.artedprvt.mi;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.network.play.server.SPacketChat;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.world.WorldServer;
import net.minecraftforge.fml.common.FMLCommonHandler;
import rawfish.artedprvt.core.ProgramUsable;
import rawfish.artedprvt.core.ScriptObject;
import rawfish.artedprvt.core.SideUsable;
import rawfish.artedprvt.core.Sides;

@SideUsable(Sides.SERVER)
@ProgramUsable
public class GameServer implements ScriptObject {
    public MinecraftServer minecraft;

    @ProgramUsable
    public GameServer(){
        up();
        minecraft = FMLCommonHandler.instance().getMinecraftServerInstance();
    }

    @ProgramUsable
    public WorldServer getWorld(int i){
        return minecraft.worlds[i];
    }

    @ProgramUsable
    public EntityPlayerMP getPlayer(WorldServer world, String name){
        return (EntityPlayerMP)world.getPlayerEntityByName(name);
    }

    @ProgramUsable
    public void sendChat(WorldServer world,String chat){
        SPacketChat packetChat=new SPacketChat(new TextComponentString(chat));
        for(EntityPlayer player:world.playerEntities){
            if(player instanceof EntityPlayerMP){
                ((EntityPlayerMP) player).connection.sendPacket(packetChat);
            }
        }
    }

    @Override
    public void onClose() {
        minecraft =null;
    }
}
