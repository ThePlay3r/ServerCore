package me.pljr.servercore.commands.gamemodecommands;

import me.pljr.pljrapispigot.commands.BukkitCommand;
import me.pljr.servercore.config.Lang;
import org.bukkit.Bukkit;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;

public class GMSCommand extends BukkitCommand {

    public GMSCommand(){
        super("gms", "servercore.gamemode.use.survival");
    }

    @Override
    public void onPlayerCommand(Player player, String[] args){
        if (args.length == 0){
            // /gms
            Bukkit.dispatchCommand(player, "gamemode survival");
            return;
        }

        if (args.length == 1){
            // /gms <player>
            Bukkit.dispatchCommand(player, "gamemode survival " + args[0]);
            return;
        }

        sendMessage(player, Lang.GAMEMODE_GMA_USAGE.get());
    }

    @Override
    public void onConsoleCommand(ConsoleCommandSender sender, String[] args){
        if (args.length == 1){
            // /gms <player>
            Bukkit.dispatchCommand(sender, "gamemode survival " + args[0]);
            return;
        }

        sendMessage(sender, Lang.GAMEMODE_GMA_USAGE.get());
    }
}
