package me.pljr.servercore.commands.movementcommands;

import me.pljr.pljrapispigot.utils.CommandUtil;
import me.pljr.servercore.config.Lang;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;

public class FSpeedCommand extends CommandUtil implements CommandExecutor {

    public FSpeedCommand(){
        super("fspeed", "servercore.fspeed.use");
    }

    private void executeFSpeedOnPlayer(CommandSender sender, String[] args){
        if (!checkPlayer(sender, args[0])) return;
        if (!checkInt(sender, args[1])) return;
        Player target = Bukkit.getPlayer(args[0]);
        float speed = Integer.parseInt(args[1]);
        if (speed > 10){
            speed = 10;
        }else if (speed < 0){
            speed = 0;
        }
        target.setFlySpeed(speed/10);
        sendMessage(sender, Lang.FSPEED_SUCCESS_OTHERS.get().replace("{player}", target.getName()).replace("{speed}", args[1]));
        sendMessage(target, Lang.FSPEED_SUCCESS_OTHERS_PLAYER.get().replace("{player}", "Console").replace("{speed}", args[1]));
    }

    @Override
    public void onPlayerCommand(Player player, String[] args){
        if (args.length == 1){
            // /fspeed <speed>
            if (!checkInt(player, args[0])) return;
            float speed = Integer.parseInt(args[0]);
            if (speed > 10){
                speed = 10;
            }else if (speed < 0){
                speed = 0;
            }
            player.setFlySpeed(speed/10);
            sendMessage(player, Lang.FSPEED_SUCCESS.get().replace("{speed}", args[0]));
            return;
        }

        if (args.length == 2){
            // /fspeed <player> <speed>
            if (!checkPerm(player, "servercore.fspeed.use.others")) return;
            executeFSpeedOnPlayer(player, args);
            return;
        }

        sendMessage(player, Lang.FSPEED_USAGE.get());
    }

    @Override
    public void onConsoleCommand(ConsoleCommandSender sender, String[] args){
        if (args.length == 2){
            // /fspeed <player> <speed>
            executeFSpeedOnPlayer(sender, args);
            return;
        }

        sendMessage(sender, Lang.FSPEED_USAGE.get());
    }
}
