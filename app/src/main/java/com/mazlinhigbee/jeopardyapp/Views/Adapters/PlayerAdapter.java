package com.mazlinhigbee.jeopardyapp.Views.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import com.mazlinhigbee.jeopardyapp.Models.Player;
import com.mazlinhigbee.jeopardyapp.R;
import com.mazlinhigbee.jeopardyapp.Views.Listeners.PickerViewContract;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * com.mazlinhigbee.jeopardyapp.Views.Adapters
 * Created by: mhigbee
 * Date: 5/8/19 Time: 12:27 PM
 */
public class PlayerAdapter extends RecyclerView.Adapter<PlayerAdapter.PlayerAdapterVH> {

    private Context context;
    private List<Player> players;

    public PlayerAdapter(Context context, List<Player> players) {
        this.context = context;
        this.players = players;
    }

    @NonNull
    @Override
    public PlayerAdapterVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new PlayerAdapterVH(
                LayoutInflater.from(parent.getContext())
                        .inflate(
                                R.layout.adapter_user_picker,
                                parent,
                                false
                        ));
    }

    @Override
    public void onBindViewHolder(@NonNull PlayerAdapterVH holder, int position) {
        final Player player = players.get(position);

        holder.txtUser.setText(player.getName());
        holder.checkBox.setChecked(player.isPlaying());

        holder.checkBox.setOnCheckedChangeListener((buttonView, isChecked) -> {
            player.setPlaying(isChecked);
        });
    }

    @Override
    public int getItemCount() {
        if (players == null) {
            return 0;
        }
        return players.size();

    }

    protected class PlayerAdapterVH extends RecyclerView.ViewHolder {
        @BindView(R.id.user_picker_adapter_name)
        TextView txtUser;

        @BindView(R.id.user_picker_adapter_check)
        CheckBox checkBox;

        public PlayerAdapterVH(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
