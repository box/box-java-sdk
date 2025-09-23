package com.box.sdkgen.schemas.integrationmappingpartneritemslack;

import com.box.sdkgen.internal.Nullable;
import com.box.sdkgen.internal.NullableFieldTracker;
import com.box.sdkgen.internal.SerializableObject;
import com.box.sdkgen.serialization.json.EnumWrapper;
import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import java.util.Objects;

@JsonFilter("nullablePropertyFilter")
public class IntegrationMappingPartnerItemSlack extends SerializableObject {

  @JsonDeserialize(
      using =
          IntegrationMappingPartnerItemSlackTypeField
              .IntegrationMappingPartnerItemSlackTypeFieldDeserializer.class)
  @JsonSerialize(
      using =
          IntegrationMappingPartnerItemSlackTypeField
              .IntegrationMappingPartnerItemSlackTypeFieldSerializer.class)
  protected EnumWrapper<IntegrationMappingPartnerItemSlackTypeField> type;

  protected final String id;

  @JsonProperty("slack_workspace_id")
  @Nullable
  protected String slackWorkspaceId;

  @JsonProperty("slack_org_id")
  @Nullable
  protected String slackOrgId;

  public IntegrationMappingPartnerItemSlack(@JsonProperty("id") String id) {
    super();
    this.id = id;
    this.type =
        new EnumWrapper<IntegrationMappingPartnerItemSlackTypeField>(
            IntegrationMappingPartnerItemSlackTypeField.CHANNEL);
  }

  protected IntegrationMappingPartnerItemSlack(Builder builder) {
    super();
    this.type = builder.type;
    this.id = builder.id;
    this.slackWorkspaceId = builder.slackWorkspaceId;
    this.slackOrgId = builder.slackOrgId;
    markNullableFieldsAsSet(builder.getExplicitlySetNullableFields());
  }

  public EnumWrapper<IntegrationMappingPartnerItemSlackTypeField> getType() {
    return type;
  }

  public String getId() {
    return id;
  }

  public String getSlackWorkspaceId() {
    return slackWorkspaceId;
  }

  public String getSlackOrgId() {
    return slackOrgId;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    IntegrationMappingPartnerItemSlack casted = (IntegrationMappingPartnerItemSlack) o;
    return Objects.equals(type, casted.type)
        && Objects.equals(id, casted.id)
        && Objects.equals(slackWorkspaceId, casted.slackWorkspaceId)
        && Objects.equals(slackOrgId, casted.slackOrgId);
  }

  @Override
  public int hashCode() {
    return Objects.hash(type, id, slackWorkspaceId, slackOrgId);
  }

  @Override
  public String toString() {
    return "IntegrationMappingPartnerItemSlack{"
        + "type='"
        + type
        + '\''
        + ", "
        + "id='"
        + id
        + '\''
        + ", "
        + "slackWorkspaceId='"
        + slackWorkspaceId
        + '\''
        + ", "
        + "slackOrgId='"
        + slackOrgId
        + '\''
        + "}";
  }

  public static class Builder extends NullableFieldTracker {

    protected EnumWrapper<IntegrationMappingPartnerItemSlackTypeField> type;

    protected final String id;

    protected String slackWorkspaceId;

    protected String slackOrgId;

    public Builder(String id) {
      super();
      this.id = id;
      this.type =
          new EnumWrapper<IntegrationMappingPartnerItemSlackTypeField>(
              IntegrationMappingPartnerItemSlackTypeField.CHANNEL);
    }

    public Builder type(IntegrationMappingPartnerItemSlackTypeField type) {
      this.type = new EnumWrapper<IntegrationMappingPartnerItemSlackTypeField>(type);
      return this;
    }

    public Builder type(EnumWrapper<IntegrationMappingPartnerItemSlackTypeField> type) {
      this.type = type;
      return this;
    }

    public Builder slackWorkspaceId(String slackWorkspaceId) {
      this.slackWorkspaceId = slackWorkspaceId;
      this.markNullableFieldAsSet("slack_workspace_id");
      return this;
    }

    public Builder slackOrgId(String slackOrgId) {
      this.slackOrgId = slackOrgId;
      this.markNullableFieldAsSet("slack_org_id");
      return this;
    }

    public IntegrationMappingPartnerItemSlack build() {
      return new IntegrationMappingPartnerItemSlack(this);
    }
  }
}
