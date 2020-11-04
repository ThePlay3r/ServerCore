package me.pljr.servercore.commands;

import me.pljr.pljrapi.utils.CommandUtil;
import me.pljr.servercore.config.CfgLang;
import me.pljr.servercore.enums.Lang;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class FlyCommand extends CommandUtil implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!checkPerm(sender, "servercore.fly.use")) return false;

        if (args.length == 0){
            // /fly
            if (!(sender instanceof Player)){
                sendMessage(sender, CfgLang.lang.get(Lang.NO_CONSOLE));
                return false;
            }
            Player player = (Player) sender;
            if (player.isFlying()){
                player.setFlying(false);
                sendMessage(player, CfgLang.lang.get(Lang.FLY_SUCCESS_OFF));
            }else{
                player.setFlying(true);
                sendMessage(player, CfgLang.lang.get(Lang.FLY_SUCCESS_ON));
            }
            return true;
        }

        if (args.length == 1){
            // /fly <player>
            if (!checkPerm(sender, "servercore.fly.use.others")) return false;
            if (!checkPlayer(sender, args[0])) return false;
            Player target = Bukkit.getPlayer(args[0]);
            if (target.isFlying()){
                target.setAllowFlight(false);
                sendMessage(sender, CfgLang.lang.get(Lang.FLY_SUCCESS_OTHERS_OFF).replace("%player", target.getName()));
                sendMessage(target, CfgLang.lang.get(Lang.FLY_SUCCESS_OTHERS_OFF_PLAYER).replace("%player", sender.getName()));
            }else{
                target.setAllowFlight(true);
                sendMessage(sender, CfgLang.lang.get(Lang.FLY_SUCCESS_OTHERS_ON).replace("%player", target.getName()));
                sendMessage(target, CfgLang.lang.get(Lang.FLY_SUCCESS_OTHERS_ON_PLAYER).replace("%player", sender.getName()));
            }
            return true;
        }

        sendMessage(sender, CfgLang.lang.get(Lang.FLY_USAGE));
        return false;
    }
}
