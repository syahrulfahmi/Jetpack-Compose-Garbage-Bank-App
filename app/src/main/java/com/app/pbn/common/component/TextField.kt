package com.app.pbn.common.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import com.app.pbn.R.string as AppText


@Composable
fun BasicField(
    value: String,
    onNewValue: (String) -> Unit,
    modifier: Modifier = Modifier,
    placeholder: String,
    isEnabled: Boolean = true,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    leadingIcon: @Composable (() -> Unit)? = null
) {
    OutlinedTextField(
        colors = TextFieldDefaults.outlinedTextFieldColors(
            unfocusedBorderColor = MaterialTheme.colors.onSurface,
            disabledBorderColor = MaterialTheme.colors.onSurface,
        ),
        singleLine = true,
        modifier = modifier,
        leadingIcon = leadingIcon,
        value = value,
        shape = RoundedCornerShape(10.dp),
        onValueChange = { onNewValue(it) },
        placeholder = { Text(placeholder) },
        enabled = isEnabled,
        keyboardOptions = keyboardOptions
    )
}

@Composable
fun NameField(value: String, onNewValue: (String) -> Unit, modifier: Modifier = Modifier) {
    OutlinedTextField(
        singleLine = true,
        modifier = modifier,
        value = value,
        shape = RoundedCornerShape(10.dp),
        onValueChange = { onNewValue(it) },
        placeholder = { Text(stringResource(AppText.name)) },
        leadingIcon = { Icon(imageVector = Icons.Default.Person, contentDescription = "nama") },
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text)
    )
}
@Composable
fun EmailField(value: String, onNewValue: (String) -> Unit, modifier: Modifier = Modifier) {
    OutlinedTextField(
        singleLine = true,
        modifier = modifier,
        value = value,
        shape = RoundedCornerShape(10.dp),
        onValueChange = { onNewValue(it) },
        placeholder = { Text(stringResource(AppText.email)) },
        leadingIcon = { Icon(imageVector = Icons.Default.Email, contentDescription = "Email") },
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email)
    )
}

@Composable
fun PasswordField(value: String, onNewValue: (String) -> Unit, modifier: Modifier = Modifier) {
    PasswordField(value, stringResource(id = AppText.pasword), modifier, onNewValue)
}

@Composable
fun RepeatPasswordField(value: String, onNewValue: (String) -> Unit, modifier: Modifier = Modifier) {
    PasswordField(value, stringResource(id = AppText.repeat_password), modifier, onNewValue)
}

@Composable
fun PasswordField(value: String, placeholder: String, modifier: Modifier, onNewValue: (String) -> Unit) {
    var isVisible by remember { mutableStateOf(false) }

    val icon = if (isVisible) {
        Icons.Filled.Visibility
    } else {
        Icons.Filled.VisibilityOff
    }

    val visualTransformation = if (isVisible) {
        VisualTransformation.None
    } else {
        PasswordVisualTransformation()
    }

    Column {
        OutlinedTextField(
            modifier = modifier,
            value = value,
            shape = RoundedCornerShape(10.dp),
            onValueChange = { onNewValue(it) },
            placeholder = { Text(text = placeholder) },
            leadingIcon = { Icon(imageVector = Icons.Default.Lock, contentDescription = "Lock") },
            trailingIcon = {
                IconButton(onClick = { isVisible = !isVisible }) {
                    Icon(imageVector = icon, contentDescription = "Visibility")
                }
            },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
            visualTransformation = visualTransformation
        )
    }
}