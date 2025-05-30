package com.box.sdkgen.internal.utils;

import com.box.sdkgen.serialization.json.Valuable;

public enum HashName implements Valuable {
  SHA1("sha1");

  private final String value;

  HashName(String value) {
    this.value = value;
  }

  public String getValue() {
    return value;
  }
}
