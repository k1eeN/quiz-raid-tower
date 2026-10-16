package dev.k1een.qrt.core.navigation

import androidx.navigation3.runtime.EntryProviderScope
import androidx.navigation3.runtime.NavKey

typealias EntryInstaller = EntryProviderScope<NavKey>.(Navigator) -> Unit
