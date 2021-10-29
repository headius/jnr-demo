plugins {
    java
    application
}

repositories {
    mavenCentral()
}

dependencies {
    implementation("com.github.jnr:jnr-ffi:2.2.7")
}

application {
    mainClass.set("in.praj.demo.BasicApp")
}

val nativeDir = buildDir.resolve("native")
val cc = tasks.register<Exec>("cc") {
    val libOutput = nativeDir.resolve("libminimal.so")
    workingDir(project.projectDir)
    commandLine("gcc", "-shared", "-o", libOutput, "-fPIC", "src/main/c/minimal.c")
    doFirst {
        mkdir(nativeDir)
    }
}

tasks.withType<JavaExec>() {
    dependsOn(cc)
    environment("LD_LIBRARY_PATH", nativeDir)
}
