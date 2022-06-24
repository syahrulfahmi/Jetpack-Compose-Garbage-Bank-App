package com.app.pbn.common.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Info
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.tooling.preview.Preview
import com.app.pbn.R

@Composable
fun Ticker(text: String) {
    Box(
        modifier = Modifier
            .padding(horizontal = dimensionResource(id = R.dimen.padding_16))
            .padding(top = dimensionResource(id = R.dimen.padding_16))
            .clip(RoundedCornerShape(30))
            .background(colorResource(id = R.color.colorPrimaryDark))
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(dimensionResource(id = R.dimen.padding_16)),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                imageVector = Icons.Outlined.Info,
                contentDescription = "Info",
                tint = Color.White
            )
            Text(
                modifier = Modifier.padding(start = dimensionResource(id = R.dimen.padding_8)),
                text = text,
                color = colorResource(id = R.color.white)
            )
        }
    }
}