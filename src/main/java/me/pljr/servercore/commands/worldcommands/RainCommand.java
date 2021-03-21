package me.pljr.servercore.commands.worldcommands;

import me.pljr.pljrapispigot.commands.BukkitCommand;
import me.pljr.servercore.config.Lang;
import me.pljr.servercore.config.Settings;
import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;

public class RainCommand extends BukkitCommand {

    private final Settings settings;

    public RainCommand(Settings settings){
        super("rain", "servercore.rain.use");
        this.settings = settings;
    }

    @Override
    public void onPlayerCommand(Player player, String[] args){
        World world = player.getWorld();
        world.setStorm(true);
        sendMessage(player, Lang.RAIN_SUCCESS.get());
    }

    @Override
    public void onConsoleCommand(ConsoleCommandSender sender, String[] args){
        World world = Bukkit.getWorld(settings.getDefaultWorld());
        world.setStorm(true);
        sendMessage(sender, Lang.RAIN_SUCCESS.get());
    }
}
