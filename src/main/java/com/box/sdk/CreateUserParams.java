package com.box.sdk;

/**
 * Contains optional parameters for creating a new enterprise user on Box.
 */
public class CreateUserParams {
    private boolean canSeeManagedUsers;
    private boolean isExemptFromDeviceLimits;
    private boolean isExemptFromLoginVerification;
    private boolean isPlatformAccessOnly;
    private boolean isSyncEnabled;
    private BoxUser.Role role;
    private BoxUser.Status status;
    private long spaceAmount;
    private String address;
    private String jobTitle;
    private String language;
    private String phone;
    private String timezone;
    private String externalAppUserId;

    /**
     * Gets whether or not the new user will be able to see other enterprise users in their contact list.
     * @return true if the new user will be able to see other enterprise users in their contact list; otherwise false.
     */
    public boolean getCanSeeManagedUsers() {
        return this.canSeeManagedUsers;
    }

    /**
     * Sets whether or not the new user will be able to see other enterprise users in their contact list.
     * @param  canSeeManagedUsers whether or not the new user will be able to see other enterprise users in their
     *                            contact list.
     * @return                    this CreateUserParams object for chaining.
     */
    public CreateUserParams setCanSeeManagedUsers(boolean canSeeManagedUsers) {
        this.canSeeManagedUsers = canSeeManagedUsers;
        return this;
    }

    /**
     * Gets whether or not the new user will be exempt from Enterprise device limits.
     * @return true if the new user will be exempt from Enterprise device limits; otherwise false.
     */
    public boolean getIsExemptFromDeviceLimits() {
        return this.isExemptFromDeviceLimits;
    }

    /**
     * Sets whether or not the new user will be exempt from Enterprise device limits.
     * @param  isExemptFromDeviceLimits whether or not the new user will be exempt from Enterprise device limits.
     * @return                          this CreateUserParams object for chaining.
     */
    public CreateUserParams setIsExemptFromDeviceLimits(boolean isExemptFromDeviceLimits) {
        this.isExemptFromDeviceLimits = isExemptFromDeviceLimits;
        return this;
    }

    /**
     * Gets whether or not the new user will be required to use two-factor authentication.
     * @return true if the new user will be required to use two-factor authentication; otherwise false.
     */
    public boolean getIsExemptFromLoginVerification() {
        return this.isExemptFromLoginVerification;
    }

    /**
     * Sets whether or not the new user will be required to use two-factor authentication.
     * @param  isExemptFromLoginVerification whether or not the new user will be required to use two-factor
     *                                       authentication.
     * @return                               this CreateUserParams object for chaining.
     */
    public CreateUserParams setIsExemptFromLoginVerification(boolean isExemptFromLoginVerification) {
        this.isExemptFromLoginVerification = isExemptFromLoginVerification;
        return this;
    }

    /**
    * Gets whether or not the user we are creating is an app user with Box Developer Edition.
    * @return true if the new user is an app user for Box Developer Addition; otherwise false.
    */
    public boolean getIsPlatformAccessOnly() {
        return this.isPlatformAccessOnly;
    }

   /**
    * Sets whether or not the user we are creating is an app user with Box Developer Edition.
    * @param  isPlatformAccessOnly whether or not the user we are creating is an app user with Box Developer
    *                              Edition.
    * @return                      this CreateUserParams object for chaining.
    */
    public CreateUserParams setIsPlatformAccessOnly(boolean isPlatformAccessOnly) {
        this.isPlatformAccessOnly = isPlatformAccessOnly;
        return this;
    }

    /**
     * Gets whether or not the new user will be able to use Box Sync.
     * @return true if the new user will be able to use Box Sync; otherwise false.
     */
    public boolean getIsSyncEnabled() {
        return this.isSyncEnabled;
    }

    /**
     * Sets whether or not the new user will be able to use Box Sync.
     * @param  isSyncEnabled whether or not the new user will be able to use Box Sync.
     * @return               this CreateUserParams object for chaining.
     */
    public CreateUserParams setIsSyncEnabled(boolean isSyncEnabled) {
        this.isSyncEnabled = isSyncEnabled;
        return this;
    }

