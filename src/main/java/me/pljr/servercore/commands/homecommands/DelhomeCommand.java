package me.pljr.servercore.commands.homecommands;

import me.pljr.pljrapispigot.utils.CommandUtil;
import me.pljr.servercore.ServerCore;
import me.pljr.servercore.config.Lang;
import me.pljr.servercore.managers.PlayerManager;
import org.bukkit.entity.Player;

public class DelhomeCommand extends CommandUtil {

    private final PlayerManager playerManager;

    public DelhomeCommand(PlayerManager playerManager){
        super("delhome", "servercore.delhome.use");
        this.playerManager = playerManager;
    }

    @Override
    public void onPlayerCommand(Player player, String[] args){
        if (args.length == 1){
            // /delhome <name>
            if (!playerManager.delHome(player, args[0])){
                sendMessage(player, Lang.DELHOME_FAILURE_NO_HOME.get().replace("{name}", args[0]));
                return;
            }
            sendMessage(player, Lang.DELHOME_SUCCESS.get().replace("{name}", args[0]));
            return;
        }

        sendMessage(player, Lang.DELHOME_USAGE.get());
    }
}
