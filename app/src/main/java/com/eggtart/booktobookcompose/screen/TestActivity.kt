package com.eggtart.booktobookcompose.screen

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.lifecycle.lifecycleScope
import com.eggtart.booktobookcompose.network.KaKaoInterface
import com.eggtart.booktobookcompose.ui.theme.BookToBookComposeTheme
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import javax.inject.Inject

//class TestViewModel : ViewModel() {
//    val value: MutableLiveData<BookData> by lazy {
//        MutableLiveData<BookData>()
//    }
//
//    init {
//        viewModelScope.launch(Dispatchers.IO) {
//            getBooks()
//        }
//    }
//
//    fun getBooks() {
//        viewModelScope.launch(Dispatchers.IO) {
//            value.postValue(
//                KakaoService.create().getApiService()
//                    .getBooks(9788901229614)
//            )
//        }
//    }
//}
//
//@Composable
//fun TestScreen(viewModel: TestViewModel) {
//    viewModel.value.observe(LocalLifecycleOwner.current, { bookData ->
//        Log.d("KWK_TEST", bookData.toString())
//    })
//}

@AndroidEntryPoint
class TestActivity : ComponentActivity() {
    @Inject lateinit var kaKaoService: KaKaoInterface

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        lifecycleScope.launchWhenCreated {
            Log.d("KWK_TEST", kaKaoService.getBooks(9788901229614).toString())
        }

        setContent {
            BookToBookComposeTheme {
                // A surface container using the 'background' color from the theme
                Surface(color = MaterialTheme.colors.background) {
//                    TestScreen(viewModel = model)
                }
            }
        }
    }


}
