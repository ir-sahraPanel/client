import org.jetbrains.kotlin.gradle.ExperimentalWasmDsl
import org.jetbrains.kotlin.gradle.dsl.JvmTarget


plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.androidMultiplatformLibrary)
    alias(libs.plugins.composeMultiplatform)
    alias(libs.plugins.composeCompiler)
    alias(libs.plugins.ksp)
    alias(libs.plugins.room3)
    alias(libs.plugins.koin.compiler)
}

kotlin {

    jvm()

    @OptIn(ExperimentalWasmDsl::class)
    wasmJs {
        browser()
    }

    androidLibrary {
        namespace = "ir.sahrapanel.app.shared"
        compileSdk = libs.versions.android.compileSdk.get().toInt()
        minSdk = libs.versions.android.minSdk.get().toInt()

        compilerOptions {
            jvmTarget = JvmTarget.JVM_11
        }
        androidResources {
            enable = true
        }
        withHostTest {
            isIncludeAndroidResources = true
        }
    }

    sourceSets {
        androidMain.dependencies {
            implementation(libs.compose.uiToolingPreview)
            implementation(libs.androidx.sqlite.bundled)
        }
        jvmMain.dependencies {
            implementation(libs.androidx.sqlite.bundled)
        }
        commonMain.dependencies {
            implementation(libs.compose.runtime)
            implementation(libs.compose.foundation)
            implementation(libs.compose.material3)
            implementation(libs.compose.ui)
            implementation(libs.compose.components.resources)
            implementation(libs.compose.uiToolingPreview)
            implementation(libs.androidx.lifecycle.viewmodelCompose)
            implementation(libs.androidx.lifecycle.runtimeCompose)
//room
            implementation(libs.androidx.room.runtime)
//koin
            implementation(project.dependencies.platform(libs.koin.bom))
            implementation(libs.koin.compose)
            implementation(libs.koin.compose.viewmodel)
            implementation(libs.koin.compose.viewmodel.navigation)

        }
        commonTest.dependencies {
            implementation(libs.kotlin.test)
        }
        wasmJsMain.dependencies {
            implementation(libs.androidx.sqlite.web)
            implementation(npm("@sqlite.org/sqlite-wasm", "3.50.1-build1"))
            implementation(npm("sqlite-wasm-worker", project.file("../sqliteWasmWorker")))
        }
    }
}

compose.resources {

    publicResClass = false
    packageOfResClass = "ir.sahrapanel.app.shared"
    generateResClass = auto

}



dependencies {
    androidRuntimeClasspath(libs.compose.uiTooling)
    add("kspAndroid", libs.androidx.room.compiler)
    add("kspJvm", libs.androidx.room.compiler)
    add("kspWasmJs", libs.androidx.room.compiler)
}
room3 {
    schemaDirectory("$projectDir/schemas")
}
