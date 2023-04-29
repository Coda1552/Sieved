package coda.sieved;

import coda.sieved.registry.SievedItems;
import net.minecraft.client.renderer.item.ItemProperties;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod(Sieved.MOD_ID)
public class Sieved {
    public static final String MOD_ID = "sieved";

    public Sieved() {
        IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();
        IEventBus forgeBus = MinecraftForge.EVENT_BUS;

        SievedItems.ITEMS.register(bus);

        bus.addListener(this::registerClient);
    }

    // todo - fix item model overrides not working
    private void registerClient(FMLClientSetupEvent event) {
        ItemProperties.register(SievedItems.SIEVE.get(), new ResourceLocation(Sieved.MOD_ID, "material"), (stack, world, player, i) ->  stack.hasTag() ? stack.getTag().getInt("Material") : 0);
    }
}
