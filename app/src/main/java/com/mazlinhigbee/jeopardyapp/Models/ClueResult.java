package com.mazlinhigbee.jeopardyapp.Models;

/**
 * com.mazlinhigbee.jeopardyapp.Models
 * Created by: mhigbee
 * Date: 5/12/19 Time: 4:57 PM
 */
public class ClueResult {

    boolean anwseredCorrectly;
    Player resultPlayer;
    Clue clue;

    public ClueResult(boolean anwseredCorrectly, Player resultPlayer, Clue clue) {
        this.anwseredCorrectly = anwseredCorrectly;
        this.resultPlayer = resultPlayer;
        this.clue = clue;
    }
}
