package com.example.demo2.ui.main

import android.content.Context
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import androidx.compose.ui.unit.sp
import com.example.demo2.data.model.FoodItem
import com.example.demo2.data.repository.FoodRepository
import kotlinx.coroutines.launch

// 이 함수가 Compose UI 트리의 한 화면 단위임을 표시
@Composable
// Activity 등에서 넘겨준 Context 사용(DataStore 등에 필요)
fun MainScreen(context: Context) {
    // 한 번만 생성해 기억하는 리포지토리 인스턴스
    val repository = remember { FoodRepository(context) }
    // Compose 수명에 맞춘 코루틴 스코프(저장 등 비동기 작업에 사용)
    val scope = rememberCoroutineScope()
    // 화면에 바인딩되는 관찰 가능한 리스트 상태
    val foodList = remember { mutableStateListOf<FoodItem>() }

    // 화면이 처음 구성될 때 1회 실행
    LaunchedEffect(Unit) {
        // DataStore 에서 불러오고 없으면 defaultList 사용
        foodList.addAll(repository.loadFoods(FoodItem.Companion.defaultList))
    }

    // 화면 전체를 세로로 배치하는 컨테이너
    Column(
        // 내부 컴포넌트 가로 가운데 정렬
        horizontalAlignment = Alignment.Companion.CenterHorizontally,
        modifier = Modifier.Companion
            .fillMaxSize() // 세로/가로 모두 화면 채우기
            .padding(16.dp) // 바깥 여백
    ) {
        Text(
            "오늘 먹어야 할 음식 체크", // 화면 제목
            fontSize = 28.sp, // 글자 크기
            color = MaterialTheme.colorScheme.primary, // 테마의 대표 색상
            lineHeight = 8.em // 행간(조금 크게)
        )
        /*
        제목과 버튼 사이 간격
         */
        Spacer(modifier = Modifier.Companion.height(16.dp))

        /*
        초기화 버튼(모든 항목을 원래 횟수로 되돌림)
         */
        Button(
            onClick = {
                scope.launch { // 저장이 포함된 비동기 작업이므로 코루틴으로 수행
                    repository.resetFoods(foodList) // 리스트 상태 갱신 + DataStore에도 초기값 저장
                }
            },
            colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.secondary) // 버튼 배경색
        ) {
            Text("초기화", color = Color.Companion.White) // 버튼 라벨
        }

        /*
        버튼과 목록 사이 간격
         */
        Spacer(modifier = Modifier.Companion.height(16.dp))

        /*
        음식 목록 컨테이너
         */
        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(12.dp), // 아이템 간 간격 12dp
            modifier = Modifier.Companion.fillMaxWidth() // 가로 폭 가득
        ) {
            items(foodList) { food -> // foodList의 각 항목을 받아 Composable 그리기

                // 개별 항목 UI(카드, 텍스트, 먹음/완료 버튼 등) — 별도 파일의 Composable
                FoodRow(
                    // 현재 항목 데이터 전달
                    food = food,

                    // onClick 콜백: "먹음" 버튼을 눌렀을 때 실행할 로직
                    onClick = {
                        if (food.remaining > 0) { // 아직 남은 횟수가 있으면
                            food.remaining-- // 화면의 남은 횟수 1 감소(상태 변경 → UI 자동 갱신)
                            scope.launch { repository.saveFood(food) } // DataStore 에도 현재 남은 횟수 저장
                        }
                    }
                )
            }
        }
    }
}