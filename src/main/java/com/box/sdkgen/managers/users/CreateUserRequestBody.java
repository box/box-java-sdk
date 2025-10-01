package com.box.sdkgen.managers.users;

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
public class CreateUserRequestBody extends SerializableObject {

  /** The name of the user. */
  protected final String name;

  /**
   * The email address the user uses to log in
   *
   * <p>Required, unless `is_platform_access_only` is set to `true`.
   */
  protected String login;

  /** Specifies that the user is an app user. */
  @JsonProperty("is_platform_access_only")
  protected Boolean isPlatformAccessOnly;

  /** The user’s enterprise role. */
  @JsonDeserialize(
      using = CreateUserRequestBodyRoleField.CreateUserRequestBodyRoleFieldDeserializer.class)
  @JsonSerialize(
      using = CreateUserRequestBodyRoleField.CreateUserRequestBodyRoleFieldSerializer.class)
  protected EnumWrapper<CreateUserRequestBodyRoleField> role;

  /**
   * The language of the user, formatted in modified version of the [ISO
   * 639-1](/guides/api-calls/language-codes) format.
   */
  protected String language;

  /** Whether the user can use Box Sync. */
  @JsonProperty("is_sync_enabled")
  protected Boolean isSyncEnabled;

  /** The user’s job title. */
  @JsonProperty("job_title")
  protected String jobTitle;

  /** The user’s phone number. */
  protected String phone;

  /** The user’s address. */
  protected String address;

  /** The user’s total available space in bytes. Set this to `-1` to indicate unlimited storage. */
  @JsonProperty("space_amount")
  protected Long spaceAmount;

  /**
   * Tracking codes allow an admin to generate reports from the admin console and assign an
   * attribute to a specific group of users. This setting must be enabled for an enterprise before
   * it can be used.
   */
  @JsonProperty("tracking_codes")
  protected List<TrackingCode> trackingCodes;

  /** Whether the user can see other enterprise users in their contact list. */
  @JsonProperty("can_see_managed_users")
  protected Boolean canSeeManagedUsers;

  /** The user's timezone. */
  protected String timezone;

  /** Whether the user is allowed to collaborate with users outside their enterprise. */
  @JsonProperty("is_external_collab_restricted")
  protected Boolean isExternalCollabRestricted;

  /** Whether to exempt the user from enterprise device limits. */
  @JsonProperty("is_exempt_from_device_limits")
  protected Boolean isExemptFromDeviceLimits;

  /** Whether the user must use two-factor authentication. */
  @JsonProperty("is_exempt_from_login_verification")
  protected Boolean isExemptFromLoginVerification;

  /** The user's account status. */
  @JsonDeserialize(
      using = CreateUserRequestBodyStatusField.CreateUserRequestBodyStatusFieldDeserializer.class)
  @JsonSerialize(
      using = CreateUserRequestBodyStatusField.CreateUserRequestBodyStatusFieldSerializer.class)
  protected EnumWrapper<CreateUserRequestBodyStatusField> status;

  /**
   * An external identifier for an app user, which can be used to look up the user. This can be used
   * to tie user IDs from external identity providers to Box users.
   */
  @JsonProperty("external_app_user_id")
  protected String externalAppUserId;

  public CreateUserRequestBody(@JsonProperty("name") String name) {
    super();
    this.name = name;
  }

  protected CreateUserRequestBody(Builder builder) {
    super();
    this.name = builder.name;
    this.login = builder.login;
    this.isPlatformAccessOnly = builder.isPlatformAccessOnly;
    this.role = builder.role;
    this.language = builder.language;
    this.isSyncEnabled = builder.isSyncEnabled;
    this.jobTitle = builder.jobTitle;
    this.phone = builder.phone;
    this.address = builder.address;
    this.spaceAmount = builder.spaceAmount;
    this.trackingCodes = builder.trackingCodes;
    this.canSeeManagedUsers = builder.canSeeManagedUsers;
    this.timezone = builder.timezone;
    this.isExternalCollabRestricted = builder.isExternalCollabRestricted;
    this.isExemptFromDeviceLimits = builder.isExemptFromDeviceLimits;
    this.isExemptFromLoginVerification = builder.isExemptFromLoginVerification;
    this.status = builder.status;
    this.externalAppUserId = builder.externalAppUserId;
    markNullableFieldsAsSet(builder.getExplicitlySetNullableFields());
  }

  public String getName() {
    return name;
  }

  public String getLogin() {
    return login;
  }

