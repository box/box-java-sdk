package com.box.sdk;

import java.io.IOException;
import java.io.StringReader;
import java.security.PrivateKey;
import java.security.Security;
import org.bouncycastle.asn1.pkcs.PrivateKeyInfo;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.bouncycastle.openssl.PEMDecryptorProvider;
import org.bouncycastle.openssl.PEMEncryptedKeyPair;
import org.bouncycastle.openssl.PEMKeyPair;
import org.bouncycastle.openssl.PEMParser;
import org.bouncycastle.openssl.jcajce.JcaPEMKeyConverter;
import org.bouncycastle.openssl.jcajce.JceOpenSSLPKCS8DecryptorProviderBuilder;
import org.bouncycastle.openssl.jcajce.JcePEMDecryptorProviderBuilder;
import org.bouncycastle.operator.InputDecryptorProvider;
import org.bouncycastle.operator.OperatorCreationException;
import org.bouncycastle.pkcs.PKCS8EncryptedPrivateKeyInfo;
import org.bouncycastle.pkcs.PKCSException;


public class BCPrivateKeyDecryptor implements IPrivateKeyDecryptor {

    public BCPrivateKeyDecryptor() {
        Security.addProvider(new BouncyCastleProvider());
    }

    @Override
    public PrivateKey decryptPrivateKey(String encryptedPrivateKey, String passphrase) {
        PrivateKey decryptedPrivateKey;
        try {
            PEMParser keyReader = new PEMParser(new StringReader(encryptedPrivateKey));
            Object keyPair = keyReader.readObject();
            keyReader.close();

            if (keyPair instanceof PrivateKeyInfo) {
                PrivateKeyInfo keyInfo = (PrivateKeyInfo) keyPair;
                decryptedPrivateKey = (new JcaPEMKeyConverter()).getPrivateKey(keyInfo);
            } else if (keyPair instanceof PEMEncryptedKeyPair) {
                JcePEMDecryptorProviderBuilder builder = new JcePEMDecryptorProviderBuilder();
                PEMDecryptorProvider decryptionProvider = builder.build(passphrase.toCharArray());
                keyPair = ((PEMEncryptedKeyPair) keyPair).decryptKeyPair(decryptionProvider);
                PrivateKeyInfo keyInfo = ((PEMKeyPair) keyPair).getPrivateKeyInfo();
                decryptedPrivateKey = (new JcaPEMKeyConverter()).getPrivateKey(keyInfo);
            } else if (keyPair instanceof PKCS8EncryptedPrivateKeyInfo) {
                InputDecryptorProvider pkcs8Prov = new JceOpenSSLPKCS8DecryptorProviderBuilder().setProvider("BC")
                        .build(passphrase.toCharArray());
                PrivateKeyInfo keyInfo = ((PKCS8EncryptedPrivateKeyInfo) keyPair).decryptPrivateKeyInfo(pkcs8Prov);
                decryptedPrivateKey = (new JcaPEMKeyConverter()).getPrivateKey(keyInfo);
            } else {
                PrivateKeyInfo keyInfo = ((PEMKeyPair) keyPair).getPrivateKeyInfo();
                decryptedPrivateKey = (new JcaPEMKeyConverter()).getPrivateKey(keyInfo);
            }
        } catch (IOException e) {
            throw new BoxAPIException("Error parsing private key for Box Developer Edition.", e);
        } catch (OperatorCreationException e) {
            throw new BoxAPIException("Error parsing PKCS#8 private key for Box Developer Edition.", e);
        } catch (PKCSException e) {
            throw new BoxAPIException("Error parsing PKCS private key for Box Developer Edition.", e);
        }
        return decryptedPrivateKey;
    }
}
