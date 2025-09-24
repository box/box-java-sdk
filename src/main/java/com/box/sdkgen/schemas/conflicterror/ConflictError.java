package com.box.sdkgen.schemas.conflicterror;

import com.box.sdkgen.schemas.clienterror.ClientError;
import com.box.sdkgen.schemas.clienterror.ClientErrorCodeField;
import com.box.sdkgen.schemas.clienterror.ClientErrorTypeField;
import com.box.sdkgen.serialization.json.EnumWrapper;
import com.fasterxml.jackson.annotation.JsonFilter;
import java.util.Map;
import java.util.Objects;

@JsonFilter("nullablePropertyFilter")
public class ConflictError extends ClientError {

  public ConflictError() {
    super();
  }

  protected ConflictError(Builder builder) {
    super(builder);
    markNullableFieldsAsSet(builder.getExplicitlySetNullableFields());
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ConflictError casted = (ConflictError) o;
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
    return "ConflictError{"
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

  public static class Builder extends ClientError.Builder {

    @Override
    public Builder type(ClientErrorTypeField type) {
      this.type = new EnumWrapper<ClientErrorTypeField>(type);
      return this;
    }

    @Override
    public Builder type(EnumWrapper<ClientErrorTypeField> type) {
      this.type = type;
      return this;
    }

    @Override
    public Builder status(Integer status) {
      this.status = status;
      return this;
    }

    @Override
    public Builder code(ClientErrorCodeField code) {
      this.code = new EnumWrapper<ClientErrorCodeField>(code);
      return this;
    }

    @Override
    public Builder code(EnumWrapper<ClientErrorCodeField> code) {
      this.code = code;
      return this;
    }

    @Override
    public Builder message(String message) {
      this.message = message;
      return this;
    }

    @Override
    public Builder contextInfo(Map<String, Object> contextInfo) {
      this.contextInfo = contextInfo;
      this.markNullableFieldAsSet("context_info");
      return this;
    }

    @Override
    public Builder helpUrl(String helpUrl) {
      this.helpUrl = helpUrl;
      return this;
    }

    @Override
    public Builder requestId(String requestId) {
      this.requestId = requestId;
      return this;
    }

    public ConflictError build() {
      return new ConflictError(this);
    }
  }
}
