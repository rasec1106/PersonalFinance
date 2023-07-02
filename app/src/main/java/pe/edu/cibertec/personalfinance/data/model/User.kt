package pe.edu.cibertec.personalfinance.data.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class User (
    val id: Int,
    val username: String,
    val password: String,
    val email: String,
    val firstName:String,
    val lastName:String,
    val phone:String
): Parcelable