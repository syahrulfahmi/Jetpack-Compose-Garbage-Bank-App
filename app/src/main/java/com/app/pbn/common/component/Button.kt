package com.app.pbn.common.component

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.app.pbn.R
import com.app.pbn.common.ext.buttonModifier

@Composable
fun ButtonPrimary(buttonText: String, modifier: Modifier, doOnClick: () -> Unit) {
    Button(
        modifier = modifier,
        shape = RoundedCornerShape(20),
        colors = ButtonDefaults.buttonColors(
            backgroundColor = colorResource(id = R.color.colorPrimary),
            contentColor = colorResource(id = R.color.white)
        ),
        onClick = { doOnClick.invoke() }) {
        TextBold(text = buttonText, fontSize = R.dimen.font_16)
    }
}

@Composable
fun ButtonDanger(buttonText: String, modifier: Modifier, doOnClick: () -> Unit) {
    Button(
        modifier = modifier,
        shape = RoundedCornerShape(20),
        colors = ButtonDefaults.buttonColors(
            backgroundColor = Color.Red,
            contentColor = colorResource(id = R.color.white)
        ),
        onClick = { doOnClick.invoke() }) {
        TextBold(text = buttonText, fontSize = R.dimen.font_16)
    }
}

@Composable
fun DialogConfirmButton(@StringRes text: Int, action: () -> Unit) {
    ButtonPrimary(buttonText = stringResource(id = text), modifier = Modifier
        .buttonModifier()
        .padding(bottom = 18.dp)) {
        action.invoke()
    }
}