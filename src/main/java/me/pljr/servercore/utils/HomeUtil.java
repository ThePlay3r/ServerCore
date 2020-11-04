package me.pljr.servercore.utils;

import org.bukkit.entity.Player;

public class HomeUtil {

    public static int getMax(Player player){
        for (int i = 1;i<=100;i++){
            if (player.hasPermission("servercore.sethome.max."+i)){
                return i;
            }
        }
        return 0;
    }
}