    /**
     * Gets what the new user's enterprise role will be.
     * @return what the new user's enterprise role will be.
     */
    public BoxUser.Role getRole() {
        return this.role;
    }

    /**
     * Sets what the new user's enterprise role will be.
     * @param  role what the new user's enterprise role will be.
     * @return      this CreateUserParams object for chaining.
     */
    public CreateUserParams setRole(BoxUser.Role role) {
        this.role = role;
        return this;
    }

    /**
     * Gets what the new user's account status will be.
     * @return what the new user's account status will be.
     */
    public BoxUser.Status getStatus() {
        return this.status;
    }

    /**
     * Sets what the new user's account status will be.
     * @param  status what the new user's account status will be.
     * @return        this CreateUserParams object for chaining.
     */
    public CreateUserParams setStatus(BoxUser.Status status) {
        this.status = status;
        return this;
    }

    /**
     * Gets what the new user's total available space will be in bytes.
     * @return what the new user's total available space will be in bytes.
     */
    public long getSpaceAmount() {
        return this.spaceAmount;
    }

    /**
     * Sets what the new user's total available space will be in bytes.
     * @param  spaceAmount what the new user's total available space will be in bytes.
     * @return             this CreateUserParams object for chaining.
     */
    public CreateUserParams setSpaceAmount(long spaceAmount) {
        this.spaceAmount = spaceAmount;
        return this;
    }

    /**
     * Gets what the address of the new user will be.
     * @return what the address of the new user will be.
     */
    public String getAddress() {
        return this.address;
    }

    /**
     * Sets what the address of the new user will be.
     * @param  address what the address of the new user will be.
     * @return         this CreateUserParams object for chaining.
     */
    public CreateUserParams setAddress(String address) {
        this.address = address;
        return this;
    }

    /**
     * Gets what the job title of the new user will be.
     * @return what the job title of the new user will be.
     */
    public String getJobTitle() {
        return this.jobTitle;
    }

    /**
     * Sets what the job title of the new user will be.
     * @param  jobTitle what the job title of the new user will be.
     * @return          this CreateUserParams object for chaining.
     */
    public CreateUserParams setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
        return this;
    }

    /**
     * Gets what the language of the new user will be.
     * @return what the language of the new user will be.
     */
    public String getLanguage() {
        return this.language;
    }

    /**
     * Sets what the language of the new user will be.
     * @param  language what the language of the new user will be.
     * @return          this CreateUserParams object for chaining.
     */
    public CreateUserParams setLanguage(String language) {
        this.language = language;
        return this;
    }

    /**
     * Gets what the phone number of the new user will be.
     * @return what the phone number of the new user will be.
     */
    public String getPhone() {
        return this.phone;
    }

    /**
     * Sets what the phone number of the new user will be.
     * @param  phone what the phone number of the new user will be.
     * @return       this CreateUserParams object for chaining.
     */
    public CreateUserParams setPhone(String phone) {
        this.phone = phone;
        return this;
    }

    /**
     * Gets what the timezone of the new user will be.
     * @return what the timezone of the new user will be.
     */
    public String getTimezone() {
        return this.timezone;
    }

    /**
     * Sets what the timezone of the new user will be.
     * @param  timezone what the timezone of the new user will be.
     * @return          this CreateUserParams object for chaining.
     */
    public CreateUserParams setTimezone(String timezone) {
        this.timezone = timezone;
        return this;
    }

    /**
     * Gets the external app user id that has been set for the app user.
     * @return the external app user id.
     */
    public String getExternalAppUserId() {
        return this.externalAppUserId;
    }

    /**
     * Sets the external app user id.
     * @param externalAppUserId external app user id.
     * @return                  this CreateUserParams object for chaining.
     */
    public CreateUserParams setExternalAppUserId(String externalAppUserId) {
        this.externalAppUserId = externalAppUserId;
        return this;
    }
}
