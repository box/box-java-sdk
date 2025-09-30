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

  protected final String name;

  protected String login;

  @JsonProperty("is_platform_access_only")
  protected Boolean isPlatformAccessOnly;

  @JsonDeserialize(
      using = CreateUserRequestBodyRoleField.CreateUserRequestBodyRoleFieldDeserializer.class)
  @JsonSerialize(
      using = CreateUserRequestBodyRoleField.CreateUserRequestBodyRoleFieldSerializer.class)
  protected EnumWrapper<CreateUserRequestBodyRoleField> role;

  protected String language;

  @JsonProperty("is_sync_enabled")
  protected Boolean isSyncEnabled;

  @JsonProperty("job_title")
  protected String jobTitle;

  protected String phone;

  protected String address;

  @JsonProperty("space_amount")
  protected Long spaceAmount;

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

  @JsonDeserialize(
      using = CreateUserRequestBodyStatusField.CreateUserRequestBodyStatusFieldDeserializer.class)
  @JsonSerialize(
      using = CreateUserRequestBodyStatusField.CreateUserRequestBodyStatusFieldSerializer.class)
  protected EnumWrapper<CreateUserRequestBodyStatusField> status;

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
