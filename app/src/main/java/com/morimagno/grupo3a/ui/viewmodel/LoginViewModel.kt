package com.morimagno.grupo3a.ui.viewmodel

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.morimagno.grupo3a.util.SharedPreferenceUtil
import com.morimagno.grupo3a.data.model.User

class LoginViewModel(private val context: Context): ViewModel() {

    private lateinit var sharedPreferenceUtil: SharedPreferenceUtil

    val emptyFieldsError = MutableLiveData<Boolean>()
    val fieldsAuthenticateError = MutableLiveData<Boolean>()
    val goSuccesActivity = MutableLiveData<Boolean>()

    fun onCreate(){
        sharedPreferenceUtil = SharedPreferenceUtil().also {
            it.setSharedPreference(context)
        }
    }

    fun validateInputs(email: String, password:String){
        if(email.isEmpty() || password.isEmpty()){
            emptyFieldsError.postValue(true)
        }

        val user: User? = sharedPreferenceUtil.getUser()

        if(email == user?.email && password == user?.password){
            goSuccesActivity.postValue(true)
        }else{
            fieldsAuthenticateError.postValue(true)
        }
    }
}