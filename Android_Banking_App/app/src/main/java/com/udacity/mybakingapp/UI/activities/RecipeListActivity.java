package com.udacity.mybakingapp.UI.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.udacity.mybakingapp.adapter.RecipeAdapter;
import com.udacity.mybakingapp.model.RecipeModel;
import com.udacity.mybakingapp.persistence.RecipePersistenceModel;
import com.udacity.mybakingapp.utils.Resource;
import com.udacity.mybakingapp.viewmodels.RecipeListViewModel;
import com.udacity.mybankingapp.R;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class RecipeListActivity extends AppCompatActivity {
    private static final String TAG = "RecipeListActivity";

    @BindView(R.id.recipe_list)
    RecyclerView recipeList;
    private RecipeListViewModel mRecipeListViewModel;
    RecipeAdapter recipeAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        recipeAdapter = new RecipeAdapter();
        setListUI();
        setItemListener();
        mRecipeListViewModel = ViewModelProviders.of(this).get(RecipeListViewModel.class);
        mRecipeListViewModel.getRecipesFromNetwork();
        mRecipeListViewModel.getRecipes().observe(this, new Observer<Resource<List<RecipeModel>>>() {
            @Override
            public void onChanged(Resource<List<RecipeModel>> listResource) {
                if(listResource != null){
                    Log.d(TAG, "onChanged: status: " + listResource.status);

                    if(listResource.data != null){
                        switch (listResource.status){
                            case LOADING:{
                                Log.d(TAG, "onChanged: LOADING");
                                break;
                            }

                            case ERROR:{
                                Log.e(TAG, "onChanged: cannot refresh the cache. ERROR" );
                                Log.e(TAG, "onChanged: ERROR message: " + listResource.message );
                                Log.e(TAG, "onChanged: status: ERROR, #recipes: " + listResource.data.size());

                                break;
                            }

                            case SUCCESS:{
                                Log.d(TAG, "onChanged: cache has been refreshed.");
                                Log.d(TAG, "onChanged: status: SUCCESS, #Recipes: " + listResource.data.size());
                                break;
                            }
                        }
                    }
                }
            }
        });
    }

    private void setItemListener() {
        recipeAdapter.setOnItemClickedListener
                (model -> Log.i(TAG, "onItemClicked: " + model.getName()));
    }

    private void setListUI() {
        recipeList.setLayoutManager(new LinearLayoutManager(this));
        recipeList.setHasFixedSize(true);
        recipeList.setAdapter(recipeAdapter);
    }
}