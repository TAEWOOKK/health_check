package com.example.demo2.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import com.example.demo2.ui.main.MainScreen
import com.example.demo2.ui.splash.SplashScreen
import com.example.demo2.worker.setupDailyResetWorker

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setupDailyResetWorker(this)

        // ✅ XML 대신 setContent 안에서 UI를 직접 작성한다!
        setContent {
            var showSplash by remember { mutableStateOf(true) }

            if (showSplash) {
                SplashScreen { showSplash = false }
            } else {
                MainScreen(context = this) // 기존 버튼 화면
            }
        }
    }
}