package me.pljr.servercore.commands.gamemodecommands;

import me.pljr.pljrapispigot.utils.CommandUtil;
import me.pljr.servercore.config.Lang;
import org.bukkit.Bukkit;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;

public class GMACommand extends CommandUtil {

    public GMACommand(){
        super("gma", "servercore.gamemode.use.adventure");
    }

    @Override
    public void onPlayerCommand(Player player, String[] args){
        if (args.length == 0){
            // /gma
            Bukkit.dispatchCommand(player, "gamemode adventure");
            return;
        }

        if (args.length == 1){
            // /gma <player>
            Bukkit.dispatchCommand(player, "gamemode adventure " + args[0]);
            return;
        }

        sendMessage(player, Lang.GAMEMODE_GMA_USAGE.get());
    }

    @Override
    public void onConsoleCommand(ConsoleCommandSender sender, String[] args){
        if (args.length == 1){
            // /gma <player>
            Bukkit.dispatchCommand(sender, "gamemode adventure " + args[0]);
            return;
        }

        sendMessage(sender, Lang.GAMEMODE_GMA_USAGE.get());
    }
}
