package me.pljr.servercore.commands.worldcommands;

import me.pljr.pljrapispigot.commands.BukkitCommand;
import me.pljr.servercore.config.Lang;
import me.pljr.servercore.config.Settings;
import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;

public class DayCommand extends BukkitCommand {

    private final Settings settings;
    public DayCommand(Settings settings){
        super("day", "servercore.day.use");
        this.settings = settings;
    }

    @Override
    public void onPlayerCommand(Player player, String[] args){
        // /day
        World world = player.getWorld();;
        world.setTime(settings.getDayTime());
        sendMessage(player, Lang.DAY_SUCCESS.get());
    }

    @Override
    public void onConsoleCommand(ConsoleCommandSender sender, String[] args){
        // /day
        World world = Bukkit.getWorld(settings.getDefaultWorld());
        world.setTime(settings.getDayTime());
        sendMessage(sender, Lang.DAY_SUCCESS.get());
    }
}
