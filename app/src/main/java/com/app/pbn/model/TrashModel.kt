package com.app.pbn.model

import com.app.pbn.constant.Constant
import java.util.*

data class TrashModel(
    val id: String = UUID.randomUUID().toString(),
    val name: String = Constant.EMPTY_STRING,
    val email: String = Constant.EMPTY_STRING,
    val weight: Int? = Constant.ZERO_VALUE,
    val price: String = Constant.EMPTY_STRING,
    var category: String = Constant.EMPTY_STRING,
    val date: String = Constant.EMPTY_STRING,
    val address: String = Constant.EMPTY_STRING,
    val notes: String = Constant.EMPTY_STRING
)

data class TrashTypeModel(
    val id: Int = Constant.ZERO_VALUE,
    var name: String = Constant.EMPTY_STRING,
    var price: Int = Constant.ZERO_VALUE
)