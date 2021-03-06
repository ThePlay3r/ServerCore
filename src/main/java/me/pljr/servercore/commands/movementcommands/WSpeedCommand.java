package me.pljr.servercore.commands.movementcommands;

import me.pljr.pljrapispigot.commands.BukkitCommand;
import me.pljr.servercore.config.Lang;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;

public class WSpeedCommand extends BukkitCommand {

    public WSpeedCommand(){
        super("wspeed", "servercore.wspeed.use");
    }

    private void executeWSpeedOnPlayer(CommandSender sender, String[] args){
        // /wspeed <player> <speed>
        if (!checkPlayer(sender, args[0])) return;
        if (!checkInt(sender, args[1])) return;
        Player target = Bukkit.getPlayer(args[0]);
        float speed = Integer.parseInt(args[1]);
        if (speed > 10){
            speed = 10;
        }else if (speed < 0){
            speed = 0;
        }
        target.setWalkSpeed(speed/10);
        sendMessage(sender, Lang.WSPEED_SUCCESS_OTHERS.get().replace("{player}", target.getName()).replace("{speed}", args[1]));
        sendMessage(target, Lang.WSPEED_SUCCESS_OTHERS_PLAYER.get().replace("{player}", sender.getName()).replace("{speed}", args[1]));
    }

    @Override
    public void onPlayerCommand(Player player, String[] args){
        if (args.length == 1){
            // /wspeed <speed>
            if (!checkInt(player, args[0])) return;
            float speed = Integer.parseInt(args[0]);
            if (speed > 10){
                speed = 10;
            }else if (speed < 0){
                speed = 0;
            }
            player.setWalkSpeed(speed/10);
            sendMessage(player, Lang.WSPEED_SUCCESS.get().replace("{speed}", args[0]));
            return;
        }

        if (args.length == 2){
            // /wspeed <player> <speed>
            if (!checkPerm(player, "servercore.wspeed.use.others")) return;
            executeWSpeedOnPlayer(player, args);;
            return;
        }

        sendMessage(player, Lang.WSPEED_USAGE.get());
    }

    @Override
    public void onConsoleCommand(ConsoleCommandSender sender, String[] args){
        if (args.length == 2){
            // /wspeed <player> <speed>
            executeWSpeedOnPlayer(sender, args);
            return;
        }

        sendMessage(sender, Lang.WSPEED_USAGE.get());
    }
}
