package me.pljr.servercore.commands.homecommands;

import me.pljr.pljrapispigot.commands.BukkitCommand;
import me.pljr.servercore.config.Lang;
import me.pljr.servercore.managers.PlayerManager;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;

public class ASethomeCommand extends BukkitCommand {

    private final PlayerManager playerManager;

    public ASethomeCommand(PlayerManager playerManager){
        super("asethome", "servercore.asethome.use");
        this.playerManager = playerManager;
    }

    @Override
    public void onPlayerCommand(Player player, String[] args){
        if (args.length == 2){
            // /asethome <player> <home>
            OfflinePlayer target = Bukkit.getOfflinePlayer(args[0]);
            playerManager.setHome(target, args[1], player.getLocation());
            sendMessage(player, Lang.ASETHOME_SUCCESS.get().replace("{name}", args[1]).replace("{player}", args[0]));
            sendMessage(target, Lang.ASETHOME_SUCCESS_PLAYER.get().replace("{name}", args[1]).replace("{player}", player.getName()));
            return;
        }

        sendMessage(player, Lang.ASETHOME_USAGE.get());
    }
}
