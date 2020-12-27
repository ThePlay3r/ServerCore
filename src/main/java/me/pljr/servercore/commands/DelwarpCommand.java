package me.pljr.servercore.commands;

import me.pljr.pljrapispigot.utils.CommandUtil;
import me.pljr.servercore.ServerCore;
import me.pljr.servercore.config.Lang;
import me.pljr.servercore.managers.WarpManager;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;

import java.util.List;

public class DelwarpCommand extends CommandUtil {

    public DelwarpCommand(){
        super("delwarp", "servercore.delwarp.use");
    }

    @Override
    public void onPlayerCommand(Player player, String[] args){
        WarpManager warpManager = ServerCore.getWarpManager();
        List<String> warpNames = warpManager.getWarpNames();

        if (args.length == 1){
            // /delwarp <name>
            if (!warpNames.contains(args[0])){
                sendMessage(player, Lang.DELWARP_FAILURE_NO_WARP.get().replace("{warp}", args[0]));
                return;
            }
            warpManager.delWarp(args[0]);
            sendMessage(player, Lang.DELWARP_SUCCESS.get().replace("{warp}", args[0]));
            return;
        }

        sendMessage(player, Lang.DELWARP_USAGE.get());
    }

    @Override
    public void onConsoleCommand(ConsoleCommandSender sender, String[] args){
        WarpManager warpManager = ServerCore.getWarpManager();
        List<String> warpNames = warpManager.getWarpNames();

        if (args.length == 1){
            // /delwarp <name>
            if (!warpNames.contains(args[0])){
                sendMessage(sender, Lang.DELWARP_FAILURE_NO_WARP.get().replace("{warp}", args[0]));
                return;
            }
            warpManager.delWarp(args[0]);
            sendMessage(sender, Lang.DELWARP_SUCCESS.get().replace("{warp}", args[0]));
            return;
        }

        sendMessage(sender, Lang.DELWARP_USAGE.get());
    }
}
