package ru.art241111.dish_recipes.models.API;

import java.util.ArrayList;

import ru.art241111.dish_recipes.R;
import ru.art241111.dish_recipes.data.FullDish;

import static ru.art241111.dish_recipes.utils.createDish.*;

/**
 * Тоже времеенный класс, который добавляет в массив значения рецептов
 * В будующем бдет заменен на подгрузку данных из БД
 */
public class getDishes {
    public static ArrayList<FullDish> getDishes() {
        ArrayList<FullDish> fullDishArrayList = new ArrayList<>();
        fullDishArrayList.add(new FullDish(R.drawable.marghuerita,PIZZA_MARGHUERITA_NAME,PIZZA_MARGHUERITA_DESCRIPTION, PIZZA_MARGHUERITA_RECIPE));
        fullDishArrayList.add(new FullDish(R.drawable.margherita_in_four, PIZZA_MARGHUERITA_4_NAME,PIZZA_MARGHUERITA_4_DESCRIPTION,PIZZA_MARGHUERITA_4_RECIPE));
        fullDishArrayList.add(new FullDish(R.drawable.ceramelised, PIZZA_CERAMELISED_NAME,PIZZA_CERAMELISED_DESCRIPTION,PIZZA_CERAMELISED_RECIPE));
        fullDishArrayList.add(new FullDish(R.drawable.superhealthy, PIZZA_SUPERHEALTHY_NAME,PIZZA_SUPERHEALTHY_DESCRIPTION,PIZZA_SUPERHEALTHY_RECIPE));
        return fullDishArrayList;
    }
}
