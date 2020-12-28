package me.pljr.servercore;

import me.pljr.pljrapispigot.database.DataSource;
import me.pljr.pljrapispigot.managers.ConfigManager;
import me.pljr.servercore.commands.*;
import me.pljr.servercore.config.CfgBackMenu;
import me.pljr.servercore.config.CfgSettings;
import me.pljr.servercore.config.CfgWarpMenu;
import me.pljr.servercore.config.Lang;
import me.pljr.servercore.listeners.*;
import me.pljr.servercore.managers.PlayerManager;
import me.pljr.servercore.managers.QueryManager;
import me.pljr.servercore.managers.SpawnManager;
import me.pljr.servercore.managers.WarpManager;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public final class ServerCore extends JavaPlugin {
    private static ServerCore instance;
    private static ConfigManager configManager;
    private static ConfigManager databaseFileManager;
    private static PlayerManager playerManager;
    private static QueryManager queryManager;
    private static WarpManager warpManager;
    private static SpawnManager spawnManager;

    @Override
    public void onEnable() {
        // Plugin startup logic
        instance = this;
        setupConfig();
        setupManagers();
        setupDatabase();
        setupListeners();
        setupComands();
    }

    public void setupConfig(){
        saveDefaultConfig();
        reloadConfig();
        configManager = new ConfigManager(this, "config.yml");
        CfgSettings.load(configManager);
        CfgBackMenu.load(configManager);
        CfgWarpMenu.load(configManager);
        Lang.load(configManager);
        databaseFileManager = new ConfigManager(this, "database.yml");
    }

    private void setupManagers(){
        playerManager = new PlayerManager();
        warpManager = new WarpManager();
        spawnManager = new SpawnManager();
        if (databaseFileManager.getConfig().isSet("spawnLocation")){
            World world = Bukkit.getWorld(databaseFileManager.getString("spawnLocation.world"));
            if (world == null){
                spawnManager.setLocation(null);
            }else {
                spawnManager.setLocation(new Location(
                        Bukkit.getWorld(databaseFileManager.getString("spawnLocation.world")),
                        databaseFileManager.getDouble("spawnLocation.x"),
                        databaseFileManager.getDouble("spawnLocation.y"),
                        databaseFileManager.getDouble("spawnLocation.z"),
                        (float)databaseFileManager.getDouble("spawnLocation.yaw"),
                        (float)databaseFileManager.getDouble("spawnLocation.pitch")));
            }
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
        pluginManager.registerEvents(new PlayerQuitListener(), this);
        pluginManager.registerEvents(new PlayerRespawnListener(), this);
        pluginManager.registerEvents(new PlayerCommandPreprocessListener(), this);
    }

    private void setupComands(){
        new ADelhomeCommand().registerCommand(this);
        new AHomeCommand().registerCommand(this);
        new AHomesCommand().registerCommand(this);
        new ASethomeCommand().registerCommand(this);
        new BackCommand().registerCommand(this);
        new CiCommand().registerCommand(this);
        new DayCommand().registerCommand(this);
        new DelhomeCommand().registerCommand(this);
        new DelwarpCommand().registerCommand(this);
        new FlyCommand().registerCommand(this);
        new FSpeedCommand().registerCommand(this);
        new GamemodeCommand().registerCommand(this);
        new GMACommand().registerCommand(this);
        new GMCCommand().registerCommand(this);
        new GMSCommand().registerCommand(this);
        new GMSPCommand().registerCommand(this);
        new HomeCommand().registerCommand(this);
        new HomesCommand().registerCommand(this);
        new ICommand().registerCommand(this);
        new NightCommand().registerCommand(this);
        new RainCommand().registerCommand(this);
        new ReloreCommand().registerCommand(this);
        new RenameCommand().registerCommand(this);
        new SethomeCommand().registerCommand(this);
        new SetwarpCommand().registerCommand(this);
        new SkullCommand().registerCommand(this);
        new SunCommand().registerCommand(this);
        new ThunderCommand().registerCommand(this);
        new TopBlockCommand().registerCommand(this);
        new TpacceptCommand().registerCommand(this);
        new TpaCommand().registerCommand(this);
        new TPCommand().registerCommand(this);
        new TpdenyCommand().registerCommand(this);
        new TPHereCommand().registerCommand(this);
        new TpignoreCommand().registerCommand(this);
        new WarpCommand().registerCommand(this);
        new WarpsCommand().registerCommand(this);
        new WSpeedCommand().registerCommand(this);
        new SpawnCommand().registerCommand(this);
        new ASpawnCommand().registerCommand(this);
        new SetspawnCommand().registerCommand(this);
        new ServerCoreCommand().registerCommand(this);
        new AWarpCommand().registerCommand(this);
    }

    public static ServerCore getInstance() {
        return instance;
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
    public static ConfigManager getDatabaseFileManager() {
        return databaseFileManager;
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
