package me.pljr.servercore.commands.homecommands;

import me.pljr.pljrapispigot.commands.BukkitCommand;
import me.pljr.servercore.config.Lang;
import me.pljr.servercore.managers.PlayerManager;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

public class ADelhomeCommand extends BukkitCommand {

    private final PlayerManager playerManager;

    public ADelhomeCommand(PlayerManager playerManager){
        super("delhome", "servercore.adelhome.use");
        this.playerManager = playerManager;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (args.length == 2){
            // /delhome <player> <home>
            OfflinePlayer target = Bukkit.getOfflinePlayer(args[0]);
            if (!playerManager.delHome(target, args[1])){
                sendMessage(sender, Lang.ADELHOME_FAILURE_NO_HOME.get().replace("{player}", args[0]).replace("{name}", args[1]));
                return true;
            }
            sendMessage(sender, Lang.ADELHOME_SUCCESS.get().replace("{player}", args[0]).replace("{name}", args[1]));
            sendMessage(target, Lang.ADELHOME_SUCCESS_PLAYER.get().replace("{player}", sender.getName()).replace("{name}", args[1]));
            return true;
        }

        sendMessage(sender, Lang.ADELHOME_USAGE.get());
        return false;
    }
}
