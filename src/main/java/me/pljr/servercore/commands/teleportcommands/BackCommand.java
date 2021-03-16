package me.pljr.servercore.commands.teleportcommands;

import me.pljr.pljrapispigot.commands.BukkitCommand;
import me.pljr.servercore.managers.PlayerManager;
import me.pljr.servercore.menus.BackMenu;
import org.bukkit.entity.Player;

public class BackCommand extends BukkitCommand {

    private final PlayerManager playerManager;

    public BackCommand(PlayerManager playerManager){
        super("back", "servercore.back.use");
        this.playerManager = playerManager;
    }

    @Override
    public void onPlayerCommand(Player player, String[] args){
        // /back
        playerManager.getPlayer(player.getUniqueId(), corePlayer -> new BackMenu(player, corePlayer).getGui().open(player));
    }
}
