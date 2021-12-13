package com.ecommercemvvmpractice.ui.login

sealed class LoginUiState {
    class ErrorState(val message: String) : LoginUiState()
}