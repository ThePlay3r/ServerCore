package me.pljr.servercore.managers;

import org.bukkit.Location;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class WarpManager {
    private HashMap<String, Location> warps;

    public WarpManager(){
        this.warps = new HashMap<>();
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

    public void setWarps(HashMap<String, Location> warps) {
        this.warps = warps;
    }

    public HashMap<String, Location> getWarps() {
        return warps;
    }
}
