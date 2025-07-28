package com.box.sdkgen.internal.utils;

import java.security.PrivateKey;

/** Decrypts private key with provided passphrase */
public interface PrivateKeyDecryptor {
  /** Decrypts private key using a passphrase. */
  PrivateKey decryptPrivateKey(String encryptedPrivateKey, String passphrase);
}
