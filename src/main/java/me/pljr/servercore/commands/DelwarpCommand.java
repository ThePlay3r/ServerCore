package me.pljr.servercore.commands;

import me.pljr.pljrapi.utils.CommandUtil;
import me.pljr.servercore.ServerCore;
import me.pljr.servercore.config.CfgLang;
import me.pljr.servercore.enums.Lang;
import me.pljr.servercore.managers.WarpManager;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import java.util.List;

public class DelwarpCommand extends CommandUtil implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!checkPerm(sender, "servercore.delwarp.use")) return false;

        WarpManager warpManager = ServerCore.getWarpManager();
        List<String> warpNames = warpManager.getWarpNames();

        if (args.length == 1){
            // /delwarp <name>
            if (!warpNames.contains(args[0])){
                sendMessage(sender, CfgLang.lang.get(Lang.DELWARP_FAILURE_NO_WARP).replace("%warp", args[0]));
                return false;
            }
            warpManager.delWarp(args[0]);
            sendMessage(sender, CfgLang.lang.get(Lang.DELWARP_SUCCESS).replace("%warp", args[0]));
            return true;
        }

        sendMessage(sender, CfgLang.lang.get(Lang.DELWARP_USAGE));
        return false;
    }
}
