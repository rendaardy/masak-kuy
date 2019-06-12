package com.renda.masakkuy;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.util.ArrayList;

public class GridRecipeAdapter extends RecyclerView.Adapter<GridRecipeAdapter.GridViewHolder> {
    private Context context;
    private ArrayList<Recipe> listRecipe;

    public GridRecipeAdapter(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public GridRecipeAdapter.GridViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.item_grid_recipe, viewGroup, false);

        return new GridViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull GridRecipeAdapter.GridViewHolder gridViewHolder, int i) {
        Glide.with(context)
                .load(getListRecipe().get(i).getRecipeImage())
                .apply(new RequestOptions().override(350, 550))
                .into(gridViewHolder.imgRecipePhoto);

        gridViewHolder.tvRecipeName.setText(getListRecipe().get(i).getRecipeName());
    }

    @Override
    public int getItemCount() {
        return getListRecipe().size();
    }

    public ArrayList<Recipe> getListRecipe() {
        return listRecipe;
    }

    public void setListRecipe(ArrayList<Recipe> listRecipe) {
        this.listRecipe = listRecipe;
    }

    public class GridViewHolder extends RecyclerView.ViewHolder {
        ImageView imgRecipePhoto;
        TextView tvRecipeName;

        public GridViewHolder(@NonNull View itemView) {
            super(itemView);

            imgRecipePhoto = itemView.findViewById(R.id.img_recipe_photo);
            tvRecipeName = itemView.findViewById(R.id.tv_recipe_name);
        }
    }
}
