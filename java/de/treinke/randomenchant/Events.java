package de.treinke.randomenchant;

import converter.*;
import net.minecraft.nbt.*;
import net.minecraft.server.level.*;
import net.minecraft.world.item.*;
import net.minecraft.world.item.enchantment.*;
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

@Mod.EventBusSubscriber(modid = "randomenchant", bus = Mod.EventBusSubscriber.Bus.FORGE, value = Dist.DEDICATED_SERVER)
public class Events {

    public static HashMap<String, Integer> chances = new HashMap<>();
    public static int maxChanced = 100;
    public static int perBlock = 1;
    public static int perMob = 5;
    public static boolean animalCounts = false;
    public static boolean leavesCounts = true;
    public static boolean global_enchantment = false;
    private static Random rnd = new Random();


    private static HashMap<String, List<EnchantmentInfo>> Possible_Enchantments = new HashMap<>();


    @SubscribeEvent
    public static void breakblock(BlockEvent.BreakEvent event) {

        if (event.getState().getBlock().getPistonPushReaction(event.getState()) == PushReaction.NORMAL
                ||(
                (event.getState().getBlock() == Blocks.BIRCH_LEAVES
                || event.getState().getBlock() == Blocks.DARK_OAK_LEAVES
                || event.getState().getBlock() == Blocks.JUNGLE_LEAVES
                || event.getState().getBlock() == Blocks.OAK_LEAVES
                || event.getState().getBlock() == Blocks.SPRUCE_LEAVES
                )&&leavesCounts)
        ) {
            checkEnchantments((ServerPlayer)event.getPlayer(), perBlock);
        }
    }

