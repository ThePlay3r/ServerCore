package me.pljr.servercore.commands;

import me.pljr.pljrapispigot.utils.CommandUtil;
import me.pljr.servercore.ServerCore;
import me.pljr.servercore.config.Lang;
import me.pljr.servercore.managers.WarpManager;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class SetwarpCommand extends CommandUtil {

    public SetwarpCommand(){
        super("setwarp", "servercore.setwarp.use");
    }

    @Override
    public void onPlayerCommand(Player player, String[] args){
        Location playerLoc = player.getLocation();

        WarpManager warpManager = ServerCore.getWarpManager();

        if (args.length == 1){
            // /setwarp <name>
            warpManager.setWarp(args[0], playerLoc);
            sendMessage(player, Lang.SETWARP_SUCCESS.get().replace("{warp}", args[0]));
            return;
        }

        sendMessage(player, Lang.SETWARP_USAGE.get());
    }
}
