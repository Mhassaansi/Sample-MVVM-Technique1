package com.ecommercemvvmpractice.ui.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.ecommercemvvmpractice.base.BaseFragment
import com.ecommercemvvmpractice.databinding.LoginBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginFragment : BaseFragment<LoginBinding>() {
    val loginViewModel: LoginViewModel by viewModels()
    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> LoginBinding
        get() = LoginBinding::inflate

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        setupViews()
    }


    fun setupViews() {
        context.let { ctx ->
            bi.siginBtn.setOnClickListener {
                loginViewModel.loginUser(bi?.email?.text.toString(), bi?.password?.text.toString())
            }
        }
    }


}