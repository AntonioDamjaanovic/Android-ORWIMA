package hr.ferit.imeprezime.naziv_projekta.ui

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldColors
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import hr.ferit.imeprezime.naziv_projekta.R
import hr.ferit.imeprezime.naziv_projekta.Routes
import hr.ferit.imeprezime.naziv_projekta.ui.theme.Pink

class Ingredient(
    @DrawableRes val image: Int,
    val title: String,
    val subtitle: String
)

class Recipe(
    @DrawableRes val image: Int,
    val title: String,
    val category: String,
    val cookingTime: Int,
    val energy: Int,
    val rating: Double,
    val description: String,
    var isFavorited: Boolean,
    val reviews: String,
    val ingredients: List<Ingredient>
)

val recipes: List<Recipe> = listOf(
    Recipe(
        image = R.drawable.strawberry_pie_1,
        title = "Strawberry Cake",
        category = "Desserts",
        cookingTime = 50,
        energy = 620,
        rating = 4.9,
        description = "This dessert is very tasty and not difficult to",
        reviews = "10 reviews",
        isFavorited = false,
        ingredients = listOf(
            Ingredient(R.drawable.flour,"Flour", "450 g"),
            Ingredient(R.drawable.eggs, "Eggs", "4"),
            Ingredient(R.drawable.juice, "Lemon juice", "150 g"),
            Ingredient(R.drawable.strawberry, "Strawberry", "200 g"),
            Ingredient(R.drawable.suggar, "Sugar", "1 cup"),
            Ingredient(R.drawable.mint, "Mint", "20 g"),
            Ingredient(R.drawable.vanilla, "Vanilla", "1/2 teaspoon"),
        )
    ),

    Recipe(
        image = R.drawable.baklava,
        title = "baklava",
        category = "Desserts",
        cookingTime = 23,
        energy = 222,
        rating = 2.9,
        description = "This dessert is very tasty and not difficult",
        reviews = "10 reviews",
        isFavorited = false,
        ingredients = listOf(
            Ingredient(R.drawable.flour, "Flour", "450 g"),
            Ingredient(R.drawable.eggs, "Eggs", "4"),
            Ingredient(R.drawable.juice, "Lemon juice", "150 g"),
            Ingredient(R.drawable.strawberry, "Strawberry", "200 g"),
            Ingredient(R.drawable.suggar, "Sugar", "1 cup"),
            Ingredient(R.drawable.mint, "Mint", "20 g"),
            Ingredient(R.drawable.vanilla, "Vanilla", "1/2 teaspoon"),
        )
    ),
    Recipe(
        image = R.drawable.apple_pie,
        title = "Apple pie",
        category = "Desserts",
        cookingTime = 62,
        energy = 511,
        rating = 3.9,
        description = "This dessert is very tasty and not difficult to prepare.",
        reviews = "10 reviews",
        isFavorited = false,
        ingredients = listOf(
            Ingredient(R.drawable.flour, "Flour", "450 g"),
            Ingredient(R.drawable.eggs, "Eggs", "4"),
            Ingredient(R.drawable.juice, "Lemon juice", "150 g"),
            Ingredient(R.drawable.strawberry, "Strawberry", "200 g"),
            Ingredient(R.drawable.suggar, "Sugar", "1 cup"),
            Ingredient(R.drawable.mint, "Mint", "20 g"),
            Ingredient(R.drawable.vanilla, "Vanilla", "1/2 teaspoon"),
        )
    ),

    Recipe(
        image = R.drawable.strawberry_pie_3,
        title = "baklava",
        category = "Desserts",
        cookingTime = 23,
        energy = 222,
        rating = 2.9,
        description = "This dessert is very tasty and not difficult",
        reviews = "10 reviews",
        isFavorited = false,
        ingredients = listOf(
            Ingredient(R.drawable.flour, "Flour", "450 g"),
            Ingredient(R.drawable.eggs, "Eggs", "4"),
            Ingredient(R.drawable.juice, "Lemon juice", "150 g"),
            Ingredient(R.drawable.strawberry, "Strawberry", "200 g"),
            Ingredient(R.drawable.suggar, "Sugar", "1 cup"),
            Ingredient(R.drawable.mint, "Mint", "20 g"),
            Ingredient(R.drawable.vanilla, "Vanilla", "1/2 teaspoon"),
        )
    )

)