    private static void checkEnchantments(ServerPlayer player, int value) {
        List<ItemStack> itemlist = new ArrayList<>();
        for (int i = 0; i < player.containerMenu.slots.size(); i++) {
            ItemStack item = player.containerMenu.slots.get(i).getItem();

            if(global_enchantment) {
                if (item.getItem() instanceof PickaxeItem
                        || item.getItem() instanceof AxeItem
                        || item.getItem() instanceof ShovelItem
                        || item.getItem() instanceof HoeItem
                        || item.getItem() instanceof BowItem
                        || item.getItem() instanceof SwordItem
                        || item.getItem() instanceof ShieldItem
                        || item.getItem() instanceof FishingRodItem
                        || item.getItem() instanceof TridentItem
                        || item.getItem() instanceof CrossbowItem
                        || item.getItem() instanceof ArmorItem
                        || item.getItem() instanceof ElytraItem
                        || item.getItem() instanceof ShearsItem
                ) {
                    itemlist.add(item);
                }
            }else{
                switch(i)
                {
                    case 5:
                    case 6:
                    case 7:
                    case 8:
                    case 45:
                        if (item.getItem() instanceof PickaxeItem
                                || item.getItem() instanceof AxeItem
                                || item.getItem() instanceof ShovelItem
                                || item.getItem() instanceof HoeItem
                                || item.getItem() instanceof BowItem
                                || item.getItem() instanceof SwordItem
                                || item.getItem() instanceof ShieldItem
                                || item.getItem() instanceof FishingRodItem
                                || item.getItem() instanceof TridentItem
                                || item.getItem() instanceof CrossbowItem
                                || item.getItem() instanceof ArmorItem
                                || item.getItem() instanceof ElytraItem
                                || item.getItem() instanceof ShearsItem
                        ) {
                            itemlist.add(item);
                        }
                        break;
                    default:
                        break;
                }
            }
        }

        if(!global_enchantment) {
            ItemStack item = player.getMainHandItem();

            if (item.getItem() instanceof PickaxeItem
                    || item.getItem() instanceof AxeItem
                    || item.getItem() instanceof ShovelItem
                    || item.getItem() instanceof HoeItem
                    || item.getItem() instanceof BowItem
                    || item.getItem() instanceof SwordItem
                    || item.getItem() instanceof ShieldItem
                    || item.getItem() instanceof FishingRodItem
                    || item.getItem() instanceof TridentItem
                    || item.getItem() instanceof CrossbowItem
                    || item.getItem() instanceof ArmorItem
                    || item.getItem() instanceof ElytraItem
                    || item.getItem() instanceof ShearsItem
            ) {
                itemlist.add(item);
            }
        }


        boolean retry = true;

        while(retry && itemlist.size() > 0) {
            int pos = rnd.nextInt(itemlist.size());
            String type = Events.getItemType(itemlist.get(pos));

            if (type != null) {
                List<EnchantmentInfo> enchantments = Possible_Enchantments.get(type);
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

    private static String getItemType(ItemStack enc) {
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



    private static void saveLevels() {
        /*
        try (FileWriter writer = new FileWriter(ServerLifecycleHooks.getCurrentServer().getWorldData().getLevelName() + File.separator + "randomenchant.levels")) {

            BufferedWriter bw = new BufferedWriter(writer);
            boolean first = true;
            for (String key : chances.keySet()) {
                if (first)
                    bw.write(key + "=" + chances.get(key));
                else
                    bw.append("\n" + key + "=" + chances.get(key));
            }
            bw.close();

        } catch (Exception ex) {
            System.out.println("Fehler beim Speichern der Einstellungen: " + ex.getMessage());
        }*/
    }

    public static void addPossibleEnchantment(String line) {

        String[] fdata = line.split(":");

        if(fdata[0].toUpperCase().equals("ARMOR"))
        {
            if(!Possible_Enchantments.containsKey("BOOTS"))
                Possible_Enchantments.put("BOOTS",new ArrayList<>());
            if(!Possible_Enchantments.containsKey("HELMET"))
                Possible_Enchantments.put("HELMET",new ArrayList<>());
            if(!Possible_Enchantments.containsKey("PANTS"))
                Possible_Enchantments.put("PANTS",new ArrayList<>());
            if(!Possible_Enchantments.containsKey("CHESTPLATE"))
                Possible_Enchantments.put("CHESTPLATE",new ArrayList<>());
        }
        else
        {
            if(!Possible_Enchantments.containsKey(fdata[0].toUpperCase()))
                Possible_Enchantments.put(fdata[0].toUpperCase(),new ArrayList<>());
        }


        int amounts = Integer.parseInt(fdata[2]);
        List<Enchantment> blocker = new ArrayList<>();

        if(fdata.length>3)
            for(int j = 4; j < fdata.length; j++)
            {
                Enchantment e = Events.name2Enchantment(fdata[j]);
                if(e != null)
                    blocker.add(e);
            }

        int lvl = Integer.parseInt(fdata[2]);
        for(int i = 0; i < amounts; i++) {
            if(fdata[0].toUpperCase().equals("ARMOR"))
            {
                Possible_Enchantments.get("BOOTS").add(new EnchantmentInfo(fdata[1], blocker,lvl));
                Possible_Enchantments.get("CHESTPLATE").add(new EnchantmentInfo(fdata[1], blocker,lvl));
                Possible_Enchantments.get("HELMET").add(new EnchantmentInfo(fdata[1], blocker,lvl));
                Possible_Enchantments.get("PANTS").add(new EnchantmentInfo(fdata[1], blocker,lvl));
            }
            else
            {
                Possible_Enchantments.get(fdata[0].toUpperCase()).add(new EnchantmentInfo(fdata[1], blocker,lvl));
            }

        }

    }

    private static Enchantment name2Enchantment(String fname) {

        switch(fname.toUpperCase())
        {
            case "FORTUNE": return Enchantments.BLOCK_FORTUNE;
            case "MENDING": return Enchantments.MENDING;
            case "EFFICIENCY": return Enchantments.BLOCK_EFFICIENCY;
            case "UNBREAKING": return Enchantments.UNBREAKING;
            case "SILK_TOUCH": return Enchantments.SILK_TOUCH;
            case "SMITE": return Enchantments.SMITE;
            case "SHARPNESS": return Enchantments.SHARPNESS;
            case "FLAMING_ARROWS": return Enchantments.FLAMING_ARROWS;
            case "POWER_ARROWS": return Enchantments.POWER_ARROWS;
            case "PUNCH_ARROWS": return Enchantments.PUNCH_ARROWS;
            case "INFINITY_ARROWS": return Enchantments.INFINITY_ARROWS;
            case "BANE_OF_ARTHROPODS": return Enchantments.BANE_OF_ARTHROPODS;
            case "FIRE_ASPECT": return Enchantments.FIRE_ASPECT;
            case "KNOCKBACK": return Enchantments.KNOCKBACK;
            case "MOB_LOOTING": return Enchantments.MOB_LOOTING;
            case "SWEEPING_EDGE": return Enchantments.SWEEPING_EDGE;
            case "LUCK": return Enchantments.FISHING_LUCK;
            case "LURE": return Enchantments.FISHING_SPEED;
            case "CHANNELING": return Enchantments.CHANNELING;
            case "IMPALING": return Enchantments.IMPALING;
            case "LOYALTY": return Enchantments.LOYALTY;
            case "RIPTIDE": return Enchantments.RIPTIDE;
            case "MULTISHOT": return Enchantments.MULTISHOT;
            case "PIERCING": return Enchantments.PIERCING;
            case "QUICK_CHARGE": return Enchantments.QUICK_CHARGE;
            case "BLAST_PROTECTION": return Enchantments.BLAST_PROTECTION;
            case "FIRE_PROTECTION": return Enchantments.FIRE_PROTECTION;
            case "PROJECTILE_PROTECTION": return Enchantments.PROJECTILE_PROTECTION;
            case "ALL_DAMAGE_PROTECTION": return Enchantments.ALL_DAMAGE_PROTECTION;
            case "THORNS": return Enchantments.THORNS;
            case "AQUA_AFFINITY": return Enchantments.AQUA_AFFINITY;
            case "RESPIRATION": return Enchantments.RESPIRATION;
            case "DEPTH_STRIDER": return Enchantments.DEPTH_STRIDER;
            case "FALL_PROTECTION": return Enchantments.FALL_PROTECTION;
            case "SOUL_SPEED": return Enchantments.SOUL_SPEED;
            case "FROSTWALKER": return Enchantments.FROST_WALKER;
        }
        return null;
    }

    @SubscribeEvent
    public static void onEntityDrop(LivingDeathEvent event) {

        if ((event.getEntityLiving() instanceof MonsterEntity)||(event.getEntityLiving() instanceof AnimalEntity && animalCounts))
        {
            if (event.getSource().getEntity() instanceof PlayerEntity) {
                checkEnchantments((ServerPlayer)event.getSource().getEntity(), perMob);
            }
        }

        if (event.getEntity() instanceof PlayerEntity) {
            chances.put(event.getEntity().getUUID().toString(), 0);
        }
    }

    public static class EnchantmentInfo {
        private Enchantment enc;
        private List<Enchantment> blocked_by;
        private int max_level;

        public EnchantmentInfo(String name, List<Enchantment> block, int lvl)
        {
            this.enc = name2Enchantment(name);
            this.blocked_by = block;
            this.max_level = lvl;
        }

        public boolean enchant(ServerPlayer player, ItemStack item, int points) {
            Map<Enchantment, Integer> enclist = EnchantmentHelper.getEnchantments(item);

            CompoundTag nbt = item.getOrCreateTag();
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
}
