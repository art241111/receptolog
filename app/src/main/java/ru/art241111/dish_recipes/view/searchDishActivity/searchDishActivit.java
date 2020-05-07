package ru.art241111.dish_recipes.view.searchDishActivity;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import ru.art241111.dish_recipes.R;
import ru.art241111.dish_recipes.adapters.RecyclerViewAdapter;
import ru.art241111.dish_recipes.data.FullDish;

import android.os.Bundle;

import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import org.apmem.tools.layouts.FlowLayout;

import java.util.ArrayList;

import static ru.art241111.dish_recipes.models.API.getDishes.getDishes;


public class searchDishActivit extends AppCompatActivity {

    RecyclerView recyclerView;
    RecyclerView.Adapter adapter;
    RecyclerView.LayoutManager layoutManager;
    FlowLayout tableIngredients;

    EditText et_ingredients;

    ArrayList<String> ingredients;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_dish);

        tableIngredients = findViewById(R.id.fl_list_ingredients);

        ingredients = new ArrayList<>();
        restoringValuesOfIngredients(savedInstanceState);

        et_ingredients = findViewById(R.id.et_ingredients);
        setListenerOnET(et_ingredients);
        setListenerOnButton(findViewById(R.id.ib_add_ingredients));

        ArrayList<FullDish> fullDishArrayList = getDishes();

        recyclerView = findViewById(R.id.rv_dish);
        customizationRecyclerView(fullDishArrayList);
    }

    private void setListenerOnButton(View button) {
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addIngredients();
            }
        });
    }

    private void restoringValuesOfIngredients(Bundle savedInstanceState) {
        if(savedInstanceState != null){
            ingredients = savedInstanceState.getStringArrayList("ingredients");
            assert ingredients != null;
            for (int i = 0; i < ingredients.size(); i++) {
                createIngredientView(ingredients.get(i));
            }
        }
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putStringArrayList("ingredients", ingredients);
    }



    private void setListenerOnET(final EditText editText) {
        editText.setOnKeyListener(new View.OnKeyListener()  {
                                      public boolean onKey(View v, int keyCode, KeyEvent event) {
                                          if(event.getAction() == KeyEvent.ACTION_DOWN &&
                                                  (keyCode == KeyEvent.KEYCODE_ENTER))
                                          {
                                              // сохраняем текст, введенный до нажатия Enter в переменную
                                              addIngredients();
                                              return true;
                                          }
                                          return false;
                                      }
                                  }
        );
    }
    private void addIngredients(){
        String ingredientName = et_ingredients.getText().toString().trim();

        ingredients.add(ingredientName);
        createIngredientView(ingredientName);

        et_ingredients.setText("");
        }
        private void createIngredientView(String ingredientName){
            LayoutInflater inflater = getLayoutInflater();
            final View ingredient = inflater.inflate(R.layout.ingredient, null);

            fillingInDataToIngredientView(ingredient, ingredientName);
            tableIngredients.addView(ingredient);
            }
            private void fillingInDataToIngredientView(final View ingredient,final String ingredientName) {
                TextView textView = ingredient.findViewById(R.id.tv_name_ingredient);
                textView.setText(ingredientName);

                ImageView imageView = ingredient.findViewById(R.id.btn_delete);
                imageView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        tableIngredients.removeView(ingredient);
                        ingredients.remove(ingredientName);
                    }
                });
            }

    private void customizationRecyclerView(ArrayList<FullDish> dishArrayList) {
        recyclerView.setHasFixedSize(true);

        adapter = new RecyclerViewAdapter(dishArrayList, this);
        layoutManager = new LinearLayoutManager(this);

        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(layoutManager);
    }

}