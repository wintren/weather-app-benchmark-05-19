@file:Suppress("SpellCheckingInspection")

object Versions {
    const val minSDK = 23
    const val targetSDK = 28
    const val compileSDK = 28
    const val versionCode = 1
    const val versionName = "1.0"

    const val kotlin = "1.3.21"
    const val androidGradlePlugin = "3.4.0"

    const val supportLib = "1.0.2"
    const val constraintLayout = "1.1.3"
    const val material = "1.0.0"

    const val androidXCore = "1.0.2"
    const val androidXLegacy = "1.0.0"

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

    const val kotlin = "org.jetbrains.kotlin:kotlin-stdlib-jdk7:${Versions.kotlin}"

    const val jUnit = "junit:junit:${Versions.jUnit}"
    const val androidXTestRunner = "androidx.test:runner:${Versions.androidXTestRunner}"
    const val androidXEspresso = "androidx.test.espresso:espresso-core:${Versions.androidXEspresso}"
}