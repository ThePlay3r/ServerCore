package me.pljr.servercore.listeners;

import lombok.AllArgsConstructor;
import me.pljr.servercore.ServerCore;
import me.pljr.servercore.managers.PlayerManager;
import me.pljr.servercore.managers.QueryManager;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerPreLoginEvent;

import java.util.UUID;
import java.util.logging.Logger;

@AllArgsConstructor
public class AsyncPlayerPreLoginListener implements Listener {
    private final PlayerManager playerManager;

    @EventHandler
    public void onJoin(AsyncPlayerPreLoginEvent event){
        playerManager.getCorePlayer(event.getUniqueId(), ignored -> ServerCore.log.info("Loaded " + event.getName()));
    }
}
