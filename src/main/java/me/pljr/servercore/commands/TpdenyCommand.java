package me.pljr.servercore.commands;

import me.pljr.pljrapispigot.utils.CommandUtil;
import me.pljr.servercore.ServerCore;
import me.pljr.servercore.config.Lang;
import me.pljr.servercore.objects.CorePlayer;
import org.bukkit.command.CommandExecutor;
import org.bukkit.entity.Player;

import java.util.UUID;

public class TpdenyCommand extends CommandUtil implements CommandExecutor {

    public TpdenyCommand(){
        super("tpdeny", "servercore.tpdeny.use");
    }

    @Override
    public void onPlayerCommand(Player player, String[] args){
        String playerName = player.getName();
        UUID playerId = player.getUniqueId();

        CorePlayer corePlayer = ServerCore.getPlayerManager().getCorePlayer(playerId);
        Player tpaRequester = corePlayer.getTpaRequester();
        corePlayer.setTpaRequester(null);
        ServerCore.getPlayerManager().setCorePlayer(playerId, corePlayer);

        // /tpdeny
        if (tpaRequester == null || !tpaRequester.isOnline()){
            sendMessage(player, Lang.TPDENY_FAILURE_NO_REQEUST.get());
            return;
        }
        String tpaRequesterName = tpaRequester.getName();
        sendMessage(player, Lang.TPDENY_SUCCESS.get().replace("{player}", tpaRequesterName));
        sendMessage(tpaRequester, Lang.TPDENY_SUCCESS_PLAYER.get().replace("{player}", playerName));
    }
}
