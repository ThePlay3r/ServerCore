package me.pljr.servercore.commands.itemcommands;

import me.pljr.pljrapispigot.commands.BukkitCommand;
import me.pljr.pljrapispigot.utils.FormatUtil;
import me.pljr.servercore.config.Lang;
import org.apache.commons.lang.StringUtils;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class RenameCommand extends BukkitCommand {

    public RenameCommand(){
        super("rename", "servercore.rename.use");
    }

    @Override
    public void onPlayerCommand(Player player, String[] args){
        if (args.length > 0){
            // /rename <name>
            ItemStack itemStack = player.getItemInHand();
            if (itemStack == null){
                sendMessage(player, Lang.RENAME_USAGE.get());
                return;
            }
            String name = FormatUtil.colorString(StringUtils.join(args, " "));
            ItemMeta itemMeta = itemStack.getItemMeta();
            itemMeta.setDisplayName(name);
            itemStack.setItemMeta(itemMeta);
            player.setItemInHand(itemStack);
            player.updateInventory();
            sendMessage(player, Lang.RENAME_SUCCESS.get().replace("{name}", name));
            return;
        }

        sendMessage(player, Lang.RENAME_USAGE.get());
    }
}
