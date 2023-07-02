package pe.edu.cibertec.personalfinance.data.remote.service

import pe.edu.cibertec.personalfinance.data.model.Entry
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface EntryService {
    @GET("entries")
    fun getEntries(): Call<List<Entry>>
    @POST("entries")
    fun createEntry(@Body entry: Entry): Call<Entry>
}