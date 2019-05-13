package com.mazlinhigbee.jeopardyapp.Activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import com.google.android.material.tabs.TabLayout;
import com.mazlinhigbee.jeopardyapp.API.JServiceRestInterface;
import com.mazlinhigbee.jeopardyapp.API.RetroFitFactory;
import com.mazlinhigbee.jeopardyapp.JeopardyApp;
import com.mazlinhigbee.jeopardyapp.Models.Category;
import com.mazlinhigbee.jeopardyapp.Models.Clue;
import com.mazlinhigbee.jeopardyapp.Models.ClueResult;
import com.mazlinhigbee.jeopardyapp.Models.GameState;
import com.mazlinhigbee.jeopardyapp.Models.Player;
import com.mazlinhigbee.jeopardyapp.R;
import com.mazlinhigbee.jeopardyapp.Views.Adapters.ClueAdapter;
import com.mazlinhigbee.jeopardyapp.Views.Adapters.ScoreAdapter;
import com.mazlinhigbee.jeopardyapp.Views.Listeners.ClueResponseListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * com.mazlinhigbee.jeopardyapp.Activities
 * Created by: mhigbee
 * Date: 5/10/19 Time: 11:22 AM
 */
public class GameActivity extends AppCompatActivity implements ClueResponseListener {

    private GameState gameState;
    private Context context;
    private int questionsRemaining;

    @BindView(R.id.activity_game_recycler)
    RecyclerView recyclerView;

    @BindView(R.id.activity_game_score_recycler)
    RecyclerView scoreRecycler;

    @BindView(R.id.activity_game_tablayout)
    TabLayout tabLayout;


    @Override
    public void onBackPressed() {
        recyclerView.setAdapter(null);
        JeopardyApp.curGameState = null;
        gameState = null;
        super.onBackPressed();

    }


    @Override
    public void onCreate(Bundle savedInstance) {
        super.onCreate(savedInstance);
        setContentView(R.layout.activity_game);
        ButterKnife.bind(this);
        context = this;
        init();
    }

    private void init() {
        gameState = JeopardyApp.curGameState;

        for (int i = 0; i < gameState.getCategories().size(); i++) {
            final Category cur = gameState.getCategories().get(i);

            getCluesForCategory(cur); //network call to get clues

            tabLayout.addTab(tabLayout.newTab().setText(cur.getTitle()).setTag(cur.getId()));//setup tablayout
        }


        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                recyclerView.setAdapter(
                        new ClueAdapter(context,
                                gameState.getCategoryClueMap().get(tab.getTag()),
                                GameActivity.this));
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });


        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        scoreRecycler.setLayoutManager(new GridLayoutManager(this,2));
        scoreRecycler.setAdapter(new ScoreAdapter(context,gameState));
    }

    /**
     * Make a network call and get the clues for the given category
     *
     * @param category current category
     */
    private void getCluesForCategory(Category category) {
        JServiceRestInterface comm = RetroFitFactory.getRetroFit().create(JServiceRestInterface.class);

        Call<List<Clue>> getClues = comm.getCluesForCategory(category.getId());
        getClues.enqueue(new Callback<List<Clue>>() {
            @Override
            public void onResponse(Call<List<Clue>> call, Response<List<Clue>> response) {
                //this is ineffcient you can do better...
                HashMap<Integer, List<Clue>> clueMap = gameState.getCategoryClueMap();

                if (clueMap == null) {
                    clueMap = new HashMap<>();
                }

                clueMap.put(category.getId(), response.body());
                gameState.setCategoryClueMap(clueMap);
                questionsRemaining += response.body().size();
                redrawRecycler();
            }

            @Override
            public void onFailure(Call<List<Clue>> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "Error retrieving clues.", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void redrawRecycler() {
        if (recyclerView.getAdapter() != null) {
            recyclerView.getAdapter().notifyDataSetChanged();
        } else {
            //we can probably assume were on the first tab and need to setit
            recyclerView.setAdapter(
                    new ClueAdapter(context,
                            gameState.getCategoryClueMap().get(
                                    tabLayout.getTabAt(tabLayout.getSelectedTabPosition()).getTag()),
                            GameActivity.this));
        }
    }

    private void showPlayerPicker(boolean isCorrect, Clue clue) {

        AlertDialog.Builder builderSingle = new AlertDialog.Builder(this);
        builderSingle.setTitle("Select a Player");
        final ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.select_dialog_singlechoice);
        for (Player p : gameState.getPlayers()) {
            arrayAdapter.add(p.getName());
        }

        builderSingle.setAdapter(arrayAdapter, (dialog, which) -> {
            String strName = arrayAdapter.getItem(which);
            for (Player p : gameState.getPlayers()) {
                if (strName.toLowerCase().equals(p.getName().toLowerCase())) {
                    ClueResult clueResult = new ClueResult(isCorrect, p, clue);

                    if (gameState.getResults().get(p) == null) {
                        ArrayList<ClueResult> results = new ArrayList<>();
                        results.add(clueResult);
                        gameState.getResults().put(p, results);
                    }
                    gameState.getResults().get(p).add(clueResult);
                    gameState.getCategoryClueMap().get(tabLayout.getTabAt(tabLayout.getSelectedTabPosition()).getTag()).remove(clue);

                    recyclerView.getAdapter().notifyDataSetChanged();
                    scoreRecycler.getAdapter().notifyDataSetChanged();

                    questionsRemaining--;
                    if (questionsRemaining == 0) {
                        startActivity(new Intent());
                    }
                }
            }
        });
        builderSingle.show();
    }


    @Override
    public void correct(Clue clue) {
        showPlayerPicker(true, clue);

    }

    @Override
    public void incorrect(Clue clue) {
        showPlayerPicker(false, clue);
    }


}
