package com.eggtart.booktobookcompose.data

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

object RetrofitClass {
    private const val BASE_URL: String = KaKaoSettings.baseUrl
    private val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    fun getApiService() : KaKaoInterface = retrofit.create(KaKaoInterface::class.java)
}

