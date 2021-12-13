package com.ecommercemvvmpractice.data.usecase

import androidx.lifecycle.MutableLiveData
import com.ecommercemvvmpractice.data.DataState
import com.ecommercemvvmpractice.data.repository.EcommerceRepository
import com.ecommercemvvmpractice.model.LoginResponse
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class loginUseCase @Inject constructor(private val ecommerceRepository: EcommerceRepository){


    suspend  fun loginUser( username:String, password:String ): Flow<DataState<LoginResponse>> {
        var loginRequest = HashMap<String,String>()

        loginRequest.put("username", username.toString())
        loginRequest.put("password", password.toString())
      return  ecommerceRepository.login(loginRequest)

    }






}