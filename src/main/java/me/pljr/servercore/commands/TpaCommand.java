package me.pljr.servercore.commands;

import me.pljr.pljrapi.utils.CommandUtil;
import me.pljr.servercore.ServerCore;
import me.pljr.servercore.config.CfgLang;
import me.pljr.servercore.enums.Lang;
import me.pljr.servercore.objects.CorePlayer;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.UUID;

public class TpaCommand extends CommandUtil implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)){
            sendMessage(sender, CfgLang.lang.get(Lang.NO_CONSOLE));
            return false;
        }
        Player player = (Player) sender;
        if (!checkPerm(player, "servercore.tpa.use")) return false;

        String playerName = player.getName();

        if (args.length == 1){
            // /tpa <player>
            if (!checkPlayer(player, args[0])) return false;
            Player target = Bukkit.getPlayer(args[0]);
            UUID targetId = target.getUniqueId();
            CorePlayer coreTarget = ServerCore.getPlayerManager().getCorePlayer(targetId);
            if (coreTarget.getTpaBlocked().contains(playerName)){
                sendMessage(player, CfgLang.lang.get(Lang.TPA_FAILURE_BLOCKED).replace("%player", args[0]));
                return false;
            }
            coreTarget.setTpaRequester(player);
            sendMessage(player, CfgLang.lang.get(Lang.TPA_SUCCESS).replace("%player", args[0]));
            sendMessage(target, CfgLang.lang.get(Lang.TPA_SUCCESS_PLAYER).replace("%player", playerName));
            return true;
        }

        sendMessage(player, CfgLang.lang.get(Lang.TPA_USAGE));
        return false;
    }
}
