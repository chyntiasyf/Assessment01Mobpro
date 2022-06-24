package org.d3if2125.persegipanjang.internet

import retrofit2.Retrofit
import retrofit2.converter.scalars.ScalarsConverterFactory
import retrofit2.http.GET

private const val BASE_URL = "https://raw.githubusercontent.com/chyntiasyf/repo-bangunruang/main/"
private val retrofit = Retrofit.Builder()
    .addConverterFactory(ScalarsConverterFactory.create())
    .baseUrl(BASE_URL)
    .build()

interface RuangApiService {
    @GET("rumusbangunruang.json")
    suspend fun getBangunRuang():String
}

object RuangApi{
    val service : RuangApiService by lazy {
        retrofit.create(RuangApiService :: class.java)
    }
}