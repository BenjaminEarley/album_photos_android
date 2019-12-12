import org.jetbrains.kotlin.gradle.dsl.KotlinJvmOptions

plugins {
    android("application")
    kotlin("android")
    kotlin("android.extensions")
}


android {
    compileSdkVersion(29)
    buildToolsVersion("29.0.2")

    defaultConfig {
        applicationId = "com.benjaminearley.albumphotos"
        minSdkVersion(19)
        targetSdkVersion(29)
        versionCode = 1
        versionName = "1.0"
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        // We have to add the explicit cast before accessing the options itself.
        // If we don't, it does not work: "unresolved reference: jvmTarget"
        @Suppress("USELESS_CAST") val options = this as KotlinJvmOptions
        options.jvmTarget = "1.8"
    }
    buildTypes {
        getByName("release") {
            isMinifyEnabled = true
            isShrinkResources = true
            proguardFiles(getDefaultProguardFile("proguard-android.txt"), "proguard-rules.pro")

        }
    }
}

dependencies {
    implementation(fileTree(mapOf("dir" to "libs", "include" to listOf("*.jar"))))
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8:$kotlinVersion")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:$coroutines")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:$coroutines")
    implementation("androidx.appcompat:appcompat:1.1.0")
    implementation("androidx.core:core-ktx:1.1.0")
    implementation("androidx.fragment:fragment-ktx:1.2.0-rc03")
    implementation("androidx.coordinatorlayout:coordinatorlayout:1.1.0")
    implementation("androidx.constraintlayout:constraintlayout:2.0.0-beta3")
    implementation("androidx.recyclerview:recyclerview:1.1.0")
    implementation("androidx.lifecycle:lifecycle-extensions:$lifecycle")
    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:$lifecycle")
    implementation("androidx.lifecycle:lifecycle-livedata-ktx:$lifecycle")
    implementation("androidx.lifecycle:lifecycle-common-java8:$lifecycle")
    implementation("com.google.android.material:material:1.0.0")
    // KitKat requirements prevent updating to OkHttp4 :(
    @Suppress("GradleDependency") implementation("com.squareup.okhttp3:okhttp:3.14.4")
    implementation("com.squareup.retrofit2:retrofit:$retrofit")
    implementation("com.squareup.retrofit2:converter-moshi:$retrofit")
    implementation("com.squareup.moshi:moshi:$moshi")
    implementation("com.squareup.moshi:moshi-kotlin:$moshi")
    implementation("com.squareup.picasso:picasso:2.71828")
    testImplementation("junit:junit:4.12")
    androidTestImplementation("androidx.test.ext:junit:1.1.1")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.2.0")
}
