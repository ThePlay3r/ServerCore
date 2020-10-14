package me.pljr.servercore.commands;

import me.pljr.pljrapi.utils.CommandUtil;
import me.pljr.pljrapi.utils.PlayerUtil;
import me.pljr.servercore.config.CfgLang;
import me.pljr.servercore.config.CfgSettings;
import me.pljr.servercore.enums.Lang;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class TPCommand extends CommandUtil implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!checkPerm(sender, "servercore.tp.use")) return false;

        String senderName = sender.getName();

        if (args.length == 1){
            // /tp <player>
            if (!(sender instanceof Player)){
                sender.sendMessage(CfgLang.lang.get(Lang.NO_CONSOLE));
                return false;
            }
            Player player = (Player) sender;
            if (!checkPlayer(player, args[0])) return false;
            Player target = Bukkit.getPlayer(args[0]);
            PlayerUtil.teleport(player, target, CfgSettings.tpDelay);
            sendMessage(player, CfgLang.lang.get(Lang.TP_SUCCESS).replace("%player", args[0]));
            sendMessage(target, CfgLang.lang.get(Lang.TP_SUCCESS_PLAYER).replace("%player", senderName));
            return true;
        }

        else if (args.length == 2){
            // /tp <player> <player>
            if (!checkPlayer(sender, args[0])) return false;
            if (!checkPlayer(sender, args[1])) return false;
            Player player1 = Bukkit.getPlayer(args[0]);
            Player player2 = Bukkit.getPlayer(args[1]);
            PlayerUtil.teleport(player1, player2, CfgSettings.tpOthersDelay);
            sendMessage(sender, CfgLang.lang.get(Lang.TP_SUCCESS_OTHERS).replace("%player1", args[0]).replace("%player2", args[1]));
            sendMessage(player1, CfgLang.lang.get(Lang.TP_SUCCESS_OTHERS_PLAYER1).replace("%target", args[1]).replace("%player", senderName));
            sendMessage(player2, CfgLang.lang.get(Lang.TP_SUCCESS_OTHERS_PLAYER2).replace("%target", args[0]).replace("%player", senderName));
            return true;
        }

        sendMessage(sender, CfgLang.lang.get(Lang.TP_USAGE));
        return false;
    }
}
