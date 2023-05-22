package com.thaer.core.utils

import android.security.keystore.KeyGenParameterSpec
import android.security.keystore.KeyProperties
import android.util.Base64
import java.math.BigInteger
import java.nio.charset.StandardCharsets
import java.security.*
import java.util.*
import javax.crypto.Cipher
import javax.crypto.KeyGenerator
import javax.crypto.SecretKey
import javax.crypto.spec.IvParameterSpec
import javax.security.auth.x500.X500Principal

object KeyStoreUtils {

    private fun getSecretKeyFromKeyStore(alias: String?): SecretKey {
        val keyStore: KeyStore = KeyStore.getInstance("AndroidKeyStore")
        keyStore.load(null)
        val keySpecBuilder = KeyGenParameterSpec.Builder(
            alias!!,
            KeyProperties.PURPOSE_ENCRYPT or KeyProperties.PURPOSE_DECRYPT
        )
            .setBlockModes(KeyProperties.BLOCK_MODE_CBC)
            .setEncryptionPaddings(KeyProperties.ENCRYPTION_PADDING_PKCS7)
            .setKeySize(256)
        val keyGenerator: KeyGenerator = KeyGenerator.getInstance(
            KeyProperties.KEY_ALGORITHM_AES, "AndroidKeyStore"
        )
        keyGenerator.init(keySpecBuilder.build())
        return keyGenerator.generateKey()
    }

    fun encrypt(alias: String?, data: String?): String {
        val keyStore = KeyStore.getInstance("AndroidKeyStore")
        keyStore.load(null)
        val secretKey = getSecretKey(keyStore, alias)
        val cipher: Cipher = Cipher.getInstance("AES/CBC/PKCS7Padding")
        cipher.init(Cipher.ENCRYPT_MODE, secretKey)
        val iv: ByteArray = cipher.getIV()
        val encryptedData: ByteArray = cipher.doFinal(data?.toByteArray(StandardCharsets.UTF_8))

        // Prepend the IV to the encrypted data
        val combinedData = ByteArray(iv.size + encryptedData.size)
        System.arraycopy(iv, 0, combinedData, 0, iv.size)
        System.arraycopy(encryptedData, 0, combinedData, iv.size, encryptedData.size)

        return Base64.encodeToString(combinedData, Base64.DEFAULT)
    }

    fun decrypt(encryptedDataWithIvBase64: String? ,alias: String?): String {
        val keyStore = KeyStore.getInstance("AndroidKeyStore")
        keyStore.load(null)

        val secretKey = getSecretKey(keyStore, alias)
        val encryptedDataWithIv = Base64.decode(encryptedDataWithIvBase64, Base64.DEFAULT)
        val iv = ByteArray(16)
        System.arraycopy(encryptedDataWithIv, 0, iv, 0, iv.size)
        val encryptedData = ByteArray(encryptedDataWithIv.size - iv.size)
        System.arraycopy(encryptedDataWithIv, iv.size, encryptedData, 0, encryptedData.size)

        val cipher = Cipher.getInstance("AES/CBC/PKCS7Padding")
        cipher.init(Cipher.DECRYPT_MODE, secretKey, IvParameterSpec(iv))
        val decryptedData = cipher.doFinal(encryptedData)

        return String(decryptedData, StandardCharsets.UTF_8)
    }

    private fun getSecretKey(keyStore: KeyStore, alias: String?): SecretKey {
        if (keyStore.getKey(alias, null) == null) {
            return getSecretKeyFromKeyStore(alias)
        } else return keyStore.getKey(alias, null) as SecretKey
    }

    fun generateKeyPair(alias: String) {
        val keyPairGenerator = KeyPairGenerator.getInstance(KeyProperties.KEY_ALGORITHM_RSA, "AndroidKeyStore")

        val keyGenParameterSpec = KeyGenParameterSpec.Builder(
            alias,
            KeyProperties.PURPOSE_SIGN or KeyProperties.PURPOSE_VERIFY or KeyProperties.PURPOSE_ENCRYPT or KeyProperties.PURPOSE_DECRYPT
        )
            .setKeySize(2048)
            .setCertificateSubject(X500Principal("CN=$alias"))
            .setCertificateSerialNumber(BigInteger.ONE)
            .setKeyValidityStart(Calendar.getInstance().time)
            .setKeyValidityEnd(Calendar.getInstance().apply { add(Calendar.YEAR, 30) }.time)
            .setDigests(KeyProperties.DIGEST_SHA256, KeyProperties.DIGEST_SHA512)
            .setEncryptionPaddings(KeyProperties.ENCRYPTION_PADDING_RSA_PKCS1)
            .setSignaturePaddings(KeyProperties.SIGNATURE_PADDING_RSA_PKCS1)
            .build()

        keyPairGenerator.initialize(keyGenParameterSpec)
        keyPairGenerator.generateKeyPair()
    }

    fun getPublicKey(alias: String): PublicKey? {
        try {
            val keyStore = KeyStore.getInstance("AndroidKeyStore").apply { load(null) }
            val certificate = keyStore.getCertificate(alias)
            return certificate?.publicKey
        } catch (e: Exception) {
            return null
        }
    }

    fun getPrivateKey(alias: String): PrivateKey? {
        try {
            val keyStore = KeyStore.getInstance("AndroidKeyStore").apply { load(null) }
            val entry = keyStore.getEntry(alias, null) as? KeyStore.PrivateKeyEntry
            return entry?.privateKey
        } catch (e: Exception) {
            return null
        }
    }

}
