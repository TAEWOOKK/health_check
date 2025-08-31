package com.example.demo2.worker

import java.util.Calendar

fun calculateDelayTo3AM(): Long {
    val now = Calendar.getInstance()
    val next3AM = Calendar.getInstance().apply {
        set(Calendar.HOUR_OF_DAY, 3)
        set(Calendar.MINUTE, 0)
        set(Calendar.SECOND, 0)
        set(Calendar.MILLISECOND, 0)
        if (before(now)) {
            add(Calendar.DAY_OF_MONTH, 1) // 오늘 3시가 지났으면 다음 날로
        }
    }
    return next3AM.timeInMillis - now.timeInMillis
}
