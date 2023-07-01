package pe.edu.cibertec.personalfinance.data.repository

import android.content.Context
import pe.edu.cibertec.personalfinance.data.local.AppDatabase
import pe.edu.cibertec.personalfinance.data.model.Category
import pe.edu.cibertec.personalfinance.data.model.Entry
import pe.edu.cibertec.personalfinance.data.remote.ApiClient
import pe.edu.cibertec.personalfinance.data.remote.service.EntryService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import pe.edu.cibertec.personalfinance.util.Result

class EntryRepository (
    private val entryService: EntryService = ApiClient.getEntryService()
){
    fun getEntries(type: Int, context: Context, callback: (Result<List<Entry>>) -> Unit){
        if(type == 1)
            return getEntriesRemote(callback)
        return getEntriesLocal(context, callback)
    }
    private fun getEntriesRemote(callback: (Result<List<Entry>>) -> Unit){
        entryService.getEntries().enqueue(object: Callback<List<Entry>> {
            override fun onResponse(call: Call<List<Entry>>, response: Response<List<Entry>>) {
                if(response.isSuccessful && response.body() != null){
                    callback(Result.Success(response.body()!!))
                }else{
                    callback(Result.Error("Data not found"))
                }
            }

            override fun onFailure(call: Call<List<Entry>>, t: Throwable) {
                callback(Result.Error("Albums not available"))
            }
        })
    }
    private fun getEntriesLocal(context: Context ,callback: (Result<List<Entry>>) -> Unit){
        val insertAll = listOf(
            Entry(1, 50.5, Category(1,"Comida","FOOD","#ff0000"),"2023-06-01", "Norky's", 0),
            Entry(2, 10.5,
                Category(2,"Salud","HEALTH","#00ff00"),"2023-06-01", "Medico a domicilio", 0),
            Entry(3, 20.5,
                Category(3,"Casa","HOME","#0000ff"),"2023-06-08", "Pasta dental y cepillo", 0),
            Entry(4, 150.0, Category(4,"Ahorros","SAVING","#ff00ff"),"2023-06-09", "Ahorro viaje", 1),
            Entry(5, 15.0,
                Category(5,"Transporte","TRANSPORT","#ffff00"),"2023-06-11", "Pasajes universidad", 0),
            Entry(6, 350.80,
                Category(6,"Viajes","TRAVEL","#00ffff"),"2023-06-12", "Viaje a Trujillo", 0),
            Entry(7, 20.0, Category(1,"Comida","FOOD","#ff0000"),"2023-06-13", "Salchipapa", 0),
            Entry(8, 38.0, Category(7,"Ocio","LEISURE","#f0f0f0"),"2023-06-15", "Cineplanet", 0)
        )
        val dao = AppDatabase.getInstance(context).daoEntry()
        dao.insertAll(insertAll)
        val entryDao = AppDatabase.getInstance(context).daoEntry()
        callback(Result.Success(entryDao.getAll()))
    }
}