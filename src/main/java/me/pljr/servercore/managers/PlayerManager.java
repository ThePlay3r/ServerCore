package me.pljr.servercore.managers;

import me.pljr.servercore.ServerCore;
import me.pljr.servercore.objects.CorePlayer;
import org.bukkit.Location;
import org.bukkit.OfflinePlayer;

import java.util.HashMap;
import java.util.UUID;

public class PlayerManager {
    private final HashMap<UUID, CorePlayer> players;

    public PlayerManager(){
        players = new HashMap<>();
    }

    public CorePlayer getCorePlayer(UUID uuid){
        if (players.containsKey(uuid)){
            return players.get(uuid);
        }
        ServerCore.getQueryManager().loadPlayerSync(uuid);
        return getCorePlayer(uuid);
    }

    public void setCorePlayer(UUID uuid, CorePlayer corePlayer){
        players.put(uuid, corePlayer);
        savePlayer(uuid);
    }

    public void savePlayer(UUID uuid){
        if (!players.containsKey(uuid)) return;
        ServerCore.getQueryManager().savePlayer(uuid);
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
