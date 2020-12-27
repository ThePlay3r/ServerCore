package me.pljr.servercore.commands;

import me.pljr.pljrapispigot.utils.CommandUtil;
import me.pljr.servercore.ServerCore;
import me.pljr.servercore.config.Lang;
import me.pljr.servercore.objects.CorePlayer;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class HomesCommand extends CommandUtil {

    public HomesCommand(){
        super("homes", "servercore.homes.use");
    }

    @Override
    public void onPlayerCommand(Player player, String[] args){
        UUID playerId = player.getUniqueId();

        CorePlayer corePlayer = ServerCore.getPlayerManager().getCorePlayer(playerId);
        HashMap<String, Location> playerHomes = corePlayer.getHomes();

        // /homes
        sendMessage(player, Lang.HOMES_SUCCESS_TITLE.get());
        for (Map.Entry<String, Location> home : playerHomes.entrySet()){
            String homeName = home.getKey();
            sendMessage(player, Lang.HOMES_SUCCESS_FORMAT.get().replace("{name}", homeName));
        }
    }
}
