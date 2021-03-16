package me.pljr.servercore.commands.gamemodecommands;

import me.pljr.pljrapispigot.commands.BukkitCommand;
import me.pljr.pljrapispigot.commands.BukkitCommand;
import me.pljr.servercore.config.Lang;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;

public class GamemodeCommand extends BukkitCommand {

    public GamemodeCommand(){
        super("gamemode", "servercore.gamemode.use");
    }

    @Override
    public void onPlayerCommand(Player player, String[] args){
        if (args.length == 1){
            // /gamemode 0|s|survival
            if (args[0].equals("0") || args[0].equalsIgnoreCase("s") || args[0].equalsIgnoreCase("survival")){
                if (!checkPerm(player, "servercore.gamemode.use.survival")) return;
                player.setGameMode(GameMode.SURVIVAL);
                sendMessage(player, Lang.GAMEMODE_SUCCESS.get().replace("{type}", "Survival"));
                return;
            }

            // /gamemdoe 1|c|creative
            if (args[0].equals("1") || args[0].equalsIgnoreCase("c") || args[0].equalsIgnoreCase("creative")){
                if (!checkPerm(player, "servercore.gamemode.use.creative")) return;
                player.setGameMode(GameMode.CREATIVE);
                sendMessage(player, Lang.GAMEMODE_SUCCESS.get().replace("{type}", "Creative"));
                sendMessageClean(player, Lang.GAMEMODE_SUCCESS.get().replace("{type}", "Creative"));
                System.out.println("executed");
                return;
            }

            // /gamemdoe 2|a|adventure
            if (args[0].equals("2") || args[0].equalsIgnoreCase("a") || args[0].equalsIgnoreCase("adventure")){
                if (!checkPerm(player, "servercore.gamemode.use.adventure")) return;
                player.setGameMode(GameMode.ADVENTURE);
                sendMessage(player, Lang.GAMEMODE_SUCCESS.get().replace("{type}", "Adventure"));
                return;
            }

            // /gamemodoe 3|sp|spectator
            if (args[0].equals("3") || args[0].equalsIgnoreCase("sp") || args[0].equalsIgnoreCase("spectator")){
                if (!checkPerm(player, "servercore.gamemode.use.creative")) return;
                player.setGameMode(GameMode.SPECTATOR);
                sendMessage(player, Lang.GAMEMODE_SUCCESS.get().replace("{type}", "Spectator"));
                return;
            }
        }

        else if (args.length == 2){
            // /gamemode 0|s|survival <player>
            if (args[0].equals("0") || args[0].equalsIgnoreCase("s") || args[0].equalsIgnoreCase("survival")){
                if (!checkPerm(player, "servercore.gamemode.use.survival.others")) return;
                if (!checkPlayer(player, args[1])) return;
                Player target = Bukkit.getPlayer(args[1]);
                target.setGameMode(GameMode.SURVIVAL);
                sendMessage(player, Lang.GAMEMODE_SUCCESS_OTHERS.get().replace("{type}", "Survival").replace("{player}", args[1]));
                sendMessage(target, Lang.GAMEMODE_SUCCESS_OTHERS_PLAYER.get().replace("{type}", "Survival").replace("{player}", player.getName()));
                return;
            }

            // /gamemode 1|c|creative <player>
            if (args[0].equals("1") || args[0].equalsIgnoreCase("c") || args[0].equalsIgnoreCase("creative")){
                if (!checkPerm(player, "servercore.gamemode.use.creative.others")) return;
                if (!checkPlayer(player, args[1])) return;
                Player target = Bukkit.getPlayer(args[1]);
                target.setGameMode(GameMode.CREATIVE);
                sendMessage(player, Lang.GAMEMODE_SUCCESS_OTHERS.get().replace("{type}", "Creative").replace("{player}", args[1]));
                sendMessage(target, Lang.GAMEMODE_SUCCESS_OTHERS_PLAYER.get().replace("{type}", "Creative").replace("{player}", player.getName()));
                return;
            }

            // /gamemode 2|a|adventure <player>
            if (args[0].equals("2") || args[0].equalsIgnoreCase("a") || args[0].equalsIgnoreCase("adventure")){
                if (!checkPerm(player, "servercore.gamemode.use.adventure.others")) return;
                if (!checkPlayer(player, args[1])) return;
                Player target = Bukkit.getPlayer(args[1]);
                target.setGameMode(GameMode.ADVENTURE);
                sendMessage(player, Lang.GAMEMODE_SUCCESS_OTHERS.get().replace("{type}", "Adventure").replace("{player}", args[1]));
                sendMessage(target, Lang.GAMEMODE_SUCCESS_OTHERS_PLAYER.get().replace("{type}", "Adventure").replace("{player}", player.getName()));
                return;
            }

            // /gamemode 3|sp|spectator <player>
            if (args[0].equals("3") || args[0].equalsIgnoreCase("sp") || args[0].equalsIgnoreCase("spectator")){
                if (!checkPerm(player, "servercore.gamemode.use.spectator.others")) return;
                if (!checkPlayer(player, args[1])) return;
                Player target = Bukkit.getPlayer(args[1]);
                target.setGameMode(GameMode.SPECTATOR);
                sendMessage(player, Lang.GAMEMODE_SUCCESS_OTHERS.get().replace("{type}", "Spectator").replace("{player}", args[1]));
                sendMessage(target, Lang.GAMEMODE_SUCCESS_OTHERS_PLAYER.get().replace("{type}", "Spectator").replace("{player}", player.getName()));
                return;
            }
        }

        sendMessage(player, Lang.GAMEMODE_USAGE.get());
    }

