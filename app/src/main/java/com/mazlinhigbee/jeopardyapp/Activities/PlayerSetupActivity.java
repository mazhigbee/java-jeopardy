package com.mazlinhigbee.jeopardyapp.Activities;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckedTextView;
import android.widget.ListView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.mazlinhigbee.jeopardyapp.API.JServiceRestInterface;
import com.mazlinhigbee.jeopardyapp.API.RetroFitFactory;
import com.mazlinhigbee.jeopardyapp.JeopardyApp;
import com.mazlinhigbee.jeopardyapp.Models.Category;
import com.mazlinhigbee.jeopardyapp.Models.Clue;
import com.mazlinhigbee.jeopardyapp.Models.Player;
import com.mazlinhigbee.jeopardyapp.Models.ViewModels.PlayerViewModel;
import com.mazlinhigbee.jeopardyapp.R;
import com.mazlinhigbee.jeopardyapp.Views.Adapters.PlayerAdapter;
import com.mazlinhigbee.jeopardyapp.Views.Listeners.PickerViewContract;
import com.mazlinhigbee.jeopardyapp.Views.UserPickerDialog;

import java.util.List;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;
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
 * Date: 5/8/19 Time: 12:34 PM
 */
public class PlayerSetupActivity extends AppCompatActivity implements PickerViewContract {

    private Context context;
    private PlayerViewModel playerViewModel;


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

        btnAdd.setImageResource(R.drawable.ic_person);
        btnAdd.setOnClickListener(v -> {
            userPickerDialog.setVisibility(View.VISIBLE);
            btnAdd.setVisibility(View.GONE);
            btnStart.setVisibility(View.GONE);
        });

        btnStart.setOnClickListener(v -> {
            //pick categories
            //start game
            pickCategory();
        });

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(new PlayerAdapter(this, Player.getAllPlayers()));

        playerViewModel = ViewModelProviders.of(this).get(PlayerViewModel.class);

        playerViewModel.getAllPlayers().observe(this, words -> {
            Player.setAllPlayers(words);
            recyclerView.setAdapter(new PlayerAdapter(this, Player.getAllPlayers()));

        });

        userPickerDialog.setViewContract(this);
        userPickerDialog.setViewModel(playerViewModel, this);


//        new RoomDatabase.Callback() {
//            @Override
//            public void onOpen(@NonNull SupportSQLiteDatabase db) {
//                super.onOpen(db);
//                new PopulateDbAsync(PlayerRoomDatabase.getDatabase(context)).execute();
//            }
//        };

    }
//
//
//    private static class PopulateDbAsync extends AsyncTask<Void, Void, Void> {
//
//        private final PlayerDao mDao;
//
//        PopulateDbAsync(PlayerRoomDatabase db) {
//            mDao = db.playerDao();
//        }
//
//        @Override
//        protected Void doInBackground(final Void... params) {
//
//            return null;
//        }
//    }

    @Override
    public void donePicking() {
        if (recyclerView.getAdapter() != null) {
            for (Player p : Player.getAllPlayers()) {
                playerViewModel.insert(p);
            }
            recyclerView.getAdapter().notifyDataSetChanged();
            onBackPressed();
        }
    }

    private void pickCategory() {
        JeopardyApp.gameChosenCategories.clear();

        final ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(
                this, android.R.layout.select_dialog_multichoice);

        for (Category c : JeopardyApp.getCategories()) {
            arrayAdapter.add(c.getTitle());
        }

        final AlertDialog dialog = new AlertDialog.Builder(this)
                .setTitle("Choose Categories")
                .setAdapter(arrayAdapter, null)
                .setPositiveButton("Done",
                        (dialog1, which) -> {
                            dialog1.dismiss();
                            //todo start game...
                        })
                .setNegativeButton(getResources().getString(android.R.string.cancel), null)
                .create();

        dialog.getListView().setItemsCanFocus(false);
        dialog.getListView().setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
        dialog.getListView().setOnItemClickListener((parent, view, position, id) -> {
            CheckedTextView textView = (CheckedTextView) view;
            if (textView.isChecked()) {
                JeopardyApp.gameChosenCategories.add(
                        JeopardyApp.getCategoryFromStringName(arrayAdapter.getItem(position)
                        ));
            } else {
                JeopardyApp.gameChosenCategories.add(
                        JeopardyApp.getCategoryFromStringName(arrayAdapter.getItem(position)
                        ));
            }
        });
        dialog.show();
    }

    private void getCluesForCategory(Category categoryId) {
        JServiceRestInterface comm = RetroFitFactory.getRetroFit().create(JServiceRestInterface.class);
        ;

        Call<List<Clue>> getClues = comm.getCluesForCategory(categoryId.getId());
        getClues.enqueue(new Callback<List<Clue>>() {
            @Override
            public void onResponse(Call<List<Clue>> call, Response<List<Clue>> response) {
                // recyclerView.setAdapter(new ClueAdapter(getApplicationContext(), response.body()));
                //go ahead and start playing?
                JeopardyApp.clueMap.put(categoryId.getId(), response.body());

            }

            @Override
            public void onFailure(Call<List<Clue>> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "Error retreiving clues.", Toast.LENGTH_SHORT).show();
            }
        });

    }
}
