package com.eggtart.booktobookcompose.screen

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.eggtart.booktobookcompose.network.BookData
import com.eggtart.booktobookcompose.network.KaKaoInterface
import com.eggtart.booktobookcompose.ui.theme.BookToBookComposeTheme
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject
import androidx.lifecycle.viewmodel.compose.viewModel
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.lifecycle.HiltViewModel

@HiltViewModel
class TestViewModel @Inject constructor(
    private val kaKaoService: KaKaoInterface
) : ViewModel() {
    val value: MutableLiveData<BookData> by lazy {
        MutableLiveData<BookData>()
    }

    init {
        viewModelScope.launch(Dispatchers.IO) {
            getBooks()
        }
    }

    private fun getBooks() {
        viewModelScope.launch(Dispatchers.IO) {
            value.postValue(
                kaKaoService.getBooks(9788901229614)
            )
        }
    }
}

@Composable
fun TestScreen(viewModel: TestViewModel = viewModel()) {
    viewModel.value.observe(LocalLifecycleOwner.current, { bookData ->
        Log.d("KWK_TEST", bookData.toString())
    })
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
