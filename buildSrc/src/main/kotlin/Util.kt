import org.gradle.plugin.use.PluginDependenciesSpec
import org.gradle.plugin.use.PluginDependencySpec

fun PluginDependenciesSpec.android(module: String): PluginDependencySpec =
    id("com.android.$module")