package me.pljr.servercore.commands;

import me.pljr.pljrapispigot.utils.CommandUtil;
import me.pljr.pljrapispigot.utils.PlayerUtil;
import me.pljr.servercore.ServerCore;
import me.pljr.servercore.config.CfgSettings;
import me.pljr.servercore.config.Lang;
import me.pljr.servercore.managers.SpawnManager;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;

public class ASpawnCommand extends CommandUtil {

    public ASpawnCommand(){
        super("aspawn", "servercore.aspawn.use");
    }

    @Override
    public void onPlayerCommand(Player player, String[] args) {
        String playerName = player.getName();

        SpawnManager spawnManager = ServerCore.getSpawnManager();
        Location spawnLoc = spawnManager.getLocation();

        if (spawnLoc == null){
            sendMessage(player, Lang.SPAWN_FAILURE_NO_SPAWN.get());
            return;
        }

        if (args.length == 1){
            // /aspawn <player>
            if (!checkPlayer(player, args[0])) return;
            Player target = Bukkit.getPlayer(args[0]);
            PlayerUtil.teleport(target, spawnLoc, CfgSettings.ASPAWN_DELAY);
            sendMessage(player, Lang.ASPAWN_SUCCESS.get().replace("{player}", args[0]));
            sendMessage(target, Lang.ASPAWN_SUCCESS_PLAYER.get().replace("{player}", playerName));
            return;
        }

        sendMessage(player, Lang.ASPAWN_USAGE.get());
    }

    @Override
    public void onConsoleCommand(ConsoleCommandSender sender, String[] args) {
        String senderName = sender.getName();

        SpawnManager spawnManager = ServerCore.getSpawnManager();
        Location spawnLoc = spawnManager.getLocation();

        if (spawnLoc == null){
            sendMessage(sender, Lang.SPAWN_FAILURE_NO_SPAWN.get());
            return;
        }

        if (args.length == 1){
            // /aspawn <player>
            if (!checkPlayer(sender, args[0])) return;
            Player target = Bukkit.getPlayer(args[0]);
            PlayerUtil.teleport(target, spawnLoc, CfgSettings.ASPAWN_DELAY);
            sendMessage(sender, Lang.ASPAWN_SUCCESS.get().replace("{player}", args[0]));
            sendMessage(target, Lang.ASPAWN_SUCCESS_PLAYER.get().replace("{player}", senderName));
            return;
        }

        sendMessage(sender, Lang.ASPAWN_USAGE.get());
    }
}
