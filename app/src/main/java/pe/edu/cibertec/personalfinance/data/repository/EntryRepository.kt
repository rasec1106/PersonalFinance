package pe.edu.cibertec.personalfinance.data.repository

import android.content.Context
import android.util.Log
import pe.edu.cibertec.personalfinance.data.local.AppDatabase
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
            getEntriesRemote(callback)
        getEntriesLocal(context, callback)
    }
    fun createEntry(type: Int, context: Context, entry: Entry, callback: (Result<Entry>) -> Unit){
        createEntryRemote(entry, callback)
    }
    fun updateEntry(type: Int, context: Context, entry: Entry, id: Int, callback: (Result<Entry>) -> Unit){
        updateEntryRemote(entry, id, callback)
    }
    fun deleteEntry(type: Int, context: Context, id: Int, callback: (Result<Entry>) -> Unit){
        deleteEntryRemote(id, callback)
    }
    private fun getEntriesRemote(callback: (Result<List<Entry>>) -> Unit){
        entryService.getEntries().enqueue(object: Callback<List<Entry>> {
            override fun onResponse(call: Call<List<Entry>>, response: Response<List<Entry>>) {
                if(response.isSuccessful && response.body() != null){
                    callback(Result.Success(response.body()!!, "Response correctly fetched"))
                }else{
                    callback(Result.Error("Data not found"))
                }
            }

            override fun onFailure(call: Call<List<Entry>>, t: Throwable) {
                callback(Result.Error("Entries not available"))
            }
        })
    }
    private fun getEntriesLocal(context: Context ,callback: (Result<List<Entry>>) -> Unit){
        val entryDao = AppDatabase.getInstance(context).entryDao()
        callback(Result.Success(entryDao.getAll()))
    }
    private fun createEntryRemote(entry: Entry, callback: (Result<Entry>) -> Unit){
        entryService.createEntry(entry).enqueue(object: Callback<Entry>{
            override fun onResponse(call: Call<Entry>, response: Response<Entry>) {
                if(response.isSuccessful && response.body() != null){
                    callback(Result.Success(response.body()!!, "Entry created correctly"))
                }else{
                    callback(Result.Error("Error creating entry"))
                }
            }

            override fun onFailure(call: Call<Entry>, t: Throwable) {
                callback(Result.Error("Create entry not available"))
            }

        })
    }
    private fun updateEntryRemote(entry: Entry, id: Int, callback: (Result<Entry>) -> Unit){
        entryService.updateEntry(entry, id).enqueue(object: Callback<Entry>{
            override fun onResponse(call: Call<Entry>, response: Response<Entry>) {
                if(response.isSuccessful && response.body() != null){
                    callback(Result.Success(response.body()!!, "Entry updated correctly"))
                }else{
                    callback(Result.Error("Error updating entry"))
                }
            }

            override fun onFailure(call: Call<Entry>, t: Throwable) {
                callback(Result.Error("Update entry not available"))
            }

        })
    }
    private fun deleteEntryRemote(id: Int, callback: (Result<Entry>) -> Unit){
        entryService.deleteEntry(id).enqueue(object: Callback<Entry>{
            override fun onResponse(call: Call<Entry>, response: Response<Entry>) {
                if(response.isSuccessful && response.body() != null){
                    callback(Result.Success(response.body()!!, "Entry deleted correctly"))
                }else{
                    callback(Result.Error("Error deleting entry"))
                }
            }

            override fun onFailure(call: Call<Entry>, t: Throwable) {
                callback(Result.Error("Delete entry not available"))
            }

        })
    }
    private fun insertLocalMockData(context: Context){
        val dao = AppDatabase.getInstance(context).entryDao()
        dao.insertAll(Entry.populateWithMockData())
    }
}