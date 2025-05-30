package com.box.sdkgen.schemas.emailalias;

import com.box.sdkgen.internal.SerializableObject;
import com.box.sdkgen.serialization.json.EnumWrapper;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import java.util.Objects;

public class EmailAlias extends SerializableObject {

  protected String id;

  @JsonDeserialize(using = EmailAliasTypeField.EmailAliasTypeFieldDeserializer.class)
  @JsonSerialize(using = EmailAliasTypeField.EmailAliasTypeFieldSerializer.class)
  protected EnumWrapper<EmailAliasTypeField> type;

  protected String email;

  @JsonProperty("is_confirmed")
  protected Boolean isConfirmed;

  public EmailAlias() {
    super();
  }

  protected EmailAlias(EmailAliasBuilder builder) {
    super();
    this.id = builder.id;
    this.type = builder.type;
    this.email = builder.email;
    this.isConfirmed = builder.isConfirmed;
  }

  public String getId() {
    return id;
  }

  public EnumWrapper<EmailAliasTypeField> getType() {
    return type;
  }

  public String getEmail() {
    return email;
  }

  public Boolean getIsConfirmed() {
    return isConfirmed;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    EmailAlias casted = (EmailAlias) o;
    return Objects.equals(id, casted.id)
        && Objects.equals(type, casted.type)
        && Objects.equals(email, casted.email)
        && Objects.equals(isConfirmed, casted.isConfirmed);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, type, email, isConfirmed);
  }

  @Override
  public String toString() {
    return "EmailAlias{"
        + "id='"
        + id
        + '\''
        + ", "
        + "type='"
        + type
        + '\''
        + ", "
        + "email='"
        + email
        + '\''
        + ", "
        + "isConfirmed='"
        + isConfirmed
        + '\''
        + "}";
  }

  public static class EmailAliasBuilder {

    protected String id;

    protected EnumWrapper<EmailAliasTypeField> type;

    protected String email;

    protected Boolean isConfirmed;

    public EmailAliasBuilder id(String id) {
      this.id = id;
      return this;
    }

    public EmailAliasBuilder type(EmailAliasTypeField type) {
      this.type = new EnumWrapper<EmailAliasTypeField>(type);
      return this;
    }

    public EmailAliasBuilder type(EnumWrapper<EmailAliasTypeField> type) {
      this.type = type;
      return this;
    }

    public EmailAliasBuilder email(String email) {
      this.email = email;
      return this;
    }

    public EmailAliasBuilder isConfirmed(Boolean isConfirmed) {
      this.isConfirmed = isConfirmed;
      return this;
    }

    public EmailAlias build() {
      return new EmailAlias(this);
    }
  }
}
