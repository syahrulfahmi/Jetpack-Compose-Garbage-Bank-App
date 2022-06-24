package com.app.pbn.ui.page.splashscreen

import androidx.lifecycle.ViewModel
import com.app.pbn.common.service.AccountService
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SplashScreenViewModel @Inject constructor(
    private val accountService: AccountService
) : ViewModel() {

    fun checkUserIfLogin(doOnSuccess: (Boolean) -> Unit) {
        doOnSuccess.invoke(accountService.hasUser())
    }
}