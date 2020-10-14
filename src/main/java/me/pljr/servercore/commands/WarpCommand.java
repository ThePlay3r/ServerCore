package me.pljr.servercore.commands;

import me.pljr.pljrapi.utils.CommandUtil;
import me.pljr.pljrapi.utils.PlayerUtil;
import me.pljr.servercore.ServerCore;
import me.pljr.servercore.config.CfgLang;
import me.pljr.servercore.enums.Lang;
import me.pljr.servercore.managers.WarpManager;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.List;

public class WarpCommand extends CommandUtil implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)){
            sendMessage(sender, CfgLang.lang.get(Lang.NO_CONSOLE));
            return false;
        }
        Player player = (Player) sender;
        if (!checkPerm(player, "servercore.warp.use")) return false;

        WarpManager warpManager = ServerCore.getWarpManager();
        List<String> warpNames = warpManager.getWarpNames();

        if (args.length == 1){
            if (!warpNames.contains(args[0])){
                sendMessage(player, CfgLang.lang.get(Lang.WARP_FAILURE_NO_WARP).replace("%warp", args[0]));
                return false;
            }
            if (!checkPerm(player, "servercore.warp.use." + args[0])) return false;
            PlayerUtil.teleport(player, warpManager.getWarps().get(args[0]), true);
            sendMessage(player, CfgLang.lang.get(Lang.WARP_SUCCESS).replace("%warp", args[0]));
            return true;
        }

        sendMessage(player, CfgLang.lang.get(Lang.WARP_USAGE));
        return false;
    }
}
