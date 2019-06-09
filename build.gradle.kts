// Top-level build file where you can add configuration options common to all sub-projects/modules.

buildscript {
    repositories {
        google()
        jcenter()

    }
    dependencies {
        classpath(Deps.androidGradlePlugin)
        classpath(Deps.kotlinGradlePlugin)
        classpath(Deps.safeArgs)
    }
}

allprojects {
    repositories {
        google()
        jcenter()
    }
}

tasks{
val clean by registering(Delete::class) {
    delete (buildDir)
}

}
