package me.pljr.servercore.commands.teleportcommands;

import me.pljr.pljrapispigot.commands.BukkitCommand;
import me.pljr.servercore.config.Lang;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;

public class TPCommand extends BukkitCommand {

    public TPCommand(){
        super("tp", "servercore.tp.use");
    }

    private void executePlayerToPlayer(CommandSender sender, String[] args){
        String senderName = sender.getName();
        if (!checkPlayer(sender, args[0])) return;
        if (!checkPlayer(sender, args[1])) return;
        Player player1 = Bukkit.getPlayer(args[0]);
        Player player2 = Bukkit.getPlayer(args[1]);
        player1.teleport(player2);
        sendMessage(sender, Lang.TP_SUCCESS_OTHERS.get().replace("{player1}", args[0]).replace("{player2}", args[1]));
        sendMessage(player1, Lang.TP_SUCCESS_OTHERS_PLAYER1.get().replace("{target}", args[1]).replace("{player}", senderName));
        sendMessage(player2, Lang.TP_SUCCESS_OTHERS_PLAYER2.get().replace("{target}", args[0]).replace("{player}", senderName));
    }

    @Override
    public void onPlayerCommand(Player player, String[] args){
        String playerName = player.getName();

        if (args.length == 1){
            // /tp <player>
            if (!checkPlayer(player, args[0])) return;
            Player target = Bukkit.getPlayer(args[0]);
            player.teleport(target);
            sendMessage(player, Lang.TP_SUCCESS.get().replace("{player}", args[0]));
            sendMessage(target, Lang.TP_SUCCESS_PLAYER.get().replace("{player}", playerName));
            return;
        }

        else if (args.length == 2){
            // /tp <player> <player>
            executePlayerToPlayer(player, args);
            return;
        }

        sendMessage(player, Lang.TP_USAGE.get());
    }

    @Override
    public void onConsoleCommand(ConsoleCommandSender sender, String[] args){
        if (args.length == 2){
            // /tp <player> <player>
            executePlayerToPlayer(sender, args);
            return;
        }

        sendMessage(sender, Lang.TP_USAGE.get());
    }
}
