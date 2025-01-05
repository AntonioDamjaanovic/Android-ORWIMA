package hr.ferit.imeprezime.naziv_projekta

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import com.google.firebase.Firebase
import com.google.firebase.firestore.firestore
import hr.ferit.imeprezime.naziv_projekta.data.RecipeViewModel
import hr.ferit.imeprezime.naziv_projekta.ui.RecipesScreen
import hr.ferit.imeprezime.naziv_projekta.ui.theme.RecipeCardTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val viewModel by viewModels<RecipeViewModel>()

        enableEdgeToEdge()
        setContent {
             RecipeCardTheme {

                 NavigationController(viewModel)

             }
        }
    }
}
