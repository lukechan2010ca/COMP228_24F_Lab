package com.example.chiwochan_comp228lab5;

import java.sql.Date;
// This class is used to store the information of a player and the game they played.
public class PlayerAndGameInfo {
    private final int playerID;
    private final String firstName;
    private final String lastName;
    private final String address;
    private final String postalCode;
    private final String province;
    private final String phoneNumber;
    private final String gameTitle;
    private final Date datePlayed;
    private final int score;

    // constructor
    public PlayerAndGameInfo(int playerID, String firstName, String lastName, String address, String postalCode, String province, String phoneNumber, String gameTitle, Date datePlayed, int score) {
        this.playerID = playerID;
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.postalCode = postalCode;
        this.province = province;
        this.phoneNumber = phoneNumber;
        this.gameTitle = gameTitle;
        this.datePlayed = datePlayed;
        this.score = score;
    }

    // getters
    public int getPlayerID() {
        return playerID;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getAddress() {
        return address;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public String getProvince() {
        return province;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getGameTitle() {
        return gameTitle;
    }

    public Date getDatePlayed() {
        return datePlayed;
    }

    public int getScore() {
        return score;
    }
}
