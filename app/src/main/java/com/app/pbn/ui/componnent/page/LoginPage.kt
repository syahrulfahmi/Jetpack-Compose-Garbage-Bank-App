package com.app.pbn.ui.componnent.page

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.ClickableText
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.app.pbn.R
import com.app.pbn.constant.Util
import com.app.pbn.ui.componnent.button.ButtonPrimary

@Composable
fun LoginPage(doOnRegisterClick: () -> Unit, doOnLoginClick: () -> Unit = {}) {
    val context = LocalContext.current
    val focusManager = LocalFocusManager.current
    var username by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var passwordVisibility by remember { mutableStateOf(false) }

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
            text = "Masuk",
            fontSize = dimensionResource(id = R.dimen.font_20).value.sp,
            fontWeight = FontWeight.Bold
        )
        OutlinedTextField(
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    top = dimensionResource(id = R.dimen.padding_22),
                    start = dimensionResource(id = R.dimen.padding_16),
                    end = dimensionResource(id = R.dimen.padding_16)
                ),
            shape = RoundedCornerShape(30),
            value = username,
            onValueChange = { username = it },
            label = { Text("Nama Pengguna") }
        )
        OutlinedTextField(
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    top = dimensionResource(id = R.dimen.padding_16),
                    start = dimensionResource(id = R.dimen.padding_16),
                    end = dimensionResource(id = R.dimen.padding_16)
                ),
            shape = RoundedCornerShape(30),
            value = password,
            onValueChange = { password = it },
            label = { Text("Kata Sandi") },
            visualTransformation = if (passwordVisibility) VisualTransformation.None else PasswordVisualTransformation(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
            trailingIcon = {
                IconButton(onClick = {
                    passwordVisibility = !passwordVisibility
                }) {
                    Icon(
                        imageVector = if (passwordVisibility)
                            Icons.Filled.Visibility
                        else
                            Icons.Filled.VisibilityOff, ""
                    )
                }
            }
        )

        ButtonPrimary(
            "Masuk",
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    top = dimensionResource(id = R.dimen.padding_8),
                    start = dimensionResource(id = R.dimen.padding_16),
                    end = dimensionResource(id = R.dimen.padding_16)
                ),
        ) {
            focusManager.clearFocus()
            Toast.makeText(context, "Masuk", Toast.LENGTH_SHORT).show()
            doOnLoginClick.invoke()
        }
        val annotatedText = buildAnnotatedString {
            append("Belum memiliki akun ? ")
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
                append("Daftar Sekarang")
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
                ).firstOrNull()?.let { annotation ->
//                    Toast.makeText(context, annotation.item, Toast.LENGTH_SHORT).show()
                    doOnRegisterClick.invoke()
                }
            },
        )
    }
}