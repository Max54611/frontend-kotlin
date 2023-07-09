package com.morimagno.grupo3a.ui.viewmodel

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.morimagno.grupo3a.util.SharedPreferenceUtil
import com.morimagno.grupo3a.data.model.User

class RegisterViewModel (private val context: Context) : ViewModel() {

    private lateinit var sharedPreferenceUtil: SharedPreferenceUtil

    val emptyFieldsError= MutableLiveData<Boolean>()
    val goSuccessActivity = MutableLiveData<Boolean>()

    init{
        sharedPreferenceUtil = SharedPreferenceUtil().also {
            it.setSharedPreference(context)
        }
    }

    fun validateInputs(userName: String, email: String, password: String) {
        if (userName.isEmpty() || email.isEmpty() || password.isEmpty()) {
            emptyFieldsError.postValue(true)
        }
        else{
            val userId = "1"
            val user = User(userId, userName, email, password)
            sharedPreferenceUtil.saveUser(user)
            goSuccessActivity.postValue(true)
        }
    }
}