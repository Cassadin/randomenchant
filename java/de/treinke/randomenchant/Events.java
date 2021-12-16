package de.treinke.randomenchant;

import converter.*;
import net.minecraft.client.*;
import net.minecraft.nbt.*;
import net.minecraft.server.level.*;
import net.minecraft.world.item.*;
import net.minecraft.world.item.enchantment.*;
import net.minecraft.world.level.*;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.material.*;
import net.minecraftforge.api.distmarker.*;
import net.minecraftforge.event.entity.living.*;
import net.minecraftforge.event.world.*;
import net.minecraftforge.eventbus.api.*;
import net.minecraftforge.fml.common.*;
import net.minecraftforge.fmllegacy.server.*;

import java.io.*;
import java.util.*;

@Mod.EventBusSubscriber(modid = "randomenchant", bus = Mod.EventBusSubscriber.Bus.FORGE)
public class Events {

    public static int maxChanced = 100;
    public static int perBlock = 1;
    public static int perMob = 5;
    public static boolean animalCounts = false;
    public static boolean leavesCounts = true;
    private static Random rnd = new Random();


    @SubscribeEvent
    public static void breakblock(BlockEvent.BreakEvent event) {
        if(!Minecraft.getInstance().level.isClientSide) {
            if (event.getState().getBlock().getPistonPushReaction(event.getState()) == PushReaction.NORMAL
                    || (
                    (event.getState().getBlock() == Blocks.BIRCH_LEAVES
                            || event.getState().getBlock() == Blocks.DARK_OAK_LEAVES
                            || event.getState().getBlock() == Blocks.JUNGLE_LEAVES
                            || event.getState().getBlock() == Blocks.OAK_LEAVES
                            || event.getState().getBlock() == Blocks.SPRUCE_LEAVES
                    ) && leavesCounts)
            ) {
                checkEnchantments((ServerPlayer) event.getPlayer(), perBlock);
            }
        }
    }

    private static void checkEnchantments(ServerPlayer player, int value) {
        List<ItemStack> itemlist = new ArrayList<>();

        if(Main.fittingItemType(player.containerMenu.slots.get(5).getItem())) itemlist.add(player.containerMenu.slots.get(5).getItem());
        if(Main.fittingItemType(player.containerMenu.slots.get(6).getItem())) itemlist.add(player.containerMenu.slots.get(6).getItem());
        if(Main.fittingItemType(player.containerMenu.slots.get(7).getItem())) itemlist.add(player.containerMenu.slots.get(7).getItem());
        if(Main.fittingItemType(player.containerMenu.slots.get(8).getItem())) itemlist.add(player.containerMenu.slots.get(8).getItem());
        if(Main.fittingItemType(player.containerMenu.slots.get(45).getItem())) itemlist.add(player.containerMenu.slots.get(45).getItem());
        if(Main.fittingItemType(player.getMainHandItem())) itemlist.add(player.getMainHandItem());

        boolean retry = true;

        while(retry && itemlist.size() > 0) {
            int pos = rnd.nextInt(itemlist.size());
            String type = ItemType.get(itemlist.get(pos));

            if (type != null) {
                List<EnchantmentInfo> enchantments = Enchantmentlist.get(type);
                if(enchantments != null) {
                    if(enchantments.size() > 0) {
                        int enc_pos = rnd.nextInt(enchantments.size());

                        retry = enchantments.get(enc_pos).enchant(player, itemlist.get(pos), value);
                    }
                }
            }
        }
        itemlist.clear();
    }


    @SubscribeEvent
    public static void onEntityDrop(LivingDeathEvent event) {
        if(!Minecraft.getInstance().level.isClientSide) {

            if ((event.getEntityLiving() instanceof MonsterEntity) || (event.getEntityLiving() instanceof AnimalEntity && animalCounts)) {
                if (event.getSource().getEntity() instanceof PlayerEntity) {
                    checkEnchantments((ServerPlayer) event.getSource().getEntity(), perMob);
                }
            }
        }
    }
}
