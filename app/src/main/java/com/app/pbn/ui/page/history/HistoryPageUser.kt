package com.app.pbn.ui.page.history

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ChevronLeft
import androidx.compose.material.icons.filled.Delete
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.app.pbn.R
import com.app.pbn.common.component.DialogConfirmButton
import com.app.pbn.common.component.TextBold
import com.app.pbn.common.ext.convertToCurrencyFormat
import com.app.pbn.common.ext.fieldModifier
import com.app.pbn.common.ext.fullTextWidth
import com.app.pbn.model.TrashHistory
import com.app.pbn.ui.theme.BankSampahPalembonTheme

@Composable
fun HistoryPageUser(viewModel: HistoryViewModel, doOnBackPressed: () -> Unit, doOnDeleted: (TrashHistory) -> Unit) {
    val uiState = viewModel.uiState.collectAsState()
    val saldoUiState = viewModel.saldoUiState.collectAsState()

    Column() {
        TopAppBar(
            title = {
                Text(
                    text = "Saldo & Riwayat",
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
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(180.dp)
                        .padding(16.dp),
                    shape = RoundedCornerShape(10.dp),
                    elevation = 4.dp
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 16.dp),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center
                    ) {
                        Text(
                            text = "Saldo Anda",
                            fontSize = 18.sp
                        )
                        Text(
                            text = saldoUiState.value,
                            fontWeight = FontWeight.Bold,
                            fontSize = 24.sp
                        )
                    }
                }
                TextBold(text = "Riwayat", modifier = Modifier.fullTextWidth(), fontSize = R.dimen.font_18)
            }

            if (uiState.value.isNotEmpty()) {
                items(uiState.value) { item ->
                    CardHistory(data = item, false) { data  ->
                        viewModel.removeHistory(data)
                        doOnDeleted.invoke(data)
                    }
                }
            } else {
                item {
                    TextBold(
                        text = "Belum ada riwayat",
                        modifier = Modifier.fullTextWidth(),
                        fontSize = R.dimen.font_20
                    )
                }
            }
        }
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
fun Preview() {
    BankSampahPalembonTheme {
        CardHistory(data = TrashHistory(), false, { _ -> })
    }
}

@Composable
fun CardHistory(data: TrashHistory, isAdmin: Boolean = false, doOnRemoveItem: (TrashHistory) -> Unit = {}) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        shape = RoundedCornerShape(10.dp),
        elevation = 4.dp
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 16.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = data.name,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                )
                if (!isAdmin) {
                    IconButton(onClick = {
                        doOnRemoveItem(data)
                    }) {
                        Icon(
                            imageVector = Icons.Filled.Delete,
                            contentDescription = "delete",
                            tint = Color.Black,
                        )
                    }
                }
            }
            Text(
                modifier = Modifier.fullTextWidth(),
                text = data.date,
                fontSize = 14.sp
            )
            Text(
                modifier = Modifier.fullTextWidth(),
                text = "Berat : ${data.weight} Kg",
                fontSize = 14.sp,
            )
            Row(
                modifier = Modifier.fieldModifier(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    modifier = Modifier.weight(1f),
                    text = data.category,
                    fontSize = 14.sp,
                )
                Text(
                    modifier = Modifier.weight(1f),
                    textAlign = TextAlign.End,
                    fontWeight = FontWeight.Bold,
                    text = "Pendapatan: ${data.weight?.times(data.price).toString().convertToCurrencyFormat()}",
                    fontSize = 14.sp,
                )
            }
            Text(
                modifier = Modifier.fullTextWidth(),
                text = when (data.status) {
                    0 -> "Masih dalam proses"
                    1 -> "Terkonfirmasi"
                    2 -> "Permintaan ditolak"
                    else -> ""
                },
                fontSize = 14.sp,
                textAlign = TextAlign.End,
                fontWeight = FontWeight.Bold,
                color = when (data.status) {
                    0 -> colorResource(id = R.color.colorSecondary)
                    1 -> Color.Green
                    else -> Color.Red
                }
            )
        }
    }
}