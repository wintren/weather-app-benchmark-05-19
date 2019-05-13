plugins {
    id("com.android.application")
    id("kotlin-android")
    id("kotlin-android-extensions")
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
    }
    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }
}

dependencies {
    implementation(fileTree(mapOf("dir" to "libs", "include" to listOf("*.jar"))))
    implementation(Deps.kotlin)

    // Android X
    implementation(Deps.androidXCore)
    implementation(Deps.androidXLegacy)

    // Android / Support
    implementation(Deps.appComppat)
    implementation(Deps.androidMaterial)
    implementation(Deps.constraintLayout)

    // Test
    testImplementation(Deps.jUnit)
    androidTestImplementation(Deps.androidXTestRunner)
    androidTestImplementation(Deps.androidXEspresso)
}
