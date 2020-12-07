package me.pljr.servercore.commands;

import me.pljr.pljrapi.utils.CommandUtil;
import me.pljr.pljrapi.utils.PlayerUtil;
import me.pljr.servercore.ServerCore;
import me.pljr.servercore.config.CfgLang;
import me.pljr.servercore.enums.Lang;
import me.pljr.servercore.objects.CorePlayer;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.UUID;

public class HomeCommand extends CommandUtil implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)){
            sendMessage(sender, CfgLang.lang.get(Lang.NO_CONSOLE));
            return false;
        }
        Player player = (Player) sender;
        if (!checkPerm(player, "servercore.home.use")) return false;

        UUID playerId = player.getUniqueId();

        CorePlayer corePlayer = ServerCore.getPlayerManager().getCorePlayer(playerId);

        if (args.length == 1){
            // /home <name>
            Location homeLoc = corePlayer.getHome(args[0]);
            if (homeLoc == null){
                sendMessage(player, CfgLang.lang.get(Lang.HOME_FAILURE_NO_HOME).replace("%name", args[0]));
                return false;
            }
            PlayerUtil.teleport(player, homeLoc, true);
            sendMessage(player, CfgLang.lang.get(Lang.HOME_SUCCESS).replace("%name", args[0]));
            return true;
        }

        sendMessage(player, CfgLang.lang.get(Lang.HOME_USAGE));
        return false;
    }
}
