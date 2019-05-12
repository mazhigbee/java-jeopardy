package com.mazlinhigbee.jeopardyapp.Models;

import java.util.HashMap;
import java.util.List;

/**
 * com.mazlinhigbee.jeopardyapp.Models
 * Created by: mhigbee
 * Date: 5/12/19 Time: 4:55 PM
 * Holds the state of a current game!
 */
public class GameState {
    private List<Category> categories;
    private List<Player> players;

    private HashMap<Integer,List<Clue>> categoryClueMap; //maps a category ID to a list of clues
    private HashMap<Player,List<ClueResult>> results; //maps a player to a list of their clue results

}
