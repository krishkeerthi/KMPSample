import org.gradle.kotlin.dsl.publishing
import org.jetbrains.kotlin.gradle.dsl.JvmTarget
import org.jetbrains.kotlin.gradle.plugin.mpp.apple.XCFramework

plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.androidLibrary)

    alias(libs.plugins.sqldelight)
    alias(libs.plugins.kotlinSerialization)

    alias(libs.plugins.skie)

    `maven-publish`
}

group = "com.example.sampledb.backend"
version = "1.0.0"

kotlin {
    androidTarget {
        compilerOptions {
            jvmTarget.set(JvmTarget.JVM_11)
        }
    }

    val xcfName = "Shared"
    val xcf = XCFramework(xcfName)

    listOf(
        iosX64(),
        iosArm64(),
        iosSimulatorArm64()
    ).forEach { iosTarget ->
        iosTarget.binaries.framework {
            baseName = "Shared"
            isStatic = false

            xcf.add(this)
        }
    }
    
    sourceSets {
        commonMain.dependencies {
            // Ktor
            implementation(libs.ktor.client.core)
            implementation(libs.ktor.client.content.negotiation)
            implementation(libs.ktor.serialization.kotlinx.json)

            // SQLDelight
            implementation(libs.sqldelight.runtime)
            implementation(libs.sqldelight.coroutines)

            // Koin
            implementation(libs.koin.core)

            // Coroutines
            implementation(libs.kotlinx.coroutines.core)
        }
        commonTest.dependencies {
//            implementation(libs.kotlin.test)
            implementation(kotlin("test"))
            implementation(libs.kotlinx.coroutines.test)
//            implementation(libs.sqldelight.sqlite.driver)
        }

        androidMain.dependencies {
            implementation(libs.ktor.client.okhttp)
            implementation(libs.sqldelight.android.driver)
        }

        iosMain.dependencies {
            implementation(libs.ktor.client.darwin)
            implementation(libs.sqldelight.native.driver)
        }
    }
}

android {
    namespace = "com.example.sampledb.shared"
    compileSdk = libs.versions.android.compileSdk.get().toInt()
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    defaultConfig {
        minSdk = libs.versions.android.minSdk.get().toInt()
    }
}

publishing {
    publications {
        named<MavenPublication>("kotlinMultiplatform") {
//            from(components["kotlin"])
            groupId = project.group.toString()
            artifactId = "shared"
            version = project.version.toString()
        }
    }
}

sqldelight {
    databases {
        create("AppDatabase") {
            packageName.set("com.example.sampledb.db")
        }
    }
}
