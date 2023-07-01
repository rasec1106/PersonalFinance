package pe.edu.cibertec.personalfinance.data.remote.service

import pe.edu.cibertec.personalfinance.data.model.Entry
import retrofit2.Call
import retrofit2.http.GET

interface EntryService {
    @GET("entries")
    fun getEntries(): Call<List<Entry>>
}