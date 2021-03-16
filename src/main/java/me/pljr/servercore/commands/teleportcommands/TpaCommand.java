package me.pljr.servercore.commands.teleportcommands;

import me.pljr.pljrapispigot.commands.BukkitCommand;
import me.pljr.servercore.config.Lang;
import me.pljr.servercore.managers.PlayerManager;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.UUID;

public class TpaCommand extends BukkitCommand {

    private final PlayerManager playerManager;

    public TpaCommand(PlayerManager playerManager){
        super("tpa", "servercore.tpa.use");
        this.playerManager = playerManager;
    }

    @Override
    public void onPlayerCommand(Player player, String[] args){
        String playerName = player.getName();

        if (args.length == 1){
            // /tpa <player>
            if (!checkPlayer(player, args[0])) return;
            Player target = Bukkit.getPlayer(args[0]);
            UUID targetId = target.getUniqueId();
            playerManager.getPlayer(targetId, coreTarget -> {
                if (coreTarget.getTpaBlocked().contains(playerName)){
                    sendMessage(player, Lang.TPA_FAILURE_BLOCKED.get().replace("{player}", args[0]));
                    return;
                }
                coreTarget.setTpaRequester(player);
                sendMessage(player, Lang.TPA_SUCCESS.get().replace("{player}", args[0]));
                sendMessage(target, Lang.TPA_SUCCESS_PLAYER.get().replace("{player}", playerName));
            });
            return;
        }

        sendMessage(player, Lang.TPA_USAGE.get());
    }
}
