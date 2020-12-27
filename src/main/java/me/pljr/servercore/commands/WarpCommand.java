package me.pljr.servercore.commands;

import me.pljr.pljrapispigot.utils.CommandUtil;
import me.pljr.pljrapispigot.utils.PlayerUtil;
import me.pljr.servercore.ServerCore;
import me.pljr.servercore.config.Lang;
import me.pljr.servercore.managers.WarpManager;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.List;

public class WarpCommand extends CommandUtil {

    public WarpCommand(){
        super("warp", "servercore.warp.use");
    }

    @Override
    public void onPlayerCommand(Player player, String[] args){
        WarpManager warpManager = ServerCore.getWarpManager();
        List<String> warpNames = warpManager.getWarpNames();

        if (args.length == 1){
            if (!warpNames.contains(args[0])){
                sendMessage(player, Lang.WARP_FAILURE_NO_WARP.get().replace("{warp}", args[0]));
                return;
            }
            if (!checkPerm(player, "servercore.warp.use." + args[0])) return;
            PlayerUtil.teleport(player, warpManager.getWarps().get(args[0]), true);
            sendMessage(player, Lang.WARP_SUCCESS.get().replace("{warp}", args[0]));
            return;
        }

        sendMessage(player, Lang.WARP_USAGE.get());
    }
}
