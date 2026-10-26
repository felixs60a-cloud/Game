plugins {
    id("com.android.application")
}

// Tandatangan keluaran dibaca dari pemboleh ubah persekitaran (GitHub Secrets).
val keystorePath: String? = System.getenv("ANDROID_KEYSTORE_PATH")

android {
    namespace = "com.OmbakSoftware.Catatanperbelanjaanharian"
    compileSdk = 36

    defaultConfig {
        applicationId = "com.OmbakSoftware.Catatanperbelanjaanharian"
        minSdk = 26
        targetSdk = 36
        versionCode = (System.getenv("VERSION_CODE") ?: "1").toInt()
        versionName = "1.0.0"
    }

    signingConfigs {
        if (keystorePath != null) {
            create("release") {
                storeFile = file(keystorePath)
                storePassword = System.getenv("ANDROID_KEYSTORE_PASSWORD")
                keyAlias = System.getenv("ANDROID_KEY_ALIAS")
                keyPassword = System.getenv("ANDROID_KEY_PASSWORD")
            }
        }
    }

    buildTypes {
        release {
            isMinifyEnabled = true
            isShrinkResources = true
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"))
            if (keystorePath != null) signingConfig = signingConfigs.getByName("release")
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }

    sourceSets["main"].assets.srcDir(layout.buildDirectory.dir("generated/webAssets"))
}

// Salin index.html dari akar repo ke dalam app, dan guna fon tempatan supaya app jalan tanpa internet.
val copyWebApp by tasks.registering(Copy::class) {
    from(rootProject.file("../index.html"))
    into(layout.buildDirectory.dir("generated/webAssets"))
    filter { line: String ->
        when {
            line.contains("fonts.googleapis.com/css2") -> "<link rel=\"stylesheet\" href=\"fonts.css\">"
            line.contains("rel=\"preconnect\"") -> ""
            else -> line
        }
    }
}
tasks.named("preBuild") { dependsOn(copyWebApp) }
