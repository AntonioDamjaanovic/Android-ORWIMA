package hr.ferit.imeprezime.naziv_projekta

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import hr.ferit.imeprezime.naziv_projekta.ui.RecipesScreen
import hr.ferit.imeprezime.naziv_projekta.ui.theme.RecipeCardTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
             RecipeCardTheme {
                 RecipesScreen()
             }
        }
    }
}
