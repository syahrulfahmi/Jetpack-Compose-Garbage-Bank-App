package com.app.pbn.ui.page.register

import com.app.pbn.constant.Constant
import java.util.*

data class RegisterUiState(
    val userId: String = UUID.randomUUID().toString(),
    val name: String = Constant.EMPTY_STRING,
    val email: String = Constant.EMPTY_STRING,
    val password: String = Constant.EMPTY_STRING,
    val repeatPassword: String = Constant.EMPTY_STRING
)
