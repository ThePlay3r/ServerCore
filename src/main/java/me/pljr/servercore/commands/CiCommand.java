package me.pljr.servercore.commands;

import me.pljr.pljrapispigot.utils.CommandUtil;
import me.pljr.servercore.config.Lang;
import org.bukkit.Bukkit;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;

public class CiCommand extends CommandUtil {

    public CiCommand(){
        super("ci", "servercore.ci.use");
    }

    @Override
    public void onPlayerCommand(Player player, String[] args){
        // /ci
        if (args.length == 0){
            player.getInventory().clear();
            player.updateInventory();
            sendMessage(player, Lang.CI_SUCCESS.get());
            return;
        } else if (args.length == 1){
            // /ci <player>
            if (!checkPerm(player, "servercore.ci.use.others")) return;
            if (!checkPlayer(player, args[0])) return;
            Player target = Bukkit.getPlayer(args[0]);
            target.getInventory().clear();
            target.updateInventory();
            sendMessage(player, Lang.CI_SUCCESS_OTHERS.get().replace("{player}", args[0]));
            sendMessage(target, Lang.CI_SUCCESS_OTHERS_PLAYER.get().replace("{player}", player.getName()));
            return;
        }

        sendMessage(player, Lang.CI_USAGE.get());
    }

    @Override
    public void onConsoleCommand(ConsoleCommandSender sender, String[] args){
        if (args.length == 1){
            // /ci <player>
            if (!checkPlayer(sender, args[0])) return;
            Player target = Bukkit.getPlayer(args[0]);
            target.getInventory().clear();
            target.updateInventory();
            sendMessage(sender, Lang.CI_SUCCESS_OTHERS.get().replace("{player}", args[0]));
            sendMessage(target, Lang.CI_SUCCESS_OTHERS_PLAYER.get().replace("{player}", sender.getName()));
            return;
        }

        sendMessage(sender, Lang.CI_USAGE.get());
    }
}
