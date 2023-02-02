package rawfish.artedprvt.script;

import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.play.client.CPacketChatMessage;
import net.minecraft.network.play.client.CPacketUseEntity;
import net.minecraft.util.EnumHand;
import org.apache.commons.io.input.ReaderInputStream;
import org.mozilla.javascript.NativeJavaObject;
import org.mozilla.javascript.Scriptable;

import java.util.List;

/**
 * 提供客户端专用功能
 */
public class ScriptClient {
    public Minecraft minecraft;
    public NetworkManager manager;
    public ScriptClient(){
        minecraft=Minecraft.getMinecraft();
        manager=minecraft.getConnection().getNetworkManager();

    }

    /**
     * 发送消息数据包
     * @param message 消息
     */
    public void sendChat(String message) {
        manager.sendPacket(new CPacketChatMessage(message));
    }

    /**
     * 发送交互实体数据包
     * @param entity 攻击的实体
     */
    public void sendUse(Entity entity){
        manager.sendPacket(new CPacketUseEntity(entity, EnumHand.MAIN_HAND));
    }

    /**
     * 获取所有实体
     * @return
     */
    public List<Entity> getAllEntity(){
        return Minecraft.getMinecraft().world.getLoadedEntityList();
    }

    /**
     * 获取自己实体
     * @return
     */
    public EntityPlayerSP getThisPlayer(){
        return minecraft.player;
    }

    public Object getDemoObject(Scriptable scope){
        EntityPlayerSP p=getThisPlayer();
        return new NativeJavaObject(scope,p,p.getClass()){
            @Override
            public Object get(String name, Scriptable start) {
                System.out.println("访问字段: "+name);
                return super.get(name,start);
            }
        };
    }
}
