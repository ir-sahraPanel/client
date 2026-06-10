import org.jetbrains.kotlin.gradle.ExperimentalWasmDsl
import org.jetbrains.kotlin.gradle.dsl.JvmTarget
import com.codingfeline.buildkonfig.compiler.FieldSpec.Type.STRING

plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.androidMultiplatformLibrary)
    alias(libs.plugins.composeMultiplatform)
    alias(libs.plugins.composeCompiler)
    alias(libs.plugins.ksp)
    alias(libs.plugins.room3)
   alias(libs.plugins.koin.compiler)
    alias(libs.plugins.jetbrains.kotlin.serialization)
    alias(libs.plugins.codingfeline.buildkonfig)
}


kotlin {

    jvm()

    @OptIn(ExperimentalWasmDsl::class)
    wasmJs {
        browser()
    }

    android {
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
            implementation(libs.ktor.client.okhttp)
            implementation(libs.androidx.datastore.preferences)
            //implementation(libs.koin.android)
        }
        jvmMain.dependencies {
            implementation(libs.androidx.sqlite.bundled)
            implementation(libs.ktor.client.okhttp)
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
         //   implementation(libs.jetbrains.material3.adaptive)
            //navigation
            implementation(libs.jetbrains.navigation3.ui)
            implementation(libs.jetbrains.lifecycle.viewmodelNavigation3)
            implementation(libs.jetbrains.material3.adaptiveNavigation3)

            //room
            implementation(libs.androidx.room.runtime)
          //koin
            implementation(project.dependencies.platform(libs.koin.bom))
            implementation(libs.koin.compose)
            implementation(libs.koin.compose.viewmodel)
            implementation(libs.koin.compose.viewmodel.navigation)


            implementation(libs.kotlin.datetime)
            implementation(libs.persiandatetime)
            //serialization
            implementation(libs.kotlinx.serialization.core)
            //ktor
            implementation(libs.ktor.client.core)
            implementation(libs.ktor.serialization.kotlinx.json)
            implementation(libs.ktor.client.content.negotiation)
            implementation(libs.ktor.client.logging)
            implementation(libs.ktor.client.auth)

            //kermit
            implementation(libs.kermit)

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


koinCompiler {
    userLogs = true
    debugLogs = true
    unsafeDslChecks = true
        compileSafety = true       // Enabled by default
        skipDefaultValues = true   // Enabled by default

}

buildkonfig{
    packageName =  "ir.sahrapanel.app.shared"

    defaultConfigs {
        buildConfigField(
            type = STRING,
            name = "BASE_URL",
            value = "http://127.0.0.1:8080/api/"
        )
        buildConfigField(
            type = STRING,
            name = "APP_VERSION",
            value = libs.versions.appVersion.get()
        )
    }
    targetConfigs{
        create("android"){
            buildConfigField(
                type = STRING,
                name = "BASE_URL",
                value = "http://10.0.2.2:8080/api/"
            )
        }
    }

}