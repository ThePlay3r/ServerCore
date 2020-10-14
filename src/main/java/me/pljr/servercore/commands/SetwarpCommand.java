package me.pljr.servercore.commands;

import me.pljr.pljrapi.utils.CommandUtil;
import me.pljr.servercore.ServerCore;
import me.pljr.servercore.config.CfgLang;
import me.pljr.servercore.enums.Lang;
import me.pljr.servercore.managers.WarpManager;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class SetwarpCommand extends CommandUtil implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)){
            sendMessage(sender, CfgLang.lang.get(Lang.NO_CONSOLE));
            return false;
        }
        Player player = (Player) sender;
        if (!checkPerm(player, "servercore.setwarp.use")) return false;

        Location playerLoc = player.getLocation();

        WarpManager warpManager = ServerCore.getWarpManager();

        if (args.length == 1){
            // /setwarp <name>
            warpManager.setWarp(args[0], playerLoc);
            sendMessage(player, CfgLang.lang.get(Lang.SETWARP_SUCCESS).replace("%warp", args[0]));
            return true;
        }

        sendMessage(player, CfgLang.lang.get(Lang.SETWARP_USAGE));
        return false;
    }
}
