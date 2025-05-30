package com.box.sdkgen.schemas.aisingleagentresponse;

import com.box.sdkgen.internal.SerializableObject;
import com.box.sdkgen.schemas.aiagentallowedentity.AiAgentAllowedEntity;
import com.box.sdkgen.schemas.userbase.UserBase;
import com.box.sdkgen.serialization.json.EnumWrapper;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import java.util.List;
import java.util.Objects;

public class AiSingleAgentResponse extends SerializableObject {

  protected final String id;

  @JsonDeserialize(
      using = AiSingleAgentResponseTypeField.AiSingleAgentResponseTypeFieldDeserializer.class)
  @JsonSerialize(
      using = AiSingleAgentResponseTypeField.AiSingleAgentResponseTypeFieldSerializer.class)
  protected EnumWrapper<AiSingleAgentResponseTypeField> type;

  protected final String origin;

  protected final String name;

  @JsonProperty("access_state")
  protected final String accessState;

  @JsonProperty("created_by")
  protected UserBase createdBy;

  @JsonProperty("created_at")
  protected String createdAt;

  @JsonProperty("modified_by")
  protected UserBase modifiedBy;

  @JsonProperty("modified_at")
  protected String modifiedAt;

  @JsonProperty("icon_reference")
  protected String iconReference;

  @JsonProperty("allowed_entities")
  protected List<AiAgentAllowedEntity> allowedEntities;

  public AiSingleAgentResponse(
      @JsonProperty("id") String id,
      @JsonProperty("origin") String origin,
      @JsonProperty("name") String name,
      @JsonProperty("access_state") String accessState) {
    super();
    this.id = id;
    this.origin = origin;
    this.name = name;
    this.accessState = accessState;
  }

  protected AiSingleAgentResponse(AiSingleAgentResponseBuilder builder) {
    super();
    this.id = builder.id;
    this.type = builder.type;
    this.origin = builder.origin;
    this.name = builder.name;
    this.accessState = builder.accessState;
    this.createdBy = builder.createdBy;
    this.createdAt = builder.createdAt;
    this.modifiedBy = builder.modifiedBy;
    this.modifiedAt = builder.modifiedAt;
    this.iconReference = builder.iconReference;
    this.allowedEntities = builder.allowedEntities;
  }

  public String getId() {
    return id;
  }

  public EnumWrapper<AiSingleAgentResponseTypeField> getType() {
    return type;
  }

  public String getOrigin() {
    return origin;
  }

  public String getName() {
    return name;
  }

  public String getAccessState() {
    return accessState;
  }

  public UserBase getCreatedBy() {
    return createdBy;
  }

  public String getCreatedAt() {
    return createdAt;
  }

  public UserBase getModifiedBy() {
    return modifiedBy;
  }

  public String getModifiedAt() {
    return modifiedAt;
  }

  public String getIconReference() {
    return iconReference;
  }

  public List<AiAgentAllowedEntity> getAllowedEntities() {
    return allowedEntities;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    AiSingleAgentResponse casted = (AiSingleAgentResponse) o;
    return Objects.equals(id, casted.id)
        && Objects.equals(type, casted.type)
        && Objects.equals(origin, casted.origin)
        && Objects.equals(name, casted.name)
        && Objects.equals(accessState, casted.accessState)
        && Objects.equals(createdBy, casted.createdBy)
        && Objects.equals(createdAt, casted.createdAt)
        && Objects.equals(modifiedBy, casted.modifiedBy)
        && Objects.equals(modifiedAt, casted.modifiedAt)
        && Objects.equals(iconReference, casted.iconReference)
        && Objects.equals(allowedEntities, casted.allowedEntities);
  }

  @Override
  public int hashCode() {
    return Objects.hash(
        id,
        type,
        origin,
        name,
        accessState,
        createdBy,
        createdAt,
        modifiedBy,
        modifiedAt,
        iconReference,
        allowedEntities);
  }

  @Override
  public String toString() {
    return "AiSingleAgentResponse{"
        + "id='"
        + id
        + '\''
        + ", "
        + "type='"
        + type
        + '\''
        + ", "
        + "origin='"
        + origin
        + '\''
        + ", "
        + "name='"
        + name
        + '\''
        + ", "
        + "accessState='"
        + accessState
        + '\''
        + ", "
        + "createdBy='"
        + createdBy
        + '\''
        + ", "
        + "createdAt='"
        + createdAt
        + '\''
        + ", "
        + "modifiedBy='"
        + modifiedBy
        + '\''
        + ", "
        + "modifiedAt='"
        + modifiedAt
        + '\''
        + ", "
        + "iconReference='"
        + iconReference
        + '\''
        + ", "
        + "allowedEntities='"
        + allowedEntities
        + '\''
        + "}";
  }

  public static class AiSingleAgentResponseBuilder {

    protected final String id;

    protected EnumWrapper<AiSingleAgentResponseTypeField> type;

    protected final String origin;

    protected final String name;

    protected final String accessState;

    protected UserBase createdBy;

    protected String createdAt;

    protected UserBase modifiedBy;

    protected String modifiedAt;

    protected String iconReference;

    protected List<AiAgentAllowedEntity> allowedEntities;

    public AiSingleAgentResponseBuilder(String id, String origin, String name, String accessState) {
      this.id = id;
      this.origin = origin;
      this.name = name;
      this.accessState = accessState;
    }

    public AiSingleAgentResponseBuilder type(AiSingleAgentResponseTypeField type) {
      this.type = new EnumWrapper<AiSingleAgentResponseTypeField>(type);
      return this;
    }

    public AiSingleAgentResponseBuilder type(EnumWrapper<AiSingleAgentResponseTypeField> type) {
      this.type = type;
      return this;
    }

    public AiSingleAgentResponseBuilder createdBy(UserBase createdBy) {
      this.createdBy = createdBy;
      return this;
    }

    public AiSingleAgentResponseBuilder createdAt(String createdAt) {
      this.createdAt = createdAt;
      return this;
    }

    public AiSingleAgentResponseBuilder modifiedBy(UserBase modifiedBy) {
      this.modifiedBy = modifiedBy;
      return this;
    }

    public AiSingleAgentResponseBuilder modifiedAt(String modifiedAt) {
      this.modifiedAt = modifiedAt;
      return this;
    }

    public AiSingleAgentResponseBuilder iconReference(String iconReference) {
      this.iconReference = iconReference;
      return this;
    }

    public AiSingleAgentResponseBuilder allowedEntities(
        List<AiAgentAllowedEntity> allowedEntities) {
      this.allowedEntities = allowedEntities;
      return this;
    }

    public AiSingleAgentResponse build() {
      return new AiSingleAgentResponse(this);
    }
  }
}
