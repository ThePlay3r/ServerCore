package me.pljr.servercore.config;

import me.pljr.pljrapi.managers.ConfigManager;
import org.bukkit.inventory.ItemStack;

public class CfgBackMenu {
    public static String title;
    public static ItemStack background;
    public static ItemStack lastLocation;
    public static ItemStack deathLocation;

    public static void load(ConfigManager config){
        title = config.getString("back-menu.title");
        background = config.getSimpleItemStack("back-menu.background");
        lastLocation = config.getSimpleItemStack("back-menu.last-location");
        deathLocation = config.getSimpleItemStack("back-menu.death-location");
    }
}