@Composable
fun RecipesScreen (modifier: Modifier = Modifier, navigation: NavController) {

    Column(
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxSize()
    ){
        ScreenTitle(
            title = "What would you like to cook today?",
            subtitle = "Good morning, Antonio"
        )
        SearchBar(
            iconResource = R.drawable.ic_search,
            labelText = "Search"
        )
        RecipeCategories()

        RecipeList(navigation)

        IconButton(iconResource=R.drawable.ic_plus, text = "Add recipes")
    }

}


@Composable
fun ScreenTitle(modifier: Modifier = Modifier, title : String, subtitle : String) {

    Box(modifier = Modifier
        .fillMaxWidth()
        .padding(top = 16.dp)){
        Text(
            text = title,
            color = Color.Black,
            fontSize = 26.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(horizontal = 15.dp, vertical = 15.dp))

        Text(
            text = subtitle,
            color = Color.Magenta,
            fontSize = 12.sp,
            fontWeight = FontWeight.Light,
            fontStyle = FontStyle.Italic,
            modifier = Modifier.padding(horizontal = 15.dp))

    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchBar(modifier: Modifier = Modifier, @DrawableRes iconResource: Int, labelText: String,
              colors:TextFieldColors = TextFieldDefaults.textFieldColors(
                  containerColor = Color.Transparent,
                  unfocusedPlaceholderColor = Color.DarkGray,
                  unfocusedTextColor = Color.DarkGray,
                  unfocusedLabelColor = Color.DarkGray,
                  focusedIndicatorColor = Color.Transparent,
                  unfocusedIndicatorColor = Color.Transparent,
                  disabledIndicatorColor = Color.Transparent)) {

    var searchInput by remember { mutableStateOf("") }

    TextField(value = searchInput,
        onValueChange={searchInput = it},
        label = {Text(labelText)},
        leadingIcon = { Icon(painter = painterResource(id=iconResource),
            contentDescription = labelText,
            tint = Color.DarkGray,
            modifier = Modifier
                .width(16.dp)
                .height(16.dp)) },
        colors = colors,
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp))

}

@Composable
fun TabButton(modifier: Modifier = Modifier,text: String, isActive: Boolean,onClick: () -> Unit) {
    Button(shape = RoundedCornerShape(24.dp),
        elevation = null,
        colors = if(isActive) ButtonDefaults.buttonColors(contentColor = Color.White,
            containerColor=Color.Magenta)
        else ButtonDefaults.buttonColors(contentColor = Color.DarkGray,
            containerColor=Color.LightGray ),
        modifier = Modifier.fillMaxHeight(),
        onClick = { onClick() }){

        Text(text)
    }

}

@Composable
fun RecipeCategories(modifier: Modifier = Modifier) {
    var currentActiveButton by remember { mutableStateOf(0)} // za praćenje stanja
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .padding(horizontal = 16.dp, vertical = 16.dp)
            .background(Color.Transparent)
            .fillMaxWidth()
            .height(44.dp)
    ){
        TabButton(text = "All", isActive = currentActiveButton == 0) {
            currentActiveButton = 0
        }
        TabButton(text = "Breakfast", isActive = currentActiveButton == 1) {
            currentActiveButton = 1
        }
        TabButton(text = "Lunch",isActive = currentActiveButton == 2){
            currentActiveButton = 2
        }
    }

}

