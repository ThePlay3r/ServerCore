package me.pljr.servercore.commands.warpcommands;

import me.pljr.pljrapispigot.commands.BukkitCommand;
import me.pljr.servercore.config.Lang;
import me.pljr.servercore.config.Settings;
import me.pljr.servercore.managers.WarpManager;
import me.pljr.servercore.menus.WarpMenu;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;

import java.util.List;

public class WarpsCommand extends BukkitCommand {

    private final Settings settings;
    private final WarpManager warpManager;

    public WarpsCommand(Settings settings, WarpManager warpManager){
        super("warps", "servercore.warps.use");
        this.settings = settings;
        this.warpManager = warpManager;
    }

    @Override
    public void onPlayerCommand(Player player, String[] args){
        List<String> warpNames = warpManager.getWarpNames();

        if (settings.isWarpGui()){
            // /warps (Gui)
            new WarpMenu(player, warpManager).getGui().open(player);
        }else{
            // /warps (Chat)
            sendMessage(player, Lang.WARPS_SUCCESS_TITLE.get());
            if (player.hasPermission("servercore.delwarp.use")){
                for (String warpName : warpNames){
                    if (player.hasPermission("servercore.warp.use." + warpName)){
                        sendMessage(player, Lang.WARPS_SUCCESS_FORMAT_REMOVE.get().replace("{name}", warpName));
                    }
                }
            }else{
                for (String warpName : warpNames){
                    if (player.hasPermission("servercore.warp.use." + warpName)){
                        sendMessage(player, Lang.WARPS_SUCCESS_FORMAT.get().replace("{name}", warpName));
                    }
                }
            }
        }
    }

    @Override
    public void onConsoleCommand(ConsoleCommandSender sender, String[] args){
        List<String> warpNames = warpManager.getWarpNames();

        // /warps (Chat)
        sendMessage(sender, Lang.WARPS_SUCCESS_TITLE.get());
        for (String warpName : warpNames){
            sendMessage(sender, Lang.WARPS_SUCCESS_FORMAT_REMOVE.get().replace("{name}", warpName));
        }
    }
}
