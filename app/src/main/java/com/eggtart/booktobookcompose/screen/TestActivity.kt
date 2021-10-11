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
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.eggtart.booktobookcompose.network.BookData
import com.eggtart.booktobookcompose.ui.theme.BookToBookComposeTheme
import javax.inject.Inject
import androidx.lifecycle.viewmodel.compose.viewModel
import com.eggtart.booktobookcompose.network.KaKaoRepository
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

@HiltViewModel
class TestViewModel @Inject constructor(
    kaKaoRepository: KaKaoRepository
) : ViewModel() {
    val books: MutableLiveData<BookData> by lazy {
        MutableLiveData<BookData>()
    }

    init {
        viewModelScope.launch {
            kaKaoRepository.books.collect {
                Log.d("KWK_VIEWMODEL",it.toString())
                books.postValue(it)
            }
        }
    }
}

@Composable
fun TestScreen(viewModel: TestViewModel = viewModel()) {
    viewModel.books.observe(LocalLifecycleOwner.current, { bookData ->
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
