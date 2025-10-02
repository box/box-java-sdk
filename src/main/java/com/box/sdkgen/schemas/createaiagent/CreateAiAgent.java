package com.box.sdkgen.schemas.createaiagent;

import com.box.sdkgen.internal.NullableFieldTracker;
import com.box.sdkgen.internal.SerializableObject;
import com.box.sdkgen.schemas.aiagentallowedentity.AiAgentAllowedEntity;
import com.box.sdkgen.schemas.aistudioagentask.AiStudioAgentAsk;
import com.box.sdkgen.schemas.aistudioagentextract.AiStudioAgentExtract;
import com.box.sdkgen.schemas.aistudioagenttextgen.AiStudioAgentTextGen;
import com.box.sdkgen.serialization.json.EnumWrapper;
import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import java.util.List;
import java.util.Objects;

/** The schema for AI agent create request. */
@JsonFilter("nullablePropertyFilter")
public class CreateAiAgent extends SerializableObject {

  /** The type of agent used to handle queries. */
  @JsonDeserialize(using = CreateAiAgentTypeField.CreateAiAgentTypeFieldDeserializer.class)
  @JsonSerialize(using = CreateAiAgentTypeField.CreateAiAgentTypeFieldSerializer.class)
  protected EnumWrapper<CreateAiAgentTypeField> type;

  /** The name of the AI Agent. */
  protected final String name;

  /**
   * The state of the AI Agent. Possible values are: `enabled`, `disabled`, and
   * `enabled_for_selected_users`.
   */
  @JsonProperty("access_state")
  protected final String accessState;

  /**
   * The icon reference of the AI Agent. It should have format of the URL
   * `https://cdn01.boxcdn.net/app-assets/aistudio/avatars/&lt;file_name&gt;` where possible values
   * of `file_name` are:
   * `logo_boxAi.png`,`logo_stamp.png`,`logo_legal.png`,`logo_finance.png`,`logo_config.png`,`logo_handshake.png`,`logo_analytics.png`,`logo_classification.png`.
   */
  @JsonProperty("icon_reference")
  protected String iconReference;

  /** List of allowed users or groups. */
  @JsonProperty("allowed_entities")
  protected List<AiAgentAllowedEntity> allowedEntities;

  protected AiStudioAgentAsk ask;

  @JsonProperty("text_gen")
  protected AiStudioAgentTextGen textGen;

  protected AiStudioAgentExtract extract;

  public CreateAiAgent(
      @JsonProperty("name") String name, @JsonProperty("access_state") String accessState) {
    super();
    this.name = name;
    this.accessState = accessState;
    this.type = new EnumWrapper<CreateAiAgentTypeField>(CreateAiAgentTypeField.AI_AGENT);
  }

  protected CreateAiAgent(Builder builder) {
    super();
    this.type = builder.type;
    this.name = builder.name;
    this.accessState = builder.accessState;
    this.iconReference = builder.iconReference;
    this.allowedEntities = builder.allowedEntities;
    this.ask = builder.ask;
    this.textGen = builder.textGen;
    this.extract = builder.extract;
    markNullableFieldsAsSet(builder.getExplicitlySetNullableFields());
  }

  public EnumWrapper<CreateAiAgentTypeField> getType() {
    return type;
  }

  public String getName() {
    return name;
  }

  public String getAccessState() {
    return accessState;
  }

  public String getIconReference() {
    return iconReference;
  }

  public List<AiAgentAllowedEntity> getAllowedEntities() {
    return allowedEntities;
  }

  public AiStudioAgentAsk getAsk() {
    return ask;
  }

  public AiStudioAgentTextGen getTextGen() {
    return textGen;
  }

  public AiStudioAgentExtract getExtract() {
    return extract;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    CreateAiAgent casted = (CreateAiAgent) o;
    return Objects.equals(type, casted.type)
        && Objects.equals(name, casted.name)
        && Objects.equals(accessState, casted.accessState)
        && Objects.equals(iconReference, casted.iconReference)
        && Objects.equals(allowedEntities, casted.allowedEntities)
        && Objects.equals(ask, casted.ask)
        && Objects.equals(textGen, casted.textGen)
        && Objects.equals(extract, casted.extract);
  }

  @Override
  public int hashCode() {
    return Objects.hash(
        type, name, accessState, iconReference, allowedEntities, ask, textGen, extract);
  }

  @Override
  public String toString() {
    return "CreateAiAgent{"
        + "type='"
        + type
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
        + "iconReference='"
        + iconReference
        + '\''
        + ", "
        + "allowedEntities='"
        + allowedEntities
        + '\''
        + ", "
        + "ask='"
        + ask
        + '\''
        + ", "
        + "textGen='"
        + textGen
        + '\''
        + ", "
        + "extract='"
        + extract
        + '\''
        + "}";
  }

  public static class Builder extends NullableFieldTracker {

    protected EnumWrapper<CreateAiAgentTypeField> type;

    protected final String name;

    protected final String accessState;

    protected String iconReference;

    protected List<AiAgentAllowedEntity> allowedEntities;

    protected AiStudioAgentAsk ask;

    protected AiStudioAgentTextGen textGen;

    protected AiStudioAgentExtract extract;

    public Builder(String name, String accessState) {
      super();
      this.name = name;
      this.accessState = accessState;
      this.type = new EnumWrapper<CreateAiAgentTypeField>(CreateAiAgentTypeField.AI_AGENT);
    }

    public Builder type(CreateAiAgentTypeField type) {
      this.type = new EnumWrapper<CreateAiAgentTypeField>(type);
      return this;
    }

    public Builder type(EnumWrapper<CreateAiAgentTypeField> type) {
      this.type = type;
      return this;
    }

    public Builder iconReference(String iconReference) {
      this.iconReference = iconReference;
      return this;
    }

    public Builder allowedEntities(List<AiAgentAllowedEntity> allowedEntities) {
      this.allowedEntities = allowedEntities;
      return this;
    }

    public Builder ask(AiStudioAgentAsk ask) {
      this.ask = ask;
      return this;
    }

    public Builder textGen(AiStudioAgentTextGen textGen) {
      this.textGen = textGen;
      return this;
    }

    public Builder extract(AiStudioAgentExtract extract) {
      this.extract = extract;
      return this;
    }

    public CreateAiAgent build() {
      return new CreateAiAgent(this);
    }
  }
}
