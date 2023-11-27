package com.example.foodApp.pref

import android.content.Context
import com.example.foodApp.models.ModelFood
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.lang.reflect.Type

class BasketSharedPreference (
    private val context: Context
){

    private val sharedPreference = context.getSharedPreferences(
        FoodSharedPreferences.SHARED_PREFERENCE_KEY, Context.MODE_PRIVATE
    )

    companion object {
        const val SHARED_PREFERENCE_KEY = "SHARED_PREFERENCE_KEY"
        const val SHARED_PREF = "YUSUF"
    }
    fun getFood(): List<ModelFood> {
        val gson = Gson()
        val json = sharedPreference.getString(FoodSharedPreferences.SHARED_PREF, null)
        val type: Type = object : TypeToken<ArrayList<ModelFood?>?>() {}.type
        val foodList = gson.fromJson<ArrayList<ModelFood>>(json, type)
        return foodList ?: emptyList()
    }

    fun saveFood(foodModel: ModelFood) {
        val foods = getFood().toMutableList()
        foods.add(0, foodModel)
        val notesGson = Gson().toJson(foods)
        sharedPreference.edit().putString(FoodSharedPreferences.SHARED_PREF, notesGson).apply()
    }

    fun deleteAllFood() {
        sharedPreference.edit().clear().apply()
    }

    fun deleteFoodByIndex(index: Int) {
        val getAllFood = getFood().toMutableList()
        if (index in 0 until getAllFood.size) {
            getAllFood.removeAt(index)
            val foodGsonString = Gson().toJson(getAllFood)
            sharedPreference.edit().putString(FoodSharedPreferences.SHARED_PREF, foodGsonString).apply()
        }
    }
}