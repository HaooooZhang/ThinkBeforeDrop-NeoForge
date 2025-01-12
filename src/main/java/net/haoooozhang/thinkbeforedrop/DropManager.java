package net.haoooozhang.thinkbeforedrop;

import me.shedaniel.autoconfig.AutoConfig;
import net.minecraft.core.component.DataComponents;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.*;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.ShulkerBoxBlock;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class DropManager {
    private static long lastDropTime = 0;
    private static int lastSlot = -1;
    private static boolean dropped = false;

    private static boolean shouldHandleDrop(ItemStack stack) {
        ModConfig config = AutoConfig.getConfigHolder(ModConfig.class).getConfig();
        if (!config.configenable) return false;
        Item item = stack.getItem();
        Block block = null;
        if (item instanceof BlockItem blockItem)
            block = blockItem.getBlock();
        ResourceLocation location = BuiltInRegistries.ITEM.getKey(item);//有问题
        String name = location.toString();
        if (config.internal.weapon)
            if (item instanceof SwordItem || item instanceof BowItem || item instanceof CrossbowItem || item instanceof TridentItem || item instanceof ArrowItem || item instanceof MaceItem)
                return true;
        if (config.internal.tool)
            if (item instanceof AxeItem || item instanceof PickaxeItem || item instanceof ShovelItem || item instanceof HoeItem)
                return true;
        if (config.internal.shulkerBox)
            if (block != null)
                if (block instanceof ShulkerBoxBlock)
                    return true;
        if (config.internal.armor)
            if (item instanceof ArmorItem || item instanceof ElytraItem)
                return true;
        //ore todo
        if (config.internal.disc)
            if (item instanceof DiscFragmentItem || item.components().has(DataComponents.JUKEBOX_PLAYABLE))
                return true;
        if (config.internal.uncommon)
            if (item.components().get(DataComponents.RARITY) == Rarity.UNCOMMON)
                return true;
        if (config.internal.rare)
            if (item.components().get(DataComponents.RARITY) == Rarity.RARE)
                return true;
        if (config.internal.epic)
            if (item.components().get(DataComponents.RARITY) == Rarity.EPIC)
                return true;
        if (config.internal.enchanted) {
            if (stack.isEnchanted())
                return true;
        }
        //hasNBT Todo
        //hasComponent Todo
        if (config.internal.enchantedBook)
            if (item instanceof EnchantedBookItem)
                return true;
        if (config.internal.book)
            if (item instanceof WritableBookItem || item instanceof WrittenBookItem)
                return true;
        return config.custom.customItems.contains(name);
    }

    public static boolean shouldThrow(ItemStack stack, int slot) {
        ModConfig config = AutoConfig.getConfigHolder(ModConfig.class).getConfig();
        if (slot != lastSlot) {
            lastDropTime = 0;
            dropped = false;
        }
        if (!shouldHandleDrop(stack) || dropped) return true;
        long now = System.currentTimeMillis();
        if (now - lastDropTime >= config.time.minSecond * 1000 && now - lastDropTime <= config.time.maxSecond * 1000) {
            if (stack.getCount() != 1)
                dropped = true;
            lastDropTime = 0;
            return true;
        }
        lastDropTime = now;
        lastSlot = slot;
        return false;
    }

    public static Component getWarningText() {
        return Component.translatable("tbt.warning");
    }
}
