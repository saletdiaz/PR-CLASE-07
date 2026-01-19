package edu.saletdiaz.pr_clase_07.ui.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.pulltorefresh.PullToRefreshBox
import androidx.compose.material3.pulltorefresh.rememberPullToRefreshState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.unit.dp
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import edu.saletdiaz.pr_clase_07.MainViewModel
import edu.saletdiaz.pr_clase_07.model.Editorial
import edu.saletdiaz.pr_clase_07.ui.components.AppScaffold

/**Mostrar nombre, logo , año fundación, y URL del sitio web , boton favorites*/
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EditorialScreen(viewModel: MainViewModel, onEditorialClick: (Int) -> Unit) {
    val editorials: List<Editorial> by viewModel.editorials.collectAsState()
    val loading by viewModel.loading.collectAsState()
    val error by viewModel.erro.collectAsState()

    val refreshState = rememberPullToRefreshState()

    LaunchedEffect(Unit) {
        if (editorials.isEmpty() && !loading && error == null) {
            viewModel.fetchEditorials()
        }
    }

    AppScaffold(
        "Editorials screen"
    ) { paddingValues ->
            Column (modifier = Modifier.padding(paddingValues)){
                if (error != null ) {
                    Text(text = "Error: $error", color = Color.Red, modifier = Modifier.padding(16.dp))
                    Button(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(8.dp),
                        onClick = {viewModel.fetchEditorials()}
                    ) {
                        Text("Update")
                    }
                } else {
                    PullToRefreshBox(
                        isRefreshing =loading,
                        state = refreshState,
                        modifier = Modifier.fillMaxSize(),
                        onRefresh = {viewModel.fetchEditorials()}
                    ) {
                        LazyColumn {
                            items(editorials) { editorial ->
                                EditorialCard(
                                    editorial = editorial,
                                    onGoComic = { onEditorialClick(editorial.id) },
                                    onFavorite = {
                                        viewModel.favoritesUpdate(editorial)
                                    }
                                )
                            }
                        }
                    }
                }
            }
    }
}

@Composable
fun EditorialCard(editorial: Editorial, onGoComic: () -> Unit, onFavorite: () -> Unit) {
    val uriHandler = LocalUriHandler.current
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clickable { onGoComic() }
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            ImagenConGlide(imageUrl = editorial.logo)
            Column(modifier = Modifier.padding(8.dp).weight(1f)) {
                Text(text = editorial.editorial, style = MaterialTheme.typography.titleLarge)
                Text(text = "Year foundation: ${editorial.year}", style = MaterialTheme.typography.bodySmall)
                TextButton(
                    onClick = {
                        uriHandler.openUri(editorial.url)
                    },
                    modifier = Modifier.padding(top = 4.dp)
                ) {
                    Text(text = "open web", style = MaterialTheme.typography.bodyMedium)
                }
                IconButton(
                    onClick = { onFavorite() }
                ) {
                    Icon(
                        imageVector = if (editorial.isFavorite) Icons.Default.Favorite else Icons.Default.FavoriteBorder,
                        contentDescription = "Favorite",
                        tint = if (editorial.isFavorite) Color.Green else Color.Gray
                    )
                }
            }
        }
    }
}
@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun ImagenConGlide(imageUrl: String) {
    GlideImage(
        model = imageUrl,
        contentDescription = "Imagen con Glide",
        modifier = Modifier
            .padding(16.dp)
            .size(100.dp)
            .clip(RoundedCornerShape(12.dp)),
        contentScale = ContentScale.Crop
    )
}
