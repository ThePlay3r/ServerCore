package me.pljr.servercore.managers;

import me.pljr.servercore.files.DatabaseFile;
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
        FileConfiguration databaseFile = DatabaseFile.getDatabaseFile();
        databaseFile.set("spawnLocation.world", location.getWorld().getName());
        databaseFile.set("spawnLocation.x", location.getX());
        databaseFile.set("spawnLocation.y", location.getY());
        databaseFile.set("spawnLocation.z", location.getZ());
        databaseFile.set("spawnLocation.pitch", location.getPitch());
        databaseFile.set("spawnLocation.yaw", location.getYaw());
        DatabaseFile.saveDatabaseFile();
    }
}
