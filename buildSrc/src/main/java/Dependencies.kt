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
    const val supportPreference = "28.0.0-alpha1"

    // Jetpack
    const val androidX = "1.2.0-alpha01"
    const val androidXLegacy = "1.0.0"
    const val navigationVersion = "2.1.0-alpha03"

    // Rx
    const val rxJava = "2.2.8"
    const val rxAndroid = "2.1.1"

    // Retrofit
    const val retrofit = "2.3.0"
    const val retrofitRxAdapter = "2.5.0"
    const val loggingInterceptor = "3.4.1"

    // Dagger 2
    const val dagger = "2.17"

    // Room
    const val room = "1.1.1"

    // Joda Time
    const val jodaTime = "2.10.2"

    // Test
    const val androidXEspresso = "3.1.1"
    const val androidXTestRunner = "1.1.1"
    const val jUnit = "4.12"
}

object Deps {


    // Plugins
    const val kotlinGradlePlugin = "org.jetbrains.kotlin:kotlin-gradle-plugin:${Versions.kotlin}"
    const val androidGradlePlugin = "com.android.tools.build:gradle:${Versions.androidGradlePlugin}"
    const val safeArgs = "androidx.navigation:navigation-safe-args-gradle-plugin:${Versions.navigationVersion}"

    // Android
    const val androidSupportLegacy = "androidx.legacy:legacy-support-v4:${Versions.androidXLegacy}"
    const val appComppat = "androidx.appcompat:appcompat:${Versions.supportLib}"
    const val androidMaterial = "com.google.android.material:material:${Versions.material}"
    const val constraintLayout = "androidx.constraintlayout:constraintlayout:${Versions.constraintLayout}"

    const val navigationUI = "androidx.navigation:navigation-ui:${Versions.navigationVersion}"
    const val lifecycleExtensions = "androidx.lifecycle:lifecycle-extensions:${Versions.navigationVersion}"

    const val androidXCore = "androidx.core:core-ktx:${Versions.androidX}"
    const val androidSupportPreference = "com.android.support:preference-v7:${Versions.supportPreference}"
    const val navigationFragment = "androidx.navigation:navigation-fragment-ktx:${Versions.navigationVersion}"
    const val lifecycleViewModel = "androidx.lifecycle:lifecycle-viewmodel-ktx:${Versions.navigationVersion}"

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

    // Dagger 2
    const val daggerAndroid = "com.google.dagger:dagger-android:${Versions.dagger}"
    const val daggerSupport = "com.google.dagger:dagger-android-support:${Versions.dagger}"
    const val daggerProcessor = "com.google.dagger:dagger-android-processor:${Versions.dagger}"
    const val daggerCompiler = "com.google.dagger:dagger-compiler:${Versions.dagger}"

    // Room
    const val archRoom = "android.arch.persistence.room:runtime:${Versions.room}"
    const val archRoomRx = "android.arch.persistence.room:rxjava2:${Versions.room}"
    const val archRoomCompiler = "android.arch.persistence.room:compiler:${Versions.room}"

    // Joda
    const val jodaTime = "joda-time:joda-time:${Versions.jodaTime}"

    // Glide
    const val glide = "com.github.bumptech.glide:glide:4.9.0"
    const val glideCompiler = "com.github.bumptech.glide:compiler:4.9.0"

    // Test
    const val jUnit = "junit:junit:${Versions.jUnit}"
    const val androidXTestRunner = "androidx.test:runner:${Versions.androidXTestRunner}"
    const val androidXEspresso = "androidx.test.espresso:espresso-core:${Versions.androidXEspresso}"
}
