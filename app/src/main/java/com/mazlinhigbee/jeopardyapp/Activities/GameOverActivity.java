package com.mazlinhigbee.jeopardyapp.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.mazlinhigbee.jeopardyapp.JeopardyApp;
import com.mazlinhigbee.jeopardyapp.MainActivity;
import com.mazlinhigbee.jeopardyapp.Models.ClueResult;
import com.mazlinhigbee.jeopardyapp.Models.Player;
import com.mazlinhigbee.jeopardyapp.Models.PlayerHistory;
import com.mazlinhigbee.jeopardyapp.Models.ViewModels.PlayerHistoryViewModel;
import com.mazlinhigbee.jeopardyapp.Models.ViewModels.PlayerViewModel;
import com.mazlinhigbee.jeopardyapp.R;
import com.mazlinhigbee.jeopardyapp.Views.Adapters.PlayerAdapter;
import com.mazlinhigbee.jeopardyapp.Views.Adapters.ScoreAdapter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * com.mazlinhigbee.jeopardyapp.Activities
 * Created by: mhigbee
 * Date: 5/12/19 Time: 8:12 PM
 */
public class GameOverActivity extends AppCompatActivity {

    @BindView(R.id.activity_done_player_score_recycler)
    RecyclerView recyclerView;

    @BindView(R.id.activity_done_done)
    TextView btnDone;

    @Override
    public void onCreate(Bundle savedInstance) {
        super.onCreate(savedInstance);
        setContentView(R.layout.activity_done);
        ButterKnife.bind(this);

        initTopScore();

        btnDone.setOnClickListener(v -> {
            startActivity(new Intent(this,MainActivity.class));
        });

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(new ScoreAdapter(this,JeopardyApp.curGameState));

    }

    private void initTopScore() {
        HashMap<Player, PlayerHistory> scores = new HashMap<>();

        for (Player p : JeopardyApp.curGameState.getPlayers()) {
            int totalWinning = 0;
            int questionsRight = 0;
            int questionsWrong = 0;

            if (JeopardyApp.curGameState.getResults() != null && JeopardyApp.curGameState.getResults().get(p) != null) {
                for (ClueResult c : JeopardyApp.curGameState.getResults().get(p)) {
                    if (c.isAnwseredCorrectly() && c.getClue().getValue() != null) {
                        totalWinning += c.getClue().getValue();
                        questionsRight++;
                    } else {
                        totalWinning -= c.getClue().getValue();
                        questionsWrong++;
                    }
                }
                scores.put(p,new PlayerHistory(p.getId(),questionsRight,questionsWrong,totalWinning));
            }
        }

        PlayerHistoryViewModel viewModel = ViewModelProviders.of(this).get(PlayerHistoryViewModel.class);

       for(PlayerHistory p : scores.values()) {
           viewModel.insert(p);
       }
    }
}
