package me.pljr.servercore.managers;

import lombok.AllArgsConstructor;
import me.pljr.pljrapispigot.database.DataSource;
import me.pljr.servercore.objects.CorePlayer;
import org.bukkit.Bukkit;
import org.bukkit.Location;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

@AllArgsConstructor
public class QueryManager {
    private final DataSource dataSource;

    public CorePlayer loadPlayer(UUID uuid){
        Location lastLoc = null;
        Location deathLoc = null;
        HashMap<String, Location> homes = new HashMap<>();
        boolean spy = false;
        List<String> tpaBlocked = new ArrayList<>();
        try {
            Connection homesConnection = dataSource.getConnection();
            PreparedStatement homesStatement = homesConnection.prepareStatement(
                    "SELECT * FROM servercore_homes WHERE uuid=?"
            );
            homesStatement.setString(1, uuid.toString());
            ResultSet homesResult = homesStatement.executeQuery();
            while (homesResult.next()){
                homes.put(homesResult.getString("name"), new Location(
                        Bukkit.getWorld(homesResult.getString("loc_world")),
                        homesResult.getDouble("loc_x"),
                        homesResult.getDouble("loc_y"),
                        homesResult.getDouble("loc_z"),
                        homesResult.getFloat("loc_yaw"),
                        homesResult.getFloat("loc_pitch")
                ));
            }
            dataSource.close(homesConnection, homesStatement, homesResult);

            Connection locationsConnection = dataSource.getConnection();
            PreparedStatement locationsStatement = locationsConnection.prepareStatement(
                    "SELECT * FROM servercore_locations WHERE uuid=?"
            );
            locationsStatement.setString(1, uuid.toString());
            ResultSet locationsResult = locationsStatement.executeQuery();
            if (locationsResult.next()){
                if (Bukkit.getWorld(locationsResult.getString("last_world"))  != null){
                    lastLoc = new Location(
                            Bukkit.getWorld(locationsResult.getString("last_world")),
                            locationsResult.getDouble("last_x"),
                            locationsResult.getDouble("last_y"),
                            locationsResult.getDouble("last_z"),
                            locationsResult.getFloat("last_yaw"),
                            locationsResult.getFloat("last_pitch")
                    );
                }
                if (Bukkit.getWorld(locationsResult.getString("death_world")) != null){
                    deathLoc = new Location(
                            Bukkit.getWorld(locationsResult.getString("death_world")),
                            locationsResult.getDouble("death_x"),
                            locationsResult.getDouble("death_y"),
                            locationsResult.getDouble("death_z"),
                            locationsResult.getFloat("death_yaw"),
                            locationsResult.getFloat("death_pitch")
                    );
                }

            }
            dataSource.close(locationsConnection, locationsStatement, locationsResult);

            Connection playersConnection = dataSource.getConnection();
            PreparedStatement playersStatement = playersConnection.prepareStatement(
                    "SELECT * FROM servercore_players WHERE uuid=?"
            );
            playersStatement.setString(1, uuid.toString());
            ResultSet playersResult = playersStatement.executeQuery();
            if (playersResult.next()){
                spy = playersResult.getBoolean("spy");
            }
            dataSource.close(playersConnection, playersStatement, playersResult);

            Connection tpaBlockedConnection = dataSource.getConnection();
            PreparedStatement tpaBlockedStatement = tpaBlockedConnection.prepareStatement(
                    "SELECT * FROM servercore_tpaBlocked WHERE uuid=?"
            );
            tpaBlockedStatement.setString(1, uuid.toString());
            ResultSet tpaBlockedResult = tpaBlockedStatement.executeQuery();
            while (tpaBlockedResult.next()){
                tpaBlocked.add(tpaBlockedResult.getString("blocked"));
            }
            dataSource.close(tpaBlockedConnection, tpaBlockedStatement, tpaBlockedResult);
        }catch (SQLException e){
            e.printStackTrace();
        }
        return new CorePlayer(uuid, lastLoc, deathLoc, homes, spy, null, tpaBlocked);
    }

