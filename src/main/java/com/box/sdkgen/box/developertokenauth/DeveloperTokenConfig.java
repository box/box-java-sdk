package com.box.sdkgen.box.developertokenauth;

public class DeveloperTokenConfig {

  public String clientId;

  public String clientSecret;

  public DeveloperTokenConfig() {}

  protected DeveloperTokenConfig(DeveloperTokenConfigBuilder builder) {
    this.clientId = builder.clientId;
    this.clientSecret = builder.clientSecret;
  }

  public String getClientId() {
    return clientId;
  }

  public String getClientSecret() {
    return clientSecret;
  }

  public static class DeveloperTokenConfigBuilder {

    protected String clientId;

    protected String clientSecret;

    public DeveloperTokenConfigBuilder clientId(String clientId) {
      this.clientId = clientId;
      return this;
    }

    public DeveloperTokenConfigBuilder clientSecret(String clientSecret) {
      this.clientSecret = clientSecret;
      return this;
    }

    public DeveloperTokenConfig build() {
      return new DeveloperTokenConfig(this);
    }
  }
}
