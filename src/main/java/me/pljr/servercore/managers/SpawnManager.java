package me.pljr.servercore.managers;

import me.pljr.pljrapispigot.managers.ConfigManager;
import me.pljr.servercore.ServerCore;
import org.bukkit.Location;
import org.bukkit.configuration.file.FileConfiguration;

public class SpawnManager {
    private Location location;

    public SpawnManager(){
        this.location = null;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public void saveToFile(){
        if (location == null) return;
        ConfigManager database = ServerCore.getDatabaseFileManager();
        FileConfiguration configuration = database.getConfig();
        configuration.set("spawnLocation.world", location.getWorld().getName());
        configuration.set("spawnLocation.x", location.getX());
        configuration.set("spawnLocation.y", location.getY());
        configuration.set("spawnLocation.z", location.getZ());
        configuration.set("spawnLocation.pitch", location.getPitch());
        configuration.set("spawnLocation.yaw", location.getYaw());
        database.save();
    }
}
