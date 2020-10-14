package me.pljr.servercore.commands;

import me.pljr.pljrapi.utils.CommandUtil;
import me.pljr.servercore.ServerCore;
import me.pljr.servercore.config.CfgLang;
import me.pljr.servercore.enums.Lang;
import me.pljr.servercore.objects.CorePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.List;
import java.util.UUID;

public class TpignoreCommand extends CommandUtil implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)){
            sendMessage(sender, CfgLang.lang.get(Lang.NO_CONSOLE));
            return false;
        }
        Player player = (Player) sender;
        if (!checkPerm(player, "servercore.tpignore.use")) return false;

        UUID playerId = player.getUniqueId();

        CorePlayer corePlayer = ServerCore.getPlayerManager().getCorePlayer(playerId);
        List<String> tpaBlocked = corePlayer.getTpaBlocked();

        if (args.length == 1){
            // /tpignore <name>
            if (tpaBlocked.contains(args[0])){
                tpaBlocked.remove(args[0]);
                sendMessage(player, CfgLang.lang.get(Lang.TPIGNORE_SUCCESS_REMOVE).replace("%player", args[0]));
            }else{
                tpaBlocked.add(args[0]);
                sendMessage(player, CfgLang.lang.get(Lang.TPIGNORE_SUCCESS_ADD).replace("%player", args[0]));
            }
            ServerCore.getPlayerManager().setCorePlayer(playerId, corePlayer);
            return true;
        }

        sendMessage(player, CfgLang.lang.get(Lang.TPIGNORE_USAGE));
        return false;
    }
}
