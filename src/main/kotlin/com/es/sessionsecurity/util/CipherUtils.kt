package com.es.sessionsecurity.util

import org.springframework.stereotype.Component
import java.security.SecureRandom
import javax.crypto.Cipher
import javax.crypto.spec.SecretKeySpec
import javax.crypto.spec.IvParameterSpec
import java.util.Base64

@Component
class CipherUtils {

    /**
     * Método para cifrar una cadena usando un cifrado por clave simétrica
     * La key proporcionada por parámetros debe ser la misma que se vaya a usar a la hora de descifrar
     */
    fun encrypt(cadenaACifrar: String, key: String): String {
        val keyBytes = key.toByteArray(Charsets.UTF_8).copyOf(16)
        val secretKey = SecretKeySpec(keyBytes, "AES")

        val cipher = Cipher.getInstance("AES/CBC/PKCS5Padding")
        val ivBytes = ByteArray(16)
        SecureRandom().nextBytes(ivBytes) // Genera un IV aleatorio
        val iv = IvParameterSpec(ivBytes)

        cipher.init(Cipher.ENCRYPT_MODE, secretKey, iv)
        val cipherText = cipher.doFinal(cadenaACifrar.toByteArray(Charsets.UTF_8))

        // Combina el IV y el texto cifrado
        val ivAndCipherText = ivBytes + cipherText
        return Base64.getEncoder().encodeToString(ivAndCipherText)
    }


    /**
     * Método para descifrar una cadena usando un cifrado por clave simétrica
     * La key proporcionada por parámetros debe ser la misma que se usó para cifrar la cadena
     */
    fun decrypt(cadenaCifrada: String, key: String): String {
        val keyBytes = key.toByteArray(Charsets.UTF_8).copyOf(16)
        val secretKey = SecretKeySpec(keyBytes, "AES")

        val decodedBytes = Base64.getDecoder().decode(cadenaCifrada)

        // Extrae el IV (primeros 16 bytes) y el texto cifrado
        val ivBytes = decodedBytes.sliceArray(0 until 16)
        val cipherText = decodedBytes.sliceArray(16 until decodedBytes.size)
        val iv = IvParameterSpec(ivBytes)

        val cipher = Cipher.getInstance("AES/CBC/PKCS5Padding")
        cipher.init(Cipher.DECRYPT_MODE, secretKey, iv)
        val plainText = cipher.doFinal(cipherText)

        return String(plainText, Charsets.UTF_8)
    }



}