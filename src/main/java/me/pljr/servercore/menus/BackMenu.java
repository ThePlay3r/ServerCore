package me.pljr.servercore.menus;

import me.pljr.pljrapi.builders.GUIBuilder;
import me.pljr.pljrapi.managers.GUIManager;
import me.pljr.pljrapi.objects.GUI;
import me.pljr.pljrapi.objects.GUIItem;
import me.pljr.pljrapi.utils.PlayerUtil;
import me.pljr.servercore.ServerCore;
import me.pljr.servercore.config.CfgBackMenu;
import me.pljr.servercore.managers.PlayerManager;
import me.pljr.servercore.objects.CorePlayer;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;

import java.util.UUID;

public class BackMenu {

    public static GUI get(Player player){
        GUIBuilder guiBuilder = new GUIBuilder(CfgBackMenu.title, 3);
        for (int i = 0;i<27;i++){
            guiBuilder.setItem(i, CfgBackMenu.background);
        }

        UUID playerId = player.getUniqueId();

        PlayerManager playerManager = ServerCore.getPlayerManager();
        CorePlayer corePlayer = playerManager.getCorePlayer(playerId);

        if (player.hasPermission("servercore.back.use")){
            guiBuilder.setItem(12, new GUIItem(CfgBackMenu.lastLocation,
                    new GUIManager.ClickRunnable() {
                        @Override
                        public void run(InventoryClickEvent inventoryClickEvent) {
                            player.closeInventory();
                            PlayerUtil.teleport(player, corePlayer.getLastLoc(), true);
                        }
                    }));
        }
        if (player.hasPermission("servercore.back.use.death")){
            guiBuilder.setItem(14, new GUIItem(CfgBackMenu.deathLocation,
                    new GUIManager.ClickRunnable() {
                        @Override
                        public void run(InventoryClickEvent inventoryClickEvent) {
                            player.closeInventory();
                            PlayerUtil.teleport(player, corePlayer.getDeathLoc(), true);
                        }
                    }));
        }
        return guiBuilder.create();
    }
}
