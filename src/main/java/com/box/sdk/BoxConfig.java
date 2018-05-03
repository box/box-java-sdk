package com.box.sdk;

import java.io.IOException;
import java.io.Reader;

import com.eclipsesource.json.JsonObject;

/**
 * Contains Box configurations.
 */
public class BoxConfig {

    private String clientId;
    private String clientSecret;
    private String enterpriseId;
    private JWTEncryptionPreferences jwtEncryptionPreferences;

    /**
     * Creates a configuration with a clientId and clientSecret.
     *
     * @param clientId     the client ID of the application
     * @param clientSecret the client secret of the application
     */
    public BoxConfig(String clientId, String clientSecret) {
        this.clientId = clientId;
        this.clientSecret = clientSecret;
    }

    /**
     * Creates a configuration with clientId, clientSecret and JWTEncryptionPreferences.
     *
     * @param clientId                 the client ID of the application
     * @param clientSecret             the client secret of the application
     * @param enterpriseId             the enterprise ID of the box account
     * @param jwtEncryptionPreferences the JWTEncryptionPreferences of the application
     */
    public BoxConfig(String clientId, String clientSecret, String enterpriseId,
                     JWTEncryptionPreferences jwtEncryptionPreferences) {
        this.clientId = clientId;
        this.clientSecret = clientSecret;
        this.enterpriseId = enterpriseId;
        this.jwtEncryptionPreferences = jwtEncryptionPreferences;
    }

    /**
     * Creates a configuration with clientId, clientSecret, publicKeyID, privateKey, privateKeyPassword.
     * and an encryptionAlgorithm.
     *
     * @param clientId            the client ID of the application
     * @param clientSecret        the client secret of the application
     * @param enterpriseId        the enterprise ID of the box account
     * @param publicKeyID         the unique ID of the uploaded public key
     * @param privateKey          the private key used to sign JWT requests
     * @param privateKeyPassword  the passphrase for the private key
     * @param encryptionAlgorithm the encryption algorithm that has to be used for signing JWT requests
     */
    public BoxConfig(String clientId, String clientSecret, String enterpriseId, String publicKeyID,
                     String privateKey, String privateKeyPassword, EncryptionAlgorithm encryptionAlgorithm) {
        this.clientId = clientId;
        this.clientSecret = clientSecret;
        this.enterpriseId = enterpriseId;
        this.jwtEncryptionPreferences = new JWTEncryptionPreferences();
        this.jwtEncryptionPreferences.setPublicKeyID(publicKeyID);
        this.jwtEncryptionPreferences.setPrivateKey(privateKey);
        this.jwtEncryptionPreferences.setPrivateKeyPassword(privateKeyPassword);
        this.jwtEncryptionPreferences.setEncryptionAlgorithm(encryptionAlgorithm);
    }

    /**
     * Creates a configuration with RSA_SHA_256 as the encryption algorithm.
     *
     * @param clientId           the client ID of the application
     * @param clientSecret       the client secret of the application
     * @param enterpriseId       the enterprise ID of the box account
     * @param publicKeyID        the unique ID of the uploaded public key
     * @param privateKey         the private key used to sign JWT requests
     * @param privateKeyPassword the passphrase for the private key
     */
    public BoxConfig(String clientId, String clientSecret, String enterpriseId, String publicKeyID,
                     String privateKey, String privateKeyPassword) {
        this.clientId = clientId;
        this.clientSecret = clientSecret;
        this.enterpriseId = enterpriseId;
        this.jwtEncryptionPreferences = new JWTEncryptionPreferences();
        this.jwtEncryptionPreferences.setPublicKeyID(publicKeyID);
        this.jwtEncryptionPreferences.setPrivateKey(privateKey);
        this.jwtEncryptionPreferences.setPrivateKeyPassword(privateKeyPassword);
        this.jwtEncryptionPreferences.setEncryptionAlgorithm(EncryptionAlgorithm.RSA_SHA_256);
    }

    /**
     * Reads OAuth 2.0 with JWT app configurations from the reader. The file should be in JSON format.
     *
     * @param reader a reader object which points to a JSON formatted configuration file
     * @return a new Instance of BoxConfig
     * @throws IOException when unable to access the mapping file's content of the reader
     */
    public static BoxConfig readFrom(Reader reader) throws IOException {
        JsonObject config = JsonObject.readFrom(reader);
        JsonObject settings = (JsonObject) config.get("boxAppSettings");
        String clientId = settings.get("clientID").asString();
        String clientSecret = settings.get("clientSecret").asString();
        JsonObject appAuth = (JsonObject) settings.get("appAuth");
        String publicKeyId = appAuth.get("publicKeyID").asString();
        String privateKey = appAuth.get("privateKey").asString();
        String passphrase = appAuth.get("passphrase").asString();
        String enterpriseId = config.get("enterpriseID").asString();
        return new BoxConfig(clientId, clientSecret, enterpriseId, publicKeyId, privateKey, passphrase);
    }

    /**
     *
     * @param clientId client ID of the Application
     */
    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    /**
     *
     * @return client secret
     */
    public String getClientSecret() {
        return this.clientSecret;
    }

    /**
     *
     * @param clientSecret client secret of the application
     */
    public void setClientSecret(String clientSecret) {
        this.clientSecret = clientSecret;
    }

    /**
     *
     * @return enterprise ID
     */
    public String getEnterpriseId() {
        return this.enterpriseId;
    }

    /**
     *
     * @param enterpriseId enterprise ID of the application
     */
    public void setEnterpriseId(String enterpriseId) {
        this.enterpriseId = enterpriseId;
    }

    /**
     *
     * @return JWT Encryption Preferences
     */
    public JWTEncryptionPreferences getJWTEncryptionPreferences() {
        return this.jwtEncryptionPreferences;
    }

    /**
     *
     * @return client ID
     */
    public String getClientId() {
        return this.clientId;
    }

    /**
     *
     * @param jwtEncryptionPreferences encryption preferences for JWT based authentication
     */
    public void setJWTEncryptionPreferences(JWTEncryptionPreferences jwtEncryptionPreferences) {
        this.jwtEncryptionPreferences = jwtEncryptionPreferences;
    }
}
