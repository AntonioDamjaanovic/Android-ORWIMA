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
import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import coil3.compose.rememberAsyncImagePainter
import hr.ferit.imeprezime.naziv_projekta.data.Ingredient
import hr.ferit.imeprezime.naziv_projekta.R
import hr.ferit.imeprezime.naziv_projekta.data.Recipe
import hr.ferit.imeprezime.naziv_projekta.Routes
import hr.ferit.imeprezime.naziv_projekta.data.RecipeViewModel
import hr.ferit.imeprezime.naziv_projekta.ui.theme.LightGray
import hr.ferit.imeprezime.naziv_projekta.ui.theme.Pink
import hr.ferit.imeprezime.naziv_projekta.ui.theme.White

@Composable
fun RecipesScreen (modifier: Modifier = Modifier, viewModel : RecipeViewModel, navigation: NavController) {

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

        RecipeList(viewModel, navigation)

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
    imageResource: String,
    title: String,
    onClick: () -> Unit
) {
    Box(
        modifier = Modifier
            .padding(bottom = 16.dp)
            .height(326.dp)
            .width(215.dp)
    ) {
        Card(
            shape = RoundedCornerShape(12.dp),
            colors = CardDefaults.cardColors(containerColor = LightGray),
            modifier = Modifier
                .padding(bottom = 8.dp)
                .clickable {
                    onClick()
                }
        ) {
            Box {
                Image(
                    painter = rememberAsyncImagePainter(model =
                    imageResource),
                    contentDescription = title,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .fillMaxSize()
                )
                Column(
                    horizontalAlignment = Alignment.Start,
                    verticalArrangement = Arrangement.Bottom,
                    modifier = Modifier
                        .fillMaxHeight()
                        .padding(16.dp)
                ) {
                    Text(
                        text = title,
                        letterSpacing = 0.32.sp,
                        style = TextStyle(
                            color = White,
                            fontWeight = FontWeight.SemiBold,
                            fontSize = 16.sp
                        ),
                        modifier = Modifier
                            .fillMaxWidth()
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
}

@Composable
fun RecipeList(
    viewModel: RecipeViewModel,
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
            items(viewModel.recipesData.size) {
                RecipeCard(
                    imageResource = viewModel.recipesData[it].image,
                    title = viewModel.recipesData[it].title
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

@Composable
fun IngredientCard(
    iconResource: String,
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
                painter = rememberAsyncImagePainter(model = iconResource),
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



