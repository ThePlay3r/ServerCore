package me.pljr.servercore.listeners;

import lombok.AllArgsConstructor;
import me.pljr.servercore.ServerCore;
import me.pljr.servercore.managers.SpawnManager;
import org.bukkit.Location;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerRespawnEvent;

@AllArgsConstructor
public class PlayerRespawnListener implements Listener {

    private final SpawnManager spawnManager;

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onRespawn(PlayerRespawnEvent event){
        Location spawnLoc = spawnManager.getLocation();
        if (spawnLoc == null) return;
        event.setRespawnLocation(spawnLoc);
    }
}
