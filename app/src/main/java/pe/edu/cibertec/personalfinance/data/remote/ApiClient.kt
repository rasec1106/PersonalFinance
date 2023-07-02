package pe.edu.cibertec.personalfinance.data.remote

import pe.edu.cibertec.personalfinance.data.remote.service.EntryService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiClient {
    const val API_BASE_URL = "https://personal-finance-dam.glitch.me/"

    private var retrofit: Retrofit? = null
    private var entryService: EntryService? = null

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
}