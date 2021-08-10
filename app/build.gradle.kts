plugins {
    id("com.android.application")
    kotlin("android")
    kotlin("kapt")
}
android {
    compileSdkVersion(30)
    defaultConfig {
        applicationId = "com.mahmoud.nagwa"
        minSdkVersion(21)
        targetSdkVersion(30)
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"


        testOptions {
            unitTests.isReturnDefaultValues = true
        }
    }

    buildTypes {


        getByName("debug") {
            isDebuggable = true
        }

        getByName("release") {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }

    kotlinOptions {
        // We have to add the explicit cast before accessing the options itself.
        val options = this as org.jetbrains.kotlin.gradle.dsl.KotlinJvmOptions
        options.jvmTarget = "1.8"
    }

    compileOptions {
        targetCompatibility = JavaVersion.VERSION_1_8
        sourceCompatibility = JavaVersion.VERSION_1_8
    }

    buildFeatures {
        dataBinding = true
    }
}

dependencies {
    implementation(fileTree(mapOf("dir" to "libs", "include" to listOf("*.jar"))))
    implementation(Kotlin.stdlib.jdk7)

    implementation(AndroidX.appCompat)
    implementation(AndroidX.constraintLayout)
    implementation(Google.android.material)

    implementation(Libs.downloader)
    //-------------------------------------------- lifecycle -------------------------------
    implementation(AndroidX.Core.ktx)
    implementation(AndroidX.fragmentKtx)
    implementation(AndroidX.activityKtx)
    implementation(AndroidX.Lifecycle.viewModelKtx)
    implementation(AndroidX.Lifecycle.runtimeKtx)
    implementation(AndroidX.Lifecycle.liveDataKtx)

    // --------------------- Dagger2 -------------------------
    implementation(Libs.dagger2)
    implementation(Libs.daggerAndroid)
    implementation(Libs.daggerSupport)
    kapt(Libs.daggerCompiler)

    //--------------------- RX -------------------------
    implementation(Libs.reactivexAndroid)
    implementation(Libs.reactivexJava)
    implementation(Libs.reactivexBinding)
    implementation(Libs.reactivexAdapter)

    //-------------------------------------------- Logging -------------------------------
    implementation(JakeWharton.timber)


    // -------------------------------------- Networking -------------------------------
    implementation(Square.okHttp3.okHttp)
    implementation(Square.okHttp3.loggingInterceptor)

    implementation(Square.retrofit2.retrofit)
    implementation(Square.retrofit2.converter.gson)

    // -------------------------------------- Navigation -------------------------------
    implementation(AndroidX.Navigation.fragmentKtx)
    implementation(AndroidX.Navigation.uiKtx)




    testImplementation(Libs.Test.junit)
    testImplementation(Libs.Test.xCore)
    androidTestImplementation(Libs.Test.xCore)

    androidTestImplementation(Libs.Test.arch)
    androidTestImplementation(Libs.Test.rule)
    androidTestImplementation(Libs.Test.runner)


    testImplementation(Libs.Test.xArch)
    androidTestImplementation(Libs.Test.xArch)
    androidTestImplementation(Libs.Test.extJunit)

    testImplementation(Libs.Test.mockito)
    androidTestImplementation(Libs.Test.mockitoAndroid)

    testImplementation(Libs.Test.mockk)


    androidTestImplementation(Libs.Test.kaspresso)
    androidTestImplementation(Libs.Test.kotlintest)

}