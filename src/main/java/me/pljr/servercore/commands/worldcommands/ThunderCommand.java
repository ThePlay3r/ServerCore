package me.pljr.servercore.commands.worldcommands;

import me.pljr.pljrapispigot.commands.BukkitCommand;
import me.pljr.servercore.config.Lang;
import me.pljr.servercore.config.Settings;
import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;

public class ThunderCommand extends BukkitCommand {

    private final Settings settings;

    public ThunderCommand(Settings settings){
        super("thunder", "servercore.thunder.use");
        this.settings = settings;
    }

    @Override
    public void onPlayerCommand(Player player, String[] args){
        World world = player.getWorld();
        world.setStorm(true);
        world.setThundering(true);
        sendMessage(player, Lang.THUNDER_SUCCESS.get());
    }

    @Override
    public void onConsoleCommand(ConsoleCommandSender sender, String[] args){
        World world = Bukkit.getWorld(settings.getDefaultWorld());
        world.setStorm(true);
        world.setThundering(true);
        sendMessage(sender, Lang.THUNDER_SUCCESS.get());
    }
}
