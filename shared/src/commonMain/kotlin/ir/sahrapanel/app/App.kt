package ir.sahrapanel.app

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.safeContentPadding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import ir.sahrapanel.app.shared.Res
import ir.sahrapanel.app.shared.compose_multiplatform
import kotlinx.coroutines.launch
import org.jetbrains.compose.resources.painterResource
import org.koin.compose.koinInject
import kotlin.random.Random

@Composable
@Preview
fun App() {
    // Initialize database only once using remember
    val database : AppDatabase = koinInject()

    val scope = rememberCoroutineScope()
    MaterialTheme {
        var showContent by remember { mutableStateOf(false) }
        var users by remember { mutableStateOf<List<User>>(emptyList()) }

        Column(
            modifier = Modifier
                .background(MaterialTheme.colorScheme.primaryContainer)
                .safeContentPadding()
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Button(onClick = {
                showContent = !showContent
                scope.launch {
                    if (showContent && database.userDao().getUserCount() < 3) {
                        database.userDao().insert(
                            User(
                                fullName = "farid",
                                username = "farid + ${Random.nextInt()}",
                                email = "farid + ${Random.nextInt()}"
                            )
                        )
                    }
                    users = database.userDao().getActiveUsers()
                }
            }) {
                Text("Click me!")
            }

            AnimatedVisibility(showContent) {
                val greeting = remember { Greeting().greet() }
                Column(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                ) {
                    Image(painterResource(Res.drawable.compose_multiplatform), null)
                    Text("Compose: $greeting")
                    // Display users when loaded
                    users.forEach { user ->
                        Text(user.fullName.orEmpty())
                    }
                }
            }
        }
    }
}