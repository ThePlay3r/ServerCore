package me.pljr.servercore.commands;

import me.pljr.pljrapi.utils.CommandUtil;
import me.pljr.pljrapi.utils.ItemStackUtil;
import me.pljr.pljrapi.utils.PlayerUtil;
import me.pljr.servercore.config.CfgLang;
import me.pljr.servercore.enums.Lang;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class SkullCommand extends CommandUtil implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)){
            sendMessage(sender, CfgLang.lang.get(Lang.NO_CONSOLE));
            return false;
        }
        Player player = (Player) sender;
        if (!checkPerm(player, "servercore.skull.use")) return false;

        String playerName = player.getName();

        if (args.length == 0){
            // /skull
            PlayerUtil.give(player, ItemStackUtil.createHead(playerName, "§e" + playerName, 1));
            sendMessage(player, CfgLang.lang.get(Lang.SKULL_SUCCESS).replace("%name", playerName));
            return true;
        }

        if (args.length == 1){
            // /skull <name>
            PlayerUtil.give(player, ItemStackUtil.createHead(args[0], "§e" + args[0], 1));
            sendMessage(player, CfgLang.lang.get(Lang.SKULL_SUCCESS).replace("%name", args[0]));
            return true;
        }

        sendMessage(player, CfgLang.lang.get(Lang.SKULL_USAGE));
        return false;
    }
}
