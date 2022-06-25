package com.app.pbn.ui.page.garbagetype

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.app.pbn.common.service.StorageService
import com.app.pbn.constant.Constant
import com.app.pbn.model.TrashTypeModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class GarbageTypeViewModel @Inject constructor(
    private val storageService: StorageService
) : ViewModel() {

    private val _trashList = MutableStateFlow(arrayListOf<TrashTypeModel>())
    val trashList = _trashList.asStateFlow()
    private val _trashType = MutableStateFlow(TrashTypeModel())
    val trashType = _trashType.asStateFlow()


    var loading = mutableStateOf(false)
    var isShowDialogError = mutableStateOf(false)
    var errorMessage = mutableStateOf("")

    fun getTrashType() {
        loading.value = true
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
            loading.value = false
        }
    }

    fun saveTrashType(doOnSuccess: () -> Unit) {
        if (!isValid()) {
            isShowDialogError.value = true
            errorMessage.value = "Data tidak boleh kosong!"
            return
        }
        loading.value = true
        viewModelScope.launch {
            storageService.saveTrashType(trashType.value) { error ->
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

    private fun isValid(): Boolean {
        return trashType.value.name.isNotEmpty()
                && trashType.value.price != Constant.ZERO_VALUE
    }

    fun onNameChange(newValue: String) {
        _trashType.value = trashType.value.copy(name = newValue)
    }

    fun onPriceChange(newValue: String) {
        try {
            _trashType.value = trashType.value.copy(price = newValue.toInt())
        } catch (e: Exception) {
            _trashType.value = trashType.value.copy(price = Constant.ZERO_VALUE)
            e.printStackTrace()
        }
    }
}