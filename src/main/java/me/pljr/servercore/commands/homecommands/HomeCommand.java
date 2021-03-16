package me.pljr.servercore.commands.homecommands;

import me.pljr.pljrapispigot.commands.BukkitCommand;
import me.pljr.pljrapispigot.utils.PlayerUtil;
import me.pljr.servercore.config.Lang;
import me.pljr.servercore.managers.PlayerManager;
import org.bukkit.Location;
import org.bukkit.entity.Player;

import java.util.UUID;

public class HomeCommand extends BukkitCommand {

    private final PlayerManager playerManager;

    public HomeCommand(PlayerManager playerManager){
        super("home", "servercore.home.use");
        this.playerManager = playerManager;
    }

    @Override
    public void onPlayerCommand(Player player, String[] args){
        UUID playerId = player.getUniqueId();

        playerManager.getPlayer(playerId, corePlayer -> {
            if (args.length == 1){
                // /home <name>
                Location homeLoc = corePlayer.getHomes().get(args[0]);
                if (homeLoc == null){
                    sendMessage(player, Lang.HOME_FAILURE_NO_HOME.get().replace("{name}", args[0]));
                    return;
                }
                PlayerUtil.teleport(player, homeLoc);
                sendMessage(player, Lang.HOME_SUCCESS.get().replace("{name}", args[0]));
                return;
            }

            sendMessage(player, Lang.HOME_USAGE.get());
        });
    }
}
