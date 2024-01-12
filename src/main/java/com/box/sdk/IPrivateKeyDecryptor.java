package com.box.sdk;

import java.security.PrivateKey;

public interface IPrivateKeyDecryptor {

    PrivateKey decryptPrivateKey(String encryptedPrivateKey, String passphrase);
}
