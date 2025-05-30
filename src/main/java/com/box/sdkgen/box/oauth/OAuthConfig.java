package com.box.sdkgen.box.oauth;

import com.box.sdkgen.box.tokenstorage.InMemoryTokenStorage;
import com.box.sdkgen.box.tokenstorage.TokenStorage;

public class OAuthConfig {

  public final String clientId;

  public final String clientSecret;

  public TokenStorage tokenStorage;

  public OAuthConfig(String clientId, String clientSecret) {
    this.clientId = clientId;
    this.clientSecret = clientSecret;
    this.tokenStorage = new InMemoryTokenStorage();
  }

  protected OAuthConfig(OAuthConfigBuilder builder) {
    this.clientId = builder.clientId;
    this.clientSecret = builder.clientSecret;
    this.tokenStorage = builder.tokenStorage;
  }

  public String getClientId() {
    return clientId;
  }

  public String getClientSecret() {
    return clientSecret;
  }

  public TokenStorage getTokenStorage() {
    return tokenStorage;
  }

  public static class OAuthConfigBuilder {

    protected final String clientId;

    protected final String clientSecret;

    protected TokenStorage tokenStorage;

    public OAuthConfigBuilder(String clientId, String clientSecret) {
      this.clientId = clientId;
      this.clientSecret = clientSecret;
      this.tokenStorage = new InMemoryTokenStorage();
    }

    public OAuthConfigBuilder tokenStorage(TokenStorage tokenStorage) {
      this.tokenStorage = tokenStorage;
      return this;
    }

    public OAuthConfig build() {
      return new OAuthConfig(this);
    }
  }
}
