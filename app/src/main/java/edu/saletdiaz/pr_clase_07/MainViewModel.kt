package edu.saletdiaz.pr_clase_07

import android.R.attr.id
import android.app.Application
import androidx.compose.ui.platform.LocalView
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import edu.saletdiaz.pr_clase_07.data.AppDatabase
import edu.saletdiaz.pr_clase_07.data.LocalDatasource
import edu.saletdiaz.pr_clase_07.data.RemoteDatasource
import edu.saletdiaz.pr_clase_07.data.Repository
import edu.saletdiaz.pr_clase_07.model.Comic
import edu.saletdiaz.pr_clase_07.model.Editorial
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class MainViewModel(application: Application) : AndroidViewModel(application) {
    private val repository: Repository
    private val remoteDatasource: RemoteDatasource
    private val localDatasource: LocalDatasource

    private val _editorials = MutableStateFlow<List<Editorial>>(emptyList())
    val editorials: StateFlow<List<Editorial>> = _editorials

    private val _comics = MutableStateFlow<List<Comic>>(emptyList())
    val comics: StateFlow<List<Comic>> = _comics

    private val _loading = MutableStateFlow(false)
    val loading: StateFlow<Boolean> = _loading

    /**Estado de error*/
    private val _error = MutableStateFlow<String?>(null)
    val erro: StateFlow<String?> = _error

    init {
        /**Inicialización de la base de datos local */
        val database = AppDatabase.getInstance(application)
        val dao = database.editorialsDAO()
        val daoC = database.comicsDAO()

        remoteDatasource = RemoteDatasource()
        localDatasource = LocalDatasource(dao, daoC)
        repository = Repository(remoteDatasource, localDatasource)

        fetchEditorials()
    }
    fun fetchEditorials() {
        viewModelScope.launch {
            _loading.value = true
            _error.value = null

            try {
                val editorials = repository.getEditorials()
                _editorials.value = editorials ?:emptyList()
            } catch (e: Exception) {
                _error.value = "ERROR: ${e.message}"
            } finally {
                _loading.value = false
            }
        }
    }
    /**para actualizar el boton de favoritos*/
    fun favoritesUpdate(editorial: Editorial) {
        viewModelScope.launch {
            val edit = repository.updateFavorite(editorial)
            _editorials.value = _editorials.value.map {
                if (it.id == editorial.id) edit else it
            }
        }
    }
    /*fun fetchComicsByEditorial(idEditorial: Int) {
        viewModelScope.launch {
            _loading.value = true
            try {
                val comicsResponse = repository.getComicsByEditorial(idEditorial) ?: emptyList()

                val idEd = _editorials.value.find { it.id == idEditorial }
                val logoUrl = idEd?.logo ?: ""

                comicsResponse.forEach { comic ->
                    comic.editorialLogo = logoUrl
                }

                _comics.value = comicsResponse
            } catch (e: Exception) {
                _error.value = e.message
            } finally {
                _loading.value = false
            }
        }
    }*/
    fun fetchComicsByEditorial(idEditorial: Int) {
        viewModelScope.launch {
            _loading.value = true
            val res = repository.getComicsByEditorial(idEditorial)

            val logo = _editorials.value.find { it.id == idEditorial }?.logo ?: ""

            res.forEach { it.editorialLogo = logo }

            _comics.value = res
            _loading.value = false
        }
    }
}