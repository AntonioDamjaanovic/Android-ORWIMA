package hr.ferit.imeprezime.naziv_projekta

import android.media.Image
import androidx.annotation.DrawableRes

data class Recipe(
    @DrawableRes val image: Int,
    val title: String,
    val category: String,
    val cookingTime: String,
    val energy: String,
    val rating: String,
    val description: String,
    val reviews: String,
    val ingredients: List<Ingredient>
)