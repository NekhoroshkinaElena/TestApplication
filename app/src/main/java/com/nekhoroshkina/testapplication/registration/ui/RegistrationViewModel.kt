package com.nekhoroshkina.testapplication.registration.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nekhoroshkina.testapplication.registration.domain.RegistrationInteractor
import com.nekhoroshkina.testapplication.registration.domain.model.RegistrationResult
import com.nekhoroshkina.testapplication.registration.domain.model.User
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RegistrationViewModel @Inject constructor(
    private val registrationInteractor: RegistrationInteractor
) : ViewModel() {

    private val _registrationScreenState = MutableLiveData<RegistrationScreenState>()
    val registrationScreenState: LiveData<RegistrationScreenState> = _registrationScreenState

    fun registerUser(name: String, email: String, password: String, confirmPassword: String) {
        val user = User(name, email, password, confirmPassword)
        viewModelScope.launch {
            val userRegistration =
                registrationInteractor.registerUser(user = user)
            if (userRegistration is RegistrationResult.Error) {
                _registrationScreenState.postValue(RegistrationScreenState.Error(userRegistration.message))
            } else {
                _registrationScreenState.postValue(RegistrationScreenState.Success)
            }
        }
    }
}
