package com.eggtart.booktobookcompose.screen

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.eggtart.booktobookcompose.data.BookData
import com.eggtart.booktobookcompose.data.RetrofitClass
import com.eggtart.booktobookcompose.ui.theme.BookToBookComposeTheme
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class TestViewModel : ViewModel() {
    val value: MutableLiveData<BookData> by lazy {
        MutableLiveData<BookData>()
    }

//    init {
//        viewModelScope.launch(Dispatchers.IO) {
//            getBooks()
//        }
//    }

    fun getBooks() {
        viewModelScope.launch(Dispatchers.IO) {
            value.postValue(
                RetrofitClass.getApiService()
                    .getBooks(9788901229614)
            )
        }
    }
}

@Composable
fun TestScreen(viewModel: TestViewModel) {
    viewModel.value.observe(LocalLifecycleOwner.current, { bookData ->
        Log.d("ASDASF", bookData.toString())
    })
}

class TestActivity : ComponentActivity() {
    private val model: TestViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        model.getBooks()
        setContent {
            BookToBookComposeTheme {
                // A surface container using the 'background' color from the theme
                Surface(color = MaterialTheme.colors.background) {
                    TestScreen(viewModel = model)
                }
            }
        }
    }
}
