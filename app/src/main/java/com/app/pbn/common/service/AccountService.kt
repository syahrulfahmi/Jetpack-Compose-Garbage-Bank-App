package com.app.pbn.common.service

interface AccountService {
    fun signUpAccount(email: String, password: String, onResult: (Throwable?) -> Unit)
    fun signInAccount(email: String, password: String, onResult: (Throwable?) -> Unit)
}