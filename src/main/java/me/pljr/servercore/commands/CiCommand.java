package me.pljr.servercore.commands;

import me.pljr.pljrapi.utils.CommandUtil;
import me.pljr.servercore.config.CfgLang;
import me.pljr.servercore.enums.Lang;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CiCommand extends CommandUtil implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!checkPerm(sender, "servercore.ci.use")) return false;

        // /ci
        if (args.length == 0){
            if (!(sender instanceof Player)){
                sendMessage(sender, CfgLang.lang.get(Lang.NO_CONSOLE));
                return false;
            }
            Player player = (Player) sender;
            player.getInventory().clear();
            player.updateInventory();
            sendMessage(player, CfgLang.lang.get(Lang.CI_SUCCESS));
            return true;
        }

        else if (args.length == 1){
            // /ci <player>
            if (!checkPerm(sender, "servercore.ci.use.others")) return false;
            if (!checkPlayer(sender, args[0])) return false;
            Player target = Bukkit.getPlayer(args[0]);
            target.getInventory().clear();
            target.updateInventory();
            sendMessage(sender, CfgLang.lang.get(Lang.CI_SUCCESS_OTHERS).replace("%player", args[0]));
            sendMessage(target, CfgLang.lang.get(Lang.CI_SUCCESS_OTHERS_PLAYER).replace("%player", sender.getName()));
            return true;
        }

        sendMessage(sender, CfgLang.lang.get(Lang.CI_USAGE));
        return false;
    }
}
