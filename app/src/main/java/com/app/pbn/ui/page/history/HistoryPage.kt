package com.app.pbn.ui.page.history

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ChevronLeft
import androidx.compose.material.icons.filled.Delete
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.app.pbn.R
import com.app.pbn.common.component.TextBold
import com.app.pbn.common.ext.fullTextWidth

@Preview(showBackground = true)
@Composable
fun HistoryPage() {
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
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
        ) {
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
                        text = "Rp. 50.000",
                        fontWeight = FontWeight.Bold,
                        fontSize = 24.sp
                    )
                }
            }
            TextBold(text = "Riwayat", modifier = Modifier.fullTextWidth(), fontSize = R.dimen.font_18)
            repeat(5) {
                CardRiwayat()
            }
//        LazyColumn {
//            items(5) {
//                CardRiwayat()
//            }
//        }
        }
    }
}

@Composable
fun CardRiwayat() {
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
                    text = "Nama Pengguna",
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold,
                )
                IconButton(onClick = {}) {
                    Icon(
                        imageVector = Icons.Filled.Delete,
                        contentDescription = "Back",
                        tint = Color.Black,
                    )
                }
            }
            Text(
                modifier = Modifier.fullTextWidth(),
                text = "Tanggal",
                fontSize = 14.sp
            )
            Text(
                modifier = Modifier.fullTextWidth(),
                text = "Berat : 20 Kg",
                fontSize = 14.sp,
            )
            Text(
                modifier = Modifier.fullTextWidth(),
                text = "Pendapatan: Rp. 50.000",
                fontSize = 14.sp,
            )
            Text(
                modifier = Modifier.fullTextWidth(),
                text = "Status",
                fontSize = 14.sp,
                textAlign = TextAlign.End,
                fontWeight = FontWeight.Bold,
                color = if (true) Color.Red else Color.Green
            )
        }
    }
}