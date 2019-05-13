package com.mazlinhigbee.jeopardyapp.Views.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.mazlinhigbee.jeopardyapp.Models.ClueResult;
import com.mazlinhigbee.jeopardyapp.Models.GameState;
import com.mazlinhigbee.jeopardyapp.Models.Player;
import com.mazlinhigbee.jeopardyapp.R;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * com.mazlinhigbee.jeopardyapp.Views.Adapters
 * Created by: mhigbee
 * Date: 5/12/19 Time: 9:22 PM
 */
public class ScoreAdapter extends RecyclerView.Adapter<ScoreAdapter.ScoreAdapterViewHolder> {

    private GameState gameState;
    private Context context;
    private List<Player> players;

    public ScoreAdapter(Context context, GameState gameState) {
        this.gameState = gameState;
        this.players = gameState.getPlayers();
        this.context = context;
    }

    @NonNull
    @Override
    public ScoreAdapterViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ScoreAdapterViewHolder(
                LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.adapter_score, parent, false)
        );
    }

    @Override
    public void onBindViewHolder(@NonNull ScoreAdapterViewHolder holder, int position) {
        final Player curPlayer = players.get(position);

        int totalWinning = 0;

        if(gameState.getResults() != null && gameState.getResults().get(curPlayer) != null) {
            for (ClueResult c : gameState.getResults().get(curPlayer)) {
                if (c.isAnwseredCorrectly() && c.getClue().getValue() != null) {
                    totalWinning += c.getClue().getValue();
                } else if(c.getClue().getValue() != null) {
                    totalWinning -= c.getClue().getValue();
                }
            }
        }

        holder.name.setText(curPlayer.getName());
        holder.score.setText("$" + String.valueOf(totalWinning));
    }

    @Override
    public int getItemCount() {
        if(gameState == null || gameState.getPlayers() == null)
            return 0;
        return gameState.getPlayers().size();
    }

    protected class ScoreAdapterViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.adapter_score_name)
        TextView name;

        @BindView(R.id.adapter_score_amount)
        TextView score;

        public ScoreAdapterViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }
}
