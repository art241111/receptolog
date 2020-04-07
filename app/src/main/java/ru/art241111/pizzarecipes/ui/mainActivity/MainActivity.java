package ru.art241111.pizzarecipes.ui.mainActivity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import ru.art241111.pizzarecipes.models.Dish;
import ru.art241111.pizzarecipes.R;
import ru.art241111.pizzarecipes.adapters.RecyclerViewAdapter;

import android.os.Bundle;

import java.util.ArrayList;

import static ru.art241111.pizzarecipes.utils.constantSting.*;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    RecyclerView.Adapter adapter;
    RecyclerView.LayoutManager layoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ArrayList<Dish> dishArrayList = new ArrayList<>();
        addItemsToArrayList(dishArrayList);

        recyclerView = findViewById(R.id.rv_dish);
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
