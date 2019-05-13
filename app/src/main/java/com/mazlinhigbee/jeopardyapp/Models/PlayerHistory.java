package com.mazlinhigbee.jeopardyapp.Models;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

/**
 * com.mazlinhigbee.jeopardyapp.Models
 * Created by: mhigbee
 * Date: 5/12/19 Time: 10:30 PM
 */
@Entity(tableName = "player_history")
public class PlayerHistory {
    @PrimaryKey(autoGenerate = true)
    private int id;

    @NonNull
    @ColumnInfo(name = "user_id")
    private int userId;

    @NonNull
    @ColumnInfo(name = "questions_correct")
    private int questionsCorrect;

    @NonNull
    @ColumnInfo(name = "questions_incorrect")
    private int questionIncorrect;

    @NonNull
    @ColumnInfo(name = "score")
    private int score;


    public PlayerHistory(@NonNull int userId, @NonNull int questionsCorrect, @NonNull int questionIncorrect, int score) {
        this.userId = userId;
        this.questionsCorrect = questionsCorrect;
        this.questionIncorrect = questionIncorrect;
        this.score = score;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @NonNull
    public int getUserId() {
        return userId;
    }

    public void setUserId(@NonNull int userId) {
        this.userId = userId;
    }

    @NonNull
    public int getQuestionsCorrect() {
        return questionsCorrect;
    }

    public void setQuestionsCorrect(@NonNull int questionsCorrect) {
        this.questionsCorrect = questionsCorrect;
    }

    @NonNull
    public int getQuestionIncorrect() {
        return questionIncorrect;
    }

    public void setQuestionIncorrect(@NonNull int questionIncorrect) {
        this.questionIncorrect = questionIncorrect;
    }

    @NonNull
    public int getScore() {
        return score;
    }

    public void setScore(@NonNull int score) {
        this.score = score;
    }
}
