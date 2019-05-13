package com.mazlinhigbee.jeopardyapp.Dao.Databases;

import android.content.Context;

import com.mazlinhigbee.jeopardyapp.Dao.PlayerHistoryDao;
import com.mazlinhigbee.jeopardyapp.Models.PlayerHistory;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

/**
 * com.mazlinhigbee.jeopardyapp.Dao
 * Created by: mhigbee
 * Date: 5/12/19 Time: 10:35 PM
 */
@Database(entities = {PlayerHistory.class}, version = 1, exportSchema = false)
public abstract class HistoryRoomDatabase extends RoomDatabase {
    public abstract PlayerHistoryDao historyDao();

    private static volatile HistoryRoomDatabase INSTANCE;

    public static HistoryRoomDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (PlayerRoomDatabase.class) {
                if (INSTANCE == null) {
                    // Create database here
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            HistoryRoomDatabase.class, "player_history_db")
                            .build();
                }
            }
        }
        return INSTANCE;
    }

}
