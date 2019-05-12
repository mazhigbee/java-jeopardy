package com.mazlinhigbee.jeopardyapp.Activities;

import android.os.Bundle;

import com.mazlinhigbee.jeopardyapp.R;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * com.mazlinhigbee.jeopardyapp.Activities
 * Created by: mhigbee
 * Date: 5/10/19 Time: 11:22 AM
 */
public class GameActivity extends AppCompatActivity {

    @BindView(R.id.activity_game_recycler)
    RecyclerView recyclerView;

    @Override
    public void onCreate(Bundle savedInstance) {
        super.onCreate(savedInstance);
        setContentView(R.layout.activity_game);
        ButterKnife.bind(this);

    }
}