  public Boolean getIsPlatformAccessOnly() {
    return isPlatformAccessOnly;
  }

  public EnumWrapper<CreateUserRequestBodyRoleField> getRole() {
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

  public Long getSpaceAmount() {
    return spaceAmount;
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

  public EnumWrapper<CreateUserRequestBodyStatusField> getStatus() {
    return status;
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
    CreateUserRequestBody casted = (CreateUserRequestBody) o;
    return Objects.equals(name, casted.name)
        && Objects.equals(login, casted.login)
        && Objects.equals(isPlatformAccessOnly, casted.isPlatformAccessOnly)
        && Objects.equals(role, casted.role)
        && Objects.equals(language, casted.language)
        && Objects.equals(isSyncEnabled, casted.isSyncEnabled)
        && Objects.equals(jobTitle, casted.jobTitle)
        && Objects.equals(phone, casted.phone)
        && Objects.equals(address, casted.address)
        && Objects.equals(spaceAmount, casted.spaceAmount)
        && Objects.equals(trackingCodes, casted.trackingCodes)
        && Objects.equals(canSeeManagedUsers, casted.canSeeManagedUsers)
        && Objects.equals(timezone, casted.timezone)
        && Objects.equals(isExternalCollabRestricted, casted.isExternalCollabRestricted)
        && Objects.equals(isExemptFromDeviceLimits, casted.isExemptFromDeviceLimits)
        && Objects.equals(isExemptFromLoginVerification, casted.isExemptFromLoginVerification)
        && Objects.equals(status, casted.status)
        && Objects.equals(externalAppUserId, casted.externalAppUserId);
  }

  @Override
  public int hashCode() {
    return Objects.hash(
        name,
        login,
        isPlatformAccessOnly,
        role,
        language,
        isSyncEnabled,
        jobTitle,
        phone,
        address,
        spaceAmount,
        trackingCodes,
        canSeeManagedUsers,
        timezone,
        isExternalCollabRestricted,
        isExemptFromDeviceLimits,
        isExemptFromLoginVerification,
        status,
        externalAppUserId);
  }

  @Override
  public String toString() {
    return "CreateUserRequestBody{"
        + "name='"
        + name
        + '\''
        + ", "
        + "login='"
        + login
        + '\''
        + ", "
        + "isPlatformAccessOnly='"
        + isPlatformAccessOnly
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
        + "spaceAmount='"
        + spaceAmount
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
        + "status='"
        + status
        + '\''
        + ", "
        + "externalAppUserId='"
        + externalAppUserId
        + '\''
        + "}";
  }

  public static class Builder extends NullableFieldTracker {

    protected final String name;

    protected String login;

    protected Boolean isPlatformAccessOnly;

    protected EnumWrapper<CreateUserRequestBodyRoleField> role;

    protected String language;

    protected Boolean isSyncEnabled;

    protected String jobTitle;

    protected String phone;

    protected String address;

    protected Long spaceAmount;

    protected List<TrackingCode> trackingCodes;

    protected Boolean canSeeManagedUsers;

    protected String timezone;

    protected Boolean isExternalCollabRestricted;

    protected Boolean isExemptFromDeviceLimits;

    protected Boolean isExemptFromLoginVerification;

    protected EnumWrapper<CreateUserRequestBodyStatusField> status;

    protected String externalAppUserId;

    public Builder(String name) {
      super();
      this.name = name;
    }

    public Builder login(String login) {
      this.login = login;
      return this;
    }

    public Builder isPlatformAccessOnly(Boolean isPlatformAccessOnly) {
      this.isPlatformAccessOnly = isPlatformAccessOnly;
      return this;
    }

    public Builder role(CreateUserRequestBodyRoleField role) {
      this.role = new EnumWrapper<CreateUserRequestBodyRoleField>(role);
      return this;
    }

    public Builder role(EnumWrapper<CreateUserRequestBodyRoleField> role) {
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

    public Builder spaceAmount(Long spaceAmount) {
      this.spaceAmount = spaceAmount;
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

    public Builder status(CreateUserRequestBodyStatusField status) {
      this.status = new EnumWrapper<CreateUserRequestBodyStatusField>(status);
      return this;
    }

    public Builder status(EnumWrapper<CreateUserRequestBodyStatusField> status) {
      this.status = status;
      return this;
    }

    public Builder externalAppUserId(String externalAppUserId) {
      this.externalAppUserId = externalAppUserId;
      return this;
    }

    public CreateUserRequestBody build() {
      return new CreateUserRequestBody(this);
    }
  }
}
