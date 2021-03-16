package me.pljr.servercore.commands.teleportcommands;

import me.pljr.pljrapispigot.commands.BukkitCommand;
import me.pljr.pljrapispigot.utils.PlayerUtil;
import me.pljr.servercore.config.Lang;
import me.pljr.servercore.managers.SpawnManager;
import org.bukkit.Location;
import org.bukkit.entity.Player;

public class SpawnCommand extends BukkitCommand {

    private final SpawnManager spawnManager;

    public SpawnCommand(SpawnManager spawnManager){
        super("spawn", "servercore.spawn.use");
        this.spawnManager = spawnManager;
    }

    @Override
    public void onPlayerCommand(Player player, String[] args){
        Location spawnLoc = spawnManager.getLocation();

        // /spawn
        if (spawnLoc == null){
            sendMessage(player, Lang.SPAWN_FAILURE_NO_SPAWN.get());
            return;
        }
        PlayerUtil.teleport(player, spawnLoc);
        sendMessage(player, Lang.SPAWN_SUCCESS.get());
    }
}
