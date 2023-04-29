package coda.sieved.registry;

import coda.sieved.Sieved;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;

public class SievedTags {
    public static final TagKey<Item> SIFTABLE = bind("siftable");

    private static TagKey<Item> bind(String pName) {
        return TagKey.create(Registry.ITEM_REGISTRY, new ResourceLocation(Sieved.MOD_ID, pName));
    }

}
