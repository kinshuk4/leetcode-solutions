/*
 * This file was generated by the Gradle 'init' task.
 */

plugins {
    id("com.vaani.java-conventions")
}

dependencies {
    api(project(":algorithms-java"))
    api(libs.commons.io.commons.io)
    api(libs.cglib.cglib)
    api(libs.org.apache.commons.commons.lang3)
    api(libs.com.github.stuxuhai.jpinyin)
    api(libs.org.apache.logging.log4j.log4j.core)
    api(libs.org.projectlombok.lombok)
    api(libs.com.google.guava.guava)
    api(libs.org.mapdb.mapdb)
    testImplementation(libs.org.junit.jupiter.junit.jupiter)
}

description = "leetcode"
