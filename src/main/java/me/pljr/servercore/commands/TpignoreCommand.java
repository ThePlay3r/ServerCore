package me.pljr.servercore.commands;

import me.pljr.pljrapispigot.utils.CommandUtil;
import me.pljr.servercore.ServerCore;
import me.pljr.servercore.config.Lang;
import me.pljr.servercore.objects.CorePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.List;
import java.util.UUID;

public class TpignoreCommand extends CommandUtil {

    public TpignoreCommand(){
        super("tpignore", "servercore.tpignore.use");
    }

    @Override
    public void onPlayerCommand(Player player, String[] args){
        UUID playerId = player.getUniqueId();

        CorePlayer corePlayer = ServerCore.getPlayerManager().getCorePlayer(playerId);
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
            ServerCore.getPlayerManager().setCorePlayer(playerId, corePlayer);
            return;
        }

        sendMessage(player, Lang.TPIGNORE_USAGE.get());
    }
}
