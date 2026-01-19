package edu.saletdiaz.pr_clase_07.data

import edu.saletdiaz.pr_clase_07.model.Comic
import edu.saletdiaz.pr_clase_07.model.Editorial
import kotlinx.coroutines.flow.Flow

class LocalDatasource (private val dao: EditorialsDAO, val daoC: ComicsDAO) {
    fun getEditorials(): Flow<List<Editorial>> = dao.getEditorials()

    suspend fun insertAllEditorials(editorials: List<Editorial>) {
        dao.insertAllEditorials(editorials)
    }
    suspend fun insertEditorial(editorial: Editorial) {
        dao.insertEditorial(editorial)
    }

    /** Obtiene todos los Comics de una sola editorial */
    fun getComicsByEditorialId(idEd: Int): Flow<List<Comic>> =
        daoC.getComicsByEditorialId(idEd)
}