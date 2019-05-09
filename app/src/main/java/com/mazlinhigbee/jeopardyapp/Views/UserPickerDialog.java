package com.mazlinhigbee.jeopardyapp.Views;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.mazlinhigbee.jeopardyapp.R;
import com.mazlinhigbee.jeopardyapp.Views.Adapters.PlayerAdapter;

import org.w3c.dom.Text;

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

    public UserPickerDialog(Context context, AttributeSet set) {
        super(context,set);
        init();
    }
    public UserPickerDialog(Context context) {
        super(context);
        init();
    }

    private void init() {
        View v = inflate(getContext(), R.layout.user_picker, this);
        ButterKnife.bind(v);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        this.setVisibility(INVISIBLE);
        //recyclerView.setAdapter(new PlayerAdapter(getContext(),));

    }
}
