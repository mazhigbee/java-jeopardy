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

    private HashMap<Integer, List<Clue>> categoryClueMap; //maps a category ID to a list of clues
    private HashMap<Player, List<ClueResult>> results; //maps a player to a list of their clue results

    public GameState(List<Category> categories, List<Player> players) {
        this.categories = categories;
        this.players = players;
    }

    public GameState(List<Category> categories, List<Player> players, HashMap<Integer, List<Clue>> categoryClueMap, HashMap<Player, List<ClueResult>> results) {
        this.categories = categories;
        this.players = players;
        this.categoryClueMap = categoryClueMap;
        this.results = results;
    }

    public List<Category> getCategories() {
        return categories;
    }

    public void setCategories(List<Category> categories) {
        this.categories = categories;
    }

    public List<Player> getPlayers() {
        return players;
    }

    public void setPlayers(List<Player> players) {
        this.players = players;
    }

    public HashMap<Integer, List<Clue>> getCategoryClueMap() {
        return categoryClueMap;
    }

    public void setCategoryClueMap(HashMap<Integer, List<Clue>> categoryClueMap) {
        this.categoryClueMap = categoryClueMap;
    }

    public HashMap<Player, List<ClueResult>> getResults() {
        return results;
    }

    public void setResults(HashMap<Player, List<ClueResult>> results) {
        this.results = results;
    }
}
