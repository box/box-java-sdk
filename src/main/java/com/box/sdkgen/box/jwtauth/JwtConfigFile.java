package com.box.sdkgen.box.jwtauth;

import com.box.sdkgen.internal.SerializableObject;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Objects;

public class JwtConfigFile extends SerializableObject {

  @JsonProperty("enterpriseID")
  protected String enterpriseId;

  @JsonProperty("userID")
  protected String userId;

  protected final JwtConfigAppSettings boxAppSettings;

  public JwtConfigFile(@JsonProperty("boxAppSettings") JwtConfigAppSettings boxAppSettings) {
    super();
    this.boxAppSettings = boxAppSettings;
  }

  protected JwtConfigFile(JwtConfigFileBuilder builder) {
    super();
    this.enterpriseId = builder.enterpriseId;
    this.userId = builder.userId;
    this.boxAppSettings = builder.boxAppSettings;
  }

  public String getEnterpriseId() {
    return enterpriseId;
  }

  public String getUserId() {
    return userId;
  }

  public JwtConfigAppSettings getBoxAppSettings() {
    return boxAppSettings;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    JwtConfigFile casted = (JwtConfigFile) o;
    return Objects.equals(enterpriseId, casted.enterpriseId)
        && Objects.equals(userId, casted.userId)
        && Objects.equals(boxAppSettings, casted.boxAppSettings);
  }

  @Override
  public int hashCode() {
    return Objects.hash(enterpriseId, userId, boxAppSettings);
  }

  @Override
  public String toString() {
    return "JwtConfigFile{"
        + "enterpriseId='"
        + enterpriseId
        + '\''
        + ", "
        + "userId='"
        + userId
        + '\''
        + ", "
        + "boxAppSettings='"
        + boxAppSettings
        + '\''
        + "}";
  }

  public static class JwtConfigFileBuilder {

    protected String enterpriseId;

    protected String userId;

    protected final JwtConfigAppSettings boxAppSettings;

    public JwtConfigFileBuilder(JwtConfigAppSettings boxAppSettings) {
      this.boxAppSettings = boxAppSettings;
    }

    public JwtConfigFileBuilder enterpriseId(String enterpriseId) {
      this.enterpriseId = enterpriseId;
      return this;
    }

    public JwtConfigFileBuilder userId(String userId) {
      this.userId = userId;
      return this;
    }

    public JwtConfigFile build() {
      return new JwtConfigFile(this);
    }
  }
}
