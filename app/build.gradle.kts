plugins {
    id("com.android.application")
    id("kotlin-android")
    id("kotlin-android-extensions")
    id("kotlin-kapt")
    id("androidx.navigation.safeargs.kotlin")
}

android {
    compileSdkVersion(Versions.compileSDK)
    defaultConfig {
        applicationId = "se.wintren.freyr"
        minSdkVersion(Versions.minSDK)
        targetSdkVersion(Versions.targetSDK)
        versionCode = Versions.versionCode
        versionName = Versions.versionName
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"

        buildConfigField("String", "GOOGLE_API_KEY", property("Google_API_KEY") as String)
        buildConfigField("String", "OPEN_WEATHER_API_KEY", property("OpenWeather_API_KEY") as String)
    }

    testOptions {
        unitTests.isReturnDefaultValues = true
    }

    dataBinding.isEnabled = true

    buildTypes {
        getByName("debug") {

        }
        getByName("release") {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }
}

dependencies {
    implementation(fileTree(mapOf("dir" to "libs", "include" to listOf("*.jar"))))
    implementation(Deps.kotlin)

    // Android / Support
    implementation(Deps.androidXCore)
    implementation(Deps.androidSupportLegacy)
    implementation(Deps.appComppat)
    implementation(Deps.androidMaterial)
    implementation(Deps.constraintLayout)
    implementation(Deps.navigationFragment)
    implementation(Deps.navigationUI)
    implementation(Deps.lifecycleExtensions)
    implementation(Deps.lifecycleViewModel)
    implementation(Deps.androidSupportPreference)

    // Rx
    implementation(Deps.rxJava)
    implementation(Deps.rxAndroid)

    // Retrofit
    implementation(Deps.retrofit)
    implementation(Deps.gsonConverter)
    implementation(Deps.retrofitRxAdapter)
    implementation(Deps.loggingInterceptor)

    // Dagger 2
    implementation(Deps.daggerAndroid)
    implementation(Deps.daggerSupport)
    kapt(Deps.daggerCompiler)
    kapt(Deps.daggerProcessor)

    // Room
    implementation(Deps.archRoom)
    implementation(Deps.archRoomRx)
    kapt(Deps.archRoomCompiler)

    // Joda
    implementation(Deps.jodaTime)

    // Glide
    implementation(Deps.glide)
    kapt(Deps.glideCompiler)

    // Test
    testImplementation(Deps.jUnit)
    testImplementation(Deps.mockito)
    testImplementation(Deps.mockitoKotlin)
    testImplementation(Deps.archCoreTesting)

    debugImplementation(Deps.xFragmentTesting)
    androidTestImplementation(Deps.mockitoDexmaker)
    androidTestImplementation(Deps.archCoreTesting)
    androidTestImplementation(Deps.mockito)
    androidTestImplementation(Deps.mockitoKotlin)
    androidTestImplementation(Deps.xTestExtJUnit)
    androidTestImplementation(Deps.androidXTestRunner)
    androidTestImplementation(Deps.androidXEspresso)
}
