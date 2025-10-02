package com.box.sdkgen.schemas.v2025r0.clienterrorv2025r0;

import com.box.sdkgen.internal.Nullable;
import com.box.sdkgen.internal.NullableFieldTracker;
import com.box.sdkgen.internal.SerializableObject;
import com.box.sdkgen.serialization.json.EnumWrapper;
import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import java.util.Map;
import java.util.Objects;

/** A generic error. */
@JsonFilter("nullablePropertyFilter")
public class ClientErrorV2025R0 extends SerializableObject {

  /** The value will always be `error`. */
  @JsonDeserialize(
      using = ClientErrorV2025R0TypeField.ClientErrorV2025R0TypeFieldDeserializer.class)
  @JsonSerialize(using = ClientErrorV2025R0TypeField.ClientErrorV2025R0TypeFieldSerializer.class)
  protected EnumWrapper<ClientErrorV2025R0TypeField> type;

  /** The HTTP status of the response. */
  protected Integer status;

  /** A Box-specific error code. */
  @JsonDeserialize(
      using = ClientErrorV2025R0CodeField.ClientErrorV2025R0CodeFieldDeserializer.class)
  @JsonSerialize(using = ClientErrorV2025R0CodeField.ClientErrorV2025R0CodeFieldSerializer.class)
  protected EnumWrapper<ClientErrorV2025R0CodeField> code;

  /** A short message describing the error. */
  protected String message;

  /**
   * A free-form object that contains additional context about the error. The possible fields are
   * defined on a per-endpoint basis. `message` is only one example.
   */
  @JsonProperty("context_info")
  @Nullable
  protected Map<String, Object> contextInfo;

  /** A URL that links to more information about why this error occurred. */
  @JsonProperty("help_url")
  protected String helpUrl;

  /** A unique identifier for this response, which can be used when contacting Box support. */
  @JsonProperty("request_id")
  protected String requestId;

  public ClientErrorV2025R0() {
    super();
  }

  protected ClientErrorV2025R0(Builder builder) {
    super();
    this.type = builder.type;
    this.status = builder.status;
    this.code = builder.code;
    this.message = builder.message;
    this.contextInfo = builder.contextInfo;
    this.helpUrl = builder.helpUrl;
    this.requestId = builder.requestId;
    markNullableFieldsAsSet(builder.getExplicitlySetNullableFields());
  }

  public EnumWrapper<ClientErrorV2025R0TypeField> getType() {
    return type;
  }

  public Integer getStatus() {
    return status;
  }

  public EnumWrapper<ClientErrorV2025R0CodeField> getCode() {
    return code;
  }

  public String getMessage() {
    return message;
  }

  public Map<String, Object> getContextInfo() {
    return contextInfo;
  }

  public String getHelpUrl() {
    return helpUrl;
  }

  public String getRequestId() {
    return requestId;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ClientErrorV2025R0 casted = (ClientErrorV2025R0) o;
    return Objects.equals(type, casted.type)
        && Objects.equals(status, casted.status)
        && Objects.equals(code, casted.code)
        && Objects.equals(message, casted.message)
        && Objects.equals(contextInfo, casted.contextInfo)
        && Objects.equals(helpUrl, casted.helpUrl)
        && Objects.equals(requestId, casted.requestId);
  }

  @Override
  public int hashCode() {
    return Objects.hash(type, status, code, message, contextInfo, helpUrl, requestId);
  }

  @Override
  public String toString() {
    return "ClientErrorV2025R0{"
        + "type='"
        + type
        + '\''
        + ", "
        + "status='"
        + status
        + '\''
        + ", "
        + "code='"
        + code
        + '\''
        + ", "
        + "message='"
        + message
        + '\''
        + ", "
        + "contextInfo='"
        + contextInfo
        + '\''
        + ", "
        + "helpUrl='"
        + helpUrl
        + '\''
        + ", "
        + "requestId='"
        + requestId
        + '\''
        + "}";
  }

  public static class Builder extends NullableFieldTracker {

    protected EnumWrapper<ClientErrorV2025R0TypeField> type;

    protected Integer status;

    protected EnumWrapper<ClientErrorV2025R0CodeField> code;

    protected String message;

    protected Map<String, Object> contextInfo;

    protected String helpUrl;

    protected String requestId;

    public Builder type(ClientErrorV2025R0TypeField type) {
      this.type = new EnumWrapper<ClientErrorV2025R0TypeField>(type);
      return this;
    }

    public Builder type(EnumWrapper<ClientErrorV2025R0TypeField> type) {
      this.type = type;
      return this;
    }

    public Builder status(Integer status) {
      this.status = status;
      return this;
    }

    public Builder code(ClientErrorV2025R0CodeField code) {
      this.code = new EnumWrapper<ClientErrorV2025R0CodeField>(code);
      return this;
    }

    public Builder code(EnumWrapper<ClientErrorV2025R0CodeField> code) {
      this.code = code;
      return this;
    }

    public Builder message(String message) {
      this.message = message;
      return this;
    }

    public Builder contextInfo(Map<String, Object> contextInfo) {
      this.contextInfo = contextInfo;
      this.markNullableFieldAsSet("context_info");
      return this;
    }

    public Builder helpUrl(String helpUrl) {
      this.helpUrl = helpUrl;
      return this;
    }

    public Builder requestId(String requestId) {
      this.requestId = requestId;
      return this;
    }

    public ClientErrorV2025R0 build() {
      return new ClientErrorV2025R0(this);
    }
  }
}
