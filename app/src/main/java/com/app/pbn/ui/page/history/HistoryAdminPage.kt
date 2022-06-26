package com.app.pbn.ui.page.history

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ChevronLeft
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.app.pbn.R
import com.app.pbn.common.component.*
import com.app.pbn.common.ext.convertToCurrencyFormat
import com.app.pbn.common.ext.fieldModifier
import com.app.pbn.common.ext.fullTextWidth
import com.app.pbn.model.TrashHistory
import com.app.pbn.ui.theme.BankSampahPalembonTheme

@Composable
fun HistoryAdminPage(viewModel: HistoryViewModel, isAdmin: Boolean, doOnBackPressed: () -> Unit, doOnUpdate: (String, Int, TrashHistory) -> Unit) {
    val requestUiState = viewModel.requestPickUp.collectAsState()
    val uiState = viewModel.uiState.collectAsState()

    Column {
        TopAppBar(
            title = {
                Text(
                    text = stringResource(R.string.trash_order_history),
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
        LazyColumn {
            item {
                Spacer(modifier = Modifier.height(18.dp))
                TextBold(text = stringResource(R.string.request_list), modifier = Modifier.fullTextWidth(), fontSize = R.dimen.font_18)
            }

            if (requestUiState.value.isEmpty()) {
                item {
                    TextBold(
                        text = stringResource(R.string.nothing_request),
                        modifier = Modifier.fullTextWidth(),
                        fontSize = R.dimen.font_20
                    )
                }
            } else {
                items(requestUiState.value) { item ->
                    CardRequestPickUp(
                        data = item,
                        doOnUpdate = { id, status ->
                            doOnUpdate.invoke(id, status, item)
                        }
                    )
                }
            }

            item {
                TextBold(text = stringResource(R.string.history), modifier = Modifier.fullTextWidth(), fontSize = R.dimen.font_18)
            }

            if (uiState.value.isNotEmpty()) {
                items(uiState.value) { item ->
                    CardHistory(data = item, isAdmin)
                }
            } else {
                item {
                    TextBold(
                        text = stringResource(R.string.nothing_history),
                        modifier = Modifier.fullTextWidth(),
                        fontSize = R.dimen.font_20
                    )
                }
            }
        }

        LoadingDialog(viewModel.loading.value)

        if (viewModel.isShowDialog.value) {
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
                dismissButton = { DialogConfirmButton(R.string.ok) { viewModel.isShowDialog.value = false } },
                confirmButton = {
                },
                onDismissRequest = { viewModel.isShowDialog.value = false }
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPrev() {
    BankSampahPalembonTheme {
        HistoryAdminPage(viewModel = viewModel(),
            false,
            doOnUpdate = { _, _, _ -> },
            doOnBackPressed = {}
        )
    }
}
