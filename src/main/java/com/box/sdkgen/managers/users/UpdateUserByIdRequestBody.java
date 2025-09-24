package com.box.sdkgen.managers.users;

import com.box.sdkgen.internal.Nullable;
import com.box.sdkgen.internal.NullableFieldTracker;
import com.box.sdkgen.internal.SerializableObject;
import com.box.sdkgen.schemas.trackingcode.TrackingCode;
import com.box.sdkgen.serialization.json.EnumWrapper;
import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import java.util.List;
import java.util.Objects;

@JsonFilter("nullablePropertyFilter")
public class UpdateUserByIdRequestBody extends SerializableObject {

  @Nullable protected String enterprise;

  protected Boolean notify;

  protected String name;

  protected String login;

  @JsonDeserialize(
      using =
          UpdateUserByIdRequestBodyRoleField.UpdateUserByIdRequestBodyRoleFieldDeserializer.class)
  @JsonSerialize(
      using = UpdateUserByIdRequestBodyRoleField.UpdateUserByIdRequestBodyRoleFieldSerializer.class)
  protected EnumWrapper<UpdateUserByIdRequestBodyRoleField> role;

  protected String language;

  @JsonProperty("is_sync_enabled")
  protected Boolean isSyncEnabled;

  @JsonProperty("job_title")
  protected String jobTitle;

  protected String phone;

  protected String address;

  @JsonProperty("tracking_codes")
  protected List<TrackingCode> trackingCodes;

  @JsonProperty("can_see_managed_users")
  protected Boolean canSeeManagedUsers;

  protected String timezone;

  @JsonProperty("is_external_collab_restricted")
  protected Boolean isExternalCollabRestricted;

  @JsonProperty("is_exempt_from_device_limits")
  protected Boolean isExemptFromDeviceLimits;

  @JsonProperty("is_exempt_from_login_verification")
  protected Boolean isExemptFromLoginVerification;

  @JsonProperty("is_password_reset_required")
  protected Boolean isPasswordResetRequired;

  @JsonDeserialize(
      using =
          UpdateUserByIdRequestBodyStatusField.UpdateUserByIdRequestBodyStatusFieldDeserializer
              .class)
  @JsonSerialize(
      using =
          UpdateUserByIdRequestBodyStatusField.UpdateUserByIdRequestBodyStatusFieldSerializer.class)
  protected EnumWrapper<UpdateUserByIdRequestBodyStatusField> status;

  @JsonProperty("space_amount")
  protected Long spaceAmount;

  @JsonProperty("notification_email")
  @Nullable
  protected UpdateUserByIdRequestBodyNotificationEmailField notificationEmail;

  @JsonProperty("external_app_user_id")
  protected String externalAppUserId;

  public UpdateUserByIdRequestBody() {
    super();
  }

  protected UpdateUserByIdRequestBody(Builder builder) {
    super();
    this.enterprise = builder.enterprise;
    this.notify = builder.notify;
    this.name = builder.name;
    this.login = builder.login;
    this.role = builder.role;
    this.language = builder.language;
    this.isSyncEnabled = builder.isSyncEnabled;
    this.jobTitle = builder.jobTitle;
    this.phone = builder.phone;
    this.address = builder.address;
    this.trackingCodes = builder.trackingCodes;
    this.canSeeManagedUsers = builder.canSeeManagedUsers;
    this.timezone = builder.timezone;
    this.isExternalCollabRestricted = builder.isExternalCollabRestricted;
    this.isExemptFromDeviceLimits = builder.isExemptFromDeviceLimits;
    this.isExemptFromLoginVerification = builder.isExemptFromLoginVerification;
    this.isPasswordResetRequired = builder.isPasswordResetRequired;
    this.status = builder.status;
    this.spaceAmount = builder.spaceAmount;
    this.notificationEmail = builder.notificationEmail;
    this.externalAppUserId = builder.externalAppUserId;
    markNullableFieldsAsSet(builder.getExplicitlySetNullableFields());
  }

  public String getEnterprise() {
    return enterprise;
  }

  public Boolean getNotify() {
    return notify;
  }

  public String getName() {
    return name;
  }

  public String getLogin() {
    return login;
  }

  public EnumWrapper<UpdateUserByIdRequestBodyRoleField> getRole() {
    return role;
  }

