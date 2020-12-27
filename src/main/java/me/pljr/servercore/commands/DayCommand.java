package me.pljr.servercore.commands;

import me.pljr.pljrapispigot.utils.CommandUtil;
import me.pljr.servercore.config.CfgSettings;
import me.pljr.servercore.config.Lang;
import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;

public class DayCommand extends CommandUtil {

    public DayCommand(){
        super("day", "servercore.day.use");
    }

    @Override
    public void onPlayerCommand(Player player, String[] args){
        // /day
        World world = player.getWorld();;
        world.setTime(CfgSettings.DAY_TIME);
        sendMessage(player, Lang.DAY_SUCCESS.get());
    }

    @Override
    public void onConsoleCommand(ConsoleCommandSender sender, String[] args){
        // /day
        World world = Bukkit.getWorld(CfgSettings.DEFAULT_WORLD);
        world.setTime(CfgSettings.DAY_TIME);
        sendMessage(sender, Lang.DAY_SUCCESS.get());
    }
}
