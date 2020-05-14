package ru.art241111.dish_recipes.data;

import android.os.Parcel;
import android.os.Parcelable;
import android.widget.ImageView;

import androidx.databinding.BindingAdapter;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.util.ArrayList;
import java.util.List;

/**
 * Model of dishes.
 * @author Artem Geraimov.
 */
public class FullDish implements Parcelable {
    // Deprecated
    private int imageDish;

    private String nameDish;
    private String descriptionDish;
    private String recipe;
    private String urlImageRecipe;
    private List<String> ingredients;
    private boolean isFavorite = false;

    public static Creator<FullDish> getCREATOR() {
        return CREATOR;
    }

    // Constructors.
    public FullDish(int imageDish, String nameDish, String descriptionDish, String recipe) {
        this.imageDish = imageDish;
        this.nameDish = nameDish;
        this.descriptionDish = descriptionDish;
        this.recipe = recipe;
    }

    public FullDish() {}

    protected FullDish(Parcel in) {
        imageDish = in.readInt();
        nameDish = in.readString();
        descriptionDish = in.readString();
        recipe = in.readString();
        urlImageRecipe = in.readString();

        ingredients = new ArrayList<>();
        in.readStringList(ingredients);
    }

    // Migrate to parcelable.
    public static final Creator<FullDish> CREATOR = new Creator<FullDish>() {
        @Override
        public FullDish createFromParcel(Parcel in) {
            return new FullDish(in);
        }

        @Override
        public FullDish[] newArray(int size) {
            return new FullDish[size];
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
        dest.writeString(urlImageRecipe);
        dest.writeStringList(ingredients);
    }

    // Setters and getters.
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

    public String getDescriptionDish() {
        return descriptionDish;
    }

    public void setDescriptionDish(String descriptionDish) {
        this.descriptionDish = descriptionDish;
    }

    public void setDescriptionDishFromArray(List<String> healthLabels) {
        StringBuilder out = new StringBuilder();
        for (String healthLabel:healthLabels) {
            out.append(healthLabel).append(", ");
        }
        this.descriptionDish = out.toString();
    }

    public boolean isFavorite() {
        return isFavorite;
    }

    public void setFavorite(boolean favorite) {
        isFavorite = favorite;
    }

    public String getUrlImageRecipe() {
        return urlImageRecipe;
    }

    public void setUrlImageRecipe(String urlImageRecipe) {
        this.urlImageRecipe = urlImageRecipe;
    }

    public String getStringIngredients() {
        StringBuilder out = new StringBuilder();
        for (String ingredient:ingredients) {
            out.append(ingredient).append("\n");
        }
        return out.toString();
    }

    public List<String> getIngredients() {
        return ingredients;
    }

    public void setIngredients(List<String> ingredients) {
        this.ingredients = ingredients;
    }

    @BindingAdapter("profileImage")
    public static void loadImage(ImageView view, String imageUrl) {
        Glide.with(view.getContext())
                .load(imageUrl).apply(new RequestOptions().circleCrop())
                .into(view);
    }
}
