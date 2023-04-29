package coda.sieved;

import coda.sieved.registry.SievedItems;
import net.minecraft.client.renderer.item.ItemProperties;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

@Mod.EventBusSubscriber(modid = Sieved.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class ClientEvents {

    @SubscribeEvent
    public static void clientSetup(FMLClientSetupEvent e) {
        e.enqueueWork(() -> {
            ItemProperties.register(SievedItems.SIEVE.get(), new ResourceLocation(Sieved.MOD_ID, "material"), (stack, world, player, i) -> stack.hasTag() ? stack.getTag().getInt("Material") : 0);
            System.out.println("Item Properties registered...hopefully");
        });
    }

}
