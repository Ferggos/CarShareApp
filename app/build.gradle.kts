plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    id("com.google.devtools.ksp")
    id("org.jetbrains.kotlin.plugin.serialization") version "2.0.20"
    id("com.google.dagger.hilt.android")
}

android {
    namespace = "com.example.testapp"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.testapp"
        minSdk = 30
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"

    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }

    buildFeatures{
        viewBinding = true
    }
}

dependencies {
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.activity)
    implementation(libs.androidx.constraintlayout)
    implementation(libs.dotsindicator)
    implementation (libs.kotlin.utils)
    implementation(libs.androidx.legacy.support.v4)
    implementation(libs.androidx.recyclerview)
    implementation (libs.androidx.lifecycle.viewmodel.ktx)
    implementation (libs.androidx.room.runtime)
    implementation(libs.androidx.fragment.ktx)
    implementation (libs.androidx.room.ktx)
    implementation (libs.mindrot.jbcrypt)
    //implementation(libs.mediation.test.suite)
    implementation ("io.github.jan-tennert.supabase:postgrest-kt:3.0.1")
    implementation ("io.github.jan-tennert.supabase:storage-kt:3.0.1")
    implementation ("io.github.jan-tennert.supabase:auth-kt:3.0.1")
    implementation ("io.ktor:ktor-client-android:3.0.0")
    implementation ("io.ktor:ktor-client-core:3.0.0")
    implementation ("io.ktor:ktor-utils:3.0.0")
    implementation ("com.github.bumptech.glide:glide:4.15.1")
    implementation("com.google.dagger:hilt-android:2.48")
    ksp ("com.google.dagger:hilt-compiler:2.48")
    implementation(libs.kotlinx.serialization.json)
    implementation(libs.ktor.client.cio)
    ksp(libs.androidx.room.compiler)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
}
