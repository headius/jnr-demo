plugins {
    java
    application
}

repositories {
    mavenCentral()
}

dependencies {
    implementation("com.github.jnr:jnr-ffi:2.2.8")
}

application {
    mainClass.set("in.praj.demo.Gtk3App")
}
