package com.box.sdk;

import java.security.PrivateKey;

/**
 *  Implement this interface to provide a custom private key decryptor.
 *  If you require the decryption operation to be FIPS compliant,
 *  ensure that your implementation exclusively utilizes FIPS certified libraries.
 */
public interface IPrivateKeyDecryptor {

    /**
     * Decrypts private key with provided passphrase using Bouncy Castle library
     *
     * @param encryptedPrivateKey Encoded private key string.
     * @param passphrase Private key passphrase.
     * @return java.security.PrivateKey instance representing decrypted private key.
     */
    PrivateKey decryptPrivateKey(String encryptedPrivateKey, String passphrase);
}
