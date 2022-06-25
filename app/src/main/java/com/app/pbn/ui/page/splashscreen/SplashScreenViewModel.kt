package com.app.pbn.ui.page.splashscreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.app.pbn.common.service.AccountService
import com.app.pbn.model.UserModel
import com.google.firebase.auth.FirebaseUser
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SplashScreenViewModel @Inject constructor(
    private val accountService: AccountService
) : ViewModel() {
    private val _userData = MutableStateFlow(UserModel())
    val uiState = _userData.asStateFlow()

    fun checkUserIfLogin(doOnSuccess: (Boolean) -> Unit) {
        doOnSuccess.invoke(accountService.hasUser())
    }

    fun getUserInfo() {
        viewModelScope.launch {
            accountService.getUserInfo()?.email?.let {
                accountService.getUserFromFireStore(
                    userEmail = it,
                    onFailure = {},
                    onSuccess = {
                        _userData.value = uiState.value.copy(email = it.email, name = it.name, password = it.password, repeatPassword = it.repeatPassword, isAdmin = it.isAdmin)
                    }
                )
            }
        }
    }
}