package com.app.pbn.ui.page.pickup

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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.app.pbn.R
import com.app.pbn.common.component.*
import com.app.pbn.common.ext.buttonModifier
import com.app.pbn.common.ext.fieldModifier
import com.app.pbn.common.ext.fullTextWidth

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
                    text = stringResource(id = R.string.pickup_trash),
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
                    text = stringResource(id = R.string.please_input_data_corectly),
                    color = colorResource(id = R.color.white)
                )
            }
        }
        Spacer(modifier = Modifier.height(18.dp))

        // FIELD INPUT USER NAME
        TextBold(
            text = stringResource(R.string.user_name), modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp, start = 16.dp, end = 16.dp),
            fontSize = R.dimen.font_14
        )
        BasicField(uiState.value.name, viewModel::onUserNameChange, Modifier.fieldModifier(), stringResource(R.string.placeholder_input_user_name))

        // FIELD INPUT CATEGORY
        DropdownDemo(trashList) {
            viewModel.onCategoryChange(it.name)
            viewModel.onPriceChange(it.price.toString())
        }

        // FIELD INPUT BERAT DAN HARGA
        WeightAndPrice(uiState = uiState.value, viewModel = viewModel, trashList)

        // FIELD INPUT TANGGAL
        ShowDatePicker(context = LocalContext.current, viewModel::onDatesChange)

        // FIELD INPUT ALAMAT
        TextBold(
            text = stringResource(R.string.address), modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp, start = 16.dp, end = 16.dp),
            fontSize = R.dimen.font_14
        )
        BasicField(uiState.value.address, viewModel::onAddressChange, Modifier.fieldModifier(), stringResource(R.string.placeholder_input_address))


        // FIELD INPUT CATATAN
        TextBold(
            text = stringResource(R.string.notes), modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp, start = 16.dp, end = 16.dp),
            fontSize = R.dimen.font_14
        )
        BasicField(uiState.value.notes, viewModel::onNotesChange, Modifier.fieldModifier(), stringResource(R.string.placeholder_input_notes))
        Spacer(modifier = Modifier.padding(top = 16.dp))

        // BUTTON SAVE
        ButtonPrimary(buttonText = stringResource(id = R.string.pickup_trash), modifier = Modifier.buttonModifier()) {
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
