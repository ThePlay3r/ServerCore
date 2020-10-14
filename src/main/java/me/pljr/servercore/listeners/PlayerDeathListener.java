package me.pljr.servercore.listeners;

import me.pljr.servercore.ServerCore;
import me.pljr.servercore.objects.CorePlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

import java.util.UUID;

public class PlayerDeathListener implements Listener {

    @EventHandler
    public void onDeath(PlayerDeathEvent event){
        Player player = event.getEntity();
        UUID playerId = player.getUniqueId();
        CorePlayer corePlayer = ServerCore.getPlayerManager().getCorePlayer(playerId);

        corePlayer.setDeathLoc(player.getLocation());
        ServerCore.getPlayerManager().setCorePlayer(playerId, corePlayer);
    }
}
