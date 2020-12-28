package me.pljr.servercore.commands;

import me.pljr.pljrapispigot.utils.CommandUtil;
import me.pljr.servercore.ServerCore;
import me.pljr.servercore.config.Lang;
import org.bukkit.entity.Player;

public class DelhomeCommand extends CommandUtil {

    public DelhomeCommand(){
        super("delhome", "servercore.delhome.use");
    }

    @Override
    public void onPlayerCommand(Player player, String[] args){
        if (args.length == 1){
            // /delhome <name>
            if (!ServerCore.getPlayerManager().delHome(player, args[0])){
                sendMessage(player, Lang.DELHOME_FAILURE_NO_HOME.get().replace("{name}", args[0]));
                return;
            }
            sendMessage(player, Lang.DELHOME_SUCCESS.get().replace("{name}", args[0]));
            return;
        }

        sendMessage(player, Lang.DELHOME_USAGE.get());
    }
}
