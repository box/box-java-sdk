package com.box.sdkgen.schemas.v2025r0.clienterrorv2025r0;

import com.box.sdkgen.internal.SerializableObject;
import com.box.sdkgen.serialization.json.EnumWrapper;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import java.util.Objects;

public class ClientErrorV2025R0 extends SerializableObject {

  @JsonDeserialize(
      using = ClientErrorV2025R0TypeField.ClientErrorV2025R0TypeFieldDeserializer.class)
  @JsonSerialize(using = ClientErrorV2025R0TypeField.ClientErrorV2025R0TypeFieldSerializer.class)
  protected EnumWrapper<ClientErrorV2025R0TypeField> type;

  protected Integer status;

  @JsonDeserialize(
      using = ClientErrorV2025R0CodeField.ClientErrorV2025R0CodeFieldDeserializer.class)
  @JsonSerialize(using = ClientErrorV2025R0CodeField.ClientErrorV2025R0CodeFieldSerializer.class)
  protected EnumWrapper<ClientErrorV2025R0CodeField> code;

  protected String message;

  @JsonProperty("context_info")
  protected ClientErrorV2025R0ContextInfoField contextInfo;

  @JsonProperty("help_url")
  protected String helpUrl;

  @JsonProperty("request_id")
  protected String requestId;

  public ClientErrorV2025R0() {
    super();
  }

  protected ClientErrorV2025R0(ClientErrorV2025R0Builder builder) {
    super();
    this.type = builder.type;
    this.status = builder.status;
    this.code = builder.code;
    this.message = builder.message;
    this.contextInfo = builder.contextInfo;
    this.helpUrl = builder.helpUrl;
    this.requestId = builder.requestId;
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

  public ClientErrorV2025R0ContextInfoField getContextInfo() {
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

  public static class ClientErrorV2025R0Builder {

    protected EnumWrapper<ClientErrorV2025R0TypeField> type;

    protected Integer status;

    protected EnumWrapper<ClientErrorV2025R0CodeField> code;

    protected String message;

    protected ClientErrorV2025R0ContextInfoField contextInfo;

    protected String helpUrl;

    protected String requestId;

    public ClientErrorV2025R0Builder type(ClientErrorV2025R0TypeField type) {
      this.type = new EnumWrapper<ClientErrorV2025R0TypeField>(type);
      return this;
    }

    public ClientErrorV2025R0Builder type(EnumWrapper<ClientErrorV2025R0TypeField> type) {
      this.type = type;
      return this;
    }

    public ClientErrorV2025R0Builder status(Integer status) {
      this.status = status;
      return this;
    }

    public ClientErrorV2025R0Builder code(ClientErrorV2025R0CodeField code) {
      this.code = new EnumWrapper<ClientErrorV2025R0CodeField>(code);
      return this;
    }

    public ClientErrorV2025R0Builder code(EnumWrapper<ClientErrorV2025R0CodeField> code) {
      this.code = code;
      return this;
    }

    public ClientErrorV2025R0Builder message(String message) {
      this.message = message;
      return this;
    }

    public ClientErrorV2025R0Builder contextInfo(ClientErrorV2025R0ContextInfoField contextInfo) {
      this.contextInfo = contextInfo;
      return this;
    }

    public ClientErrorV2025R0Builder helpUrl(String helpUrl) {
      this.helpUrl = helpUrl;
      return this;
    }

    public ClientErrorV2025R0Builder requestId(String requestId) {
      this.requestId = requestId;
      return this;
    }

    public ClientErrorV2025R0 build() {
      return new ClientErrorV2025R0(this);
    }
  }
}
