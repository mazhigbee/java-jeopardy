package com.mazlinhigbee.jeopardyapp.Views;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.mazlinhigbee.jeopardyapp.Models.Player;
import com.mazlinhigbee.jeopardyapp.R;
import com.mazlinhigbee.jeopardyapp.Views.Adapters.PlayerAdapter;
import com.mazlinhigbee.jeopardyapp.Views.Listeners.PickerViewContract;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * com.mazlinhigbee.jeopardyapp.Views
 * Created by: mhigbee
 * Date: 5/9/19 Time: 1:34 PM
 */
public class UserPickerDialog extends ConstraintLayout {
    @BindView(R.id.user_picker_done)
    TextView btnDone;

    @BindView(R.id.user_picker_recycler)
    RecyclerView recyclerView;

    @BindView(R.id.user_picker_add_player_name)
    Button btnAddPlayer;

    @BindView(R.id.user_picker_new_name)
    EditText edtUser;

    private PickerViewContract viewContract;

    public void setViewContract(PickerViewContract viewContract) {
        this.viewContract = viewContract;
    }

    public UserPickerDialog(Context context, AttributeSet set) {
        super(context,set);
        init();
    }
    public UserPickerDialog(Context context) {
        super(context);
        init();
    }

    private void init() {
        View view = inflate(getContext(), R.layout.user_picker, this);
        ButterKnife.bind(view);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        this.setVisibility(INVISIBLE);
        recyclerView.setAdapter(new PlayerAdapter(getContext(),Player.getAllPlayers()));
        //todo fetch from room


        btnAddPlayer.setOnClickListener(v -> {
            if(edtUser.getText().length() > 0) {
                Player.getAllPlayers().add(new Player(edtUser.getText().toString(),true));
                recyclerView.setAdapter(new PlayerAdapter(getContext(),Player.getAllPlayers()));

            }
        });

        btnDone.setOnClickListener(v -> {
            this.setVisibility(INVISIBLE);
            viewContract.donePicking();
        });

    }
}
