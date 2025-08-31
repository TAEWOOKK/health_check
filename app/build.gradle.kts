plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)

    // ✅ 코틀린 2.0 전용 Compose 컴파일러 플러그인
    id("org.jetbrains.kotlin.plugin.compose") version "2.0.0"
}

android {
    namespace = "com.example.demo2"
    compileSdk = 36

    defaultConfig {
        applicationId = "com.example.demo2"
        minSdk = 26
        targetSdk = 36
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    // ✅ Compose 켜기
    buildFeatures {
        compose = true
    }

    kotlinOptions {
        jvmTarget = "11"
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
}


dependencies {

    // Compose BOM (버전 통일)
    implementation(platform("androidx.compose:compose-bom:2024.08.00"))
    // AndroidX 기본 라이브러리
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation("androidx.datastore:datastore-preferences:1.0.0")
    implementation("com.google.android.material:material:1.9.0")
    implementation("androidx.activity:activity-compose:1.9.0")
    implementation(libs.androidx.foundation.layout)
    implementation(libs.androidx.material3) // ✅ 이거 추가
    implementation("androidx.work:work-runtime-ktx:2.8.1")


    // 테스트 라이브러리
    testImplementation(libs.junit)                         // 단위 테스트
    androidTestImplementation(libs.androidx.junit)        // 안드로이드 테스트

    // Compose UI & Material3
    implementation("androidx.compose.ui:ui")
    implementation("androidx.compose.material3:material3")
    implementation("androidx.compose.ui:ui-tooling-preview")

    // Compose 개발용 툴
    debugImplementation("androidx.compose.ui:ui-tooling")
    debugImplementation("androidx.compose.ui:ui-test-manifest")
}