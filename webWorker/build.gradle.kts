plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.kotlinSerialization)
}

val copyLaunchModels by tasks.registering(Sync::class) {
    from("../shared/src/commonMain/kotlin") {
        include("**/data/LaunchDtos.kt", "**/domain/Agency.kt", "**/domain/Launch.kt", "**/domain/Pad.kt")
    }
    into(layout.buildDirectory.dir("generated/launchModels"))
}

kotlin {
    js {
        browser()
        useEsModules()
        binaries.executable()
    }

    sourceSets {
        jsMain {
            kotlin.srcDir(copyLaunchModels)
            dependencies {
                implementation(libs.kotlinx.serializationJson)
            }
        }
    }
}

val workerBundle by configurations.creating {
    isCanBeConsumed = true
    isCanBeResolved = false
}

val workerModules = layout.buildDirectory.dir("compileSync/js/main/productionExecutable/kotlin")

val packageWorker by tasks.registering(Sync::class) {
    dependsOn("jsProductionExecutableCompileSync")
    from(workerModules) {
        include("*.mjs")
        rename("SpaceLaunches-webWorker.mjs", "main.mjs")
    }
    into(layout.buildDirectory.dir("workerModules"))
}

artifacts.add(workerBundle.name, packageWorker.map { it.destinationDir }) {
    builtBy(packageWorker)
}
