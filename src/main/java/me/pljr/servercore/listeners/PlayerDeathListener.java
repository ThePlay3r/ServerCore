package me.pljr.servercore.listeners;

import lombok.AllArgsConstructor;
import me.pljr.servercore.managers.PlayerManager;
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
        playerManager.getPlayer(playerId, corePlayer -> {
            corePlayer.setDeathLoc(player.getLocation());
            playerManager.setPlayer(playerId, corePlayer);
        });
    }
}
