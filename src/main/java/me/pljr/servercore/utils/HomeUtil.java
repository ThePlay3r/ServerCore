package me.pljr.servercore.utils;

import org.bukkit.entity.Player;

public final class HomeUtil {

    public static int getMax(Player player){
        int max = 0;
        for (int i = 1;i<=100;i++){
            if (player.hasPermission("servercore.sethome.max."+i)){
                max = i;
            }
        }
        return max;
    }
}
