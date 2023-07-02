package pe.edu.cibertec.personalfinance.data.remote.service

import androidx.room.Update
import pe.edu.cibertec.personalfinance.data.model.Entry
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

interface EntryService {
    @GET("entries")
    fun getEntries(): Call<List<Entry>>
    @POST("entries")
    fun createEntry(@Body entry: Entry): Call<Entry>
    @PUT("entries/{id}")
    fun updateEntry(@Body entry: Entry, @Path("id") id: Int): Call<Entry>
    @DELETE("entries/{id}")
    fun deleteEntry(@Path("id") id: Int): Call<Entry>
}