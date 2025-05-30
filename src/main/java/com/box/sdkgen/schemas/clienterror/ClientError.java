package com.box.sdkgen.schemas.clienterror;

import com.box.sdkgen.internal.SerializableObject;
import com.box.sdkgen.serialization.json.EnumWrapper;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import java.util.Map;
import java.util.Objects;

public class ClientError extends SerializableObject {

  @JsonDeserialize(using = ClientErrorTypeField.ClientErrorTypeFieldDeserializer.class)
  @JsonSerialize(using = ClientErrorTypeField.ClientErrorTypeFieldSerializer.class)
  protected EnumWrapper<ClientErrorTypeField> type;

  protected Integer status;

  @JsonDeserialize(using = ClientErrorCodeField.ClientErrorCodeFieldDeserializer.class)
  @JsonSerialize(using = ClientErrorCodeField.ClientErrorCodeFieldSerializer.class)
  protected EnumWrapper<ClientErrorCodeField> code;

  protected String message;

  @JsonProperty("context_info")
  protected Map<String, Object> contextInfo;

  @JsonProperty("help_url")
  protected String helpUrl;

  @JsonProperty("request_id")
  protected String requestId;

  public ClientError() {
    super();
  }

  protected ClientError(ClientErrorBuilder builder) {
    super();
    this.type = builder.type;
    this.status = builder.status;
    this.code = builder.code;
    this.message = builder.message;
    this.contextInfo = builder.contextInfo;
    this.helpUrl = builder.helpUrl;
    this.requestId = builder.requestId;
  }

  public EnumWrapper<ClientErrorTypeField> getType() {
    return type;
  }

  public Integer getStatus() {
    return status;
  }

  public EnumWrapper<ClientErrorCodeField> getCode() {
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
    ClientError casted = (ClientError) o;
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
    return "ClientError{"
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

  public static class ClientErrorBuilder {

    protected EnumWrapper<ClientErrorTypeField> type;

    protected Integer status;

    protected EnumWrapper<ClientErrorCodeField> code;

    protected String message;

    protected Map<String, Object> contextInfo;

    protected String helpUrl;

    protected String requestId;

    public ClientErrorBuilder type(ClientErrorTypeField type) {
      this.type = new EnumWrapper<ClientErrorTypeField>(type);
      return this;
    }

    public ClientErrorBuilder type(EnumWrapper<ClientErrorTypeField> type) {
      this.type = type;
      return this;
    }

    public ClientErrorBuilder status(Integer status) {
      this.status = status;
      return this;
    }

    public ClientErrorBuilder code(ClientErrorCodeField code) {
      this.code = new EnumWrapper<ClientErrorCodeField>(code);
      return this;
    }

    public ClientErrorBuilder code(EnumWrapper<ClientErrorCodeField> code) {
      this.code = code;
      return this;
    }

    public ClientErrorBuilder message(String message) {
      this.message = message;
      return this;
    }

    public ClientErrorBuilder contextInfo(Map<String, Object> contextInfo) {
      this.contextInfo = contextInfo;
      return this;
    }

    public ClientErrorBuilder helpUrl(String helpUrl) {
      this.helpUrl = helpUrl;
      return this;
    }

    public ClientErrorBuilder requestId(String requestId) {
      this.requestId = requestId;
      return this;
    }

    public ClientError build() {
      return new ClientError(this);
    }
  }
}
