package com.dak0ta.learnity.core.datastore.data.crypto

internal interface CryptoManager {

    fun encryptToBase64(plain: String): String
    fun decryptFromBase64(cipher: String): String?
}
