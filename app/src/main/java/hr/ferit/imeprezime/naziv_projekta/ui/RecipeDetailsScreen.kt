package hr.ferit.imeprezime.naziv_projekta.ui

import androidx.compose.ui.graphics.Color
import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ButtonElevation
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil3.compose.rememberAsyncImagePainter
import hr.ferit.imeprezime.naziv_projekta.R
import hr.ferit.imeprezime.naziv_projekta.Routes
import hr.ferit.imeprezime.naziv_projekta.data.Recipe
import hr.ferit.imeprezime.naziv_projekta.data.RecipeViewModel
import hr.ferit.imeprezime.naziv_projekta.ui.theme.DarkGray
import hr.ferit.imeprezime.naziv_projekta.ui.theme.Gray
import hr.ferit.imeprezime.naziv_projekta.ui.theme.LightGray
import hr.ferit.imeprezime.naziv_projekta.ui.theme.Pink
import hr.ferit.imeprezime.naziv_projekta.ui.theme.Purple500
import hr.ferit.imeprezime.naziv_projekta.ui.theme.Transparent
import hr.ferit.imeprezime.naziv_projekta.ui.theme.White

@Composable
fun RecipeDetailsScreen(
    viewModel: RecipeViewModel,
    navigation: NavController,
    recipeId: Int
) {
    val scrollState = rememberLazyListState()
    val recipe = viewModel.recipesData[recipeId]
    LazyColumn(
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.Start,
        state = scrollState,
        modifier = Modifier
            .fillMaxSize()
    ) {
        item {
            TopImageAndBar(
                coverImage = recipe.image,
                viewModel = viewModel,
                navigation = navigation,
                recipe = recipe
            )
            ScreenInfo(recipe.title, recipe.category)
            BasicInfo(recipe)
            Description(recipe)
            Servings()
            IngredientsHeader()
            IngredientsList(recipe)
            ShoppingListButton()
            Reviews(recipe)
            OtherRecipes()
        }
    }
}

@Composable
fun CircularButton(
    @DrawableRes iconResource: Int,
    color: Color = Gray,
    elevation: ButtonElevation? = ButtonDefaults.buttonElevation(defaultElevation = 12.dp),
    onClick: () -> Unit = {}
) {
    Button(
        contentPadding = PaddingValues(),
        elevation = elevation,
        onClick = { onClick() },
        colors = ButtonDefaults.buttonColors(
            containerColor = White,
            contentColor = color
        ),
        shape = RoundedCornerShape(5.dp),
        modifier = Modifier
            .width(38.dp)
            .height(38.dp)
    ) {
        Icon(
            painter = painterResource(id = iconResource),
            contentDescription = null,
        )
    }
}

@Composable
fun TopImageAndBar(
    coverImage: String,
    viewModel: RecipeViewModel,
    navigation: NavController,
    recipe: Recipe
) {
    Box(
        modifier = Modifier
            .height(300.dp)
            .fillMaxWidth()
    ) {
        Image(
            painter = rememberAsyncImagePainter(model = coverImage),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxSize()
        )
        Column(
            verticalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .fillMaxHeight()
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier
                    .fillMaxWidth()
                    .statusBarsPadding()
                    .height(56.dp)
                    .padding(horizontal = 16.dp)
            ) {
                CircularButton(
                iconResource = R.drawable.ic_arrow_back,
                color = Pink
            ) {
                navigation.popBackStack(Routes.SCREEN_ALL_RECIPES,
                    false)
            }
                CircularButton(
                    iconResource = R.drawable.ic_favorite,
                    color = if(recipe.isFavorited) Pink else DarkGray
                ) {
                    recipe.isFavorited = !recipe.isFavorited
                    viewModel.updateRecipe(recipe)
                }
            }
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(
                        Brush.verticalGradient(
                            colors = listOf(
                                Color.Transparent,
                                Color.White
                            ),
                            startY = 100f
                        )
                    )
            )
        }
    }
}


@Composable
fun ScreenInfo(
    title: String,
    category: String
) {
    Column {
        Text(
            text = category,
            style = TextStyle(
                color = Purple500,
                fontSize = 15.sp,
                fontWeight = FontWeight.Light
            ),
            modifier = Modifier
                .padding(horizontal = 16.dp)
        )
        Text(
            text = title,
            style = TextStyle(
                color = Color.Black,
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold
            ),
            modifier = Modifier
                .padding(horizontal = 16.dp)
        )
    }
}

@Composable
fun InfoColumn(
    @DrawableRes iconResource: Int,
    text: String
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Icon(
            painter = painterResource(id = iconResource),
            contentDescription = null,
            tint = Pink,
            modifier = Modifier.height(24.dp)
        )
        Text(text = text, fontWeight = FontWeight.Bold)
    }
}
@Composable
fun BasicInfo(recipe: Recipe) {
    Row(
        horizontalArrangement = Arrangement.SpaceEvenly,
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 16.dp)
    ) {
        InfoColumn(R.drawable.ic_clock, recipe.cookingTime.toString())
        InfoColumn(R.drawable.ic_flame, recipe.energy.toString())
        InfoColumn(R.drawable.ic_star, recipe.rating.toString())
    }
}

@Composable
fun Description(
    recipe: Recipe
) {
    Text(
        text = recipe.description,
        fontWeight = FontWeight.Medium,
        modifier = Modifier
            .padding(horizontal = 16.dp, vertical = 20.dp)
    )
}

