package com.app.pbn.model

import com.app.pbn.constant.Constant
import java.util.*

data class UserModel(
    val name: String = Constant.EMPTY_STRING,
    val email: String = Constant.EMPTY_STRING,
    val password: String = Constant.EMPTY_STRING,
    val repeatPassword: String = Constant.EMPTY_STRING,
    val isAdmin: Boolean = Constant.FALSE
)