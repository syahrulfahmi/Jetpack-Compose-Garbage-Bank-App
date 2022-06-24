package com.app.pbn.common.service

import com.app.pbn.model.UserModel
import com.google.firebase.auth.FirebaseUser

interface AccountService {
    fun hasUser(): Boolean
    fun getUserInfo(): FirebaseUser?
    fun signUpAccount(email: String, password: String, onResult: (Throwable?) -> Unit)
    fun signInAccount(email: String, password: String, onResult: (Throwable?) -> Unit)
    fun addUserToFireStore(data: UserModel, onResult: (Throwable?) -> Unit)
    fun getUserFromFireStore(userEmail: String, onFailure: (Throwable?) -> Unit, onSuccess: (UserModel) -> Unit)
    fun logOut()
}