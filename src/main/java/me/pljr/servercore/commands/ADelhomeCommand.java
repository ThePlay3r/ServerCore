package me.pljr.servercore.commands;

import me.pljr.pljrapispigot.utils.CommandUtil;
import me.pljr.servercore.ServerCore;
import me.pljr.servercore.config.Lang;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;

public class ADelhomeCommand extends CommandUtil {

    public ADelhomeCommand(){
        super("delhome", "servercore.adelhome.use");
    }

    @Override
    public void onPlayerCommand(Player player, String[] args){
        if (args.length == 2){
            // /delhome <player> <home>
            OfflinePlayer target = Bukkit.getOfflinePlayer(args[0]);
            if (!ServerCore.getPlayerManager().delHome(target, args[1])){
                sendMessage(player, Lang.ADELHOME_FAILURE_NO_HOME.get().replace("{player}", args[0]).replace("{name}", args[1]));
                return;
            }
            sendMessage(player, Lang.ADELHOME_SUCCESS.get().replace("{player}", args[0]).replace("{name}", args[1]));
            sendMessage(target, Lang.ADELHOME_SUCCESS_PLAYER.get().replace("{player}", player.getName()).replace("{name}", args[1]));
            return;
        }

        sendMessage(player, Lang.ADELHOME_USAGE.get());
    }

    @Override
    public void onConsoleCommand(ConsoleCommandSender sender, String[] args){
        if (args.length == 2){
            // /delhome <player> <home>
            OfflinePlayer target = Bukkit.getOfflinePlayer(args[0]);
            if (!ServerCore.getPlayerManager().delHome(target, args[1])){
                sendMessage(sender, Lang.ADELHOME_FAILURE_NO_HOME.get().replace("{player}", args[0]).replace("{name}", args[1]));
                return;
            }
            sendMessage(sender, Lang.ADELHOME_SUCCESS.get().replace("{player}", args[0]).replace("{name}", args[1]));
            sendMessage(target, Lang.ADELHOME_SUCCESS_PLAYER.get().replace("{player}", sender.getName()).replace("{name}", args[1]));
            return;
        }

        sendMessage(sender, Lang.ADELHOME_USAGE.get());
    }
}
