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

) {
    /**Utilizaremos esta variable para el logo de la editorial
     * Lo ponemos como ignore, para que no la ponga en la base de datos pero si que la
     * muestre en la UI*/
    @Ignore
    var editorialLogo: String = ""
}
