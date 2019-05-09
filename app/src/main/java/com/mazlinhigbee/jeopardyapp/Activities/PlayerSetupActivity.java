package com.mazlinhigbee.jeopardyapp.Activities;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.mazlinhigbee.jeopardyapp.Models.Player;
import com.mazlinhigbee.jeopardyapp.R;
import com.mazlinhigbee.jeopardyapp.Views.Adapters.PlayerAdapter;
import com.mazlinhigbee.jeopardyapp.Views.Listeners.PickerViewContract;
import com.mazlinhigbee.jeopardyapp.Views.UserPickerDialog;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
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
        btnAdd.setImageResource(R.drawable.ic_person);
        btnAdd.setOnClickListener(v -> {
            //setContentView(new UserPickerDialog(this));
            userPickerDialog.setVisibility(View.VISIBLE);
            btnAdd.setVisibility(View.GONE);
            btnStart.setVisibility(View.GONE);

        });

        btnStart.setOnClickListener(v -> {

        });

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(new PlayerAdapter(this,Player.getAllPlayers()));
        userPickerDialog.setViewContract(this);
    }

    @Override
    public void donePicking() {
        if(recyclerView.getAdapter() != null) {
            recyclerView.getAdapter().notifyDataSetChanged();
            onBackPressed();
        }
    }
}
