package com.box.sdkgen.box.jwtauth;

import static com.box.sdkgen.internal.utils.UtilsManager.readTextFromFile;
import static com.box.sdkgen.serialization.json.JsonManager.jsonToSerializedData;

import com.box.sdkgen.box.tokenstorage.InMemoryTokenStorage;
import com.box.sdkgen.box.tokenstorage.TokenStorage;
import com.box.sdkgen.internal.utils.DefaultPrivateKeyDecryptor;
import com.box.sdkgen.internal.utils.JwtAlgorithm;
import com.box.sdkgen.internal.utils.PrivateKeyDecryptor;
import com.box.sdkgen.serialization.json.EnumWrapper;
import com.box.sdkgen.serialization.json.JsonManager;

public class JWTConfig {

  public final String clientId;

  public final String clientSecret;

  public final String jwtKeyId;

  public final String privateKey;

  public final String privateKeyPassphrase;

  public String enterpriseId;

  public String userId;

  public EnumWrapper<JwtAlgorithm> algorithm;

  public TokenStorage tokenStorage;

  public PrivateKeyDecryptor privateKeyDecryptor;

  public JWTConfig(
      String clientId,
      String clientSecret,
      String jwtKeyId,
      String privateKey,
      String privateKeyPassphrase) {
    this.clientId = clientId;
    this.clientSecret = clientSecret;
    this.jwtKeyId = jwtKeyId;
    this.privateKey = privateKey;
    this.privateKeyPassphrase = privateKeyPassphrase;
    this.algorithm = new EnumWrapper<JwtAlgorithm>(JwtAlgorithm.RS256);
    this.tokenStorage = new InMemoryTokenStorage();
    this.privateKeyDecryptor = new DefaultPrivateKeyDecryptor();
  }

  protected JWTConfig(Builder builder) {
    this.clientId = builder.clientId;
    this.clientSecret = builder.clientSecret;
    this.jwtKeyId = builder.jwtKeyId;
    this.privateKey = builder.privateKey;
    this.privateKeyPassphrase = builder.privateKeyPassphrase;
    this.enterpriseId = builder.enterpriseId;
    this.userId = builder.userId;
    this.algorithm = builder.algorithm;
    this.tokenStorage = builder.tokenStorage;
    this.privateKeyDecryptor = builder.privateKeyDecryptor;
  }

  public static JWTConfig fromConfigJsonString(String configJsonString) {
    return fromConfigJsonString(configJsonString, null, null);
  }

  public static JWTConfig fromConfigJsonString(String configJsonString, TokenStorage tokenStorage) {
    return fromConfigJsonString(configJsonString, tokenStorage, null);
  }

  public static JWTConfig fromConfigJsonString(
      String configJsonString, PrivateKeyDecryptor privateKeyDecryptor) {
    return fromConfigJsonString(configJsonString, null, privateKeyDecryptor);
  }

  public static JWTConfig fromConfigJsonString(
      String configJsonString, TokenStorage tokenStorage, PrivateKeyDecryptor privateKeyDecryptor) {
    JwtConfigFile configJson =
        JsonManager.deserialize(jsonToSerializedData(configJsonString), JwtConfigFile.class);
    TokenStorage tokenStorageToUse =
        (tokenStorage == null ? new InMemoryTokenStorage() : tokenStorage);
    PrivateKeyDecryptor privateKeyDecryptorToUse =
        (privateKeyDecryptor == null ? new DefaultPrivateKeyDecryptor() : privateKeyDecryptor);
    JWTConfig newConfig =
        new JWTConfig.Builder(
                configJson.getBoxAppSettings().getClientId(),
                configJson.getBoxAppSettings().getClientSecret(),
                configJson.getBoxAppSettings().getAppAuth().getPublicKeyId(),
                configJson.getBoxAppSettings().getAppAuth().getPrivateKey(),
                configJson.getBoxAppSettings().getAppAuth().getPassphrase())
            .enterpriseId(configJson.getEnterpriseId())
            .userId(configJson.getUserId())
            .tokenStorage(tokenStorageToUse)
            .privateKeyDecryptor(privateKeyDecryptorToUse)
            .build();
    return newConfig;
  }

  public static JWTConfig fromConfigFile(String configFilePath) {
    return fromConfigFile(configFilePath, null, null);
  }

  public static JWTConfig fromConfigFile(String configFilePath, TokenStorage tokenStorage) {
    return fromConfigFile(configFilePath, tokenStorage, null);
  }

  public static JWTConfig fromConfigFile(
      String configFilePath, PrivateKeyDecryptor privateKeyDecryptor) {
    return fromConfigFile(configFilePath, null, privateKeyDecryptor);
  }

  public static JWTConfig fromConfigFile(
      String configFilePath, TokenStorage tokenStorage, PrivateKeyDecryptor privateKeyDecryptor) {
    String configJsonString = readTextFromFile(configFilePath);
    return JWTConfig.fromConfigJsonString(configJsonString, tokenStorage, privateKeyDecryptor);
  }

  public String getClientId() {
    return clientId;
  }

  public String getClientSecret() {
    return clientSecret;
  }

  public String getJwtKeyId() {
    return jwtKeyId;
  }

  public String getPrivateKey() {
    return privateKey;
  }

  public String getPrivateKeyPassphrase() {
    return privateKeyPassphrase;
  }

  public String getEnterpriseId() {
    return enterpriseId;
  }

  public String getUserId() {
    return userId;
  }

  public EnumWrapper<JwtAlgorithm> getAlgorithm() {
    return algorithm;
  }

  public TokenStorage getTokenStorage() {
    return tokenStorage;
  }

  public PrivateKeyDecryptor getPrivateKeyDecryptor() {
    return privateKeyDecryptor;
  }

  public static class Builder {

    protected final String clientId;

    protected final String clientSecret;

    protected final String jwtKeyId;

    protected final String privateKey;

    protected final String privateKeyPassphrase;

    protected String enterpriseId;

    protected String userId;

    protected EnumWrapper<JwtAlgorithm> algorithm;

    protected TokenStorage tokenStorage;

    protected PrivateKeyDecryptor privateKeyDecryptor;

    public Builder(
        String clientId,
        String clientSecret,
        String jwtKeyId,
        String privateKey,
        String privateKeyPassphrase) {
      this.clientId = clientId;
      this.clientSecret = clientSecret;
      this.jwtKeyId = jwtKeyId;
      this.privateKey = privateKey;
      this.privateKeyPassphrase = privateKeyPassphrase;
      this.algorithm = new EnumWrapper<JwtAlgorithm>(JwtAlgorithm.RS256);
      this.tokenStorage = new InMemoryTokenStorage();
      this.privateKeyDecryptor = new DefaultPrivateKeyDecryptor();
    }

    public Builder enterpriseId(String enterpriseId) {
      this.enterpriseId = enterpriseId;
      return this;
    }

    public Builder userId(String userId) {
      this.userId = userId;
      return this;
    }

    public Builder algorithm(JwtAlgorithm algorithm) {
      this.algorithm = new EnumWrapper<JwtAlgorithm>(algorithm);
      return this;
    }

    public Builder algorithm(EnumWrapper<JwtAlgorithm> algorithm) {
      this.algorithm = algorithm;
      return this;
    }

    public Builder tokenStorage(TokenStorage tokenStorage) {
      this.tokenStorage = tokenStorage;
      return this;
    }

    public Builder privateKeyDecryptor(PrivateKeyDecryptor privateKeyDecryptor) {
      this.privateKeyDecryptor = privateKeyDecryptor;
      return this;
    }

    public JWTConfig build() {
      return new JWTConfig(this);
    }
  }
}
