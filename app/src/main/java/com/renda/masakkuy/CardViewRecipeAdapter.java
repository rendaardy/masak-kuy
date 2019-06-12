package com.renda.masakkuy;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.util.ArrayList;

public class CardViewRecipeAdapter extends RecyclerView.Adapter<CardViewRecipeAdapter.CardViewViewHolder> {
    private Context context;
    private ArrayList<Recipe> listRecipe;

    public CardViewRecipeAdapter(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public CardViewRecipeAdapter.CardViewViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.item_cardview_recipe, viewGroup, false);

        return new CardViewViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CardViewRecipeAdapter.CardViewViewHolder cardViewViewHolder, int i) {
        Glide.with(context)
                .load(getListRecipe().get(i).getRecipeImage())
                .apply(new RequestOptions().override(350, 550))
                .into(cardViewViewHolder.imgRecipePhoto);

        cardViewViewHolder.tvRecipeName.setText(getListRecipe().get(i).getRecipeName());

        cardViewViewHolder.btnSetFavorite.setOnClickListener(new CustomOnItemClickListener(i,
                (v, pos) -> Toast.makeText(context,
                        "Favorite " + getListRecipe().get(pos).getRecipeName(),
                        Toast.LENGTH_SHORT).show())
        );

        cardViewViewHolder.btnSetShare.setOnClickListener(new CustomOnItemClickListener(i,
                (v, pos) -> Toast.makeText(context,
                        "Share " + getListRecipe().get(pos).getRecipeName(),
                        Toast.LENGTH_SHORT).show())
        );
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

    public class CardViewViewHolder extends RecyclerView.ViewHolder {
        ImageView imgRecipePhoto;
        TextView tvRecipeName;
        Button btnSetFavorite;
        Button btnSetShare;

        public CardViewViewHolder(@NonNull View itemView) {
            super(itemView);

            imgRecipePhoto = itemView.findViewById(R.id.img_recipe_photo);
            tvRecipeName = itemView.findViewById(R.id.tv_recipe_name);
            btnSetFavorite = itemView.findViewById(R.id.btn_set_favorite);
            btnSetShare = itemView.findViewById(R.id.btn_set_share);
        }
    }
}
