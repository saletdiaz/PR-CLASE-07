package edu.saletdiaz.pr_clase_07.model

import android.R
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.Date

/**Clase editorial la cual tendrá como atributos
 * logo, nombre, año de fundación y
 * la URL de su sitio web -> Pedir permiso para que se vaya a abrir la url
 * Esto se logra con el Tema 3 : Intents y permisos*/
@Entity(tableName = "editorial")
data class Editorial(
    @PrimaryKey(autoGenerate = true ) val idEd: Int = 0,
    val logo: String,
    val name: String,
    val year: String,
    val url: String,
    var isFavorite: Boolean = false
)
