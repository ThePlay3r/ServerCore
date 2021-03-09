package me.pljr.servercore.managers;

import lombok.AllArgsConstructor;
import me.pljr.servercore.ServerCore;
import me.pljr.servercore.objects.CorePlayer;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.OfflinePlayer;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashMap;
import java.util.UUID;
import java.util.function.Consumer;

@AllArgsConstructor
public class PlayerManager {

    private final HashMap<UUID, CorePlayer> players = new HashMap<>();
    private final JavaPlugin plugin;
    private final QueryManager queryManager;

    public void getCorePlayer(UUID uuid, Consumer<CorePlayer> consumer){
        if (!players.containsKey(uuid)){
            Bukkit.getScheduler().runTaskAsynchronously(plugin, () -> {
                CorePlayer corePlayer = queryManager.loadPlayer(uuid);
                setCorePlayer(uuid, corePlayer);
                consumer.accept(corePlayer);
            });
        }else{
            consumer.accept(players.get(uuid));
        }
    }

    public void setCorePlayer(UUID uuid, CorePlayer corePlayer){
        players.put(uuid, corePlayer);
        savePlayer(uuid);
    }

    public void savePlayer(UUID uuid){
        if (!players.containsKey(uuid)) return;
        queryManager.savePlayer(players.get(uuid));
    }

    public void setHome(OfflinePlayer player, String name, Location location){
        UUID uuid = player.getUniqueId();
        CorePlayer corePlayer = players.get(uuid);
        corePlayer.getHomes().put(name, location);
        players.put(uuid, corePlayer);
    }

    public boolean delHome(OfflinePlayer player, String name){
        UUID uuid = player.getUniqueId();
        CorePlayer corePlayer = players.get(uuid);
        HashMap<String, Location> playerHomes = corePlayer.getHomes();
        if (playerHomes.containsKey(name)){
            playerHomes.remove(name);
            players.put(uuid, corePlayer);
            return true;
        }
        return false;
    }
}
