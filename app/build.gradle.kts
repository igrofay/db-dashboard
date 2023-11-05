plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("com.google.gms.google-services")
}

android {
    namespace = "five.head.dbdashboard"
    compileSdk = 34

    defaultConfig {
        applicationId = "five.head.dbdashboard"
        minSdk = 24
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
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.2"
    }
}

dependencies {
    implementation(kotlin("reflect"))
    implementation(project(":core"))
    // Android core
    implementation("androidx.core:core-ktx:1.12.0")
    implementation("com.google.firebase:firebase-crashlytics-buildtools:2.9.9")
    // Test
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
    // Compose
    val composeBom = platform("androidx.compose:compose-bom:2023.01.00")
    implementation(composeBom)
    androidTestImplementation(composeBom)
    implementation("androidx.compose.material:material")
    implementation("androidx.activity:activity-compose:1.8.0")
    implementation("androidx.lifecycle:lifecycle-viewmodel-compose:2.6.2")
    debugImplementation("androidx.compose.ui:ui-tooling")
    implementation("androidx.compose.ui:ui-tooling-preview")
    // Nav compose
    implementation("androidx.navigation:navigation-compose:2.7.4")
    // Kodein
    implementation("org.kodein.di:kodein-di-framework-compose:7.19.0")
    // Orbit-MVI
    implementation("org.orbit-mvi:orbit-compose:6.1.0")
    // Vico
    implementation("com.patrykandpatrick.vico:compose:1.12.0")
    // firebase
    implementation(platform("com.google.firebase:firebase-bom:32.5.0"))
    implementation("com.google.firebase:firebase-messaging")
}