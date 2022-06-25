package com.app.pbn.ui.page.login

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.app.pbn.common.ext.isValidEmail
import com.app.pbn.common.ext.isValidPassword
import com.app.pbn.common.service.AccountService
import com.app.pbn.model.UserModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val accountService: AccountService
) : ViewModel() {
    var uiState = mutableStateOf(LoginUiState())
        private set

    private val email get() = uiState.value.email
    private val password get() = uiState.value.password
    var loading = mutableStateOf(false)
    var isShowDialogError = mutableStateOf(false)
    var errorMessage = mutableStateOf("")

    fun loginAccount(doOnSuccess: (Boolean) -> Unit) {
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
        loading.value = true
        viewModelScope.launch {
            accountService.signInAccount(email, password) { error ->
                if (error == null) {
                    getUserInfo(doOnSuccess::invoke)
                } else {
                    isShowDialogError.value = true
                    errorMessage.value = error.message.toString()
                }
                loading.value = false
            }
        }
    }

    private fun getUserInfo(doOnSuccess: (Boolean) -> Unit) {
        viewModelScope.launch {
            accountService.getUserInfo()?.email?.let { email ->
                accountService.getUserFromFireStore(
                    email,
                    onFailure = {},
                    onSuccess = {
                        doOnSuccess.invoke(it.isAdmin)
                    }
                )
            }
        }
    }

    fun onEmailChange(newValue: String) {
        uiState.value = uiState.value.copy(email = newValue)
    }

    fun onPasswordChange(newValue: String) {
        uiState.value = uiState.value.copy(password = newValue)
    }
}