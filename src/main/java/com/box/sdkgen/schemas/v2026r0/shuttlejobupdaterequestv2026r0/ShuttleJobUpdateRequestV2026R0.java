package com.box.sdkgen.schemas.v2026r0.shuttlejobupdaterequestv2026r0;

import com.box.sdkgen.internal.Nullable;
import com.box.sdkgen.internal.NullableFieldTracker;
import com.box.sdkgen.internal.SerializableObject;
import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import java.util.Objects;

/**
 * The request body used to update an existing shuttle job. Only the fields supplied in the request
 * body are changed.
 */
@JsonFilter("nullablePropertyFilter")
public class ShuttleJobUpdateRequestV2026R0 extends SerializableObject {

  /** The human-readable name of the shuttle job. */
  protected String name;

  /** An optional free-form comment describing the shuttle job. */
  @Nullable protected String comment;

  /** A list of free-form tags associated with the shuttle job. */
  protected List<String> tags;

  /**
   * The account mapping configuration that controls how source identities and permissions are
   * translated into Box.
   */
  @JsonProperty("account_mapping")
  protected ShuttleJobUpdateRequestV2026R0AccountMappingField accountMapping;

  /** The tunable settings that control how the shuttle job runs. */
  protected ShuttleJobUpdateRequestV2026R0SettingsField settings;

  public ShuttleJobUpdateRequestV2026R0() {
    super();
  }

  protected ShuttleJobUpdateRequestV2026R0(Builder builder) {
    super();
    this.name = builder.name;
    this.comment = builder.comment;
    this.tags = builder.tags;
    this.accountMapping = builder.accountMapping;
    this.settings = builder.settings;
    markNullableFieldsAsSet(builder.getExplicitlySetNullableFields());
  }

  public String getName() {
    return name;
  }

  public String getComment() {
    return comment;
  }

  public List<String> getTags() {
    return tags;
  }

  public ShuttleJobUpdateRequestV2026R0AccountMappingField getAccountMapping() {
    return accountMapping;
  }

  public ShuttleJobUpdateRequestV2026R0SettingsField getSettings() {
    return settings;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ShuttleJobUpdateRequestV2026R0 casted = (ShuttleJobUpdateRequestV2026R0) o;
    return Objects.equals(name, casted.name)
        && Objects.equals(comment, casted.comment)
        && Objects.equals(tags, casted.tags)
        && Objects.equals(accountMapping, casted.accountMapping)
        && Objects.equals(settings, casted.settings);
  }

  @Override
  public int hashCode() {
    return Objects.hash(name, comment, tags, accountMapping, settings);
  }

  @Override
  public String toString() {
    return "ShuttleJobUpdateRequestV2026R0{"
        + "name='"
        + name
        + '\''
        + ", "
        + "comment='"
        + comment
        + '\''
        + ", "
        + "tags='"
        + tags
        + '\''
        + ", "
        + "accountMapping='"
        + accountMapping
        + '\''
        + ", "
        + "settings='"
        + settings
        + '\''
        + "}";
  }

  public static class Builder extends NullableFieldTracker {

    protected String name;

    protected String comment;

    protected List<String> tags;

    protected ShuttleJobUpdateRequestV2026R0AccountMappingField accountMapping;

    protected ShuttleJobUpdateRequestV2026R0SettingsField settings;

    public Builder name(String name) {
      this.name = name;
      return this;
    }

    public Builder comment(String comment) {
      this.comment = comment;
      this.markNullableFieldAsSet("comment");
      return this;
    }

    public Builder tags(List<String> tags) {
      this.tags = tags;
      return this;
    }

    public Builder accountMapping(
        ShuttleJobUpdateRequestV2026R0AccountMappingField accountMapping) {
      this.accountMapping = accountMapping;
      return this;
    }

    public Builder settings(ShuttleJobUpdateRequestV2026R0SettingsField settings) {
      this.settings = settings;
      return this;
    }

    public ShuttleJobUpdateRequestV2026R0 build() {
      return new ShuttleJobUpdateRequestV2026R0(this);
    }
  }
}
