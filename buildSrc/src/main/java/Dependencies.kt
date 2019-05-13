@file:Suppress("SpellCheckingInspection")

object Versions {
    // App
    const val minSDK = 23
    const val targetSDK = 28
    const val compileSDK = 28
    const val versionCode = 1
    const val versionName = "1.0"

    // Plugins
    const val kotlin = "1.3.21"
    const val androidGradlePlugin = "3.4.0"

    // Android / Support
    const val supportLib = "1.0.2"
    const val constraintLayout = "1.1.3"
    const val material = "1.0.0"

    // Android X
    const val androidXCore = "1.0.2"
    const val androidXLegacy = "1.0.0"

    // Rx
    const val rxJava = "2.2.8"
    const val rxAndroid = "2.1.1"

    // Retrofit
    const val retrofit = "2.3.0"
    const val retrofitRxAdapter = "2.5.0"
    const val loggingInterceptor = "3.4.1"

    // Test
    const val androidXEspresso = "3.1.1"
    const val androidXTestRunner = "1.1.1"
    const val jUnit = "4.12"


}

object Deps {
    // Gradle Plugins
    const val kotlinGradlePlugin = "org.jetbrains.kotlin:kotlin-gradle-plugin:${Versions.kotlin}"
    const val androidGradlePlugin = "com.android.tools.build:gradle:${Versions.androidGradlePlugin}"

    // Android
    const val appComppat = "androidx.appcompat:appcompat:${Versions.supportLib}"
    const val androidXCore = "androidx.core:core-ktx:${Versions.androidXCore}"
    const val androidXLegacy = "androidx.legacy:legacy-support-v4:${Versions.androidXLegacy}"
    const val androidMaterial = "com.google.android.material:material:${Versions.material}"
    const val constraintLayout = "androidx.constraintlayout:constraintlayout:${Versions.constraintLayout}"

    // Kotlin
    const val kotlin = "org.jetbrains.kotlin:kotlin-stdlib-jdk7:${Versions.kotlin}"

    // Rx
    const val rxJava = "io.reactivex.rxjava2:rxjava:${Versions.rxJava}"
    const val rxAndroid = "io.reactivex.rxjava2:rxandroid:${Versions.rxAndroid}"

    // Retrofit
    const val retrofit = "com.squareup.retrofit2:retrofit:${Versions.retrofit}"
    const val gsonConverter = "com.squareup.retrofit2:converter-gson:${Versions.retrofit}"
    const val retrofitRxAdapter = "com.squareup.retrofit2:adapter-rxjava2:${Versions.retrofitRxAdapter}"
    const val loggingInterceptor = "com.squareup.okhttp3:logging-interceptor:${Versions.loggingInterceptor}"

    // Test
    const val jUnit = "junit:junit:${Versions.jUnit}"
    const val androidXTestRunner = "androidx.test:runner:${Versions.androidXTestRunner}"
    const val androidXEspresso = "androidx.test.espresso:espresso-core:${Versions.androidXEspresso}"
}