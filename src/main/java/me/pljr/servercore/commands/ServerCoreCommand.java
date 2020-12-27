package me.pljr.servercore.commands;

import me.pljr.pljrapispigot.utils.CommandUtil;
import me.pljr.servercore.ServerCore;
import me.pljr.servercore.config.Lang;
import me.pljr.servercore.objects.CorePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.UUID;

public class ServerCoreCommand extends CommandUtil implements CommandExecutor {

    public ServerCoreCommand(){
        super("servercore", "servercore.servercore.use");
    }

    @Override
    public void onPlayerCommand(Player player, String[] args){
        UUID playerId = player.getUniqueId();

        CorePlayer corePlayer = ServerCore.getPlayerManager().getCorePlayer(playerId);

        if (args.length == 1){
            // /servercore spy
            if (args[0].equalsIgnoreCase("spy")){
                if (!checkPerm(player, "servercore.servercore.use.spy")) return;
                boolean isSpy = corePlayer.isSpy();
                if (isSpy){
                    corePlayer.setSpy(false);
                    sendMessage(player, Lang.SERVERCORE_SPY_SUCCESS_OFF.get());
                }else{
                    corePlayer.setSpy(true);
                    sendMessage(player, Lang.SERVERCORE_SPY_SUCCESS_ON.get());
                }
                return;
            }

            // /servercore reload
            if (args[0].equalsIgnoreCase("reload")){
                if (!checkPerm(player, "servercore.servercore.use.reload")) return;
                ServerCore.getInstance().setupConfig();
                player.sendMessage("§a§l✔ Reloaded");
                return;
            }
        }

        sendMessage(player, Lang.SERVERCORE_USAGE.get());

    }
}
