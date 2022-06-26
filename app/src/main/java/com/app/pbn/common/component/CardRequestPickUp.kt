package com.app.pbn.common.component

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
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
fun CardRequestPickUp(data: TrashHistory, doOnUpdate: (String, Int) -> Unit) {
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
                    text = "Harga: ${data.weight?.times(data.price).toString().convertToCurrencyFormat()}",
                    fontSize = 14.sp,
                )
            }
            Row(
                modifier = Modifier.fieldModifier(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                ButtonDanger(buttonText = stringResource(R.string.reject), modifier = Modifier.weight(1f)) {
                    doOnUpdate(data.id, 2)
                }
                Spacer(modifier = Modifier.width(16.dp))
                ButtonPrimary(buttonText = stringResource(R.string.confirm), modifier = Modifier.weight(1f)) {
                    doOnUpdate(data.id, 1)
                }
            }
        }
    }
}