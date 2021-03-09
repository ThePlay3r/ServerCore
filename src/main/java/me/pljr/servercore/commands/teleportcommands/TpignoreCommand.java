package me.pljr.servercore.commands.teleportcommands;

import me.pljr.pljrapispigot.utils.CommandUtil;
import me.pljr.servercore.ServerCore;
import me.pljr.servercore.config.Lang;
import me.pljr.servercore.managers.PlayerManager;
import me.pljr.servercore.objects.CorePlayer;
import org.bukkit.entity.Player;

import java.util.List;
import java.util.UUID;

public class TpignoreCommand extends CommandUtil {

    private final PlayerManager playerManager;

    public TpignoreCommand(PlayerManager playerManager){
        super("tpignore", "servercore.tpignore.use");
        this.playerManager = playerManager;
    }

    @Override
    public void onPlayerCommand(Player player, String[] args){
        UUID playerId = player.getUniqueId();

        playerManager.getCorePlayer(playerId, corePlayer -> {
            List<String> tpaBlocked = corePlayer.getTpaBlocked();

            if (args.length == 1){
                // /tpignore <name>
                if (tpaBlocked.contains(args[0])){
                    tpaBlocked.remove(args[0]);
                    sendMessage(player, Lang.TPIGNORE_SUCCESS_REMOVE.get().replace("{player}", args[0]));
                }else{
                    tpaBlocked.add(args[0]);
                    sendMessage(player, Lang.TPIGNORE_SUCCESS_ADD.get().replace("{player}", args[0]));
                }
                playerManager.setCorePlayer(playerId, corePlayer);
                return;
            }

            sendMessage(player, Lang.TPIGNORE_USAGE.get());
        });
    }
}
