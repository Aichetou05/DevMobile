plugins {
    alias(libs.plugins.android.application)
}

android {
    namespace = "com.example.androidminiprojet"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.example.androidminiprojet"
        minSdk = 24
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
}

dependencies {

    implementation(libs.appcompat)
    implementation(libs.material)
    implementation(libs.activity)
    implementation(libs.constraintlayout)
    testImplementation(libs.junit)
    androidTestImplementation(libs.ext.junit)
    androidTestImplementation(libs.espresso.core)

    // ORMLite
    implementation (libs.ormlite.android)
    implementation (libs.ormlite.core)
    // Retrofit
    implementation(libs.retrofit)
    implementation(libs.converter.gson)

    // (Optionnel) Logs HTTP
    implementation (libs.logging.interceptor)
    testImplementation(libs.junit)
    androidTestImplementation(libs.ext.junit)
    androidTestImplementation(libs.espresso.core)



}