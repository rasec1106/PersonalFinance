package pe.edu.cibertec.personalfinance.data.remote.service

import pe.edu.cibertec.personalfinance.data.model.Category
import retrofit2.Call
import retrofit2.http.*

interface CategoryService {
    @GET("categories")
    fun getCategories(): Call<List<Category>>
    @POST("categories")
    fun createCategory(@Body category: Category): Call<Category>
    @PUT("categories/{id}")
    fun updateCategory(@Body category: Category, @Path("id") id: Int): Call<Category>
    @DELETE("categories/{id}")
    fun deleteCategory(@Path("id") id: Int): Call<Category>
}