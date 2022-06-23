package com.app.pbn.ui.page.pickup

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class PickUpViewModel @Inject constructor() : ViewModel() {
    var uiState = mutableStateOf(PickUpUiState())
        private set

    fun onUserNameChange(newValue: String) {
        uiState.value = uiState.value.copy(username = newValue)
    }

    fun onWeightChange(newValue: String) {
        uiState.value = uiState.value.copy(weight = newValue)
    }

    fun onAddressChange(newValue: String) {
        uiState.value = uiState.value.copy(address = newValue)
    }

    fun onNotesChange(newValue: String) {
        uiState.value = uiState.value.copy(notes = newValue)
    }
}