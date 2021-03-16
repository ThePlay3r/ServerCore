package me.pljr.servercore.commands.homecommands;

import me.pljr.pljrapispigot.commands.BukkitCommand;
import me.pljr.servercore.config.Lang;
import me.pljr.servercore.managers.PlayerManager;
import org.bukkit.Location;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class HomesCommand extends BukkitCommand {

    private final PlayerManager playerManager;

    public HomesCommand(PlayerManager playerManager){
        super("homes", "servercore.homes.use");
        this.playerManager = playerManager;
    }

    @Override
    public void onPlayerCommand(Player player, String[] args){
        UUID playerId = player.getUniqueId();

        playerManager.getPlayer(playerId, corePlayer -> {
            HashMap<String, Location> playerHomes = corePlayer.getHomes();

            // /homes
            sendMessage(player, Lang.HOMES_SUCCESS_TITLE.get());
            for (Map.Entry<String, Location> home : playerHomes.entrySet()){
                String homeName = home.getKey();
                sendMessage(player, Lang.HOMES_SUCCESS_FORMAT.get().replace("{name}", homeName));
            }
        });

    }
}
