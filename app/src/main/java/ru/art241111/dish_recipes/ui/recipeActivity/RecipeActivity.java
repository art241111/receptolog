package ru.art241111.dish_recipes.ui.recipeActivity;

import androidx.appcompat.app.AppCompatActivity;
import ru.art241111.dish_recipes.R;
import ru.art241111.dish_recipes.models.Dish;

import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TabHost;
import android.widget.TextView;

public class RecipeActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe);

        Dish dish = new Dish();

        getDataFromIntent(dish);

        setTextToTextView(dish);

        setupTabHost();

        ScrollView scrollView = findViewById(R.id.scrollable);
        scrollView.fullScroll(View.FOCUS_UP);
    }

    private void setupTabHost() {
        TabHost tabHost = (TabHost) findViewById(R.id.tabHost);

        tabHost.setup();

        TabHost.TabSpec tabSpec = tabHost.newTabSpec("tag1");

        tabSpec.setContent(R.id.tab1);
        tabSpec.setIndicator("Ингредиенты");
        tabHost.addTab(tabSpec);

        tabSpec = tabHost.newTabSpec("tag2");
        tabSpec.setContent(R.id.tab2);
        tabSpec.setIndicator("Рецепт");
        tabHost.addTab(tabSpec);
    }

    private void setTextToTextView(Dish dish) {
        ImageView iv_dish = findViewById(R.id.iv_recipe_dish);
        TextView name = findViewById(R.id.tv_name_dish);
        TextView description = findViewById(R.id.tv_description_dish);
        TextView recipe = findViewById(R.id.tv_recipe_dish);
        TextView ingredients = findViewById(R.id.tv_ingredients);



        iv_dish.setImageResource(dish.getImageDish());
        name.setText(dish.getNameDish());
        description.setText(dish.getDescriptionDish());
        recipe.setText(dish.getRecipe());
        ingredients.setText(dish.getDescriptionDish());
    }

    private void getDataFromIntent(Dish dish) {
        Intent intent = getIntent();
        if(intent != null){
            dish.setNameDish(intent.getStringExtra("name"));
            dish.setDescriptionDish(intent.getStringExtra("description"));
            dish.setRecipe(intent.getStringExtra("recipe"));
            dish.setImageDish(intent.getIntExtra("imageResource", 1));




        }
    }
}
