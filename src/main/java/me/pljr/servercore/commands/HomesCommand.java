package me.pljr.servercore.commands;

import me.pljr.pljrapi.utils.CommandUtil;
import me.pljr.servercore.ServerCore;
import me.pljr.servercore.config.CfgLang;
import me.pljr.servercore.enums.Lang;
import me.pljr.servercore.objects.CorePlayer;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class HomesCommand extends CommandUtil implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)){
            sender.sendMessage(CfgLang.lang.get(Lang.NO_CONSOLE));
            return false;
        }
        Player player = (Player) sender;
        if (!checkPerm(player, "servercore.homes.use")) return false;

        UUID playerId = player.getUniqueId();

        CorePlayer corePlayer = ServerCore.getPlayerManager().getCorePlayer(playerId);
        HashMap<String, Location> playerHomes = corePlayer.getHomes();

        // /homes
        sendMessage(player, CfgLang.lang.get(Lang.HOMES_SUCCESS_TITLE));
        for (Map.Entry<String, Location> home : playerHomes.entrySet()){
            String homeName = home.getKey();
            sendMessage(player, CfgLang.lang.get(Lang.HOMES_SUCCESS_FORMAT).replace("%name", homeName));
        }

        return true;
    }
}
