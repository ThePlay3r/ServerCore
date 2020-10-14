package me.pljr.servercore.listeners;

import me.pljr.servercore.ServerCore;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerPreLoginEvent;

public class AsyncPlayerPreLoginListener implements Listener {

    @EventHandler
    public void onJoin(AsyncPlayerPreLoginEvent event){
        ServerCore.getQueryManager().loadPlayer(event.getUniqueId());
    }
}
