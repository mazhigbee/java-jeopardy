package com.mazlinhigbee.jeopardyapp.Models.ViewModels;

import android.app.Application;

import com.mazlinhigbee.jeopardyapp.Dao.Repository.PlayerHistoryRepo;
import com.mazlinhigbee.jeopardyapp.Models.PlayerHistory;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

/**
 * com.mazlinhigbee.jeopardyapp.Models.ViewModels
 * Created by: mhigbee
 * Date: 5/12/19 Time: 10:40 PM
 */
public class PlayerHistoryViewModel extends AndroidViewModel {
    private PlayerHistoryRepo mRepo;

    public PlayerHistoryViewModel(@NonNull Application application) {
        super(application);
        mRepo = new PlayerHistoryRepo(application);
    }

    public LiveData<List<PlayerHistory>> getAllPlayerHistory() {
        return mRepo.getAllPlayerHistory();
    }

    public void insert(PlayerHistory... playerHistory) {
        mRepo.insert(playerHistory);
    }
}
