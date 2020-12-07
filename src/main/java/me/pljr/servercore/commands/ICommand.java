package me.pljr.servercore.commands;

import me.pljr.pljrapi.XMaterial;
import me.pljr.pljrapi.utils.CommandUtil;
import me.pljr.pljrapi.utils.PlayerUtil;
import me.pljr.servercore.config.CfgLang;
import me.pljr.servercore.enums.Lang;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class ICommand extends CommandUtil implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)){
            sendMessage(sender, CfgLang.lang.get(Lang.NO_CONSOLE));
            return false;
        }
        Player player = (Player) sender;
        if (!checkPerm(player, "servercore.i.use")) return false;

        // /i <material>
        if (args.length == 1){
            if (!checkMaterial(player, args[0])) return false;
            ItemStack itemStack = XMaterial.valueOf(args[0].toUpperCase()).parseItem();
            PlayerUtil.give(player, itemStack);
            sendMessage(player, CfgLang.lang.get(Lang.I_SUCCESS).replace("%material", args[0]));
            return true;
        }

        sendMessage(player, CfgLang.lang.get(Lang.I_USAGE));
        return false;
    }
}
