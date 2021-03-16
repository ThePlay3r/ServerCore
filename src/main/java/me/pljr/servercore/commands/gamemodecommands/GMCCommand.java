package me.pljr.servercore.commands.gamemodecommands;

import me.pljr.pljrapispigot.commands.BukkitCommand;
import me.pljr.servercore.config.Lang;
import org.bukkit.Bukkit;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;

public class GMCCommand extends BukkitCommand {

    public GMCCommand(){
        super("gmc", "servercore.gamemode.use.creative");
    }

    @Override
    public void onPlayerCommand(Player player, String[] args){
        if (args.length == 0){
            // /gmc
            Bukkit.dispatchCommand(player, "gamemode creative");
            return;
        }

        if (args.length == 1){
            // /gmc <player>
            Bukkit.dispatchCommand(player, "gamemode creative " + args[0]);
            return;
        }

        sendMessage(player, Lang.GAMEMODE_GMA_USAGE.get());
    }

    @Override
    public void onConsoleCommand(ConsoleCommandSender sender, String[] args){
        if (args.length == 1){
            // /gmc <player>
            Bukkit.dispatchCommand(sender, "gamemode creative " + args[0]);
            return;
        }

        sendMessage(sender, Lang.GAMEMODE_GMA_USAGE.get());
    }
}
