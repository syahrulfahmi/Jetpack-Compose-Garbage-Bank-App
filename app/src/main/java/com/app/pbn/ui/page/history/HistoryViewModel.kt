package com.app.pbn.ui.page.history

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.app.pbn.common.ext.convertToCurrencyFormat
import com.app.pbn.common.service.AccountService
import com.app.pbn.common.service.StorageService
import com.app.pbn.model.TrashHistory
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HistoryViewModel @Inject constructor(
    private val storageService: StorageService,
    private val accountService: AccountService
) : ViewModel() {
    private val _uiState = MutableStateFlow(emptyList<TrashHistory>())
    val uiState = _uiState.asStateFlow()

    private val _requestPickUp = MutableStateFlow(emptyList<TrashHistory>())
    val requestPickUp = _requestPickUp.asStateFlow()

    var userState = mutableStateOf("")
        private set

    private val email get() = userState.value
    private val _saldoUiState = MutableStateFlow("")
    val saldoUiState = _saldoUiState.asStateFlow()

    var loading = mutableStateOf(false)
    var isShowDialog = mutableStateOf(false)
    var errorMessage = mutableStateOf("")

    fun getUserInfo() {
        viewModelScope.launch {
            accountService.getUserInfo()?.email?.let { email ->
                accountService.getUserFromFireStore(
                    email,
                    onFailure = {},
                    onSuccess = {
                        userState.value = it.email
                    }
                )
            }
        }
    }

    fun getHistoryByEmail() {
        loading.value = true
        viewModelScope.launch {
            accountService.getUserInfo()?.email?.let { email ->
                storageService.getHistory(email,
                    onFailure = {
                        isShowDialog.value = true
                        errorMessage.value = "Gagal mengambil data!"
                    },
                    onSuccess = {
                        _uiState.value = ArrayList(it)
                        _saldoUiState.value = if (_uiState.value.isNotEmpty()) {
                            _uiState.value.filter { item -> item.status == 1 }.sumOf { item -> item.weight?.times(item.price) ?: 0 }.toString().convertToCurrencyFormat()
                        } else {
                            "Rp 0"
                        }
                    }
                )
                loading.value = false
            }
        }
    }

    fun getHistoryPickUp(acceptedBy: String) {
        loading.value = true
        viewModelScope.launch {
            storageService.getHistoryPickUp(
                acceptedBy = acceptedBy,
                onFailure = {
                    isShowDialog.value = true
                    errorMessage.value = "Gagal mengambil data!"
                },
                onSuccess = {
                    _uiState.value = ArrayList(it)
                }
            )
            loading.value = false
        }
    }

    fun getRequestPickUp() {
        loading.value = true
        viewModelScope.launch {
            storageService.getRequestPickUp(
                onFailure = {
                    isShowDialog.value = true
                    errorMessage.value = "Gagal mengambil data!"
                },
                onSuccess = {
                    _requestPickUp.value = ArrayList(it)
                }
            )
            loading.value = false
        }
    }

    fun updateRequestStatus(requestId: String, status: Int, doOnSuccess: () -> Unit) {
        loading.value = true
        viewModelScope.launch {
            storageService.updateRequestPickUp(requestId, status, email) { error ->
                if (error != null) {
                    isShowDialog.value = true
                    errorMessage.value = "Gagal memperbarui data!"
                } else {
                    doOnSuccess.invoke()
                }
            }
            loading.value = false
        }
    }

    fun deleteHistory(historyId: String, doOnSuccess: () -> Unit) {
        viewModelScope.launch {
            storageService.deleteHistory(historyId) { error ->
                error?.let {
                    isShowDialog.value = true
                    errorMessage.value = "Gagal menghapus data!"
                } ?: kotlin.run {
                    isShowDialog.value = true
                    errorMessage.value = "Berhasil menghapus data!"
                }
            }
        }
    }

    fun addNewItem(trashModel: TrashHistory, status: Int) {
        val data = trashModel.copy(status = status)
        _uiState.value = _uiState.value + data
    }

    fun removeHistory(trashModel: TrashHistory) {
        _uiState.value = uiState.value - trashModel
    }

    fun removeItem(trashModel: TrashHistory) {
        _requestPickUp.value = _requestPickUp.value - trashModel
    }

}