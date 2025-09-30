package com.box.sdkgen.managers.groups;

import com.box.sdkgen.internal.NullableFieldTracker;
import com.box.sdkgen.internal.SerializableObject;
import com.box.sdkgen.serialization.json.EnumWrapper;
import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import java.util.Objects;

@JsonFilter("nullablePropertyFilter")
public class CreateGroupRequestBody extends SerializableObject {

  protected final String name;

  protected String provenance;

  @JsonProperty("external_sync_identifier")
  protected String externalSyncIdentifier;

  protected String description;

  @JsonDeserialize(
      using =
          CreateGroupRequestBodyInvitabilityLevelField
              .CreateGroupRequestBodyInvitabilityLevelFieldDeserializer.class)
  @JsonSerialize(
      using =
          CreateGroupRequestBodyInvitabilityLevelField
              .CreateGroupRequestBodyInvitabilityLevelFieldSerializer.class)
  @JsonProperty("invitability_level")
  protected EnumWrapper<CreateGroupRequestBodyInvitabilityLevelField> invitabilityLevel;

  @JsonDeserialize(
      using =
          CreateGroupRequestBodyMemberViewabilityLevelField
              .CreateGroupRequestBodyMemberViewabilityLevelFieldDeserializer.class)
  @JsonSerialize(
      using =
          CreateGroupRequestBodyMemberViewabilityLevelField
              .CreateGroupRequestBodyMemberViewabilityLevelFieldSerializer.class)
  @JsonProperty("member_viewability_level")
  protected EnumWrapper<CreateGroupRequestBodyMemberViewabilityLevelField> memberViewabilityLevel;

  public CreateGroupRequestBody(@JsonProperty("name") String name) {
    super();
    this.name = name;
  }

  protected CreateGroupRequestBody(Builder builder) {
    super();
    this.name = builder.name;
    this.provenance = builder.provenance;
    this.externalSyncIdentifier = builder.externalSyncIdentifier;
    this.description = builder.description;
    this.invitabilityLevel = builder.invitabilityLevel;
    this.memberViewabilityLevel = builder.memberViewabilityLevel;
    markNullableFieldsAsSet(builder.getExplicitlySetNullableFields());
  }

  public String getName() {
    return name;
  }

  public String getProvenance() {
    return provenance;
  }

  public String getExternalSyncIdentifier() {
    return externalSyncIdentifier;
  }

  public String getDescription() {
    return description;
  }

  public EnumWrapper<CreateGroupRequestBodyInvitabilityLevelField> getInvitabilityLevel() {
    return invitabilityLevel;
  }

  public EnumWrapper<CreateGroupRequestBodyMemberViewabilityLevelField>
      getMemberViewabilityLevel() {
    return memberViewabilityLevel;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    CreateGroupRequestBody casted = (CreateGroupRequestBody) o;
    return Objects.equals(name, casted.name)
        && Objects.equals(provenance, casted.provenance)
        && Objects.equals(externalSyncIdentifier, casted.externalSyncIdentifier)
        && Objects.equals(description, casted.description)
        && Objects.equals(invitabilityLevel, casted.invitabilityLevel)
        && Objects.equals(memberViewabilityLevel, casted.memberViewabilityLevel);
  }

  @Override
  public int hashCode() {
    return Objects.hash(
        name,
        provenance,
        externalSyncIdentifier,
        description,
        invitabilityLevel,
        memberViewabilityLevel);
  }

  @Override
  public String toString() {
    return "CreateGroupRequestBody{"
        + "name='"
        + name
        + '\''
        + ", "
        + "provenance='"
        + provenance
        + '\''
        + ", "
        + "externalSyncIdentifier='"
        + externalSyncIdentifier
        + '\''
        + ", "
        + "description='"
        + description
        + '\''
        + ", "
        + "invitabilityLevel='"
        + invitabilityLevel
        + '\''
        + ", "
        + "memberViewabilityLevel='"
        + memberViewabilityLevel
        + '\''
        + "}";
  }

  public static class Builder extends NullableFieldTracker {

    protected final String name;

    protected String provenance;

    protected String externalSyncIdentifier;

    protected String description;

    protected EnumWrapper<CreateGroupRequestBodyInvitabilityLevelField> invitabilityLevel;

    protected EnumWrapper<CreateGroupRequestBodyMemberViewabilityLevelField> memberViewabilityLevel;

    public Builder(String name) {
      super();
      this.name = name;
    }

    public Builder provenance(String provenance) {
      this.provenance = provenance;
      return this;
    }

    public Builder externalSyncIdentifier(String externalSyncIdentifier) {
      this.externalSyncIdentifier = externalSyncIdentifier;
      return this;
    }

    public Builder description(String description) {
      this.description = description;
      return this;
    }

    public Builder invitabilityLevel(
        CreateGroupRequestBodyInvitabilityLevelField invitabilityLevel) {
      this.invitabilityLevel =
          new EnumWrapper<CreateGroupRequestBodyInvitabilityLevelField>(invitabilityLevel);
      return this;
    }

    public Builder invitabilityLevel(
        EnumWrapper<CreateGroupRequestBodyInvitabilityLevelField> invitabilityLevel) {
      this.invitabilityLevel = invitabilityLevel;
      return this;
    }

    public Builder memberViewabilityLevel(
        CreateGroupRequestBodyMemberViewabilityLevelField memberViewabilityLevel) {
      this.memberViewabilityLevel =
          new EnumWrapper<CreateGroupRequestBodyMemberViewabilityLevelField>(
              memberViewabilityLevel);
      return this;
    }

    public Builder memberViewabilityLevel(
        EnumWrapper<CreateGroupRequestBodyMemberViewabilityLevelField> memberViewabilityLevel) {
      this.memberViewabilityLevel = memberViewabilityLevel;
      return this;
    }

    public CreateGroupRequestBody build() {
      return new CreateGroupRequestBody(this);
    }
  }
}
