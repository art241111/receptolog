package ru.art241111.dish_recipes.ui.mainActivity.createArray;

import java.util.ArrayList;

import ru.art241111.dish_recipes.R;
import ru.art241111.dish_recipes.models.Dish;

import static ru.art241111.dish_recipes.utils.constantSting.*;

/**
 * Тоже времеенный класс, который добавляет в массив значения рецептов
 * В будующем бдет заменен на подгрузку данных из БД
 */
public class addItemsToArray {
    public static void addItemsToArrayList(ArrayList<Dish> dishArrayList) {
        dishArrayList.add(new Dish(R.drawable.marghuerita,PIZZA_MARGHUERITA_NAME,PIZZA_MARGHUERITA_DESCRIPTION, PIZZA_MARGHUERITA_RECIPE));
        dishArrayList.add(new Dish(R.drawable.margherita_in_four, PIZZA_MARGHUERITA_4_NAME,PIZZA_MARGHUERITA_4_DESCRIPTION,PIZZA_MARGHUERITA_4_RECIPE));
        dishArrayList.add(new Dish(R.drawable.ceramelised, PIZZA_CERAMELISED_NAME,PIZZA_CERAMELISED_DESCRIPTION,PIZZA_CERAMELISED_RECIPE));
        dishArrayList.add(new Dish(R.drawable.superhealthy, PIZZA_SUPERHEALTHY_NAME,PIZZA_SUPERHEALTHY_DESCRIPTION,PIZZA_SUPERHEALTHY_RECIPE));
    }
}
