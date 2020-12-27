package me.pljr.servercore.commands;

import me.pljr.pljrapispigot.utils.CommandUtil;
import me.pljr.servercore.config.Lang;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;

public class GMSPCommand extends CommandUtil implements CommandExecutor {

    public GMSPCommand(){
        super("gmsp", "servercore.gamemode.use.spectator");
    }

    @Override
    public void onPlayerCommand(Player player, String[] args){
        if (args.length == 0){
            // /gmsp
            Bukkit.dispatchCommand(player, "gamemode spectator");
            return;
        }

        if (args.length == 1){
            // /gmsp <player>
            Bukkit.dispatchCommand(player, "gamemode spectator " + args[0]);
            return;
        }

        sendMessage(player, Lang.GAMEMODE_GMA_USAGE.get());
    }

    @Override
    public void onConsoleCommand(ConsoleCommandSender sender, String[] args){
        if (args.length == 1){
            // /gmsp <player>
            Bukkit.dispatchCommand(sender, "gamemode spectator " + args[0]);
            return;
        }

        sendMessage(sender, Lang.GAMEMODE_GMA_USAGE.get());
    }
}
