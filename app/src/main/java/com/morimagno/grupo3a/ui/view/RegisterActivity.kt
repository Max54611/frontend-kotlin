package com.morimagno.grupo3a.ui.view

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import com.morimagno.grupo3a.databinding.ActivityRegisterBinding
import com.morimagno.grupo3a.ui.viewmodel.RegisterViewModel

class RegisterActivity : BaseActivity<ActivityRegisterBinding>(ActivityRegisterBinding::inflate) {
    private lateinit var registerViewModel: RegisterViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        registerViewModel = RegisterViewModel(this)

        registerViewModel.emptyFieldsError.observe(this, {
            Toast.makeText(this, "Ingrese todos los datos de registro", Toast.LENGTH_SHORT).show()
        })

        registerViewModel.goSuccessActivity.observe(this, {
            startActivity(Intent(this, LoginActivity::class.java))
        })

        binding.btnRegister.setOnClickListener {
            registerViewModel.validateInputs(
                binding.edtUserName.text.toString(),
                binding.edtEmail.text.toString(),
                binding.edtPassword.text.toString()
            )
        }
        binding.btnGoLogin.setOnClickListener {
            startActivity(Intent(this, LoginActivity::class.java))
        }
    }
}