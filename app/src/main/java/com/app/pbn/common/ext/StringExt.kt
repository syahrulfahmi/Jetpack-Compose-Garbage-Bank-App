package com.app.pbn.common.ext

import android.util.Base64
import android.util.Patterns
import com.app.pbn.constant.iv
import com.app.pbn.constant.salt
import com.app.pbn.constant.secretKey
import javax.crypto.Cipher
import javax.crypto.SecretKeyFactory
import javax.crypto.spec.IvParameterSpec
import javax.crypto.spec.PBEKeySpec
import javax.crypto.spec.SecretKeySpec

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

fun String.encrypt(): String {
    val ivParameterSpec = IvParameterSpec(Base64.decode(iv, Base64.DEFAULT))
    val factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1")
    val spec = PBEKeySpec(secretKey.toCharArray(), Base64.decode(salt, Base64.DEFAULT), 10000, 256)
    val tmp = factory.generateSecret(spec)
    val secretKey = SecretKeySpec(tmp.encoded, "AES")

    val cipher = Cipher.getInstance("AES/CBC/PKCS7Padding")
    cipher.init(Cipher.ENCRYPT_MODE, secretKey, ivParameterSpec)
    return Base64.encodeToString(cipher.doFinal(this.toByteArray(Charsets.UTF_8)), Base64.DEFAULT)
}