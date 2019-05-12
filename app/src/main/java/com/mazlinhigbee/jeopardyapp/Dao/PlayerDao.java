package com.mazlinhigbee.jeopardyapp.Dao;

import com.mazlinhigbee.jeopardyapp.Models.Player;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

/**
 * com.mazlinhigbee.jeopardyapp.Dao
 * Created by: mhigbee
 * Date: 5/9/19 Time: 5:25 PM
 */
@Dao
public interface PlayerDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(Player player);

    @Query("DELETE FROM players")
    void deleteAll();

    @Query("SELECT * from players ORDER BY name ASC")
    LiveData<List<Player>> getAllPlayers();

    @Query("select * from players where is_playing = :playing")
    LiveData<List<Player>> getAllActivePlayers(boolean playing);
}
