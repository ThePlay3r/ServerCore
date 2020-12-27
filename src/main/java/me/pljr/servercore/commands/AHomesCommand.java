package me.pljr.servercore.commands;

import me.pljr.pljrapispigot.utils.CommandUtil;
import me.pljr.servercore.ServerCore;
import me.pljr.servercore.config.Lang;
import me.pljr.servercore.objects.CorePlayer;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class AHomesCommand extends CommandUtil {

    public AHomesCommand(){
        super("ahomes", "servercore.ahomes.use");
    }

    @Override
    public void onPlayerCommand(Player player, String[] args){
        if (args.length == 1){
            // /ahomes <player>
            OfflinePlayer target = Bukkit.getOfflinePlayer(args[0]);
            UUID targetId = target.getUniqueId();
            CorePlayer coreTarget = ServerCore.getPlayerManager().getCorePlayer(targetId);
            HashMap<String, Location> targetHomes = coreTarget.getHomes();
            sendMessage(player, Lang.AHOMES_SUCCESS_TITLE.get().replace("{player}", args[0]));
            for (Map.Entry<String, Location> home : targetHomes.entrySet()){
                String homeName = home.getKey();
                sendMessage(player, Lang.AHOMES_SUCCESS_FORMAT.get().replace("{name}", homeName).replace("{player}", args[0]));
            }
            return;
        }

        sendMessage(player, Lang.AHOMES_USAGE.get());
    }

    @Override
    public void onConsoleCommand(ConsoleCommandSender sender, String[] args){
        if (args.length == 1){
            // /ahomes <player>
            OfflinePlayer target = Bukkit.getOfflinePlayer(args[0]);
            UUID targetId = target.getUniqueId();
            CorePlayer coreTarget = ServerCore.getPlayerManager().getCorePlayer(targetId);
            HashMap<String, Location> targetHomes = coreTarget.getHomes();
            sendMessage(sender, Lang.AHOMES_SUCCESS_TITLE.get().replace("{player}", args[0]));
            for (Map.Entry<String, Location> home : targetHomes.entrySet()){
                String homeName = home.getKey();
                sendMessage(sender, Lang.AHOMES_SUCCESS_FORMAT.get().replace("{name}", homeName).replace("{player}", args[0]));
            }
            return;
        }

        sendMessage(sender, Lang.AHOMES_USAGE.get());
    }
}
