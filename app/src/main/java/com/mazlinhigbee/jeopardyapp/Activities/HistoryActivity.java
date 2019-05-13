package com.mazlinhigbee.jeopardyapp.Activities;

import android.os.Bundle;

import com.mazlinhigbee.jeopardyapp.Models.Player;
import com.mazlinhigbee.jeopardyapp.Models.PlayerHistory;
import com.mazlinhigbee.jeopardyapp.Models.ViewModels.PlayerHistoryViewModel;
import com.mazlinhigbee.jeopardyapp.Models.ViewModels.PlayerViewModel;
import com.mazlinhigbee.jeopardyapp.R;
import com.mazlinhigbee.jeopardyapp.Views.Adapters.HistoryAdapter;
import com.mazlinhigbee.jeopardyapp.Views.Adapters.PlayerAdapter;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * com.mazlinhigbee.jeopardyapp.Activities
 * Created by: mhigbee
 * Date: 5/12/19 Time: 11:00 PM
 */
public class HistoryActivity extends AppCompatActivity {

    @BindView(R.id.activity_history_recycler)
    RecyclerView recyclerView;

    PlayerViewModel playerViewModel;
    PlayerHistoryViewModel playerHistoryViewModel;

    @Override
    public void onCreate(Bundle savedInstance) {
        super.onCreate(savedInstance);
        setContentView(R.layout.activity_history);
        ButterKnife.bind(this);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));


        playerViewModel = ViewModelProviders.of(this).get(PlayerViewModel.class);

        playerHistoryViewModel = ViewModelProviders.of(this).get(PlayerHistoryViewModel.class);

        playerHistoryViewModel.getAllPlayerHistory().observe(this,historyList -> {
            HistoryAdapter h = new HistoryAdapter(this,historyList);
            h.setViewModel(playerViewModel,this);
            recyclerView.setAdapter(h);
        });


    }
}
