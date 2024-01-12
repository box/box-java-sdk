package com.box.sdk;

import java.security.Security;
import org.bouncycastle.jce.provider.BouncyCastleProvider;

/**
 * Contains the encryption preferences for JWT assertion.
 */

public class JWTEncryptionPreferences {
    private String publicKeyID;
    private String privateKey;
    private String privateKeyPassword;
    private EncryptionAlgorithm encryptionAlgorithm;
    private IPrivateKeyDecryptor privateKeyDecryptor = new BCPrivateKeyDecryptor();

    static {
        Security.addProvider(new BouncyCastleProvider());
    }

    /**
     * Returns the ID for public key for validating the JWT signature.
     *
     * @return the publicKeyID.
     */
    public String getPublicKeyID() {
        return this.publicKeyID;
    }

    /**
     * Sets the ID for public key for validating the JWT signature.
     *
     * @param publicKeyID the publicKeyID to set.
     */
    public void setPublicKeyID(String publicKeyID) {
        this.publicKeyID = publicKeyID;
    }

    /**
     * Returns the private key for generating the JWT signature.
     *
     * @return the privateKey.
     */
    public String getPrivateKey() {
        return this.privateKey;
    }

    /**
     * Sets the private key for generating the JWT signature.
     *
     * @param privateKey the privateKey to set.
     */
    public void setPrivateKey(String privateKey) {
        this.privateKey = privateKey;
    }

    /**
     * Returns the password for the private key.
     *
     * @return the privateKeyPassword.
     */
    public String getPrivateKeyPassword() {
        return this.privateKeyPassword;
    }

    /**
     * Sets the password for the private key.
     *
     * @param privateKeyPassword the privateKeyPassword to set.
     */
    public void setPrivateKeyPassword(String privateKeyPassword) {
        this.privateKeyPassword = privateKeyPassword;
    }

    /**
     * Returns the type of encryption algorithm for JWT.
     *
     * @return the encryptionAlgorithm.
     */
    public EncryptionAlgorithm getEncryptionAlgorithm() {
        return this.encryptionAlgorithm;
    }

    /**
     * Sets the type of encryption algorithm for JWT.
     *
     * @param encryptionAlgorithm the encryptionAlgorithm to set.
     */
    public void setEncryptionAlgorithm(EncryptionAlgorithm encryptionAlgorithm) {
        this.encryptionAlgorithm = encryptionAlgorithm;
    }

    public IPrivateKeyDecryptor getPrivateKeyDecryptor() {
        return privateKeyDecryptor;
    }

    public void setPrivateKeyDecryptor(IPrivateKeyDecryptor privateKeyDecryptor) {
        this.privateKeyDecryptor = privateKeyDecryptor;
    }
}
