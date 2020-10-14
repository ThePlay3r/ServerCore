package me.pljr.servercore.commands;

import me.pljr.pljrapi.utils.CommandUtil;
import me.pljr.pljrapi.utils.PlayerUtil;
import me.pljr.servercore.ServerCore;
import me.pljr.servercore.config.CfgLang;
import me.pljr.servercore.enums.Lang;
import me.pljr.servercore.objects.CorePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.UUID;

public class TpacceptCommand extends CommandUtil implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sendMessage(sender, CfgLang.lang.get(Lang.NO_CONSOLE));
            return false;
        }
        Player player = (Player) sender;
        if (!checkPerm(player, "servercore.tpaccept.use")) return false;

        String playerName = player.getName();
        UUID playerId = player.getUniqueId();

        CorePlayer corePlayer = ServerCore.getPlayerManager().getCorePlayer(playerId);
        Player tpaRequester = corePlayer.getTpaRequester();
        corePlayer.setTpaRequester(null);
        ServerCore.getPlayerManager().setCorePlayer(playerId, corePlayer);

        // /tpaccept
        if (tpaRequester == null || !tpaRequester.isOnline()){
            sendMessage(player, CfgLang.lang.get(Lang.TPACCEPT_FAILURE_NO_REQUEST));
            return false;
        }
        String tpaRequesterName = tpaRequester.getName();
        PlayerUtil.teleport(tpaRequester, player, true);
        sendMessage(player, CfgLang.lang.get(Lang.TPACCEPT_SUCCESS).replace("%player", tpaRequesterName));
        sendMessage(tpaRequester, CfgLang.lang.get(Lang.TPACCEPT_SUCCESS_PLAYER).replace("%player", playerName));

        return true;
    }
}
