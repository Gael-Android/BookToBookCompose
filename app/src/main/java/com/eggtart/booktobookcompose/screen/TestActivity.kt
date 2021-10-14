package com.eggtart.booktobookcompose.screen

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.eggtart.booktobookcompose.network.BookData
import com.eggtart.booktobookcompose.ui.theme.BookToBookComposeTheme
import javax.inject.Inject
import androidx.lifecycle.viewmodel.compose.viewModel
import com.eggtart.booktobookcompose.network.KaKaoRepository
import com.eggtart.booktobookcompose.network.Meta
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

@HiltViewModel
class TestViewModel @Inject constructor(
    kaKaoRepository: KaKaoRepository
) : ViewModel() {
    val books = MutableStateFlow(
        KaKaoAPIState.Success(
            Meta(false, 0, 0)
        )
    )

    init {
        viewModelScope.launch {
            kaKaoRepository.getBooks(9788901229614)
                .collect {
                    Log.d("KWK_VIEWMODEL", it.toString())
                    books.value = KaKaoAPIState.Success(it)
                }
        }
    }
}

sealed class KaKaoAPIState {
    data class Success(val meta: Meta) : KaKaoAPIState()
}

@Composable
fun TestScreen(viewModel: TestViewModel = viewModel()) {
    val state by viewModel.books.collectAsState()

    Log.d("KWK_SCREEN", state.meta.toString())
}

@AndroidEntryPoint
class TestActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BookToBookComposeTheme {
                // A surface container using the 'background' color from the theme
                Surface(color = MaterialTheme.colors.background) {
                    TestScreen()
                }
            }
        }
    }


}
