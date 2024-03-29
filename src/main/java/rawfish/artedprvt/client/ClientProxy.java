package rawfish.artedprvt.client;

import net.minecraftforge.client.ClientCommandHandler;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import rawfish.artedprvt.command.CommandLoader;
import rawfish.artedprvt.common.CommonProxy;
import rawfish.artedprvt.core.rhino.ClassCollection;
import rawfish.artedprvt.core.rhino.McpToSrgString;
import rawfish.artedprvt.id.Local;

public class ClientProxy extends CommonProxy
{
    @Override
    public void preInit(FMLPreInitializationEvent event)
    {
        super.preInit(event);
    }

    @Override
    public void init(FMLInitializationEvent event)
    {
        super.init(event);

        new CommandLoader(ClientCommandHandler.instance);
        ClassCollection.load(McpToSrgString.getMcpToSrgString());
        Local.load();
    }

    @Override
    public void postInit(FMLPostInitializationEvent event)
    {
        super.postInit(event);
    }
}