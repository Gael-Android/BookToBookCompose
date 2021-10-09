package com.eggtart.booktobookcompose.network

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface KaKaoInterface {
    @Headers("Authorization: KakaoAK ${KaKaoSettings.serviceKey}")
    @GET("v3/search/book?target=isbn")
    suspend fun getBooks(
        @Query("query") query: Long
    ): BookData
}

@Module
@InstallIn(ActivityComponent::class)
object KakaoService {
    private const val BASE_URL: String = KaKaoSettings.baseUrl

    @Provides
    fun provide(): KaKaoInterface {
        val client = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        return client.create(KaKaoInterface::class.java)
    }
}


