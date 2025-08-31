package com.example.demo2.worker

import android.content.Context
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.example.demo2.data.model.FoodItem
import com.example.demo2.data.repository.FoodRepository

class ResetFoodsWorker(
    context: Context,
    params: WorkerParameters
) : CoroutineWorker(context, params) {
    override suspend fun doWork(): Result {
        val repository = FoodRepository(applicationContext)
        val foodList = FoodItem.defaultList.toMutableList()
        repository.resetFoods(foodList)
        return Result.success()
    }
}