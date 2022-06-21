package com.app.pbn.ui.componnent.button

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import com.app.pbn.R


@Composable()
fun ButtonPrimary(buttonText: String, modifier: Modifier, doOnClick: () -> Unit) {
    Button(
        modifier = modifier,
        shape = RoundedCornerShape(20),
        colors = ButtonDefaults.buttonColors(
            backgroundColor = colorResource(id = R.color.colorPrimary),
            contentColor = colorResource(id = R.color.white)
        ),
        onClick = { doOnClick.invoke() }) {
        Text(text = buttonText)
    }
}