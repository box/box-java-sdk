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

@JsonFilter("nullablePropertyFilter")
public class AiLlmEndpointParamsIbm extends SerializableObject {

  @JsonDeserialize(
      using = AiLlmEndpointParamsIbmTypeField.AiLlmEndpointParamsIbmTypeFieldDeserializer.class)
  @JsonSerialize(
      using = AiLlmEndpointParamsIbmTypeField.AiLlmEndpointParamsIbmTypeFieldSerializer.class)
  protected EnumWrapper<AiLlmEndpointParamsIbmTypeField> type;

  @Nullable protected Double temperature;

  @JsonProperty("top_p")
  @Nullable
  protected Double topP;

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
