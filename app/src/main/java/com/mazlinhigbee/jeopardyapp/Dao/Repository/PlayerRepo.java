package com.mazlinhigbee.jeopardyapp.Dao.Repository;

import android.app.Application;
import android.os.AsyncTask;

import com.mazlinhigbee.jeopardyapp.Dao.PlayerDao;
import com.mazlinhigbee.jeopardyapp.Dao.Databases.PlayerRoomDatabase;
import com.mazlinhigbee.jeopardyapp.Models.Player;

import java.util.List;

import androidx.lifecycle.LiveData;

/**
 * com.mazlinhigbee.jeopardyapp.Dao
 * Created by: mhigbee
 * Date: 5/9/19 Time: 5:38 PM
 */
public class PlayerRepo {
    private PlayerDao playerDao;
    private LiveData<List<Player>> allPlayers;

    public PlayerRepo(Application application) {
        PlayerRoomDatabase db = PlayerRoomDatabase.getDatabase(application);
        playerDao = db.playerDao();
        allPlayers = playerDao.getAllPlayers();
    }

    public LiveData<List<Player>> getAllPlayers() {
        return allPlayers;
    }

    public LiveData<List<Player>> getAllActivePlayers() {
        return playerDao.getAllActivePlayers(true);
    }

    public void insert(Player player) {
        new insertAsyncTask(playerDao).execute(player);
    }

    private static class insertAsyncTask extends AsyncTask<Player, Void, Void> {

        private PlayerDao mAsyncTaskDao;

        insertAsyncTask(PlayerDao dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final Player... params) {
            mAsyncTaskDao.insert(params[0]);
            return null;
        }
    }
}
