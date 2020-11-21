package com.udacity.mybakingapp.model;

import androidx.lifecycle.LiveData;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.udacity.mybakingapp.network.responses.ApiResponse;
import com.udacity.mybakingapp.persistence.RecipePersistenceModel;

import java.util.List;

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
public class ResponseModel {


    private LiveData<ApiResponse<List<RecipeModel>>> recipes = null;

    public LiveData<ApiResponse<List<RecipeModel>>> getRecipes() {
        return recipes;
    }

    public void setRecipes(LiveData<ApiResponse<List<RecipeModel>>> ingredients) {
        this.recipes = ingredients;
    }
}
