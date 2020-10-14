package me.pljr.servercore.commands;

import me.pljr.pljrapi.utils.CommandUtil;
import me.pljr.pljrapi.utils.PlayerUtil;
import me.pljr.servercore.ServerCore;
import me.pljr.servercore.config.CfgLang;
import me.pljr.servercore.config.CfgSettings;
import me.pljr.servercore.enums.Lang;
import me.pljr.servercore.managers.SpawnManager;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class ASpawnCommand extends CommandUtil implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!checkPerm(sender, "servercore.aspawn.use")) return false;

        String senderName = sender.getName();

        SpawnManager spawnManager = ServerCore.getSpawnManager();
        Location spawnLoc = spawnManager.getLocation();

        if (spawnLoc == null){
            sendMessage(sender, CfgLang.lang.get(Lang.SPAWN_FAILURE_NO_SPAWN));
            return false;
        }

        if (args.length == 1){
            // /aspawn <player>
            if (!checkPlayer(sender, args[0])) return false;
            Player target = Bukkit.getPlayer(args[0]);
            PlayerUtil.teleport(target, spawnLoc, CfgSettings.aspawnDelay);
            sendMessage(sender, CfgLang.lang.get(Lang.ASPAWN_SUCCESS).replace("%player", args[0]));
            sendMessage(target, CfgLang.lang.get(Lang.ASPAWN_SUCCESS_PLAYER).replace("%player", senderName));
            return true;
        }

        sendMessage(sender, CfgLang.lang.get(Lang.ASPAWN_USAGE));
        return false;
    }
}
