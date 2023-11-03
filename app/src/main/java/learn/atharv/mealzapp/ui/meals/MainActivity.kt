package learn.atharv.mealzapp.ui.meals

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImagePainter.State.Empty.painter
import coil.compose.rememberAsyncImagePainter
import coil.compose.rememberImagePainter
import coil.request.ImageRequest
import coil.transform.CircleCropTransformation
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import learn.atharv.mealzapp.model.response.MealResponse
import learn.atharv.mealzapp.ui.theme.MealzAppTheme
import org.w3c.dom.Text

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MealzAppTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    MealsCategoriesScreen()
                }
            }
        }
    }
}

@Composable
fun MealsCategoriesScreen() {
    //Instantiating VM in the composable
    val viewModel : MealsCategoriesViewModel = viewModel()

    val meals = viewModel.mealsState.value
//    val rememberedMeals: MutableState<List<MealResponse>> = remember { mutableStateOf(emptyList<MealResponse>()) }

//    val coroutineScope = rememberCoroutineScope()

    // launched effect for the coroutine to be constrained with specified key
//    LaunchedEffect(key1 = "GET_MEALS"){
//        coroutineScope.launch(Dispatchers.IO){
//            val meals = viewModel.getMeals()
//            rememberedMeals.value = meals
//        }
//    }

    LazyColumn {
        items(meals) { meal ->
            Row (verticalAlignment = Alignment.CenterVertically) {
                Image(
                    painter = rememberImagePainter(meal.imageUrl),
//                    painter = rememberAsyncImagePainter(
//                        ImageRequest.Builder(LocalContext.current).data(meal.imageUrl).apply(block = fun ImageRequest.Builder.() {
//                        }).build()
//                    ),
                            modifier = Modifier . size (100.dp),
                    contentDescription = "Profile picture description",
                )
                Text(text  = meal.name , fontStyle = FontStyle.Italic , fontSize = 24.sp , color = if(meal.name == "Chicken") Color.Red else Color.Black)
                Text(text = meal.id)
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun MealsCategoriesScreenPreview() {
    MealzAppTheme {
        MealsCategoriesScreen()
    }
}