@Composable
fun IconButton(modifier: Modifier = Modifier,@DrawableRes iconResource : Int,
               text: String) {
    Button(
        onClick = { /*TODO*/},
        colors = ButtonDefaults.buttonColors(containerColor = Pink)
    ){
        Row{
            Icon(painter = painterResource(id=iconResource),contentDescription = "")
            Spacer(Modifier.width(2.dp))
            Text(text=text,
                style = TextStyle(fontSize = 16.sp, fontWeight = FontWeight.Light))
        }
    }

}

@Composable
fun Chip(
    modifier: Modifier = Modifier,
    text:String,
    backgroundColor: Color = Color.White,
    textColor:Color = Color.Magenta
) {
    Box(modifier = Modifier
        .background(color = backgroundColor, shape = RoundedCornerShape(12.dp))
        .padding(horizontal = 8.dp, vertical = 2.dp)){
        Text(
            text = text,
            style = TextStyle(color=textColor, fontSize = 12.sp)
        )
    }
}


@Composable
fun RecipeCard(
    modifier: Modifier = Modifier,
    @DrawableRes imageResource: Int,title:
    String,onClick: () -> Unit
) {

    Card(
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(containerColor = Color.LightGray),
        modifier = modifier
            .padding(bottom = 16.dp)
            .height(326.dp)
            .width(215.dp)
            .clickable { onClick() } // Dodajemo navigaciju klikom
    ) {
        // Glavni Box unutar Card-a
        Box {
            // Slika recepta
            Image(
                painter = painterResource(id = imageResource),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxSize()
            )

            // Kolona s tekstom i detaljima na dnu
            Column(
                horizontalAlignment = Alignment.Start,
                verticalArrangement = Arrangement.Bottom,
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp)
            ) {
                Text(
                    text = title,
                    letterSpacing = 0.32.sp,
                    style = TextStyle(
                        color = Color.Black,
                        fontWeight = FontWeight.SemiBold,
                        fontSize = 16.sp
                    ),
                    modifier = Modifier.fillMaxWidth()
                )

                Row {
                    Chip(text = "30 min")
                    Spacer(Modifier.width(4.dp))
                    Chip(text = "4 ingredients")
                }
            }
        }
    }
}
@Composable
fun RecipeList(navigation: NavController) {
    Column {
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 12.dp)
        ) {
            Text(
                text = "7 recipes",
                style = TextStyle(color = Color.DarkGray, fontSize = 14.sp)
            )
            Icon(
                painter = painterResource(id = R.drawable.ic_flame),
                contentDescription = "Flame",
                tint = Color.DarkGray,
                modifier = Modifier
                    .width(18.dp)
                    .height(18.dp)
            )
        }
        LazyRow(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
        ) {
            items(recipes.size) { index ->
                RecipeCard(
                    imageResource = recipes[index].image,
                    title = recipes[index].title
                ) {
                    navigation.navigate(Routes.getRecipeDetailsPath(index))
                }
                Spacer(modifier = Modifier.width(8.dp))
            }
        }
    }
}

@Composable
fun IngredientCard(
    @DrawableRes iconResource: Int,
    title: String,
    subtitle: String
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.padding(bottom = 16.dp)
    ) {
        Card(
            shape = RoundedCornerShape(12.dp),
            colors = CardDefaults.cardColors(containerColor = Color.LightGray),
            modifier = Modifier
                .width(100.dp)
                .height(100.dp)
                .padding(bottom = 8.dp)
        ) {
            Image(
                painter = painterResource(id = iconResource),
                contentDescription = title,
                modifier = Modifier
                    .padding(16.dp)
                    .fillMaxSize()
            )
        }
        Text(
            text = title,
            modifier = Modifier.width(100.dp),
            style = TextStyle(fontWeight = FontWeight.Medium, fontSize = 14.sp)
        )
        Text(
            text = subtitle,
            color = Color.DarkGray,
            style = TextStyle(fontSize = 14.sp),
            modifier = Modifier.width(100.dp)
        )
    }
}



