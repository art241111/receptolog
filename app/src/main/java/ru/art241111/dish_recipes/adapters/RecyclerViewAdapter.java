package ru.art241111.dish_recipes.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import ru.art241111.dish_recipes.data.FullDish;
import ru.art241111.dish_recipes.R;
import ru.art241111.dish_recipes.view.recipeActivity.RecipeActivity;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.RecyclerViewViewHolder> {
    private ArrayList<FullDish> dishArrayList;
    private Context context;

    public RecyclerViewAdapter(ArrayList<FullDish> arrayList, Context context) {
        this.dishArrayList = arrayList;
        this.context = context;
    }

    class RecyclerViewViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        ImageView iv_dish;
        TextView tv_name_dish;
        TextView tv_description_dish;

        RecyclerViewViewHolder(@NonNull View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);

            iv_dish = itemView.findViewById(R.id.iv_dish);
            tv_name_dish = itemView.findViewById(R.id.tv_name_dish);
            tv_description_dish = itemView.findViewById(R.id.tv_description_dish);
        }

        @Override
        public void onClick(View v) {
            int position = getAdapterPosition();
            FullDish dish = (FullDish) dishArrayList.get(position);

            Intent intent = new Intent(context, RecipeActivity.class);
            intent.putExtra("Dish", dish);

            context.startActivity(intent);
        }
    }

    @NonNull
    @Override
    public RecyclerViewViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext())
                                  .inflate(R.layout.recycler_view_item, viewGroup, false);

        return new RecyclerViewViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewViewHolder holder, int position) {
        FullDish fullDish = (FullDish)dishArrayList.get(position);

        holder.iv_dish.setImageResource(fullDish.getImageDish());
        holder.tv_name_dish.setText(fullDish.getNameDish());
        holder.tv_description_dish.setText(fullDish.getDescriptionDish());
    }

    @Override
    public int getItemCount() {
        return dishArrayList.size();
    }


}
