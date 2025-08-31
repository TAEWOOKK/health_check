# Demo2 - 식단 체크 앱

이 앱은 하루 동안 먹어야 할 음식을 체크하고, 앱을 종료해도 상태를 저장/복원할 수 있도록 만든 간단한 식단 관리 앱입니다.  
구글의 **Jetpack Compose**와 **DataStore(Preferences)** 를 활용하여 상태를 로컬에 저장합니다.

---

## 📂 프로젝트 구조

```
com.example.demo2
│
├── data
│ ├── model
│ │ └── FoodItem.kt // 음식 데이터 클래스 + 기본 식단 목록 (defaultList)
│ └── repository
│ └─└── FoodRepository.kt // DataStore를 활용한 저장/불러오기/초기화 로직
│
├── ui
│ └── main
│ └── MyFirstScreen.kt // 실제 UI 화면 (식단 체크, 초기화 버튼 포함)
│
└── MainActivity.kt // 앱의 진입점 (MyFirstScreen 호출)
```


---

## 📦 주요 기능

1. **음식 체크**
    - 음식 항목 옆의 `먹음` 버튼을 누르면 남은 횟수가 감소합니다.
    - 다 먹으면 `완료` 상태로 변경됩니다.

2. **상태 저장**
    - DataStore를 사용해 앱을 종료해도 체크 상태가 유지됩니다.

3. **초기화 버튼**
    - 모든 음식을 다시 처음 상태로 리셋할 수 있습니다.

---

## 🛠 기술 스택

- **Kotlin**
- **Jetpack Compose**
- **DataStore (Preferences)**
- **Material 3 (M3 Components)**

---

## 🚀 실행 방법

1. Android Studio에서 이 프로젝트를 열기
2. 앱을 실행하면 `오늘 먹어야 할 음식 체크` 화면이 표시됩니다
3. 버튼을 눌러 음식을 체크하거나 `초기화` 버튼으로 리셋할 수 있습니다

---

## 📖 코드 설명

### FoodItem.kt
```kotlin
data class FoodItem(
    val name: String,
    val totalCount: Int,
    var remaining: Int
) {
    companion object {
        val defaultList = listOf(
            FoodItem("프로틴", 2, 2),
            FoodItem("계란", 2, 2),
            FoodItem("생선", 1, 1),
            FoodItem("프로틴시리얼", 1, 1),
            FoodItem("닭가슴살", 1, 1)
        )
    }
}
