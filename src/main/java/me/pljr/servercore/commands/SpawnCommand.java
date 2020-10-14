package me.pljr.servercore.commands;

import me.pljr.pljrapi.utils.CommandUtil;
import me.pljr.pljrapi.utils.PlayerUtil;
import me.pljr.servercore.ServerCore;
import me.pljr.servercore.config.CfgLang;
import me.pljr.servercore.enums.Lang;
import me.pljr.servercore.managers.SpawnManager;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class SpawnCommand extends CommandUtil implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)){
            sendMessage(sender, CfgLang.lang.get(Lang.NO_CONSOLE));
            return false;
        }
        Player player = (Player) sender;
        if (!checkPerm(player, "servercore.spawn.use")) return false;

        SpawnManager spawnManager = ServerCore.getSpawnManager();
        Location spawnLoc = spawnManager.getLocation();

        // /spawn
        if (spawnLoc == null){
            sendMessage(player, CfgLang.lang.get(Lang.SPAWN_FAILURE_NO_SPAWN));
            return false;
        }
        PlayerUtil.teleport(player, spawnLoc, true);
        sendMessage(player, CfgLang.lang.get(Lang.SPAWN_SUCCESS));

        return true;
    }
}
