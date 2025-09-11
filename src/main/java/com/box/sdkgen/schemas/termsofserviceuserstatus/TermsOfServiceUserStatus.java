package com.box.sdkgen.schemas.termsofserviceuserstatus;

import com.box.sdkgen.internal.NullableFieldTracker;
import com.box.sdkgen.internal.SerializableObject;
import com.box.sdkgen.internal.utils.DateTimeUtils;
import com.box.sdkgen.schemas.termsofservicebase.TermsOfServiceBase;
import com.box.sdkgen.schemas.usermini.UserMini;
import com.box.sdkgen.serialization.json.EnumWrapper;
import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import java.time.OffsetDateTime;
import java.util.Objects;

@JsonFilter("nullablePropertyFilter")
public class TermsOfServiceUserStatus extends SerializableObject {

  protected final String id;

  @JsonDeserialize(
      using = TermsOfServiceUserStatusTypeField.TermsOfServiceUserStatusTypeFieldDeserializer.class)
  @JsonSerialize(
      using = TermsOfServiceUserStatusTypeField.TermsOfServiceUserStatusTypeFieldSerializer.class)
  protected EnumWrapper<TermsOfServiceUserStatusTypeField> type;

  protected TermsOfServiceBase tos;

  protected UserMini user;

  @JsonProperty("is_accepted")
  protected Boolean isAccepted;

  @JsonProperty("created_at")
  @JsonSerialize(using = DateTimeUtils.DateTimeSerializer.class)
  @JsonDeserialize(using = DateTimeUtils.DateTimeDeserializer.class)
  protected OffsetDateTime createdAt;

  @JsonProperty("modified_at")
  @JsonSerialize(using = DateTimeUtils.DateTimeSerializer.class)
  @JsonDeserialize(using = DateTimeUtils.DateTimeDeserializer.class)
  protected OffsetDateTime modifiedAt;

  public TermsOfServiceUserStatus(@JsonProperty("id") String id) {
    super();
    this.id = id;
    this.type =
        new EnumWrapper<TermsOfServiceUserStatusTypeField>(
            TermsOfServiceUserStatusTypeField.TERMS_OF_SERVICE_USER_STATUS);
  }

  protected TermsOfServiceUserStatus(Builder builder) {
    super();
    this.id = builder.id;
    this.type = builder.type;
    this.tos = builder.tos;
    this.user = builder.user;
    this.isAccepted = builder.isAccepted;
    this.createdAt = builder.createdAt;
    this.modifiedAt = builder.modifiedAt;
    markNullableFieldsAsSet(builder.getExplicitlySetNullableFields());
  }

  public String getId() {
    return id;
  }

  public EnumWrapper<TermsOfServiceUserStatusTypeField> getType() {
    return type;
  }

  public TermsOfServiceBase getTos() {
    return tos;
  }

  public UserMini getUser() {
    return user;
  }

  public Boolean getIsAccepted() {
    return isAccepted;
  }

  public OffsetDateTime getCreatedAt() {
    return createdAt;
  }

  public OffsetDateTime getModifiedAt() {
    return modifiedAt;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    TermsOfServiceUserStatus casted = (TermsOfServiceUserStatus) o;
    return Objects.equals(id, casted.id)
        && Objects.equals(type, casted.type)
        && Objects.equals(tos, casted.tos)
        && Objects.equals(user, casted.user)
        && Objects.equals(isAccepted, casted.isAccepted)
        && Objects.equals(createdAt, casted.createdAt)
        && Objects.equals(modifiedAt, casted.modifiedAt);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, type, tos, user, isAccepted, createdAt, modifiedAt);
  }

  @Override
  public String toString() {
    return "TermsOfServiceUserStatus{"
        + "id='"
        + id
        + '\''
        + ", "
        + "type='"
        + type
        + '\''
        + ", "
        + "tos='"
        + tos
        + '\''
        + ", "
        + "user='"
        + user
        + '\''
        + ", "
        + "isAccepted='"
        + isAccepted
        + '\''
        + ", "
        + "createdAt='"
        + createdAt
        + '\''
        + ", "
        + "modifiedAt='"
        + modifiedAt
        + '\''
        + "}";
  }

  public static class Builder extends NullableFieldTracker {

    protected final String id;

    protected EnumWrapper<TermsOfServiceUserStatusTypeField> type;

    protected TermsOfServiceBase tos;

    protected UserMini user;

    protected Boolean isAccepted;

    protected OffsetDateTime createdAt;

    protected OffsetDateTime modifiedAt;

    public Builder(String id) {
      super();
      this.id = id;
      this.type =
          new EnumWrapper<TermsOfServiceUserStatusTypeField>(
              TermsOfServiceUserStatusTypeField.TERMS_OF_SERVICE_USER_STATUS);
    }

    public Builder type(TermsOfServiceUserStatusTypeField type) {
      this.type = new EnumWrapper<TermsOfServiceUserStatusTypeField>(type);
      return this;
    }

    public Builder type(EnumWrapper<TermsOfServiceUserStatusTypeField> type) {
      this.type = type;
      return this;
    }

    public Builder tos(TermsOfServiceBase tos) {
      this.tos = tos;
      return this;
    }

    public Builder user(UserMini user) {
      this.user = user;
      return this;
    }

    public Builder isAccepted(Boolean isAccepted) {
      this.isAccepted = isAccepted;
      return this;
    }

    public Builder createdAt(OffsetDateTime createdAt) {
      this.createdAt = createdAt;
      return this;
    }

    public Builder modifiedAt(OffsetDateTime modifiedAt) {
      this.modifiedAt = modifiedAt;
      return this;
    }

    public TermsOfServiceUserStatus build() {
      return new TermsOfServiceUserStatus(this);
    }
  }
}
