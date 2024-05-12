package ru.myworld.pokemon.api

import okhttp3.MultipartBody
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path
import ru.myworld.pokemon.dto.Detail
import ru.myworld.pokemon.dto.ResultPoke

private const val BASE_URL = "https://pokeapi.co/api/v2/pokemon/"

private val logging = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
}

private val okhttp = OkHttpClient.Builder()
    .addInterceptor(logging)
    .build()

private val retrofit = Retrofit.Builder()
    .addConverterFactory(GsonConverterFactory.create())
    .baseUrl(BASE_URL)
    .client(okhttp)
    .build()

interface ApiService {
    @GET("?offset=0&limit=1302")
    suspend fun getAllPoke(): Response<ResultPoke>

    @GET("{id}")
    suspend fun getDetail(@Path ("id") id: Int): Response<Detail>
}

object Api {
    val service: ApiService by lazy {
        retrofit.create(ApiService::class.java)
    }
}