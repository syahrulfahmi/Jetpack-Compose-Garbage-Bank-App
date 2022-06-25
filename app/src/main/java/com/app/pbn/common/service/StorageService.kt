package com.app.pbn.common.service

import com.app.pbn.model.TrashHistory
import com.app.pbn.model.TrashModel
import com.app.pbn.model.TrashTypeModel

interface StorageService {
    fun savePickUpTrash(trashModel: TrashModel, onResult: (Throwable?) -> Unit)
    fun saveTrashType(trasyTypeModel: TrashTypeModel, onResult: (Throwable?) -> Unit)
    fun getTrashType(onFailure: (Throwable?) -> Unit, onSuccess: (List<TrashTypeModel>) -> Unit)
    fun getHistory(userEmail: String, onFailure: (Throwable?) -> Unit, onSuccess: (List<TrashHistory>) -> Unit)
    fun getHistoryPickUp(acceptedBy: String, onFailure: (Throwable?) -> Unit, onSuccess: (List<TrashHistory>) -> Unit)
    fun getRequestPickUp(onFailure: (Throwable?) -> Unit, onSuccess: (List<TrashHistory>) -> Unit)
    fun updateRequestPickUp(requestId: String, status: Int, acceptedBy: String, onResult: (Throwable?) -> Unit)
    fun deleteHistory(historyId: String, onResult: (Throwable?) -> Unit)
}