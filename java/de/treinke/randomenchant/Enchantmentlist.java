package de.treinke.randomenchant;

import net.minecraft.world.item.enchantment.*;

import java.util.*;

public class Enchantmentlist {
    private static HashMap<String, List<EnchantmentInfo>> Possible_Enchantments = new HashMap<>();

    public static void addPossibleEnchantment(String line) {

        String[] fdata = line.split(":");

        if (fdata[0].toUpperCase().equals("ARMOR")) {
            if (!Possible_Enchantments.containsKey("BOOTS"))
                Possible_Enchantments.put("BOOTS", new ArrayList<>());
            if (!Possible_Enchantments.containsKey("HELMET"))
                Possible_Enchantments.put("HELMET", new ArrayList<>());
            if (!Possible_Enchantments.containsKey("PANTS"))
                Possible_Enchantments.put("PANTS", new ArrayList<>());
            if (!Possible_Enchantments.containsKey("CHESTPLATE"))
                Possible_Enchantments.put("CHESTPLATE", new ArrayList<>());
        } else {
            if (!Possible_Enchantments.containsKey(fdata[0].toUpperCase()))
                Possible_Enchantments.put(fdata[0].toUpperCase(), new ArrayList<>());
        }


        int amounts = Integer.parseInt(fdata[2]);
        List<Enchantment> blocker = new ArrayList<>();

        if (fdata.length > 3)
            for (int j = 4; j < fdata.length; j++) {
                Enchantment e = EnchantmentName.get(fdata[j]);
                if (e != null)
                    blocker.add(e);
            }

        int lvl = Integer.parseInt(fdata[2]);
        for (int i = 0; i < amounts; i++) {
            if (fdata[0].toUpperCase().equals("ARMOR")) {
                Possible_Enchantments.get("BOOTS").add(new EnchantmentInfo(fdata[1], blocker, lvl));
                Possible_Enchantments.get("CHESTPLATE").add(new EnchantmentInfo(fdata[1], blocker, lvl));
                Possible_Enchantments.get("HELMET").add(new EnchantmentInfo(fdata[1], blocker, lvl));
                Possible_Enchantments.get("PANTS").add(new EnchantmentInfo(fdata[1], blocker, lvl));
            } else {
                Possible_Enchantments.get(fdata[0].toUpperCase()).add(new EnchantmentInfo(fdata[1], blocker, lvl));
            }

        }

    }

    public static List<EnchantmentInfo> get(String type) {
        return Possible_Enchantments.get(type);
    }
}
