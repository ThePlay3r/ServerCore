package me.pljr.servercore.listeners;

import lombok.AllArgsConstructor;
import me.pljr.pljrapispigot.utils.PlayerUtil;
import me.pljr.servercore.ServerCore;
import me.pljr.servercore.config.Settings;
import me.pljr.servercore.managers.PlayerManager;
import me.pljr.servercore.managers.SpawnManager;
import me.pljr.servercore.objects.CorePlayer;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import java.util.UUID;

@AllArgsConstructor
public class PlayerJoinListener implements Listener {

    private final Settings settings;
    private final PlayerManager playerManager;
    private final SpawnManager spawnManager;

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onJoin(PlayerJoinEvent event){
        Player player = event.getPlayer();
        UUID playerId = player.getUniqueId();

        playerManager.getCorePlayer(playerId, corePlayer -> {
            Location spawn = spawnManager.getLocation();

            if (spawn != null){
                if (corePlayer.getDeathLoc() == null){
                    corePlayer.setDeathLoc(player.getLocation());
                }
                if (!player.hasPlayedBefore() || settings.isAlwaysSpawnOnSpawn()){
                    player.teleport(spawn);
                }
            }
        });
    }
}
