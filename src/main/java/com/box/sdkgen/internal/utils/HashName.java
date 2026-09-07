package com.box.sdkgen.internal.utils;

import com.box.sdkgen.serialization.json.Valuable;

public enum HashName implements Valuable {
  SHA1("sha1"),
  SHA512("sha512");

  private final String value;

  HashName(String value) {
    this.value = value;
  }

  public String getValue() {
    return value;
  }
}
