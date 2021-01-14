package me.pljr.servercore.config;

import me.pljr.pljrapispigot.managers.ConfigManager;

public class CfgSettings {
    public static boolean ALWAYS_SPAWN_ON_SPAWN;
    public static boolean WARP_GUI;
    public static String DEFAULT_WORLD;
    public static int DAY_TIME;
    public static int NIGHT_TIME;

    public static void load(ConfigManager config){
        ALWAYS_SPAWN_ON_SPAWN = config.getBoolean("settings.always-spawn-on-spawn");
        WARP_GUI = config.getBoolean("settings.warp-gui");
        DEFAULT_WORLD = config.getString("settings.default-world");
        DAY_TIME = config.getInt("settings.day-time");
        NIGHT_TIME = config.getInt("settings.night-time");
    }
}
