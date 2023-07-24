import dependencies.MyDependencies
import java.util.Properties

plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
    id("dagger.hilt.android.plugin")
    id("kotlin-kapt")
}

@Suppress("UnstableApiUsage")
android {
    namespace = "id.barakkadev.coremap"
    compileSdk = Versions.compile_sdk

    defaultConfig {
        minSdk = Versions.min_sdk
        targetSdk = Versions.target_sdk

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
    }

    buildTypes {
        val localProperties = Properties()
        localProperties.load(project.rootProject.file("local.properties").inputStream())
        debug {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
            buildConfigField("String", "MAPS_BASE_URL", "\"https://maps.googleapis.com/maps/api/\"")
            buildConfigField("String", "MAPS_API_KEY", "\"" + localProperties.getProperty("MAPS_API_KEY") + "\"")
        }
        release {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
            buildConfigField("String", "MAPS_BASE_URL", "\"https://maps.googleapis.com/maps/api/\"")
            buildConfigField("String", "MAPS_API_KEY", "\"" + localProperties.getProperty("MAPS_API_KEY") + "\"")
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = JavaVersion.VERSION_11.toString()
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = Versions.compose_compiler
    }
    packagingOptions {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
    tasks.withType().configureEach {
        kotlinOptions {
            freeCompilerArgs = freeCompilerArgs + listOf(
                "-opt-in=kotlin.RequiresOptIn",
                "-opt-in=androidx.compose.material3.ExperimentalMaterial3Api",
            )
        }
    }
}

dependencies {

    // DEFAULT DEPENDENCIES
    api(MyDependencies.core_ktx)
    api(MyDependencies.lifecycle_ktx)

    // TESTING
    testImplementation(MyDependencies.junit)
    androidTestImplementation(MyDependencies.test_ext_junit)

    // COMPOSE
    api(MyDependencies.activity_compose)
    api(platform(MyDependencies.compose_bom))
    api(MyDependencies.ui_compose)
    api(MyDependencies.ui_graphics)
    api(MyDependencies.ui_tooling_preview)
    api(MyDependencies.material3_compose)
    api(MyDependencies.material_compose)
    api(MyDependencies.navigation_compose)
    api(MyDependencies.material_icons_extended)
    api(MyDependencies.constraintlayout_compose)

    // REMOTE
    api(MyDependencies.retrofit)
    api(MyDependencies.retrofit2_converter_gson)
    api(MyDependencies.retrofit2_adapter_rxjava2)
    api(MyDependencies.okhttp3)

    // Hilt
    implementation(MyDependencies.hilt_android)
    kapt(MyDependencies.hilt_android_compiler)
    api(MyDependencies.hilt_compose) {
        exclude("androidx.lifecycle")
    }
    kapt(MyDependencies.hilt_compose_compiler)

    // MAPS
    api(MyDependencies.maps_compose)
    api(MyDependencies.play_service_maps) // Make sure to also include the latest version of the Maps SDK for Android
    api(MyDependencies.maps_compose_utils) // Optionally, you can include the Compose utils library for Clustering, etc.
    api(MyDependencies.maps_compose_widgets) // Optionally, you can include the widgets library for ScaleBar, etc.
}