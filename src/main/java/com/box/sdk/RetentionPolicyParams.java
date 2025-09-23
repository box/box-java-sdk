package com.box.sdk;

import java.util.ArrayList;
import java.util.List;

/**
 * Optional parameters for creating an updating a Retention Policy.
 *
 * @see BoxRetentionPolicy
 */
public class RetentionPolicyParams {

  /** @see #getCanOwnerExtendRetention() */
  private boolean canOwnerExtendRetention;

  /** @see #getAreOwnersNotified() */
  private boolean areOwnersNotified;

  /** @see #getDescription() */
  private String description;

  /** @see #getCustomNotificationRecipients() */
  private List<BoxUser.Info> customNotificationRecipients;

  /** @see #getCustomNotificationRecipients() */
  private RetentionType retentionType;

  /** Creates optional retention policy params with default values. */
  public RetentionPolicyParams() {
    this.canOwnerExtendRetention = false;
    this.areOwnersNotified = false;
    this.customNotificationRecipients = new ArrayList<>();
    this.description = "";
    this.retentionType = RetentionType.MODIFIABLE;
  }

  /** @return the flag denoting whether the owner can extend the retention. */
  public boolean getCanOwnerExtendRetention() {
    return this.canOwnerExtendRetention;
  }

  /**
   * Set the flag denoting whether the owner can extend the retentiion.
   *
   * @param canOwnerExtendRetention The flag value.
   */
  public void setCanOwnerExtendRetention(boolean canOwnerExtendRetention) {
    this.canOwnerExtendRetention = canOwnerExtendRetention;
  }

  /**
   * @return the flag denoting whether owners and co-onwers are notified when the retention period
   *     is ending.
   */
  public boolean getAreOwnersNotified() {
    return this.areOwnersNotified;
  }

  /**
   * Set the flag denoting whether owners and co-owners are notified when the retention period is
   * ending.
   *
   * @param areOwnersNotified The flag value.
   */
  public void setAreOwnersNotified(boolean areOwnersNotified) {
    this.areOwnersNotified = areOwnersNotified;
  }

  /** @return The additional text description of the retention policy */
  public String getDescription() {
    return this.description;
  }

  /**
   * @return retention type. It can be one of values: `modifiable` or `non-modifiable`.
   *     <p>`modifiable` means that you can modify the retention policy. For example, you can add or
   *     remove folders, shorten or lengthen the policy duration, or delete the assignment.
   *     <p>`non-modifiable` means that can modify the retention policy only in a limited way: add a
   *     folder, lengthen the duration, retire the policy, change the disposition action or
   *     notification settings. You cannot perform other actions, such as deleting the assignment or
   *     shortening the policy duration.
   */
  public RetentionType getRetentionType() {
    return retentionType;
  }

  /** @param retentionType The retention type: `modifiable` or `non-modifiable`. */
  public void setRetentionType(RetentionType retentionType) {
    this.retentionType = retentionType;
  }

  /**
   * Set additional text description of the retention policy.
   *
   * @param description The additional text description of the retention policy.
   */
  public void setDescription(String description) {
    this.description = description;
  }

  /** @return the list of extra users to notify when the retention period is ending. */
  public List<BoxUser.Info> getCustomNotificationRecipients() {
    return this.customNotificationRecipients;
  }

  /**
   * Set the list of extra users to notify when the retention period is ending.
   *
   * @param customNotificationRecipients The list of users.
   */
  public void setCustomNotificationRecipients(List<BoxUser.Info> customNotificationRecipients) {
    this.customNotificationRecipients = customNotificationRecipients;
  }

  /**
   * Add a user by ID to the list of people to notify when the retention period is ending.
   *
   * @param userID The ID of the user to add to the list.
   */
  public void addCustomNotificationRecipient(String userID) {
    BoxUser user = new BoxUser(null, userID);
    this.customNotificationRecipients.add(user.new Info());
  }

  /**
   * Add a user to the list of people to notify when the retention period is ending.
   *
   * @param user The info of the user to add to the list
   */
  public void addCustomNotificationRecipient(BoxUser user) {
    this.customNotificationRecipients.add(user.new Info());
  }

  /** The type of retention. */
  public enum RetentionType {
    /**
     * You can modify the retention policy. For example, you can add or remove folders, shorten or
     * lengthen the policy duration, or delete the assignment. Use this type if your retention
     * policy is not related to any regulatory purposes.
     */
    MODIFIABLE("modifiable"),

    /**
     * You can modify the retention policy only in a limited way: add a folder, lengthen the
     * duration, retire the policy, change the disposition action or notification settings. You
     * cannot perform other actions, such as deleting the assignment or shortening the policy
     * duration. Use this type to ensure compliance with regulatory retention policies.
     */
    NON_MODIFIABLE("non_modifiable");

    private final String jsonValue;

    RetentionType(String jsonValue) {
      this.jsonValue = jsonValue;
    }

    static RetentionType fromJSONString(String jsonValue) {
      return RetentionType.valueOf(jsonValue.toUpperCase(java.util.Locale.ROOT));
    }

    String toJSONString() {
      return this.jsonValue;
    }
  }
}
