package learn.atharv.mealzapp.ui.meals

import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import learn.atharv.mealzapp.model.MealsRepository
import learn.atharv.mealzapp.model.response.MealResponse
import learn.atharv.mealzapp.model.response.MealsCategoriesResponse

class MealsCategoriesViewModel(private val repository: MealsRepository = MealsRepository()) :
    ViewModel() {

    //custom job
    val mealsJob = Job()


    init {
        // custom scope
//        val scope = CoroutineScope(mealsJob + Dispatchers.IO)

        viewModelScope.launch(Dispatchers.IO) {
            val meals = getMeals()
            mealsState.value = meals
        }
    }

    override fun onCleared() {
        super.onCleared()
        mealsJob.cancel()
    }
    val mealsState : MutableState<List<MealResponse>> =
        mutableStateOf(emptyList<MealResponse>())

    suspend fun getMeals(): List<MealResponse> {
        return repository.getMeals().categories
    }

}