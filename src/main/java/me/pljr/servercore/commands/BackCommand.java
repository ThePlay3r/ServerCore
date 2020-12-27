package me.pljr.servercore.commands;

import me.pljr.pljrapispigot.utils.CommandUtil;
import me.pljr.servercore.menus.BackMenu;
import org.bukkit.entity.Player;

public class BackCommand extends CommandUtil {

    public BackCommand(){
        super("back", "servercore.back.use");
    }

    @Override
    public void onPlayerCommand(Player player, String[] args){
        // /back
        BackMenu.get(player).open(player);
    }
}
