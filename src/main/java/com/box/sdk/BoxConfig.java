package com.box.sdk;

import com.eclipsesource.json.JsonObject;

import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.util.Properties;

/**
 *
 */
public class BoxConfig {

	private String clientId;
	private String clientSecret;
	private String enterpriseId;
	private JWTEncryptionPreferences jwtEncryptionPreferences;

	/**
	 * Reads configurations from the reader. The file should be in JSON format
	 * @param reader
	 * @throws IOException
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
	 * Creates a configuration with a clientId and clientSecret
	 * @param clientId
	 * @param clientSecret
	 */
	public BoxConfig(String clientId, String clientSecret) {
		this.clientId = clientId;
		this.clientSecret = clientSecret;
	}

	/**
	 * Creates a configuration with clientId, clientSecret and JWTEncryptionPreferences
	 * @param clientId
	 * @param clientSecret
	 * @param enterpriseId
	 * @param JWTEncryptionPreferences
	 */
	public BoxConfig(String clientId, String clientSecret, String enterpriseId, JWTEncryptionPreferences JWTEncryptionPreferences) {
		this.clientId = clientId;
		this.clientSecret = clientSecret;
		this.enterpriseId = enterpriseId;
		this.jwtEncryptionPreferences = JWTEncryptionPreferences;
	}

	/**
	 * Creates a configuration with clientId, clientSecret, publicKeyID, privateKey, privateKeyPassword and an encryptionAlgorithm
	 * @param clientId
	 * @param clientSecret
	 * @param enterpriseId
	 * @param publicKeyID
	 * @param privateKey
	 * @param privateKeyPassword
	 * @param encryptionAlgorithm
	 */
	public BoxConfig(String clientId, String clientSecret, String enterpriseId, String publicKeyID, String privateKey, String privateKeyPassword,
					 EncryptionAlgorithm encryptionAlgorithm) {
		this.clientId = clientId;
		this.clientSecret = clientSecret;
		this.enterpriseId = enterpriseId;
		this.jwtEncryptionPreferences = new JWTEncryptionPreferences();
		jwtEncryptionPreferences.setPublicKeyID(publicKeyID);
		jwtEncryptionPreferences.setPrivateKey(privateKey);
		jwtEncryptionPreferences.setPrivateKeyPassword(privateKey);
		jwtEncryptionPreferences.setEncryptionAlgorithm(encryptionAlgorithm);
	}

	/**
	 * Creates a configuration with RSA_SHA_256 as the encryption algorithm
	 * @param clientId
	 * @param clientSecret
	 * @param enterpriseId
	 * @param publicKeyID
	 * @param privateKey
	 * @param privateKeyPassword
	 */
	public BoxConfig(String clientId, String clientSecret, String enterpriseId, String publicKeyID, String privateKey, String privateKeyPassword) {
		this.clientId = clientId;
		this.clientSecret = clientSecret;
		this.enterpriseId = enterpriseId;
		this.jwtEncryptionPreferences = new JWTEncryptionPreferences();
		jwtEncryptionPreferences.setPublicKeyID(publicKeyID);
		jwtEncryptionPreferences.setPrivateKey(privateKey);
		jwtEncryptionPreferences.setPrivateKeyPassword(privateKeyPassword);
		jwtEncryptionPreferences.setEncryptionAlgorithm(EncryptionAlgorithm.RSA_SHA_256);
	}

	public void setClientId(String clientId) {
		this.clientId = clientId;
	}

	public String getClientSecret() {
		return clientSecret;
	}

	public void setClientSecret(String clientSecret) {
		this.clientSecret = clientSecret;
	}

	public String getEnterpriseId() {
		return enterpriseId;
	}

	public void setEnterpriseId(String enterpriseId) {
		this.enterpriseId = enterpriseId;
	}

	public JWTEncryptionPreferences getJWTEncryptionPreferences() {
		return jwtEncryptionPreferences;
	}

	public String getClientId() {
		return clientId;
	}

	public void setJWTEncryptionPreferences(JWTEncryptionPreferences JWTEncryptionPreferences) {
		this.jwtEncryptionPreferences = JWTEncryptionPreferences;
	}
}
