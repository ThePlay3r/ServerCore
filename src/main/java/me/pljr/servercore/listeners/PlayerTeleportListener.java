package me.pljr.servercore.listeners;

import me.pljr.servercore.ServerCore;
import me.pljr.servercore.objects.CorePlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerTeleportEvent;

import java.util.UUID;

public class PlayerTeleportListener implements Listener {

    @EventHandler
    public void onTeleport(PlayerTeleportEvent event){
        Player player = event.getPlayer();
        UUID playerId = player.getUniqueId();
        CorePlayer corePlayer = ServerCore.getPlayerManager().getCorePlayer(playerId);

        corePlayer.setLastLoc(event.getFrom());
        ServerCore.getPlayerManager().setCorePlayer(playerId, corePlayer);
    }
}
