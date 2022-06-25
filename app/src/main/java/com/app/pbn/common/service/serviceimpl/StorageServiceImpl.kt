package com.app.pbn.common.service.serviceimpl

import com.app.pbn.common.service.StorageService
import com.app.pbn.constant.TRASH
import com.app.pbn.constant.TRASH_TYPE
import com.app.pbn.model.TrashHistory
import com.app.pbn.model.TrashModel
import com.app.pbn.model.TrashTypeModel
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import javax.inject.Inject

class StorageServiceImpl @Inject constructor() : StorageService {

    override fun savePickUpTrash(trashModel: TrashModel, onResult: (Throwable?) -> Unit) {
        Firebase.firestore
            .collection(TRASH)
            .document(trashModel.id)
            .set(trashModel)
            .addOnCompleteListener { onResult(it.exception) }
    }

    override fun saveTrashType(trasyTypeModel: TrashTypeModel, onResult: (Throwable?) -> Unit) {
        Firebase.firestore
            .collection(TRASH_TYPE)
            .document(trasyTypeModel.id)
            .set(trasyTypeModel)
            .addOnCompleteListener { onResult(it.exception) }
    }

    override fun getTrashType(onFailure: (Throwable?) -> Unit, onSuccess: (List<TrashTypeModel>) -> Unit) {
        Firebase.firestore
            .collection(TRASH_TYPE)
            .get()
            .addOnFailureListener(onFailure)
            .addOnCompleteListener {
                if (it.isSuccessful) {
                    val trashTypeList = mutableListOf<TrashTypeModel>()
                    val document = it.result
                    document.forEach { item ->
                        trashTypeList.add(
                            TrashTypeModel(
                                id = item["id"].toString(),
                                name = item["name"].toString(),
                                price = item["price"].toString().toInt()
                            )
                        )
                    }
                    onSuccess(trashTypeList)
                }
            }
    }

    override fun getHistory(userEmail: String, onFailure: (Throwable?) -> Unit, onSuccess: (List<TrashHistory>) -> Unit) {
        Firebase.firestore
            .collection(TRASH)
            .whereEqualTo(USER_EMAIL, userEmail)
            .get()
            .addOnFailureListener { error -> onFailure(error) }
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    val data = mutableListOf<TrashHistory>()
                    for (doc in task.result) {
                        data.add(
                            TrashHistory(
                                id = doc.data["id"].toString(),
                                name = doc.data["name"].toString(),
                                weight = doc.data["weight"].toString().toInt(),
                                price = doc.data["price"].toString().toInt(),
                                category = doc.data["category"].toString(),
                                address = doc.data["address"].toString(),
                                date = doc.data["date"].toString(),
                                status = doc.data["status"].toString().toInt()
                            )
                        )
                    }
                    onSuccess(data)
                }
            }
    }

    override fun getHistoryPickUp(acceptedBy: String, onFailure: (Throwable?) -> Unit, onSuccess: (List<TrashHistory>) -> Unit) {
        Firebase.firestore
            .collection(TRASH)
            .whereEqualTo(ACCEPTED_BY, acceptedBy)
            .get()
            .addOnFailureListener { error -> onFailure(error) }
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    val data = mutableListOf<TrashHistory>()
                    for (doc in task.result) {
                        data.add(
                            TrashHistory(
                                id = doc.data["id"].toString(),
                                name = doc.data["name"].toString(),
                                weight = doc.data["weight"].toString().toInt(),
                                price = doc.data["price"].toString().toInt(),
                                category = doc.data["category"].toString(),
                                address = doc.data["address"].toString(),
                                date = doc.data["date"].toString(),
                                status = doc.data["status"].toString().toInt()
                            )
                        )
                    }
                    onSuccess(data)
                }
            }
    }

    override fun getRequestPickUp(onFailure: (Throwable?) -> Unit, onSuccess: (List<TrashHistory>) -> Unit) {
        Firebase.firestore
            .collection(TRASH)
            .whereEqualTo(STATUS, 0)
            .get()
            .addOnFailureListener { error -> onFailure(error) }
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    val data = mutableListOf<TrashHistory>()
                    for (doc in task.result) {
                        data.add(
                            TrashHistory(
                                id = doc.data["id"].toString(),
                                name = doc.data["name"].toString(),
                                weight = doc.data["weight"].toString().toInt(),
                                price = doc.data["price"].toString().toInt(),
                                category = doc.data["category"].toString(),
                                address = doc.data["address"].toString(),
                                date = doc.data["date"].toString(),
                                status = doc.data["status"].toString().toInt()
                            )
                        )
                    }
                    onSuccess(data)
                }
            }
    }

    override fun updateRequestPickUp(requestId: String, status: Int, acceptedBy: String, onResult: (Throwable?) -> Unit) {
        Firebase.firestore
            .collection(TRASH)
            .document(requestId)
            .update(
                STATUS, status,
                ACCEPTED_BY, acceptedBy
            )
            .addOnFailureListener { error -> onResult(error) }
            .addOnSuccessListener { onResult(null) }
    }

    override fun deleteHistory(historyId: String, onResult: (Throwable?) -> Unit) {
        Firebase.firestore
            .collection(TRASH)
            .document(historyId)
            .delete()
            .addOnCompleteListener { onResult(it.exception) }
    }

    companion object {
        private const val USER_EMAIL = "email"
        private const val STATUS = "status"
        private const val ACCEPTED_BY = "acceptedBy"
    }
}