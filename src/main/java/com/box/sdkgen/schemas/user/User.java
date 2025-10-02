package com.box.sdkgen.schemas.user;

import com.box.sdkgen.internal.Nullable;
import com.box.sdkgen.internal.utils.DateTimeUtils;
import com.box.sdkgen.schemas.userbase.UserBaseTypeField;
import com.box.sdkgen.schemas.usermini.UserMini;
import com.box.sdkgen.serialization.json.EnumWrapper;
import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import java.time.OffsetDateTime;
import java.util.Objects;

/** A standard representation of a user, as returned from any user API endpoints by default. */
@JsonFilter("nullablePropertyFilter")
public class User extends UserMini {

  /** When the user object was created. */
  @JsonProperty("created_at")
  @JsonSerialize(using = DateTimeUtils.DateTimeSerializer.class)
  @JsonDeserialize(using = DateTimeUtils.DateTimeDeserializer.class)
  protected OffsetDateTime createdAt;

  /** When the user object was last modified. */
  @JsonProperty("modified_at")
  @JsonSerialize(using = DateTimeUtils.DateTimeSerializer.class)
  @JsonDeserialize(using = DateTimeUtils.DateTimeDeserializer.class)
  protected OffsetDateTime modifiedAt;

  /**
   * The language of the user, formatted in modified version of the [ISO
   * 639-1](/guides/api-calls/language-codes) format.
   */
  protected String language;

  /** The user's timezone. */
  protected String timezone;

  /** The user’s total available space amount in bytes. */
  @JsonProperty("space_amount")
  protected Long spaceAmount;

  /** The amount of space in use by the user. */
  @JsonProperty("space_used")
  protected Long spaceUsed;

  /** The maximum individual file size in bytes the user can have. */
  @JsonProperty("max_upload_size")
  protected Long maxUploadSize;

  /** The user's account status. */
  @JsonDeserialize(using = UserStatusField.UserStatusFieldDeserializer.class)
  @JsonSerialize(using = UserStatusField.UserStatusFieldSerializer.class)
  protected EnumWrapper<UserStatusField> status;

  /** The user’s job title. */
  @JsonProperty("job_title")
  protected String jobTitle;

  /** The user’s phone number. */
  protected String phone;

  /** The user’s address. */
  protected String address;

  /** URL of the user’s avatar image. */
  @JsonProperty("avatar_url")
  protected String avatarUrl;

  /**
   * An alternate notification email address to which email notifications are sent. When it's
   * confirmed, this will be the email address to which notifications are sent instead of to the
   * primary email address.
   */
  @JsonProperty("notification_email")
  @Nullable
  protected UserNotificationEmailField notificationEmail;

  public User(@JsonProperty("id") String id) {
    super(id);
  }

  protected User(Builder builder) {
    super(builder);
    this.createdAt = builder.createdAt;
    this.modifiedAt = builder.modifiedAt;
    this.language = builder.language;
    this.timezone = builder.timezone;
    this.spaceAmount = builder.spaceAmount;
    this.spaceUsed = builder.spaceUsed;
    this.maxUploadSize = builder.maxUploadSize;
    this.status = builder.status;
    this.jobTitle = builder.jobTitle;
    this.phone = builder.phone;
    this.address = builder.address;
    this.avatarUrl = builder.avatarUrl;
    this.notificationEmail = builder.notificationEmail;
    markNullableFieldsAsSet(builder.getExplicitlySetNullableFields());
  }

  public OffsetDateTime getCreatedAt() {
    return createdAt;
  }

  public OffsetDateTime getModifiedAt() {
    return modifiedAt;
  }

  public String getLanguage() {
    return language;
  }

  public String getTimezone() {
    return timezone;
  }

  public Long getSpaceAmount() {
    return spaceAmount;
  }

  public Long getSpaceUsed() {
    return spaceUsed;
  }

  public Long getMaxUploadSize() {
    return maxUploadSize;
  }

  public EnumWrapper<UserStatusField> getStatus() {
    return status;
  }

  public String getJobTitle() {
    return jobTitle;
  }

  public String getPhone() {
    return phone;
  }

  public String getAddress() {
    return address;
  }

  public String getAvatarUrl() {
    return avatarUrl;
  }

  public UserNotificationEmailField getNotificationEmail() {
    return notificationEmail;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    User casted = (User) o;
    return Objects.equals(id, casted.id)
        && Objects.equals(type, casted.type)
        && Objects.equals(name, casted.name)
        && Objects.equals(login, casted.login)
        && Objects.equals(createdAt, casted.createdAt)
        && Objects.equals(modifiedAt, casted.modifiedAt)
        && Objects.equals(language, casted.language)
        && Objects.equals(timezone, casted.timezone)
        && Objects.equals(spaceAmount, casted.spaceAmount)
        && Objects.equals(spaceUsed, casted.spaceUsed)
        && Objects.equals(maxUploadSize, casted.maxUploadSize)
        && Objects.equals(status, casted.status)
        && Objects.equals(jobTitle, casted.jobTitle)
        && Objects.equals(phone, casted.phone)
        && Objects.equals(address, casted.address)
        && Objects.equals(avatarUrl, casted.avatarUrl)
        && Objects.equals(notificationEmail, casted.notificationEmail);
  }

