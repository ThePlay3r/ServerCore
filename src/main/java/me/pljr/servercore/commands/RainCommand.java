package me.pljr.servercore.commands;

import me.pljr.pljrapispigot.utils.CommandUtil;
import me.pljr.servercore.config.CfgSettings;
import me.pljr.servercore.config.Lang;
import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;

public class RainCommand extends CommandUtil {

    public RainCommand(){
        super("rain", "servercore.rain.use");
    }

    @Override
    public void onPlayerCommand(Player player, String[] args){
        World world = player.getWorld();
        world.setStorm(true);
        sendMessage(player, Lang.NIGHT_SUCCESS.get());
    }

    @Override
    public void onConsoleCommand(ConsoleCommandSender sender, String[] args){
        World world = Bukkit.getWorld(CfgSettings.DEFAULT_WORLD);
        world.setStorm(true);
        sendMessage(sender, Lang.NIGHT_SUCCESS.get());
    }
}
