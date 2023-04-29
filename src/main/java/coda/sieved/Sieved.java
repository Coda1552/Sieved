package coda.sieved;

import coda.sieved.registry.SievedItems;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod(Sieved.MOD_ID)
public class Sieved {
    public static final String MOD_ID = "sieved";

    public Sieved() {
        IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();
        IEventBus forgeBus = MinecraftForge.EVENT_BUS;

        SievedItems.ITEMS.register(bus);
    }
}
