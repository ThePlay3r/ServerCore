package me.pljr.servercore.commands.teleportcommands;

import me.pljr.pljrapispigot.utils.CommandUtil;
import me.pljr.servercore.ServerCore;
import me.pljr.servercore.config.Lang;
import me.pljr.servercore.managers.SpawnManager;
import org.bukkit.Location;
import org.bukkit.entity.Player;

public class SetspawnCommand extends CommandUtil {

    private final SpawnManager spawnManager;

    public SetspawnCommand(SpawnManager spawnManager){
        super("setspawn", "servercore.setspawn.use");
        this.spawnManager = spawnManager;
    }

    @Override
    public void onPlayerCommand(Player player, String[] args){
        Location playerLoc = player.getLocation();

        // /setspawn
        spawnManager.setLocation(playerLoc);
        sendMessage(player, Lang.SETSPAWN_SUCCESS.get());
    }
}
