package me.pljr.servercore.objects;

import org.bukkit.Location;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.List;

public class CorePlayer {
    private Location lastLoc;
    private Location deathLoc;
    private final HashMap<String, Location> homes;
    private boolean spy;
    private Player tpaRequester;
    private final List<String> tpaBlocked;

    public CorePlayer(Location lastLoc, Location deathLoc, HashMap<String, Location> homes, boolean spy, List<String> tpaBlocked){
        this.lastLoc = lastLoc;
        this.deathLoc = deathLoc;
        this.homes = homes;
        this.spy = spy;
        this.tpaRequester = null;
        this.tpaBlocked = tpaBlocked;
    }

    public Location getLastLoc() {
        return lastLoc;
    }

    public void setLastLoc(Location lastLoc) {
        this.lastLoc = lastLoc;
    }

    public Location getDeathLoc() {
        return deathLoc;
    }

    public void setDeathLoc(Location deathLoc) {
        this.deathLoc = deathLoc;
    }

    public HashMap<String, Location> getHomes() {
        return homes;
    }

    public Location getHome(String name){
        if (homes.containsKey(name)){
            return homes.get(name);
        }
        return null;
    }

    public boolean isSpy() {
        return spy;
    }

    public void setSpy(boolean spy) {
        this.spy = spy;
    }

    public Player getTpaRequester() {
        return tpaRequester;
    }

    public void setTpaRequester(Player tpaRequester) {
        this.tpaRequester = tpaRequester;
    }

    public List<String> getTpaBlocked() {
        return tpaBlocked;
    }
}
