package me.pljr.servercore.commands.teleportcommands;

import me.pljr.pljrapispigot.utils.CommandUtil;
import me.pljr.servercore.managers.PlayerManager;
import me.pljr.servercore.menus.BackMenu;
import org.bukkit.entity.Player;

public class BackCommand extends CommandUtil {

    private final PlayerManager playerManager;

    public BackCommand(PlayerManager playerManager){
        super("back", "servercore.back.use");
        this.playerManager = playerManager;
    }

    @Override
    public void onPlayerCommand(Player player, String[] args){
        // /back
        playerManager.getCorePlayer(player.getUniqueId(), corePlayer -> new BackMenu(player, corePlayer).getGui().open(player));
    }
}
