package me.pljr.servercore.commands.warpcommands;

import me.pljr.pljrapispigot.utils.CommandUtil;
import me.pljr.servercore.ServerCore;
import me.pljr.servercore.config.Lang;
import me.pljr.servercore.managers.WarpManager;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;

import java.util.List;

public class DelwarpCommand extends CommandUtil {

    private final WarpManager warpManager;

    public DelwarpCommand(WarpManager warpManager){
        super("delwarp", "servercore.delwarp.use");
        this.warpManager = warpManager;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        List<String> warpNames = warpManager.getWarpNames();

        if (args.length == 1){
            // /delwarp <name>
            if (!warpNames.contains(args[0])){
                sendMessage(sender, Lang.DELWARP_FAILURE_NO_WARP.get().replace("{warp}", args[0]));
                return false;
            }
            warpManager.delWarp(args[0]);
            sendMessage(sender, Lang.DELWARP_SUCCESS.get().replace("{warp}", args[0]));
            return true;
        }

        sendMessage(sender, Lang.DELWARP_USAGE.get());
        return false;
    }
}