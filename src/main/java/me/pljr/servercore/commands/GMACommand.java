package me.pljr.servercore.commands;

import me.pljr.pljrapi.utils.CommandUtil;
import me.pljr.servercore.config.CfgLang;
import me.pljr.servercore.enums.Lang;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class GMACommand extends CommandUtil implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!checkPerm(sender, "servercore.gamemode.use.adventure")) return false;

        if (args.length == 0){
            // /gma
            Bukkit.dispatchCommand(sender, "gamemode adventure");
            return true;
        }

        if (args.length == 1){
            // /gma <player>
            Bukkit.dispatchCommand(sender, "gamemode adventure " + args[0]);
            return true;
        }

        sendMessage(sender, CfgLang.lang.get(Lang.GAMEMODE_GMA_USAGE));
        return false;
    }
}
