package ir.sahrapanel.app.features.main

import androidx.compose.material3.adaptive.navigationsuite.NavigationSuiteScaffold
import androidx.compose.runtime.Composable
import androidx.navigation3.runtime.EntryProviderScope
import androidx.navigation3.runtime.NavKey
import kotlinx.serialization.Serializable

@Serializable
data object MainRoute: NavKey
fun EntryProviderScope<NavKey>.mainEntry(){
    entry<MainRoute> {
        MainScreen()
    }
}

@Composable
fun MainScreen(){
    NavigationSuiteScaffold(
        navigationSuiteItems = {

        }
    )
}