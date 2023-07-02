package pe.edu.cibertec.personalfinance.data.remote

import pe.edu.cibertec.personalfinance.data.remote.service.CategoryService
import pe.edu.cibertec.personalfinance.data.remote.service.EntryService
import pe.edu.cibertec.personalfinance.data.remote.service.UserService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiClient {
    const val API_BASE_URL = "https://appfinanzas.glitch.me/"

    private var retrofit: Retrofit? = null
    private var entryService: EntryService? = null
    private var userService: UserService? = null
    private var categoryService: CategoryService? = null

    private fun getRetrofit(): Retrofit {
        if(retrofit == null){
            retrofit = Retrofit.Builder()
                .baseUrl(API_BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        }
        return retrofit as Retrofit
    }
    fun getEntryService(): EntryService {
        if(entryService == null){
            entryService = getRetrofit().create(EntryService:: class.java)
        }
        return entryService as EntryService
    }
    fun getUserService(): UserService {
        if (userService == null){
            userService = getRetrofit().create(UserService::class.java)
        }
        return userService as UserService
    }
    fun getCategoryService(): CategoryService {
        if (categoryService == null){
            categoryService = getRetrofit().create(CategoryService::class.java)
        }
        return categoryService as CategoryService
    }
}