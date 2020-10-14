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

public class TPHereCommand extends CommandUtil implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)){
            sendMessage(sender, CfgLang.lang.get(Lang.NO_CONSOLE));
            return false;
        }
        Player player = (Player) sender;
        if (!checkPerm(player, "servercore.tphere.use")) return false;

        String playerName = player.getName();

        if (args.length == 1){
            // /tphere <player>
            if (!checkPlayer(player, args[0])) return false;
            Player target = Bukkit.getPlayer(args[0]);
            PlayerUtil.teleport(target, player, CfgSettings.tpHereDelay);
            sendMessage(player, CfgLang.lang.get(Lang.TPHERE_SUCCESS).replace("%player", args[0]));
            sendMessage(target, CfgLang.lang.get(Lang.TPHERE_SUCCESS_PLAYER).replace("%player", playerName));
            return true;
        }

        sendMessage(player, CfgLang.lang.get(Lang.TPHERE_USAGE));
        return false;
    }
}
