package com.app.pbn.common.ext

import android.util.Patterns

private const val MIN_PASS_LENGTH = 6

fun String.isValidEmail(): Boolean {
    return this.isNotBlank() && Patterns.EMAIL_ADDRESS.matcher(this).matches()
}

fun String.isValidPassword(): Boolean {
    return this.isNotBlank() && this.length >= MIN_PASS_LENGTH
}

fun String.passwordMatches(repeated: String): Boolean {
    return this == repeated
}