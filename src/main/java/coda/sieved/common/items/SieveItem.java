package coda.sieved.common.items;

import coda.sieved.common.gui.SieveInventory;
import net.minecraft.core.particles.BlockParticleOption;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.Mth;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.Vec3;

public class SieveItem extends Item {

    public SieveItem() {
        super(new Item.Properties().tab(CreativeModeTab.TAB_TOOLS).stacksTo(1).durability(250));
    }

    @Override
    public void inventoryTick(ItemStack stcak, Level pLevel, Entity pEntity, int pSlotId, boolean pIsSelected) {
        if (getStackInventory(stcak).isDirty()) {
            //System.out.println(stcak.getTag().getInt("Material"));
        }

        super.inventoryTick(stcak, pLevel, pEntity, pSlotId, pIsSelected);
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
        ItemStack stack = player.getItemInHand(hand);
        ItemStack offhandStack = player.getItemInHand(InteractionHand.OFF_HAND);
        SieveInventory inventory = getStackInventory(stack);

        if (offhandStack.is(Items.COARSE_DIRT) && inventory.getItem(0).getCount() == 0) {
            inventory.setItem(0, offhandStack.split(1));

            BlockState state = Blocks.COARSE_DIRT.defaultBlockState();

            level.playSound(player, player.blockPosition(), SoundEvents.GRAVEL_BREAK, SoundSource.PLAYERS, 1.0F, 0.8F);

            Vec3 position = getYawVec(player.yBodyRot, -0.45D, 0.65D, 0.4D).add(player.position());
            for (int i = 0; i < 10; i++) {
                level.addParticle(new BlockParticleOption(ParticleTypes.BLOCK, state), position.x(), position.y(), position.z(), 0.0D, 0.0D, 0.0D);
            }

            if (!level.isClientSide && inventory.isDirty()) {
                inventory.write(stack);
                stack.getOrCreateTag().putInt("Material", Block.getId(state));
            }

            return InteractionResultHolder.pass(stack);
        }


        // todo - add to the final sifting stage:
        /*stack.hurtAndBreak(1, player, (p_41300_) -> {
            p_41300_.broadcastBreakEvent(hand);
        });*/

        return super.use(level, player, hand);
    }

    public static Vec3 getYawVec(float yaw, double xOffset, double yOffset, double zOffset) {
        return new Vec3(xOffset, yOffset, zOffset).yRot(-yaw * (Mth.PI / 180f));
    }

    private static SieveInventory getStackInventory(ItemStack stack) {
        SieveInventory inventory = new SieveInventory(1);
        if (!stack.isEmpty() && stack.hasTag()) {
            ListTag items = stack.getOrCreateTag().getList("Items", 10);
            for (int i = 0; i < items.size(); i++) {
                CompoundTag item = items.getCompound(i);
                inventory.setItem(item.getByte("Slot"), ItemStack.of(item));
            }
        }
        return inventory;
    }
}
