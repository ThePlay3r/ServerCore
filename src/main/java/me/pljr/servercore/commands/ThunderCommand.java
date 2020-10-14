package me.pljr.servercore.commands;

import me.pljr.pljrapi.utils.CommandUtil;
import me.pljr.servercore.config.CfgLang;
import me.pljr.servercore.config.CfgSettings;
import me.pljr.servercore.enums.Lang;
import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class ThunderCommand extends CommandUtil implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!checkPerm(sender, "servercore.thunder.use")) return false;

        // /thunder
        World world;
        if (sender instanceof Player){
            Player player = (Player) sender;
            world = player.getWorld();
        }else{
            world = Bukkit.getWorld(CfgSettings.defaultWorld);
        }
        world.setStorm(true);
        world.setThundering(true);
        sendMessage(sender, CfgLang.lang.get(Lang.THUNDER_SUCCESS));

        return true;
    }
}
