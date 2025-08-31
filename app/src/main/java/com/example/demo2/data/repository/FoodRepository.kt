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
        // defaultList의 각 FoodItem을 순회해서 DataStore에 저장된 값이 있으면 그 값으로, 없으면 기본값(totalCount)으로 남은횟수를 채워서 반환한다.
        return defaultList.map { food ->
            // DataStore에 저장할 때 사용할 key를 만든다. (예: "프로틴" 같은 이름으로 int 타입 key 생성)
            val key = intPreferencesKey(food.name)

            // context.dataStore.data는 Preferences의 Flow<Preferences> 이다.
            // .first()는 이 Flow에서 첫 번째 emitted Preferences 값을 한 번 받아오는 suspend 호출이다.
            // 따라서 stored에는 현재 DataStore에 저장된 값(또는 null)이 들어온다.
            val stored = context.dataStore.data.first()[key] ?: food.totalCount
            // stored가 null이면 ?: 연산자로 food.totalCount(기본값)를 사용한다.

            // 기존 food 객체의 remaining 값을 변경하지 않고, remaining이 채워진 새로운 FoodItem을 만들어 반환한다.
            // (불변성을 유지하고 싶을 때 유용)
            food.copy(remainingInit = stored)
        }
    }

    suspend fun saveFood(food: FoodItem) {
        // 저장할 때 사용할 key 생성
        val key = intPreferencesKey(food.name)

        // DataStore에 값을 저장하려면 edit 블록을 사용한다.
        // edit { prefs -> prefs[key] = value } 는 suspend 함수이고,
        // 내부는 Atomic(원자적)으로 Preferences를 수정하고 디스크에 반영한다.
        context.dataStore.edit { prefs ->
            prefs[key] = food.remaining
        }
    }

    suspend fun resetFoods(foodList: List<FoodItem>) {
        // 여러 항목을 한 번에 초기화해서 저장하려면 edit 블록 하나로 묶는 게 좋다.
        // 이렇게 하면 모든 키/값이 하나의 transaction처럼 처리되어 일관성이 보장된다.
        context.dataStore.edit { prefs ->
            // 전달된 리스트의 각 항목에 대해 반복
            foodList.forEach { food ->
                // UI에서 사용하는 객체를 바로 변경해서 화면에도 반영되게 한다(주의: 같은 인스턴스여야 UI에 반영됨).
                food.remaining = food.totalCount

                // 각 항목에 해당하는 key를 만들고 default 값(totalCount)을 저장
                val key = intPreferencesKey(food.name)
                prefs[key] = food.totalCount
            }
        }
    }
}