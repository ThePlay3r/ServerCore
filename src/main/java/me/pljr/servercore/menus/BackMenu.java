package me.pljr.servercore.menus;

import me.pljr.pljrapispigot.builders.GUIBuilder;
import me.pljr.pljrapispigot.managers.GUIManager;
import me.pljr.pljrapispigot.objects.GUI;
import me.pljr.pljrapispigot.objects.GUIItem;
import me.pljr.pljrapispigot.utils.PlayerUtil;
import me.pljr.servercore.ServerCore;
import me.pljr.servercore.config.CfgBackMenu;
import me.pljr.servercore.managers.PlayerManager;
import me.pljr.servercore.objects.CorePlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;

import java.util.UUID;

public class BackMenu {

    public static GUI get(Player player){
        GUIBuilder guiBuilder = new GUIBuilder(CfgBackMenu.TITLE, 3);
        for (int i = 0;i<27;i++){
            guiBuilder.setItem(i, CfgBackMenu.BACKGROUND);
        }

        UUID playerId = player.getUniqueId();

        PlayerManager playerManager = ServerCore.getPlayerManager();
        CorePlayer corePlayer = playerManager.getCorePlayer(playerId);

        if (player.hasPermission("servercore.back.use")){
            guiBuilder.setItem(12, new GUIItem(CfgBackMenu.LAST_LOCATION,
                    run -> {
                        player.closeInventory();
                        PlayerUtil.teleport(player, corePlayer.getLastLoc(), true);
                    }));
        }
        if (player.hasPermission("servercore.back.use.death")){
            guiBuilder.setItem(14, new GUIItem(CfgBackMenu.DEATH_LOCATION,
                    run -> {
                        player.closeInventory();
                        PlayerUtil.teleport(player, corePlayer.getDeathLoc(), true);
                    }));
        }
        return guiBuilder.create();
    }
}
