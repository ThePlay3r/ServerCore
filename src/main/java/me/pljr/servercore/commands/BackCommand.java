package me.pljr.servercore.commands;

import me.pljr.pljrapi.utils.CommandUtil;
import me.pljr.servercore.config.CfgLang;
import me.pljr.servercore.enums.Lang;
import me.pljr.servercore.menus.BackMenu;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class BackCommand extends CommandUtil implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)){
            sendMessage(sender, CfgLang.lang.get(Lang.NO_CONSOLE));
            return false;
        }
        Player player = (Player) sender;
        if (!checkPerm(player, "servercore.back.use")) return false;

        // /back
        BackMenu.get(player).open(player);

        return true;
    }
}
