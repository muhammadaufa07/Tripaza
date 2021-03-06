package com.tripaza.tripaza.api.responses

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class LoginResponse(

    @field:SerializedName("status")
    val status: Boolean = false,
    
    // database id cannot be NULL
    @field:SerializedName("id")
    val id: String? = "",
    
    @field:SerializedName("message")
    val message: String = ""

):Parcelable
