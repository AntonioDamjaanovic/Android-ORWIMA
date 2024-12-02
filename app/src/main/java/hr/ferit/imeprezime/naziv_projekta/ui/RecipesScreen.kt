package hr.ferit.imeprezime.naziv_projekta.ui

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldColors
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import hr.ferit.imeprezime.naziv_projekta.ui.theme.DarkGray
import hr.ferit.imeprezime.naziv_projekta.ui.theme.LightGray
import hr.ferit.imeprezime.naziv_projekta.ui.theme.Pink
import hr.ferit.imeprezime.naziv_projekta.ui.theme.Purple500
import hr.ferit.imeprezime.naziv_projekta.ui.theme.White
import hr.ferit.imeprezime.naziv_projekta.R
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue

@Preview(showBackground = true)
@Composable
fun RecipesScreen() {
    Column(
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxSize()
    ) {
        ScreenTitle(
            title="What would you like to cook today?",
            subtitle="Good morning, Antonio"
        )
        SearchBar(
            iconResource = R.drawable.ic_search,
            labelText = "Search..."
        )
        RecipeCategories()
        RecipeCount(
            iconResource =  R.drawable.ic_flame,
            labelText =  "7 recipes"
        )
        Row()
        {
            RecipeCard(
                iconResource = R.drawable.strawberry_pie_1,
                title = "Strawberry pie"
            )
            Spacer(Modifier.width(4.dp))
            RecipeCard(
                iconResource = R.drawable.strawberry_pie_2,
                title = "Strawberry pie 2"
            )
        }
        IconButton(
            R.drawable.ic_plus,
            "Add new recipe"
        )

    }
}


@Composable
fun ScreenTitle(
    title: String,
    subtitle: String
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 16.dp)
    ) {
        Text(
            text = subtitle,
            style = TextStyle(
                color = Purple500,
                fontSize = 12.sp,
                fontWeight = FontWeight.Light,
                fontStyle = FontStyle.Italic
            ),
            modifier = Modifier.padding(horizontal = 15.dp, vertical = 2.dp)
        )

    }
    Text(
        text = title,
        style = TextStyle(
            color = Color.Black,
            fontSize = 26.sp,
            fontWeight = FontWeight.Bold
        ),
        modifier = Modifier
            .padding(vertical = 1.dp, horizontal = 16.dp)
    )
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchBar(
    @DrawableRes iconResource: Int,
    labelText: String,
    colors: TextFieldColors = TextFieldDefaults.textFieldColors(
        containerColor = Color.Transparent,
        unfocusedPlaceholderColor = DarkGray,
        focusedTextColor = DarkGray,
        unfocusedLabelColor = DarkGray,
        focusedIndicatorColor = Color.Transparent,
        unfocusedIndicatorColor = Color.Transparent,
        disabledIndicatorColor = Color.Transparent
    )
) {
    var searchInput by remember {
        mutableStateOf("")
    }
    TextField(
        value = searchInput,
        onValueChange = { searchInput = it },
        label = {
            Text(labelText)
        },
        leadingIcon = {
            Icon(
                painter = painterResource(id = iconResource),
                contentDescription = labelText,
                tint = DarkGray,
                modifier = Modifier
                    .width(16.dp)
                    .height(16.dp)
            )
        },
        colors = colors,
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp)
    )
}

@Composable
fun TabButton(
    text: String,
    isActive: Boolean,
    onClick: () -> Unit
) {
    Button(
        shape = RoundedCornerShape(24.dp),
        elevation = null,
        colors = if (isActive) ButtonDefaults.buttonColors(
            contentColor = White,
            containerColor = Pink
        ) else
            ButtonDefaults.buttonColors(
                contentColor = DarkGray,
                containerColor = LightGray
            ),
        modifier = Modifier.fillMaxHeight(),
        onClick = { onClick() }
    ) {
        Text(text)
    }
}

@Composable
fun RecipeCategories() {
    var currentActiveButton by remember { mutableStateOf(0) }
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .padding(horizontal = 16.dp, vertical = 16.dp)
            .background(Color.Transparent)
            .fillMaxWidth()
            .height(44.dp)
    ) {
        TabButton(
            text = "All",
            isActive = currentActiveButton == 0) {
            currentActiveButton = 0
        }
        TabButton(
            text = "Breakfast",
            isActive = currentActiveButton == 1
        ){
            currentActiveButton = 1
        }
        TabButton(
            "Lunch",
            currentActiveButton == 2) {
            currentActiveButton = 2
        }
    }
}

@Composable
fun IconButton(
    @DrawableRes iconResource: Int,
    text: String
) {
    Button(
        onClick = {  },
        colors = ButtonDefaults.buttonColors(containerColor = Pink),

        ) {
        Row {
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
                ),
                modifier = Modifier.padding(vertical = 2.dp)
            )
        }
    }
}

@Composable
fun Chip(
    text: String,
    backgroundColor: Color = Color.White,
    textColor: Color = Pink,
) {
    Box(
        modifier = Modifier
            .background(
                color = backgroundColor,
                shape = RoundedCornerShape(12.dp)
            )
            .clip(RoundedCornerShape(12.dp))
            .padding(horizontal = 8.dp, vertical = 2.dp)
    ) {
        Text(
            text = text,
            color = textColor,
            fontSize = 12.sp
        )
    }
}

@Composable
fun RecipeCard(
    @DrawableRes iconResource: Int,
    title: String
) {
    Box(
        modifier = Modifier
            .width(215.dp)
            .height(326.dp)
    )
    {

        Image(
            painter = painterResource(id = iconResource),
            contentDescription = title,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxSize()
                .clip(RoundedCornerShape(24.dp))
        )

        Column (
            verticalArrangement = Arrangement.Bottom,
            modifier = Modifier
                .fillMaxSize()
                .padding(vertical = 5.dp)

        ) {
            Text(
                text = title,
                style = TextStyle(
                    color = Color.White,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold
                ),

                modifier = Modifier
                    .padding(vertical = 2.dp, horizontal = 16.dp)

            )
            Row (
                modifier = Modifier
                    .padding(vertical = 12.dp, horizontal = 16.dp)
            ) {
                Chip("30 mins")
                Spacer(Modifier.width(2.dp))
                Chip("4 Ingredients")
            }
        }
    }
}

@Composable
fun RecipeCount(
    @DrawableRes iconResource: Int,
    labelText: String,
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp)
    )
    {
        Text(
            text = labelText,
            modifier = Modifier.padding(vertical = 8.dp)
        )
        Icon(
            painter = painterResource(id = iconResource),
            contentDescription = "text",
            modifier = Modifier
                .width(12.dp)
                .height(12.dp)
        )
    }
}