plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)

    // CHANGED: Use 'id' instead of 'alias' to prevent "Unresolved Reference" errors
    id("com.google.gms.google-services")
    alias(libs.plugins.google.android.libraries.mapsplatform.secrets.gradle.plugin)
}

android {
    namespace = "com.example.fitx"
    compileSdk = 36

    defaultConfig {
        applicationId = "com.example.fitx"
        minSdk = 24
        targetSdk = 36
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
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = "11"
    }

    // View Binding enabled
    buildFeatures {
        viewBinding = true
    }
}

dependencies {
    // --- STANDARD LIBRARIES (Keep these as 'libs' if they were default) ---
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.activity)
    implementation(libs.androidx.constraintlayout)

    // --- FIREBASE SETUP ---
    // 1. BoM (Controls versions automatically)
    implementation(platform("com.google.firebase:firebase-bom:33.1.0"))

    // 2. Auth & Google Sign In (For Login)
    implementation("com.google.firebase:firebase-auth")
    implementation("com.google.android.gms:play-services-auth:21.0.0")

    // 3. Firestore (Database) - CHANGED to text to avoid 'libs' error
    implementation("com.google.firebase:firebase-firestore")

    // --- DASHBOARD EXTRAS ---
    implementation("com.airbnb.android:lottie:6.3.0")
    implementation("com.github.PhilJay:MPAndroidChart:v3.1.0")
    implementation(libs.play.services.maps)

    // --- TESTING ---
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
}