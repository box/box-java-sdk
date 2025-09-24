package com.box.sdkgen.box.jwtauth;

import com.box.sdkgen.internal.SerializableObject;
import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Objects;

@JsonFilter("nullablePropertyFilter")
public class JwtConfigAppSettingsAppAuth extends SerializableObject {

  @JsonProperty("publicKeyID")
  protected final String publicKeyId;

  protected final String privateKey;

  protected final String passphrase;

  public JwtConfigAppSettingsAppAuth(
      @JsonProperty("publicKeyID") String publicKeyId,
      @JsonProperty("privateKey") String privateKey,
      @JsonProperty("passphrase") String passphrase) {
    super();
    this.publicKeyId = publicKeyId;
    this.privateKey = privateKey;
    this.passphrase = passphrase;
  }

  public String getPublicKeyId() {
    return publicKeyId;
  }

  public String getPrivateKey() {
    return privateKey;
  }

  public String getPassphrase() {
    return passphrase;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    JwtConfigAppSettingsAppAuth casted = (JwtConfigAppSettingsAppAuth) o;
    return Objects.equals(publicKeyId, casted.publicKeyId)
        && Objects.equals(privateKey, casted.privateKey)
        && Objects.equals(passphrase, casted.passphrase);
  }

  @Override
  public int hashCode() {
    return Objects.hash(publicKeyId, privateKey, passphrase);
  }

  @Override
  public String toString() {
    return "JwtConfigAppSettingsAppAuth{"
        + "publicKeyId='"
        + publicKeyId
        + '\''
        + ", "
        + "privateKey='"
        + privateKey
        + '\''
        + ", "
        + "passphrase='"
        + passphrase
        + '\''
        + "}";
  }
}
