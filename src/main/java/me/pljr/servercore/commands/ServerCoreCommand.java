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

import java.util.UUID;

public class ServerCoreCommand extends CommandUtil implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)){
            sendMessage(sender, CfgLang.lang.get(Lang.NO_CONSOLE));
            return false;
        }
        Player player = (Player) sender;
        if (!checkPerm(player, "servercore.servercore.use")) return false;

        UUID playerId = player.getUniqueId();

        CorePlayer corePlayer = ServerCore.getPlayerManager().getCorePlayer(playerId);

        if (args.length == 1){
            // /servercore spy
            if (args[0].equalsIgnoreCase("spy")){
                if (!checkPerm(player, "servercore.servercore.use.spy")) return false;
                boolean isSpy = corePlayer.isSpy();
                if (isSpy){
                    corePlayer.setSpy(false);
                    sendMessage(player, CfgLang.lang.get(Lang.SERVERCORE_SPY_SUCCESS_OFF));
                }else{
                    corePlayer.setSpy(true);
                    sendMessage(player, CfgLang.lang.get(Lang.SERVERCORE_SPY_SUCCESS_ON));
                }
                return true;
            }
        }

        sendMessage(player, CfgLang.lang.get(Lang.SERVERCORE_USAGE));
        return false;
    }
}
