package me.pljr.servercore;

import me.pljr.pljrapi.PLJRApi;
import me.pljr.pljrapi.database.DataSource;
import me.pljr.pljrapi.managers.ConfigManager;
import me.pljr.servercore.commands.*;
import me.pljr.servercore.config.CfgBackMenu;
import me.pljr.servercore.config.CfgLang;
import me.pljr.servercore.config.CfgSettings;
import me.pljr.servercore.files.DatabaseFile;
import me.pljr.servercore.listeners.*;
import me.pljr.servercore.managers.PlayerManager;
import me.pljr.servercore.managers.QueryManager;
import me.pljr.servercore.managers.SpawnManager;
import me.pljr.servercore.managers.WarpManager;
import me.pljr.servercore.menus.BackMenu;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public final class ServerCore extends JavaPlugin {
    private static ConfigManager configManager;
    private static ConfigManager databaseFileManager;
    private static PlayerManager playerManager;
    private static QueryManager queryManager;
    private static WarpManager warpManager;
    private static SpawnManager spawnManager;

    @Override
    public void onEnable() {
        // Plugin startup logic
        if (!setupPLJRApi()) return;
        setupConfig();
        setupManagers();
        setupDatabase();
        setupListeners();
        setupComands();
    }

    private boolean setupPLJRApi(){
        PLJRApi api = (PLJRApi) Bukkit.getServer().getPluginManager().getPlugin("PLJRApi");
        if (api == null){
            Bukkit.getConsoleSender().sendMessage("§cServerCore: PLJRApi not found, disabling plugin!");
            getServer().getPluginManager().disablePlugin(this);
            return false;
        }else{
            Bukkit.getConsoleSender().sendMessage("§aServerCore: Hooked into PLJRApi!");
            return true;
        }
    }

    private void setupConfig(){
        saveDefaultConfig();
        configManager = new ConfigManager(getConfig(), "§cServerCore:", "config.yml");
        CfgSettings.load(configManager);
        CfgBackMenu.load(configManager);
        CfgLang.load(configManager);
        DatabaseFile.setupDatabaseFile(this);
        databaseFileManager = new ConfigManager(DatabaseFile.getDatabaseFile(), "§cServerCore:", "database.yml");
    }

    private void setupManagers(){
        playerManager = new PlayerManager();
        warpManager = new WarpManager();
        spawnManager = new SpawnManager();
        if (DatabaseFile.getDatabaseFile().isSet("spawnLocation")){
            spawnManager.setLocation(new Location(
                    Bukkit.getWorld(databaseFileManager.getString("spawnLocation.world")),
                            databaseFileManager.getDouble("spawnLocation.x"),
                            databaseFileManager.getDouble("spawnLocation.y"),
                            databaseFileManager.getDouble("spawnLocation.z"))
            );
        }
    }

    private void setupDatabase(){
        queryManager = new QueryManager(this, DataSource.getFromConfig(configManager));
        queryManager.setupTables();
        queryManager.loadWarpsSync();
        for (Player player : Bukkit.getOnlinePlayers()){
            queryManager.loadPlayer(player.getUniqueId());
        }
    }

    private void setupListeners(){
        PluginManager pluginManager = getServer().getPluginManager();
        pluginManager.registerEvents(new AsyncPlayerPreLoginListener(), this);
        pluginManager.registerEvents(new PlayerDeathListener(), this);
        pluginManager.registerEvents(new PlayerJoinListener(), this);
        pluginManager.registerEvents(new PlayerTeleportListener(), this);
        pluginManager.registerEvents(new BackMenu(), this);
        pluginManager.registerEvents(new PlayerQuitListener(), this);
    }

    private void setupComands(){
        getCommand("adelhome").setExecutor(new ADelhomeCommand());
        getCommand("ahome").setExecutor(new AHomeCommand());
        getCommand("ahomes").setExecutor(new AHomesCommand());
        getCommand("asethome").setExecutor(new ASethomeCommand());
        getCommand("back").setExecutor(new BackCommand());
        getCommand("ci").setExecutor(new CiCommand());
        getCommand("day").setExecutor(new DayCommand());
        getCommand("delhome").setExecutor(new DelhomeCommand());
        getCommand("delwarp").setExecutor(new DelwarpCommand());
        getCommand("fly").setExecutor(new FlyCommand());
        getCommand("fspeed").setExecutor(new FSpeedCommand());
        getCommand("gamemode").setExecutor(new GamemodeCommand());
        getCommand("gma").setExecutor(new GMACommand());
        getCommand("gmc").setExecutor(new GMCCommand());
        getCommand("gms").setExecutor(new GMSCommand());
        getCommand("gmsp").setExecutor(new GMSPCommand());
        getCommand("home").setExecutor(new HomeCommand());
        getCommand("homes").setExecutor(new HomesCommand());
        getCommand("i").setExecutor(new ICommand());
        getCommand("night").setExecutor(new NightCommand());
        getCommand("rain").setExecutor(new RainCommand());
        getCommand("relore").setExecutor(new ReloreCommand());
        getCommand("rename").setExecutor(new RenameCommand());
        getCommand("sethome").setExecutor(new SethomeCommnad());
        getCommand("setwarp").setExecutor(new SetwarpCommand());
        getCommand("skull").setExecutor(new SkullCommand());
        getCommand("sun").setExecutor(new SunCommand());
        getCommand("thunder").setExecutor(new ThunderCommand());
        getCommand("topblock").setExecutor(new TopBlockCommand());
        getCommand("tpaccept").setExecutor(new TpacceptCommand());
        getCommand("tpa").setExecutor(new TpaCommand());
        getCommand("tp").setExecutor(new TPCommand());
        getCommand("tpdeny").setExecutor(new TpdenyCommand());
        getCommand("tphere").setExecutor(new TPHereCommand());
        getCommand("tpignore").setExecutor(new TpignoreCommand());
        getCommand("warp").setExecutor(new WarpCommand());
        getCommand("warps").setExecutor(new WarpsCommand());
        getCommand("wspeed").setExecutor(new WSpeedCommand());
        getCommand("spawn").setExecutor(new SpawnCommand());
        getCommand("aspawn").setExecutor(new ASpawnCommand());
        getCommand("setspawn").setExecutor(new SetspawnCommand());
        getCommand("servercore").setExecutor(new ServerCoreCommand());
    }

    public static PlayerManager getPlayerManager() {
        return playerManager;
    }
    public static QueryManager getQueryManager() {
        return queryManager;
    }
    public static WarpManager getWarpManager() {
        return warpManager;
    }
    public static SpawnManager getSpawnManager() {
        return spawnManager;
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        spawnManager.saveToFile();
        queryManager.saveWarpsSync();
        for (Player player : Bukkit.getOnlinePlayers()){
            queryManager.savePlayerSync(player.getUniqueId());
        }
    }
}
