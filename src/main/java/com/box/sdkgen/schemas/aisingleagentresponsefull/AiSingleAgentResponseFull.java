package com.box.sdkgen.schemas.aisingleagentresponsefull;

import com.box.sdkgen.schemas.aiagentallowedentity.AiAgentAllowedEntity;
import com.box.sdkgen.schemas.aisingleagentresponse.AiSingleAgentResponse;
import com.box.sdkgen.schemas.aisingleagentresponse.AiSingleAgentResponseTypeField;
import com.box.sdkgen.schemas.aistudioagentaskresponse.AiStudioAgentAskResponse;
import com.box.sdkgen.schemas.aistudioagentextractresponse.AiStudioAgentExtractResponse;
import com.box.sdkgen.schemas.aistudioagenttextgenresponse.AiStudioAgentTextGenResponse;
import com.box.sdkgen.schemas.userbase.UserBase;
import com.box.sdkgen.serialization.json.EnumWrapper;
import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.time.OffsetDateTime;
import java.util.List;
import java.util.Objects;

@JsonFilter("nullablePropertyFilter")
public class AiSingleAgentResponseFull extends AiSingleAgentResponse {

  protected AiStudioAgentAskResponse ask;

  @JsonProperty("text_gen")
  protected AiStudioAgentTextGenResponse textGen;

  protected AiStudioAgentExtractResponse extract;

  public AiSingleAgentResponseFull(
      @JsonProperty("id") String id,
      @JsonProperty("origin") String origin,
      @JsonProperty("name") String name,
      @JsonProperty("access_state") String accessState) {
    super(id, origin, name, accessState);
  }

  protected AiSingleAgentResponseFull(Builder builder) {
    super(builder);
    this.ask = builder.ask;
    this.textGen = builder.textGen;
    this.extract = builder.extract;
    markNullableFieldsAsSet(builder.getExplicitlySetNullableFields());
  }

  public AiStudioAgentAskResponse getAsk() {
    return ask;
  }

  public AiStudioAgentTextGenResponse getTextGen() {
    return textGen;
  }

  public AiStudioAgentExtractResponse getExtract() {
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
    AiSingleAgentResponseFull casted = (AiSingleAgentResponseFull) o;
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
        && Objects.equals(allowedEntities, casted.allowedEntities)
        && Objects.equals(ask, casted.ask)
        && Objects.equals(textGen, casted.textGen)
        && Objects.equals(extract, casted.extract);
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
        allowedEntities,
        ask,
        textGen,
        extract);
  }

  @Override
  public String toString() {
    return "AiSingleAgentResponseFull{"
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

  public static class Builder extends AiSingleAgentResponse.Builder {

    protected AiStudioAgentAskResponse ask;

    protected AiStudioAgentTextGenResponse textGen;

    protected AiStudioAgentExtractResponse extract;

    public Builder(String id, String origin, String name, String accessState) {
      super(id, origin, name, accessState);
    }

    public Builder ask(AiStudioAgentAskResponse ask) {
      this.ask = ask;
      return this;
    }

    public Builder textGen(AiStudioAgentTextGenResponse textGen) {
      this.textGen = textGen;
      return this;
    }

    public Builder extract(AiStudioAgentExtractResponse extract) {
      this.extract = extract;
      return this;
    }

    @Override
    public Builder type(AiSingleAgentResponseTypeField type) {
      this.type = new EnumWrapper<AiSingleAgentResponseTypeField>(type);
      return this;
    }

    @Override
    public Builder type(EnumWrapper<AiSingleAgentResponseTypeField> type) {
      this.type = type;
      return this;
    }

    @Override
    public Builder createdBy(UserBase createdBy) {
      this.createdBy = createdBy;
      return this;
    }

    @Override
    public Builder createdAt(OffsetDateTime createdAt) {
      this.createdAt = createdAt;
      return this;
    }

    @Override
    public Builder modifiedBy(UserBase modifiedBy) {
      this.modifiedBy = modifiedBy;
      return this;
    }

    @Override
    public Builder modifiedAt(OffsetDateTime modifiedAt) {
      this.modifiedAt = modifiedAt;
      return this;
    }

    @Override
    public Builder iconReference(String iconReference) {
      this.iconReference = iconReference;
      return this;
    }

    @Override
    public Builder allowedEntities(List<AiAgentAllowedEntity> allowedEntities) {
      this.allowedEntities = allowedEntities;
      return this;
    }

    public AiSingleAgentResponseFull build() {
      return new AiSingleAgentResponseFull(this);
    }
  }
}
