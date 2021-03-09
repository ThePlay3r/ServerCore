package me.pljr.servercore.menus;

import lombok.Getter;
import me.pljr.pljrapispigot.builders.GUIBuilder;
import me.pljr.pljrapispigot.builders.ItemBuilder;
import me.pljr.pljrapispigot.objects.GUI;
import me.pljr.pljrapispigot.objects.GUIItem;
import me.pljr.servercore.ServerCore;
import me.pljr.servercore.config.Lang;
import me.pljr.servercore.config.MenuItem;
import me.pljr.servercore.managers.WarpManager;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.List;

@Getter
public class WarpMenu {

    private final GUI gui;

    public WarpMenu(Player player, WarpManager warpManager){
        GUIBuilder builder = new GUIBuilder(Lang.WARP_MENU_TITLE.get(), 6);

        for (int i = 0; i < 6*9; i++){
            builder.setItem(i, MenuItem.WARP_BACKGROUND.get());
        }

        int warpSlot = 0;
        List<String> warps = warpManager.getWarpNames();

        for (String warp : warps){
            if (!player.hasPermission("servercore.warp.use." + warp)) continue;

            ItemBuilder item = new ItemBuilder(MenuItem.WARP_ITEM.get());
            String itemName = item.getName().replace("{warp}", warp);
            item.withName(itemName);
            item.replaceLore("{warp}", warp);

            builder.setItem(warpSlot, new GUIItem(item.create(),
                    run -> {
                        player.closeInventory();
                        Bukkit.dispatchCommand(player, "warp " + warp);
                    }));

            warpSlot++;
        }

        gui = builder.create();
    }
}
