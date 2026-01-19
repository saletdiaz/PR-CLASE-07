package edu.saletdiaz.pr_clase_07

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController
import edu.saletdiaz.pr_clase_07.ui.navigation.NavigationHost
import edu.saletdiaz.pr_clase_07.ui.screens.EditorialScreen
import edu.saletdiaz.pr_clase_07.ui.theme.PRCLASE07Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            PRCLASE07Theme {
                Surface {
                    val navController = rememberNavController()
                    NavigationHost(navController = navController)
                }
            }
        }
    }
}


