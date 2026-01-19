package edu.saletdiaz.pr_clase_07.data

class RemoteDatasource {
    private val apiService = RetrofitEditorial.apiService

    suspend fun getEditorials() =apiService.getEditorials()

    suspend fun getComicsByEditorial(id: Int) = apiService.getComicsByEditId(id)
}