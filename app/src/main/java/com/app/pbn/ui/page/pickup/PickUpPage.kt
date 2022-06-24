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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.app.pbn.R
import com.app.pbn.common.component.*
import com.app.pbn.common.ext.buttonModifier
import com.app.pbn.common.ext.fieldModifier
import com.app.pbn.common.ext.fullTextWidth
import com.app.pbn.constant.Constant
import com.app.pbn.model.TrashModel
import com.app.pbn.model.TrashTypeModel

@Composable
fun PickUpPage(viewModel: PickUpViewModel, doOnBack: () -> Unit, doOnSave: () -> Unit) {
    val uiState = viewModel.uiState.collectAsState()
    val trashList = viewModel.trashList.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
    ) {
        TopAppBar(
            title = {
                Text(
                    text = "Jemput Sampah",
                    color = colorResource(id = R.color.black),
                    style = MaterialTheme.typography.body1,
                    fontWeight = FontWeight.Bold,
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
        Spacer(modifier = Modifier.height(18.dp))
        TextBold(
            text = "Nama Pengguna", modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp, start = 16.dp, end = 16.dp),
            fontSize = R.dimen.font_14
        )
        BasicField(uiState.value.name, viewModel::onUserNameChange, Modifier.fieldModifier(), "Masukan nama lengkap")
        DropdownDemo(trashList) {
            viewModel.onCategoryChange(it.name)
            viewModel.onPriceChange(it.price.toString())
        }
        WeightAndPrice(uiState = uiState.value, viewModel = viewModel, trashList)
        ShowDatePicker(context = LocalContext.current, viewModel::onDatesChange)
        TextBold(
            text = "Alamat", modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp, start = 16.dp, end = 16.dp),
            fontSize = R.dimen.font_14
        )
        BasicField(uiState.value.address, viewModel::onAddressChange, Modifier.fieldModifier(), "Masukan alamat")
        TextBold(
            text = "Catatan Tambahan (Optional)", modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp, start = 16.dp, end = 16.dp),
            fontSize = R.dimen.font_14
        )
        BasicField(uiState.value.notes, viewModel::onNotesChange, Modifier.fieldModifier(), "Masukan catatan tambahan")
        Spacer(modifier = Modifier.padding(top = 16.dp))
        ButtonPrimary(buttonText = "Jemput Sampah ", modifier = Modifier.buttonModifier()) {
            doOnSave.invoke()
        }
        Spacer(modifier = Modifier.height(18.dp))

        LoadingDialog(viewModel.loading.value)

        if (viewModel.isShowDialogError.value) {
            AlertDialog(
                shape = RoundedCornerShape(10.dp),
                text = {
                    Text(
                        modifier = Modifier.fullTextWidth(),
                        text = viewModel.errorMessage.value,
                        fontSize = dimensionResource(id = R.dimen.font_18).value.sp,
                        textAlign = TextAlign.Center,
                        color = Color.Black
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
                    value = if (uiState.price == Constant.ZERO_VALUE) "Rp. ${items.value[0].price}" else "Rp. ${uiState.price}",
                    onNewValue = viewModel::onPriceChange,
                    modifier = Modifier,
                    placeholder = "Masukan berat sampah",
                    isEnabled = false
                )
            }
        }
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun DropdownDemo(items: State<ArrayList<TrashTypeModel>>, doOnItemClick: (TrashTypeModel) -> Unit) {
    if (items.value.isNotEmpty()) {
        var expanded by remember { mutableStateOf(false) }
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
                            text = items.value[selectedIndex].name,
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
                    items.value.forEachIndexed { index, item ->
                        DropdownMenuItem(
                            modifier = Modifier.fillMaxWidth(),
                            onClick = {
                                doOnItemClick.invoke(item)
                                selectedIndex = index
                                expanded = false
                            }) {
                            Text(text = item.name)
                        }
                    }
                }
            }
        }
    }
}