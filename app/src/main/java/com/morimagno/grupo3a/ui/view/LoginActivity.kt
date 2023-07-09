package com.morimagno.grupo3a.ui.view

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import com.morimagno.grupo3a.databinding.ActivityLoginBinding
import com.morimagno.grupo3a.ui.viewmodel.LoginViewModel

class LoginActivity : BaseActivity<ActivityLoginBinding>(ActivityLoginBinding::inflate) {

    private lateinit var loginViewModel: LoginViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        loginViewModel = LoginViewModel(this)

        loginViewModel.onCreate()

        loginViewModel.emptyFieldsError.observe(this){
            Toast.makeText(this,"Ingrese los datos del usuario", Toast.LENGTH_SHORT).show()
        }

        loginViewModel.goSuccesActivity.observe(this){
            startActivity(Intent(this, MainActivity::class.java))
        }

        loginViewModel.fieldsAuthenticateError.observe(this){
            Toast.makeText(this,"Error Usuario", Toast.LENGTH_SHORT).show()
        }

        binding.btnLogin.setOnClickListener {
            loginViewModel.validateInputs(
                binding.edtEmail.text.toString(),
                binding.edtPassword.text.toString()
            )
        }

        binding.btnRegister.setOnClickListener {
            startActivity(Intent(this, RegisterActivity::class.java))
        }

    }


}