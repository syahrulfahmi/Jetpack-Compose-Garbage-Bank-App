package com.app.pbn.common.service.serviceimpl

import com.app.pbn.common.service.StorageService
import com.app.pbn.constant.TRASH
import com.app.pbn.constant.TRASH_TYPE
import com.app.pbn.model.TrashModel
import com.app.pbn.model.TrashTypeModel
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import javax.inject.Inject


class StorageServiceImpl @Inject constructor() : StorageService {

    override fun savePickUpTrash(trashModel: TrashModel, onResult: (Throwable?) -> Unit) {
        Firebase.firestore
            .collection(TRASH)
            .document(trashModel.email)
            .set(trashModel)
            .addOnCompleteListener { onResult(it.exception) }
    }

    override fun getTrashType(onFailure: (Throwable?) -> Unit, onSuccess: (List<TrashTypeModel>) -> Unit) {
        Firebase.firestore
            .collection(TRASH_TYPE)
            .document("izQzTYkj6ocuKCFWCKrg")
            .get()
            .addOnFailureListener(onFailure)
            .addOnCompleteListener {
                if (it.isSuccessful) {
                    val trashTypeList = mutableListOf<TrashTypeModel>()
                    val document: DocumentSnapshot = it.result
                    if (document.exists()) {
                        val users = document["sampah"] as List<Map<String, Any>>?
                        users?.forEach { item ->
                            trashTypeList.add(
                                TrashTypeModel(
                                    id = item["id"].toString().toInt(),
                                    name = item["name"].toString(),
                                    price = item["price"].toString().toInt()
                                )
                            )
                        }
                        onSuccess(trashTypeList)
                    }
                }
            }
    }
}