package de.treinke.randomenchant;

import net.minecraft.nbt.*;
import net.minecraft.server.level.*;
import net.minecraft.world.item.*;
import net.minecraft.world.item.enchantment.*;
import net.minecraftforge.fmllegacy.server.*;

import java.util.*;

import static de.treinke.randomenchant.Events.maxChanced;

public class EnchantmentInfo {
    private Enchantment enc;
    private List<Enchantment> blocked_by;
    private int max_level;

    public EnchantmentInfo(String name, List<Enchantment> block, int lvl)
    {
        this.enc = EnchantmentName.get(name);
        this.blocked_by = block;
        this.max_level = lvl;
    }

    public boolean enchant(ServerPlayer player, ItemStack item, int points) {
        Map<Enchantment, Integer> enclist = EnchantmentHelper.getEnchantments(item);

        CompoundTag nbt = item.getOrCreateTagElement("randomenchante");
        if(!nbt.contains("value")) {
            nbt.putInt("value", 0);
            nbt.putInt("maxvalue",maxChanced);
        }
        int vp = nbt.getInt("value");

        if(vp+points >= maxChanced) {
            int level = 1;
            if (enclist.containsKey(enc)) {
                if (!(enclist.get(enc) < max_level))
                    return true;
                level = enclist.get(enc) + 1;
            }

            for (int i = 0; i < blocked_by.size(); i++) {
                if (enclist.containsKey(blocked_by.get(i)))
                    return true;
            }
            vp = vp+points-maxChanced;


            enclist.put(enc, level);
            EnchantmentHelper.setEnchantments(enclist, item);

            String tellraw = "tellraw " + player.getName().getString() + " [\"\",";
            tellraw += "{\"text\":\"Dein Item \"},";
            tellraw += "{\"color\":\"green\",\"translate\":\"item.minecraft." + item.getItem().toString() + "\"},";
            tellraw += "{\"text\":\" hat nun die Verzauberung \"},";
            tellraw += "{\"color\":\"red\",\"translate\":\"enchantment.minecraft." + enc.getRegistryName().getPath() + "\"}";
            if (level > 1)
                tellraw += ",{\"color\":\"red\",\"text\":\" " + level + "\"}";
            tellraw += "]";
            ServerLifecycleHooks.getCurrentServer().getCommands().performCommand(ServerLifecycleHooks.getCurrentServer().createCommandSourceStack(), tellraw);
        }else{
            vp = vp+points;
        }
        nbt.putInt("value",vp);

        return false;
    }
}

