package me.pljr.servercore.config;

import lombok.Getter;
import me.pljr.pljrapispigot.managers.ConfigManager;

@Getter
public class Settings {
    private final boolean cachePlayers;
    private final boolean alwaysSpawnOnSpawn;
    private final boolean warpGui;
    private final String defaultWorld;
    private final int dayTime;
    private final int nightTime;

    public Settings(ConfigManager config){
        this.cachePlayers = config.getBoolean("settings.cache-players");
        this.alwaysSpawnOnSpawn = config.getBoolean("settings.always-spawn-on-spawn");
        this.warpGui = config.getBoolean("settings.warp-gui");
        this.defaultWorld = config.getString("settings.default-world");
        this.dayTime = config.getInt("settings.day-time");
        this.nightTime = config.getInt("settings.night-time");
    }
}
