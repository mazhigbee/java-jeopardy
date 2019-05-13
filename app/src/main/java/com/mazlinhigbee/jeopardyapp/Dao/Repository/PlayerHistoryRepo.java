package com.mazlinhigbee.jeopardyapp.Dao.Repository;

import android.app.Application;
import android.os.AsyncTask;

import com.mazlinhigbee.jeopardyapp.Dao.Databases.HistoryRoomDatabase;
import com.mazlinhigbee.jeopardyapp.Dao.PlayerDao;
import com.mazlinhigbee.jeopardyapp.Dao.PlayerHistoryDao;
import com.mazlinhigbee.jeopardyapp.Models.Player;
import com.mazlinhigbee.jeopardyapp.Models.PlayerHistory;

import java.util.List;

import androidx.lifecycle.LiveData;

/**
 * com.mazlinhigbee.jeopardyapp.Dao
 * Created by: mhigbee
 * Date: 5/12/19 Time: 10:39 PM
 */
public class PlayerHistoryRepo {
    private PlayerHistoryDao historyDao;

    public PlayerHistoryRepo(Application application) {
        HistoryRoomDatabase db = HistoryRoomDatabase.getDatabase(application);
        historyDao = db.historyDao();
    }

    public LiveData<List<PlayerHistory>> getAllPlayerHistory() {
        return historyDao.getAllPlayerHistory();
    }


    public void insert(PlayerHistory... playerHistory) {
        new insertAsyncTask(historyDao).execute(playerHistory);
    }

    private static class insertAsyncTask extends AsyncTask<PlayerHistory, Void, Void> {

        private PlayerHistoryDao mAsyncTaskDao;

        insertAsyncTask(PlayerHistoryDao dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final PlayerHistory... params) {
            mAsyncTaskDao.insert(params[0]);
            return null;
        }
    }
}
