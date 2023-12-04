plugins {
    java
}


allprojects {
    group = "com.yourcompany"
    version = "1.0-SNAPSHOT"
}

repositories {
    mavenCentral()
}

allprojects {
    group = "com.vaani"
    version = "1.0-SNAPSHOT"
}

//group = "com.vaani"
//version = "1.0-SNAPSHOT"
//java.sourceCompatibility = JavaVersion.VERSION_15
subprojects {
    apply(plugin = "java")

    repositories {
        mavenCentral()
    }

    java {
        toolchain {
            languageVersion.set(JavaLanguageVersion.of(17))
        }
    }
}
