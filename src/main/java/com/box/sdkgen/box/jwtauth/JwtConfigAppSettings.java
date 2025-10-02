package com.box.sdkgen.box.jwtauth;

import com.box.sdkgen.internal.SerializableObject;
import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Objects;

@JsonFilter("nullablePropertyFilter")
public class JwtConfigAppSettings extends SerializableObject {

  /** App client ID */
  @JsonProperty("clientID")
  protected final String clientId;

  /** App client secret */
  protected final String clientSecret;

  /** App auth settings */
  protected final JwtConfigAppSettingsAppAuth appAuth;

  public JwtConfigAppSettings(
      @JsonProperty("clientID") String clientId,
      @JsonProperty("clientSecret") String clientSecret,
      @JsonProperty("appAuth") JwtConfigAppSettingsAppAuth appAuth) {
    super();
    this.clientId = clientId;
    this.clientSecret = clientSecret;
    this.appAuth = appAuth;
  }

  public String getClientId() {
    return clientId;
  }

  public String getClientSecret() {
    return clientSecret;
  }

  public JwtConfigAppSettingsAppAuth getAppAuth() {
    return appAuth;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    JwtConfigAppSettings casted = (JwtConfigAppSettings) o;
    return Objects.equals(clientId, casted.clientId)
        && Objects.equals(clientSecret, casted.clientSecret)
        && Objects.equals(appAuth, casted.appAuth);
  }

  @Override
  public int hashCode() {
    return Objects.hash(clientId, clientSecret, appAuth);
  }

  @Override
  public String toString() {
    return "JwtConfigAppSettings{"
        + "clientId='"
        + clientId
        + '\''
        + ", "
        + "clientSecret='"
        + clientSecret
        + '\''
        + ", "
        + "appAuth='"
        + appAuth
        + '\''
        + "}";
  }
}
