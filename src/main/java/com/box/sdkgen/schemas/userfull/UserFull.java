package com.box.sdkgen.schemas.userfull;

import com.box.sdkgen.schemas.trackingcode.TrackingCode;
import com.box.sdkgen.schemas.user.User;
import com.box.sdkgen.schemas.user.UserNotificationEmailField;
import com.box.sdkgen.schemas.user.UserStatusField;
import com.box.sdkgen.schemas.userbase.UserBaseTypeField;
import com.box.sdkgen.serialization.json.EnumWrapper;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import java.util.List;
import java.util.Objects;

public class UserFull extends User {

  @JsonDeserialize(using = UserFullRoleField.UserFullRoleFieldDeserializer.class)
  @JsonSerialize(using = UserFullRoleField.UserFullRoleFieldSerializer.class)
  protected EnumWrapper<UserFullRoleField> role;

  @JsonProperty("tracking_codes")
  protected List<TrackingCode> trackingCodes;

  @JsonProperty("can_see_managed_users")
  protected Boolean canSeeManagedUsers;

  @JsonProperty("is_sync_enabled")
  protected Boolean isSyncEnabled;

  @JsonProperty("is_external_collab_restricted")
  protected Boolean isExternalCollabRestricted;

  @JsonProperty("is_exempt_from_device_limits")
  protected Boolean isExemptFromDeviceLimits;

  @JsonProperty("is_exempt_from_login_verification")
  protected Boolean isExemptFromLoginVerification;

  protected UserFullEnterpriseField enterprise;

  @JsonProperty("my_tags")
  protected List<String> myTags;

  protected String hostname;

  @JsonProperty("is_platform_access_only")
  protected Boolean isPlatformAccessOnly;

  @JsonProperty("external_app_user_id")
  protected String externalAppUserId;

  public UserFull(@JsonProperty("id") String id) {
    super(id);
  }

  protected UserFull(UserFullBuilder builder) {
    super(builder);
    this.role = builder.role;
    this.trackingCodes = builder.trackingCodes;
    this.canSeeManagedUsers = builder.canSeeManagedUsers;
    this.isSyncEnabled = builder.isSyncEnabled;
    this.isExternalCollabRestricted = builder.isExternalCollabRestricted;
    this.isExemptFromDeviceLimits = builder.isExemptFromDeviceLimits;
    this.isExemptFromLoginVerification = builder.isExemptFromLoginVerification;
    this.enterprise = builder.enterprise;
    this.myTags = builder.myTags;
    this.hostname = builder.hostname;
    this.isPlatformAccessOnly = builder.isPlatformAccessOnly;
    this.externalAppUserId = builder.externalAppUserId;
  }

  public EnumWrapper<UserFullRoleField> getRole() {
    return role;
  }

  public List<TrackingCode> getTrackingCodes() {
    return trackingCodes;
  }

  public Boolean getCanSeeManagedUsers() {
    return canSeeManagedUsers;
  }

  public Boolean getIsSyncEnabled() {
    return isSyncEnabled;
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

  public UserFullEnterpriseField getEnterprise() {
    return enterprise;
  }

  public List<String> getMyTags() {
    return myTags;
  }

  public String getHostname() {
    return hostname;
  }

  public Boolean getIsPlatformAccessOnly() {
    return isPlatformAccessOnly;
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
    UserFull casted = (UserFull) o;
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
        && Objects.equals(notificationEmail, casted.notificationEmail)
        && Objects.equals(role, casted.role)
        && Objects.equals(trackingCodes, casted.trackingCodes)
        && Objects.equals(canSeeManagedUsers, casted.canSeeManagedUsers)
        && Objects.equals(isSyncEnabled, casted.isSyncEnabled)
        && Objects.equals(isExternalCollabRestricted, casted.isExternalCollabRestricted)
        && Objects.equals(isExemptFromDeviceLimits, casted.isExemptFromDeviceLimits)
        && Objects.equals(isExemptFromLoginVerification, casted.isExemptFromLoginVerification)
        && Objects.equals(enterprise, casted.enterprise)
        && Objects.equals(myTags, casted.myTags)
        && Objects.equals(hostname, casted.hostname)
        && Objects.equals(isPlatformAccessOnly, casted.isPlatformAccessOnly)
        && Objects.equals(externalAppUserId, casted.externalAppUserId);
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
        notificationEmail,
        role,
        trackingCodes,
        canSeeManagedUsers,
        isSyncEnabled,
        isExternalCollabRestricted,
        isExemptFromDeviceLimits,
        isExemptFromLoginVerification,
        enterprise,
        myTags,
        hostname,
        isPlatformAccessOnly,
        externalAppUserId);
  }

  @Override
  public String toString() {
    return "UserFull{"
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
        + ", "
        + "role='"
        + role
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
        + "isSyncEnabled='"
        + isSyncEnabled
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
        + "enterprise='"
        + enterprise
        + '\''
        + ", "
        + "myTags='"
        + myTags
        + '\''
        + ", "
        + "hostname='"
        + hostname
        + '\''
        + ", "
        + "isPlatformAccessOnly='"
        + isPlatformAccessOnly
        + '\''
        + ", "
        + "externalAppUserId='"
        + externalAppUserId
        + '\''
        + "}";
  }

