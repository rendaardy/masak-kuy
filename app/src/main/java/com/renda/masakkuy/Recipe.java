package com.renda.masakkuy;

import android.os.Parcel;
import android.os.Parcelable;

public class Recipe implements Parcelable {
    private String recipeName;
    private String recipeImage;
    private String recipeDetail;

    public String getRecipeName() {
        return recipeName;
    }

    public void setRecipeName(String recipeName) {
        this.recipeName = recipeName;
    }

    public String getRecipeImage() {
        return recipeImage;
    }

    public void setRecipeImage(String recipeImage) {
        this.recipeImage = recipeImage;
    }

    public String getRecipeDetail() {
        return recipeDetail;
    }

    public void setRecipeDetail(String recipeDetail) {
        this.recipeDetail = recipeDetail;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.recipeName);
        dest.writeString(this.recipeImage);
        dest.writeString(this.recipeDetail);
    }

    public Recipe() {}

    protected Recipe(Parcel in) {
        this.recipeName = in.readString();
        this.recipeImage= in.readString();
        this.recipeDetail = in.readString();
    }

    public static final Parcelable.Creator<Recipe> CREATOR = new Parcelable.Creator<Recipe>() {
        @Override
        public Recipe createFromParcel(Parcel source) {
            return new Recipe(source);
        }

        @Override
        public Recipe[] newArray(int size) {
            return new Recipe[size];
        }
    };
}
