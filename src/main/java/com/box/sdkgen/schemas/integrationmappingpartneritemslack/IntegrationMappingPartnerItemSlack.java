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

/**
 * The schema for an integration mapping mapped item object for type Slack.
 *
 * <p>Depending if Box for Slack is installed at the org or workspace level, provide **either**
 * `slack_org_id` **or** `slack_workspace_id`. Do not use both parameters at the same time.
 */
@JsonFilter("nullablePropertyFilter")
public class IntegrationMappingPartnerItemSlack extends SerializableObject {

  /** Type of the mapped item referenced in `id`. */
  @JsonDeserialize(
      using =
          IntegrationMappingPartnerItemSlackTypeField
              .IntegrationMappingPartnerItemSlackTypeFieldDeserializer.class)
  @JsonSerialize(
      using =
          IntegrationMappingPartnerItemSlackTypeField
              .IntegrationMappingPartnerItemSlackTypeFieldSerializer.class)
  protected EnumWrapper<IntegrationMappingPartnerItemSlackTypeField> type;

  /** ID of the mapped item (of type referenced in `type`). */
  protected final String id;

  /**
   * ID of the Slack workspace with which the item is associated. Use this parameter if Box for
   * Slack is installed at a workspace level. Do not use `slack_org_id` at the same time.
   */
  @JsonProperty("slack_workspace_id")
  @Nullable
  protected String slackWorkspaceId;

  /**
   * ID of the Slack org with which the item is associated. Use this parameter if Box for Slack is
   * installed at the org level. Do not use `slack_workspace_id` at the same time.
   */
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
      if (this.type == null) {
        this.type =
            new EnumWrapper<IntegrationMappingPartnerItemSlackTypeField>(
                IntegrationMappingPartnerItemSlackTypeField.CHANNEL);
      }
      return new IntegrationMappingPartnerItemSlack(this);
    }
  }
}
