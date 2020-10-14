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

public class ADelhomeCommand extends CommandUtil implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!checkPerm(sender, "servercore.adelhome.use")) return false;

        if (args.length == 2){
            OfflinePlayer target = Bukkit.getOfflinePlayer(args[0]);
            if (!ServerCore.getPlayerManager().delHome(target, args[1])){
                sendMessage(sender, CfgLang.lang.get(Lang.ADELHOME_FAILURE_NO_HOME).replace("%player", args[0]).replace("%name", args[1]));
                return false;
            }
            sendMessage(sender, CfgLang.lang.get(Lang.ADELHOME_SUCCESS).replace("%player", args[0]).replace("%name", args[1]));
            sendMessage(target, CfgLang.lang.get(Lang.ADELHOME_SUCCESS_PLAYER).replace("%player", sender.getName()).replace("%name", args[1]));
            return true;
        }

        sendMessage(sender, CfgLang.lang.get(Lang.ADELHOME_USAGE));
        return false;
    }
}
