package com.app.pbn.ui.componnent

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.app.pbn.R

@Preview
@Composable
fun Login() {
    var username by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var passwordVisibility: Boolean by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .background(colorResource(id = R.color.white)),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
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
                    top = dimensionResource(id = R.dimen.padding_16),
                    start = dimensionResource(id = R.dimen.padding_16),
                    end = dimensionResource(id = R.dimen.padding_16)
                ),
            shape = RoundedCornerShape(30),
            value = username,
            onValueChange = { username = it },
            label = { Text("Masukan username/email") }
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
            label = { Text("Enter password") },
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
        Button(
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    top = dimensionResource(id = R.dimen.padding_8),
                    start = dimensionResource(id = R.dimen.padding_16),
                    end = dimensionResource(id = R.dimen.padding_16)
                ),
            shape = RoundedCornerShape(40),
            colors = ButtonDefaults.buttonColors(
                backgroundColor = colorResource(id = R.color.colorPrimary),
                contentColor = colorResource(id = R.color.white)
            ),
            onClick = { /*TODO*/ }) {
            Text(text = "Masuk")
        }
    }
}