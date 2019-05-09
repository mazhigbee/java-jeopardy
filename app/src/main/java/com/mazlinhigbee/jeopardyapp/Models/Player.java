package com.mazlinhigbee.jeopardyapp.Models;

import android.widget.ArrayAdapter;

import java.util.ArrayList;

/**
 * com.mazlinhigbee.jeopardyapp.Models
 * Created by: mhigbee
 * Date: 5/8/19 Time: 12:26 PM
 */
public class Player {

    private static ArrayList<Player> allPlayers;

    private String name;
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

    public static void setAllPlayers(ArrayList<Player> allPlayers) {
        Player.allPlayers = allPlayers;
    }
}
