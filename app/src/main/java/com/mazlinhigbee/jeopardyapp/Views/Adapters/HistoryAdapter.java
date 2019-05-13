package com.mazlinhigbee.jeopardyapp.Views.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.mazlinhigbee.jeopardyapp.Models.Player;
import com.mazlinhigbee.jeopardyapp.Models.PlayerHistory;
import com.mazlinhigbee.jeopardyapp.Models.ViewModels.PlayerViewModel;
import com.mazlinhigbee.jeopardyapp.R;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.lifecycle.LifecycleOwner;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * com.mazlinhigbee.jeopardyapp.Views.Adapters
 * Created by: mhigbee
 * Date: 5/12/19 Time: 11:02 PM
 */
public class HistoryAdapter extends RecyclerView.Adapter<HistoryAdapter.HistoryAdapterViewHolder> {

    private Context context;
    private List<PlayerHistory> historyList;
    private PlayerViewModel viewModel;
    private LifecycleOwner owner;

    public HistoryAdapter(Context context, List<PlayerHistory> historyList) {
        this.context = context;
        this.historyList = historyList;
    }

    public void setViewModel(PlayerViewModel viewModel, LifecycleOwner owner) {
        this.viewModel = viewModel;
        this.owner = owner;
    }


    @NonNull
    @Override
    public HistoryAdapterViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new HistoryAdapterViewHolder(
                LayoutInflater.from(parent.getContext())
                        .inflate(
                                R.layout.adapter_history,
                                parent,
                                false
                        ));
    }

    @Override
    public void onBindViewHolder(@NonNull HistoryAdapterViewHolder holder, int position) {
        final PlayerHistory playerHistory = historyList.get(position);

        holder.rightScore.setText(String.valueOf(playerHistory.getQuestionsCorrect()));
        holder.wrongScore.setText(String.valueOf(playerHistory.getQuestionIncorrect()));
        holder.score.setText("$" + String.valueOf(playerHistory.getScore()));

        viewModel.getPlayerViaId(playerHistory.getId()).observe(owner, player -> {
            if(player != null)
                holder.name.setText(player.getName());
        });

    }

    @Override
    public int getItemCount() {
        if (historyList == null)
            return 0;
        return historyList.size();
    }

    protected class HistoryAdapterViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.history_right_count)
        TextView rightScore;

        @BindView(R.id.history_wrong_count)
        TextView wrongScore;

        @BindView(R.id.history_player_name)
        TextView name;

        @BindView(R.id.history_right_score)
        TextView score;

        public HistoryAdapterViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

    }

}

