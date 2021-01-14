package me.pljr.servercore.commands;

import me.pljr.pljrapispigot.utils.CommandUtil;
import me.pljr.pljrapispigot.utils.PlayerUtil;
import me.pljr.servercore.ServerCore;
import me.pljr.servercore.config.Lang;
import me.pljr.servercore.objects.CorePlayer;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;

import java.util.UUID;

public class AHomeCommand extends CommandUtil {

    public AHomeCommand(){
        super("ahome", "servercore.ahome.use");
    }

    @Override
    public void onPlayerCommand(Player player, String[] args){
        if (args.length == 2){
            // /ahome <player> <home>
            OfflinePlayer target = Bukkit.getOfflinePlayer(args[0]);
            UUID targetId = target.getUniqueId();
            CorePlayer coreTarget = ServerCore.getPlayerManager().getCorePlayer(targetId);
            Location targetHome = coreTarget.getHome(args[1]);
            if (targetHome == null){
                sendMessage(player, Lang.AHOME_FAILURE_NO_HOME.get().replace("{player}", args[0]).replace("{home}", args[1]));
                return;
            }
            PlayerUtil.teleport(player, targetHome);
            sendMessage(player, Lang.AHOME_SUCCESS.get().replace("{player}", args[0]).replace("{home}", args[1]));
            return;
        }

        sendMessage(player, Lang.AHOME_USAGE.get());
    }
}