  public String getLanguage() {
    return language;
  }

  public Boolean getIsSyncEnabled() {
    return isSyncEnabled;
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

  public List<TrackingCode> getTrackingCodes() {
    return trackingCodes;
  }

  public Boolean getCanSeeManagedUsers() {
    return canSeeManagedUsers;
  }

  public String getTimezone() {
    return timezone;
  }

  public Boolean getIsExternalCollabRestricted() {
    return isExternalCollabRestricted;
  }

  public Boolean getIsExemptFromDeviceLimits() {
    return isExemptFromDeviceLimits;
  }

  public Boolean getIsExemptFromLoginVerification() {
    return isExemptFromLoginVerification;
  }

  public Boolean getIsPasswordResetRequired() {
    return isPasswordResetRequired;
  }

  public EnumWrapper<UpdateUserByIdRequestBodyStatusField> getStatus() {
    return status;
  }

  public Long getSpaceAmount() {
    return spaceAmount;
  }

  public UpdateUserByIdRequestBodyNotificationEmailField getNotificationEmail() {
    return notificationEmail;
  }

  public String getExternalAppUserId() {
    return externalAppUserId;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    UpdateUserByIdRequestBody casted = (UpdateUserByIdRequestBody) o;
    return Objects.equals(enterprise, casted.enterprise)
        && Objects.equals(notify, casted.notify)
        && Objects.equals(name, casted.name)
        && Objects.equals(login, casted.login)
        && Objects.equals(role, casted.role)
        && Objects.equals(language, casted.language)
        && Objects.equals(isSyncEnabled, casted.isSyncEnabled)
        && Objects.equals(jobTitle, casted.jobTitle)
        && Objects.equals(phone, casted.phone)
        && Objects.equals(address, casted.address)
        && Objects.equals(trackingCodes, casted.trackingCodes)
        && Objects.equals(canSeeManagedUsers, casted.canSeeManagedUsers)
        && Objects.equals(timezone, casted.timezone)
        && Objects.equals(isExternalCollabRestricted, casted.isExternalCollabRestricted)
        && Objects.equals(isExemptFromDeviceLimits, casted.isExemptFromDeviceLimits)
        && Objects.equals(isExemptFromLoginVerification, casted.isExemptFromLoginVerification)
        && Objects.equals(isPasswordResetRequired, casted.isPasswordResetRequired)
        && Objects.equals(status, casted.status)
        && Objects.equals(spaceAmount, casted.spaceAmount)
        && Objects.equals(notificationEmail, casted.notificationEmail)
        && Objects.equals(externalAppUserId, casted.externalAppUserId);
  }

  @Override
  public int hashCode() {
    return Objects.hash(
        enterprise,
        notify,
        name,
        login,
        role,
        language,
        isSyncEnabled,
        jobTitle,
        phone,
        address,
        trackingCodes,
        canSeeManagedUsers,
        timezone,
        isExternalCollabRestricted,
        isExemptFromDeviceLimits,
        isExemptFromLoginVerification,
        isPasswordResetRequired,
        status,
        spaceAmount,
        notificationEmail,
        externalAppUserId);
  }

  @Override
  public String toString() {
    return "UpdateUserByIdRequestBody{"
        + "enterprise='"
        + enterprise
        + '\''
        + ", "
        + "notify='"
        + notify
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
        + "role='"
        + role
        + '\''
        + ", "
        + "language='"
        + language
        + '\''
        + ", "
        + "isSyncEnabled='"
        + isSyncEnabled
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
        + "trackingCodes='"
        + trackingCodes
        + '\''
        + ", "
        + "canSeeManagedUsers='"
        + canSeeManagedUsers
        + '\''
        + ", "
        + "timezone='"
        + timezone
        + '\''
        + ", "
        + "isExternalCollabRestricted='"
        + isExternalCollabRestricted
        + '\''
        + ", "
        + "isExemptFromDeviceLimits='"
        + isExemptFromDeviceLimits
        + '\''
        + ", "
        + "isExemptFromLoginVerification='"
        + isExemptFromLoginVerification
        + '\''
        + ", "
        + "isPasswordResetRequired='"
        + isPasswordResetRequired
        + '\''
        + ", "
        + "status='"
        + status
        + '\''
        + ", "
        + "spaceAmount='"
        + spaceAmount
        + '\''
        + ", "
        + "notificationEmail='"
        + notificationEmail
        + '\''
        + ", "
        + "externalAppUserId='"
        + externalAppUserId
        + '\''
        + "}";
  }

  public static class Builder extends NullableFieldTracker {

    protected String enterprise;

    protected Boolean notify;

    protected String name;

    protected String login;

    protected EnumWrapper<UpdateUserByIdRequestBodyRoleField> role;

    protected String language;

    protected Boolean isSyncEnabled;

    protected String jobTitle;

    protected String phone;

    protected String address;

    protected List<TrackingCode> trackingCodes;

    protected Boolean canSeeManagedUsers;

    protected String timezone;

    protected Boolean isExternalCollabRestricted;

    protected Boolean isExemptFromDeviceLimits;

    protected Boolean isExemptFromLoginVerification;

    protected Boolean isPasswordResetRequired;

    protected EnumWrapper<UpdateUserByIdRequestBodyStatusField> status;

    protected Long spaceAmount;

    protected UpdateUserByIdRequestBodyNotificationEmailField notificationEmail;

    protected String externalAppUserId;

    public Builder enterprise(String enterprise) {
      this.enterprise = enterprise;
      this.markNullableFieldAsSet("enterprise");
      return this;
    }

    public Builder notify(Boolean notify) {
      this.notify = notify;
      return this;
    }

    public Builder name(String name) {
      this.name = name;
      return this;
    }

    public Builder login(String login) {
      this.login = login;
      return this;
    }

    public Builder role(UpdateUserByIdRequestBodyRoleField role) {
      this.role = new EnumWrapper<UpdateUserByIdRequestBodyRoleField>(role);
      return this;
    }

    public Builder role(EnumWrapper<UpdateUserByIdRequestBodyRoleField> role) {
      this.role = role;
      return this;
    }

    public Builder language(String language) {
      this.language = language;
      return this;
    }

    public Builder isSyncEnabled(Boolean isSyncEnabled) {
      this.isSyncEnabled = isSyncEnabled;
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

    public Builder trackingCodes(List<TrackingCode> trackingCodes) {
      this.trackingCodes = trackingCodes;
      return this;
    }

    public Builder canSeeManagedUsers(Boolean canSeeManagedUsers) {
      this.canSeeManagedUsers = canSeeManagedUsers;
      return this;
    }

    public Builder timezone(String timezone) {
      this.timezone = timezone;
      return this;
    }

    public Builder isExternalCollabRestricted(Boolean isExternalCollabRestricted) {
      this.isExternalCollabRestricted = isExternalCollabRestricted;
      return this;
    }

    public Builder isExemptFromDeviceLimits(Boolean isExemptFromDeviceLimits) {
      this.isExemptFromDeviceLimits = isExemptFromDeviceLimits;
      return this;
    }

    public Builder isExemptFromLoginVerification(Boolean isExemptFromLoginVerification) {
      this.isExemptFromLoginVerification = isExemptFromLoginVerification;
      return this;
    }

    public Builder isPasswordResetRequired(Boolean isPasswordResetRequired) {
      this.isPasswordResetRequired = isPasswordResetRequired;
      return this;
    }

    public Builder status(UpdateUserByIdRequestBodyStatusField status) {
      this.status = new EnumWrapper<UpdateUserByIdRequestBodyStatusField>(status);
      return this;
    }

    public Builder status(EnumWrapper<UpdateUserByIdRequestBodyStatusField> status) {
      this.status = status;
      return this;
    }

    public Builder spaceAmount(Long spaceAmount) {
      this.spaceAmount = spaceAmount;
      return this;
    }

    public Builder notificationEmail(
        UpdateUserByIdRequestBodyNotificationEmailField notificationEmail) {
      this.notificationEmail = notificationEmail;
      this.markNullableFieldAsSet("notification_email");
      return this;
    }

    public Builder externalAppUserId(String externalAppUserId) {
      this.externalAppUserId = externalAppUserId;
      return this;
    }

    public UpdateUserByIdRequestBody build() {
      return new UpdateUserByIdRequestBody(this);
    }
  }
}
