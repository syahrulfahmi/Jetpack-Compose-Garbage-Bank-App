package com.app.pbn.ui.page.pickup

import com.app.pbn.constant.Constant

data class PickUpUiState(
    val username: String = Constant.EMPTY_STRING,
    val address: String = Constant.EMPTY_STRING,
    val weight: String = Constant.EMPTY_STRING,
    var notes: String = Constant.EMPTY_STRING
)