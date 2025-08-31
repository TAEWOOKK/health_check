package com.example.demo2.ui.splash

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.graphics.Color
import com.example.demo2.R
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(onTimeout: () -> Unit) {
    var visible by remember { mutableStateOf(false) }
    var finished by remember { mutableStateOf(false) }

    // 애니메이션 시작 & 전환 타이밍 제어
    LaunchedEffect(Unit) {
        visible = true
        delay(2000)     // 로고+텍스트 보여주기
        visible = false // fadeOut 시작
        delay(800)      // fadeOut 끝날 때까지 대기
        finished = true
        onTimeout()     // Main 화면으로 이동
    }

    // fadeIn + fadeOut 애니메이션
    AnimatedVisibility(
        visible = visible,
        enter = fadeIn(),
        exit = fadeOut()
    ) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                // 로고
                Image(
                    painter = painterResource(id = R.drawable.logo), // drawable/logo.png 필요
                    contentDescription = "Logo",
                    modifier = Modifier.size(180.dp)
                )
                Spacer(modifier = Modifier.height(16.dp))
                // 제작사 이름
                Text(
                    text = "MINI YOUJINI",
                    fontSize = 20.sp,
                    color = Color.Gray,
                    modifier = Modifier.alpha(0.8f)
                )
            }
        }
    }
}
