package me.pljr.servercore.config;

import me.pljr.pljrapi.managers.ConfigManager;

public class CfgSettings {
    public static boolean tpDelay;
    public static boolean tpOthersDelay;
    public static boolean tpHereDelay;
    public static boolean aspawnDelay;
    public static boolean alwaysSpawnOnSpawn;
    public static String defaultWorld;
    public static int dayTime;
    public static int nightTime;

    public static void load(ConfigManager config){
        tpDelay = config.getBoolean("settings.tp-delay");
        tpOthersDelay = config.getBoolean("settings.tp-others-delay");
        tpHereDelay = config.getBoolean("settings.tphere-delay");
        aspawnDelay = config.getBoolean("settings.aspawn-delay");
        alwaysSpawnOnSpawn = config.getBoolean("settings.always-spawn-on-spawn");
        defaultWorld = config.getString("settings.default-world");
        dayTime = config.getInt("settings.day-time");
        nightTime = config.getInt("settings.night-time");
    }
}
