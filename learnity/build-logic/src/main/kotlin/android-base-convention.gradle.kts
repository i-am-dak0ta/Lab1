import com.android.build.gradle.BaseExtension
import com.dak0ta.learnity.buildlogic.libs

plugins {
    id("base-convention")
}

val javaVersion: String by project

configure<BaseExtension> {
    compileSdkVersion(libs.versions.compileSdk.get().toInt())

    defaultConfig {
        minSdk = libs.versions.minSdk.get().toInt()
        targetSdk = libs.versions.targetSdk.get().toInt()
    }

    compileOptions {
        sourceCompatibility = JavaVersion.toVersion(javaVersion)
        targetCompatibility = JavaVersion.toVersion(javaVersion)
    }
}
