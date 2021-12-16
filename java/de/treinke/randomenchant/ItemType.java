package de.treinke.randomenchant;

import net.minecraft.world.item.*;

public class ItemType {
    public static String get(ItemStack enc) {
        if (enc.getItem() instanceof PickaxeItem) return "PICKAXE";
        if (enc.getItem() instanceof AxeItem) return "AXE";
        if (enc.getItem() instanceof ShovelItem) return "SHOVEL";
        if (enc.getItem() instanceof HoeItem) return "HOE";
        if (enc.getItem() instanceof BowItem) return "BOW";
        if (enc.getItem() instanceof SwordItem) return "SWORD";
        if (enc.getItem() instanceof ShieldItem) return "SHIELD";
        if (enc.getItem() instanceof FishingRodItem) return "FISHING_ROD";
        if (enc.getItem() instanceof ShieldItem) return "SHIELD";
        if (enc.getItem() instanceof TridentItem) return "TRIDENT";
        if (enc.getItem() instanceof CrossbowItem) return "CROSSBOW";
        if (enc.getItem() instanceof ElytraItem) return "ELYTRA";
        if (enc.getItem() instanceof ShearsItem) return "SHEARS";
        if (enc.getItem() == Items.DIAMOND_HELMET
                || enc.getItem() == Items.GOLDEN_HELMET
                || enc.getItem() == Items.LEATHER_HELMET
                || enc.getItem() == Items.TURTLE_HELMET
                || enc.getItem() == Items.CHAINMAIL_HELMET
                || enc.getItem() == Items.NETHERITE_HELMET
                || enc.getItem() == Items.IRON_HELMET
        ) {
            return "HELMET";
        }
        if (enc.getItem() == Items.DIAMOND_BOOTS
                || enc.getItem() == Items.GOLDEN_BOOTS
                || enc.getItem() == Items.LEATHER_BOOTS
                || enc.getItem() == Items.CHAINMAIL_BOOTS
                || enc.getItem() == Items.NETHERITE_BOOTS
                || enc.getItem() == Items.IRON_BOOTS
        ) {
            return "BOOTS";
        }

        if (enc.getItem() == Items.DIAMOND_CHESTPLATE
                || enc.getItem() == Items.GOLDEN_CHESTPLATE
                || enc.getItem() == Items.LEATHER_CHESTPLATE
                || enc.getItem() == Items.CHAINMAIL_CHESTPLATE
                || enc.getItem() == Items.NETHERITE_CHESTPLATE
                || enc.getItem() == Items.CHAINMAIL_CHESTPLATE
        ) {
            return "CHESTPLATE";
        }

        if (enc.getItem() == Items.DIAMOND_LEGGINGS
                || enc.getItem() == Items.GOLDEN_LEGGINGS
                || enc.getItem() == Items.LEATHER_LEGGINGS
                || enc.getItem() == Items.CHAINMAIL_LEGGINGS
                || enc.getItem() == Items.NETHERITE_LEGGINGS
                || enc.getItem() == Items.IRON_LEGGINGS
        ) {
            return "PANTS";
        }

        return null;
    }
}
