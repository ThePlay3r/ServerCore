package me.pljr.servercore.commands;

import me.pljr.pljrapispigot.utils.CommandUtil;
import me.pljr.servercore.ServerCore;
import me.pljr.servercore.config.Lang;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;

public class ASethomeCommand extends CommandUtil {

    public ASethomeCommand(){
        super("asethome", "servercore.asethome.use");
    }

    @Override
    public void onPlayerCommand(Player player, String[] args){
        if (args.length == 2){
            // /asethome <player> <home>
            OfflinePlayer target = Bukkit.getOfflinePlayer(args[0]);
            ServerCore.getPlayerManager().setHome(target, args[1], player.getLocation());
            sendMessage(player, Lang.ASETHOME_SUCCESS.get().replace("{name}", args[1]).replace("{player}", args[0]));
            sendMessage(target, Lang.ASETHOME_SUCCESS_PLAYER.get().replace("{name}", args[1]).replace("{player}", player.getName()));
            return;
        }

        sendMessage(player, Lang.ASETHOME_USAGE.get());
    }
}
