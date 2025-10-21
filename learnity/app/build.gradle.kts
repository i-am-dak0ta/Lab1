import com.android.build.api.variant.impl.VariantOutputImpl
import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
    id("kotlin-kapt")
    alias(libs.plugins.ksp)
    id("base-convention")
}

val javaVersion: String by project
val vrsCode: String by project
val vrsName: String by project
val appNameFile: String by project

android {
    namespace = "com.dak0ta.learnity.app"
    compileSdk = libs.versions.compileSdk.get().toInt()

    defaultConfig {
        applicationId = "com.dak0ta.learnity.app"
        minSdk = libs.versions.minSdk.get().toInt()
        targetSdk = libs.versions.targetSdk.get().toInt()
        versionCode = vrsCode.toInt()
        versionName = vrsName
    }

    compileOptions {
        sourceCompatibility = JavaVersion.toVersion(javaVersion)
        targetCompatibility = JavaVersion.toVersion(javaVersion)
    }

    kotlin {
        compilerOptions {
            jvmTarget.set(JvmTarget.JVM_17)
        }
    }

    buildFeatures {
        compose = true
    }
}

androidComponents {
    onVariants { variant ->
        variant.outputs.filterIsInstance<VariantOutputImpl>().forEach { output ->
            output.outputFileName.set(
                "$appNameFile-${variant.buildType}-$vrsName-$vrsCode.apk",
            )
        }
    }
}

dependencies {
    implementation(projects.feature.authorization.domain)
    implementation(projects.feature.authorization.data)
    implementation(projects.feature.authorization.presentation)
    implementation(projects.feature.home.domain)
    implementation(projects.feature.home.data)
    implementation(projects.feature.home.presentation)
    implementation(projects.feature.profile.domain)
    implementation(projects.feature.profile.data)
    implementation(projects.feature.profile.presentation)
    implementation(projects.feature.settings.domain)
    implementation(projects.feature.settings.presentation)

    implementation(projects.core.coroutine)
    implementation(projects.core.domain)
    implementation(projects.core.di)
    implementation(projects.core.navigation)
    implementation(projects.core.navigationCompose)
    implementation(projects.core.mvvm)
    implementation(projects.core.database.data)
    implementation(projects.core.database.domain)
    implementation(projects.core.datastore.data)
    implementation(projects.core.datastore.domain)
    implementation(projects.core.network.data)
    implementation(projects.core.network.domain)

    kapt(libs.dagger.compiler)
    implementation(libs.retrofit)
    implementation(libs.retrofit.converter.moshi)
    implementation(libs.okhttp)
    implementation(libs.logging.interceptor)
    implementation(libs.moshi)
    implementation(libs.moshi.kotlin)
    ksp(libs.moshi.kotlin.codegen)

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.compose.material.iconsExtended)
    implementation(libs.androidx.compose.ui)
    implementation(libs.androidx.compose.ui.graphics)
    implementation(libs.androidx.compose.ui.tooling.preview)
    implementation(libs.androidx.compose.material3)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.compose.ui.test.junit4)
    debugImplementation(libs.androidx.compose.ui.tooling)
    debugImplementation(libs.androidx.compose.ui.test.manifest)
}
