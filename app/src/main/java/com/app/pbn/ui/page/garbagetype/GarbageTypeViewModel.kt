package com.app.pbn.ui.page.garbagetype

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.app.pbn.common.service.StorageService
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
}