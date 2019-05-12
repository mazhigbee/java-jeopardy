package com.mazlinhigbee.jeopardyapp.Models;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

/**
 * com.mazlinhigbee.jeopardyapp.Models
 * Created by: mhigbee
 * Date: 5/8/19 Time: 12:26 PM
 */
@Entity(tableName = "players")
public class Player {

    private static ArrayList<Player> allPlayers;

    @PrimaryKey(autoGenerate = true)
    private int id;

    @NonNull
    @ColumnInfo(name = "name")
    private String name;

    @NonNull
    @ColumnInfo(name = "is_playing")
    private boolean isPlaying;
    //ScoreHistory history

    public Player(String name,boolean isPlaying) {
        this.name = name;
        this.isPlaying = isPlaying;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isPlaying() {
        return isPlaying;
    }

    public void setPlaying(boolean playing) {
        isPlaying = playing;
    }

    public static ArrayList<Player> getAllPlayers() {
        if(Player.allPlayers == null) {
            allPlayers = new ArrayList<>();
        }
        return allPlayers;
    }

    public static void setAllPlayers(List<Player> allPlayers) {
        Player.allPlayers = new ArrayList<>(allPlayers);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
