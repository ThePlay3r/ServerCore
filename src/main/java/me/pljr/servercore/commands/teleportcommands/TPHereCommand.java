package me.pljr.servercore.commands.teleportcommands;

import me.pljr.pljrapispigot.utils.CommandUtil;
import me.pljr.servercore.config.Lang;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class TPHereCommand extends CommandUtil {

    public TPHereCommand(){
        super("tphere", "servercore.tphere.use");
    }

    @Override
    public void onPlayerCommand(Player player, String[] args){
        String playerName = player.getName();

        if (args.length == 1){
            // /tphere <player>
            if (!checkPlayer(player, args[0])) return;
            Player target = Bukkit.getPlayer(args[0]);
            target.teleport(player);
            sendMessage(player, Lang.TPHERE_SUCCESS.get().replace("{player}", args[0]));
            sendMessage(target, Lang.TPHERE_SUCCESS_PLAYER.get().replace("{player}", playerName));
            return;
        }

        sendMessage(player, Lang.TPHERE_USAGE.get());
    }
}
