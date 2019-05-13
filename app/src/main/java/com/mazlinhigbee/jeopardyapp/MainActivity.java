package com.mazlinhigbee.jeopardyapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;
import com.mazlinhigbee.jeopardyapp.API.JServiceRestInterface;
import com.mazlinhigbee.jeopardyapp.API.RetroFitFactory;
import com.mazlinhigbee.jeopardyapp.Activities.HistoryActivity;
import com.mazlinhigbee.jeopardyapp.Activities.PlayerSetupActivity;
import com.mazlinhigbee.jeopardyapp.Models.Category;
import com.mazlinhigbee.jeopardyapp.Models.Clue;

import java.util.List;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    JServiceRestInterface comm;

    @BindView(R.id.activity_main_progress)
    ProgressBar progressBar;

    @BindView(R.id.activity_main_start)
    TextView btnStart;


    TextView drawerText;
    TextView drawerTextSub;

    NavigationView navView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        navView = (NavigationView) findViewById(R.id.nav_view);
        navView.setNavigationItemSelectedListener(this);

        comm = RetroFitFactory.getRetroFit().create(JServiceRestInterface.class);

        loadCategories();
        getCluesForCategory(JeopardyApp.CURRENT_CATEGORY);


        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(this, 1);
        layoutManager.requestLayout();


        drawerText = navView.getHeaderView(0).findViewById(R.id.nav_header_main_name);

        btnStart.setOnClickListener(v -> startActivity(new Intent(this, PlayerSetupActivity.class)));

        JeopardyApp.mainActivity = this;
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }


    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_category) {
            startActivity(new Intent(this, HistoryActivity.class));

        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public void newCategoryChosen(Category category) {

    }

    private void loadCategories() {
        Call<List<Category>> getJepCategories = comm.getJepCategories();
        getJepCategories.enqueue(new Callback<List<Category>>() {
            @Override
            public void onResponse(Call<List<Category>> call, Response<List<Category>> response) {
                JeopardyApp.setCategories(response.body());
            }

            @Override
            public void onFailure(Call<List<Category>> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "Error fetching categories.", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void getCluesForCategory(Category categoryId) {
        progressBar.setVisibility(View.VISIBLE);

        Call<List<Clue>> getClues = comm.getCluesForCategory(categoryId.getId());
        getClues.enqueue(new Callback<List<Clue>>() {
            @Override
            public void onResponse(Call<List<Clue>> call, Response<List<Clue>> response) {
                // recyclerView.setAdapter(new ClueAdapter(getApplicationContext(), response.body()));
                progressBar.setVisibility(View.GONE);
            }

            @Override
            public void onFailure(Call<List<Clue>> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "Error retreiving clues.", Toast.LENGTH_SHORT).show();
            }
        });

    }
}
