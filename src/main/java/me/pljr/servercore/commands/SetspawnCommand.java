package me.pljr.servercore.commands;

import me.pljr.pljrapispigot.utils.CommandUtil;
import me.pljr.servercore.ServerCore;
import me.pljr.servercore.config.Lang;
import me.pljr.servercore.managers.SpawnManager;
import org.bukkit.Location;
import org.bukkit.entity.Player;

public class SetspawnCommand extends CommandUtil {

    public SetspawnCommand(){
        super("setspawn", "servercore.setspawn.use");
    }

    @Override
    public void onPlayerCommand(Player player, String[] args){
        Location playerLoc = player.getLocation();

        SpawnManager spawnManager = ServerCore.getSpawnManager();

        // /setspawn
        spawnManager.setLocation(playerLoc);
        sendMessage(player, Lang.SETSPAWN_SUCCESS.get());
    }
}
