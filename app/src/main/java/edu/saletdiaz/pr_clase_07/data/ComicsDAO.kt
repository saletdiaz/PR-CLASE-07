package edu.saletdiaz.pr_clase_07.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import edu.saletdiaz.pr_clase_07.model.Comic
import kotlinx.coroutines.flow.Flow

@Dao
interface ComicsDAO {
    @Query("SELECT * FROM comics")
    fun getAllComics(): Flow<List<Comic>>
    @Query("SELECT * FROM comics WHERE editorialId = :id")
    fun getComicsByEditorialId(id:Int): Flow<List<Comic>>
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAllComics(comics: List<Comic>)
}