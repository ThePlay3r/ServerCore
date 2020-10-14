package me.pljr.servercore.commands;

import me.pljr.pljrapi.utils.CommandUtil;
import me.pljr.servercore.ServerCore;
import me.pljr.servercore.config.CfgLang;
import me.pljr.servercore.enums.Lang;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class DelhomeCommand extends CommandUtil implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)){
            sendMessage(sender, CfgLang.lang.get(Lang.NO_CONSOLE));
            return false;
        }
        Player player = (Player) sender;
        if (!checkPerm(player, "servercore.delhome.use")) return false;

        if (args.length == 1){
            // /delhome <name>
            if (!ServerCore.getPlayerManager().delHome(player, args[0])){
                sendMessage(player, CfgLang.lang.get(Lang.DELHOME_FAILURE_NO_HOME).replace("%name", args[0]));
                return false;
            }
            sendMessage(player, CfgLang.lang.get(Lang.DELHOME_SUCCESS).replace("%name", args[0]));
            return true;
        }

        sendMessage(player, CfgLang.lang.get(Lang.DELHOME_USAGE));
        return false;
    }
}
