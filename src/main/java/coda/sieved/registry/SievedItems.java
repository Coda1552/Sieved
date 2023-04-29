package coda.sieved.registry;

import coda.sieved.Sieved;
import coda.sieved.common.items.SieveItem;
import net.minecraft.world.item.Item;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class SievedItems {
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, Sieved.MOD_ID);

    public static final RegistryObject<Item> SIEVE = ITEMS.register("copper_sieve", () -> new SieveItem());
}
