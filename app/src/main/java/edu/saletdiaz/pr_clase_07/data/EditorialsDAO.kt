package edu.saletdiaz.pr_clase_07.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import edu.saletdiaz.pr_clase_07.model.ComicWithEditorial
import edu.saletdiaz.pr_clase_07.model.Editorial
import kotlinx.coroutines.flow.Flow

@Dao
interface EditorialsDAO {
    @Query("SELECT * FROM editorials")
    fun getEditorials(): Flow<List<Editorial>>

   /* @Transaction
    @Query("SELECT * FROM editorials WHERE id = :idEditorial")
    fun getComicsWithEditorial(idEditorial: Int): Flow<ComicWithEditorial>*/

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllEditorials(editorials: List<Editorial>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertEditorial(editorial:Editorial)
}