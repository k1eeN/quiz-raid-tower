package dev.k1een.qrt.core.navigation

import androidx.compose.runtime.mutableStateListOf
import androidx.navigation3.runtime.NavKey

@Suppress("MISSING_DEPENDENCY_SUPERCLASS")
class Navigator(startDestination: NavKey) {
    private val stack = mutableStateListOf(startDestination)
    val backStack: List<NavKey> get() = stack.toList()

    fun navigate(destination: NavKey) {
        when {
            destination == stack.first() -> popToRoot()
            destination != stack.last() -> stack.add(destination)
        }
    }

    fun back() {
        if (stack.size > 1) stack.removeAt(stack.lastIndex)
    }

    fun replace(destination: NavKey) {
        if (stack.size <= 1 || destination == stack.last()) return
        when (destination) {
            stack.first() -> popToRoot()
            stack[stack.lastIndex - 1] -> back()
            else -> stack[stack.lastIndex] = destination
        }
    }

    fun popToRoot() {
        while (stack.size > 1) stack.removeAt(stack.lastIndex)
    }
}
