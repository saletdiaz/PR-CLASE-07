package edu.saletdiaz.pr_clase_07.data

import edu.saletdiaz.pr_clase_07.model.Comic
import edu.saletdiaz.pr_clase_07.model.Editorial
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path

object RetrofitEditorial {
    private const val BASE_URL = "https://www.javiercarrasco.es/api/"

    val apiService: ApiService by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiService::class.java)
    }
}

interface ApiService {
    @GET("supers/editorials")
    suspend fun getEditorials(): Response<List<Editorial>>

    @GET("supers/comicsed/{id}")
    suspend fun getComicsByEditId(@Path("id") id: Int ): Response<List<Comic>>
    
}