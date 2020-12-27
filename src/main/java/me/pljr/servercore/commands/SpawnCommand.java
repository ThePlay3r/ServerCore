package me.pljr.servercore.commands;

import me.pljr.pljrapispigot.utils.CommandUtil;
import me.pljr.pljrapispigot.utils.PlayerUtil;
import me.pljr.servercore.ServerCore;
import me.pljr.servercore.config.Lang;
import me.pljr.servercore.managers.SpawnManager;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class SpawnCommand extends CommandUtil {

    public SpawnCommand(){
        super("spawn", "servercore.spawn.use");
    }

    @Override
    public void onPlayerCommand(Player player, String[] args){
        SpawnManager spawnManager = ServerCore.getSpawnManager();
        Location spawnLoc = spawnManager.getLocation();

        // /spawn
        if (spawnLoc == null){
            sendMessage(player, Lang.SPAWN_FAILURE_NO_SPAWN.get());
            return;
        }
        PlayerUtil.teleport(player, spawnLoc, true);
        sendMessage(player, Lang.SPAWN_SUCCESS.get());
    }
}
