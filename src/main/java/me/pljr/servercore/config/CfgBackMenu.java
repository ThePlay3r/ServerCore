package me.pljr.servercore.config;

import me.pljr.pljrapispigot.managers.ConfigManager;
import org.bukkit.inventory.ItemStack;

public class CfgBackMenu {
    public static String TITLE;
    public static ItemStack BACKGROUND;
    public static ItemStack LAST_LOCATION;
    public static ItemStack DEATH_LOCATION;

    public static void load(ConfigManager config){
        TITLE = config.getString("back-menu.title");
        BACKGROUND = config.getSimpleItemStack("back-menu.background");
        LAST_LOCATION = config.getSimpleItemStack("back-menu.last-location");
        DEATH_LOCATION = config.getSimpleItemStack("back-menu.death-location");
    }
}
