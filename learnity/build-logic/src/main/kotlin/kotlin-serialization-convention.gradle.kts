import com.dak0ta.learnity.buildlogic.libs

plugins {
    id("org.jetbrains.kotlin.plugin.serialization")
}

dependencies {
    add("implementation", libs.kotlinx.serialization.json)
}
