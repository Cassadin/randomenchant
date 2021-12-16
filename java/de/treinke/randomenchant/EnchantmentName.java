package de.treinke.randomenchant;

import net.minecraft.world.item.enchantment.*;

import java.util.*;

public class EnchantmentName {
    private static Map<String,Enchantment> list;
    static {
        list = new HashMap<>();
        list.put("FORTUNE",Enchantments.BLOCK_FORTUNE);
        list.put("MENDING",Enchantments.MENDING);
        list.put("EFFICIENCY",Enchantments.BLOCK_EFFICIENCY);
        list.put("UNBREAKING",Enchantments.UNBREAKING);
        list.put("SILK_TOUCH",Enchantments.SILK_TOUCH);
        list.put("SMITE",Enchantments.SMITE);
        list.put("SHARPNESS",Enchantments.SHARPNESS);
        list.put("FLAMING_ARROWS",Enchantments.FLAMING_ARROWS);
        list.put("POWER_ARROWS",Enchantments.POWER_ARROWS);
        list.put("PUNCH_ARROWS",Enchantments.PUNCH_ARROWS);
        list.put("INFINITY_ARROWS",Enchantments.INFINITY_ARROWS);
        list.put("BANE_OF_ARTHROPODS",Enchantments.BANE_OF_ARTHROPODS);
        list.put("FIRE_ASPECT",Enchantments.FIRE_ASPECT);
        list.put("KNOCKBACK",Enchantments.KNOCKBACK);
        list.put("MOB_LOOTING",Enchantments.MOB_LOOTING);
        list.put("SWEEPING_EDGE",Enchantments.SWEEPING_EDGE);
        list.put("LUCK",Enchantments.FISHING_LUCK);
        list.put("LURE",Enchantments.FISHING_SPEED);
        list.put("CHANNELING",Enchantments.CHANNELING);
        list.put("IMPALING",Enchantments.IMPALING);
        list.put("LOYALTY",Enchantments.LOYALTY);
        list.put("RIPTIDE",Enchantments.RIPTIDE);
        list.put("MULTISHOT",Enchantments.MULTISHOT);
        list.put("PIERCING",Enchantments.PIERCING);
        list.put("QUICK_CHARGE",Enchantments.QUICK_CHARGE);
        list.put("BLAST_PROTECTION",Enchantments.BLAST_PROTECTION);
        list.put("FIRE_PROTECTION",Enchantments.FIRE_PROTECTION);
        list.put("PROJECTILE_PROTECTION",Enchantments.PROJECTILE_PROTECTION);
        list.put("ALL_DAMAGE_PROTECTION",Enchantments.ALL_DAMAGE_PROTECTION);
        list.put("THORNS",Enchantments.THORNS);
        list.put("AQUA_AFFINITY",Enchantments.AQUA_AFFINITY);
        list.put("RESPIRATION",Enchantments.RESPIRATION);
        list.put("DEPTH_STRIDER",Enchantments.DEPTH_STRIDER);
        list.put("FALL_PROTECTION",Enchantments.FALL_PROTECTION);
        list.put("SOUL_SPEED",Enchantments.SOUL_SPEED);
        list.put("FROSTWALKER",Enchantments.FROST_WALKER);
    }

    public static Enchantment get(String name)
    {
        if(list.containsKey(name))
            return list.get(name);
        return null;
    }
}
