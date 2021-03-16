package me.pljr.servercore.commands.homecommands;

import me.pljr.pljrapispigot.commands.BukkitCommand;
import me.pljr.pljrapispigot.utils.PlayerUtil;
import me.pljr.servercore.config.Lang;
import me.pljr.servercore.managers.PlayerManager;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;

import java.util.UUID;

public class AHomeCommand extends BukkitCommand {

    private final PlayerManager playerManager;

    public AHomeCommand(PlayerManager playerManager){
        super("ahome", "servercore.ahome.use");
        this.playerManager = playerManager;
    }

    @Override
    public void onPlayerCommand(Player player, String[] args){
        if (args.length == 2){
            // /ahome <player> <home>
            OfflinePlayer target = Bukkit.getOfflinePlayer(args[0]);
            UUID targetId = target.getUniqueId();
            playerManager.getPlayer(targetId, coreTarget -> {
                Location targetHome = coreTarget.getHomes().get(args[1]);
                if (targetHome == null){
                    sendMessage(player, Lang.AHOME_FAILURE_NO_HOME.get().replace("{player}", args[0]).replace("{home}", args[1]));
                    return;
                }
                PlayerUtil.teleport(player, targetHome);
                sendMessage(player, Lang.AHOME_SUCCESS.get().replace("{player}", args[0]).replace("{home}", args[1]));
            });
            return;
        }

        sendMessage(player, Lang.AHOME_USAGE.get());
    }
}
