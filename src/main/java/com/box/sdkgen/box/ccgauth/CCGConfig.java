package com.box.sdkgen.box.ccgauth;

import com.box.sdkgen.box.tokenstorage.InMemoryTokenStorage;
import com.box.sdkgen.box.tokenstorage.TokenStorage;

public class CCGConfig {

  public final String clientId;

  public final String clientSecret;

  public String enterpriseId;

  public String userId;

  public TokenStorage tokenStorage;

  public CCGConfig(String clientId, String clientSecret) {
    this.clientId = clientId;
    this.clientSecret = clientSecret;
    this.tokenStorage = new InMemoryTokenStorage();
  }

  protected CCGConfig(Builder builder) {
    this.clientId = builder.clientId;
    this.clientSecret = builder.clientSecret;
    this.enterpriseId = builder.enterpriseId;
    this.userId = builder.userId;
    this.tokenStorage = builder.tokenStorage;
  }

  public String getClientId() {
    return clientId;
  }

  public String getClientSecret() {
    return clientSecret;
  }

  public String getEnterpriseId() {
    return enterpriseId;
  }

  public String getUserId() {
    return userId;
  }

  public TokenStorage getTokenStorage() {
    return tokenStorage;
  }

  public static class Builder {

    protected final String clientId;

    protected final String clientSecret;

    protected String enterpriseId;

    protected String userId;

    protected TokenStorage tokenStorage;

    public Builder(String clientId, String clientSecret) {
      this.clientId = clientId;
      this.clientSecret = clientSecret;
      this.tokenStorage = new InMemoryTokenStorage();
    }

    public Builder enterpriseId(String enterpriseId) {
      this.enterpriseId = enterpriseId;
      return this;
    }

    public Builder userId(String userId) {
      this.userId = userId;
      return this;
    }

    public Builder tokenStorage(TokenStorage tokenStorage) {
      this.tokenStorage = tokenStorage;
      return this;
    }

    public CCGConfig build() {
      return new CCGConfig(this);
    }
  }
}
