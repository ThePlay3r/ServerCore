package me.pljr.servercore.commands.homecommands;

import me.pljr.pljrapispigot.utils.CommandUtil;
import me.pljr.servercore.config.Lang;
import me.pljr.servercore.managers.PlayerManager;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class AHomesCommand extends CommandUtil {

    private final PlayerManager playerManager;

    public AHomesCommand(PlayerManager playerManager){
        super("ahomes", "servercore.ahomes.use");
        this.playerManager = playerManager;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (args.length == 1){
            // /ahomes <player>
            OfflinePlayer target = Bukkit.getOfflinePlayer(args[0]);
            UUID targetId = target.getUniqueId();
            playerManager.getCorePlayer(targetId, coreTarget -> {
                HashMap<String, Location> targetHomes = coreTarget.getHomes();
                sendMessage(sender, Lang.AHOMES_SUCCESS_TITLE.get().replace("{player}", args[0]));
                for (Map.Entry<String, Location> home : targetHomes.entrySet()){
                    String homeName = home.getKey();
                    sendMessage(sender, Lang.AHOMES_SUCCESS_FORMAT.get().replace("{name}", homeName).replace("{player}", args[0]));
                }
            });
            return true;
        }

        sendMessage(sender, Lang.AHOMES_USAGE.get());
        return false;
    }
}
