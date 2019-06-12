package com.renda.masakkuy;

import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.util.ArrayList;

public class RecipeDetailActivity extends AppCompatActivity {

    private ImageView imgRecipePhoto;
    private TextView tvRecipeName;
    private TextView tvRecipeDetail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_detail);

        String recipeName = getIntent().getStringExtra("extra_recipe_name");
        String recipeImage = getIntent().getStringExtra("extra_recipe_image");
        String recipeDetail = getIntent().getStringExtra("extra_recipe_detail");

        imgRecipePhoto = findViewById(R.id.img_recipe_photo);
        tvRecipeName = findViewById(R.id.tv_recipe_name);
        tvRecipeDetail = findViewById(R.id.tv_recipe_detail);

        Glide.with(this)
                .load(recipeImage)
                .apply(new RequestOptions().override(1024, 720))
                .into(imgRecipePhoto);

        tvRecipeName.setText(recipeName);
        tvRecipeDetail.setText(recipeDetail);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_recipe:
                NavUtils.navigateUpFromSameTask(this);
                return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
