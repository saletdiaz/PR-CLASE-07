package edu.saletdiaz.pr_clase_07.model

import androidx.room.Embedded
import androidx.room.Relation

/**Clase encargada de mostrar la relación N:1*/
data class ComicWithEditorial(
    @Embedded val comics: Comic,
    @Relation(
        parentColumn = "idEditorial",
        entityColumn = "idEd"
    )
    val editorial: Editorial
)
