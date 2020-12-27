package me.pljr.servercore.commands;

import me.pljr.pljrapispigot.utils.CommandUtil;
import me.pljr.servercore.config.Lang;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class TopBlockCommand extends CommandUtil {

    public TopBlockCommand(){
        super("topblock", "Servercore.topblock.use");
    }

    @Override
    public void onPlayerCommand(Player player, String[] args){
        Location playerLoc = player.getLocation();
        double x = playerLoc.getX();
        double y = playerLoc.getY();
        double z = playerLoc.getZ();
        World playerWorld = playerLoc.getWorld();

        // /topblock
        for (int i = 255; i != y; i--){
            Location loc = new Location(playerWorld, x, i, z);
            Block block = playerWorld.getBlockAt(loc);
            if (block == null || block.getType() == Material.AIR) continue;
            player.teleport(loc.add(0, 1, 0));
            break;
        }
        sendMessage(player, Lang.TOPBLOCK_SUCCESS.get());
    }
}
