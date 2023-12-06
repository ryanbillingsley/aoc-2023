plugins {
    kotlin("jvm") version "1.9.20"
}

dependencies {
    val kotestVersion = "5.5.5"

    testImplementation(platform("org.junit:junit-bom:5.10.0"))
    testImplementation("org.junit.jupiter:junit-jupiter")
    testImplementation("org.junit.jupiter:junit-jupiter-params")
    testImplementation("io.kotest:kotest-assertions-core:$kotestVersion")
    testImplementation("io.kotest:kotest-assertions-json:$kotestVersion")
}

sourceSets {
    main {
        kotlin.srcDir("src/main")
    }
    test {
        kotlin.srcDir("src/test")
    }
}

tasks {
    wrapper {
        gradleVersion = "8.5"
    }

    test {
        useJUnitPlatform()
    }
}
