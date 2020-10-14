package me.pljr.servercore.commands;

import me.pljr.pljrapi.utils.CommandUtil;
import me.pljr.servercore.config.CfgLang;
import me.pljr.servercore.enums.Lang;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class TopBlockCommand extends CommandUtil implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)){
            sendMessage(sender, CfgLang.lang.get(Lang.NO_CONSOLE));
            return false;
        }
        Player player = (Player) sender;
        if (!checkPerm(player, "servercore.topblock.use")) return false;

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
        sendMessage(player, CfgLang.lang.get(Lang.TOPBLOCK_SUCCESS));

        return true;
    }
}