  public static class UserFullBuilder extends UserBuilder {

    protected EnumWrapper<UserFullRoleField> role;

    protected List<TrackingCode> trackingCodes;

    protected Boolean canSeeManagedUsers;

    protected Boolean isSyncEnabled;

    protected Boolean isExternalCollabRestricted;

    protected Boolean isExemptFromDeviceLimits;

    protected Boolean isExemptFromLoginVerification;

    protected UserFullEnterpriseField enterprise;

    protected List<String> myTags;

    protected String hostname;

    protected Boolean isPlatformAccessOnly;

    protected String externalAppUserId;

    public UserFullBuilder(String id) {
      super(id);
    }

    public UserFullBuilder role(UserFullRoleField role) {
      this.role = new EnumWrapper<UserFullRoleField>(role);
      return this;
    }

    public UserFullBuilder role(EnumWrapper<UserFullRoleField> role) {
      this.role = role;
      return this;
    }

    public UserFullBuilder trackingCodes(List<TrackingCode> trackingCodes) {
      this.trackingCodes = trackingCodes;
      return this;
    }

    public UserFullBuilder canSeeManagedUsers(Boolean canSeeManagedUsers) {
      this.canSeeManagedUsers = canSeeManagedUsers;
      return this;
    }

    public UserFullBuilder isSyncEnabled(Boolean isSyncEnabled) {
      this.isSyncEnabled = isSyncEnabled;
      return this;
    }

    public UserFullBuilder isExternalCollabRestricted(Boolean isExternalCollabRestricted) {
      this.isExternalCollabRestricted = isExternalCollabRestricted;
      return this;
    }

    public UserFullBuilder isExemptFromDeviceLimits(Boolean isExemptFromDeviceLimits) {
      this.isExemptFromDeviceLimits = isExemptFromDeviceLimits;
      return this;
    }

    public UserFullBuilder isExemptFromLoginVerification(Boolean isExemptFromLoginVerification) {
      this.isExemptFromLoginVerification = isExemptFromLoginVerification;
      return this;
    }

    public UserFullBuilder enterprise(UserFullEnterpriseField enterprise) {
      this.enterprise = enterprise;
      return this;
    }

    public UserFullBuilder myTags(List<String> myTags) {
      this.myTags = myTags;
      return this;
    }

    public UserFullBuilder hostname(String hostname) {
      this.hostname = hostname;
      return this;
    }

    public UserFullBuilder isPlatformAccessOnly(Boolean isPlatformAccessOnly) {
      this.isPlatformAccessOnly = isPlatformAccessOnly;
      return this;
    }

    public UserFullBuilder externalAppUserId(String externalAppUserId) {
      this.externalAppUserId = externalAppUserId;
      return this;
    }

    @Override
    public UserFullBuilder type(UserBaseTypeField type) {
      this.type = new EnumWrapper<UserBaseTypeField>(type);
      return this;
    }

    @Override
    public UserFullBuilder type(EnumWrapper<UserBaseTypeField> type) {
      this.type = type;
      return this;
    }

    @Override
    public UserFullBuilder name(String name) {
      this.name = name;
      return this;
    }

    @Override
    public UserFullBuilder login(String login) {
      this.login = login;
      return this;
    }

    @Override
    public UserFullBuilder createdAt(String createdAt) {
      this.createdAt = createdAt;
      return this;
    }

    @Override
    public UserFullBuilder modifiedAt(String modifiedAt) {
      this.modifiedAt = modifiedAt;
      return this;
    }

    @Override
    public UserFullBuilder language(String language) {
      this.language = language;
      return this;
    }

    @Override
    public UserFullBuilder timezone(String timezone) {
      this.timezone = timezone;
      return this;
    }

    @Override
    public UserFullBuilder spaceAmount(Long spaceAmount) {
      this.spaceAmount = spaceAmount;
      return this;
    }

    @Override
    public UserFullBuilder spaceUsed(Long spaceUsed) {
      this.spaceUsed = spaceUsed;
      return this;
    }

    @Override
    public UserFullBuilder maxUploadSize(Long maxUploadSize) {
      this.maxUploadSize = maxUploadSize;
      return this;
    }

    @Override
    public UserFullBuilder status(UserStatusField status) {
      this.status = new EnumWrapper<UserStatusField>(status);
      return this;
    }

    @Override
    public UserFullBuilder status(EnumWrapper<UserStatusField> status) {
      this.status = status;
      return this;
    }

    @Override
    public UserFullBuilder jobTitle(String jobTitle) {
      this.jobTitle = jobTitle;
      return this;
    }

    @Override
    public UserFullBuilder phone(String phone) {
      this.phone = phone;
      return this;
    }

    @Override
    public UserFullBuilder address(String address) {
      this.address = address;
      return this;
    }

    @Override
    public UserFullBuilder avatarUrl(String avatarUrl) {
      this.avatarUrl = avatarUrl;
      return this;
    }

    @Override
    public UserFullBuilder notificationEmail(UserNotificationEmailField notificationEmail) {
      this.notificationEmail = notificationEmail;
      return this;
    }

    public UserFull build() {
      return new UserFull(this);
    }
  }
}
