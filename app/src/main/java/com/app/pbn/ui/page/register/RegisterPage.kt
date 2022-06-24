package com.app.pbn.ui.page.register

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.ClickableText
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.AlertDialog
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Person
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.app.pbn.R
import com.app.pbn.common.component.*
import com.app.pbn.common.ext.buttonModifier
import com.app.pbn.common.ext.fieldModifier
import com.app.pbn.common.ext.fullTextWidth
import com.app.pbn.constant.Util

@Composable
fun RegisterPage(viewModel: RegisterViewModel, doOnLoginClick: () -> Unit, doOnRegisterClick: () -> Unit) {
    val focusManager = LocalFocusManager.current
    val uiState by viewModel.uiState
    val fieldModifier = Modifier.fieldModifier()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .background(colorResource(id = R.color.white)),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Row(modifier = Modifier.height(100.dp)) { }
        Image(
            painter = painterResource(
                id = R.drawable.ic_trash
            ),
            contentDescription = null,
            modifier = Modifier.size(120.dp)
        )
        Text(
            modifier = Modifier.padding(top = dimensionResource(id = R.dimen.padding_16)),
            text = stringResource(R.string.daftar),
            fontSize = dimensionResource(id = R.dimen.font_20).value.sp,
            fontWeight = FontWeight.Bold
        )

        NameField(uiState.name, viewModel::onNameChange, fieldModifier)
        EmailField(uiState.email, viewModel::onEmailChange, fieldModifier)
        PasswordField(uiState.password, viewModel::onPasswordChange, fieldModifier)
        RepeatPasswordField(uiState.repeatPassword, viewModel::onRepeatPasswordChange, fieldModifier)

        ButtonPrimary(stringResource(id = R.string.daftar), modifier = Modifier.buttonModifier()) {
            focusManager.clearFocus()
            doOnRegisterClick.invoke()
        }

        val annotatedText = buildAnnotatedString {
            append(stringResource(R.string.sudah_memiliki_akun))
            pushStringAnnotation(
                tag = Util.PAGE,
                annotation = "https://developer.android.com"
            )
            withStyle(
                style = SpanStyle(
                    color = colorResource(id = R.color.colorPrimary),
                    fontWeight = FontWeight.Bold
                )
            ) {
                append(stringResource(id = R.string.masuk))
            }
            pop()
        }
        ClickableText(
            modifier = Modifier
                .padding(
                    top = dimensionResource(id = R.dimen.padding_24),
                    start = dimensionResource(id = R.dimen.padding_16),
                    end = dimensionResource(id = R.dimen.padding_16)
                ),
            text = annotatedText,
            onClick = { offset ->
                annotatedText.getStringAnnotations(
                    tag = Util.PAGE,
                    start = offset,
                    end = offset
                ).firstOrNull()?.let { _ ->
                    doOnLoginClick.invoke()
                }
            },
        )
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