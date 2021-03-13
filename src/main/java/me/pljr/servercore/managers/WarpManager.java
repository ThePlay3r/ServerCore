package me.pljr.servercore.managers;

import org.bukkit.Location;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class WarpManager {
    private final HashMap<String, Location> warps;

    public WarpManager(QueryManager queryManager){
        this.warps = queryManager.loadWarps();
    }

    public void setWarp(String name, Location location){
        warps.put(name, location);
    }

    public void delWarp(String name){
        warps.remove(name);
    }

    public List<String> getWarpNames(){
        List<String> list = new ArrayList<>();
        for (Map.Entry<String, Location> entry : warps.entrySet()){
            list.add(entry.getKey());
        }
        return list;
    }

    public boolean isWarp(String warpName){
        return this.warps.containsKey(warpName);
    }

    public Location getWarp(String warpName){
        return this.warps.get(warpName);
    }

    public HashMap<String, Location> getWarps() {
        return warps;
    }
}
