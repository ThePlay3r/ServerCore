package me.pljr.servercore.listeners;

import lombok.AllArgsConstructor;
import me.pljr.servercore.managers.PlayerManager;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.UUID;

@AllArgsConstructor
public class PlayerCommandPreprocessListener implements Listener {

    private final JavaPlugin plugin;
    private final PlayerManager playerManager;

    @EventHandler
    public void onCommandPreprocess(PlayerCommandPreprocessEvent event){
        Bukkit.getScheduler().runTaskAsynchronously(plugin, ()->{
            Player player = event.getPlayer();
            String playerName = player.getName();
            String message = event.getMessage();

            for (Player p : Bukkit.getOnlinePlayers()){
                UUID pID = p.getUniqueId();
                playerManager.getCorePlayer(pID, pCore -> {
                    if (pCore.isSpy()){
                        p.sendMessage("§c" + playerName + " §8> §c" + message);
                    }
                });
            }
        });
    }
}
