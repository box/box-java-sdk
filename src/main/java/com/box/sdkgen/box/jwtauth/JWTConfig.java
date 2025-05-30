package com.box.sdkgen.box.jwtauth;

import static com.box.sdkgen.internal.utils.UtilsManager.readTextFromFile;
import static com.box.sdkgen.serialization.json.JsonManager.jsonToSerializedData;

import com.box.sdkgen.box.tokenstorage.InMemoryTokenStorage;
import com.box.sdkgen.box.tokenstorage.TokenStorage;
import com.box.sdkgen.internal.utils.JwtAlgorithm;
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
  }

  protected JWTConfig(JWTConfigBuilder builder) {
    this.clientId = builder.clientId;
    this.clientSecret = builder.clientSecret;
    this.jwtKeyId = builder.jwtKeyId;
    this.privateKey = builder.privateKey;
    this.privateKeyPassphrase = builder.privateKeyPassphrase;
    this.enterpriseId = builder.enterpriseId;
    this.userId = builder.userId;
    this.algorithm = builder.algorithm;
    this.tokenStorage = builder.tokenStorage;
  }

  public static JWTConfig fromConfigJsonString(String configJsonString) {
    return fromConfigJsonString(configJsonString, null);
  }

  public static JWTConfig fromConfigJsonString(String configJsonString, TokenStorage tokenStorage) {
    JwtConfigFile configJson =
        JsonManager.deserialize(jsonToSerializedData(configJsonString), JwtConfigFile.class);
    JWTConfig newConfig =
        (!(tokenStorage == null)
            ? new JWTConfig.JWTConfigBuilder(
                    configJson.getBoxAppSettings().getClientId(),
                    configJson.getBoxAppSettings().getClientSecret(),
                    configJson.getBoxAppSettings().getAppAuth().getPublicKeyId(),
                    configJson.getBoxAppSettings().getAppAuth().getPrivateKey(),
                    configJson.getBoxAppSettings().getAppAuth().getPassphrase())
                .enterpriseId(configJson.getEnterpriseId())
                .userId(configJson.getUserId())
                .tokenStorage(tokenStorage)
                .build()
            : new JWTConfig.JWTConfigBuilder(
                    configJson.getBoxAppSettings().getClientId(),
                    configJson.getBoxAppSettings().getClientSecret(),
                    configJson.getBoxAppSettings().getAppAuth().getPublicKeyId(),
                    configJson.getBoxAppSettings().getAppAuth().getPrivateKey(),
                    configJson.getBoxAppSettings().getAppAuth().getPassphrase())
                .enterpriseId(configJson.getEnterpriseId())
                .userId(configJson.getUserId())
                .build());
    return newConfig;
  }

  public static JWTConfig fromConfigFile(String configFilePath) {
    return fromConfigFile(configFilePath, null);
  }

  public static JWTConfig fromConfigFile(String configFilePath, TokenStorage tokenStorage) {
    String configJsonString = readTextFromFile(configFilePath);
    return JWTConfig.fromConfigJsonString(configJsonString, tokenStorage);
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

  public static class JWTConfigBuilder {

    protected final String clientId;

    protected final String clientSecret;

    protected final String jwtKeyId;

    protected final String privateKey;

    protected final String privateKeyPassphrase;

    protected String enterpriseId;

    protected String userId;

    protected EnumWrapper<JwtAlgorithm> algorithm;

    protected TokenStorage tokenStorage;

    public JWTConfigBuilder(
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
    }

    public JWTConfigBuilder enterpriseId(String enterpriseId) {
      this.enterpriseId = enterpriseId;
      return this;
    }

    public JWTConfigBuilder userId(String userId) {
      this.userId = userId;
      return this;
    }

    public JWTConfigBuilder algorithm(JwtAlgorithm algorithm) {
      this.algorithm = new EnumWrapper<JwtAlgorithm>(algorithm);
      return this;
    }

    public JWTConfigBuilder algorithm(EnumWrapper<JwtAlgorithm> algorithm) {
      this.algorithm = algorithm;
      return this;
    }

    public JWTConfigBuilder tokenStorage(TokenStorage tokenStorage) {
      this.tokenStorage = tokenStorage;
      return this;
    }

    public JWTConfig build() {
      return new JWTConfig(this);
    }
  }
}
