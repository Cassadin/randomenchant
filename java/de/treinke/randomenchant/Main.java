package de.treinke.randomenchant;


import net.minecraft.world.item.*;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.InterModComms;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.event.lifecycle.InterModEnqueueEvent;
import net.minecraftforge.fml.event.lifecycle.InterModProcessEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.fmllegacy.server.ServerLifecycleHooks;
import net.minecraftforge.fmlserverevents.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.*;

// The value here should match an entry in the META-INF/mods.toml file
@Mod("randomenchant")
public class Main
{
    // Directly reference a log4j logger.
    public static final String MODID = "minecraftguilds";
    private static final Logger LOGGER = LogManager.getLogger(MODID);

    public Main() {


        // Register the setup method for modloading
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::setup);
        // Register the enqueueIMC method for modloading
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::enqueueIMC);
        // Register the processIMC method for modloading
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::processIMC);

        // Register ourselves for server and other game events we are interested in
        MinecraftForge.EVENT_BUS.register(this);

    }

    public static boolean fittingItemType(ItemStack item) {
        return item.getItem() instanceof PickaxeItem
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
                || item.getItem() instanceof ShearsItem;
    }

    private void setup(final FMLCommonSetupEvent event)
    {
    }

    private void enqueueIMC(final InterModEnqueueEvent event)
    {
        // some example code to dispatch IMC to another mod
        InterModComms.sendTo("examplemod", "helloworld", () -> { LOGGER.info("Hello world from the MDK"); return "Hello world";});
    }

    private void processIMC(final InterModProcessEvent event)
    {
    }
    // You can use SubscribeEvent and let the Event Bus discover methods to call
    @SubscribeEvent
    public void onServerStarting(FMLServerStartingEvent event) {

    }
    @SubscribeEvent
    public void onServerStarted(FMLServerStartedEvent event)
    {
        // Grunddateien erstellen, wenn noch nicht vorhanden
        if(!(new File(ServerLifecycleHooks.getCurrentServer().getWorldData().getLevelName()+File.separator+"randomenchant.conf").exists()))
        {
            try (FileWriter writer = new FileWriter(ServerLifecycleHooks.getCurrentServer().getWorldData().getLevelName()+File.separator+"randomenchant.conf")) {

                BufferedWriter bw = new BufferedWriter(writer);
                bw.write("NEEDED_ENCHANTMENT_POINTS=100");
                bw.append("\nPOINTS_PER_BLOCK=1");
                bw.append("\nPOINTS_PER_MOB=5");
                bw.append("\nANIMALS=false");
                bw.append("\nLEAVES=true");
                bw.close();

            }catch(Exception ex)
            {
                System.out.println("Fehler beim Speichern der Einstellungen: "+ex.getMessage());
            }
        }

        if(!(new File(ServerLifecycleHooks.getCurrentServer().getWorldData().getLevelName()+File.separator+"randomenchant.enchantments").exists()))
        {
            try (FileWriter writer = new FileWriter(ServerLifecycleHooks.getCurrentServer().getWorldData().getLevelName()+File.separator+"randomenchant.enchantments")) {

                BufferedWriter bw = new BufferedWriter(writer);
                bw.write("PICKAXE:FORTUNE:10:99999:SILK_TOUCH");
                bw.append("\nPICKAXE:MENDING:10:1:");
                bw.append("\nPICKAXE:EFFICIENCY:10:99999:");
                bw.append("\nPICKAXE:UNBREAKING:10:99999:");
                bw.append("\n#PICKAXE:SILK_TOUCH:10:1:FORTUNE");

                bw.append("\n\nAXE:FORTUNE:10:99999:SILK_TOUCH");
                bw.append("\nAXE:MENDING:10:1:");
                bw.append("\nAXE:EFFICIENCY:10:99999:");
                bw.append("\nAXE:UNBREAKING:10:99999:");
                bw.append("\n#AXE:SILK_TOUCH:10:1:FORTUNE");
                bw.append("\nAXE:SMITE:10:99999:");
                bw.append("\nAXE:SHARPNESS:10:99999:");

                bw.append("\n\nSHOVEL:FORTUNE:10:99999:SILK_TOUCH");
                bw.append("\nSHOVEL:MENDING:10:1:");
                bw.append("\nSHOVEL:EFFICIENCY:10:99999:");
                bw.append("\nSHOVEL:UNBREAKING:10:99999:");
                bw.append("\n#SHOVEL:SILK_TOUCH:10:1:FORTUNE");

                bw.append("\n\nHOE:MENDING:10:1:");
                bw.append("\nHOE:UNBREAKING:10:99999:");
                bw.append("\n#HOE:SILK_TOUCH:10:1:");

                bw.append("\n\nBOW:MENDING:10:1:");
                bw.append("\nBOW:UNBREAKING:10:99999:");
                bw.append("\nBOW:FLAMING_ARROWS:10:99999:");
                bw.append("\nBOW:POWER_ARROWS:10:99999:");
                bw.append("\nBOW:PUNCH_ARROWS:10:99999:");
                bw.append("\nBOW:INFINITY_ARROWS:10:1:");

                bw.append("\n\nSWORD:BANE_OF_ARTHROPODS:10:99999:");
                bw.append("\nSWORD:FIRE_ASPECT:10:99999:");
                bw.append("\nSWORD:KNOCKBACK:10:99999:");
                bw.append("\nSWORD:MOB_LOOTING:10:99999:");
                bw.append("\nSWORD:MENDING:10:99999:");
                bw.append("\nSWORD:SHARPNESS:10:99999:");
                bw.append("\nSWORD:SMITE:10:99999:");
                bw.append("\nSWORD:SWEEPING_EDGE:10:99999:");
                bw.append("\nSWORD:UNBREAKING:10:99999:");

                bw.append("\n\nSHIELD:UNBREAKING:10:99999:");
                bw.append("\nSHIELD:MENDING:10:1:");

                bw.append("\n\nFISHING_ROD:MENDING:10:1:");
                bw.append("\nFISHING_ROD:LUCK:10:99999:");
                bw.append("\nFISHING_ROD:LURE:10:99999:");
                bw.append("\nFISHING_ROD:UNBREAKING:10:99999:");

                bw.append("\n\nTRIDENT:MENDING:10:1:");
                bw.append("\nTRIDENT:CHANNELING:10:99999:");
                bw.append("\nTRIDENT:IMPALING:10:99999:");
                bw.append("\nTRIDENT:LOYALTY:10:1:");
                bw.append("\nTRIDENT:RIPTIDE:10:99999:");
                bw.append("\nTRIDENT:UNBREAKING:10:99999:");

                bw.append("\n\nCROSSBOW:MENDING:10:1:");
                bw.append("\nCROSSBOW:MULTISHOT:10:1:");
                bw.append("\nCROSSBOW:PIERCING:10:99999:");
                bw.append("\nCROSSBOW:QUICK_CHARGE:10:99999:");
                bw.append("\nCROSSBOW:UNBREAKING:10:99999:");

                bw.append("\n\nARMOR:UNBREAKING:10:99999:");
                bw.append("\nARMOR:BLAST_PROTECTION:10:99999:");
                bw.append("\nARMOR:FIRE_PROTECTION:10:99999:");
                bw.append("\nARMOR:MENDING:10:1:");
                bw.append("\nARMOR:PROJECTILE_PROTECTION:10:99999:");
                bw.append("\nARMOR:ALL_DAMAGE_PROTECTION:10:99999:");
                bw.append("\nARMOR:THORNS:10:99999:");
                bw.append("\nARMOR:FIRE_PROTECTION:10:99999:");

                bw.append("\n\nHELMET:AQUA_AFFINITY:10:1:");
                bw.append("\nHELMET:RESPIRATION:10:99999:");

                bw.append("\n\nBOOTS:DEPTH_STRIDER:10:99999:");
                bw.append("\nBOOTS:FALL_PROTECTION:10:99999:");
                bw.append("\nBOOTS:SOUL_SPEED:10:99999:");
                bw.append("\n#BOOTS:FROSTWALKER:10:99999:");

                bw.append("\n\nELYTRA:MENDING:10:1:");
                bw.append("\nELYTRA:UNBREAKING:10:99999:");

                bw.append("\n\nSHEARS:MENDING:10:1:");
                bw.append("\nSHEARS:UNBREAKING:10:99999:");

                bw.close();

            }catch(Exception ex)
            {
                System.out.println("Fehler beim Speichern der Einstellungen: "+ex.getMessage());
            }
        }


        // Dateien auslesen
        try (FileReader writer = new FileReader(ServerLifecycleHooks.getCurrentServer().getWorldData().getLevelName()+File.separator+"randomenchant.conf")) {
            BufferedReader bw = new BufferedReader(writer);

            while(bw.ready()) {
                String line = bw.readLine();

                if(line.contains("="))
                {
                    String name = line.substring(0,line.indexOf("=")).trim();
                    String val = line.substring(line.indexOf("=")+1);

                    switch(name)
                    {
                        case "POINTS_PER_BLOCK":
                            Events.perBlock = Integer.parseInt(val);
                            break;
                        case "POINTS_PER_MOB":
                            Events.perMob = Integer.parseInt(val);
                            break;
                        case "NEEDED_ENCHANTMENT_POINTS":
                            Events.maxChanced = Integer.parseInt(val);
                            break;
                        case "ANIMALS":
                            Events.animalCounts = Boolean.parseBoolean(val);
                            break;
                        case "LEAVES":
                            Events.leavesCounts = Boolean.parseBoolean(val);
                            break;
                    }
                }
            }

            bw.close();

        }catch(Exception ex)
        {
            System.out.println("Fehler beim Laden der Einstellungen: "+ex.getMessage());
        }

        try (FileReader writer = new FileReader(ServerLifecycleHooks.getCurrentServer().getWorldData().getLevelName()+File.separator+"randomenchant.enchantments")) {
            BufferedReader bw = new BufferedReader(writer);

            while(bw.ready()) {
                String line = bw.readLine();

                if(line.contains("#"))
                    line = line.substring(0,line.indexOf("#"));

                if(line.contains(":")) {
                    Enchantmentlist.addPossibleEnchantment(line);
                }
            }

            bw.close();

        }catch(Exception ex)
        {
            System.out.println("Fehler beim Laden der Verzauberungen: "+ex.getMessage());
        }
        ServerLifecycleHooks.getCurrentServer().getCommands().performCommand(ServerLifecycleHooks.getCurrentServer().createCommandSourceStack() ,"gamerule sendCommandFeedback false");

    }

    // You can use EventBusSubscriber to automatically subscribe events on the contained class (this is subscribing to the MOD
    // Event bus for receiving Registry Events)
    @Mod.EventBusSubscriber(bus=Mod.EventBusSubscriber.Bus.MOD)
    public static class RegistryEvents {
        @SubscribeEvent
        public static void onBlocksRegistry(final RegistryEvent.Register<Block> blockRegistryEvent) {
            // register a new block here
        }
    }
}
