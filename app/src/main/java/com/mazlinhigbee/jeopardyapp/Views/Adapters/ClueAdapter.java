package com.mazlinhigbee.jeopardyapp.Views.Adapters;

import android.content.Context;
import android.content.res.Resources;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.mazlinhigbee.jeopardyapp.JeopardyApp;
import com.mazlinhigbee.jeopardyapp.Models.Clue;
import com.mazlinhigbee.jeopardyapp.Models.GameState;
import com.mazlinhigbee.jeopardyapp.R;
import com.mazlinhigbee.jeopardyapp.Views.Listeners.ClueResponseListener;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * com.mazlinhigbee.jeopardyapp.Views.Adapters
 * Created by: mhigbee
 * Date: 4/21/19 Time: 11:21 PM
 */
public class ClueAdapter extends RecyclerView.Adapter<ClueAdapter.ClueAdapterViewHolder> {

    private Context context;
    private List<Clue> clues;
    private ClueResponseListener responseListener;

    public ClueAdapter(Context context, List<Clue> clues, ClueResponseListener responseListener) {
        this.context = context;
        this.clues = clues;
        this.responseListener = responseListener;
    }

    @NonNull
    @Override
    public ClueAdapterViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ClueAdapterViewHolder(
                LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.adapter_clue, parent, false)
        );
    }

    @Override
    public void onBindViewHolder(@NonNull ClueAdapterViewHolder holder, int position) {
        final Clue curClue = clues.get(position);

        holder.clue.setText(curClue.getQuestion());
        holder.cost.setText(String.valueOf(curClue.getValue()));
        if(!curClue.isAnswered()) {
            holder.rightArrow.setOnClickListener(v -> {
                AlertDialog.Builder modal = new AlertDialog.Builder(context, 0);
                modal.setTitle("What is...?");
                modal.setMessage(curClue.getAnswer());
                modal.setPositiveButton("Correct", (dialog, which) -> {
                    responseListener.correct(curClue);
                    curClue.setAnswered(true);
                    dialog.dismiss();
                });
                modal.setNegativeButton("Wrong", (dialog, which) -> {
                    responseListener.incorrect(curClue);
                    curClue.setAnswered(true);
                    dialog.dismiss();
                });
                modal.show();
            });
        }
    }

    @Override
    public int getItemCount() {
        if (clues == null) {
            return 0;
        }
        return clues.size();
    }

    protected class ClueAdapterViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.clue_answer)
        TextView clue;

        @BindView(R.id.clue_cost)
        TextView cost;

        @BindView(R.id.reveal_question)
        ImageView rightArrow;

        public ClueAdapterViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }
}
