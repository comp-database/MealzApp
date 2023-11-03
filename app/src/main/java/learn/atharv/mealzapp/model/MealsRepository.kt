package learn.atharv.mealzapp.model

import learn.atharv.mealzapp.model.api.MealsWebService
import learn.atharv.mealzapp.model.response.MealsCategoriesResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MealsRepository(private val webService : MealsWebService = MealsWebService()) {

    suspend fun getMeals() : MealsCategoriesResponse {
        return webService.getMeals()
    }
}