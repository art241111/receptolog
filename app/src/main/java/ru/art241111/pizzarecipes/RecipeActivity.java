package ru.art241111.pizzarecipes;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class RecipeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe);

        TextView name = findViewById(R.id.tv_name_dish);
        TextView description = findViewById(R.id.tv_description_dish);
        TextView recipe = findViewById(R.id.tv_recipe_dish);

        Intent intent = getIntent();
        if(intent != null){
            name.setText(intent.getStringExtra("name"));
            description.setText(intent.getStringExtra("description"));
            recipe.setText(intent.getStringExtra("recipe"));
        }
    }
}
