package com.app.pbn.common.service.serviceimpl

import com.app.pbn.common.ext.encrypt
import com.app.pbn.common.service.AccountService
import com.app.pbn.constant.USER
import com.app.pbn.model.UserModel
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.ktx.toObject
import com.google.firebase.ktx.Firebase
import javax.inject.Inject

class AccountServiceImpl @Inject constructor() : AccountService {

    override fun hasUser(): Boolean {
        return Firebase.auth.currentUser != null
    }

    override fun getUserInfo(): FirebaseUser? {
        return Firebase.auth.currentUser
    }

    override fun signUpAccount(email: String, password: String, onResult: (Throwable?) -> Unit) {
        Firebase.auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener { onResult(it.exception) }
    }

    override fun signInAccount(email: String, password: String, onResult: (Throwable?) -> Unit) {
        Firebase.auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener { onResult(it.exception) }
    }

    override fun addUserToFireStore(data: UserModel, onResult: (Throwable?) -> Unit) {
        val storeData = UserModel(
            name = data.name,
            email = data.email,
            password = data.password.encrypt(),
            repeatPassword = data.repeatPassword.encrypt()
        )
        Firebase.firestore
            .collection(USER)
            .document(storeData.email)
            .set(storeData)
            .addOnCompleteListener { onResult(it.exception) }
    }

    override fun getUserFromFireStore(userEmail: String, onFailure: (Throwable?) -> Unit, onSuccess: (UserModel) -> Unit) {
        Firebase.firestore
            .collection(USER)
            .document(userEmail)
            .get()
            .addOnFailureListener { onFailure(it) }
            .addOnSuccessListener { result -> onSuccess(result.toObject() ?: UserModel()) }
    }

    override fun logOut() {
        Firebase.auth.signOut()
    }
}