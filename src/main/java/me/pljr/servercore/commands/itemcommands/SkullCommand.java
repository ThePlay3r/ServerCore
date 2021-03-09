package me.pljr.servercore.commands.itemcommands;

import me.pljr.pljrapispigot.builders.ItemBuilder;
import me.pljr.pljrapispigot.utils.CommandUtil;
import me.pljr.pljrapispigot.utils.PlayerUtil;
import me.pljr.pljrapispigot.xseries.XMaterial;
import me.pljr.servercore.config.Lang;
import org.bukkit.entity.Player;

public class SkullCommand extends CommandUtil {

    public SkullCommand(){
        super("skull", "servercore.skull.use");
    }

    @Override
    public void onPlayerCommand(Player player, String[] args){
        String playerName = player.getName();

        if (args.length == 0){
            // /skull
            PlayerUtil.give(player,
                    new ItemBuilder(XMaterial.PLAYER_HEAD)
                            .withName(playerName)
                            .withOwner(playerName)
                            .create());
            sendMessage(player, Lang.SKULL_SUCCESS.get().replace("{name}", playerName));
            return;
        }

        if (args.length == 1){
            // /skull <name>
            PlayerUtil.give(player,
                    new ItemBuilder(XMaterial.PLAYER_HEAD)
                            .withName(args[0])
                            .withOwner(args[0])
                            .create());
            sendMessage(player, Lang.SKULL_SUCCESS.get().replace("{name}", args[0]));
            return;
        }

        sendMessage(player, Lang.SKULL_USAGE.get());
    }
}
