package com.teslasoft.security.aes;

import java.nio.charset.StandardCharsets;

public abstract class AESCrypt {
    private static final String AES_MODE = "AES/CBC/PKCS7Padding";
    private static final String HASH_ALGORITHM = "SHA-256";
    private static final byte[] ivBytes = {0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00};

    private AESCrypt() {}

    private static javax.crypto.spec.SecretKeySpec generateKey(final String password) throws java.security.NoSuchAlgorithmException /*, java.io.UnsupportedEncodingException */ {
        final java.security.MessageDigest digest = java.security.MessageDigest.getInstance(HASH_ALGORITHM);
        byte[] bytes = password.getBytes(StandardCharsets.UTF_8);
        digest.update(bytes, 0, bytes.length);
        byte[] key = digest.digest();
        return new javax.crypto.spec.SecretKeySpec(key, "AES");
    }

    public static String encrypt(final String password, String message) throws java.security.GeneralSecurityException {
        final javax.crypto.spec.SecretKeySpec key = generateKey(password);

        byte[] cipherText = encrypt(key, ivBytes, message.getBytes(StandardCharsets.UTF_8));
        byte[] xCipherText = new byte[cipherText.length];
        int size = cipherText.length;

        for (int i = 0; i < size; i++) {
            if ((int) xCipherText[i] < 0) {
                xCipherText[i] = (byte) (255 + xCipherText[i]);
            } else {
                xCipherText[i] = cipherText[i];
            }
        }

        return android.util.Base64.encodeToString(xCipherText, android.util.Base64.NO_WRAP);
    }

    public static byte[] encrypt(final javax.crypto.spec.SecretKeySpec key, final byte[] iv, final byte[] message) throws java.security.GeneralSecurityException {
        final javax.crypto.Cipher cipher = javax.crypto.Cipher.getInstance(AES_MODE);
        javax.crypto.spec.IvParameterSpec ivSpec = new javax.crypto.spec.IvParameterSpec(iv);
        cipher.init(javax.crypto.Cipher.ENCRYPT_MODE, key, ivSpec);
        return cipher.doFinal(message);
    }

    public static String decrypt(final String password, String base64EncodedCipherText) throws java.security.GeneralSecurityException {
        final javax.crypto.spec.SecretKeySpec key = generateKey(password);
        byte[] decodedCipherText = android.util.Base64.decode(base64EncodedCipherText, android.util.Base64.NO_WRAP);
        byte[] decryptedBytes = decrypt(key, ivBytes, decodedCipherText);
        return new String(decryptedBytes, StandardCharsets.UTF_8);
    }

    public static byte[] decrypt(final javax.crypto.spec.SecretKeySpec key, final byte[] iv, final byte[] decodedCipherText) throws java.security.GeneralSecurityException {
        final javax.crypto.Cipher cipher = javax.crypto.Cipher.getInstance(AES_MODE);
        javax.crypto.spec.IvParameterSpec ivSpec = new javax.crypto.spec.IvParameterSpec(iv);
        cipher.init(javax.crypto.Cipher.DECRYPT_MODE, key, ivSpec);
        return cipher.doFinal(decodedCipherText);
    }
}