package com.app.pbn.ui.page.register

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.app.pbn.common.ext.isValidEmail
import com.app.pbn.common.ext.isValidPassword
import com.app.pbn.common.ext.passwordMatches
import com.app.pbn.common.service.AccountService
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RegisterViewModel @Inject constructor(
    private val accountService: AccountService
) : ViewModel() {
    var uiState = mutableStateOf(RegisterUiState())
        private set

    private val email get() = uiState.value.email
    private val password get() = uiState.value.password
    var loading = mutableStateOf(false)
    var isShowDialogError = mutableStateOf(false)
    var errorMessage = mutableStateOf("")

    fun registerAccount(doOnSuccess: () -> Unit) {
        if (!email.isValidEmail()) {
            isShowDialogError.value = true
            errorMessage.value = "Email anda tidak valid"
            return
        }

        if (!password.isValidPassword()) {
            isShowDialogError.value = true
            errorMessage.value = "Kata sandi harus lebih dari 5 karakter"
            return
        }

        if (!password.passwordMatches(uiState.value.repeatPassword)) {
            isShowDialogError.value = true
            errorMessage.value = "Kata sandi tidak sama"
            return
        }
        loading.value = true
        viewModelScope.launch {
            accountService.signUpAccount(email, password) { error ->
                if (error == null) {
                    Log.i("zxcasd", "succes")
                    doOnSuccess.invoke()
                } else {
                    Log.i("zxcasd", "${error.message}")
                    isShowDialogError.value = true
                    errorMessage.value = error.message.toString()
                }
                loading.value = false
            }
        }
    }

    fun onEmailChange(newValue: String) {
        uiState.value = uiState.value.copy(email = newValue)
    }

    fun onPasswordChange(newValue: String) {
        uiState.value = uiState.value.copy(password = newValue)
    }

    fun onRepeatPasswordChange(newValue: String) {
        uiState.value = uiState.value.copy(repeatPassword = newValue)
    }
}