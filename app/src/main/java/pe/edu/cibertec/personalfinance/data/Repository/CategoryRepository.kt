package pe.edu.cibertec.personalfinance.data.repository

import android.content.Context
import pe.edu.cibertec.personalfinance.data.local.AppDatabase
import pe.edu.cibertec.personalfinance.data.model.Category
import pe.edu.cibertec.personalfinance.data.remote.ApiClient
import pe.edu.cibertec.personalfinance.data.remote.service.CategoryService
import pe.edu.cibertec.personalfinance.util.Result
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CategoryRepository(
    private val categoryService: CategoryService = ApiClient.getCategoryService()
) {
    fun getCategories(type: Int, context: Context, callback: (Result<List<Category>>) -> Unit){
        if(type == 1)
            getCategoriesRemote(callback)
        getCategoriesLocal(context, callback)
    }
    fun createCategory(type: Int, context: Context, category: Category, callback: (Result<Category>) -> Unit){
        createCategoryRemote(category, callback)
    }
    fun updateCategory(type: Int, context: Context, category: Category, id: Int, callback: (Result<Category>) -> Unit){
        updateCategoryRemote(category, id, callback)
    }
    fun deleteCategory(type: Int, context: Context, id: Int, callback: (Result<Category>) -> Unit){
        deleteCategoryRemote(id, callback)
    }
    private fun getCategoriesRemote(callback: (Result<List<Category>>) -> Unit){
        categoryService.getCategories().enqueue(object: Callback<List<Category>> {
            override fun onResponse(call: Call<List<Category>>, response: Response<List<Category>>) {
                if(response.isSuccessful && response.body() != null){
                    callback(Result.Success(response.body()!!, "Response correctly fetched"))
                }else{
                    callback(Result.Error("Data not found"))
                }
            }

            override fun onFailure(call: Call<List<Category>>, t: Throwable) {
                callback(Result.Error("Categories not available"))
            }
        })
    }
    private fun getCategoriesLocal(context: Context, callback: (Result<List<Category>>) -> Unit){
        val categoryDao = AppDatabase.getInstance(context).categoryDao()
        callback(Result.Success(categoryDao.getAll()))
    }
    private fun createCategoryRemote(Category: Category, callback: (Result<Category>) -> Unit){
        categoryService.createCategory(Category).enqueue(object: Callback<Category> {
            override fun onResponse(call: Call<Category>, response: Response<Category>) {
                if(response.isSuccessful && response.body() != null){
                    callback(Result.Success(response.body()!!, "Category created correctly"))
                }else{
                    callback(Result.Error("Error creating category"))
                }
            }

            override fun onFailure(call: Call<Category>, t: Throwable) {
                callback(Result.Error("Create category not available"))
            }

        })
    }
    private fun updateCategoryRemote(Category: Category, id: Int, callback: (Result<Category>) -> Unit){
        categoryService.updateCategory(Category, id).enqueue(object: Callback<Category> {
            override fun onResponse(call: Call<Category>, response: Response<Category>) {
                if(response.isSuccessful && response.body() != null){
                    callback(Result.Success(response.body()!!, "Category updated correctly"))
                }else{
                    callback(Result.Error("Error updating category"))
                }
            }

            override fun onFailure(call: Call<Category>, t: Throwable) {
                callback(Result.Error("Update category not available"))
            }

        })
    }
    private fun deleteCategoryRemote(id: Int, callback: (Result<Category>) -> Unit){
        categoryService.deleteCategory(id).enqueue(object: Callback<Category> {
            override fun onResponse(call: Call<Category>, response: Response<Category>) {
                if(response.isSuccessful && response.body() != null){
                    callback(Result.Success(response.body()!!, "Category deleted correctly"))
                }else{
                    callback(Result.Error("Error deleting category"))
                }
            }

            override fun onFailure(call: Call<Category>, t: Throwable) {
                callback(Result.Error("Delete category not available"))
            }

        })
    }
}