package com.mazlinhigbee.jeopardyapp.Dao;

import com.mazlinhigbee.jeopardyapp.Models.PlayerHistory;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

/**
 * com.mazlinhigbee.jeopardyapp.Dao
 * Created by: mhigbee
 * Date: 5/12/19 Time: 10:37 PM
 */
@Dao
public interface PlayerHistoryDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(PlayerHistory playerHistory);

    @Query("select * from player_history order by id asc")
    LiveData<List<PlayerHistory>> getAllPlayerHistory();
}
