package com.box.sdkgen.internal.utils;

public class JwtKey {
  public final String key;
  public final String passphrase;

  public JwtKey(String key, String passphrase) {
    this.key = key;
    this.passphrase = passphrase;
  }

  public String getKey() {
    return key;
  }

  public String getPassphrase() {
    return passphrase;
  }
}
