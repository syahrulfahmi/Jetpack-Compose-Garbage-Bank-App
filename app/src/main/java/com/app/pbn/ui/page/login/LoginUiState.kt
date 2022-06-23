package com.app.pbn.ui.page.login

import com.app.pbn.constant.Constant

data class LoginUiState(
    val email: String = Constant.EMPTY_STRING,
    val password: String = Constant.EMPTY_STRING,
)