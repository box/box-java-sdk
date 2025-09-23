package com.box.sdkgen.networking.proxyconfig;

public class ProxyConfig {

  public final String url;

  public String username;

  public String password;

  public ProxyConfig(String url) {
    this.url = url;
  }

  protected ProxyConfig(Builder builder) {
    this.url = builder.url;
    this.username = builder.username;
    this.password = builder.password;
  }

  public String getUrl() {
    return url;
  }

  public String getUsername() {
    return username;
  }

  public String getPassword() {
    return password;
  }

  public static class Builder {

    protected final String url;

    protected String username;

    protected String password;

    public Builder(String url) {
      this.url = url;
    }

    public Builder username(String username) {
      this.username = username;
      return this;
    }

    public Builder password(String password) {
      this.password = password;
      return this;
    }

    public ProxyConfig build() {
      return new ProxyConfig(this);
    }
  }
}
