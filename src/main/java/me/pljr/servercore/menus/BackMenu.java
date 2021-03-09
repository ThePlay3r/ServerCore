package me.pljr.servercore.menus;

import lombok.Getter;
import me.pljr.pljrapispigot.builders.GUIBuilder;
import me.pljr.pljrapispigot.objects.GUI;
import me.pljr.pljrapispigot.objects.GUIItem;
import me.pljr.pljrapispigot.utils.PlayerUtil;
import me.pljr.servercore.config.Lang;
import me.pljr.servercore.config.MenuItem;
import me.pljr.servercore.objects.CorePlayer;
import org.bukkit.entity.Player;

@Getter
public class BackMenu {

    private final GUI gui;

    public BackMenu(Player player, CorePlayer corePlayer){
        GUIBuilder guiBuilder = new GUIBuilder(Lang.BACK_MENU_TITLE.get(), 3);
        for (int i = 0;i<27;i++){
            guiBuilder.setItem(i, MenuItem.BACK_BACKGROUND.get());
        }

        if (player.hasPermission("servercore.back.use")){
            guiBuilder.setItem(12, new GUIItem(MenuItem.BACK_LAST_LOCATION.get(),
                    run -> {
                        player.closeInventory();
                        PlayerUtil.teleport(player, corePlayer.getLastLoc());
                    }));
        }
        if (player.hasPermission("servercore.back.use.death")){
            guiBuilder.setItem(14, new GUIItem(MenuItem.BACK_DEATH_LOCATION.get(),
                    run -> {
                        player.closeInventory();
                        PlayerUtil.teleport(player, corePlayer.getDeathLoc());
                    }));
        }
        gui = guiBuilder.create();
    }
}
