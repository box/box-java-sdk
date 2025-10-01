package com.box.sdkgen.schemas.aillmendpointparamsibm;

import com.box.sdkgen.internal.Nullable;
import com.box.sdkgen.internal.NullableFieldTracker;
import com.box.sdkgen.internal.SerializableObject;
import com.box.sdkgen.serialization.json.EnumWrapper;
import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import java.util.Objects;

/** AI LLM endpoint params IBM object. */
@JsonFilter("nullablePropertyFilter")
public class AiLlmEndpointParamsIbm extends SerializableObject {

  /** The type of the AI LLM endpoint params object for IBM. This parameter is **required**. */
  @JsonDeserialize(
      using = AiLlmEndpointParamsIbmTypeField.AiLlmEndpointParamsIbmTypeFieldDeserializer.class)
  @JsonSerialize(
      using = AiLlmEndpointParamsIbmTypeField.AiLlmEndpointParamsIbmTypeFieldSerializer.class)
  protected EnumWrapper<AiLlmEndpointParamsIbmTypeField> type;

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

  /**
   * `Top-K` changes how the model selects tokens for output. A low `top-K` means the next selected
   * token is the most probable among all tokens in the model's vocabulary (also called greedy
   * decoding), while a high `top-K` means that the next token is selected from among the three most
   * probable tokens by using temperature.
   */
  @JsonProperty("top_k")
  @Nullable
  protected Double topK;

  public AiLlmEndpointParamsIbm() {
    super();
    this.type =
        new EnumWrapper<AiLlmEndpointParamsIbmTypeField>(
            AiLlmEndpointParamsIbmTypeField.IBM_PARAMS);
  }

  protected AiLlmEndpointParamsIbm(Builder builder) {
    super();
    this.type = builder.type;
    this.temperature = builder.temperature;
    this.topP = builder.topP;
    this.topK = builder.topK;
    markNullableFieldsAsSet(builder.getExplicitlySetNullableFields());
  }

  public EnumWrapper<AiLlmEndpointParamsIbmTypeField> getType() {
    return type;
  }

  public Double getTemperature() {
    return temperature;
  }

  public Double getTopP() {
    return topP;
  }

  public Double getTopK() {
    return topK;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    AiLlmEndpointParamsIbm casted = (AiLlmEndpointParamsIbm) o;
    return Objects.equals(type, casted.type)
        && Objects.equals(temperature, casted.temperature)
        && Objects.equals(topP, casted.topP)
        && Objects.equals(topK, casted.topK);
  }

  @Override
  public int hashCode() {
    return Objects.hash(type, temperature, topP, topK);
  }

  @Override
  public String toString() {
    return "AiLlmEndpointParamsIbm{"
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
        + ", "
        + "topK='"
        + topK
        + '\''
        + "}";
  }

  public static class Builder extends NullableFieldTracker {

    protected EnumWrapper<AiLlmEndpointParamsIbmTypeField> type;

    protected Double temperature;

    protected Double topP;

    protected Double topK;

    public Builder() {
      super();
      this.type =
          new EnumWrapper<AiLlmEndpointParamsIbmTypeField>(
              AiLlmEndpointParamsIbmTypeField.IBM_PARAMS);
    }

    public Builder type(AiLlmEndpointParamsIbmTypeField type) {
      this.type = new EnumWrapper<AiLlmEndpointParamsIbmTypeField>(type);
      return this;
    }

    public Builder type(EnumWrapper<AiLlmEndpointParamsIbmTypeField> type) {
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

    public Builder topK(Double topK) {
      this.topK = topK;
      this.markNullableFieldAsSet("top_k");
      return this;
    }

    public AiLlmEndpointParamsIbm build() {
      return new AiLlmEndpointParamsIbm(this);
    }
  }
}
