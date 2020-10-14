package me.pljr.servercore.commands;

import me.pljr.pljrapi.utils.CommandUtil;
import me.pljr.servercore.config.CfgLang;
import me.pljr.servercore.enums.Lang;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class GamemodeCommand extends CommandUtil implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!checkPerm(sender, "servercore.gamemode.use")) return false;

        if (args.length == 1){
            if (!(sender instanceof Player)){
                sendMessage(sender, CfgLang.lang.get(Lang.NO_CONSOLE));
                return false;
            }
            Player player = (Player) sender;

            // /gamemode 0|s|survival
            if (args[0].equals("0") || args[0].equalsIgnoreCase("s") || args[0].equalsIgnoreCase("survival")){
                if (!checkPerm(player, "servercore.gamemode.use.survival")) return false;
                player.setGameMode(GameMode.SURVIVAL);
                sendMessage(player, CfgLang.lang.get(Lang.GAMEMODE_SUCCESS).replace("%type", "Survival"));
                return true;
            }

            // /gamemdoe 1|c|creative
            if (args[0].equals("1") || args[0].equalsIgnoreCase("c") || args[0].equalsIgnoreCase("creative")){
                if (!checkPerm(player, "servercore.gamemode.use.creative")) return false;
                player.setGameMode(GameMode.CREATIVE);
                sendMessage(player, CfgLang.lang.get(Lang.GAMEMODE_SUCCESS).replace("%type", "Creative"));
                return true;
            }

            // /gamemdoe 2|a|adventure
            if (args[0].equals("2") || args[0].equalsIgnoreCase("a") || args[0].equalsIgnoreCase("adventure")){
                if (!checkPerm(player, "servercore.gamemode.use.adventure")) return false;
                player.setGameMode(GameMode.ADVENTURE);
                sendMessage(player, CfgLang.lang.get(Lang.GAMEMODE_SUCCESS).replace("%type", "Adventure"));
                return true;
            }

            // /gamemodoe 3|sp|spectator
            if (args[0].equals("3") || args[0].equalsIgnoreCase("sp") || args[0].equalsIgnoreCase("spectator")){
                if (!checkPerm(player, "servercore.gamemode.use.creative")) return false;
                player.setGameMode(GameMode.SPECTATOR);
                sendMessage(player, CfgLang.lang.get(Lang.GAMEMODE_SUCCESS).replace("%type", "Spectator"));
                return true;
            }
        }

        else if (args.length == 2){
            // /gamemode 0|s|survival <player>
            if (args[0].equals("0") || args[0].equalsIgnoreCase("s") || args[0].equalsIgnoreCase("survival")){
                if (!checkPerm(sender, "servercore.gamemode.use.survival.others")) return false;
                if (!checkPlayer(sender, args[1])) return false;
                Player target = Bukkit.getPlayer(args[1]);
                target.setGameMode(GameMode.SURVIVAL);
                sendMessage(sender, CfgLang.lang.get(Lang.GAMEMODE_SUCCESS_OTHERS).replace("%type", "Survival").replace("%player", args[1]));
                sendMessage(target, CfgLang.lang.get(Lang.GAMEMODE_SUCCESS_OTHERS_PLAYER).replace("%type", "Survival").replace("%player", sender.getName()));
                return true;
            }

            // /gamemode 1|c|creative <player>
            if (args[0].equals("1") || args[0].equalsIgnoreCase("c") || args[0].equalsIgnoreCase("creative")){
                if (!checkPerm(sender, "servercore.gamemode.use.creative.others")) return false;
                if (!checkPlayer(sender, args[1])) return false;
                Player target = Bukkit.getPlayer(args[1]);
                target.setGameMode(GameMode.CREATIVE);
                sendMessage(sender, CfgLang.lang.get(Lang.GAMEMODE_SUCCESS_OTHERS).replace("%type", "Creative").replace("%player", args[1]));
                sendMessage(target, CfgLang.lang.get(Lang.GAMEMODE_SUCCESS_OTHERS_PLAYER).replace("%type", "Creative").replace("%player", sender.getName()));
                return true;
            }

            // /gamemode 2|a|adventure <player>
            if (args[0].equals("2") || args[0].equalsIgnoreCase("a") || args[0].equalsIgnoreCase("adventure")){
                if (!checkPerm(sender, "servercore.gamemode.use.adventure.others")) return false;
                if (!checkPlayer(sender, args[1])) return false;
                Player target = Bukkit.getPlayer(args[1]);
                target.setGameMode(GameMode.ADVENTURE);
                sendMessage(sender, CfgLang.lang.get(Lang.GAMEMODE_SUCCESS_OTHERS).replace("%type", "Adventure").replace("%player", args[1]));
                sendMessage(target, CfgLang.lang.get(Lang.GAMEMODE_SUCCESS_OTHERS_PLAYER).replace("%type", "Adventure").replace("%player", sender.getName()));
                return true;
            }

            // /gamemode 3|sp|spectator <player>
            if (args[0].equals("3") || args[0].equalsIgnoreCase("sp") || args[0].equalsIgnoreCase("spectator")){
                if (!checkPerm(sender, "servercore.gamemode.use.spectator.others")) return false;
                if (!checkPlayer(sender, args[1])) return false;
                Player target = Bukkit.getPlayer(args[1]);
                target.setGameMode(GameMode.SPECTATOR);
                sendMessage(sender, CfgLang.lang.get(Lang.GAMEMODE_SUCCESS_OTHERS).replace("%type", "Spectator").replace("%player", args[1]));
                sendMessage(target, CfgLang.lang.get(Lang.GAMEMODE_SUCCESS_OTHERS_PLAYER).replace("%type", "Spectator").replace("%player", sender.getName()));
                return true;
            }
        }

        sender.sendMessage(CfgLang.lang.get(Lang.GAMEMODE_USAGE));
        return false;
    }
}
