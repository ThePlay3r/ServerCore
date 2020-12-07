package me.pljr.servercore.listeners;

import me.pljr.servercore.ServerCore;
import me.pljr.servercore.managers.PlayerManager;
import me.pljr.servercore.objects.CorePlayer;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;

import java.util.UUID;

public class PlayerCommandPreprocessListener implements Listener {

    @EventHandler
    public void onCommandPreprocess(PlayerCommandPreprocessEvent event){
        Bukkit.getScheduler().runTaskAsynchronously(ServerCore.getInstance(), ()->{
            Player player = event.getPlayer();
            String playerName = player.getName();
            String message = event.getMessage();

            PlayerManager playerManager = ServerCore.getPlayerManager();

            for (Player p : Bukkit.getOnlinePlayers()){
                UUID pID = p.getUniqueId();
                CorePlayer pCore = playerManager.getCorePlayer(pID);
                if (pCore.isSpy()){
                    p.sendMessage("§c" + playerName + " §8> §c" + message);
                }
            }
        });
    }
}
