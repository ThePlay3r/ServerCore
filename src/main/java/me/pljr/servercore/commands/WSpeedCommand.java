package me.pljr.servercore.commands;

import me.pljr.pljrapi.utils.CommandUtil;
import me.pljr.servercore.config.CfgLang;
import me.pljr.servercore.enums.Lang;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class WSpeedCommand extends CommandUtil implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!checkPerm(sender, "servercore.wspeed.use")) return false;

        if (args.length == 1){
            // /wspeed <speed>
            if (!(sender instanceof Player)){
                sendMessage(sender, CfgLang.lang.get(Lang.NO_CONSOLE));
                return false;
            }
            Player player = (Player) sender;
            if (!checkInt(player, args[0])) return false;
            float speed = Integer.parseInt(args[0]);
            if (speed > 10){
                speed = 10;
            }else if (speed < 0){
                speed = 0;
            }
            player.setWalkSpeed(speed/10);
            sendMessage(player, CfgLang.lang.get(Lang.WSPEED_SUCCESS).replace("%speed", args[0]));
            return true;
        }

        if (args.length == 2){
            // /wspeed <player> <speed>
            if (!checkPerm(sender, "servercore.wspeed.use.others")) return false;
            if (!checkPlayer(sender, args[0])) return false;
            if (!checkInt(sender, args[1])) return false;
            Player target = Bukkit.getPlayer(args[0]);
            float speed = Integer.parseInt(args[1]);
            if (speed > 10){
                speed = 10;
            }else if (speed < 0){
                speed = 0;
            }
            target.setWalkSpeed(speed/10);
            sendMessage(sender, CfgLang.lang.get(Lang.WSPEED_SUCCESS_OTHERS).replace("%player", target.getName()).replace("%speed", args[1]));
            sendMessage(target, CfgLang.lang.get(Lang.WSPEED_SUCCESS_OTHERS_PLAYER).replace("%player", sender.getName()).replace("%speed", args[1]));
            return true;
        }

        sendMessage(sender, CfgLang.lang.get(Lang.WSPEED_USAGE));
        return false;
    }
}
