package com.box.sdkgen.schemas.v2025r0.keysafesettingsv2025r0;

import com.box.sdkgen.internal.Nullable;
import com.box.sdkgen.internal.NullableFieldTracker;
import com.box.sdkgen.internal.SerializableObject;
import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Objects;

/** The KeySafe settings. */
@JsonFilter("nullablePropertyFilter")
public class KeysafeSettingsV2025R0 extends SerializableObject {

  /** Whether KeySafe addon is enabled for the enterprise. */
  @JsonProperty("keysafe_enabled")
  protected Boolean keysafeEnabled;

  /** The cloud provider. */
  @JsonProperty("cloud_provider")
  @Nullable
  protected String cloudProvider;

  /** The key ID. */
  @JsonProperty("key_id")
  @Nullable
  protected String keyId;

  /** The account ID. */
  @JsonProperty("account_id")
  @Nullable
  protected String accountId;

  /** The location ID. */
  @JsonProperty("location_id")
  @Nullable
  protected String locationId;

  /** The project ID. */
  @JsonProperty("project_id")
  @Nullable
  protected String projectId;

  /** The key ring ID. */
  @JsonProperty("keyring_id")
  @Nullable
  protected String keyringId;

  public KeysafeSettingsV2025R0() {
    super();
  }

  protected KeysafeSettingsV2025R0(Builder builder) {
    super();
    this.keysafeEnabled = builder.keysafeEnabled;
    this.cloudProvider = builder.cloudProvider;
    this.keyId = builder.keyId;
    this.accountId = builder.accountId;
    this.locationId = builder.locationId;
    this.projectId = builder.projectId;
    this.keyringId = builder.keyringId;
    markNullableFieldsAsSet(builder.getExplicitlySetNullableFields());
  }

  public Boolean getKeysafeEnabled() {
    return keysafeEnabled;
  }

  public String getCloudProvider() {
    return cloudProvider;
  }

  public String getKeyId() {
    return keyId;
  }

  public String getAccountId() {
    return accountId;
  }

  public String getLocationId() {
    return locationId;
  }

  public String getProjectId() {
    return projectId;
  }

  public String getKeyringId() {
    return keyringId;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    KeysafeSettingsV2025R0 casted = (KeysafeSettingsV2025R0) o;
    return Objects.equals(keysafeEnabled, casted.keysafeEnabled)
        && Objects.equals(cloudProvider, casted.cloudProvider)
        && Objects.equals(keyId, casted.keyId)
        && Objects.equals(accountId, casted.accountId)
        && Objects.equals(locationId, casted.locationId)
        && Objects.equals(projectId, casted.projectId)
        && Objects.equals(keyringId, casted.keyringId);
  }

  @Override
  public int hashCode() {
    return Objects.hash(
        keysafeEnabled, cloudProvider, keyId, accountId, locationId, projectId, keyringId);
  }

  @Override
  public String toString() {
    return "KeysafeSettingsV2025R0{"
        + "keysafeEnabled='"
        + keysafeEnabled
        + '\''
        + ", "
        + "cloudProvider='"
        + cloudProvider
        + '\''
        + ", "
        + "keyId='"
        + keyId
        + '\''
        + ", "
        + "accountId='"
        + accountId
        + '\''
        + ", "
        + "locationId='"
        + locationId
        + '\''
        + ", "
        + "projectId='"
        + projectId
        + '\''
        + ", "
        + "keyringId='"
        + keyringId
        + '\''
        + "}";
  }

  public static class Builder extends NullableFieldTracker {

    protected Boolean keysafeEnabled;

    protected String cloudProvider;

    protected String keyId;

    protected String accountId;

    protected String locationId;

    protected String projectId;

    protected String keyringId;

    public Builder keysafeEnabled(Boolean keysafeEnabled) {
      this.keysafeEnabled = keysafeEnabled;
      return this;
    }

    public Builder cloudProvider(String cloudProvider) {
      this.cloudProvider = cloudProvider;
      this.markNullableFieldAsSet("cloud_provider");
      return this;
    }

    public Builder keyId(String keyId) {
      this.keyId = keyId;
      this.markNullableFieldAsSet("key_id");
      return this;
    }

    public Builder accountId(String accountId) {
      this.accountId = accountId;
      this.markNullableFieldAsSet("account_id");
      return this;
    }

    public Builder locationId(String locationId) {
      this.locationId = locationId;
      this.markNullableFieldAsSet("location_id");
      return this;
    }

    public Builder projectId(String projectId) {
      this.projectId = projectId;
      this.markNullableFieldAsSet("project_id");
      return this;
    }

    public Builder keyringId(String keyringId) {
      this.keyringId = keyringId;
      this.markNullableFieldAsSet("keyring_id");
      return this;
    }

    public KeysafeSettingsV2025R0 build() {
      return new KeysafeSettingsV2025R0(this);
    }
  }
}
