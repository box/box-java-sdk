package com.box.sdkgen.schemas.v2025r0.clienterrorv2025r0;

import com.box.sdkgen.internal.SerializableObject;
import java.util.Objects;

public class ClientErrorV2025R0ContextInfoField extends SerializableObject {

  protected String message;

  public ClientErrorV2025R0ContextInfoField() {
    super();
  }

  protected ClientErrorV2025R0ContextInfoField(ClientErrorV2025R0ContextInfoFieldBuilder builder) {
    super();
    this.message = builder.message;
  }

  public String getMessage() {
    return message;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ClientErrorV2025R0ContextInfoField casted = (ClientErrorV2025R0ContextInfoField) o;
    return Objects.equals(message, casted.message);
  }

  @Override
  public int hashCode() {
    return Objects.hash(message);
  }

  @Override
  public String toString() {
    return "ClientErrorV2025R0ContextInfoField{" + "message='" + message + '\'' + "}";
  }

  public static class ClientErrorV2025R0ContextInfoFieldBuilder {

    protected String message;

    public ClientErrorV2025R0ContextInfoFieldBuilder message(String message) {
      this.message = message;
      return this;
    }

    public ClientErrorV2025R0ContextInfoField build() {
      return new ClientErrorV2025R0ContextInfoField(this);
    }
  }
}
