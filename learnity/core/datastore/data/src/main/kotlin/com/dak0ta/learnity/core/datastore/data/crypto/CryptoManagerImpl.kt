package com.dak0ta.learnity.core.datastore.data.crypto

import android.security.keystore.KeyProperties.BLOCK_MODE_GCM
import android.security.keystore.KeyProperties.ENCRYPTION_PADDING_NONE
import android.security.keystore.KeyProperties.PURPOSE_DECRYPT
import android.security.keystore.KeyProperties.PURPOSE_ENCRYPT
import android.util.Base64
import java.security.KeyStore
import javax.crypto.Cipher
import javax.crypto.KeyGenerator
import javax.crypto.SecretKey
import javax.crypto.spec.GCMParameterSpec
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
internal class CryptoManagerImpl @Inject constructor() : CryptoManager {

    private val keyAlias = "core_datastore_aes_key"
    private val androidKeyStore = "AndroidKeyStore"
    private val keySize = 256
    private val aesMode = "AES/GCM/NoPadding"
    private val ivSize = 12

    private fun getOrCreateSecretKey(): SecretKey {
        val keyStore = KeyStore.getInstance(androidKeyStore).apply { load(null) }
        (keyStore.getKey(keyAlias, null) as? SecretKey)?.let { return it }

        val keyGenerator = KeyGenerator.getInstance("AES", androidKeyStore)
        val paramSpec = android.security.keystore.KeyGenParameterSpec.Builder(
            keyAlias,
            PURPOSE_ENCRYPT or PURPOSE_DECRYPT,
        ).run {
            setBlockModes(BLOCK_MODE_GCM)
            setEncryptionPaddings(ENCRYPTION_PADDING_NONE)
            setKeySize(keySize)
            setUserAuthenticationRequired(false)
            build()
        }
        keyGenerator.init(paramSpec)
        return keyGenerator.generateKey()
    }

    override fun encryptToBase64(plain: String): String {
        val key = getOrCreateSecretKey()
        val cipher = Cipher.getInstance(aesMode)
        cipher.init(Cipher.ENCRYPT_MODE, key)
        val iv = cipher.iv
        val ciphertext = cipher.doFinal(plain.toByteArray(Charsets.UTF_8))
        val combined = ByteArray(iv.size + ciphertext.size)
        System.arraycopy(iv, 0, combined, 0, iv.size)
        System.arraycopy(ciphertext, 0, combined, iv.size, ciphertext.size)
        return Base64.encodeToString(combined, Base64.NO_WRAP)
    }

    override fun decryptFromBase64(cipher: String): String? {
        var result: String? = null
        try {
            val combined = Base64.decode(cipher, Base64.NO_WRAP)
            if (combined.size > ivSize) {
                val iv = combined.copyOfRange(0, ivSize)
                val cipherText = combined.copyOfRange(ivSize, combined.size)
                val key = getOrCreateSecretKey()
                val cipher = Cipher.getInstance(aesMode)
                val spec = GCMParameterSpec(128, iv)
                cipher.init(Cipher.DECRYPT_MODE, key, spec)
                val plain = cipher.doFinal(cipherText)
                result = String(plain, Charsets.UTF_8)
            }
        } catch (_: Exception) {
        }
        return result
    }
}
