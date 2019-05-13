package com.mazlinhigbee.jeopardyapp.Models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * com.mazlinhigbee.jeopardyapp.Models
 * Created by: mhigbee
 * Date: 4/21/19 Time: 10:06 PM
 */
public class Clue {
    Integer id;
    String answer;
    String question;
    Integer value;
    String airdate;

    @SerializedName("created_at")
    String createdAt;

    @SerializedName("updated_at")
    String updatedAt;

    @SerializedName("category_id")
    Integer categoryId;

    @SerializedName("game_id")
    Integer gameId;

    Category category;

    private boolean isAnswered;

//    public Clue(Integer id, String answer, String question, Integer value, String airdate, String createdAt, String updatedAt, Integer categoryId, Integer gameId, Category category) {
//        this.id = id;
//        this.answer = answer;
//        this.question = question;
//        this.value = value;
//        this.airdate = airdate;
//        this.createdAt = createdAt;
//        this.updatedAt = updatedAt;
//        this.categoryId = categoryId;
//        this.gameId = gameId;
//        this.category = category;
//    }
    public Clue() {
        //empty constructor
    }

    public Integer getId() {
        return id;
    }

    public String getAnswer() {
        return answer;
    }

    public String getQuestion() {
        return question;
    }

    public Integer getValue() {
        return value;
    }

    public String getAirdate() {
        return airdate;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public Integer getCategoryId() {
        return categoryId;
    }

    public Integer getGameId() {
        return gameId;
    }

    public Category getCategory() {
        return category;
    }

    public boolean isAnswered() {
        return isAnswered;
    }

    public void setAnswered(boolean answered) {
        isAnswered = answered;
    }
}
//{
//
//	          "id": 87879,
//            "answer": "a diamond",
//            "question": "Baseball players know the infield is shaped like this gem, which gives the field its name",
//            "value": 200,
//            "airdate": "2009-07-14T12:00:00.000Z",
//            "created_at": "2014-02-14T01:53:32.494Z",
//            "updated_at": "2014-02-14T01:53:32.494Z",
//            "category_id": 11541,
//            "game_id": null,
//            "invalid_count": null,
//            "category": {
//                  "id": 11541,
//                  "title": "named for their looks",
//                  "created_at": "2014-02-14T01:53:32.269Z",
//                  "updated_at": "2014-02-14T01:53:32.269Z",
//                  "clues_count": 5
//                  }
//            },