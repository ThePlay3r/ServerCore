package me.pljr.servercore;

import me.pljr.pljrapispigot.PLJRApiSpigot;
import me.pljr.pljrapispigot.database.DataSource;
import me.pljr.pljrapispigot.managers.ConfigManager;
import me.pljr.servercore.commands.ServerCoreCommand;
import me.pljr.servercore.commands.gamemodecommands.*;
import me.pljr.servercore.commands.homecommands.*;
import me.pljr.servercore.commands.itemcommands.*;
import me.pljr.servercore.commands.movementcommands.FSpeedCommand;
import me.pljr.servercore.commands.movementcommands.FlyCommand;
import me.pljr.servercore.commands.movementcommands.WSpeedCommand;
import me.pljr.servercore.commands.teleportcommands.*;
import me.pljr.servercore.commands.warpcommands.*;
import me.pljr.servercore.commands.worldcommands.*;
import me.pljr.servercore.config.Lang;
import me.pljr.servercore.config.MenuItem;
import me.pljr.servercore.config.Settings;
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

import java.util.logging.Logger;

public final class ServerCore extends JavaPlugin {

    public static Logger log;

    private PLJRApiSpigot pljrApiSpigot;

    private PlayerManager playerManager;
    private QueryManager queryManager;
    private WarpManager warpManager;
    private SpawnManager spawnManager;

    // Files
    private ConfigManager configManager;
    private ConfigManager databaseFileManager;
    private ConfigManager langManager;
    private ConfigManager menuFileManager;

    private Settings settings;

    @Override
    public void onEnable() {
        // Plugin startup logic
        log = this.getLogger();
        if (!setupPLJRApi()) return;
        setupConfig();
        setupDatabase();
        setupManagers();
        setupListeners();
        setupComands();
    }

    public boolean setupPLJRApi(){
        if (PLJRApiSpigot.get() == null){
            getLogger().warning("PLJRApi-Spigot is not enabled!");
            return false;
        }
        pljrApiSpigot = PLJRApiSpigot.get();
        return true;
    }

    public void setupConfig(){
        saveDefaultConfig();
        reloadConfig();
        configManager = new ConfigManager(this, "config.yml");
        databaseFileManager = new ConfigManager(this, "database.yml");
        langManager = new ConfigManager(this, "lang.yml");
        menuFileManager = new ConfigManager(this, "menus.yml");
        settings = new Settings(configManager);
        Lang.load(langManager);
        MenuItem.load(menuFileManager);
    }

    private void setupManagers(){
        playerManager = new PlayerManager(this, queryManager, settings.isCachePlayers());
        playerManager.initAutoSave();
        warpManager = new WarpManager(queryManager);
        if (databaseFileManager.getConfig().isSet("spawnLocation")){
            World world = Bukkit.getWorld(databaseFileManager.getString("spawnLocation.world"));
            if (world == null){
                spawnManager = new SpawnManager(databaseFileManager, null);
            }else {
                spawnManager = new SpawnManager(databaseFileManager, new Location(
                        Bukkit.getWorld(databaseFileManager.getString("spawnLocation.world")),
                        databaseFileManager.getDouble("spawnLocation.x"),
                        databaseFileManager.getDouble("spawnLocation.y"),
                        databaseFileManager.getDouble("spawnLocation.z"),
                        (float)databaseFileManager.getDouble("spawnLocation.yaw"),
                        (float)databaseFileManager.getDouble("spawnLocation.pitch")));
            }
        }else{
            spawnManager = new SpawnManager(databaseFileManager, null);
        }
    }

    private void setupDatabase(){
        queryManager = new QueryManager(pljrApiSpigot.getDataSource(configManager));
        queryManager.setupTables();
        for (Player player : Bukkit.getOnlinePlayers()){
            Bukkit.getScheduler().runTaskAsynchronously(this, () -> queryManager.loadPlayer(player.getUniqueId()));
        }
    }

    private void setupListeners(){
        PluginManager pluginManager = getServer().getPluginManager();
        pluginManager.registerEvents(new AsyncPlayerPreLoginListener(playerManager), this);
        pluginManager.registerEvents(new PlayerDeathListener(playerManager), this);
        pluginManager.registerEvents(new PlayerJoinListener(settings, playerManager, spawnManager), this);
        pluginManager.registerEvents(new PlayerTeleportListener(playerManager), this);
        pluginManager.registerEvents(new PlayerQuitListener(playerManager), this);
        pluginManager.registerEvents(new PlayerRespawnListener(spawnManager), this);
        pluginManager.registerEvents(new PlayerCommandPreprocessListener(this, playerManager), this);
    }

    private void setupComands(){
        new ADelhomeCommand(playerManager).registerCommand(this);
        new AHomeCommand(playerManager).registerCommand(this);
        new AHomesCommand(playerManager).registerCommand(this);
        new ASethomeCommand(playerManager).registerCommand(this);
        new BackCommand(playerManager).registerCommand(this);
        new CiCommand().registerCommand(this);
        new DayCommand(settings).registerCommand(this);
        new DelhomeCommand(playerManager).registerCommand(this);
        new DelwarpCommand(warpManager).registerCommand(this);
        new FlyCommand().registerCommand(this);
        new FSpeedCommand().registerCommand(this);
        new GamemodeCommand().registerCommand(this);
        new GMACommand().registerCommand(this);
        new GMCCommand().registerCommand(this);
        new GMSCommand().registerCommand(this);
        new GMSPCommand().registerCommand(this);
        new HomeCommand(playerManager).registerCommand(this);
        new HomesCommand(playerManager).registerCommand(this);
        new ICommand().registerCommand(this);
        new NightCommand(settings).registerCommand(this);
        new RainCommand(settings).registerCommand(this);
        new ReloreCommand().registerCommand(this);
        new RenameCommand().registerCommand(this);
        new SethomeCommand(playerManager).registerCommand(this);
        new SetwarpCommand(warpManager).registerCommand(this);
        new SkullCommand().registerCommand(this);
        new SunCommand(settings).registerCommand(this);
        new ThunderCommand(settings).registerCommand(this);
        new TopBlockCommand().registerCommand(this);
        new TpacceptCommand(playerManager).registerCommand(this);
        new TpaCommand(playerManager).registerCommand(this);
        new TPCommand().registerCommand(this);
        new TpdenyCommand(playerManager).registerCommand(this);
        new TPHereCommand().registerCommand(this);
        new TpignoreCommand(playerManager).registerCommand(this);
        new WarpCommand(warpManager).registerCommand(this);
        new WarpsCommand(settings, warpManager).registerCommand(this);
        new WSpeedCommand().registerCommand(this);
        new SpawnCommand(spawnManager).registerCommand(this);
        new ASpawnCommand(spawnManager).registerCommand(this);
        new SetspawnCommand(spawnManager).registerCommand(this);
        new ServerCoreCommand(this, playerManager).registerCommand(this);
        new AWarpCommand(warpManager).registerCommand(this);
        new TpAllCommand().registerCommand(this);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        spawnManager.saveToFile();
        queryManager.saveWarps(warpManager.getWarps());
        for (Player player : Bukkit.getOnlinePlayers()){
            playerManager.getPlayer(player.getUniqueId(), queryManager::savePlayer);
        }
    }
}
