package edu.saletdiaz.pr_clase_07.ui.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

/**Clase que se tomara como plantilla para que aparezca el titulo al principio*/
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppScaffold (title: String,navigationIcon: @Composable () -> Unit = {},
                 screen: @Composable (PaddingValues) -> Unit){
    Scaffold (
        topBar ={
            TopAppBar(
                title = { Text(text = title) },
                navigationIcon = navigationIcon /**Para que pueda tener los iconos funcionales*/
            )
        }
    ) { innerPadding ->
            screen(innerPadding)
    }
}