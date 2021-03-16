package me.pljr.servercore.commands.movementcommands;

import me.pljr.pljrapispigot.commands.BukkitCommand;
import me.pljr.servercore.config.Lang;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;

public class FlyCommand extends BukkitCommand  {

    public FlyCommand(){
        super("fly", "servercore.fly.use");
    }

    @Override
    public void onPlayerCommand(Player player, String[] args){
        if (args.length == 0){
            // /fly
            if (player.isFlying()){
                player.setFlying(false);
                sendMessage(player, Lang.FLY_SUCCESS_OFF.get());
            }else{
                player.setFlying(true);
                sendMessage(player, Lang.FLY_SUCCESS_ON.get());
            }
            return;
        }

        if (args.length == 1){
            // /fly <player>
            if (!checkPerm(player, "servercore.fly.use.others")) return;
            if (!checkPlayer(player, args[0])) return;
            Player target = Bukkit.getPlayer(args[0]);
            if (target.isFlying()){
                target.setAllowFlight(false);
                sendMessage(player, Lang.FLY_SUCCESS_OTHERS_OFF.get().replace("{player}", target.getName()));
                sendMessage(target, Lang.FLY_SUCCESS_OTHERS_OFF_PLAYER.get().replace("{player}", player.getName()));
            }else{
                target.setAllowFlight(true);
                sendMessage(player, Lang.FLY_SUCCESS_OTHERS_ON.get().replace("{player}", target.getName()));
                sendMessage(target, Lang.FLY_SUCCESS_OTHERS_ON_PLAYER.get().replace("{player}", player.getName()));
            }
            return;
        }

        sendMessage(player, Lang.FLY_USAGE.get());
    }

    @Override
    public void onConsoleCommand(ConsoleCommandSender sender, String[] args){
        if (args.length == 1){
            // /fly <player>
            if (!checkPlayer(sender, args[0])) return;
            Player target = Bukkit.getPlayer(args[0]);
            if (target.isFlying()){
                target.setAllowFlight(false);
                sendMessage(sender, Lang.FLY_SUCCESS_OTHERS_OFF.get().replace("{player}", target.getName()));
                sendMessage(target, Lang.FLY_SUCCESS_OTHERS_OFF_PLAYER.get().replace("{player}", "Console"));
            }else{
                target.setAllowFlight(true);
                sendMessage(sender, Lang.FLY_SUCCESS_OTHERS_ON.get().replace("{player}", target.getName()));
                sendMessage(target, Lang.FLY_SUCCESS_OTHERS_ON_PLAYER.get().replace("{player}", "Console"));
            }
            return;
        }

        sendMessage(sender, Lang.FLY_USAGE.get());
    }
}
