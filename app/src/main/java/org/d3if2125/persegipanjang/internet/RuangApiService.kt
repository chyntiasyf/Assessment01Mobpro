package org.d3if2125.persegipanjang.internet

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import org.d3if2125.persegipanjang.ui.bangunruang.BangunRuang
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET

private const val BASE_URL = "https://raw.githubusercontent.com/chyntiasyf/repo-bangunruang/main/"
private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

private val retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .baseUrl(BASE_URL)
    .build()

interface RuangApiService {
    @GET("rumusbangunruang.json")
    suspend fun getBangunRuang():List<BangunRuang>
}

object RuangApi{
    val service : RuangApiService by lazy {
        retrofit.create(RuangApiService :: class.java)
    }

    fun getBangunRuangUrl (nama: String) : String{
        return "$BASE_URL$nama.png"
    }
}

enum class ApiStatus{LOADING, SUCCESS, FAILED}