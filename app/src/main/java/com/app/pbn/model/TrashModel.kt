package com.app.pbn.model

import com.app.pbn.constant.Constant
import java.util.*

data class TrashModel(
    val id: String = UUID.randomUUID().toString(),
    val name: String = Constant.EMPTY_STRING,
    val email: String = Constant.EMPTY_STRING,
    val weight: Int? = Constant.ZERO_VALUE,
    val price: Int = Constant.ZERO_VALUE,
    var category: String = Constant.EMPTY_STRING,
    val date: String = Constant.EMPTY_STRING,
    val address: String = Constant.EMPTY_STRING,
    val notes: String = Constant.EMPTY_STRING,
    var status: Int = Constant.ZERO_VALUE
)

data class TrashTypeModel(
    val id: String = UUID.randomUUID().toString(),
    var name: String = Constant.EMPTY_STRING,
    var price: Int = Constant.ZERO_VALUE
)

data class TrashHistory(
    val id: String = UUID.randomUUID().toString(),
    val name: String = Constant.EMPTY_STRING,
    val email: String = Constant.EMPTY_STRING,
    val weight: Int? = Constant.ZERO_VALUE,
    val price: Int = Constant.ZERO_VALUE,
    var category: String = Constant.EMPTY_STRING,
    val date: String = Constant.EMPTY_STRING,
    val acceptedBy: String = Constant.EMPTY_STRING,
    val address: String = Constant.EMPTY_STRING,
    val status: Int = Constant.ZERO_VALUE
)