package com.app.pbn.common.component

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.app.pbn.R
import com.app.pbn.common.ext.convertToCurrencyFormat
import com.app.pbn.common.ext.fieldModifier
import com.app.pbn.constant.Constant
import com.app.pbn.model.TrashModel
import com.app.pbn.model.TrashTypeModel
import com.app.pbn.ui.page.pickup.PickUpViewModel

@Composable
fun WeightAndPrice(uiState: TrashModel, viewModel: PickUpViewModel, items: State<ArrayList<TrashTypeModel>>) {
    Row(
        modifier = Modifier.fieldModifier(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Column(modifier = Modifier.weight(1f)) {
            TextBold(
                text = "Berat Sampah (Kg)", modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 16.dp),
                fontSize = R.dimen.font_14
            )
            BasicField(
                value = if (uiState.weight == Constant.ZERO_VALUE) Constant.EMPTY_STRING else uiState.weight.toString(),
                onNewValue = viewModel::onWeightChange,
                modifier = Modifier,
                placeholder = "Masukan berat sampah",
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
            )
        }
        Spacer(modifier = Modifier.width(12.dp))
        Column(modifier = Modifier.weight(1f)) {
            TextBold(
                text = "Harga per (Kg)", modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 16.dp),
                fontSize = R.dimen.font_14
            )
            if (items.value.isNotEmpty()) {
                BasicField(
                    value = if (uiState.price == Constant.ZERO_VALUE) items.value[0].price.toString().convertToCurrencyFormat() else uiState.price.toString().convertToCurrencyFormat(),
                    onNewValue = viewModel::onPriceChange,
                    modifier = Modifier,
                    placeholder = "Masukan berat sampah",
                    isEnabled = false
                )
            }
        }
    }
}
