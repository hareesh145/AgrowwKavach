plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    id("kotlin-android")
    id("kotlin-kapt")
    id("com.google.dagger.hilt.android")
    id("kotlin-parcelize")
}

android {
    namespace = "com.ak"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.ak"
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
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = "17"
    }
    buildFeatures {
        buildConfig = true
        dataBinding = true
    }
    flavorDimensions("main")
    productFlavors {
        create("dev") {
            dimension = "main"
            buildConfigField("String", "BASE_URL", "\"http://www.agrowwkavach.com:8080/akapis/\"")
        }
        create("prod") {
            dimension = "main"
            buildConfigField("String", "BASE_URL", "\"http://www.agrowwkavach.com:8080/akapis/\"")
        }
    }
}

dependencies {
//    implementation("androidx.core:core-ktx:1.13.1")
//    implementation("androidx.appcompat:appcompat:1.5.1")
//    implementation("com.google.android.material:material:1.12.0")
//    implementation("androidx.navigation:navigation-fragment-ktx:2.7.7")
//    implementation("androidx.navigation:navigation-ui-ktx:2.7.7")
//    testImplementation("junit:junit:4.13.2")
//    androidTestImplementation("androidx.test.ext:junit:1.2.1")
//    androidTestImplementation("androidx.test.espresso:espresso-core:3.6.1")
//    implementation("com.intuit.sdp:sdp-android:1.1.0")
//    implementation("com.intuit.ssp:ssp-android:1.1.0")


    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.activity)
    implementation(libs.androidx.constraintlayout)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)

    implementation(libs.glide)
    implementation(libs.dagger)
    implementation(libs.dagger.android)
    implementation(libs.dagger.android.support) // if you use the support libraries
//    annotationProcessor(libs.dagger.compiler)
    annotationProcessor(libs.dagger.android.processor)

    implementation(libs.hilt.android)
    kapt(libs.hilt.android.compiler)

    implementation(libs.retrofit)
    implementation(libs.converter.gson)
    implementation(libs.logging.interceptor)

    implementation(libs.androidx.lifecycle.livedata.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.lifecycle.viewmodel.ktx)

    implementation(libs.sdp.android)
    implementation(libs.ssp.android)
    implementation(libs.androidx.navigation.fragment.ktx)
    implementation(libs.androidx.navigation.ui.ktx)
    implementation(libs.androidx.core.splashscreen)

    implementation(libs.android.gif.drawable)
}