package me.pljr.servercore.commands;

import me.pljr.pljrapispigot.utils.CommandUtil;
import me.pljr.pljrapispigot.utils.PlayerUtil;
import me.pljr.servercore.ServerCore;
import me.pljr.servercore.config.Lang;
import me.pljr.servercore.objects.CorePlayer;
import org.bukkit.Location;
import org.bukkit.entity.Player;

import java.util.UUID;

public class HomeCommand extends CommandUtil {

    public HomeCommand(){
        super("home", "servercore.home.use");
    }

    @Override
    public void onPlayerCommand(Player player, String[] args){
        UUID playerId = player.getUniqueId();

        CorePlayer corePlayer = ServerCore.getPlayerManager().getCorePlayer(playerId);

        if (args.length == 1){
            // /home <name>
            Location homeLoc = corePlayer.getHome(args[0]);
            if (homeLoc == null){
                sendMessage(player, Lang.HOME_FAILURE_NO_HOME.get().replace("{name}", args[0]));
                return;
            }
            PlayerUtil.teleport(player, homeLoc, true);
            sendMessage(player, Lang.HOME_SUCCESS.get().replace("{name}", args[0]));
            return;
        }

        sendMessage(player, Lang.HOME_USAGE.get());
    }
}
