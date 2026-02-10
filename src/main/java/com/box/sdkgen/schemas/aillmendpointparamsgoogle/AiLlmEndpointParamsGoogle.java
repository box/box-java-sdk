package com.box.sdkgen.schemas.aillmendpointparamsgoogle;

import com.box.sdkgen.internal.Nullable;
import com.box.sdkgen.internal.NullableFieldTracker;
import com.box.sdkgen.internal.SerializableObject;
import com.box.sdkgen.serialization.json.EnumWrapper;
import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import java.util.Objects;

/** AI LLM endpoint params Google object. */
@JsonFilter("nullablePropertyFilter")
public class AiLlmEndpointParamsGoogle extends SerializableObject {

  /** The type of the AI LLM endpoint params object for Google. This parameter is **required**. */
  @JsonDeserialize(
      using =
          AiLlmEndpointParamsGoogleTypeField.AiLlmEndpointParamsGoogleTypeFieldDeserializer.class)
  @JsonSerialize(
      using = AiLlmEndpointParamsGoogleTypeField.AiLlmEndpointParamsGoogleTypeFieldSerializer.class)
  protected EnumWrapper<AiLlmEndpointParamsGoogleTypeField> type;

  /**
   * The temperature is used for sampling during response generation, which occurs when `top-P` and
   * `top-K` are applied. Temperature controls the degree of randomness in the token selection.
   */
  @Nullable protected Double temperature;

  /**
   * `Top-P` changes how the model selects tokens for output. Tokens are selected from the most (see
   * `top-K`) to least probable until the sum of their probabilities equals the `top-P` value.
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

  public AiLlmEndpointParamsGoogle() {
    super();
    this.type =
        new EnumWrapper<AiLlmEndpointParamsGoogleTypeField>(
            AiLlmEndpointParamsGoogleTypeField.GOOGLE_PARAMS);
  }

  protected AiLlmEndpointParamsGoogle(Builder builder) {
    super();
    this.type = builder.type;
    this.temperature = builder.temperature;
    this.topP = builder.topP;
    this.topK = builder.topK;
    markNullableFieldsAsSet(builder.getExplicitlySetNullableFields());
  }

  public EnumWrapper<AiLlmEndpointParamsGoogleTypeField> getType() {
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
    AiLlmEndpointParamsGoogle casted = (AiLlmEndpointParamsGoogle) o;
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
    return "AiLlmEndpointParamsGoogle{"
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

    protected EnumWrapper<AiLlmEndpointParamsGoogleTypeField> type;

    protected Double temperature;

    protected Double topP;

    protected Double topK;

    public Builder() {
      super();
    }

    public Builder type(AiLlmEndpointParamsGoogleTypeField type) {
      this.type = new EnumWrapper<AiLlmEndpointParamsGoogleTypeField>(type);
      return this;
    }

    public Builder type(EnumWrapper<AiLlmEndpointParamsGoogleTypeField> type) {
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

    public AiLlmEndpointParamsGoogle build() {
      if (this.type == null) {
        this.type =
            new EnumWrapper<AiLlmEndpointParamsGoogleTypeField>(
                AiLlmEndpointParamsGoogleTypeField.GOOGLE_PARAMS);
      }
      return new AiLlmEndpointParamsGoogle(this);
    }
  }
}
