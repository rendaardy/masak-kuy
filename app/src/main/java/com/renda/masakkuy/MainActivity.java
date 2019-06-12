package com.renda.masakkuy;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private RecyclerView rvCategory;
    private ArrayList<Recipe> list;

    private final String STATE_LIST = "state_list";
    private final String STATE_MODE = "state_mode";

    private int mode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rvCategory = findViewById(R.id.rv_category);
        rvCategory.setHasFixedSize(true);

        list = new ArrayList<>();

        if (savedInstanceState == null) {
            list.addAll(RecipeData.getListData());
            mode = R.id.action_grid;
        } else {
            ArrayList<Recipe> stateList = savedInstanceState.getParcelableArrayList(STATE_LIST);
            int stateMode = savedInstanceState.getInt(STATE_MODE);
            list.addAll(stateList);
            mode = stateMode;
        }

        setMode(mode);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        outState.putParcelableArrayList(STATE_LIST, list);
        outState.putInt(STATE_MODE, mode);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        setMode(item.getItemId());

        return super.onOptionsItemSelected(item);
    }

    private void showSelectedRecipe(Recipe recipe) {
//        Toast.makeText(this, "Kamu memilih " + recipe.getRecipeName(), Toast.LENGTH_SHORT).show();

        Intent intent = new Intent(MainActivity.this, RecipeDetailActivity.class);
        intent.putExtra("extra_recipe_name", recipe.getRecipeName());
        intent.putExtra("extra_recipe_image", recipe.getRecipeImage());
        intent.putExtra("extra_recipe_detail", recipe.getRecipeDetail());
        startActivity(intent);
    }

    private void setMode(int selectedMode) {
        switch (selectedMode) {
            case R.id.action_grid:
                showRecyclerRecipeGrid();
                break;
            case R.id.action_cardview:
                showRecyclerRecipeCardView();
                break;
        }

        mode = selectedMode;
    }

    private void showRecyclerRecipeGrid() {
        rvCategory.setLayoutManager(new GridLayoutManager(this, 2));
        GridRecipeAdapter adapter = new GridRecipeAdapter(this);
        adapter.setListRecipe(list);
        rvCategory.setAdapter(adapter);

        ItemClickSupport.addTo(rvCategory)
                .setOnItemClickListener((recyclerView, position, v) -> showSelectedRecipe(list.get(position)));
    }

    private void showRecyclerRecipeCardView() {
        rvCategory.setLayoutManager(new LinearLayoutManager(this));
        CardViewRecipeAdapter adapter = new CardViewRecipeAdapter(this);
        adapter.setListRecipe(list);
        rvCategory.setAdapter(adapter);

        ItemClickSupport.addTo(rvCategory)
                .setOnItemClickListener(((recyclerView, position, v) -> showSelectedRecipe(list.get(position))));
    }
}
