package learn.atharv.mealzapp.model.api

import learn.atharv.mealzapp.model.response.MealsCategoriesResponse
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET


//webservice class for retrofit instance
// calling the interface in this class for getting the particular function
class MealsWebService{
    private lateinit var api : MealsApi
    init {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://www.themealdb.com/api/json/v1/1/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        api = retrofit.create(MealsApi::class.java)
    }
    suspend fun getMeals() : MealsCategoriesResponse{
        return api.getMeals()
    }

    // interface for the working getting response
    interface MealsApi{
        @GET("categories.php")
        suspend fun getMeals() : MealsCategoriesResponse
    }
}