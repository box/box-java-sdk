package com.box.sdkgen.schemas.user;

import com.box.sdkgen.schemas.userbase.UserBaseTypeField;
import com.box.sdkgen.schemas.usermini.UserMini;
import com.box.sdkgen.serialization.json.EnumWrapper;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import java.util.Objects;

public class User extends UserMini {

  @JsonProperty("created_at")
  protected String createdAt;

  @JsonProperty("modified_at")
  protected String modifiedAt;

  protected String language;

  protected String timezone;

  @JsonProperty("space_amount")
  protected Long spaceAmount;

  @JsonProperty("space_used")
  protected Long spaceUsed;

  @JsonProperty("max_upload_size")
  protected Long maxUploadSize;

  @JsonDeserialize(using = UserStatusField.UserStatusFieldDeserializer.class)
  @JsonSerialize(using = UserStatusField.UserStatusFieldSerializer.class)
  protected EnumWrapper<UserStatusField> status;

  @JsonProperty("job_title")
  protected String jobTitle;

  protected String phone;

  protected String address;

  @JsonProperty("avatar_url")
  protected String avatarUrl;

  @JsonProperty("notification_email")
  protected UserNotificationEmailField notificationEmail;

  public User(@JsonProperty("id") String id) {
    super(id);
  }

  protected User(UserBuilder builder) {
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
  }

  public String getCreatedAt() {
    return createdAt;
  }

  public String getModifiedAt() {
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

  public static class UserBuilder extends UserMiniBuilder {

    protected String createdAt;

    protected String modifiedAt;

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

    public UserBuilder(String id) {
      super(id);
    }

    public UserBuilder createdAt(String createdAt) {
      this.createdAt = createdAt;
      return this;
    }

    public UserBuilder modifiedAt(String modifiedAt) {
      this.modifiedAt = modifiedAt;
      return this;
    }

    public UserBuilder language(String language) {
      this.language = language;
      return this;
    }

    public UserBuilder timezone(String timezone) {
      this.timezone = timezone;
      return this;
    }

    public UserBuilder spaceAmount(Long spaceAmount) {
      this.spaceAmount = spaceAmount;
      return this;
    }

    public UserBuilder spaceUsed(Long spaceUsed) {
      this.spaceUsed = spaceUsed;
      return this;
    }

    public UserBuilder maxUploadSize(Long maxUploadSize) {
      this.maxUploadSize = maxUploadSize;
      return this;
    }

    public UserBuilder status(UserStatusField status) {
      this.status = new EnumWrapper<UserStatusField>(status);
      return this;
    }

    public UserBuilder status(EnumWrapper<UserStatusField> status) {
      this.status = status;
      return this;
    }

    public UserBuilder jobTitle(String jobTitle) {
      this.jobTitle = jobTitle;
      return this;
    }

    public UserBuilder phone(String phone) {
      this.phone = phone;
      return this;
    }

    public UserBuilder address(String address) {
      this.address = address;
      return this;
    }

    public UserBuilder avatarUrl(String avatarUrl) {
      this.avatarUrl = avatarUrl;
      return this;
    }

    public UserBuilder notificationEmail(UserNotificationEmailField notificationEmail) {
      this.notificationEmail = notificationEmail;
      return this;
    }

    @Override
    public UserBuilder type(UserBaseTypeField type) {
      this.type = new EnumWrapper<UserBaseTypeField>(type);
      return this;
    }

    @Override
    public UserBuilder type(EnumWrapper<UserBaseTypeField> type) {
      this.type = type;
      return this;
    }

    @Override
    public UserBuilder name(String name) {
      this.name = name;
      return this;
    }

    @Override
    public UserBuilder login(String login) {
      this.login = login;
      return this;
    }

    public User build() {
      return new User(this);
    }
  }
}
