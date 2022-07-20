import org.jetbrains.kotlin.kapt3.base.Kapt.kapt
plugins {
    //kotlin("jvm")
    id("com.android.application")
    id ("org.jetbrains.kotlin.android")
    id("com.google.devtools.ksp")
}

android {
    compileSdk = 32

    defaultConfig {
        applicationId = "com.logocito.atlas"
        minSdk = 21
        targetSdk = 32
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            //minifyEnabled = false
            proguardFiles (getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
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
    implementation("com.google.devtools.ksp:symbol-processing-api:1.7.10-1.0.6")
    implementation ("androidx.core:core-ktx:1.7.0")
    implementation ("androidx.appcompat:appcompat:1.4.2")
    implementation ("com.google.android.material:material:1.6.1")
    implementation ("androidx.constraintlayout:constraintlayout:2.1.4")
    implementation ("androidx.navigation:navigation-fragment-ktx:2.4.2")
    implementation ("androidx.navigation:navigation-ui-ktx:2.4.2")
    val lifecycleVersion = "2.5.0"
    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:$lifecycleVersion")
    implementation("androidx.lifecycle:lifecycle-livedata-ktx:$lifecycleVersion")
    implementation ("androidx.fragment:fragment-ktx:1.4.1")
    implementation ("androidx.activity:activity-ktx:1.4.0")
    //testimplementation ("junit:junit:4.13.2")
    //androidTestimplementation ("androidx.test.ext:junit:1.1.3")
    //androidTestimplementation ("androidx.test.espresso:espresso-core:3.4.0")


    val roomVersion = "2.4.2"

    implementation("androidx.room:room-runtime:$roomVersion")
    //annotationProcessor ("androidx.room:room-compiler:$roomVersion")
    // To use Kotlin Symbolic Processing (KSP)
    ksp("androidx.room:room-compiler:$roomVersion")
    // optional - RxJava2 support for Room
    implementation("androidx.room:room-ktx:$roomVersion")
   // implementation("androidx.room:room-rxjava2:$roomVersion")

    // optional - RxJava3 support for Room
    //implementation("androidx.room:room-rxjava3:$roomVersion")

    // optional - Guava support for Room, including Optional and ListenableFuture
    //implementation("androidx.room:room-guava:$roomVersion")

    // optional - Test helpers
   //testimplementation("androidx.room:room-testing:$roomVersion")

    // optional - Paging 3 Integration
    //implementation("androidx.room:room-paging:2.5.0-alpha02")
}