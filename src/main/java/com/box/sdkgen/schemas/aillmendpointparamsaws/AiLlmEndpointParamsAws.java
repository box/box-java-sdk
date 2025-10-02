package com.box.sdkgen.schemas.aillmendpointparamsaws;

import com.box.sdkgen.internal.Nullable;
import com.box.sdkgen.internal.NullableFieldTracker;
import com.box.sdkgen.internal.SerializableObject;
import com.box.sdkgen.serialization.json.EnumWrapper;
import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import java.util.Objects;

/** AI LLM endpoint params AWS object. */
@JsonFilter("nullablePropertyFilter")
public class AiLlmEndpointParamsAws extends SerializableObject {

  /** The type of the AI LLM endpoint params object for AWS. This parameter is **required**. */
  @JsonDeserialize(
      using = AiLlmEndpointParamsAwsTypeField.AiLlmEndpointParamsAwsTypeFieldDeserializer.class)
  @JsonSerialize(
      using = AiLlmEndpointParamsAwsTypeField.AiLlmEndpointParamsAwsTypeFieldSerializer.class)
  protected EnumWrapper<AiLlmEndpointParamsAwsTypeField> type;

  /**
   * What sampling temperature to use, between 0 and 1. Higher values like 0.8 will make the output
   * more random, while lower values like 0.2 will make it more focused and deterministic. We
   * generally recommend altering this or `top_p` but not both.
   */
  @Nullable protected Double temperature;

  /**
   * An alternative to sampling with temperature, called nucleus sampling, where the model considers
   * the results of the tokens with `top_p` probability mass. So 0.1 means only the tokens
   * comprising the top 10% probability mass are considered. We generally recommend altering this or
   * temperature but not both.
   */
  @JsonProperty("top_p")
  @Nullable
  protected Double topP;

  public AiLlmEndpointParamsAws() {
    super();
    this.type =
        new EnumWrapper<AiLlmEndpointParamsAwsTypeField>(
            AiLlmEndpointParamsAwsTypeField.AWS_PARAMS);
  }

  protected AiLlmEndpointParamsAws(Builder builder) {
    super();
    this.type = builder.type;
    this.temperature = builder.temperature;
    this.topP = builder.topP;
    markNullableFieldsAsSet(builder.getExplicitlySetNullableFields());
  }

  public EnumWrapper<AiLlmEndpointParamsAwsTypeField> getType() {
    return type;
  }

  public Double getTemperature() {
    return temperature;
  }

  public Double getTopP() {
    return topP;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    AiLlmEndpointParamsAws casted = (AiLlmEndpointParamsAws) o;
    return Objects.equals(type, casted.type)
        && Objects.equals(temperature, casted.temperature)
        && Objects.equals(topP, casted.topP);
  }

  @Override
  public int hashCode() {
    return Objects.hash(type, temperature, topP);
  }

  @Override
  public String toString() {
    return "AiLlmEndpointParamsAws{"
        + "type='"
        + type
        + '\''
        + ", "
        + "temperature='"
        + temperature
        + '\''
        + ", "
        + "topP='"
        + topP
        + '\''
        + "}";
  }

  public static class Builder extends NullableFieldTracker {

    protected EnumWrapper<AiLlmEndpointParamsAwsTypeField> type;

    protected Double temperature;

    protected Double topP;

    public Builder() {
      super();
      this.type =
          new EnumWrapper<AiLlmEndpointParamsAwsTypeField>(
              AiLlmEndpointParamsAwsTypeField.AWS_PARAMS);
    }

    public Builder type(AiLlmEndpointParamsAwsTypeField type) {
      this.type = new EnumWrapper<AiLlmEndpointParamsAwsTypeField>(type);
      return this;
    }

    public Builder type(EnumWrapper<AiLlmEndpointParamsAwsTypeField> type) {
      this.type = type;
      return this;
    }

    public Builder temperature(Double temperature) {
      this.temperature = temperature;
      this.markNullableFieldAsSet("temperature");
      return this;
    }

    public Builder topP(Double topP) {
      this.topP = topP;
      this.markNullableFieldAsSet("top_p");
      return this;
    }

    public AiLlmEndpointParamsAws build() {
      return new AiLlmEndpointParamsAws(this);
    }
  }
}
