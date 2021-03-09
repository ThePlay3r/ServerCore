package me.pljr.servercore.commands.teleportcommands;

import me.pljr.pljrapispigot.utils.CommandUtil;
import me.pljr.servercore.config.Lang;
import me.pljr.servercore.managers.SpawnManager;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class ASpawnCommand extends CommandUtil {

    private final SpawnManager spawnManager;

    public ASpawnCommand(SpawnManager spawnManager){
        super("aspawn", "servercore.aspawn.use");
        this.spawnManager = spawnManager;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        String senderName = sender.getName();

        Location spawnLoc = spawnManager.getLocation();

        if (spawnLoc == null){
            sendMessage(sender, Lang.SPAWN_FAILURE_NO_SPAWN.get());
            return false;
        }

        if (args.length == 1){
            // /aspawn <player>
            if (!checkPlayer(sender, args[0])) return false;
            Player target = Bukkit.getPlayer(args[0]);
            target.teleport(spawnLoc);
            sendMessage(sender, Lang.ASPAWN_SUCCESS.get().replace("{player}", args[0]));
            sendMessage(target, Lang.ASPAWN_SUCCESS_PLAYER.get().replace("{player}", senderName));
            return true;
        }

        sendMessage(sender, Lang.ASPAWN_USAGE.get());
        return false;
    }
}
