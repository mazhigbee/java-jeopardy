package com.mazlinhigbee.jeopardyapp;

import android.app.Activity;

import com.mazlinhigbee.jeopardyapp.Models.Category;
import com.mazlinhigbee.jeopardyapp.Models.Clue;
import com.mazlinhigbee.jeopardyapp.Models.GameState;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * com.mazlinhigbee.jeopardyapp
 * Created by: mhigbee
 * Date: 4/21/19 Time: 10:17 PM
 */
public class JeopardyApp {
    public static Category CURRENT_CATEGORY = new Category(11524,"transformed food",5);
    private static List<Category> categories;
    public static HashMap<Integer,List<Clue>> clueMap = new HashMap<>();
    public static GameState curGameState;
    public static MainActivity mainActivity;


    public static List<Category> getCategories() {
        if(categories == null) {
            categories = new ArrayList<>();
        }
        return categories;
    }

    public static void setCategories(List<Category> categories) {
        JeopardyApp.categories = categories;
    }

    /**
     * Return a given category via its name..
     * @param catName
     * @return
     */
    public static Category getCategoryFromStringName(String catName) {
        if(categories != null) {
            for(Category c : categories) {
                if(c.getTitle().toLowerCase().equals(catName.toLowerCase())) {
                    return c;
                }
            }
        }
        return null;
    }
}
