package me.pljr.servercore.commands;

import me.pljr.pljrapi.utils.CommandUtil;
import me.pljr.pljrapi.utils.FormatUtil;
import me.pljr.servercore.config.CfgLang;
import me.pljr.servercore.enums.Lang;
import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

public class ReloreCommand extends CommandUtil implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)){
            sendMessage(sender, CfgLang.lang.get(Lang.NO_CONSOLE));
            return false;
        }
        Player player = (Player) sender;
        if (!checkPerm(player, "servercore.relore.use")) return false;

        if (args.length >= 2){
            // /relore <line> <text>
            if (!checkInt(player, args[0])) return false;
            ItemStack itemStack = player.getItemInHand();
            if (itemStack == null || itemStack.getType() == null || itemStack.getType() == Material.AIR){
                sendMessage(player, CfgLang.lang.get(Lang.RELORE_USAGE));
                return false;
            }
            int line = Integer.parseInt(args[0]);
            if (line < 1) line = 1;
            if (line > 64) line = 64;
            String text = FormatUtil.colorString(StringUtils.join(ArrayUtils.subarray(args, 1, args.length), " "));
            ItemMeta itemMeta = itemStack.getItemMeta();
            List<String> itemLore = itemMeta.getLore();
            if (itemLore == null) itemLore = new ArrayList<>();
            if (line >= itemLore.size()){
                int diff = line - itemLore.size();
                for (int i = 0; i < diff; i++){
                    itemLore.add("");
                }
            }
            itemLore.set(line-1, text);
            itemMeta.setLore(itemLore);
            itemStack.setItemMeta(itemMeta);
            player.setItemInHand(itemStack);
            player.updateInventory();
            sendMessage(player, CfgLang.lang.get(Lang.RELORE_SUCCESS).replace("%line", args[0]).replace("%text", text));
            return true;
        }

        sendMessage(player, CfgLang.lang.get(Lang.RELORE_USAGE));
        return false;
    }
}
