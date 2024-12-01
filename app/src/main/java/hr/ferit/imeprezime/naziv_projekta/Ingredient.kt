package hr.ferit.imeprezime.naziv_projekta

import androidx.annotation.DrawableRes

data class Ingredient(
    @DrawableRes val image: Int,
    val title: String,
    val subtitle: String
)