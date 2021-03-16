package me.pljr.servercore.commands.teleportcommands;

import me.pljr.pljrapispigot.commands.BukkitCommand;
import me.pljr.pljrapispigot.utils.PlayerUtil;
import me.pljr.servercore.config.Lang;
import me.pljr.servercore.managers.PlayerManager;
import org.bukkit.entity.Player;

import java.util.UUID;

public class TpacceptCommand extends BukkitCommand {

    private final PlayerManager playerManager;

    public TpacceptCommand(PlayerManager playerManager){
        super("tpaccept", "servercore.tpaccept.use");
        this.playerManager = playerManager;
    }

    @Override
    public void onPlayerCommand(Player player, String[] args){
        String playerName = player.getName();
        UUID playerId = player.getUniqueId();

        playerManager.getPlayer(playerId, corePlayer -> {
            Player tpaRequester = corePlayer.getTpaRequester();
            corePlayer.setTpaRequester(null);
            playerManager.setPlayer(playerId, corePlayer);

            // /tpaccept
            if (tpaRequester == null || !tpaRequester.isOnline()){
                sendMessage(player, Lang.TPACCEPT_FAILURE_NO_REQUEST.get());
                return;
            }
            String tpaRequesterName = tpaRequester.getName();
            PlayerUtil.teleport(tpaRequester, player);
            sendMessage(player, Lang.TPACCEPT_SUCCESS.get().replace("{player}", tpaRequesterName));
            sendMessage(tpaRequester, Lang.TPACCEPT_SUCCESS_PLAYER.get().replace("{player}", playerName));
        });

    }
}
