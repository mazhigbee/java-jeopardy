package com.mazlinhigbee.jeopardyapp.Dao.Databases;

import android.content.Context;

import com.mazlinhigbee.jeopardyapp.Dao.PlayerDao;
import com.mazlinhigbee.jeopardyapp.Models.Player;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

/**
 * com.mazlinhigbee.jeopardyapp.Dao
 * Created by: mhigbee
 * Date: 5/9/19 Time: 5:28 PM
 */
@Database(entities = {Player.class}, version = 1, exportSchema = false)
public abstract class PlayerRoomDatabase extends RoomDatabase {
    public abstract PlayerDao playerDao();

    private static volatile PlayerRoomDatabase INSTANCE;

    public static PlayerRoomDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (PlayerRoomDatabase.class) {
                if (INSTANCE == null) {
                    // Create database here
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            PlayerRoomDatabase.class, "player_db")
                            .build();
                }
            }
        }
        return INSTANCE;
    }

}
