package me.pljr.servercore.commands;

import me.pljr.pljrapi.utils.CommandUtil;
import me.pljr.servercore.ServerCore;
import me.pljr.servercore.config.CfgLang;
import me.pljr.servercore.enums.Lang;
import me.pljr.servercore.objects.CorePlayer;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class AHomesCommand extends CommandUtil implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!checkPerm(sender, "servercore.ahomes.use")) return false;

        if (args.length == 1){
            // /homes <player>
            OfflinePlayer target = Bukkit.getOfflinePlayer(args[0]);
            UUID targetId = target.getUniqueId();
            CorePlayer coreTarget = ServerCore.getPlayerManager().getCorePlayer(targetId);
            HashMap<String, Location> targetHomes = coreTarget.getHomes();
            sendMessage(sender, CfgLang.lang.get(Lang.AHOMES_SUCCESS_TITLE).replace("%player", args[0]));
            for (Map.Entry<String, Location> home : targetHomes.entrySet()){
                String homeName = home.getKey();
                sendMessage(sender, CfgLang.lang.get(Lang.AHOMES_SUCCESS_FORMAT).replace("%name", homeName).replace("%player", args[0]));
            }
            return true;
        }

        sendMessage(sender, CfgLang.lang.get(Lang.AHOMES_USAGE));
        return false;
    }
}
