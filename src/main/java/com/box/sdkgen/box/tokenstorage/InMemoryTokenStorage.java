package com.box.sdkgen.box.tokenstorage;

import com.box.sdkgen.schemas.accesstoken.AccessToken;

public class InMemoryTokenStorage implements TokenStorage {
  private AccessToken token;

  public InMemoryTokenStorage() {
    this.token = null;
  }

  public InMemoryTokenStorage(AccessToken token) {
    this.token = token;
  }

  protected InMemoryTokenStorage(Builder builder) {
    this.token = builder.token;
  }

  @Override
  public void store(AccessToken token) {
    this.token = token;
  }

  @Override
  public AccessToken get() {
    return token;
  }

  @Override
  public void clear() {
    this.token = null;
  }

  public static class Builder {

    protected AccessToken token;

    public Builder token(AccessToken token) {
      this.token = token;
      return this;
    }

    public InMemoryTokenStorage build() {
      return new InMemoryTokenStorage(this);
    }
  }
}
