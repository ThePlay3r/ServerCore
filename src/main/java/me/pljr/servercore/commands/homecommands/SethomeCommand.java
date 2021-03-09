package me.pljr.servercore.commands.homecommands;

import me.pljr.pljrapispigot.utils.CommandUtil;
import me.pljr.servercore.config.Lang;
import me.pljr.servercore.managers.PlayerManager;
import me.pljr.servercore.utils.HomeUtil;
import org.bukkit.Location;
import org.bukkit.entity.Player;

import java.util.UUID;

public class SethomeCommand extends CommandUtil {

    private final PlayerManager playerManager;

    public SethomeCommand(PlayerManager playerManager){
        super("sethome", "servercore.sethome.use");
        this.playerManager = playerManager;
    }

    @Override
    public void onPlayerCommand(Player player, String[] args){
        Location playerLoc = player.getLocation();
        UUID playerId = player.getUniqueId();

        playerManager.getCorePlayer(playerId, corePlayer -> {
            int currentHomes = corePlayer.getHomes().size();
            int maxHomes = HomeUtil.getMax(player);
            boolean canMore = maxHomes > currentHomes;

            if (args.length == 1){
                // /sethome <home>
                if (corePlayer.getHomes().containsKey(args[0])){
                    playerManager.setHome(player, args[0], playerLoc);
                    sendMessage(player, Lang.SETHOME_SUCCESS.get().replace("{name}", args[0]));
                    return;
                }
                if (!canMore){
                    sendMessage(player, Lang.SETHOME_FAILURE_MAX_HOMES.get());
                    return;
                }
                playerManager.setHome(player, args[0], playerLoc);
                sendMessage(player, Lang.SETHOME_SUCCESS.get().replace("{name}", args[0]));
                return;
            }

            sendMessage(player, Lang.SETHOME_USAGE.get());
        });
    }
}