    public void savePlayer(CorePlayer corePlayer){
        try {
            UUID uuid = corePlayer.getUuid();
            Connection homesDeleteConnection = dataSource.getConnection();
            PreparedStatement homesDeleteStatement = homesDeleteConnection.prepareStatement(
                    "DELETE FROM servercore_homes WHERE uuid=?"
            );
            homesDeleteStatement.setString(1, uuid.toString());
            homesDeleteStatement.executeUpdate();
            dataSource.close(homesDeleteConnection, homesDeleteStatement, null);

            for (Map.Entry<String, Location> entry : corePlayer.getHomes().entrySet()){
                Location homeLoc = entry.getValue();

                Connection homesInsertConnection = dataSource.getConnection();
                PreparedStatement homesInsertStatement = homesInsertConnection.prepareStatement(
                        "INSERT INTO servercore_homes VALUES (?,?,?,?,?,?,?,?)"
                );
                homesInsertStatement.setString(1, uuid.toString());
                homesInsertStatement.setString(2, entry.getKey());
                homesInsertStatement.setString(3, homeLoc.getWorld().getName());
                homesInsertStatement.setDouble(4, homeLoc.getX());
                homesInsertStatement.setDouble(5, homeLoc.getY());
                homesInsertStatement.setDouble(6, homeLoc.getZ());
                homesInsertStatement.setFloat(7, homeLoc.getYaw());
                homesInsertStatement.setFloat(8, homeLoc.getPitch());
                homesInsertStatement.executeUpdate();
                dataSource.close(homesInsertConnection, homesInsertStatement, null);
            }

            Location lastLoc = corePlayer.getLastLoc();
            Location deathLoc = corePlayer.getDeathLoc();
            Connection locationsReplaceConnection = dataSource.getConnection();
            PreparedStatement locationsReplaceStatement = locationsReplaceConnection.prepareStatement(
                    "REPLACE INTO servercore_locations VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?)"
            );
            locationsReplaceStatement.setString(1, uuid.toString());
            if (lastLoc != null && lastLoc.getWorld() != null){
                saveLoc(lastLoc, locationsReplaceStatement);
            }else{
                locationsReplaceStatement.setString(2, "");
                locationsReplaceStatement.setDouble(3, 0);
                locationsReplaceStatement.setDouble(4, 0);
                locationsReplaceStatement.setDouble(5, 0);
                locationsReplaceStatement.setFloat(6, 0);
                locationsReplaceStatement.setFloat(7, 0);
            }

            if (deathLoc != null && deathLoc.getWorld() != null){
                locationsReplaceStatement.setString(8, deathLoc.getWorld().getName());
                locationsReplaceStatement.setDouble(9, deathLoc.getX());
                locationsReplaceStatement.setDouble(10, deathLoc.getY());
                locationsReplaceStatement.setDouble(11, deathLoc.getZ());
                locationsReplaceStatement.setFloat(12, deathLoc.getYaw());
                locationsReplaceStatement.setFloat(13, deathLoc.getPitch());
            }else{
                locationsReplaceStatement.setString(8, "");
                locationsReplaceStatement.setDouble(9, 0);
                locationsReplaceStatement.setDouble(10, 0);
                locationsReplaceStatement.setDouble(11, 0);
                locationsReplaceStatement.setFloat(12, 0);
                locationsReplaceStatement.setFloat(13, 0);
            }
            locationsReplaceStatement.executeUpdate();
            dataSource.close(locationsReplaceConnection, locationsReplaceStatement, null);

            boolean spy = corePlayer.isSpy();
            Connection playersReplaceConnection = dataSource.getConnection();
            PreparedStatement playersReplaceStatement = playersReplaceConnection.prepareStatement(
                    "REPLACE INTO servercore_players VALUES (?,?)"
            );
            playersReplaceStatement.setString(1, uuid.toString());
            playersReplaceStatement.setBoolean(2, spy);
            dataSource.close(playersReplaceConnection, playersReplaceStatement, null);

            Connection tpaBlockedDelete = dataSource.getConnection();
            PreparedStatement tpaBlockedDeleteStatement = tpaBlockedDelete.prepareStatement(
                    "DELETE FROM servercore_tpaBlocked WHERE uuid=?"
            );
            tpaBlockedDeleteStatement.setString(1, uuid.toString());
            tpaBlockedDeleteStatement.executeUpdate();
            dataSource.close(tpaBlockedDelete, tpaBlockedDeleteStatement, null);

            List<String> tpaBlocked = corePlayer.getTpaBlocked();
            for (String blocked : tpaBlocked){
                Connection tpaBlockedConnection = dataSource.getConnection();
                PreparedStatement tpaBlockedStatement = tpaBlockedConnection.prepareStatement(
                        "INSERT INTO servercore_tpaBlocked VALUES (?,?)"
                );
                tpaBlockedStatement.setString(1, uuid.toString());
                tpaBlockedStatement.setString(2, blocked);
                tpaBlockedStatement.executeUpdate();
                dataSource.close(tpaBlockedConnection, tpaBlockedStatement, null);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    public HashMap<String, Location> loadWarps(){
        HashMap<String, Location> warps = new HashMap<>();
        try {
            Connection connection = dataSource.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "SELECT * FROM servercore_warps"
            );
            ResultSet results = preparedStatement.executeQuery();
            while (results.next()){
                warps.put(results.getString("name"), new Location(
                        Bukkit.getWorld(results.getString("loc_world")),
                        results.getDouble("loc_x"),
                        results.getDouble("loc_y"),
                        results.getDouble("loc_z"),
                        results.getFloat("loc_yaw"),
                        results.getFloat("loc_pitch")
                ));
            }
            dataSource.close(connection, preparedStatement, results);
        }catch (SQLException e){
            e.printStackTrace();
        }
        return warps;
    }

    public void saveWarps(HashMap<String, Location> warps){
        try {
            Connection delete = dataSource.getConnection();
            PreparedStatement deleteStatement = delete.prepareStatement(
                    "DELETE FROM servercore_warps"
            );
            deleteStatement.executeUpdate();
            dataSource.close(delete, deleteStatement, null);

            for (Map.Entry<String, Location> entry : warps.entrySet()){
                Location location = entry.getValue();

                Connection connection = dataSource.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(
                        "INSERT INTO servercore_warps VALUES (?,?,?,?,?,?,?)"
                );
                preparedStatement.setString(1, entry.getKey());
                saveLoc(location, preparedStatement);
                preparedStatement.executeUpdate();
                dataSource.close(connection, preparedStatement, null);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    private void saveLoc(Location location, PreparedStatement preparedStatement) throws SQLException {
        preparedStatement.setString(2, location.getWorld().getName());
        preparedStatement.setDouble(3, location.getX());
        preparedStatement.setDouble(4, location.getY());
        preparedStatement.setDouble(5, location.getZ());
        preparedStatement.setFloat(6, location.getYaw());
        preparedStatement.setFloat(7, location.getPitch());
    }

    public void setupTables(){
        try {
            Connection homes = dataSource.getConnection();
            PreparedStatement homesStatement = homes.prepareStatement(
                    "CREATE TABLE IF NOT EXISTS servercore_homes (" +
                            "uuid char(36) NOT NULL," +
                            "name varchar(255) NOT NULL," +
                            "loc_world varchar(255) NOT NULL," +
                            "loc_x double NOT NULL," +
                            "loc_y double NOT NULL," +
                            "loc_z double NOT NULL," +
                            "loc_yaw float NOT NULL," +
                            "loc_pitch float NOT NULL);"
            );
            homesStatement.executeUpdate();
            dataSource.close(homes, homesStatement, null);

            Connection locations = dataSource.getConnection();
            PreparedStatement locationsStatement = locations.prepareStatement(
                    "CREATE TABLE IF NOT EXISTS servercore_locations (" +
                            "uuid char(36) NOT NULL PRIMARY KEY," +
                            "last_world varchar(255) NOT NULL," +
                            "last_x double NOT NULL," +
                            "last_y double NOT NULL," +
                            "last_z double NOT NULL," +
                            "last_yaw float NOT NULL," +
                            "last_pitch float NOT NULL," +
                            "death_world varchar(255) NOT NULL," +
                            "death_x double NOT NULL," +
                            "death_y double NOT NULL," +
                            "death_z double NOT NULL," +
                            "death_yaw float NOT NULL," +
                            "death_pitch float);"
            );
            locationsStatement.executeUpdate();
            dataSource.close(locations, locationsStatement, null);

            Connection warps = dataSource.getConnection();
            PreparedStatement warpsStatement = warps.prepareStatement(
                    "CREATE TABLE IF NOT EXISTS servercore_warps (" +
                            "name varchar(255) NOT NULL PRIMARY KEY," +
                            "loc_world varchar(255) NOT NULL," +
                            "loc_x double NOT NULL," +
                            "loc_y double NOT NULL," +
                            "loc_z double NOT NULL," +
                            "loc_yaw double NOT NULL," +
                            "loc_pitch double NOT NULL);"
            );
            warpsStatement.executeUpdate();
            dataSource.close(warps, warpsStatement, null);

            Connection players = dataSource.getConnection();
            PreparedStatement playersStatement = players.prepareStatement(
                    "CREATE TABLE IF NOT EXISTS servercore_players (" +
                            "uuid char(36) NOT NULL PRIMARY KEY," +
                            "spy tinyint(1) NOT NULL);"
            );
            playersStatement.executeUpdate();
            dataSource.close(players, playersStatement, null);

            Connection tpaBlocked = dataSource.getConnection();
            PreparedStatement tpaBlockedStatement = tpaBlocked.prepareStatement(
                    "CREATE TABLE IF NOT EXISTS servercore_tpaBlocked (" +
                            "uuid char(36) NOT NULL," +
                            "blocked varchar(16) NOT NULL);"
            );
            tpaBlockedStatement.executeUpdate();
            dataSource.close(tpaBlocked, tpaBlockedStatement, null);
        }catch (SQLException e){
            e.printStackTrace();
        }
    }
}
