package me.pljr.servercore.commands;

import me.pljr.pljrapi.utils.CommandUtil;
import me.pljr.servercore.ServerCore;
import me.pljr.servercore.config.CfgLang;
import me.pljr.servercore.enums.Lang;
import me.pljr.servercore.managers.WarpManager;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;

import java.util.List;

public class WarpsCommand extends CommandUtil implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!checkPerm(sender, "servercore.warps.use")) return false;

        WarpManager warpManager = ServerCore.getWarpManager();
        List<String> warpNames = warpManager.getWarpNames();

        // /warps
        sendMessage(sender, CfgLang.lang.get(Lang.WARPS_SUCCESS_TITLE));
        if (sender instanceof ConsoleCommandSender){
            for (String warpName : warpNames){
                sendMessage(sender, CfgLang.lang.get(Lang.WARPS_SUCCESS_FORMAT_REMOVE).replace("%name", warpName));
            }
        }else if (sender.hasPermission("servercore.delwarp.use")){
            for (String warpName : warpNames){
                if (sender.hasPermission("servercore.warp.use." + warpName)){
                    sendMessage(sender, CfgLang.lang.get(Lang.WARPS_SUCCESS_FORMAT_REMOVE).replace("%name", warpName));
                }
            }
        }else{
            for (String warpName : warpNames){
                if (sender.hasPermission("servercore.warp.use." + warpName)){
                    sendMessage(sender, CfgLang.lang.get(Lang.WARPS_SUCCESS_FORMAT).replace("%name", warpName));
                }
            }
        }

        return true;
    }
}