    @Override
    public void onConsoleCommand(ConsoleCommandSender sender, String[] args){
        if (args.length == 2){
            // /gamemode 0|s|survival <player>
            if (args[0].equals("0") || args[0].equalsIgnoreCase("s") || args[0].equalsIgnoreCase("survival")){
                if (!checkPlayer(sender, args[1])) return;
                Player target = Bukkit.getPlayer(args[1]);
                target.setGameMode(GameMode.SURVIVAL);
                sendMessage(sender, Lang.GAMEMODE_SUCCESS_OTHERS.get().replace("{type}", "Survival").replace("{player}", args[1]));
                sendMessage(target, Lang.GAMEMODE_SUCCESS_OTHERS_PLAYER.get().replace("{type}", "Survival").replace("{player}", "Console"));
                return;
            }

            // /gamemode 1|c|creative <player>
            if (args[0].equals("1") || args[0].equalsIgnoreCase("c") || args[0].equalsIgnoreCase("creative")){
                if (!checkPlayer(sender, args[1])) return;
                Player target = Bukkit.getPlayer(args[1]);
                target.setGameMode(GameMode.CREATIVE);
                sendMessage(sender, Lang.GAMEMODE_SUCCESS_OTHERS.get().replace("{type}", "Creative").replace("{player}", args[1]));
                sendMessage(target, Lang.GAMEMODE_SUCCESS_OTHERS_PLAYER.get().replace("{type}", "Creative").replace("{player}", "Console"));
                return;
            }

            // /gamemode 2|a|adventure <player>
            if (args[0].equals("2") || args[0].equalsIgnoreCase("a") || args[0].equalsIgnoreCase("adventure")){
                if (!checkPlayer(sender, args[1])) return;
                Player target = Bukkit.getPlayer(args[1]);
                target.setGameMode(GameMode.ADVENTURE);
                sendMessage(sender, Lang.GAMEMODE_SUCCESS_OTHERS.get().replace("{type}", "Adventure").replace("{player}", args[1]));
                sendMessage(target, Lang.GAMEMODE_SUCCESS_OTHERS_PLAYER.get().replace("{type}", "Adventure").replace("{player}", "Console"));
                return;
            }

            // /gamemode 3|sp|spectator <player>
            if (args[0].equals("3") || args[0].equalsIgnoreCase("sp") || args[0].equalsIgnoreCase("spectator")){
                if (!checkPlayer(sender, args[1])) return;
                Player target = Bukkit.getPlayer(args[1]);
                target.setGameMode(GameMode.SPECTATOR);
                sendMessage(sender, Lang.GAMEMODE_SUCCESS_OTHERS.get().replace("{type}", "Spectator").replace("{player}", args[1]));
                sendMessage(target, Lang.GAMEMODE_SUCCESS_OTHERS_PLAYER.get().replace("{type}", "Spectator").replace("{player}", "Console"));
                return;
            }
        }

        sendMessage(sender, Lang.GAMEMODE_USAGE.get());
    }
}
