package com.app.pbn.ui.componnent.page

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.app.pbn.R

@Preview
@Composable
fun SplashScreenPage() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(colorResource(id = R.color.colorPrimary)),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Image(
            painter = painterResource(id = R.drawable.ic_trash_white),
            contentDescription = null,
            modifier = Modifier.size(120.dp)
        )
        Text(
            modifier = Modifier.padding(top = dimensionResource(id = R.dimen.padding_16)),
            text = stringResource(id = R.string.app_name),
            fontSize = dimensionResource(id = R.dimen.font_20).value.sp,
            fontWeight = FontWeight.Bold,
            color = colorResource(id = R.color.white)
        )
    }
}