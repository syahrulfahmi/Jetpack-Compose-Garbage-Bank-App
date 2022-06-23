package com.app.pbn.ui.page.register

import com.app.pbn.constant.Constant

data class RegisterUiState(
    val email: String = Constant.EMPTY_STRING,
    val password: String = Constant.EMPTY_STRING,
    val repeatPassword: String = Constant.EMPTY_STRING
)
