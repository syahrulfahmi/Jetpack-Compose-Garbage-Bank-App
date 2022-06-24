package com.app.pbn.common.service

import com.app.pbn.model.TrashModel
import com.app.pbn.model.TrashTypeModel

interface StorageService {
    fun savePickUpTrash(trashModel: TrashModel, onResult: (Throwable?) -> Unit)
    fun getTrashType(onFailure: (Throwable?) -> Unit, onSuccess: (List<TrashTypeModel>) -> Unit)
}