package org.d3if0043.bookhub.network


import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okhttp3.MultipartBody
import okhttp3.RequestBody
import org.d3if0043.bookhub.model.Book
import org.d3if0043.bookhub.model.OpStatus
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part
import retrofit2.http.Query

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
    suspend fun getBook(
        @Header("Authorization") userId: String
    ): List<Book>

    @Multipart
    @POST("book.php")
    suspend fun postBook(
        @Header("Authorization") userId: String,
        @Part("title") title: RequestBody,
        @Part image: MultipartBody.Part
    ): OpStatus

    @DELETE("hewan.php")
    suspend fun delete(
        @Header("Authorization") userId: String,
        @Query("bookId") bookId: String
    ): OpStatus
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