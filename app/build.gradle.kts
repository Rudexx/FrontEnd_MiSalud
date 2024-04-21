plugins {
    id("com.android.application")
}

android {
    namespace = "com.example.helloworld"
    compileSdk = 33

    packagingOptions {
        exclude("META-INF/LICENSE.md")
        exclude("META-INF/NOTICE.md")
        exclude("META-INF/ErraiApp.properties")
        exclude("META-INF/INDEX.LIST")
        // Puedes agregar más exclusiones aquí si hay otros archivos duplicados
    }

    defaultConfig {
        applicationId = "com.example.helloworld"
        minSdk = 24
        targetSdk = 33
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
    buildFeatures {
        viewBinding = true
    }
}

dependencies {
    implementation ("com.google.code.gson:gson:2.8.8")
    implementation ("org.kie:kie-api:7.59.0.Final")
    implementation ("org.drools:drools-core:8.44.0.Final")
    implementation("org.drools:drools-compiler:8.44.0.Final")
    implementation("org.drools:drools-mvel:8.44.0.Final")
    implementation ("com.github.prolificinteractive:material-calendarview:2.0.1")
    implementation("androidx.recyclerview:recyclerview:1.2.1")
    implementation("com.google.android.material:material:1.8.0") // Conserva la versión más reciente
    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")
    implementation("androidx.navigation:navigation-fragment:2.5.3")
    implementation("androidx.navigation:navigation-ui:2.5.3")
    implementation("com.squareup.retrofit2:retrofit:2.9.0")
    implementation("com.squareup.retrofit2:converter-gson:2.9.0")
    implementation ("com.squareup.okhttp3:logging-interceptor:4.9.1")
    implementation ("com.squareup.retrofit2:converter-scalars:2.11.0")
    implementation ("com.jakewharton.threetenabp:threetenabp:1.3.0")// Asegúrate de que esté la versión correcta
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")

}
