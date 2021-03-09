package me.pljr.servercore.commands.itemcommands;

import me.pljr.pljrapispigot.utils.CommandUtil;
import me.pljr.pljrapispigot.utils.FormatUtil;
import me.pljr.servercore.config.Lang;
import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

public class ReloreCommand extends CommandUtil {

    public ReloreCommand(){
        super("relore", "servercore.relore.use");
    }

    @Override
    public void onPlayerCommand(Player player, String[] args){
        if (args.length >= 2){
            // /relore <line> <text>
            if (!checkInt(player, args[0])) return;
            ItemStack itemStack = player.getItemInHand();
            if (itemStack == null || itemStack.getType() == null || itemStack.getType() == Material.AIR){
                sendMessage(player, Lang.RELORE_USAGE.get());
                return;
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
            sendMessage(player, Lang.RELORE_SUCCESS.get().replace("{line}", args[0]).replace("{text}", text));
            return;
        }

        sendMessage(player, Lang.RELORE_USAGE.get());
    }
}
