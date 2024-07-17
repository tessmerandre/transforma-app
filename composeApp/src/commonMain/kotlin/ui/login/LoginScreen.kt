package ui.login

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import co.touchlab.kermit.Logger
import io.github.jan.supabase.SupabaseClient
import io.github.jan.supabase.compose.auth.composable.NativeSignInResult
import io.github.jan.supabase.compose.auth.composable.rememberSignInWithApple
import io.github.jan.supabase.compose.auth.composable.rememberSignInWithGoogle
import io.github.jan.supabase.compose.auth.composeAuth
import io.github.jan.supabase.gotrue.auth
import io.github.jan.supabase.gotrue.providers.Google
import kotlinx.coroutines.launch
import org.koin.compose.koinInject
import org.koin.compose.viewmodel.koinViewModel
import org.koin.core.annotation.KoinExperimentalAPI

@OptIn(ExperimentalMaterial3Api::class, KoinExperimentalAPI::class)
@Composable
fun LoginScreen(
    goToHome: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: LoginViewModel = koinViewModel(),
    supabase: SupabaseClient = koinInject()
) {
    val scope = rememberCoroutineScope()
    val snackbarHostState = remember { SnackbarHostState() }

    val appleLogin = supabase.composeAuth.rememberSignInWithApple(
        onResult = {},
        fallback = {}
    )
    val googleLogin = supabase.composeAuth.rememberSignInWithGoogle(
        onResult = { result ->
            // probably move this to the view model -- something like vm.onUiEvent(UiEvent.SignInResult(result))
            // which then updates the state (or triggers events channel).
            // alternatively we could somehow subscribe to the current user session, and as soon as
            // supabase updates it, we update the state, this way we don't have to handle this right here.
            when (result) {
                is NativeSignInResult.Success -> goToHome()
                is NativeSignInResult.Error -> {
                    scope.launch { snackbarHostState.showSnackbar(result.message) }
                }
                is NativeSignInResult.NetworkError -> {
                    scope.launch { snackbarHostState.showSnackbar(result.message) }
                }
                is NativeSignInResult.ClosedByUser -> {}
            }
        },
        fallback = { supabase.auth.signInWith(Google) }
    )

    Scaffold(
        modifier = modifier,
        snackbarHost = {
            SnackbarHost(hostState = snackbarHostState)
        },
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text("Login")
                }
            )
        },
        content = { padding ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues = padding),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                Button(
                    onClick = googleLogin::startFlow
                ) {
                    Text("Google Login")
                }
                Button(
                    onClick = appleLogin::startFlow
                ) {
                    Text("Apple Login")
                }
                Button(
                    onClick = goToHome
                ) {
                    Text("Go to Home Screen")
                }
            }
        }
    )
}