package com.box.sdkgen.schemas.v2025r0.enterprisefeaturesettingv2025r0;

import com.box.sdkgen.internal.Nullable;
import com.box.sdkgen.internal.NullableFieldTracker;
import com.box.sdkgen.internal.SerializableObject;
import com.box.sdkgen.schemas.v2025r0.userorgroupreferencev2025r0.UserOrGroupReferenceV2025R0;
import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import java.util.Objects;

/** An enterprise feature setting. */
@JsonFilter("nullablePropertyFilter")
public class EnterpriseFeatureSettingV2025R0 extends SerializableObject {

  /** The identifier of the enterprise feature setting. */
  @Nullable protected String id;

  /** The feature. */
  protected EnterpriseFeatureSettingV2025R0FeatureField feature;

  /** The state of the feature. */
  @Nullable protected String state;

  /** Whether the feature can be configured. */
  @JsonProperty("can_configure")
  @Nullable
  protected Boolean canConfigure;

  /** Whether the feature is configured. */
  @JsonProperty("is_configured")
  @Nullable
  protected Boolean isConfigured;

  /** Enterprise feature setting is enabled for only this set of users and groups. */
  @Nullable protected List<UserOrGroupReferenceV2025R0> allowlist;

  /** Enterprise feature setting is enabled for everyone except this set of users and groups. */
  @Nullable protected List<UserOrGroupReferenceV2025R0> denylist;

  public EnterpriseFeatureSettingV2025R0() {
    super();
  }

  protected EnterpriseFeatureSettingV2025R0(Builder builder) {
    super();
    this.id = builder.id;
    this.feature = builder.feature;
    this.state = builder.state;
    this.canConfigure = builder.canConfigure;
    this.isConfigured = builder.isConfigured;
    this.allowlist = builder.allowlist;
    this.denylist = builder.denylist;
    markNullableFieldsAsSet(builder.getExplicitlySetNullableFields());
  }

  public String getId() {
    return id;
  }

  public EnterpriseFeatureSettingV2025R0FeatureField getFeature() {
    return feature;
  }

  public String getState() {
    return state;
  }

  public Boolean getCanConfigure() {
    return canConfigure;
  }

  public Boolean getIsConfigured() {
    return isConfigured;
  }

  public List<UserOrGroupReferenceV2025R0> getAllowlist() {
    return allowlist;
  }

  public List<UserOrGroupReferenceV2025R0> getDenylist() {
    return denylist;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    EnterpriseFeatureSettingV2025R0 casted = (EnterpriseFeatureSettingV2025R0) o;
    return Objects.equals(id, casted.id)
        && Objects.equals(feature, casted.feature)
        && Objects.equals(state, casted.state)
        && Objects.equals(canConfigure, casted.canConfigure)
        && Objects.equals(isConfigured, casted.isConfigured)
        && Objects.equals(allowlist, casted.allowlist)
        && Objects.equals(denylist, casted.denylist);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, feature, state, canConfigure, isConfigured, allowlist, denylist);
  }

  @Override
  public String toString() {
    return "EnterpriseFeatureSettingV2025R0{"
        + "id='"
        + id
        + '\''
        + ", "
        + "feature='"
        + feature
        + '\''
        + ", "
        + "state='"
        + state
        + '\''
        + ", "
        + "canConfigure='"
        + canConfigure
        + '\''
        + ", "
        + "isConfigured='"
        + isConfigured
        + '\''
        + ", "
        + "allowlist='"
        + allowlist
        + '\''
        + ", "
        + "denylist='"
        + denylist
        + '\''
        + "}";
  }

  public static class Builder extends NullableFieldTracker {

    protected String id;

    protected EnterpriseFeatureSettingV2025R0FeatureField feature;

    protected String state;

    protected Boolean canConfigure;

    protected Boolean isConfigured;

    protected List<UserOrGroupReferenceV2025R0> allowlist;

    protected List<UserOrGroupReferenceV2025R0> denylist;

    public Builder id(String id) {
      this.id = id;
      this.markNullableFieldAsSet("id");
      return this;
    }

    public Builder feature(EnterpriseFeatureSettingV2025R0FeatureField feature) {
      this.feature = feature;
      return this;
    }

    public Builder state(String state) {
      this.state = state;
      this.markNullableFieldAsSet("state");
      return this;
    }

    public Builder canConfigure(Boolean canConfigure) {
      this.canConfigure = canConfigure;
      this.markNullableFieldAsSet("can_configure");
      return this;
    }

    public Builder isConfigured(Boolean isConfigured) {
      this.isConfigured = isConfigured;
      this.markNullableFieldAsSet("is_configured");
      return this;
    }

    public Builder allowlist(List<UserOrGroupReferenceV2025R0> allowlist) {
      this.allowlist = allowlist;
      this.markNullableFieldAsSet("allowlist");
      return this;
    }

    public Builder denylist(List<UserOrGroupReferenceV2025R0> denylist) {
      this.denylist = denylist;
      this.markNullableFieldAsSet("denylist");
      return this;
    }

    public EnterpriseFeatureSettingV2025R0 build() {
      return new EnterpriseFeatureSettingV2025R0(this);
    }
  }
}
