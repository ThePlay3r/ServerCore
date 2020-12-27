package me.pljr.servercore.config;

import me.pljr.pljrapispigot.managers.ConfigManager;

public class CfgSettings {
    public static boolean TP_DELAY;
    public static boolean TP_OTHER_DELAY;
    public static boolean TPHERE_DELAY;
    public static boolean ASPAWN_DELAY;
    public static boolean ALWAYS_SPAWN_ON_SPAWN;
    public static boolean WARP_GUI;
    public static String DEFAULT_WORLD;
    public static int DAY_TIME;
    public static int NIGHT_TIME;

    public static void load(ConfigManager config){
        TP_DELAY = config.getBoolean("settings.tp-delay");
        TP_OTHER_DELAY = config.getBoolean("settings.tp-others-delay");
        TPHERE_DELAY = config.getBoolean("settings.tphere-delay");
        ASPAWN_DELAY = config.getBoolean("settings.aspawn-delay");
        ALWAYS_SPAWN_ON_SPAWN = config.getBoolean("settings.always-spawn-on-spawn");
        WARP_GUI = config.getBoolean("settings.warp-gui");
        DEFAULT_WORLD = config.getString("settings.default-world");
        DAY_TIME = config.getInt("settings.day-time");
        NIGHT_TIME = config.getInt("settings.night-time");
    }
}
