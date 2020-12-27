package me.pljr.servercore.listeners;

import me.pljr.pljrapispigot.utils.PlayerUtil;
import me.pljr.servercore.ServerCore;
import me.pljr.servercore.config.CfgSettings;
import me.pljr.servercore.managers.SpawnManager;
import me.pljr.servercore.objects.CorePlayer;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import java.util.UUID;

public class PlayerJoinListener implements Listener {

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onJoin(PlayerJoinEvent event){
        Player player = event.getPlayer();
        UUID playerId = player.getUniqueId();

        CorePlayer corePlayer = ServerCore.getPlayerManager().getCorePlayer(playerId);

        SpawnManager spawnManager = ServerCore.getSpawnManager();
        Location spawn = spawnManager.getLocation();

        if (spawn != null){
            if (corePlayer.getDeathLoc() == null){
                corePlayer.setDeathLoc(player.getLocation());
            }
            if (!player.hasPlayedBefore() || CfgSettings.ALWAYS_SPAWN_ON_SPAWN){
                PlayerUtil.teleport(player, spawn, false);
            }
        }
    }
}
