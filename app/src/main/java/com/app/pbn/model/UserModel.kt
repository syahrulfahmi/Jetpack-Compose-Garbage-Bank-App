package com.app.pbn.model

import com.app.pbn.constant.Constant
import java.util.*

data class UserModel(
    val userId: String = UUID.randomUUID().toString(),
    val name: String = Constant.EMPTY_STRING,
    val password: String = Constant.EMPTY_STRING
)