package com.example.demo2.data.model

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.setValue


// 음식 데이터 클래스
data class FoodItem(
    val name: String,       // 음식 이름
    val totalCount: Int,    // 하루 목표 총 섭취 횟수
    val remainingInit: Int  // 앱 시작 시 남은 횟수 초기값
) {
    // Compose 상태로 관리되는 남은 횟수
    // mutableIntStateOf 덕분에 값이 변하면 Compose가 자동으로 UI를 갱신
    var remaining by mutableIntStateOf(remainingInit)

    companion object {
        // 앱 최초 실행 시 기본 제공되는 음식 리스트
        // DataStore에 값이 저장되어 있지 않으면 이 리스트를 사용
        val defaultList = listOf(
            FoodItem("프로틴", 2, 2),          // 이름, 총 횟수, 초기 남은 횟수
            FoodItem("계란", 2, 2),
            FoodItem("생선", 1, 1),
            FoodItem("프로틴시리얼", 1, 1),
            FoodItem("닭가슴살", 1, 1)
        )
    }
}
