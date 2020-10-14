package me.pljr.servercore.commands;

import me.pljr.pljrapi.utils.CommandUtil;
import me.pljr.pljrapi.utils.PlayerUtil;
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
import org.bukkit.entity.Player;

import java.util.UUID;

public class AHomeCommand extends CommandUtil implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)){
            sendMessage(sender, CfgLang.lang.get(Lang.NO_CONSOLE));
            return false;
        }
        Player player = (Player) sender;
        if (!checkPerm(player, "servercore.ahome.use")) return false;

        if (args.length == 2){
            // /ahome <player> <home>
            OfflinePlayer target = Bukkit.getOfflinePlayer(args[0]);
            UUID targetId = target.getUniqueId();
            CorePlayer coreTarget = ServerCore.getPlayerManager().getCorePlayer(targetId);
            Location targetHome = coreTarget.getHome(args[1]);
            if (targetHome == null){
                sendMessage(player, CfgLang.lang.get(Lang.AHOME_FAILURE_NO_HOME).replace("%player", args[0]).replace("%home", args[1]));
                return false;
            }
            PlayerUtil.teleport(player, targetHome, true);
            sendMessage(player, CfgLang.lang.get(Lang.AHOME_SUCCESS).replace("%player", args[0]).replace("%home", args[1]));
            return true;
        }

        sendMessage(player, CfgLang.lang.get(Lang.AHOME_USAGE));
        return false;
    }
}
