package ru.art241111.dish_recipes.ui.recipeActivity;

import androidx.appcompat.app.AppCompatActivity;
import ru.art241111.dish_recipes.R;
import ru.art241111.dish_recipes.models.Dish;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class RecipeActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe);

        Dish dish = new Dish();

        getDataFromIntent(dish);

        setTextToTextView(dish);
    }

    private void setTextToTextView(Dish dish) {
        TextView name = findViewById(R.id.tv_name_dish);
        TextView description = findViewById(R.id.tv_description_dish);
        TextView recipe = findViewById(R.id.tv_recipe_dish);

        name.setText(dish.getNameDish());
        description.setText(dish.getDescriptionDish());
        recipe.setText(dish.getRecipe());
    }

    private void getDataFromIntent(Dish dish) {
        Intent intent = getIntent();
        if(intent != null){
            dish.setNameDish(intent.getStringExtra("name"));
            dish.setDescriptionDish(intent.getStringExtra("description"));
            dish.setRecipe(intent.getStringExtra("recipe"));
        }
    }
}
