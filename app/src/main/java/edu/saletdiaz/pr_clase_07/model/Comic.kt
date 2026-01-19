package edu.saletdiaz.pr_clase_07.model

import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

/**Clase comic la cual se encargará de tener portada, titulo,
 * autor y la editorial a la que pertenece*/
@Entity(tableName = "comics")
data class Comic(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val title: String,
    val author: String?,
    val cover: String?,
    var editorialId: Int = 0,
    /**Aqui aumentaremos el constructor de editorial
    @Ignore // solo se usará para la UI
    var editorialLogo: String = ""*/
) {
    /*constructor(id: Int, title: String, author: String?, cover: String?, editorialId: Int)
    :this(id, title, author, cover,editorialId, "")*/
    @Ignore
    val editorial: Editorial? =  null
    @Ignore
    var editorialLogo: String = ""
}
