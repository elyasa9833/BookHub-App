package org.d3if0043.bookhub.network


import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import org.d3if0043.bookhub.model.Book
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET

private const val BASE_URL = "https://mainly-fond-cod.ngrok-free.app/d3if0043/"

private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

private val retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .baseUrl(BASE_URL)
    .build()

interface BookApiService {
    @GET("book.php")
    suspend fun getBook(): List<Book>

}

object BookApi {
    val service: BookApiService by lazy {
        retrofit.create(BookApiService::class.java)
    }

    fun getBookUrl(imageUrl: String): String {
        return "$BASE_URL/src/image/$imageUrl"
    }
}

enum class ApiStatus { LOADING, SUCCESS, FAILED }