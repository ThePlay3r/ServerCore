package me.pljr.servercore.config;

import me.pljr.pljrapispigot.managers.ConfigManager;
import org.bukkit.configuration.file.FileConfiguration;

import java.util.HashMap;

public enum Lang {
    CI_USAGE("&aServerCore &8» &bUsage: &f/ci <player>"),
    CI_SUCCESS("&aServerCore &8» &fSuccessfully cleared your inventory."),
    CI_SUCCESS_OTHERS("&aServerCore &8» &fSuccessfully cleared inventory of &b{player}&f."),
    CI_SUCCESS_OTHERS_PLAYER("&aServerCore &8» &fYour inventory has been cleared by &b{player}&f."),
    I_USAGE("&aServerCore &8» &bUsage: &f/i <material>"),
    I_SUCCESS("&aServerCore &8» &fYou successfully received &b{material}&f."),
    BACK_SUCCESS("&aServerCore &8» &fOpening menu.."),
    FLY_USAGE("&aServerCore &8» &bUsage: &f/fly <player>"),
    FLY_SUCCESS_ON("&aServerCore &8» &fFly &bactivated&f."),
    FLY_SUCCESS_OFF("&aServerCore &8» &fFly &bdeactivated&f."),
    FLY_SUCCESS_OTHERS_ON("&aServerCore &8» &fSuccessfully &benabled &fflight of &b{player}&f."),
    FLY_SUCCESS_OTHERS_OFF("&aServerCore &8» &fSuccessfully &bdisabeld &fflight of &b{player}&f."),
    FLY_SUCCESS_OTHERS_ON_PLAYER("&aServerCore &8» &fYour flight has been &benabled &fby &b{player}&f."),
    FLY_SUCCESS_OTHERS_OFF_PLAYER("&aServerCore &8» &fYour flight has been &fdisabled &fby &b{player}&f."),
    FSPEED_USAGE("&aServerCore &8» &bUsage: &f/fspeed <player> <speed>"),
    FSPEED_SUCCESS("&aServerCore &8» &fSuccessfully set your flight speed to &b{speed}&f."),
    FSPEED_SUCCESS_OTHERS("&aServerCore &8» &fSuccessfully set flight speed of &b{player} &fto &b{speed}&f."),
    FSPEED_SUCCESS_OTHERS_PLAYER("&aServerCore &8» &fYour flight speed has been set to &b{speed} &fby &b{player}&f."),
    WSPEED_USAGE("&aServerCore &8» &bUsage: &f/wspeed <player> <speed>"),
    WSPEED_SUCCESS("&aServerCore &8» &fSuccessfully set your walk speed to &b{speed}&f."),
    WSPEED_SUCCESS_OTHERS("&aServerCore &8» &fSuccessfully set walk speed of &b{player} &fto &b{player}&f."),
    WSPEED_SUCCESS_OTHERS_PLAYER("&aServerCore &8» &fYour walk speed has been set to &b{speed} &fby &b{player}&f."),
    GAMEMODE_USAGE("&aServerCore &8» &bUsage: &f/gamemode <type> <player>"),
    GAMEMODE_SUCCESS("&aServerCore &8» &fYour gamemode has been set to &b{type}&f."),
    GAMEMODE_SUCCESS_OTHERS("&aServerCore &8» &fSuccessfully set gamemode of &b{player} &fto &b{type}&f."),
    GAMEMODE_SUCCESS_OTHERS_PLAYER("&aServerCore &8» &fYour gamemode has been set to &b{type} &fby &b{player}&f."),
    GAMEMODE_FAILURE_NO_GAMEMODE("&aServerCore &8» &b{type} &fis not a valid gamemode!"),
    GAMEMODE_GMC_USAGE("&aServerCore &8» &bUsage: &f/gmc <player>"),
    GAMEMODE_GMS_USAGE("&aServerCore &8» &bUsage: &f/gms <player>"),
    GAMEMODE_GMA_USAGE("&aServerCore &8» &bUsage: &f/gma <player>"),
    GAMEMODE_GMSP_USAGE("&aServerCore &8» &bUsage: &f/gmsp <player>"),
    SETHOME_USAGE("&aServerCore &8» &bUsage: &f/sethome <name>"),
    SETHOME_SUCCESS("&aServerCore &8» &fSuccessfully create new home with name &b{name}&f."),
    SETHOME_FAILURE_MAX_HOMES("&aServerCore &8» &fYou can't create more homes!"),
    ASETHOME_USAGE("&aServerCore &8» &bUsage: &f/asethome <player> <name>"),
    ASETHOME_SUCCESS("&aServerCore &8» &fSuccessfully create home &b{name} &ffor &b{player}&f."),
    ASETHOME_SUCCESS_PLAYER("&aServerCore &8» &b{player} &fcreated a new home with name &b{name} &ffor you."),
    HOME_USAGE("&aServerCore &8» &bUsage: &f/home <name>"),
    HOME_SUCCESS("&aServerCore &8» &fSuccessfully teleported to home &b{home}&f."),
    HOME_FAILURE_NO_HOME("&aServerCore &8» &fYou don't have home with name &b{name}&f."),
    AHOME_USAGE("&aServerCore &8» &bUsage: &f/ahome <player> <name>"),
    AHOME_SUCCESS("&aServerCore &8» &fSuccessfully teleported to home &b{home} &fof &b{player}&f."),
    AHOME_FAILURE_NO_HOME("&aServerCore &8» &b{player} &fdoes not have a home with name &b{name}&f."),
    DELHOME_USAGE("&aServerCore &8» &bUsage: &f/delhome <name>"),
    DELHOME_SUCCESS("&aServerCore &8» &fSuccessfully deleted home with name &b{name}&f."),
    DELHOME_FAILURE_NO_HOME("&aServerCore &8» &fYou do not have a home with name &b{name}&f."),
    ADELHOME_USAGE("&aServerCore &8» &bUsage: &f/adelhome <player> <name>"),
    ADELHOME_SUCCESS("&aServerCore &8» &fSuccessfully deleted home &b{name} &fof player &b{player}&f."),
    ADELHOME_SUCCESS_PLAYER("&aServerCore &8» &fYour home &b{name} &fwas deleted by &b{player}&f."),
    ADELHOME_FAILURE_NO_HOME("&aServerCore &8» &b{player} &fdoes not own a home with name &b{name}&f."),
    HOMES_SUCCESS_TITLE("&aServerCore &8» &bYour homes:"),
    HOMES_SUCCESS_FORMAT("&7- &f{name}" +
            "<hover:show_text:'&eClick to teleport'><click:run_command:'home {name}'>&e➹</hover></click>" +
            "<hover:show_text:'&cClick to remove'><click:run_command:'delhome {name}'>&c✕</hover></click>"),
    AHOMES_USAGE("&aServerCore &8» &bUsage: &f/ahomes <player>"),
    AHOMES_SUCCESS_TITLE("&aServerCore &8» &bHomes of {player}:"),
    AHOMES_SUCCESS_FORMAT("&7- &f{name}" +
            "<hover:show_text:'&eClick to teleport'><click:run_command:'ahome {player} {name}'>&e➹</hover></click>" +
            "<hover:show_text:'&cClick to remove'><click:run_command:'adelhome {player} {name}'>&c✕</hover></click>"),
    RELORE_USAGE("&aServerCore &8» &bUsage: &f/relore <line> <text> &7(With item in hand)"),
    RELORE_SUCCESS("&aServerCore &8» &fSuccessfully changed line &b{line} &fto &b{text}&f."),
    RENAME_USAGE("&aServerCore &8» &bUsage: &f/rename <name> &7(With item in hand)"),
    RENAME_SUCCESS("&aServerCore &8» &fSuccessfully renamed item to &b{name}&f."),
    SKULL_USAGE("&aServerCore &8» &bUsage: &f/skull <name>"),
    SKULL_SUCCESS("&aServerCore &8» &fSuccessfully got a skull of &b{name}&f."),
    TP_USAGE("&aServerCore &8» &bUsage: &f/tp <player> <player>"),
    TP_SUCCESS("&aServerCore &8» &fYou have been successfully teleported to &b{player}&f."),
    TP_SUCCESS_PLAYER("&aServerCore &8» &b{player} &fhas teleported to you."),
    TP_SUCCESS_OTHERS("&aServerCore &8» &fSuccessfully teleported &b{player1} &fto &b{player2}&f."),
    TP_SUCCESS_OTHERS_PLAYER1("&aServerCore &8» &fYou have been teleported to &b{target} &fby &b{player}&f."),
    TP_SUCCESS_OTHERS_PLAYER2("&aServerCore &8» &b{target} &fhas been teleported to you by &b{player}&f."),
    TPHERE_USAGE("&aServerCore &8» &bUsage: &f/tphere <player>"),
    TPHERE_SUCCESS("&aServerCore &8» &fSuccessfully teleported &b{player} &fto you."),
    TPHERE_SUCCESS_PLAYER("&aServerCore &8» &fYou have been teleported to &b{player}&f."),
    TOPBLOCK_SUCCESS("&aServerCore &8» &fYou've been teleported to the highest block."),
    TPA_USAGE("&aServerCore &8» &bUsage: &f/tpa <player>"),
    TPA_SUCCESS("&aServerCore &8» &fSuccessfully requested a teleportation to &b{player}&f."),
    TPA_SUCCESS_PLAYER("&aServerCore &8» &b{player} &frequested a teleportation." +
            "\n<hover:show_text:'&aClick to accept'><click:run_command:'tpaccept'>&a&lAccept &a✓</click></hover>" +
            "\n<hover:show_text:'&cClick to decline'><click:run_command:'tpdeny'>&c&lDecline &c✕</click></hover>"),
    TPA_FAILURE_BLOCKED("&aServerCore &8» &fYou are in the blocked list of &b{player}&f."),
    TPACCEPT_SUCCESS("&aServerCore &8» &fSuccessfully accepted teleportation request from &b{player}&f."),
    TPACCEPT_SUCCESS_PLAYER("&aServerCore &8» &b{player} &faccepted your teleportation request."),
    TPACCEPT_FAILURE_NO_REQUEST("&aServerCore &8» &fYou don't have any teleportation requests."),
    TPDENY_SUCCESS("&aServerCore &8» &fYou declined teleportation request from &b{player}&f."),
    TPDENY_SUCCESS_PLAYER("&aServerCore &8» &b{player} &fdeclined your teleportation request."),
    TPDENY_FAILURE_NO_REQEUST("&aServerCore &8» &fYou don't have any teleportation requests."),
    TPIGNORE_USAGE("&aServerCore &8» &bUsage: &f/tpignore <player>"),
    TPIGNORE_SUCCESS_ADD("&aServerCore &8» &b{player} &fhas been added to your blocked list."),
    TPIGNORE_SUCCESS_REMOVE("&aServerCore &8» &b{player} &fhas been removed from your blocked list."),
    DAY_SUCCESS("&aServerCore &8» &fSuccessfully set time to &bday."),
    NIGHT_SUCCESS("&aServerCore &8» &fSuccessfully set time to &bnight."),
    RAIN_SUCCESS("&aServerCore &8» &fSuccessfully set weather to &brain."),
    SUN_SUCCESS("&aServerCore &8» &fSuccessfully set weather to &bsunny."),
    THUNDER_SUCCESS("&aServerCore &8» &fSuccessfully set weather to &bthunder."),
    WARP_USAGE("&aServerCore &8» &bUsage: &f/warp <name>"),
    WARP_SUCCESS("&aServerCore &8» &fTeleporting to &b{warp}&f."),
    WARP_FAILURE_NO_WARP("&aServerCore &8» &b{warp} &fis not a warp."),
    SETWARP_USAGE("&aServerCore &8» &bUsage: &f/setwarp <name>"),
    SETWARP_SUCCESS("&aServerCore &8» &fSuccessfully created warp &b{warp}&f." +
            "\n<hover:show_text:'&eClick to teleport'><click:run_command:'warp {warp}'>&e&lTeleport &e➹</click></hover>" +
            "\n<hover:show_text:'&cClick to remove'><click:run_command:'delwarp {warp}'>&c&lRemove &c✕</click></hover>"),
    DELWARP_USAGE("&aServerCore &8» &bUsage: &f/delwarp <name>"),
    DELWARP_SUCCESS("&aServerCore &8» &fSuccessfully removed warp &b{warp}&f."),
    DELWARP_FAILURE_NO_WARP("&aServerCore &8» &b{warp} &fdoes not exists."),
    WARPS_SUCCESS_TITLE("&aServerCore &8» &bServer warps:"),
    WARPS_SUCCESS_FORMAT("&7- &f{name} <hover:show_text:'&eClick to teleport'><click:run_command:'warp {name}'>&e➹</hover></click>"),
    WARPS_SUCCESS_FORMAT_REMOVE("&7- &f{name}" +
            "<hover:show_text:'&eClick to teleport'><click:run_command:'warp {name}'>&e➹</hover></click>" +
            "<hover:show_text:'&cClick to remove'><click:run_command:'delwarp {name}'>&c✕</hover></click>"),
    SPAWN_SUCCESS("&aServerCore &8» &fSuccessfully teleported to spawn."),
    SPAWN_FAILURE_NO_SPAWN("&aServerCore &8» &fSpawn location is &bnot &fset."),
    ASPAWN_USAGE("&aServerCore &8» &bUsage: &f/aspawn <player>"),
    ASPAWN_SUCCESS("&aServerCore &8» &fSuccessfully teleported &b{player} &fto spawn."),
    ASPAWN_SUCCESS_PLAYER("&aServerCore &8» &fYou have been teleported to spawn by &b{player}&f."),
    SETSPAWN_SUCCESS("&aServerCore &8» &fSuccessfully &bset &fthe spawn location."),
    SERVERCORE_USAGE("&aServerCore &8» &bUsage: &f/servercore spy"),
    SERVERCORE_SPY_SUCCESS_ON("&aServerCore &8» &fSuccessfully &benabled &fspying."),
    SERVERCORE_SPY_SUCCESS_OFF("&aServerCore &8» &fSuccessfully &bdisabled &fspying."),
    AWARP_SUCCESS("&aServerCore &8» &fSuccessfully teleported &b{player} &fto &b{warp}&f."),
    AWARP_SUCCESS_PLAYER("&aServerCore &8» &fYou have been teleported to &b{warp} &fby &b{player}&f."),
    AWARP_USAGE("&aServerCore &8» &bUsage: &f/awarp <player> <warp>"),
    BACK_MENU_TITLE("&aServerCore &8> &b/back"),
    WARP_MENU_TITLE("&aServerCore &8> &bWarps"),
    TPALL_SUCCESS("&ServerCore &8» &fTeleported &ball &fplayers to &byou&f.");

    private static HashMap<Lang, String> lang;
    private final String defaultValue;

    Lang(String defaultValue){
        this.defaultValue = defaultValue;
    }

    public static void load(ConfigManager config){
        FileConfiguration fileConfig = config.getConfig();
        lang = new HashMap<>();
        for (Lang lang : values()){
            if (!fileConfig.isSet(lang.toString())){
                fileConfig.set(lang.toString(), lang.defaultValue);
            }else{
                Lang.lang.put(lang, config.getString(lang.toString()));
            }
        }
        config.save();
    }

    public String get(){
        return lang.getOrDefault(this, defaultValue);
    }
}
