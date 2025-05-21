plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    id("kotlin-parcelize")
    id("com.google.devtools.ksp")
    id ("androidx.navigation.safeargs")
}

android {
    namespace = "com.example.savingbyshopping"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.example.savingbyshopping"
        minSdk = 26
        targetSdk = 35
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
    buildFeatures {
        viewBinding = true
    }
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.activity)
    implementation(libs.androidx.constraintlayout)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    testImplementation(libs.mockito.core)
    testImplementation(kotlin("test"))
    implementation(libs.androidx.lifecycle.viewmodel.ktx)
    testImplementation(libs.kotlin.test.junit) // Update version if a newer one is available
    testImplementation(libs.mockito.core.v531) // mock final class
    testImplementation(libs.androidx.core.testing)
    testImplementation(libs.kotlinx.coroutines.test) // atau versi terbaru
    implementation (libs.androidx.constraintlayout.v214)
    implementation ("com.google.android.material:material:1.12.0")
    implementation(libs.androidx.navigation.fragment.ktx)
    implementation(libs.navigation.ui.ktx)



    // room 
    ksp(libs.androidx.room.compiler)
    implementation(libs.androidx.room.ktx)

}