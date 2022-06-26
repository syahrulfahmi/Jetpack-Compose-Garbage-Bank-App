package com.app.pbn.ui.page.garbagetype

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.app.pbn.R
import com.app.pbn.common.component.ButtonPrimary
import com.app.pbn.common.component.LoadingDialog
import com.app.pbn.common.component.Ticker
import com.app.pbn.common.ext.buttonModifier

@Composable
fun GarbageTypePage(isAdmin: Boolean, viewModel: GarbageTypeViewModel, doOnBackPressed: () -> Unit, doOnAddData: () -> Unit) {
    val uiState = viewModel.trashList.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
    ) {
        TopAppBar(
            title = {
                Text(
                    text = stringResource(R.string.trash_type),
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
        uiState.value.forEach { item ->
            Ticker("${item.name} : ${item.price}/kg")
        }
        if (isAdmin) {
            ButtonPrimary(buttonText = stringResource(R.string.add_trash_data), modifier = Modifier.buttonModifier()) {
                doOnAddData.invoke()
            }
        }
        LoadingDialog(isShowDialog = viewModel.loading.value)
    }
}