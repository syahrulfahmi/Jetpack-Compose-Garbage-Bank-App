package com.app.pbn.common.component

import androidx.annotation.DimenRes
import androidx.annotation.StringRes
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

@Composable
fun TextBold(text: String, modifier: Modifier = Modifier, @DimenRes fontSize: Int) {
    Text(
        modifier = modifier,
        text = text,
        fontWeight = FontWeight.Bold,
        fontSize = dimensionResource(id = fontSize).value.sp
    )
}

@Composable
fun TextBold(@StringRes text: Int, modifier: Modifier = Modifier, @DimenRes fontSize: Int) {
    Text(
        modifier = modifier,
        text = stringResource(id = text),
        fontWeight = FontWeight.Bold,
        fontSize = dimensionResource(id = fontSize).value.sp
    )
}