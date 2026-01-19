package edu.saletdiaz.pr_clase_07.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import edu.saletdiaz.pr_clase_07.MainViewModel
import edu.saletdiaz.pr_clase_07.model.Comic
import edu.saletdiaz.pr_clase_07.ui.components.AppScaffold
import edu.saletdiaz.pr_clase_07.R

/**Funcion que mostrara y recorrera los comics con un LazyColumn y con la plantilla
 * predeterminada de AppScaffold.kt*/
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ComicScreen(idEditorial: Int, viewModel: MainViewModel, onBack: () -> Unit) {
    val comics by viewModel.comics.collectAsState()
    val loading by viewModel.loading.collectAsState()

    LaunchedEffect(idEditorial) {
        viewModel.fetchComicsByEditorial(idEditorial)
    }

    AppScaffold(
        title = stringResource(R.string.txt_comic) ,
        navigationIcon = {
            IconButton(onClick = onBack) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                    contentDescription = stringResource(R.string.btn_back)
                )
            }
        }
    ) { paddingValues ->

        /**Lanzaremos la lista de comics, llamando a ComicCard para pintar cada tarjetita*/

        LazyColumn(modifier = Modifier.padding(paddingValues).fillMaxSize()) {
            items(comics) { comic ->
                ComicCard(comic)
            }
        }
    }
}

/**Encargado de sacar la plantilla para un comic*/
@Composable
fun ComicCard(comic: Comic) {
    Card(
        modifier = Modifier.fillMaxWidth().padding(8.dp),
        shape = RoundedCornerShape(8.dp)
    ) {
        Row(
            modifier = Modifier.padding(12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            ImagenConGlide(
                imageUrl = comic.cover ?: "",
                modifier = Modifier.size(80.dp, 120.dp)
            )

            Column(modifier = Modifier.weight(1f).padding(start = 16.dp)) {
                Text(text = comic.title, style = MaterialTheme.typography.titleMedium)
                Text(text = "Author: ${comic.author}", style = MaterialTheme.typography.bodySmall)
            }

            ImagenConGlide(
                imageUrl = comic.editorialLogo,
                modifier = Modifier
                    .size(35.dp)
                    .clip(CircleShape)
            )
        }
    }
}

/**Utilizamores Glide para poder cargar las imagenes por internet */
@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun ImagenConGlide(imageUrl: String, modifier: Modifier = Modifier) {
    GlideImage(
        model = imageUrl,
        contentDescription = "img",
        modifier = modifier,
        contentScale = ContentScale.Crop
    )
}