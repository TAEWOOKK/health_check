package com.example.demo2.data.repository

import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.example.demo2.data.model.FoodItem
import kotlinx.coroutines.flow.first

val Context.dataStore by preferencesDataStore(name = "food_prefs")

class FoodRepository(private val context: Context) {

    suspend fun loadFoods(defaultList: List<FoodItem>): List<FoodItem> {
        return defaultList.map { food ->
            val key = intPreferencesKey(food.name)
            val stored = context.dataStore.data.first()[key] ?: food.totalCount
            food.copy(remaining = stored)
        }
    }

    suspend fun saveFood(food: FoodItem) {
        val key = intPreferencesKey(food.name)
        context.dataStore.edit { prefs ->
            prefs[key] = food.remaining
        }
    }

    suspend fun resetFoods(foodList: List<FoodItem>) {
        context.dataStore.edit { prefs ->
            foodList.forEach { food ->
                food.remaining = food.totalCount
                val key = intPreferencesKey(food.name)
                prefs[key] = food.totalCount
            }
        }
    }
}