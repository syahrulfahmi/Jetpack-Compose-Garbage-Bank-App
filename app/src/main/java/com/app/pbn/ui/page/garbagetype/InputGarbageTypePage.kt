package com.app.pbn.ui.page.garbagetype

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ChevronLeft
import androidx.compose.material.icons.outlined.Info
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.app.pbn.R
import com.app.pbn.common.component.BasicField
import com.app.pbn.common.component.ButtonPrimary
import com.app.pbn.common.component.DialogConfirmButton
import com.app.pbn.common.component.LoadingDialog
import com.app.pbn.common.ext.buttonModifier
import com.app.pbn.common.ext.fieldModifier
import com.app.pbn.common.ext.fullTextWidth
import com.app.pbn.constant.Constant

@Composable
fun InputGarbageTypePage(viewModel: GarbageTypeViewModel, doOnInputClick: () -> Unit, doOnBackPressed: () -> Unit = {}) {
    val uiState = viewModel.trashType.collectAsState()
    val focusManager = LocalFocusManager.current

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
    ) {
        TopAppBar(
            title = {
                Text(
                    text = stringResource(R.string.pickup_trash),
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
        Box(
            modifier = Modifier
                .padding(horizontal = dimensionResource(id = R.dimen.padding_16))
                .padding(top = 32.dp)
                .clip(RoundedCornerShape(30))
                .background(colorResource(id = R.color.colorPrimary))
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(dimensionResource(id = R.dimen.padding_16)),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    imageVector = Icons.Outlined.Info,
                    contentDescription = "Info",
                    tint = Color.White
                )
                Text(
                    modifier = Modifier.padding(start = dimensionResource(id = R.dimen.padding_8)),
                    text = stringResource(R.string.please_input_data_corectly),
                    color = colorResource(id = R.color.white)
                )
            }
        }

        Spacer(modifier = Modifier.height(18.dp))
        BasicField(value = uiState.value.name, onNewValue = viewModel::onNameChange, modifier = Modifier.fieldModifier(), placeholder = "Masukan jenis sampah")
        BasicField(
            value = if (uiState.value.price == Constant.ZERO_VALUE) Constant.EMPTY_STRING else uiState.value.price.toString(),
            modifier = Modifier.fieldModifier(),
            onNewValue = viewModel::onPriceChange,
            placeholder = stringResource(R.string.placeholder_trash_per_kg)
        )

        ButtonPrimary("Tambah", modifier = Modifier.buttonModifier()) {
            focusManager.clearFocus()
            doOnInputClick.invoke()
        }
        LoadingDialog(viewModel.loading.value)

        if (viewModel.isShowDialogError.value) {
            AlertDialog(
                shape = RoundedCornerShape(10.dp),
                text = {
                    Text(
                        modifier = Modifier.fullTextWidth(),
                        text = viewModel.errorMessage.value,
                        fontSize = dimensionResource(id = R.dimen.font_18).value.sp,
                        textAlign = TextAlign.Center
                    )
                },
                dismissButton = { DialogConfirmButton(R.string.ok) { viewModel.isShowDialogError.value = false } },
                confirmButton = {
                },
                onDismissRequest = { viewModel.isShowDialogError.value = false }
            )
        }
    }
}