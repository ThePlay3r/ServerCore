package me.pljr.servercore.objects;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.bukkit.Location;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

@Setter
@Getter
@AllArgsConstructor
public class SCorePlayer {
    private final UUID uuid;
    private Location lastLoc;
    private Location deathLoc;
    private final HashMap<String, Location> homes;
    private boolean spy;
    private Player tpaRequester;
    private final List<String> tpaBlocked;
}
