package me.pljr.servercore.listeners;

import lombok.AllArgsConstructor;
import me.pljr.servercore.ServerCore;
import me.pljr.servercore.managers.QueryManager;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerPreLoginEvent;

@AllArgsConstructor
public class AsyncPlayerPreLoginListener implements Listener {

    private final QueryManager queryManager;

    @EventHandler
    public void onJoin(AsyncPlayerPreLoginEvent event){
        queryManager.loadPlayer(event.getUniqueId());
    }
}
