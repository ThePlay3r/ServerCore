package me.pljr.servercore.config;

import me.pljr.pljrapi.managers.ConfigManager;
import org.bukkit.inventory.ItemStack;

public class CfgWarpMenu {
    public static String title;
    public static ItemStack background;
    public static ItemStack warpItem;

    public static void load(ConfigManager config){
        title = config.getString("warp-menu.title");
        background = config.getSimpleItemStack("warp-menu.background");
        warpItem = config.getSimpleItemStack("warp-menu.warp-item");
    }
}
