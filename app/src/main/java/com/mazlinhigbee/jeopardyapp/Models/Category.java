package com.mazlinhigbee.jeopardyapp.Models;

import com.google.gson.annotations.SerializedName;

/**
 * com.mazlinhigbee.jeopardyapp.Models
 * Created by: mhigbee
 * Date: 4/21/19 Time: 10:03 PM
 */
public class Category {
    Integer id;
    String title;

    @SerializedName("clues_count")
    Integer cluesCount;

    @SerializedName("created_at")
    String createdAt;

    @SerializedName("updated_at")
    String updatedAt;


    public Category(Integer id, String title, Integer cluesCount) {
        this.id = id;
        this.title = title;
        this.cluesCount = cluesCount;
    }

    public Integer getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public Integer getCluesCount() {
        return cluesCount;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }
}
