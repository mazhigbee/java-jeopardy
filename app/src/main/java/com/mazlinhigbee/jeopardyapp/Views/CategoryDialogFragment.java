package com.mazlinhigbee.jeopardyapp.Views;

import android.app.Application;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;

import com.mazlinhigbee.jeopardyapp.JeopardyApp;
import com.mazlinhigbee.jeopardyapp.Models.Category;
import com.mazlinhigbee.jeopardyapp.Views.Adapters.CategoryAdapter;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.DialogFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

/**
 * com.mazlinhigbee.jeopardyapp.Views
 * Created by: mhigbee
 * Date: 4/21/19 Time: 10:14 PM
 */
public class CategoryDialogFragment extends DialogFragment {

    public CategoryDialogFragment() {
    }

    private RecyclerView mRecyclerView;

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        mRecyclerView = new RecyclerView(getContext());
        // you can use LayoutInflater.from(getContext()).inflate(...) if you have xml layout
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        mRecyclerView.setAdapter(new CategoryAdapter(getContext(), JeopardyApp.getCategories()));

        return new AlertDialog.Builder(getActivity())
                .setTitle("Choose a Category.")
                .setView(mRecyclerView)
                .setPositiveButton(android.R.string.ok,
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int whichButton) {
                                JeopardyApp.mainActivity.newCategoryChosen(JeopardyApp.getCategories().get(whichButton));
                            }
                        }
                ).create();
    }
}

