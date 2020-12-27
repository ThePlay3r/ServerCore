package me.pljr.servercore.config;

import me.pljr.pljrapispigot.managers.ConfigManager;
import org.bukkit.inventory.ItemStack;

public class CfgWarpMenu {
    public static String TITLE;
    public static ItemStack BACKGROUND;
    public static ItemStack WARP_ITEM;

    public static void load(ConfigManager config){
        TITLE = config.getString("warp-menu.title");
        BACKGROUND = config.getSimpleItemStack("warp-menu.background");
        WARP_ITEM = config.getSimpleItemStack("warp-menu.warp-item");
    }
}
