package com.ecommercemvvmpractice.data.repository

import com.ecommercemvvmpractice.data.DataState
import com.ecommercemvvmpractice.data.remote.*
import com.ecommercemvvmpractice.model.LoginResponse
import com.ecommercemvvmpractice.utils.StringUtils
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.io.IOException
import javax.inject.Inject

class EcommerceRepositoryImpl @Inject constructor(
    private val stringUtils: StringUtils,
    private val apiService: ApiService
) : EcommerceRepository {


    override suspend fun login(loginRequest: HashMap<String, String>): Flow<DataState<LoginResponse>> {
        return flow {
            apiService.login(loginRequest).apply {
                onSuccessSuspend {
                    data?.let {
                        emit(DataState.success(it))
                    }
                }
            }.onErrorSuspend {
                emit(DataState.error(message()))
            }.onExceptionSuspend {
                if (this.exception is IOException) {
                    emit(DataState.error(stringUtils.noNetworkErrorMessage()))
                } else {
                    emit(DataState.error(stringUtils.somethingWentWrong()))
                }
            }
        }
    }

//    override suspend fun getCategories(): Flow<DataState<>> {
//        TODO("Not yet implemented")
//    }
//
//    override suspend fun getAllProducts(): Flow<DataState<>> {
//        TODO("Not yet implemented")
//    }


}