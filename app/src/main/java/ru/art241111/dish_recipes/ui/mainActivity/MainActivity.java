package ru.art241111.dish_recipes.ui.mainActivity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import ru.art241111.dish_recipes.models.Dish;
import ru.art241111.dish_recipes.R;
import ru.art241111.dish_recipes.adapters.RecyclerViewAdapter;

import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.ArrayList;

import static ru.art241111.dish_recipes.utils.constantSting.*;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    RecyclerView.Adapter adapter;
    RecyclerView.LayoutManager layoutManager;

    EditText et_ingredients;

    ArrayList<String> ingredients;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ingredients = new ArrayList<>();

        et_ingredients = findViewById(R.id.et_ingredients);
        setListenerOnET(et_ingredients);

        setListenerOnButton((Button) findViewById(R.id.bt_add_ingredients));

        ArrayList<Dish> dishArrayList = new ArrayList<>();
        addItemsToArrayList(dishArrayList);

        recyclerView = findViewById(R.id.rv_dish);
        customizationRecyclerView(dishArrayList);

    }

    private void addIngredients(){
        ingredients.add(et_ingredients.getText().toString());
    }

    private void setListenerOnButton(Button bt_add_ingredients) {
        bt_add_ingredients.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addIngredients();
            }
        });
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

    private void customizationRecyclerView(ArrayList<Dish> dishArrayList) {
        recyclerView.setHasFixedSize(true);

        adapter = new RecyclerViewAdapter(dishArrayList, this);
        layoutManager = new LinearLayoutManager(this);

        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(layoutManager);
    }

    private void addItemsToArrayList(ArrayList<Dish> dishArrayList) {
        dishArrayList.add(new Dish(R.drawable.marghuerita,PIZZA_MARGHUERITA_NAME,PIZZA_MARGHUERITA_DESCRIPTION, PIZZA_MARGHUERITA_RECIPE));
        dishArrayList.add(new Dish(R.drawable.margherita_in_four, PIZZA_MARGHUERITA_4_NAME,PIZZA_MARGHUERITA_4_DESCRIPTION,PIZZA_MARGHUERITA_4_RECIPE));
        dishArrayList.add(new Dish(R.drawable.ceramelised, PIZZA_CERAMELISED_NAME,PIZZA_CERAMELISED_DESCRIPTION,PIZZA_CERAMELISED_RECIPE));
        dishArrayList.add(new Dish(R.drawable.superhealthy, PIZZA_SUPERHEALTHY_NAME,PIZZA_SUPERHEALTHY_DESCRIPTION,PIZZA_SUPERHEALTHY_RECIPE));
    }
}
