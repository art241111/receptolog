package ru.art241111.dish_recipes.models;

import android.os.Parcel;
import android.os.Parcelable;

public class Dish implements Parcelable {
    private int imageDish;
    private String nameDish;
    private String descriptionDish;
    private String recipe;

    //TODO add rating
    private int rating;

    public Dish(int imageDish, String nameDish, String descriptionDish, String recipe) {
        this.imageDish = imageDish;
        this.nameDish = nameDish;
        this.descriptionDish = descriptionDish;
        this.recipe = recipe;
    }

    public Dish() {
    }

    protected Dish(Parcel in) {
        imageDish = in.readInt();
        nameDish = in.readString();
        descriptionDish = in.readString();
        recipe = in.readString();
        rating = in.readInt();
    }

    public static final Creator<Dish> CREATOR = new Creator<Dish>() {
        @Override
        public Dish createFromParcel(Parcel in) {
            return new Dish(in);
        }

        @Override
        public Dish[] newArray(int size) {
            return new Dish[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(imageDish);
        dest.writeString(nameDish);
        dest.writeString(descriptionDish);
        dest.writeString(recipe);
        dest.writeInt(rating);
    }

    public String getRecipe() {
        return recipe;
    }

    public void setRecipe(String recipe) {
        this.recipe = recipe;
    }

    public int getImageDish() {
        return imageDish;
    }

    public void setImageDish(int imageDish) {
        this.imageDish = imageDish;
    }

    public String getNameDish() {
        return nameDish;
    }

    public void setNameDish(String nameDish) {
        this.nameDish = nameDish;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public String getDescriptionDish() {
        return descriptionDish;
    }

    public void setDescriptionDish(String descriptionDish) {
        this.descriptionDish = descriptionDish;
    }


}
