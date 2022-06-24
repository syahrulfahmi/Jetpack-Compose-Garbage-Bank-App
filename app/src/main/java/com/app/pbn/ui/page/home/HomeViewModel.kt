package com.app.pbn.ui.page.home

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.app.pbn.common.service.AccountService
import com.app.pbn.model.UserModel
import com.google.firebase.auth.FirebaseUser
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val accountService: AccountService
) : ViewModel() {

    var uiState = mutableStateOf(UserModel())
        private set

    fun getUserInfo() {
        viewModelScope.launch {
            accountService.getUserInfo()?.email?.let { email ->
                accountService.getUserFromFireStore(
                    email,
                    onFailure = {},
                    onSuccess = {
                        uiState.value = uiState.value.copy(email = it.email, name = it.name, password = it.password, repeatPassword = it.repeatPassword, isAdmin = it.isAdmin)
                    }
                )
            }
        }
    }

    fun logOut(doOnSuccess: () -> Unit) {
        viewModelScope.launch {
            accountService.logOut()
            doOnSuccess.invoke()
        }
    }
}