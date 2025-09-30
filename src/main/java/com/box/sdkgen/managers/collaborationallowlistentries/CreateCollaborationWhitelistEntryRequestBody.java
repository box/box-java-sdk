package com.box.sdkgen.managers.collaborationallowlistentries;

import com.box.sdkgen.internal.SerializableObject;
import com.box.sdkgen.serialization.json.EnumWrapper;
import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import java.util.Objects;

@JsonFilter("nullablePropertyFilter")
public class CreateCollaborationWhitelistEntryRequestBody extends SerializableObject {

  protected final String domain;

  @JsonDeserialize(
      using =
          CreateCollaborationWhitelistEntryRequestBodyDirectionField
              .CreateCollaborationWhitelistEntryRequestBodyDirectionFieldDeserializer.class)
  @JsonSerialize(
      using =
          CreateCollaborationWhitelistEntryRequestBodyDirectionField
              .CreateCollaborationWhitelistEntryRequestBodyDirectionFieldSerializer.class)
  protected final EnumWrapper<CreateCollaborationWhitelistEntryRequestBodyDirectionField> direction;

  public CreateCollaborationWhitelistEntryRequestBody(
      String domain, CreateCollaborationWhitelistEntryRequestBodyDirectionField direction) {
    super();
    this.domain = domain;
    this.direction =
        new EnumWrapper<CreateCollaborationWhitelistEntryRequestBodyDirectionField>(direction);
  }

  public CreateCollaborationWhitelistEntryRequestBody(
      @JsonProperty("domain") String domain,
      @JsonProperty("direction")
          EnumWrapper<CreateCollaborationWhitelistEntryRequestBodyDirectionField> direction) {
    super();
    this.domain = domain;
    this.direction = direction;
  }

  public String getDomain() {
    return domain;
  }

  public EnumWrapper<CreateCollaborationWhitelistEntryRequestBodyDirectionField> getDirection() {
    return direction;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    CreateCollaborationWhitelistEntryRequestBody casted =
        (CreateCollaborationWhitelistEntryRequestBody) o;
    return Objects.equals(domain, casted.domain) && Objects.equals(direction, casted.direction);
  }

  @Override
  public int hashCode() {
    return Objects.hash(domain, direction);
  }

  @Override
  public String toString() {
    return "CreateCollaborationWhitelistEntryRequestBody{"
        + "domain='"
        + domain
        + '\''
        + ", "
        + "direction='"
        + direction
        + '\''
        + "}";
  }
}
