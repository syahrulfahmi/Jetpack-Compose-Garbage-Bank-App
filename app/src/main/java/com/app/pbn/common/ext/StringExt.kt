package com.app.pbn.common.ext

import android.text.TextUtils
import android.util.Base64
import android.util.Patterns
import com.app.pbn.constant.iv
import com.app.pbn.constant.salt
import com.app.pbn.constant.secretKey
import java.text.DecimalFormat
import java.text.DecimalFormatSymbols
import java.util.*
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
fun String.convertToCurrencyFormat(): String {
    val locale = Locale("in", "id")
    val symbols = DecimalFormatSymbols.getInstance(locale)
    symbols.groupingSeparator = '.'
    symbols.monetaryDecimalSeparator = ','
    symbols.currencySymbol = symbols.currencySymbol

    val df = DecimalFormat.getCurrencyInstance(locale) as DecimalFormat
    val kursIndonesia = DecimalFormat(df.toPattern(), symbols)
    kursIndonesia.maximumFractionDigits = 0

    return if (TextUtils.isEmpty(this)) {
        kursIndonesia.format(0)
    } else {
        kursIndonesia.format(java.lang.Double.parseDouble(this))
    }
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