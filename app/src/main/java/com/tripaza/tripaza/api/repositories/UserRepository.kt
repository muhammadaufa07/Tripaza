package com.tripaza.tripaza.api.repositories

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import com.tripaza.tripaza.api.ApiService
import com.tripaza.tripaza.api.Result
import com.tripaza.tripaza.api.postrequest.PostGetUserProfile
import com.tripaza.tripaza.api.postrequest.PostLogin
import com.tripaza.tripaza.api.postrequest.PostRegister
import com.tripaza.tripaza.api.postrequest.PutUpdateProfile
import com.tripaza.tripaza.api.responses.*

class UserRepository {
    companion object{
        private const val TAG = "UserRepository"
    }
    
    private lateinit var apiService: ApiService
    
    fun setApiService(apiService: ApiService){
        this.apiService = apiService
    }
    
    fun login(email: String, password: String): LiveData<Result<LoginResponse>> = liveData{
        emit(Result.Loading)
        try {
            Log.d(TAG, "login: executing login")
            val postLogin = PostLogin(email, password)
            val response = apiService.login(postLogin)
            emit(Result.Success(response))
        } catch (e: Exception) {
            Log.d(TAG, "login: FAILED " + e.message.toString())
            emit(Result.Error("Login Failed"))
        }
    }
    
    fun register(email: String, password: String, full_name:String, birth_date:String, phone_number:String): LiveData<Result<RegisterResponse>> = liveData{
        emit(Result.Loading)
        try {
            Log.d(TAG, "register: executing register")
            val postRegister = PostRegister(email, password, full_name, birth_date, phone_number)
            val response = apiService.register(postRegister)
            emit(Result.Success(response))
        } catch (e: Exception) {
            Log.d(TAG, "register: FAILED " + e.message.toString())
            emit(Result.Error("register Failed"))
        }
    }
    
    fun getUserProfileData(token: String): LiveData<Result<ProfileDataResponse>> = liveData{
        emit(Result.Loading)
        try {
            Log.d(TAG, "register: executing getUserProfileData")
            val postGetUserProfile = PostGetUserProfile(token)
            val response = apiService.getUserProfileData(postGetUserProfile)
            emit(Result.Success(response))
        } catch (e: Exception) {
            Log.d(TAG, "getUserProfileData: FAILED " + e.message.toString())
            emit(Result.Error("getUserProfileData Failed"))
        }
    }
    fun updateUserProfileData(
        token: Int,
        full_name: String,
        birth_date: String,
        phone_number: String,
        email: String, 
        password: String
    ): LiveData<Result<PutUpdateProfileResponse>> = liveData{
        emit(Result.Loading)
        try {
            Log.d(TAG, "register: executing getUserProfileData")
            
            val putUpdateProfile = PutUpdateProfile(token, full_name, birth_date, phone_number, email, password)
            val response = apiService.updateUserProfile(putUpdateProfile)
            emit(Result.Success(response))
        } catch (e: Exception) {
            Log.d(TAG, "getUserProfileData: FAILED " + e.message.toString())
            emit(Result.Error("getUserProfileData Failed"))
        }
    }
    
    fun getFoodList(): LiveData<Result<FoodsResponse>> = liveData{
        emit(Result.Loading)
        try {
            val response = apiService.getFoodList()
            emit(Result.Success(response))
            Log.d(TAG, "getFoodList: SUCCESS")
        }catch (e: Exception){
            emit(Result.Error("getFoodList() request error"))
            Log.d(TAG, "getFoodList: EXCEPTION ERROR: ${e.cause}")
        }
    }
}