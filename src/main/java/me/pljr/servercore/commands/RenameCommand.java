package me.pljr.servercore.commands;

import me.pljr.pljrapi.utils.CommandUtil;
import me.pljr.pljrapi.utils.FormatUtil;
import me.pljr.servercore.config.CfgLang;
import me.pljr.servercore.enums.Lang;
import org.apache.commons.lang.StringUtils;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class RenameCommand extends CommandUtil implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)){
            sendMessage(sender, CfgLang.lang.get(Lang.NO_CONSOLE));
            return false;
        }
        Player player = (Player) sender;
        if (!checkPerm(player, "servercore.rename.use")) return false;

        if (args.length > 0){
            // /rename <name>
            ItemStack itemStack = player.getItemInHand();
            if (itemStack == null){
                sendMessage(player, CfgLang.lang.get(Lang.RENAME_USAGE));
                return false;
            }
            String name = FormatUtil.colorString(StringUtils.join(args, " "));
            ItemMeta itemMeta = itemStack.getItemMeta();
            itemMeta.setDisplayName(name);
            itemStack.setItemMeta(itemMeta);
            player.setItemInHand(itemStack);
            player.updateInventory();
            sendMessage(player, CfgLang.lang.get(Lang.RENAME_SUCCESS).replace("%name", name));
            return true;
        }

        sendMessage(player, CfgLang.lang.get(Lang.RENAME_USAGE));
        return false;
    }
}
