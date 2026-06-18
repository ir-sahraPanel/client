package ir.sahrapanel.app.features.dashboard

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.PermanentNavigationDrawer
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp
import androidx.navigation3.runtime.EntryProviderScope
import androidx.navigation3.runtime.NavKey
import ir.sahrapanel.app.core.ui.components.VSpacer
import kotlinx.serialization.Serializable

@Serializable
data object DashboardRoute: NavKey

fun EntryProviderScope<NavKey>.dashboardEntry(){
    entry<DashboardRoute> {
        DashboardScreen()
    }
}

@Composable
fun DashboardScreen(){
    Column {
        VSpacer(80.dp)
        Text("test")
    }

}