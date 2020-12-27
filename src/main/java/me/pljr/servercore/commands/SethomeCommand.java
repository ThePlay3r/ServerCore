package me.pljr.servercore.commands;

import me.pljr.pljrapispigot.utils.CommandUtil;
import me.pljr.servercore.ServerCore;
import me.pljr.servercore.config.Lang;
import me.pljr.servercore.objects.CorePlayer;
import me.pljr.servercore.utils.HomeUtil;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.UUID;

public class SethomeCommand extends CommandUtil {

    public SethomeCommand(){
        super("sethome", "servercore.sethome.use");
    }

    @Override
    public void onPlayerCommand(Player player, String[] args){
        Location playerLoc = player.getLocation();
        UUID playerId = player.getUniqueId();

        CorePlayer corePlayer = ServerCore.getPlayerManager().getCorePlayer(playerId);
        int currentHomes = corePlayer.getHomes().size();
        int maxHomes = HomeUtil.getMax(player);
        boolean canMore = maxHomes > currentHomes;

        if (args.length == 1){
            // /sethome <home>
            if (corePlayer.getHomes().containsKey(args[0])){
                ServerCore.getPlayerManager().setHome(player, args[0], playerLoc);
                sendMessage(player, Lang.SETHOME_SUCCESS.get().replace("{name}", args[0]));
                return;
            }
            if (!canMore){
                sendMessage(player, Lang.SETHOME_FAILURE_MAX_HOMES.get());
                return;
            }
            ServerCore.getPlayerManager().setHome(player, args[0], playerLoc);
            sendMessage(player, Lang.SETHOME_SUCCESS.get().replace("{name}", args[0]));
            return;
        }

        sendMessage(player, Lang.SETHOME_USAGE.get());
    }
}
