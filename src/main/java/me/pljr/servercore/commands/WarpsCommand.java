package me.pljr.servercore.commands;

import me.pljr.pljrapispigot.utils.CommandUtil;
import me.pljr.servercore.ServerCore;
import me.pljr.servercore.config.CfgSettings;
import me.pljr.servercore.config.Lang;
import me.pljr.servercore.managers.WarpManager;
import me.pljr.servercore.menus.WarpMenu;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;

import java.util.List;

public class WarpsCommand extends CommandUtil {

    public WarpsCommand(){
        super("warps", "servercore.warps.use");
    }

    @Override
    public void onPlayerCommand(Player player, String[] args){
        WarpManager warpManager = ServerCore.getWarpManager();
        List<String> warpNames = warpManager.getWarpNames();

        if (CfgSettings.WARP_GUI){
            // /warps (Gui)
            WarpMenu.get(player).open(player);
        }else{
            // /warps (Chat)
            sendMessage(player, Lang.WARPS_SUCCESS_TITLE.get());
            if (player.hasPermission("servercore.delwarp.use")){
                for (String warpName : warpNames){
                    if (player.hasPermission("servercore.warp.use." + warpName)){
                        sendMessage(player, Lang.WARPS_SUCCESS_FORMAT_REMOVE.get().replace("{name}", warpName));
                    }
                }
            }else{
                for (String warpName : warpNames){
                    if (player.hasPermission("servercore.warp.use." + warpName)){
                        sendMessage(player, Lang.WARPS_SUCCESS_FORMAT.get().replace("{name}", warpName));
                    }
                }
            }
        }
    }

    @Override
    public void onConsoleCommand(ConsoleCommandSender sender, String[] args){
        WarpManager warpManager = ServerCore.getWarpManager();
        List<String> warpNames = warpManager.getWarpNames();

        // /warps (Chat)
        sendMessage(sender, Lang.WARPS_SUCCESS_TITLE.get());
        for (String warpName : warpNames){
            sendMessage(sender, Lang.WARPS_SUCCESS_FORMAT_REMOVE.get().replace("{name}", warpName));
        }
    }
}
