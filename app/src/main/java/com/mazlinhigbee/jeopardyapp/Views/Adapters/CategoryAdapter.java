package com.mazlinhigbee.jeopardyapp.Views.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.mazlinhigbee.jeopardyapp.Models.Category;
import com.mazlinhigbee.jeopardyapp.R;
import com.mazlinhigbee.jeopardyapp.Views.CategoryAdapterViewContract;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * com.mazlinhigbee.jeopardyapp.Views.Adapters
 * Created by: mhigbee
 * Date: 4/21/19 Time: 10:20 PM
 */
public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.CategoryAdapterViewHolder> {

    private List<Category> categories;
    private Context context;
//    private CategoryAdapterViewContract viewContract;

    public CategoryAdapter(Context context, List<Category> categories) {
        this.context = context;
        this.categories = categories;
      //  this.viewContract = viewContract;
    }

    @NonNull
    @Override
    public CategoryAdapterViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new CategoryAdapterViewHolder(
                LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.adapter_category_picker, parent, false)
        );
    }

    @Override
    public void onBindViewHolder(@NonNull CategoryAdapterViewHolder holder, int position) {
        final Category curCat = categories.get(position);

        holder.categoryTxt.setText(curCat.getTitle());
    }

    @Override
    public int getItemCount() {
        return 0;
    }

    protected class CategoryAdapterViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.adapter_category_picker_text)
        TextView categoryTxt;

        public CategoryAdapterViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }


}
