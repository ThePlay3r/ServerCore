package me.pljr.servercore.commands;

import me.pljr.pljrapispigot.utils.CommandUtil;
import me.pljr.servercore.config.CfgSettings;
import me.pljr.servercore.config.Lang;
import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;

public class SunCommand extends CommandUtil {

    public SunCommand(){
        super("sun", "servercore.sun.use");
    }

    @Override
    public void onPlayerCommand(Player player, String[] args){
        World world = player.getWorld();
        world.setStorm(false);
        world.setThundering(false);
        sendMessage(player, Lang.NIGHT_SUCCESS.get());
    }

    @Override
    public void onConsoleCommand(ConsoleCommandSender sender, String[] args){
        World world = Bukkit.getWorld(CfgSettings.DEFAULT_WORLD);
        world.setStorm(false);
        world.setThundering(false);
        sendMessage(sender, Lang.NIGHT_SUCCESS.get());
    }
}
