package com.ecommercemvvmpractice.data.remote

import com.ecommercemvvmpractice.model.LoginResponse
import retrofit2.http.Body
import retrofit2.http.POST

interface ApiService {
@POST
suspend fun login(
@Body loginRequest:HashMap<String,String>
):ApiResponse<LoginResponse>

    companion object{
       val BASE_API_URL:String?="https://fakestoreapi.com/"
    }
}