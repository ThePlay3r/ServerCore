package me.pljr.servercore.commands.warpcommands;

import me.pljr.pljrapispigot.commands.BukkitCommand;
import me.pljr.pljrapispigot.utils.PlayerUtil;
import me.pljr.servercore.config.Lang;
import me.pljr.servercore.managers.WarpManager;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class AWarpCommand extends BukkitCommand {

    private final WarpManager warpManager;

    public AWarpCommand(WarpManager warpManager){
        super("awarp", "servercore.awarp.use");
        this.warpManager = warpManager;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        // /awarp <player> <warp>
        if (args.length == 2) {
            if (!checkPlayer(sender, args[0])) return false;
            if (!warpManager.isWarp(args[1])) {
                sendMessage(sender, Lang.WARP_FAILURE_NO_WARP.get().replace("{warp}", args[1]));
                return false;
            }
            Player target = Bukkit.getPlayer(args[0]);
            sendMessage(sender, Lang.AWARP_SUCCESS.get()
                    .replace("{player}", args[0])
                    .replace("{warp}", args[1]));
            sendMessage(target, Lang.AWARP_SUCCESS.get()
                    .replace("{player}", "Console")
                    .replace("{warp}", args[1]));
            target.teleport(warpManager.getWarp(args[1]));
            PlayerUtil.teleport(target, warpManager.getWarp(args[1]));
            return true;
        }

        sendMessage(sender, Lang.AWARP_USAGE.get());
        return false;
    }
}
