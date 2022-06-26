package com.app.pbn.common.component

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.app.pbn.R
import com.app.pbn.common.ext.convertToCurrencyFormat
import com.app.pbn.common.ext.fieldModifier
import com.app.pbn.common.ext.fullTextWidth
import com.app.pbn.model.TrashHistory


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