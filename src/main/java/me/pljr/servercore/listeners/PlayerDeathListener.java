package me.pljr.servercore.listeners;

import lombok.AllArgsConstructor;
import me.pljr.servercore.ServerCore;
import me.pljr.servercore.managers.PlayerManager;
import me.pljr.servercore.objects.CorePlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

import java.util.UUID;

@AllArgsConstructor
public class PlayerDeathListener implements Listener {

    private final PlayerManager playerManager;

    @EventHandler
    public void onDeath(PlayerDeathEvent event){
        Player player = event.getEntity();
        UUID playerId = player.getUniqueId();
        playerManager.getCorePlayer(playerId, corePlayer -> {
            corePlayer.setDeathLoc(player.getLocation());
            playerManager.setCorePlayer(playerId, corePlayer);
        });
    }
}
