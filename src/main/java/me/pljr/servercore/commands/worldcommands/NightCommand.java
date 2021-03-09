package me.pljr.servercore.commands.worldcommands;

import me.pljr.pljrapispigot.utils.CommandUtil;
import me.pljr.servercore.config.Settings;
import me.pljr.servercore.config.Lang;
import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;

public class NightCommand extends CommandUtil {

    private final Settings settings;

    public NightCommand(Settings settings){
        super("night", "servercore.night.use");
        this.settings = settings;
    }

    @Override
    public void onPlayerCommand(Player player, String[] args){
        World world = player.getWorld();
        world.setTime(settings.getNightTime());
        sendMessage(player, Lang.NIGHT_SUCCESS.get());
    }

    @Override
    public void onConsoleCommand(ConsoleCommandSender sender, String[] args){
        World world = Bukkit.getWorld(settings.getDefaultWorld());
        world.setTime(settings.getNightTime());
        sendMessage(sender, Lang.NIGHT_SUCCESS.get());
    }
}
