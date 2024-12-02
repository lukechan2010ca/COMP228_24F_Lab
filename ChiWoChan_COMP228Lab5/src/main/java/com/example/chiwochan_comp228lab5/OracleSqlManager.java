package com.example.chiwochan_comp228lab5;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OracleSqlManager {
    private final String url;
    private final String user;
    private final String password;

    // constructor to initialize the database connection details
    public OracleSqlManager(String url, String user, String password) {
        this.url = "jdbc:oracle:thin:@199.212.26.208:1521:SQLD";
        this.user = "COMP228_F24_soh_4";
        this.password = "Comp228luke";
        // String url = "jdbc:oracle:thin:@oracle1.centennialcollege.ca.208:1521:SQLD"; //school
    }

    // a method to connect to the database and return a connection object
    private Connection connect() throws SQLException {
        return DriverManager.getConnection(url, user, password);
    }

    // a method to add a new record to the database
    public void addRecord(int playerID, String firstName, String lastName, String address, String postalCode, String province, String phoneNumber, String gameTitle, int score, String datePlayed) {
        String addPlayerQuery = "INSERT INTO chiwo_chan_player (player_id, first_name, last_name, address, postal_code, province, phone_number) VALUES (?, ?, ?, ?, ?, ?, ?)";
        String getGameIdQuery = "SELECT game_id FROM chiwo_chan_game WHERE game_title = ?";
        String addGameQuery = "INSERT INTO chiwo_chan_game (game_id, game_title) VALUES (chiwo_chan_game_id_seq.NEXTVAL, ?)";
        String getNewGameIdQuery = "SELECT game_id FROM chiwo_chan_game WHERE game_title = ?";
        String addPlayerGameQuery = "INSERT INTO chiwo_chan_player_and_game (player_game_id, game_id, player_id, playing_date, score) VALUES (player_and_game_id_seq.NEXTVAL, ?, ?, TO_DATE(?, 'YYYY-MM-DD'), ?)";

        // create connection and prepare statements
        try (Connection conn = connect();
             // create statement objects
             PreparedStatement addPlayerStmt = conn.prepareStatement(addPlayerQuery);
             PreparedStatement getGameIdStmt = conn.prepareStatement(getGameIdQuery);
             PreparedStatement addGameStmt = conn.prepareStatement(addGameQuery);
             PreparedStatement getNewGameIdStmt = conn.prepareStatement(getNewGameIdQuery);
             PreparedStatement addPlayerGameStmt = conn.prepareStatement(addPlayerGameQuery)) {
            // add player record
            addPlayerStmt.setInt(1, playerID);
            addPlayerStmt.setString(2, firstName);
            addPlayerStmt.setString(3, lastName);
            addPlayerStmt.setString(4, address);
            addPlayerStmt.setString(5, postalCode);
            addPlayerStmt.setString(6, province);
            addPlayerStmt.setString(7, phoneNumber);
            addPlayerStmt.executeUpdate();

            // get game_id from game_title
            getGameIdStmt.setString(1, gameTitle);
            ResultSet rs = getGameIdStmt.executeQuery();
            int gameId;
            if (rs.next()) {
                // Game title exists, retrieve its game_id
                gameId = rs.getInt("game_id");
            } else {
                // Game title doesn't exist, add it
                addGameStmt.setString(1, gameTitle);
                addGameStmt.executeUpdate();

                // Retrieve the newly inserted game_id
                getNewGameIdStmt.setString(1, gameTitle);
                ResultSet newGameIdRs = getNewGameIdStmt.executeQuery();
                if (newGameIdRs.next()) {
                    gameId = newGameIdRs.getInt("game_id");
                } else {
                    throw new SQLException("Failed to retrieve game_id for newly added game.");
                }
            }

            // add player_game record
            addPlayerGameStmt.setInt(1, gameId);
            addPlayerGameStmt.setInt(2, playerID);
            addPlayerGameStmt.setString(3, datePlayed);
            addPlayerGameStmt.setInt(4, score);
            addPlayerGameStmt.executeUpdate();
            System.out.println("Record added successfully.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // a method to get all records from the database and return a list of PlayerAndGameInfo objects
    public List<PlayerAndGameInfo> getAllRecords() {
        String getAllRecordsQuery = "SELECT p.player_id, p.first_name, p.last_name, p.address, p.postal_code, p.province, p.phone_number, g.game_title, pg.playing_date, pg.score FROM chiwo_chan_player p JOIN chiwo_chan_player_and_game pg ON p.player_id = pg.player_id JOIN chiwo_chan_game g ON g.game_id = pg.game_id order by p.player_id";
        // execute query and get result set
        List<PlayerAndGameInfo> list = new ArrayList<>();
        // create connection and statement object
        try (Connection conn = connect();
             Statement stmt = conn.createStatement()) {
            ResultSet rs = stmt.executeQuery(getAllRecordsQuery);
            while (rs.next()) {
                PlayerAndGameInfo playerAndGameInfo = new PlayerAndGameInfo(
                        rs.getInt("player_id"),
                        rs.getString("first_name"),
                        rs.getString("last_name"),
                        rs.getString("address"),
                        rs.getString("postal_code"),
                        rs.getString("province"),
                        rs.getString("phone_number"),
                        rs.getString("game_title"),
                        rs.getDate("playing_date"),
                        rs.getInt("score")
                );
                list.add(playerAndGameInfo);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    // a method to get all records from the database for a specific player
    public List<PlayerAndGameInfo> getSelectedRecords (int playerID) {
        String getRecordsByIDQuery = "SELECT p.player_id, p.first_name, p.last_name, p.address, p.postal_code, p.province, p.phone_number, g.game_title, pg.playing_date, pg.score FROM chiwo_chan_player p JOIN chiwo_chan_player_and_game pg ON p.player_id = pg.player_id JOIN chiwo_chan_game g ON g.game_id = pg.game_id WHERE p.player_id = ?";

        // execute query and get result set
        List<PlayerAndGameInfo> list = new ArrayList<>();
        // create connection and statement object
        try (Connection conn = connect();
             PreparedStatement stmt = conn.prepareStatement(getRecordsByIDQuery)) {
            stmt.setInt(1, playerID);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                PlayerAndGameInfo playerAndGameInfo = new PlayerAndGameInfo(
                        rs.getInt("player_id"),
                        rs.getString("first_name"),
                        rs.getString("last_name"),
                        rs.getString("address"),
                        rs.getString("postal_code"),
                        rs.getString("province"),
                        rs.getString("phone_number"),
                        rs.getString("game_title"),
                        rs.getDate("playing_date"),
                        rs.getInt("score")
                );
                list.add(playerAndGameInfo);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    // a method to update a player record in the database
    public void updatePlayer(int playerID, String firstName, String lastName, String address, String postalCode, String province, String phoneNumber) {

        StringBuilder queryBuilder = new StringBuilder("UPDATE chiwo_chan_player SET ");
        List<Object> parameters = new ArrayList<>();
        boolean hasFieldToUpdate = false;

        if (firstName != null) {
            queryBuilder.append("first_name = ?, ");
            parameters.add(firstName);
            hasFieldToUpdate = true;
        }
        if (lastName != null) {
            queryBuilder.append("last_name = ?, ");
            parameters.add(lastName);
            hasFieldToUpdate = true;
        }
        if (address != null) {
            queryBuilder.append("address = ?, ");
            parameters.add(address);
            hasFieldToUpdate = true;
        }
        if (postalCode != null) {
            queryBuilder.append("postal_code = ?, ");
            parameters.add(postalCode);
            hasFieldToUpdate = true;
        }
        if (province != null) {
            queryBuilder.append("province = ?, ");
            parameters.add(province);
            hasFieldToUpdate = true;
        }
        if (phoneNumber != null) {
            queryBuilder.append("phone_number = ?, ");
            parameters.add(phoneNumber);
            hasFieldToUpdate = true;
        }

        if (!hasFieldToUpdate) {
            throw new IllegalArgumentException("No player information provided for update.");
        }
        // Remove the last comma and space
        queryBuilder.setLength(queryBuilder.length() - 2);
        queryBuilder.append(" WHERE player_id = ?");
        parameters.add(playerID);

        // create variables for prepared statement
        String updatePlayerQuery = queryBuilder.toString();

        // execute query and get result set
        // create connection and statement object
        try (Connection conn = connect();
             PreparedStatement stmt = conn.prepareStatement(updatePlayerQuery)) {
            for (int i = 0; i < parameters.size(); i++) {
                stmt.setObject(i+1, parameters.get(i));
            }
            stmt.executeUpdate();
            System.out.println("Player record updated successfully.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
