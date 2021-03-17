package me.pljr.servercore.managers;

import lombok.AllArgsConstructor;
import lombok.Builder;
import me.pljr.servercore.ServerCore;
import me.pljr.servercore.objects.SCorePlayer;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.OfflinePlayer;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.function.Consumer;

@AllArgsConstructor
public class PlayerManager {
    private final static int AUTOSAVE = 12000;

    private final HashMap<UUID, SCorePlayer> players = new HashMap<>();
    private final JavaPlugin plugin;
    private final QueryManager queryManager;
    private final boolean cachePlayers;

    public void getPlayer(UUID uuid, Consumer<SCorePlayer> consumer){
        if (!players.containsKey(uuid)){
            Bukkit.getScheduler().runTaskAsynchronously(plugin, () -> {
                SCorePlayer player = queryManager.loadPlayer(uuid);
                setPlayer(uuid, player);
                consumer.accept(player);
            });
        }else{
            consumer.accept(players.get(uuid));
        }
    }

    public void setPlayer(UUID uuid, SCorePlayer player){
        players.put(uuid, player);
    }

    public void savePlayer(UUID uuid){
        if (!cachePlayers) players.remove(uuid);
        queryManager.savePlayer(players.get(uuid));
    }

    public void initAutoSave(){
        Bukkit.getScheduler().runTaskTimerAsynchronously(plugin, () -> {
            ServerCore.log.info("Saving players..");
            for (Map.Entry<UUID, SCorePlayer> entry : players.entrySet()){
                savePlayer(entry.getKey());
            }
            ServerCore.log.info("All players were saved.");
        }, AUTOSAVE, AUTOSAVE);
    }

    public void setHome(OfflinePlayer player, String name, Location location){
        UUID uuid = player.getUniqueId();
        SCorePlayer scorePlayer = players.get(uuid);
        scorePlayer.getHomes().put(name, location);
        players.put(uuid, scorePlayer);
    }

    public boolean delHome(OfflinePlayer player, String name){
        UUID uuid = player.getUniqueId();
        SCorePlayer scorePlayer = players.get(uuid);
        HashMap<String, Location> playerHomes = scorePlayer.getHomes();
        if (playerHomes.containsKey(name)){
            playerHomes.remove(name);
            players.put(uuid, scorePlayer);
            return true;
        }
        return false;
    }
}
