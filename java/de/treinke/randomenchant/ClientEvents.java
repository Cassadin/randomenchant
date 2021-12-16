package de.treinke.randomenchant;

import net.minecraft.nbt.*;
import net.minecraft.network.chat.*;
import net.minecraft.world.item.*;
import net.minecraftforge.api.distmarker.*;
import net.minecraftforge.event.entity.player.*;
import net.minecraftforge.eventbus.api.*;
import net.minecraftforge.fml.common.*;

import java.util.*;

@Mod.EventBusSubscriber(modid = "randomenchant", bus = Mod.EventBusSubscriber.Bus.FORGE, value = Dist.CLIENT)
public class ClientEvents {

    @SubscribeEvent
    public static void onToolTip(ItemTooltipEvent event)
    {
        ItemStack item = event.getItemStack();
        if (Main.fittingItemType(item))
        {
            List<Component> tooltop = event.getToolTip();
            CompoundTag nbt = item.getTag();
            if(nbt != null) {
                if (!nbt.contains("value")) {
                    nbt.putInt("value", 0);
                    nbt.putInt("maxvalue", 100);
                }
                int vp = nbt.getInt("value");
                int maxChanced = nbt.getInt("maxvalue");

                System.out.println(item.getDisplayName().getString() + " Erfahrung: " + vp + "/" + maxChanced);
                if (vp > 0) {
                    tooltop.add(new TextComponent("Erfahrung: " + vp + "/" + maxChanced));
                }
            }
        }
    }
}
