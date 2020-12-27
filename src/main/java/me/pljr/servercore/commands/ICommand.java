package me.pljr.servercore.commands;

import me.pljr.pljrapispigot.xseries.XMaterial;
import me.pljr.pljrapispigot.utils.CommandUtil;
import me.pljr.pljrapispigot.utils.PlayerUtil;
import me.pljr.servercore.config.Lang;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class ICommand extends CommandUtil implements CommandExecutor {

    public ICommand(){
        super("i", "servercore.i.use");
    }

    @Override
    public void onPlayerCommand(Player player, String[] args){
        // /i <material>
        if (args.length == 1){
            if (!checkMaterial(player, args[0])) return;
            ItemStack itemStack = XMaterial.valueOf(args[0].toUpperCase()).parseItem();
            PlayerUtil.give(player, itemStack);
            sendMessage(player, Lang.I_SUCCESS.get().replace("%material", args[0]));
            return;
        }

        sendMessage(player, Lang.I_USAGE.get());
    }
}
