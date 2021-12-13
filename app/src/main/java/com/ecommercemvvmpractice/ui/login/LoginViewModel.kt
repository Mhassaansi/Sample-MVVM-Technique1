package com.ecommercemvvmpractice.ui.login

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ecommercemvvmpractice.data.DataState
import com.ecommercemvvmpractice.data.usecase.loginUseCase
import com.ecommercemvvmpractice.model.LoginResponse
import com.ecommercemvvmpractice.utils.UserPreferences
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
 class LoginViewModel @Inject constructor(
    val loginUseCase: loginUseCase
    //,
    //private val userPreferences: UserPreferences
) : ViewModel() {

 /*   val username = MutableLiveData<String>("")
    val password = MutableLiveData<String>("")*/
    private var mutableLoginData = MutableLiveData<LoginResponse>()

    private var _loginUiState = MutableLiveData<LoginUiState>()
    var uiStateLiveData: LiveData<LoginUiState> = _loginUiState

    private var _photosList = MutableLiveData<List<LoginResponse>>()
    var photosListLiveData: LiveData<List<LoginResponse>> = _photosList




    fun loginUser(email:String, password:String) {
        viewModelScope.launch {
            loginUseCase.loginUser(email, password).collect { datasate ->
                when (datasate) {
                    is DataState.Success -> {
                        // mutableLoginData.value.apply {
                        // saveLoginData(datasate.data.token)
                        //}
                        saveLoginData(mutableLoginData.value!!.token)
                        mutableLoginData.postValue(datasate.data!!)
                        Log.d("Token->->->", datasate.data.toString())
                    }
                    is DataState.Error -> {
                        Log.d("Error", datasate.message)
                        // mutableLoginData.postValue(datasate.message)
                        _loginUiState.postValue(LoginUiState.ErrorState(datasate.message))
                    }

                }

            }
        }
    }


    fun saveLoginData(loginResponse: String) {
        viewModelScope.launch {
         //   userPreferences.saveUserData(loginResponse)
        }
    }


}