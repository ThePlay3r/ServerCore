package me.pljr.servercore.files;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.Plugin;

import java.io.File;
import java.io.IOException;

public class DatabaseFile {
    private static File file;
    private static FileConfiguration customFile;

    public static void setupDatabaseFile(Plugin plugin){
        file = new File(plugin.getDataFolder(), "database.yml");

        if (!file.exists()){
            try{
                file.createNewFile();
            }catch (IOException e){
                //
            }
        }
        customFile = YamlConfiguration.loadConfiguration(file);
    }

    public static FileConfiguration getDatabaseFile(){
        return customFile;
    }

    public static void saveDatabaseFile(){
        try{
            customFile.save(file);
        }catch (IOException e){
            System.out.println("ServerCore: Couldn't save Database File!");
        }
    }

    public static void removeDatabaseFile(){
        if (file.delete()){
            System.out.println("ServerCore: Database File deleted.");
        }else{
            System.out.println("ServerCore: Couldn't delete Database File!");
        }
    }

    public static void reloadDatabaseFile(){
        customFile = YamlConfiguration.loadConfiguration(file);
    }
}
