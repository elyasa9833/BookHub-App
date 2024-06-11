package org.d3if0043.bookhub.network


import retrofit2.Retrofit
import retrofit2.converter.scalars.ScalarsConverterFactory
import retrofit2.http.GET

private const val BASE_URL = "https://raw.githubusercontent.com/elyasa9833/static-api/main/"
private val retrofit = Retrofit.Builder()
    .addConverterFactory(ScalarsConverterFactory.create())
    .baseUrl(BASE_URL)
    .build()

interface BookApiService {
    @GET("data-dummy.json")
    suspend fun getBook(): String

}

object BookApi {
    val service: BookApiService by lazy {
        retrofit.create(BookApiService::class.java)
    }
}