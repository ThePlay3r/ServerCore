package me.pljr.servercore.commands;

import me.pljr.pljrapispigot.utils.CommandUtil;
import me.pljr.servercore.config.CfgSettings;
import me.pljr.servercore.config.Lang;
import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;

public class NightCommand extends CommandUtil {

    public NightCommand(){
        super("night", "servercore.night.use");
    }

    @Override
    public void onPlayerCommand(Player player, String[] args){
        World world = player.getWorld();
        world.setTime(CfgSettings.NIGHT_TIME);
        sendMessage(player, Lang.NIGHT_SUCCESS.get());
    }

    @Override
    public void onConsoleCommand(ConsoleCommandSender sender, String[] args){
        World world = Bukkit.getWorld(CfgSettings.DEFAULT_WORLD);
        world.setTime(CfgSettings.NIGHT_TIME);
        sendMessage(sender, Lang.NIGHT_SUCCESS.get());
    }
}
