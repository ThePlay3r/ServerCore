package me.pljr.servercore.commands;

import me.pljr.pljrapi.utils.CommandUtil;
import me.pljr.servercore.ServerCore;
import me.pljr.servercore.config.CfgLang;
import me.pljr.servercore.enums.Lang;
import me.pljr.servercore.objects.CorePlayer;
import me.pljr.servercore.utils.HomeUtil;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.UUID;

public class SethomeCommnad extends CommandUtil implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)){
            sendMessage(sender, CfgLang.lang.get(Lang.NO_CONSOLE));
            return false;
        }
        Player player = (Player) sender;
        if (!checkPerm(player, "servercore.sethome.use")) return false;

        Location playerLoc = player.getLocation();
        UUID playerId = player.getUniqueId();

        CorePlayer corePlayer = ServerCore.getPlayerManager().getCorePlayer(playerId);
        int currentHomes = corePlayer.getHomes().size();
        int maxHomes = HomeUtil.getMax(player);
        boolean canMore = maxHomes > currentHomes;

        if (args.length == 1){
            // /sethome <home>
            if (corePlayer.getHomes().containsKey(args[0])){
                ServerCore.getPlayerManager().setHome(player, args[0], playerLoc);
                sendMessage(player, CfgLang.lang.get(Lang.SETHOME_SUCCESS).replace("%name", args[0]));
                return true;
            }
            if (!canMore){
                sendMessage(player, CfgLang.lang.get(Lang.SETHOME_FAILURE_MAX_HOMES));
                return false;
            }
            ServerCore.getPlayerManager().setHome(player, args[0], playerLoc);
            sendMessage(player, CfgLang.lang.get(Lang.SETHOME_SUCCESS).replace("%name", args[0]));
            return true;
        }

        sendMessage(player, CfgLang.lang.get(Lang.SETHOME_USAGE));
        return false;
    }
}
