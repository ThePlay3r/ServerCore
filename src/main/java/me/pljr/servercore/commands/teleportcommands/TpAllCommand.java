package me.pljr.servercore.commands.teleportcommands;

import me.pljr.pljrapispigot.commands.BukkitCommand;
import me.pljr.servercore.config.Lang;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class TpAllCommand extends BukkitCommand {

    public TpAllCommand(){
        super("tpall", "servercore.tpall.use");
    }

    @Override
    public void onPlayerCommand(Player player, String[] args) {
        sendMessage(player, Lang.TPALL_SUCCESS.get());
        for (Player p : Bukkit.getOnlinePlayers()){
            p.teleport(player);
        }
    }
}
