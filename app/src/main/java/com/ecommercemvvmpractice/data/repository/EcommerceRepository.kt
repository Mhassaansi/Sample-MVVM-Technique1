package com.ecommercemvvmpractice.data.repository

import com.ecommercemvvmpractice.data.DataState
import com.ecommercemvvmpractice.model.LoginResponse
import kotlinx.coroutines.flow.Flow

interface EcommerceRepository {

    suspend fun login(loginRequest:HashMap<String,String>): Flow<DataState<LoginResponse>>


}