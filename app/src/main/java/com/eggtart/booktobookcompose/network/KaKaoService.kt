package com.eggtart.booktobookcompose.network

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.android.components.ViewModelComponent
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

class KaKaoRepository @Inject constructor(
    private val kaKaoService: KaKaoService
) {
    suspend fun getBooks(query: Long): BookData = kaKaoService.getBooks(query)
}



