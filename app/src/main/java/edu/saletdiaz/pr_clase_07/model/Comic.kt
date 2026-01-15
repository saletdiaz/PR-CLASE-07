package edu.saletdiaz.pr_clase_07.model

import androidx.room.Entity
import androidx.room.PrimaryKey

/**Clase comic la cual se encargará de tener portada, titulo,
 * autor y la editorial a la que pertenece*/
@Entity(tableName = "comic")
data class Comic(
    @PrimaryKey(autoGenerate = true)
    val idComic: Int = 0,
    val frontPage: String,
    val title: String,
    val author: String,
    val idEditorial: Int = 0
)
