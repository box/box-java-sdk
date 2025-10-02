package com.box.sdkgen.box.ccgauth;

import com.box.sdkgen.box.tokenstorage.InMemoryTokenStorage;
import com.box.sdkgen.box.tokenstorage.TokenStorage;

public class CCGConfig {

  /** Box API key used for identifying the application the user is authenticating with */
  public final String clientId;

  /** Box API secret used for making auth requests. */
  public final String clientSecret;

  /** The ID of the Box Developer Edition enterprise. */
  public String enterpriseId;

  /**
   * The user id to authenticate. This value is not required. But if it is provided, then the user
   * will be auto-authenticated at the time of the first API call.
   */
  public String userId;

  /**
   * Object responsible for storing token. If no custom implementation provided,the token will be
   * stored in memory.
   */
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