@Composable
fun Servings() {
    var value by remember {
        mutableStateOf(6)
    }
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .padding(horizontal = 16.dp, vertical = 0.dp)
            .clip(RoundedCornerShape(6.dp))
            .background(LightGray)
            .padding(horizontal = 16.dp)
    ) {
        Text(
            text = "Serving",
            modifier = Modifier.weight(1f),
            style = TextStyle(fontWeight = FontWeight.Medium)
        )
        CircularButton(iconResource = R.drawable.ic_minus, elevation =
        null, color = Pink) {
            value--
        }
        Text(
            text = "$value",
            modifier = Modifier
                .padding(16.dp),
            style = TextStyle(fontWeight = FontWeight.Medium)
        )
        CircularButton(iconResource = R.drawable.ic_plus, elevation =
        null, color = Pink) {
            value ++
        }
    }
}

@Composable
fun <T> EasyGrid(nColumns: Int, items: List<T>, content: @Composable (T) -> Unit) {
    Column(Modifier.padding(16.dp)) {
        for (i in items.indices step nColumns) {
            Row {
                for (j in 0 until nColumns) {
                    if (i + j < items.size) {
                        Box(
                            contentAlignment = Alignment.TopCenter,
                            modifier = Modifier.weight(1f)
                        ) {
                            content(items[i + j])
                        }
                    } else {
                        Spacer(Modifier.weight(1f, fill = true))
                    }
                }
            }
        }
    }
}

@Composable
fun IngredientsHeader() {
    var currentActiveButton by remember { mutableStateOf(0) }
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .padding(horizontal = 16.dp, vertical = 16.dp)
            .clip(RoundedCornerShape(8.dp))
            .background(LightGray)
            .fillMaxWidth()
            .height(44.dp)
    ) {
        TabButton(modifier = Modifier,"Ingredients", currentActiveButton == 0) {
            currentActiveButton = 0
        }
        TabButton(modifier = Modifier,"Tools", currentActiveButton == 1){
            currentActiveButton = 1
        }
        TabButton(modifier = Modifier,"Steps", currentActiveButton == 2) {
            currentActiveButton = 2
        }
    }
}
@Composable
fun IngredientsList(
    recipe: Recipe
) {
    EasyGrid(nColumns = 3, items = recipe.ingredients) {
        IngredientCard(it.image, it.title, it.subtitle)
    }
}

@Composable
fun ShoppingListButton() {
    Button(
        onClick = { /*TODO*/ },
        elevation = null,
        colors = ButtonDefaults.buttonColors(
            containerColor = Color.Transparent,
            contentColor = Color.Black
        ),
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        Text(text = "Add to shopping list", modifier =
        Modifier.padding(8.dp))
    }
}

@Composable
fun IconButton(
    @DrawableRes iconResource: Int,
    text: String,
    colors: ButtonColors = ButtonDefaults.buttonColors(containerColor =
    Pink),
    side: Int = 0
) {
    Button(
        onClick = { /*TODO*/ },
        colors = colors,
    ) {
        Row {
            if (side == 0) {
                Icon(
                    painter = painterResource(id = iconResource),
                    contentDescription = text
                )
                Spacer(Modifier.width(2.dp))
                Text(
                    text = text,
                    style = TextStyle(
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Light
                    )
                )
            }
            else {
                Text(
                    text = text,
                    style = TextStyle(
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Light
                    )
                )
                Spacer(Modifier.width(2.dp))
                Icon(
                    painter = painterResource(id = iconResource),
                    contentDescription = text
                )
            }
        }
    }
}

@Composable
fun Reviews(recipe: Recipe) {
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier
            .fillMaxWidth()
            .padding(PaddingValues(start = 16.dp))
    ) {
        Column {
            Text(text = "Reviews", style = TextStyle(fontSize = 16.sp,
                fontWeight = FontWeight.Bold))
            Text(text = recipe.reviews, color = DarkGray)
        }
        IconButton(
            iconResource = R.drawable.ic_arrow_right,
            text = "See all",
            colors = ButtonDefaults.buttonColors(containerColor =
            Transparent, contentColor = Pink),
            side = 1
        )
    }
}

@Composable
fun OtherRecipes() {
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        Image(
            painter = painterResource(id = R.drawable.strawberry_pie_2),
            contentDescription = "Strawberry Pie",
            modifier = Modifier
                .weight(1f)
                .clip(RoundedCornerShape(12.dp))
        )
        Spacer(modifier = Modifier.width(16.dp))
        Image(
            painter = painterResource(id = R.drawable.strawberry_pie_3),
            contentDescription = "Strawberry Pie",
            modifier = Modifier
                .weight(1f)
                .clip(RoundedCornerShape(12.dp))
        )
    }
}

@Composable
fun RecipeList(
    recipes: List<Recipe>,
    navigation: NavController
) {
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
                style = TextStyle(color = Color.DarkGray, fontSize =
                14.sp)
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
            items(recipes.size) {
                RecipeCard(
                    imageResource = recipes[it].image,
                    title = recipes[it].title
                ) {
                    navigation.navigate(
                        Routes.getRecipeDetailsPath(it)
                    )
                }
                Spacer(modifier = Modifier.width(8.dp))
            }
        }
    }
}

