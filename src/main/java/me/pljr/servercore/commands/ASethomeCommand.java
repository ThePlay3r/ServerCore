package me.pljr.servercore.commands;

import me.pljr.pljrapi.utils.CommandUtil;
import me.pljr.servercore.ServerCore;
import me.pljr.servercore.config.CfgLang;
import me.pljr.servercore.enums.Lang;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class ASethomeCommand extends CommandUtil implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)){
            sendMessage(sender, CfgLang.lang.get(Lang.NO_CONSOLE));
            return false;
        }
        Player player = (Player) sender;
        if (!checkPerm(player, "servercore.asethome.use")) return false;

        if (args.length == 2){
            // /asethome <player> <home>
            OfflinePlayer target = Bukkit.getOfflinePlayer(args[0]);
            ServerCore.getPlayerManager().setHome(target, args[1], player.getLocation());
            sendMessage(player, CfgLang.lang.get(Lang.ASETHOME_SUCCESS).replace("%name", args[1]).replace("%player", args[0]));
            sendMessage(target, CfgLang.lang.get(Lang.ASETHOME_SUCCESS_PLAYER).replace("%name", args[1]).replace("%player", player.getName()));
            return true;
        }

        sendMessage(player, CfgLang.lang.get(Lang.ASETHOME_USAGE));
        return false;
    }
}
