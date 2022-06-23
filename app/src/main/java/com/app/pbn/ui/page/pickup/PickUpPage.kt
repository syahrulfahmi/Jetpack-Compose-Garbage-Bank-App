package com.app.pbn.ui.page.pickup

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.ChevronLeft
import androidx.compose.material.icons.outlined.Info
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.app.pbn.R
import com.app.pbn.common.component.BasicField
import com.app.pbn.common.component.ButtonPrimary
import com.app.pbn.common.component.ShowDatePicker
import com.app.pbn.common.component.TextBold
import com.app.pbn.common.ext.buttonModifier
import com.app.pbn.common.ext.fieldModifier

@Composable
fun PickUpPage(viewModel: PickUpViewModel? = null, doOnBack: () -> Unit) {
    val uiState by viewModel?.uiState!!

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
    ) {
        TopAppBar(
            title = {
                Text(
                    text = "Jemput Sampah",
                    color = colorResource(id = R.color.black)
                )
            },
            navigationIcon = {
                IconButton(onClick = {
                    doOnBack.invoke()
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
                    text = "Mohon isi data di bawah ini dengan benar",
                    color = colorResource(id = R.color.white)
                )
            }
        }
        Spacer(modifier = Modifier.height(32.dp))
        TextBold(
            text = "Nama Pengguna", modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp, start = 16.dp, end = 16.dp),
            fontSize = R.dimen.font_14
        )
        BasicField(uiState.username, viewModel!!::onUserNameChange, Modifier.fieldModifier(), "Masukan nama lengkap")
        WeightAndPrice(uiState = uiState, viewModel = viewModel)
        DropdownDemo()
        ShowDatePicker(context = LocalContext.current)
        TextBold(
            text = "Alamat", modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp, start = 16.dp, end = 16.dp),
            fontSize = R.dimen.font_14
        )
        BasicField(uiState.address, viewModel::onAddressChange, Modifier.fieldModifier(), "Masukan alamat")
        TextBold(
            text = "Catatan Tambahan", modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp, start = 16.dp, end = 16.dp),
            fontSize = R.dimen.font_14
        )
        BasicField(uiState.notes, viewModel::onNotesChange, Modifier.fieldModifier(), "Masukan catatan tambahan")
        Spacer(modifier = Modifier.padding(top = 16.dp))
        ButtonPrimary(buttonText = "Jemput Sampah ", modifier = Modifier.buttonModifier()) {

        }
    }
}

@Composable
fun WeightAndPrice(uiState: PickUpUiState, viewModel: PickUpViewModel) {
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
                value = uiState.weight,
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
            BasicField(
                value = "Rp. 30.000",
                onNewValue = { },
                modifier = Modifier,
                placeholder = "Masukan berat sampah",
                isEnabled = false
            )
        }
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun DropdownDemo() {
    var expanded by remember { mutableStateOf(false) }
    val items = listOf("A", "B", "C", "D", "E", "F")
    val disabledValue = "B"
    var selectedIndex by remember { mutableStateOf(0) }
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
            .wrapContentSize(Alignment.TopStart),
    ) {
        Column(
            modifier = Modifier.fillMaxWidth()
        ) {
            TextBold(
                text = "Kategori Sampah",
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 16.dp),
                fontSize = R.dimen.font_14
            )
            Card(
                shape = RoundedCornerShape(10.dp),
                modifier = Modifier.padding(top = 8.dp),
                border = BorderStroke(1.dp, MaterialTheme.colors.onSurface),
                backgroundColor = Color.White,
                onClick = {
                    expanded = true
                },
            ) {
                Row(
                    modifier = Modifier
                        .fieldModifier()
                        .padding(vertical = 16.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = items[selectedIndex],
                    )
                    Icon(
                        imageVector = Icons.Filled.ArrowDropDown,
                        contentDescription = "Back",
                        tint = Color.Black
                    )
                }
            }
            DropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false },
            ) {
                items.forEachIndexed { index, s ->
                    DropdownMenuItem(
                        modifier = Modifier.fillMaxWidth(),
                        onClick = {
                            selectedIndex = index
                            expanded = false
                        }) {
                        val disabledText = if (s == disabledValue) {
                            " (Disabled) asdqeqwewe"
                        } else {
                            ""
                        }
                        Text(text = s + disabledText)
                    }
                }
            }
        }
    }
}