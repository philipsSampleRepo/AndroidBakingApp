package com.udacity.mybakingapp.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.udacity.mybakingapp.model.RecipeModel;
import com.udacity.mybankingapp.R;

/**
 * © 2015 Visa.  This code is distributed pursuant to your Visa Mobile Application Developer License
 * Agreement and may be used solely in accordance with the terms and conditions set forth therein.
 * Visa provides this software on as "as is", "where is" basis, with all faults known and unknown.
 * Visa makes no warranty, express, statutory or implied, and explicitly disclaims the * *
 * warranties or merchantability, fitness for a particular purpose, any warranty of non-infringement
 * of any third party’s intellectual property rights, any warranty that the licensed * works will
 * meet the requirements of licensee or any other user, any warrantee that the software will be
 * error-free or will operate without interruption, and any warranty that the software will
 * interoperate with any licensee or third party hardware, software or systems. Visa undertakes
 * no obligation whatsoever to support or maintain all or any part of this software.
 * The software is not fault tolerant and is not designed, intended or authorized for use in any
 * medical, lifesaving or life sustaining systems, or any other application in which the failure
 * of the licensed work could create a situation where personal injury or death may occur.
 * <p>
 * All other rights are reserved.
 **/
public class RecipeAdapter extends ListAdapter<RecipeModel, RecipeAdapter.RecipeViewHolder> {


    private OnItemClickedListener itemListener;

    private static DiffUtil.ItemCallback<RecipeModel> CALL_BACK = new DiffUtil.ItemCallback<RecipeModel>() {
        @Override
        public boolean areItemsTheSame(@NonNull RecipeModel oldItem, @NonNull RecipeModel newItem) {
            return false;
        }

        @Override
        public boolean areContentsTheSame(@NonNull RecipeModel oldItem, @NonNull RecipeModel newItem) {
            return false;
        }
    };

    public RecipeAdapter() {
        super(CALL_BACK);
    }

    @NonNull
    @Override
    public RecipeAdapter.RecipeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recipe_item_view, parent, false);
        return new RecipeViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecipeViewHolder holder, int position) {
        RecipeModel recipeModel = getItem(position);

        if (recipeModel != null) {
            holder.servings.setText(String.valueOf(recipeModel.getServings()));
            holder.title.setText(recipeModel.getName());

            Glide.with(holder.recipeImage.getContext())
                    .load(recipeModel.getImage())
                    .placeholder(R.drawable.recipe_icon)
                    .error(R.drawable.recipe_icon)
                    .into(holder.recipeImage);
        }
    }

    public RecipeModel getRecipeAt(int position) {
        return getItem(position);
    }

    class RecipeViewHolder extends RecyclerView.ViewHolder {

        private TextView title;
        private TextView servings;
        private ImageView recipeImage;

        public RecipeViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.txt_title);
            servings = itemView.findViewById(R.id.txt_servings);
            recipeImage = itemView.findViewById(R.id.recipe_img);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int position = getAdapterPosition();
                    if (itemListener != null && RecyclerView.NO_POSITION != position) {
                        itemListener.onItemClicked(getItem(position));
                    }
                }
            });
        }
    }

   public interface OnItemClickedListener {
        void onItemClicked(RecipeModel model);
    }

    public void setOnItemClickedListener(OnItemClickedListener listener) {
        this.itemListener = listener;
    }
}
