package org.d3if3130.unspoken.network

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import org.d3if3130.unspoken.model.OpStatus
import org.d3if3130.unspoken.model.PostPostingan
import org.d3if3130.unspoken.model.Postingan
import org.d3if3130.unspoken.model.RequestIdPostingan
import org.d3if3130.unspoken.model.RequestUsername
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

private const val BASE_URL = "https://unspoken.my.id/"

private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

private val retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .baseUrl(BASE_URL)
    .build()
interface UnspokenApiService {
    @GET("Query.php")
    suspend fun getPostingan(): List<Postingan>

    @POST("Query.php")
    suspend fun postPostingan(
        @Body postPostingan: PostPostingan
    ): OpStatus

    @POST("query_postingan.php")
    suspend fun getPostinganDetail(
        @Body id_postingan: RequestIdPostingan
    ): List<Postingan>

    @POST("myPostingan_query.php")
    suspend fun getUserPostingan(
        @Body username: RequestUsername
    ): List<Postingan>
}

object PostinganApi {
    val service: UnspokenApiService by lazy {
        retrofit.create((UnspokenApiService::class.java))
    }
}