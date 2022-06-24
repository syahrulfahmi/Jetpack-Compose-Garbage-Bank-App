package com.app.pbn.ui.page.garbagetype

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ChevronLeft
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.app.pbn.R
import com.app.pbn.common.component.LoadingDialog
import com.app.pbn.common.component.Ticker

@Composable
fun GarbageTypePage(viewModel: GarbageTypeViewModel, doOnBackPressed: () -> Unit) {

    val uiState = viewModel.trashList.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
    ) {
        TopAppBar(
            title = {
                Text(
                    text = "Jenis Sampah",
                    color = colorResource(id = R.color.black),
                    style = MaterialTheme.typography.body1,
                    fontWeight = FontWeight.Bold,
                )
            },
            navigationIcon = {
                IconButton(onClick = {
                    doOnBackPressed.invoke()
                }) {
                    Icon(
                        imageVector = Icons.Filled.ChevronLeft,
                        contentDescription = "Back",
                        tint = Color.Black
                    )
                }
            },
            backgroundColor = Color.White,
            contentColor = Color.Gray,
            elevation = 2.dp
        )
        Spacer(modifier = Modifier.height(18.dp))
        uiState.value.forEach { item ->
            Ticker("${item.name} : ${item.price}/kg")
        }
        LoadingDialog(isShowDialog = viewModel.loading.value)
    }
}