package com.mazlinhigbee.jeopardyapp.Models.ViewModels;

import android.app.Application;

import com.mazlinhigbee.jeopardyapp.Dao.PlayerRepo;
import com.mazlinhigbee.jeopardyapp.Models.Player;

import java.util.List;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

/**
 * com.mazlinhigbee.jeopardyapp.Models.ViewModels
 * Created by: mhigbee
 * Date: 5/9/19 Time: 5:42 PM
 */
public class PlayerViewModel extends AndroidViewModel {
    private PlayerRepo mRepository;

    private LiveData<List<Player>> mAllWords;

    public PlayerViewModel(Application application) {
        super(application);
        mRepository = new PlayerRepo(application);
        mAllWords = mRepository.getAllPlayers();
    }

    public LiveData<List<Player>> getAllPlayers() {
        return mAllWords;
    }

    public LiveData<List<Player>> getAllActivePlayers() {
        return mRepository.getAllActivePlayers();
    }

    public void insert(Player word) {
        mRepository.insert(word);
    }
}
