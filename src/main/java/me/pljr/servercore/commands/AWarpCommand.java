package me.pljr.servercore.commands;

import me.pljr.pljrapispigot.utils.CommandUtil;
import me.pljr.pljrapispigot.utils.PlayerUtil;
import me.pljr.servercore.ServerCore;
import me.pljr.servercore.config.Lang;
import me.pljr.servercore.managers.WarpManager;
import org.bukkit.Bukkit;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;

public class AWarpCommand extends CommandUtil {

    public AWarpCommand(){
        super("awarp", "servercore.awarp.use");
    }

    @Override
    public void onPlayerCommand(Player player, String[] args){
        WarpManager warpManager = ServerCore.getWarpManager();

        // /awarp <player> <warp>
        if (args.length == 2){
            if (!checkPlayer(player, args[0])) return;
            if (!warpManager.isWarp(args[1])){
                sendMessage(player, Lang.WARP_FAILURE_NO_WARP.get().replace("{warp}", args[1]));
                return;
            }
            Player target = Bukkit.getPlayer(args[0]);
            sendMessage(player, Lang.AWARP_SUCCESS.get()
                    .replace("{player}", args[0])
                    .replace("{warp}", args[1]));
            sendMessage(target, Lang.AWARP_SUCCESS.get()
                    .replace("{player}", "Console")
                    .replace("{warp}", args[1]));
            PlayerUtil.teleport(target, warpManager.getWarp(args[1]));
            return;
        }

        sendMessage(player, Lang.AWARP_USAGE.get());
    }

    @Override
    public void onConsoleCommand(ConsoleCommandSender sender, String[] args) {
        WarpManager warpManager = ServerCore.getWarpManager();

        // /awarp <player> <warp>
        if (args.length == 2){
            if (!checkPlayer(sender, args[0])) return;
            if (!warpManager.isWarp(args[1])){
                sendMessage(sender, Lang.WARP_FAILURE_NO_WARP.get().replace("{warp}", args[1]));
                return;
            }
            Player target = Bukkit.getPlayer(args[0]);
            sendMessage(sender, Lang.AWARP_SUCCESS.get()
                    .replace("{player}", args[0])
                    .replace("{warp}", args[1]));
            sendMessage(target, Lang.AWARP_SUCCESS.get()
                    .replace("{player}", "Console")
                    .replace("{warp}", args[1]));
            PlayerUtil.teleport(target, warpManager.getWarp(args[1]));
            return;
        }

        sendMessage(sender, Lang.AWARP_USAGE.get());
    }
}
