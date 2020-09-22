package ash.sasamkar.plobaltask.rest

import ash.sasamkar.plobaltask.model.ApiResponse
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

const val BASE_URL = "https://plobalapps.s3-ap-southeast-1.amazonaws.com/"
interface StoreApi {
    @GET("dummy-app-data.json")
    fun getStore() : Call<ApiResponse>

    companion object{
        operator fun invoke(): StoreApi{
            return Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(StoreApi::class.java)
        }
    }
}