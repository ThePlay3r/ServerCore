package me.pljr.servercore.listeners;

import lombok.AllArgsConstructor;
import me.pljr.servercore.managers.PlayerManager;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerTeleportEvent;

import java.util.UUID;

@AllArgsConstructor
public class PlayerTeleportListener implements Listener {

    private final PlayerManager playerManager;

    @EventHandler
    public void onTeleport(PlayerTeleportEvent event){
        Player player = event.getPlayer();
        UUID playerId = player.getUniqueId();
        playerManager.getCorePlayer(playerId, corePlayer -> {
            corePlayer.setLastLoc(event.getFrom());
            playerManager.setCorePlayer(playerId, corePlayer);
        });
    }
}
