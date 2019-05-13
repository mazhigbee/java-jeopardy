package com.mazlinhigbee.jeopardyapp.Activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckedTextView;
import android.widget.ListView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.mazlinhigbee.jeopardyapp.JeopardyApp;
import com.mazlinhigbee.jeopardyapp.Models.Category;
import com.mazlinhigbee.jeopardyapp.Models.GameState;
import com.mazlinhigbee.jeopardyapp.Models.Player;
import com.mazlinhigbee.jeopardyapp.Models.ViewModels.PlayerViewModel;
import com.mazlinhigbee.jeopardyapp.R;
import com.mazlinhigbee.jeopardyapp.Views.Adapters.PlayerAdapter;
import com.mazlinhigbee.jeopardyapp.Views.Listeners.PickerViewContract;
import com.mazlinhigbee.jeopardyapp.Views.UserPickerDialog;

import java.util.ArrayList;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * com.mazlinhigbee.jeopardyapp.Activities
 * Created by: mhigbee
 * Date: 5/8/19 Time: 12:34 PM
 */
public class PlayerSetupActivity extends AppCompatActivity implements PickerViewContract {

    private Context context;
    private PlayerViewModel playerViewModel;
    private ArrayList<Category> gameChosenCategories;
    private ArrayList<Player> chosenPlayers;


    @BindView(R.id.player_setup_add)
    FloatingActionButton btnAdd;

    @BindView(R.id.player_setup_start)
    Button btnStart;

    @Nullable
    @BindView(R.id.player_setup_userpicker)
    UserPickerDialog userPickerDialog;

    @BindView(R.id.player_setup_recycler)
    RecyclerView recyclerView;

    @Override
    public void onBackPressed() {
        if (userPickerDialog != null && userPickerDialog.getVisibility() == View.VISIBLE) {
            hidePlayerPicker();
        } else if (userPickerDialog != null && ((userPickerDialog.getVisibility() == View.GONE)
                || (userPickerDialog.getVisibility()) == View.INVISIBLE)) {
            super.onBackPressed();
        }
    }

    private void hidePlayerPicker(){
        if(userPickerDialog != null)
            userPickerDialog.setVisibility(View.GONE);

        btnAdd.setVisibility(View.VISIBLE);
        btnStart.setVisibility(View.VISIBLE);
    }

    @Override
    public void onCreate(Bundle savedInstace) {
        super.onCreate(savedInstace);
        setContentView(R.layout.player_setup_activity);
        ButterKnife.bind(this);

        context = getApplicationContext();
        gameChosenCategories = new ArrayList<>();
        chosenPlayers = new ArrayList<>();

        btnAdd.setImageResource(R.drawable.ic_person);
        btnAdd.setOnClickListener(v -> {
            userPickerDialog.setVisibility(View.VISIBLE);
            btnAdd.setVisibility(View.GONE);
            btnStart.setVisibility(View.GONE);
        });

        btnStart.setOnClickListener(v -> canWeStart());

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(new PlayerAdapter(this, Player.getAllPlayers()));

        playerViewModel = ViewModelProviders.of(this).get(PlayerViewModel.class);

        playerViewModel.getAllPlayers().observe(this, players -> {
            Player.setAllPlayers(players);
            recyclerView.setAdapter(new PlayerAdapter(this, Player.getAllPlayers()));
        });

        userPickerDialog.setViewContract(this);
        userPickerDialog.setViewModel(playerViewModel, this);

    }

    private void canWeStart() {
        playerViewModel.getAllActivePlayers().observe(this, players -> {
            if (players == null || players.size() == 0) {
                Toast.makeText(this, "Please add a player first.", Toast.LENGTH_SHORT).show();
            } else {
                chosenPlayers = new ArrayList<>(players);
                pickCategory();
            }
        });
    }


    @Override
    public void donePicking() {
        if (recyclerView.getAdapter() != null) {
            for (Player p : Player.getAllPlayers()) {
                playerViewModel.insert(p);
            }
            recyclerView.getAdapter().notifyDataSetChanged();
            hidePlayerPicker();
        }
    }

    private void pickCategory() {
        gameChosenCategories.clear();

        final ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(
                this, android.R.layout.select_dialog_multichoice);

        for (Category c : JeopardyApp.getCategories()) {
            arrayAdapter.add(c.getTitle());
        }

        final AlertDialog dialog = new AlertDialog.Builder(this)
                .setTitle("Choose Categories")
                .setAdapter(arrayAdapter, null)
                .setPositiveButton("Done", (dialog1, which) -> {
                        JeopardyApp.curGameState = new GameState(gameChosenCategories, chosenPlayers);
                        startActivity(new Intent(this, GameActivity.class));
                        dialog1.dismiss();
                })
                .setNegativeButton(getResources().getString(android.R.string.cancel), null)
                .create();

        dialog.getListView().setItemsCanFocus(false);
        dialog.getListView().setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
        dialog.getListView().setOnItemClickListener((parent, view, position, id) -> {
            CheckedTextView textView = (CheckedTextView) view;
            if (textView.isChecked()) {
                gameChosenCategories.add(
                        JeopardyApp.getCategoryFromStringName(arrayAdapter.getItem(position)
                        ));
            } else {
                gameChosenCategories.remove(
                        JeopardyApp.getCategoryFromStringName(arrayAdapter.getItem(position)
                        ));
            }
        });
        dialog.show();
    }

}
