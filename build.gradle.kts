plugins {
    // Apply the java plugin to add support for Java
    java

    // Apply the application plugin to add support for building a CLI application
    // You can run your app via task "run": ./gradlew run
    application

    /*
     * Adds tasks to export a runnable jar.
     * In order to create it, launch the "shadowJar" task.
     * The runnable jar will be found in build/libs/projectname-all.jar
     */
    id("com.github.johnrengelman.shadow") version "7.0.0"

    id("org.panteleyev.jpackageplugin") version "1.5.2"
}

repositories {
    mavenCentral()
}

val javaFXModules = listOf(
    "base",
    "controls",
    "fxml",
    "swing",
    "graphics"
)

val supportedPlatforms = listOf("linux", "mac", "win") // All required for OOP
val jUnitVersion = "5.7.1"
val javaFxVersion = 15

val jgraphtVersion = "1.5.1"

dependencies {
    // Example library: Guava. Add what you need (and remove Guava if you don't use it)
    // implementation("com.google.guava:guava:28.1-jre")
    
    // JGraphT: comment out if you do not need them
    implementation("org.jgrapht:jgrapht-core:$jgraphtVersion")
    
    implementation("net.harawata:appdirs:1.2.1")

    for (platform in supportedPlatforms) {
        for (module in javaFXModules) {
            implementation("org.openjfx:javafx-$module:$javaFxVersion:$platform")
        }
    }

    // JUnit API and testing engine
    testImplementation("org.junit.jupiter:junit-jupiter-api:$jUnitVersion")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:$jUnitVersion")
}

tasks.jpackage {
    appName = "Hidenseek"
    appVersion = "1.0"
    input = "build/libs/"
    destination = "build/release"
    mainJar = "OOP-hidenseek-all.jar"
    
    jLinkOptions = listOf(
        "--bind-services"
    )

    linux {
        icon = "icons/icon.png"
    }

    windows {
        winDirChooser = true
        winShortcutPrompt = true
        icon = "icons/icon.ico"
    }
}


tasks.withType<JavaCompile> {
    options.encoding = "UTF-8"
}

tasks.withType<Test> {
    // Enables JUnit 5 Jupiter module
    useJUnitPlatform()
}

application {
    // Define the main class for the application
    mainClass.set("hidenseek.App")
}
