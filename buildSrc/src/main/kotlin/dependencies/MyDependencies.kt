package dependencies

/** Created by github.com/im-o on 12/13/2022. */

object MyDependencies {

    // DEFAULT DEPENDENCIES
    const val core_ktx = "androidx.core:core-ktx:${Versions.core_ktx_version}"
    const val lifecycle_ktx = "androidx.lifecycle:lifecycle-runtime-ktx:${Versions.lifecycle_ktx_version}"
    const val activity_compose = "androidx.activity:activity-compose:${Versions.activity_compose_version}"
    const val compose_bom = "androidx.compose:compose-bom:${Versions.compose_bom_version}"
    const val ui_compose = "androidx.compose.ui:ui"
    const val ui_graphics = "androidx.compose.ui:ui-graphics"
    const val ui_tooling_preview = "androidx.compose.ui:ui-tooling-preview"
    const val material3_compose = "androidx.compose.material3:material3"
    const val material_icons_extended = "androidx.compose.material:material-icons-extended"
    const val material_compose = "androidx.compose.material:material:${Versions.material_compose_version}"
    const val constraintlayout_compose = "androidx.constraintlayout:constraintlayout-compose:${Versions.constraintlayout_compose_version}"

    const val junit = "junit:junit:${Versions.junit_version}"
    const val test_ext_junit = "androidx.test.ext:junit:${Versions.test_ext_junit_version}"
    const val navigation_compose = "androidx.navigation:navigation-compose:${Versions.navigation_compose}"

    // REMOTE
    const val retrofit = "com.squareup.retrofit2:retrofit:${Versions.retrofit_version}"
    const val retrofit2_converter_gson = "com.squareup.retrofit2:converter-gson:${Versions.retrofit_version}"
    const val retrofit2_adapter_rxjava2 = "com.squareup.retrofit2:adapter-rxjava2:${Versions.retrofit_version}"
    const val okhttp3 = "com.squareup.okhttp3:logging-interceptor:${Versions.okHttp3_version}"

    // HILT
    const val hilt_android = "com.google.dagger:hilt-android:${Versions.hilt_android}"
    const val hilt_android_compiler = "com.google.dagger:hilt-android-compiler:${Versions.hilt_android}"
    const val hilt_compose = "androidx.hilt:hilt-navigation-compose:${Versions.hilt_compose}"
    const val hilt_compose_compiler = "androidx.hilt:hilt-compiler:${Versions.hilt_compose}"

    // MAPS
    const val maps_compose = "com.google.maps.android:maps-compose:${Versions.maps_compose_version}"
    const val maps_compose_utils = "com.google.maps.android:maps-compose-utils:${Versions.maps_compose_version}"
    const val maps_compose_widgets = "com.google.maps.android:maps-compose-widgets:${Versions.maps_compose_version}"
    const val play_service_maps = "com.google.android.gms:play-services-maps:${Versions.play_service_maps_version}"
}