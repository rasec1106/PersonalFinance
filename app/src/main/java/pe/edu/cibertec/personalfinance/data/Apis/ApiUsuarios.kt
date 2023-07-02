package pe.edu.cibertec.personalfinance.data.Apis

import pe.edu.cibertec.personalfinance.data.service.UserService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiUsuarios {
    private const val API_BASE_URL = "https://personal-finance-dam.glitch.me/"

    private var retrofit: Retrofit? = null
    private var userService: UserService? = null

    private fun getRetrofit(): Retrofit {
        if (retrofit == null) {
            retrofit = Retrofit.Builder()
                .baseUrl(API_BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        }
        return retrofit as Retrofit
    }

    fun getUserInterface(): UserService {
        if (userService == null){
            userService = getRetrofit().create(UserService::class.java)
        }
        return userService as UserService
    }
}