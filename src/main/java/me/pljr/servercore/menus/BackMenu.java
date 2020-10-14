package me.pljr.servercore.menus;

import me.pljr.pljrapi.utils.PlayerUtil;
import me.pljr.servercore.ServerCore;
import me.pljr.servercore.config.CfgBackMenu;
import me.pljr.servercore.objects.CorePlayer;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;

public class BackMenu implements Listener {
    public static void open(Player player){
        Inventory inventory = Bukkit.createInventory(player, 3*9, CfgBackMenu.title);
        for (int i = 0;i<27;i++){
            inventory.setItem(i, CfgBackMenu.background);
        }
        if (player.hasPermission("servercore.back.use")){
            inventory.setItem(12, CfgBackMenu.lastLocation);
        }
        if (player.hasPermission("servercore.back.use.death")){
            inventory.setItem(14, CfgBackMenu.deathLocation);
        }
        player.openInventory(inventory);
    }

    @EventHandler
    public static void onClick(InventoryClickEvent event){
        if (event.getWhoClicked() instanceof Player && event.getView().getTitle().equals(CfgBackMenu.title)){
            Player player = (Player) event.getWhoClicked();
            CorePlayer corePlayer = ServerCore.getPlayerManager().getCorePlayer(player.getUniqueId());
            int slot = event.getSlot();
            switch (slot){
                case 12:
                    if (player.hasPermission("servercore.back.use")){
                        player.closeInventory();
                        PlayerUtil.teleport(player, corePlayer.getLastLoc(), true);
                    }
                    break;
                case 14:
                    if (player.hasPermission("servercore.back.use.death")){
                        player.closeInventory();
                        PlayerUtil.teleport(player, corePlayer.getDeathLoc(), true);
                    }
                    break;
            }
        }
    }
}
