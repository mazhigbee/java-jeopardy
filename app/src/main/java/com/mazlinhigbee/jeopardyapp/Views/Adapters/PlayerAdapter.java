package com.mazlinhigbee.jeopardyapp.Views.Adapters;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import com.mazlinhigbee.jeopardyapp.Models.Player;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

/**
 * com.mazlinhigbee.jeopardyapp.Views.Adapters
 * Created by: mhigbee
 * Date: 5/8/19 Time: 12:27 PM
 */
public class PlayerAdapter extends RecyclerView.Adapter<PlayerAdapter.PlayerAdapterVH> {

    private Context context;
    private List<Player> players;

    public PlayerAdapter(Context context, List<Player> players){

    }
    @NonNull
    @Override
    public PlayerAdapterVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull PlayerAdapterVH holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    protected class PlayerAdapterVH extends RecyclerView.ViewHolder {

        public PlayerAdapterVH(@NonNull View itemView) {
            super(itemView);
        }
    }
}