  @Override
  public int hashCode() {
    return Objects.hash(
        id,
        type,
        name,
        login,
        createdAt,
        modifiedAt,
        language,
        timezone,
        spaceAmount,
        spaceUsed,
        maxUploadSize,
        status,
        jobTitle,
        phone,
        address,
        avatarUrl,
        notificationEmail);
  }

  @Override
  public String toString() {
    return "User{"
        + "id='"
        + id
        + '\''
        + ", "
        + "type='"
        + type
        + '\''
        + ", "
        + "name='"
        + name
        + '\''
        + ", "
        + "login='"
        + login
        + '\''
        + ", "
        + "createdAt='"
        + createdAt
        + '\''
        + ", "
        + "modifiedAt='"
        + modifiedAt
        + '\''
        + ", "
        + "language='"
        + language
        + '\''
        + ", "
        + "timezone='"
        + timezone
        + '\''
        + ", "
        + "spaceAmount='"
        + spaceAmount
        + '\''
        + ", "
        + "spaceUsed='"
        + spaceUsed
        + '\''
        + ", "
        + "maxUploadSize='"
        + maxUploadSize
        + '\''
        + ", "
        + "status='"
        + status
        + '\''
        + ", "
        + "jobTitle='"
        + jobTitle
        + '\''
        + ", "
        + "phone='"
        + phone
        + '\''
        + ", "
        + "address='"
        + address
        + '\''
        + ", "
        + "avatarUrl='"
        + avatarUrl
        + '\''
        + ", "
        + "notificationEmail='"
        + notificationEmail
        + '\''
        + "}";
  }

  public static class Builder extends UserMini.Builder {

    protected OffsetDateTime createdAt;

    protected OffsetDateTime modifiedAt;

    protected String language;

    protected String timezone;

    protected Long spaceAmount;

    protected Long spaceUsed;

    protected Long maxUploadSize;

    protected EnumWrapper<UserStatusField> status;

    protected String jobTitle;

    protected String phone;

    protected String address;

    protected String avatarUrl;

    protected UserNotificationEmailField notificationEmail;

    public Builder(String id) {
      super(id);
    }

    public Builder createdAt(OffsetDateTime createdAt) {
      this.createdAt = createdAt;
      return this;
    }

    public Builder modifiedAt(OffsetDateTime modifiedAt) {
      this.modifiedAt = modifiedAt;
      return this;
    }

    public Builder language(String language) {
      this.language = language;
      return this;
    }

    public Builder timezone(String timezone) {
      this.timezone = timezone;
      return this;
    }

    public Builder spaceAmount(Long spaceAmount) {
      this.spaceAmount = spaceAmount;
      return this;
    }

    public Builder spaceUsed(Long spaceUsed) {
      this.spaceUsed = spaceUsed;
      return this;
    }

    public Builder maxUploadSize(Long maxUploadSize) {
      this.maxUploadSize = maxUploadSize;
      return this;
    }

    public Builder status(UserStatusField status) {
      this.status = new EnumWrapper<UserStatusField>(status);
      return this;
    }

    public Builder status(EnumWrapper<UserStatusField> status) {
      this.status = status;
      return this;
    }

    public Builder jobTitle(String jobTitle) {
      this.jobTitle = jobTitle;
      return this;
    }

    public Builder phone(String phone) {
      this.phone = phone;
      return this;
    }

    public Builder address(String address) {
      this.address = address;
      return this;
    }

    public Builder avatarUrl(String avatarUrl) {
      this.avatarUrl = avatarUrl;
      return this;
    }

    public Builder notificationEmail(UserNotificationEmailField notificationEmail) {
      this.notificationEmail = notificationEmail;
      this.markNullableFieldAsSet("notification_email");
      return this;
    }

    @Override
    public Builder type(UserBaseTypeField type) {
      this.type = new EnumWrapper<UserBaseTypeField>(type);
      return this;
    }

    @Override
    public Builder type(EnumWrapper<UserBaseTypeField> type) {
      this.type = type;
      return this;
    }

    @Override
    public Builder name(String name) {
      this.name = name;
      return this;
    }

    @Override
    public Builder login(String login) {
      this.login = login;
      return this;
    }

    public User build() {
      return new User(this);
    }
  }
}
