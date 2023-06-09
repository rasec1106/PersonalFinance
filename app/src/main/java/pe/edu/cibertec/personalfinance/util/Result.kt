package pe.edu.cibertec.personalfinance.util

sealed class Result<T>(val data: T? = null, val message: String? = null){
    class Success<T>(data: T, message: String = ""): Result<T>(data= data, message = message)
    class Error<T>(message: String, data: T? = null): Result<T>(data = data, message = message)
}
