plugins {
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
