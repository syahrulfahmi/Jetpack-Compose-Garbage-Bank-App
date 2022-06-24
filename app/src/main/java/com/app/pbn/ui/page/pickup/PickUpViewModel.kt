package com.app.pbn.ui.page.pickup

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.app.pbn.common.service.AccountService
import com.app.pbn.common.service.StorageService
import com.app.pbn.constant.Constant
import com.app.pbn.model.TrashModel
import com.app.pbn.model.TrashTypeModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PickUpViewModel @Inject constructor(
    private val storageService: StorageService,
    private val accountService: AccountService
) : ViewModel() {
    private val _uiState = MutableStateFlow(TrashModel())
    val uiState = _uiState.asStateFlow()

    private val _trashList = MutableStateFlow(arrayListOf<TrashTypeModel>())
    val trashList = _trashList.asStateFlow()


    var loading = mutableStateOf(false)
    var isShowDialogError = mutableStateOf(false)
    var errorMessage = mutableStateOf("")

    fun savePickUpRequest(doOnSuccess: () -> Unit) {
        if (!isValid()) {
            isShowDialogError.value = true
            errorMessage.value = "Data tidak boleh kosong!"
            return
        }
        loading.value = true
        viewModelScope.launch {
            storageService.savePickUpTrash(uiState.value) { error ->
                if (error == null) {
                    doOnSuccess.invoke()
                } else {
                    isShowDialogError.value = true
                    errorMessage.value = error.message.toString()
                }
                loading.value = false
            }
        }
    }

    fun getUserAccount() {
        viewModelScope.launch {
            accountService.getUserInfo()?.email?.let {
                onChangeEmail(it)
            }
        }
    }

    fun getTrashType() {
        viewModelScope.launch {
            storageService.getTrashType(
                onFailure = { error ->
                    isShowDialogError.value = true
                    errorMessage.value = error?.message.toString()
                },
                onSuccess = {
                    _trashList.value = ArrayList(it)
                }
            )
        }
    }

    private fun isValid(): Boolean {
        return uiState.value.name.isNotEmpty()
                && uiState.value.weight != Constant.ZERO_VALUE
                && uiState.value.price != Constant.ZERO_VALUE
                && uiState.value.category.isNotEmpty()
                && uiState.value.date.isNotEmpty()
                && uiState.value.address.isNotEmpty()
    }

    private fun onChangeEmail(newValue: String) {
        _uiState.value = uiState.value.copy(email = newValue)
    }

    fun onUserNameChange(newValue: String) {
        _uiState.value = uiState.value.copy(name = newValue)
    }

    fun onWeightChange(newValue: String) {
        try {
            _uiState.value = uiState.value.copy(weight = newValue.toInt())
        } catch (e: Exception) {
            _uiState.value = uiState.value.copy(weight = Constant.ZERO_VALUE)
            e.printStackTrace()
        }
    }

    fun onPriceChange(newValue: String) {
        try {
            _uiState.value = uiState.value.copy(price = newValue.toInt())
        } catch (e: Exception) {
            _uiState.value = uiState.value.copy(price = Constant.ZERO_VALUE)
            e.printStackTrace()
        }
    }

    fun onCategoryChange(newValue: String) {
        _uiState.value = uiState.value.copy(category = newValue)
    }

    fun onDatesChange(newValue: String) {
        _uiState.value = uiState.value.copy(date = newValue)
    }

    fun onAddressChange(newValue: String) {
        _uiState.value = uiState.value.copy(address = newValue)
    }

    fun onNotesChange(newValue: String) {
        _uiState.value = uiState.value.copy(notes = newValue)
    }
}