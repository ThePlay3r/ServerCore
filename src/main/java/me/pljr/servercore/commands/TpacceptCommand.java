package me.pljr.servercore.commands;

import me.pljr.pljrapispigot.utils.CommandUtil;
import me.pljr.pljrapispigot.utils.PlayerUtil;
import me.pljr.servercore.ServerCore;
import me.pljr.servercore.config.Lang;
import me.pljr.servercore.objects.CorePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.UUID;

public class TpacceptCommand extends CommandUtil {

    public TpacceptCommand(){
        super("tpaaccept", "servercore.tpaccept.use");
    }

    @Override
    public void onPlayerCommand(Player player, String[] args){
        String playerName = player.getName();
        UUID playerId = player.getUniqueId();

        CorePlayer corePlayer = ServerCore.getPlayerManager().getCorePlayer(playerId);
        Player tpaRequester = corePlayer.getTpaRequester();
        corePlayer.setTpaRequester(null);
        ServerCore.getPlayerManager().setCorePlayer(playerId, corePlayer);

        // /tpaccept
        if (tpaRequester == null || !tpaRequester.isOnline()){
            sendMessage(player, Lang.TPACCEPT_FAILURE_NO_REQUEST.get());
            return;
        }
        String tpaRequesterName = tpaRequester.getName();
        PlayerUtil.teleport(tpaRequester, player, true);
        sendMessage(player, Lang.TPACCEPT_SUCCESS.get().replace("{player}", tpaRequesterName));
        sendMessage(tpaRequester, Lang.TPACCEPT_SUCCESS_PLAYER.get().replace("{player}", playerName));
    }
}
