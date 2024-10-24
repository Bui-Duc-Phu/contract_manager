plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)

    //kapt
    kotlin("kapt")

    //hilt
    id("com.google.dagger.hilt.android")
}

android {
    namespace = "com.example.contract_app"
    compileSdk = 34

    

    defaultConfig {
        applicationId = "com.example.contract_app"
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

    viewBinding {
        enable=true
    }

}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.activity)
    implementation(libs.androidx.constraintlayout)
    implementation(libs.androidx.espresso.core)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)

    //room
    implementation (libs.androidx.room.runtime)
    implementation(libs.androidx.room.ktx)
    kapt( libs.androidx.room.compiler)

    //viewModel
    implementation (libs.androidx.lifecycle.viewmodel.ktx)
    implementation( libs.androidx.lifecycle.livedata.ktx)
    implementation (libs.gson)

    //paging
    implementation(libs.androidx.paging.runtime)

    //hilt
    implementation(libs.hilt.android)
    kapt(libs.hilt.android.compiler)

}

kapt {
    correctErrorTypes = true
}
