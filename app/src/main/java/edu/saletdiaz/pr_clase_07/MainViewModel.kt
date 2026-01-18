package edu.saletdiaz.pr_clase_07

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
}