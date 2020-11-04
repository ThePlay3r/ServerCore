package me.pljr.servercore.listeners;

import me.pljr.servercore.ServerCore;
import me.pljr.servercore.managers.SpawnManager;
import org.bukkit.Location;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerRespawnEvent;

public class PlayerRespawnListener implements Listener {

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onRespawn(PlayerRespawnEvent event){
        SpawnManager spawnManager = ServerCore.getSpawnManager();
        Location spawnLoc = spawnManager.getLocation();
        if (spawnLoc == null) return;
        event.setRespawnLocation(spawnLoc);
    }
}
