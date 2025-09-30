package com.box.sdkgen.schemas.collaborationallowlistentry;

import com.box.sdkgen.internal.NullableFieldTracker;
import com.box.sdkgen.internal.SerializableObject;
import com.box.sdkgen.internal.utils.DateTimeUtils;
import com.box.sdkgen.serialization.json.EnumWrapper;
import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import java.time.OffsetDateTime;
import java.util.Objects;

@JsonFilter("nullablePropertyFilter")
public class CollaborationAllowlistEntry extends SerializableObject {

  protected String id;

  @JsonDeserialize(
      using =
          CollaborationAllowlistEntryTypeField.CollaborationAllowlistEntryTypeFieldDeserializer
              .class)
  @JsonSerialize(
      using =
          CollaborationAllowlistEntryTypeField.CollaborationAllowlistEntryTypeFieldSerializer.class)
  protected EnumWrapper<CollaborationAllowlistEntryTypeField> type;

  protected String domain;

  @JsonDeserialize(
      using =
          CollaborationAllowlistEntryDirectionField
              .CollaborationAllowlistEntryDirectionFieldDeserializer.class)
  @JsonSerialize(
      using =
          CollaborationAllowlistEntryDirectionField
              .CollaborationAllowlistEntryDirectionFieldSerializer.class)
  protected EnumWrapper<CollaborationAllowlistEntryDirectionField> direction;

  protected CollaborationAllowlistEntryEnterpriseField enterprise;

  @JsonProperty("created_at")
  @JsonSerialize(using = DateTimeUtils.DateTimeSerializer.class)
  @JsonDeserialize(using = DateTimeUtils.DateTimeDeserializer.class)
  protected OffsetDateTime createdAt;

  public CollaborationAllowlistEntry() {
    super();
  }

  protected CollaborationAllowlistEntry(Builder builder) {
    super();
    this.id = builder.id;
    this.type = builder.type;
    this.domain = builder.domain;
    this.direction = builder.direction;
    this.enterprise = builder.enterprise;
    this.createdAt = builder.createdAt;
    markNullableFieldsAsSet(builder.getExplicitlySetNullableFields());
  }

  public String getId() {
    return id;
  }

  public EnumWrapper<CollaborationAllowlistEntryTypeField> getType() {
    return type;
  }

  public String getDomain() {
    return domain;
  }

  public EnumWrapper<CollaborationAllowlistEntryDirectionField> getDirection() {
    return direction;
  }

  public CollaborationAllowlistEntryEnterpriseField getEnterprise() {
    return enterprise;
  }

  public OffsetDateTime getCreatedAt() {
    return createdAt;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    CollaborationAllowlistEntry casted = (CollaborationAllowlistEntry) o;
    return Objects.equals(id, casted.id)
        && Objects.equals(type, casted.type)
        && Objects.equals(domain, casted.domain)
        && Objects.equals(direction, casted.direction)
        && Objects.equals(enterprise, casted.enterprise)
        && Objects.equals(createdAt, casted.createdAt);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, type, domain, direction, enterprise, createdAt);
  }

  @Override
  public String toString() {
    return "CollaborationAllowlistEntry{"
        + "id='"
        + id
        + '\''
        + ", "
        + "type='"
        + type
        + '\''
        + ", "
        + "domain='"
        + domain
        + '\''
        + ", "
        + "direction='"
        + direction
        + '\''
        + ", "
        + "enterprise='"
        + enterprise
        + '\''
        + ", "
        + "createdAt='"
        + createdAt
        + '\''
        + "}";
  }

  public static class Builder extends NullableFieldTracker {

    protected String id;

    protected EnumWrapper<CollaborationAllowlistEntryTypeField> type;

    protected String domain;

    protected EnumWrapper<CollaborationAllowlistEntryDirectionField> direction;

    protected CollaborationAllowlistEntryEnterpriseField enterprise;

    protected OffsetDateTime createdAt;

    public Builder id(String id) {
      this.id = id;
      return this;
    }

    public Builder type(CollaborationAllowlistEntryTypeField type) {
      this.type = new EnumWrapper<CollaborationAllowlistEntryTypeField>(type);
      return this;
    }

    public Builder type(EnumWrapper<CollaborationAllowlistEntryTypeField> type) {
      this.type = type;
      return this;
    }

    public Builder domain(String domain) {
      this.domain = domain;
      return this;
    }

    public Builder direction(CollaborationAllowlistEntryDirectionField direction) {
      this.direction = new EnumWrapper<CollaborationAllowlistEntryDirectionField>(direction);
      return this;
    }

    public Builder direction(EnumWrapper<CollaborationAllowlistEntryDirectionField> direction) {
      this.direction = direction;
      return this;
    }

    public Builder enterprise(CollaborationAllowlistEntryEnterpriseField enterprise) {
      this.enterprise = enterprise;
      return this;
    }

    public Builder createdAt(OffsetDateTime createdAt) {
      this.createdAt = createdAt;
      return this;
    }

    public CollaborationAllowlistEntry build() {
      return new CollaborationAllowlistEntry(this);
    }
  }
}
