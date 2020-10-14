package me.pljr.servercore.listeners;

import me.pljr.servercore.ServerCore;
import me.pljr.servercore.managers.PlayerManager;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

import java.util.UUID;

public class PlayerQuitListener implements Listener {

    @EventHandler
    public void onQuit(PlayerQuitEvent event){
        Player player = event.getPlayer();
        UUID playerId = player.getUniqueId();

        PlayerManager playerManager = ServerCore.getPlayerManager();
        playerManager.savePlayer(playerId);
    }
}
