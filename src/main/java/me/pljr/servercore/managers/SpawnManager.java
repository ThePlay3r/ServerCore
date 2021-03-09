package me.pljr.servercore.managers;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import me.pljr.pljrapispigot.managers.ConfigManager;
import org.bukkit.Location;
import org.bukkit.configuration.file.FileConfiguration;

@AllArgsConstructor
public class SpawnManager {

    private final ConfigManager database;
    @Setter @Getter
    private Location location;

    public void saveToFile(){
        if (location == null) return;
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
