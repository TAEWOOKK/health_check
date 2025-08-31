package com.example.demo2.data.model


// 음식 데이터 클래스
data class FoodItem(
    val name: String,
    val totalCount: Int,
    var remaining: Int
) {
    companion object {
        // 앱 최초 실행 시 기본 제공되는 음식 리스트
        val defaultList = listOf(
            FoodItem("프로틴", 2, 2),
            FoodItem("계란", 2, 2),
            FoodItem("생선", 1, 1),
            FoodItem("프로틴시리얼", 1, 1),
            FoodItem("닭가슴살", 1, 1)
        )
    }
}
