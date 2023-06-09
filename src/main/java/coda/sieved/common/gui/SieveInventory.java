package coda.sieved.common.gui;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.item.ItemStack;

public class SieveInventory extends SimpleContainer {
    private boolean isDirty;

    public SieveInventory(int size) {
        super(size);
    }

    public boolean isDirty() {
        return isDirty;
    }

    @Override
    public void setChanged() {
        super.setChanged();
        isDirty = true;
    }

    public void write(ItemStack stack) {
        CompoundTag tag = new CompoundTag();
        ListTag list = new ListTag();
        for (int i = 0; i < getContainerSize(); i++) {
            CompoundTag item = new CompoundTag();
            item.putByte("Slot", (byte) i);
            list.add(getItem(i).save(item));
        }
        tag.put("Items", list);
        stack.setTag(tag);
        isDirty = false;
    }
}
