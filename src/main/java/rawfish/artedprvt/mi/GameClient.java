package rawfish.artedprvt.mi;

import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.client.multiplayer.WorldClient;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.play.client.CPacketChatMessage;
import rawfish.artedprvt.core.ProgramUsable;
import rawfish.artedprvt.core.ScriptObject;
import rawfish.artedprvt.core.SideUsable;
import rawfish.artedprvt.core.Sides;

/**
 * 游戏客户端
 */
@SideUsable(Sides.CLIENT)
@ProgramUsable
public class GameClient implements ScriptObject {
    public Minecraft minecraft;
    public NetworkManager networkManager;

    @ProgramUsable
    public GameClient(){
        up();
        minecraft=Minecraft.getMinecraft();
        networkManager=minecraft.getConnection().getNetworkManager();
    }

    /**
     * 获取自己世界
     * @return
     */
    @ProgramUsable
    public WorldClient getWorld(){
        return minecraft.world;
    }

    /**
     * 获取自己玩家
     * @return
     */
    @ProgramUsable
    public EntityPlayerSP getPlayer(){
        return minecraft.player;
    }

    /**
     * 发送数据包
     * @param packet 数据包
     */
    @ProgramUsable
    public void sendPacket(Packet packet){
        networkManager.sendPacket(packet);
    }

    /**
     * 发送聊天数据包
     * @param chat 聊天信息
     */
    @ProgramUsable
    public void sendChat(String chat){
        sendPacket(new CPacketChatMessage(chat));
    }

    @Override
    public void onClose() {
        minecraft=null;
        networkManager=null;
    }
}
