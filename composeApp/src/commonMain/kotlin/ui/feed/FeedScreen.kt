package ui.feed

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import co.touchlab.kermit.Logger
import io.github.jan.supabase.SupabaseClient
import io.github.jan.supabase.compose.auth.composable.NativeSignInResult
import io.github.jan.supabase.compose.auth.composable.rememberSignInWithApple
import io.github.jan.supabase.compose.auth.composable.rememberSignInWithGoogle
import io.github.jan.supabase.compose.auth.composeAuth
import io.github.jan.supabase.gotrue.auth
import io.github.jan.supabase.gotrue.providers.Google
import org.koin.compose.koinInject

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FeedScreen(
    onBackPressed: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: FeedViewModel = koinInject(),
    supabase: SupabaseClient = koinInject()
) {
    val appleLogin = supabase.composeAuth.rememberSignInWithApple(
        onResult = {},
        fallback = {}
    )
    val googleLogin = supabase.composeAuth.rememberSignInWithGoogle(
        onResult = { result -> //optional error handling
            Logger.i("PD: auth result: $result")
            when (result) {
                is NativeSignInResult.Success -> {
                    supabase.auth.currentSessionOrNull()
                }
                is NativeSignInResult.ClosedByUser -> {}
                is NativeSignInResult.Error -> {}
                is NativeSignInResult.NetworkError -> {}
            }
        },
        fallback = { // optional: add custom error handling, not required by default
            Logger.i("PD: fallback")
            supabase.auth.signInWith(Google)
        }
    )

    Scaffold(
        modifier = modifier,
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text("Feed")
                },
                navigationIcon = {
                    IconButton(
                        onClick = onBackPressed,
                        content = {
                            Icon(
                                imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                                contentDescription = "Back"
                            )
                        }
                    )
                }
            )
        },
        content = { padding ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues = padding),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Button(
                    onClick = { googleLogin.startFlow() } //optional: you can also pass in extra data for the user like a name. A nonce is automatically generated, but you can also pass in a custom nonce
                ) {
                    Text("Google Login")
                }
                Button(
                    onClick = { appleLogin.startFlow() } //optional: you can also pass in extra data for the user like a name. A nonce is automatically generated, but you can also pass in a custom nonce
                ) {
                    Text("Apple Login")
                }
            }
        }
    )
}