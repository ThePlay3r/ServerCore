package me.pljr.servercore.commands.warpcommands;

import me.pljr.pljrapispigot.commands.BukkitCommand;
import me.pljr.servercore.config.Lang;
import me.pljr.servercore.managers.WarpManager;
import org.bukkit.Location;
import org.bukkit.entity.Player;

public class SetwarpCommand extends BukkitCommand {

    private final WarpManager warpManager;

    public SetwarpCommand(WarpManager warpManager){
        super("setwarp", "servercore.setwarp.use");
        this.warpManager = warpManager;
    }

    @Override
    public void onPlayerCommand(Player player, String[] args){
        Location playerLoc = player.getLocation();

        if (args.length == 1){
            // /setwarp <name>
            warpManager.setWarp(args[0], playerLoc);
            sendMessage(player, Lang.SETWARP_SUCCESS.get().replace("{warp}", args[0]));
            return;
        }

        sendMessage(player, Lang.SETWARP_USAGE.get());
    }
}
