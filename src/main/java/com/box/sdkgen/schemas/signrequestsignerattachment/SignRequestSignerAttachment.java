package com.box.sdkgen.schemas.signrequestsignerattachment;

import com.box.sdkgen.internal.Nullable;
import com.box.sdkgen.internal.NullableFieldTracker;
import com.box.sdkgen.internal.SerializableObject;
import com.fasterxml.jackson.annotation.JsonFilter;
import java.util.Objects;

/** Metadata describing a file uploaded by a signer as an attachment. */
@JsonFilter("nullablePropertyFilter")
public class SignRequestSignerAttachment extends SerializableObject {

  /** Identifier of the attachment file. */
  @Nullable protected String id;

  /** Display name of the attachment file. */
  @Nullable protected String name;

  public SignRequestSignerAttachment() {
    super();
  }

  protected SignRequestSignerAttachment(Builder builder) {
    super();
    this.id = builder.id;
    this.name = builder.name;
    markNullableFieldsAsSet(builder.getExplicitlySetNullableFields());
  }

  public String getId() {
    return id;
  }

  public String getName() {
    return name;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    SignRequestSignerAttachment casted = (SignRequestSignerAttachment) o;
    return Objects.equals(id, casted.id) && Objects.equals(name, casted.name);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, name);
  }

  @Override
  public String toString() {
    return "SignRequestSignerAttachment{"
        + "id='"
        + id
        + '\''
        + ", "
        + "name='"
        + name
        + '\''
        + "}";
  }

  public static class Builder extends NullableFieldTracker {

    protected String id;

    protected String name;

    public Builder id(String id) {
      this.id = id;
      this.markNullableFieldAsSet("id");
      return this;
    }

    public Builder name(String name) {
      this.name = name;
      this.markNullableFieldAsSet("name");
      return this;
    }

    public SignRequestSignerAttachment build() {
      return new SignRequestSignerAttachment(this);
    }
  }
}
