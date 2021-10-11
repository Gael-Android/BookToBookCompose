package com.eggtart.booktobookcompose.network

import android.util.Log
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.android.components.ViewModelComponent
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.runBlocking
import okhttp3.Dispatcher
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query
import javax.inject.Inject

interface KaKaoService {
    @Headers("Authorization: KakaoAK ${KaKaoSettings.serviceKey}")
    @GET("v3/search/book?target=isbn")
    suspend fun getBooks(
        @Query("query") query: Long
    ): BookData
}

@Module
@InstallIn(ViewModelComponent::class)
class KakaoModule {

    @Provides
    fun provide(): KaKaoService {
        val client = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        return client.create(KaKaoService::class.java)
    }

    companion object {
        private const val BASE_URL: String = KaKaoSettings.baseUrl
    }
}

class KaKaoRemoteSource @Inject constructor(
    private val kaKaoService: KaKaoService
) {
    val books: Flow<BookData> = flow {
            val books = kaKaoService.getBooks(9788901229614)
            Log.d("KWK_REMOTE", books.toString())
            emit(books)
    }
}

class KaKaoRepository @Inject constructor(
    kaKaoRemoteSource: KaKaoRemoteSource
) {
    val books: Flow<BookData> = kaKaoRemoteSource.books
        .filterNotNull()
}




