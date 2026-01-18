package edu.saletdiaz.pr_clase_07.ui.screens

import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable

@Composable
fun EditorialScreen (onGoComic: () -> Unit){
    Text(text = "HOLA COMO ESTAS , PANTALLA EDITORIAL")
    Button(
        onGoComic
    ) {
        Text(text = "ir a la pantalla de comic ")
    }

}