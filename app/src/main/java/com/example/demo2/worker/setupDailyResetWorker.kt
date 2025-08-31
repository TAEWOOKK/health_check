package com.example.demo2.worker

import android.content.Context
import androidx.work.ExistingPeriodicWorkPolicy
import androidx.work.PeriodicWorkRequestBuilder
import androidx.work.WorkManager
import java.util.concurrent.TimeUnit

fun setupDailyResetWorker(context: Context) {
    val workRequest = PeriodicWorkRequestBuilder<ResetFoodsWorker>(
        24, TimeUnit.HOURS
    )
        .setInitialDelay(calculateDelayTo3AM(), TimeUnit.MILLISECONDS)
        .build()

    WorkManager.getInstance(context).enqueueUniquePeriodicWork(
        "daily_reset",
        ExistingPeriodicWorkPolicy.REPLACE,
        workRequest
    )
}
