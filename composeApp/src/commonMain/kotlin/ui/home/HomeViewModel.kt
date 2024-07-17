package ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.stateIn

class HomeViewModel : ViewModel() {

    private val mockItems = List(100) {
        HomeItem(
            id = it,
            title = "Item $it",
            description = "Item description"
        )
    }

    val uiState = getUiState().stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000),
        initialValue = HomeUiState.INITIAL
    )

    private fun getUiState(): Flow<HomeUiState> {
        return flow {
            emit(HomeUiState.INITIAL)
            delay(500)
            emit(HomeUiState(loading = false, items = mockItems))
        }
    }
}

data class HomeItem(val id: Int, val title: String, val description: String)

data class HomeUiState(
    val loading: Boolean,
    val items: List<HomeItem>
) {
    companion object {
        val INITIAL = HomeUiState(loading = true, items = emptyList())
    }
}