package com.app.pbn.common.ext

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

fun Modifier.buttonModifier(): Modifier {
    return this
        .fillMaxWidth()
        .padding(top = 8.dp, start = 16.dp, end = 16.dp)
}

fun Modifier.fieldModifier(): Modifier {
    return this
        .fillMaxWidth()
        .padding(top = 8.dp, start = 16.dp, end = 16.dp)
}

fun Modifier.fullTextWidth(): Modifier {
    return this
        .fillMaxWidth()
        .padding(top = 8.dp, start = 16.dp, end = 16.dp)
}