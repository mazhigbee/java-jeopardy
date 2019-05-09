package com.mazlinhigbee.jeopardyapp.Activities;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.mazlinhigbee.jeopardyapp.Dao.PlayerDao;
import com.mazlinhigbee.jeopardyapp.Dao.PlayerRoomDatabase;
import com.mazlinhigbee.jeopardyapp.Models.Player;
import com.mazlinhigbee.jeopardyapp.Models.ViewModels.PlayerViewModel;
import com.mazlinhigbee.jeopardyapp.R;
import com.mazlinhigbee.jeopardyapp.Views.Adapters.PlayerAdapter;
import com.mazlinhigbee.jeopardyapp.Views.Listeners.PickerViewContract;
import com.mazlinhigbee.jeopardyapp.Views.UserPickerDialog;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;
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

        });



        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(new PlayerAdapter(this, Player.getAllPlayers()));
        userPickerDialog.setViewContract(this);


        //room
        playerViewModel = ViewModelProviders.of(this).get(PlayerViewModel.class);

        playerViewModel.getAllPlayers().observe(this, words -> {
            // Update the cached copy of the words in the adapter.
            Player.setAllPlayers(words);
            recyclerView.setAdapter(new PlayerAdapter(this, Player.getAllPlayers()));

        });


        new RoomDatabase.Callback(){
            @Override
            public void onOpen (@NonNull SupportSQLiteDatabase db){
                super.onOpen(db);
                new PopulateDbAsync(PlayerRoomDatabase.getDatabase(context)).execute();
            }
        };

    }



    private static class PopulateDbAsync extends AsyncTask<Void, Void, Void> {

        private final PlayerDao mDao;

        PopulateDbAsync(PlayerRoomDatabase db) {
            mDao = db.playerDao();
        }

        @Override
        protected Void doInBackground(final Void... params) {

            return null;
        }
    }

    @Override
    public void donePicking() {
        if (recyclerView.getAdapter() != null) {
            for(Player p : Player.getAllPlayers()) {
                playerViewModel.insert(p);
            }
            recyclerView.getAdapter().notifyDataSetChanged();
            onBackPressed();
        }
    }
}
