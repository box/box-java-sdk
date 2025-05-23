package com.box.sdk;

import static com.box.sdk.BinaryBodyUtils.writeStream;
import static com.box.sdk.http.HttpMethod.DELETE;
import static com.box.sdk.internal.utils.JsonUtils.addIfNotNull;

import com.eclipsesource.json.Json;
import com.eclipsesource.json.JsonArray;
import com.eclipsesource.json.JsonObject;
import com.eclipsesource.json.JsonValue;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * Represents a Box user account.
 *
 * <p>Unless otherwise noted, the methods in this class can throw an unchecked {@link BoxAPIException} (unchecked
 * meaning that the compiler won't force you to handle it) if an error occurs. If you wish to implement custom error
 * handling for errors related to the Box REST API, you should capture this exception explicitly.</p>
 */
@BoxResourceType("user")
public class BoxUser extends BoxCollaborator {

    /**
     * An array of all possible file fields that can be requested when calling {@link #getInfo(String...)}.
     */
    public static final String[] ALL_FIELDS = {"type", "id", "name", "login", "created_at", "modified_at", "role",
        "language", "timezone", "space_amount", "space_used", "max_upload_size", "tracking_codes",
        "can_see_managed_users", "is_sync_enabled", "is_external_collab_restricted", "status", "job_title", "phone",
        "address", "avatar_url", "is_exempt_from_device_limits", "is_exempt_from_login_verification", "enterprise",
        "my_tags", "hostname", "is_platform_access_only", "external_app_user_id", "notification_email"};

    /**
     * User URL Template.
     */
    public static final URLTemplate USER_URL_TEMPLATE = new URLTemplate("users/%s");
    /**
     * Get Me URL Template.
     */
    public static final URLTemplate GET_ME_URL = new URLTemplate("users/me");
    /**
     * Users URL Template.
     */
    public static final URLTemplate USERS_URL_TEMPLATE = new URLTemplate("users");
    /**
     * User Memberships URL Template.
     */
    public static final URLTemplate USER_MEMBERSHIPS_URL_TEMPLATE = new URLTemplate("users/%s/memberships");
    /**
     * E-Mail Alias URL Template.
     */
    public static final URLTemplate EMAIL_ALIAS_URL_TEMPLATE = new URLTemplate("users/%s/email_aliases/%s");
    /**
     * E-Mail Aliases URL Template.
     */
    public static final URLTemplate EMAIL_ALIASES_URL_TEMPLATE = new URLTemplate("users/%s/email_aliases");
    /**
     * Move Folder To User Template.
     */
    public static final URLTemplate MOVE_FOLDER_TO_USER_TEMPLATE = new URLTemplate("users/%s/folders/%s");
    /**
     * User Avatar Template.
     */
    public static final URLTemplate USER_AVATAR_TEMPLATE = new URLTemplate("users/%s/avatar");

    /**
     * Constructs a BoxUser for a user with a given ID.
     *
     * @param api the API connection to be used by the user.
     * @param id  the ID of the user.
     */
    public BoxUser(BoxAPIConnection api, String id) {
        super(api, id);
    }

    /**
     * Provisions a new app user in an enterprise using Box Developer Edition.
     *
     * @param api  the API connection to be used by the created user.
     * @param name the name of the user.
     * @param fields the fields to retrieve. Leave this out for the standard fields.
     * @return the created user's info.
     */
    public static BoxUser.Info createAppUser(BoxAPIConnection api, String name, String... fields) {
        return createAppUser(api, name, new CreateUserParams(), fields);
    }

    /**
     * Provisions a new app user in an enterprise with additional user information using Box Developer Edition.
     *
     * @param api    the API connection to be used by the created user.
     * @param name   the name of the user.
     * @param params additional user information.
     * @param fields the fields to retrieve. Leave this out for the standard fields.
     * @return the created user's info.
     */
    public static BoxUser.Info createAppUser(BoxAPIConnection api, String name,
                                             CreateUserParams params, String... fields) {

        params.setIsPlatformAccessOnly(true);
        return createEnterpriseUser(api, null, name, params, fields);
    }

    /**
     * Provisions a new user in an enterprise.
     *
     * @param api   the API connection to be used by the created user.
     * @param login the email address the user will use to login.
     * @param name  the name of the user.
     * @param fields the fields to retrieve. Leave this out for the standard fields.
     * @return the created user's info.
     */
    public static BoxUser.Info createEnterpriseUser(BoxAPIConnection api, String login, String name, String... fields) {
        return createEnterpriseUser(api, login, name, null, fields);
    }

    /**
     * Provisions a new user in an enterprise with additional user information.
     *
     * @param api    the API connection to be used by the created user.
     * @param login  the email address the user will use to login.
     * @param name   the name of the user.
     * @param params additional user information.
     * @param fields the fields to retrieve. Leave this out for the standard fields.
     * @return the created user's info.
     */
    public static BoxUser.Info createEnterpriseUser(BoxAPIConnection api, String login, String name,
                                                    CreateUserParams params, String... fields) {

        JsonObject requestJSON = new JsonObject();
        requestJSON.add("login", login);
        requestJSON.add("name", name);

        if (params != null) {
            if (params.getRole() != null) {
                requestJSON.add("role", params.getRole().toJSONValue());
            }

            if (params.getStatus() != null) {
                requestJSON.add("status", params.getStatus().toJSONValue());
            }

            if (params.getTrackingCodes() != null) {
                requestJSON.add("tracking_codes", toTrackingCodesJson(params.getTrackingCodes()));
            }

            addIfNotNull(requestJSON, "language", params.getLanguage());
            addIfNotNull(requestJSON, "is_sync_enabled", params.getIsSyncEnabled());
            addIfNotNull(requestJSON, "job_title", params.getJobTitle());
            addIfNotNull(requestJSON, "phone", params.getPhone());
            addIfNotNull(requestJSON, "address", params.getAddress());
            addIfNotNull(requestJSON, "space_amount", params.getSpaceAmount());
            addIfNotNull(requestJSON, "can_see_managed_users", params.getCanSeeManagedUsers());
            addIfNotNull(requestJSON, "timezone", params.getTimezone());
            addIfNotNull(requestJSON, "is_exempt_from_device_limits", params.getIsExemptFromDeviceLimits());
            addIfNotNull(requestJSON, "is_exempt_from_login_verification", params.getIsExemptFromLoginVerification());
            addIfNotNull(requestJSON, "is_platform_access_only", params.getIsPlatformAccessOnly());
            addIfNotNull(requestJSON, "external_app_user_id", params.getExternalAppUserId());
            addIfNotNull(requestJSON, "is_external_collab_restricted", params.getIsExternalCollabRestricted());
        }

        QueryStringBuilder queryString = new QueryStringBuilder();
        if (fields.length > 0) {
            queryString.appendParam("fields", fields);
        }
        URL url = USERS_URL_TEMPLATE.buildWithQuery(api.getBaseURL(), queryString.toString());
        BoxJSONRequest request = new BoxJSONRequest(api, url, "POST");
        request.setBody(requestJSON.toString());
        try (BoxJSONResponse response = request.send()) {
            JsonObject responseJSON = Json.parse(response.getJSON()).asObject();

            BoxUser createdUser = new BoxUser(api, responseJSON.get("id").asString());
            return createdUser.new Info(responseJSON);
        }
    }

    /**
     * Gets the current user.
     *
     * @param api the API connection of the current user.
     * @return the current user.
     */
    public static BoxUser getCurrentUser(BoxAPIConnection api) {
        URL url = GET_ME_URL.build(api.getBaseURL());
        BoxJSONRequest request = new BoxJSONRequest(api, url, "GET");
        try (BoxJSONResponse response = request.send()) {
            JsonObject jsonObject = Json.parse(response.getJSON()).asObject();
            return new BoxUser(api, jsonObject.get("id").asString());
        }
    }

    /**
     * Returns an iterable containing all the enterprise users.
     *
     * @param api the API connection to be used when retrieving the users.
     * @return an iterable containing all the enterprise users.
     */
    public static Iterable<BoxUser.Info> getAllEnterpriseUsers(final BoxAPIConnection api) {
        return getAllEnterpriseUsers(api, false, null);
    }


    /**
     * Returns an iterable containing all the enterprise users. Uses marker based pagination.
     *
     * @param api       the API connection to be used when retrieving the users.
     * @param usemarker Boolean that determines whether to use marker based pagination.
     * @param marker    The marker at which the iterator will begin.
     * @return an iterable containing all the enterprise users.
     */
    public static Iterable<BoxUser.Info> getAllEnterpriseUsers(final BoxAPIConnection api, final boolean usemarker,
                                                               final String marker) {
        return getUsersInfoForType(api, null, null, null, usemarker, marker);
    }

    /**
     * Returns an iterable containing all the enterprise users that matches the filter and specifies which child fields
     * to retrieve from the API.
     *
     * @param api        the API connection to be used when retrieving the users.
     * @param filterTerm used to filter the results to only users starting with this string in either the name or the
     *                   login. Can be null to not filter the results.
     * @param fields     the fields to retrieve. Leave this out for the standard fields.
     * @return an iterable containing all the enterprise users that matches the filter.
     */
    public static Iterable<BoxUser.Info> getAllEnterpriseUsers(final BoxAPIConnection api, final String filterTerm,
                                                               final String... fields) {
        return getUsersInfoForType(api, filterTerm, null, null, false, null, fields);
    }

    /**
     * Returns an iterable containing all the enterprise users that matches the filter and specifies which child fields
     * to retrieve from the API. Uses marker based pagination.
     *
     * @param api        the API connection to be used when retrieving the users.
     * @param filterTerm used to filter the results to only users starting with this string in either the name or the
     *                   login. Can be null to not filter the results.
     * @param usemarker  Boolean that determines whether to use marker based pagination.
     * @param marker     The marker at which the iterator will begin.
     * @param fields     the fields to retrieve. Leave this out for the standard fields.
     * @return an iterable containing all the enterprise users that matches the filter.
     */
    public static Iterable<BoxUser.Info> getAllEnterpriseUsers(
        final BoxAPIConnection api,
        final String filterTerm,
        final boolean usemarker,
        final String marker,
        final String... fields
    ) {
        return getUsersInfoForType(api, filterTerm, null, null, usemarker, marker, fields);
    }

    /**
     * Gets a limited set of information about an external user. (A user collaborating
     * on content owned by the enterprise). Note: Only fields the user has permission to
     * see will be returned with values. Other fields will return a value of null.
     *
     * @param api        the API connection to be used when retrieving the users.
     * @param filterTerm used to filter the results to only users matching the given login.
     *                   This does exact match only, so if no filter term is passed in, nothing
     *                   will be returned.
     * @param fields     the fields to retrieve. Leave this out for the standard fields.
     * @return an iterable containing external users matching the given email
     */
    public static Iterable<BoxUser.Info> getExternalUsers(final BoxAPIConnection api, final String filterTerm,
                                                          final String... fields) {
        return getUsersInfoForType(api, filterTerm, "external", null, false, null, fields);
    }

    /**
     * Gets a limited set of information about an external user. (A user collaborating
     * on content owned by the enterprise). Note: Only fields the user has permission to
     * see will be returned with values. Other fields will return a value of null. Uses marker based pagination.
     *
     * @param api        the API connection to be used when retrieving the users.
     * @param filterTerm used to filter the results to only users matching the given login.
     *                   This does exact match only, so if no filter term is passed in, nothing
     *                   will be returned.
     * @param usemarker  Boolean that determines whether to use marker based pagination.
     * @param marker     The marker at which the iterator will begin.
     * @param fields     the fields to retrieve. Leave this out for the standard fields.
     * @return an iterable containing external users matching the given email
     */
    public static Iterable<BoxUser.Info> getExternalUsers(
        final BoxAPIConnection api,
        final String filterTerm,
        final boolean usemarker,
        final String marker,
        final String... fields
    ) {
        return getUsersInfoForType(api, filterTerm, "external", null, usemarker, marker, fields);
    }

    /**
     * Gets any managed users that match the filter term as well as any external users that
     * match the filter term. For managed users it matches any users names or emails that
     * start with the term. For external, it only does full match on email. This method
     * is ideal to use in the case where you have a full email for a user and you don't
     * know if they're managed or external.
     *
     * @param api        the API connection to be used when retrieving the users.
     * @param filterTerm The filter term to lookup users by (login for external, login or name for managed)
     * @param fields     the fields to retrieve. Leave this out for the standard fields.
     * @return an iterable containing users matching the given email
     */
    public static Iterable<BoxUser.Info> getAllEnterpriseOrExternalUsers(
        final BoxAPIConnection api,
        final String filterTerm,
        final String... fields
    ) {
        return getUsersInfoForType(api, filterTerm, "all", null, false, null, fields);
    }

    /**
     * Gets any managed users that match the filter term as well as any external users that
     * match the filter term. For managed users it matches any users names or emails that
     * start with the term. For external, it only does full match on email. This method
     * is ideal to use in the case where you have a full email for a user and you don't
     * know if they're managed or external. Uses marker based pagination.
     *
     * @param api        the API connection to be used when retrieving the users.
     * @param filterTerm The filter term to lookup users by (login for external, login or name for managed)
     * @param usemarker  Boolean that determines whether to use marker based pagination.
     * @param marker     The marker at which the iterator will begin.
     * @param fields     the fields to retrieve. Leave this out for the standard fields.
     * @return an iterable containing users matching the given email
     */
    public static Iterable<BoxUser.Info> getAllEnterpriseOrExternalUsers(
        final BoxAPIConnection api,
        final String filterTerm,
        final boolean usemarker,
        final String marker,
        final String... fields
    ) {
        return getUsersInfoForType(api, filterTerm, "all", null, usemarker, marker, fields);
    }

    /**
     * Gets any app users that has an exact match with the externalAppUserId term.
     *
     * @param api               the API connection to be used when retrieving the users.
     * @param externalAppUserId the external app user id that has been set for app user
     * @param fields            the fields to retrieve. Leave this out for the standard fields.
     * @return an iterable containing users matching the given email
     */
    public static Iterable<BoxUser.Info> getAppUsersByExternalAppUserID(
        final BoxAPIConnection api,
        final String externalAppUserId,
        final String... fields
    ) {
        return getUsersInfoForType(api, null, null, externalAppUserId, false, null, fields);
    }

    /**
     * Gets any app users that has an exact match with the externalAppUserId term using marker based pagination.
     *
     * @param api               the API connection to be used when retrieving the users.
     * @param externalAppUserId the external app user id that has been set for app user
     * @param usemarker         Boolean that determines whether to use marker based pagination.
     * @param marker            The marker at which the iterator will begin.
     * @param fields            the fields to retrieve. Leave this out for the standard fields.
     * @return an iterable containing users matching the given email
     */
    public static Iterable<BoxUser.Info> getAppUsersByExternalAppUserID(
        final BoxAPIConnection api,
        final String externalAppUserId,
        final boolean usemarker,
        String marker,
        final String... fields
    ) {
        return getUsersInfoForType(api, null, null, externalAppUserId, usemarker, marker, fields);
    }

    /**
     * Helper method to abstract out the common logic from the various users methods.
     *
     * @param api               the API connection to be used when retrieving the users.
     * @param filterTerm        The filter term to lookup users by (login for external, login or name for managed)
     * @param userType          The type of users we want to search with this request.
     *                          Valid values are 'managed' (enterprise users), 'external' or 'all'
     * @param externalAppUserId the external app user id that has been set for an app user
     * @param usemarker         Boolean that determines whether to use marker based pagination.
     * @param marker            The marker at which the iterator will begin.
     * @param fields            the fields to retrieve. Leave this out for the standard fields.
     * @return An iterator over the selected users.
     */
    private static Iterable<BoxUser.Info> getUsersInfoForType(
        final BoxAPIConnection api,
        final String filterTerm,
        final String userType,
        final String externalAppUserId,
        final boolean usemarker,
        final String marker,
        final String... fields
    ) {

        final QueryStringBuilder builder = new QueryStringBuilder();
        if (filterTerm != null) {
            builder.appendParam("filter_term", filterTerm);
        }
        if (userType != null) {
            builder.appendParam("user_type", userType);
        }
        if (externalAppUserId != null) {
            builder.appendParam("external_app_user_id", externalAppUserId);
        }
        if (usemarker) {
            builder.appendParam("usemarker", "true");
        }
        if (fields.length > 0) {
            builder.appendParam("fields", fields);
        }
        final URL url = USERS_URL_TEMPLATE.buildWithQuery(api.getBaseURL(), builder.toString());

        if (usemarker) {
            return new BoxResourceIterable<BoxUser.Info>(api, url, 100, null, marker) {
                @Override
                protected BoxUser.Info factory(JsonObject jsonObject) {
                    BoxUser user = new BoxUser(api, jsonObject.get("id").asString());
                    return user.new Info(jsonObject);
                }
            };
        } else {
            return () -> new BoxUserIterator(api, url);
        }
    }

    private static JsonArray toTrackingCodesJson(Map<String, String> trackingCodes) {
        JsonArray trackingCodesJsonArray = new JsonArray();
        for (String attrKey : trackingCodes.keySet()) {
            JsonObject trackingCode = new JsonObject();
            trackingCode.set("type", "tracking_code");
            trackingCode.set("name", attrKey);
            trackingCode.set("value", trackingCodes.get(attrKey));
            trackingCodesJsonArray.add(trackingCode);
        }
        return trackingCodesJsonArray;
    }

    /**
     * Gets information about this user.
     *
     * @param fields the optional fields to retrieve.
     * @return info about this user.
     */
    public BoxUser.Info getInfo(String... fields) {
        URL url;
        if (fields.length > 0) {
            String queryString = new QueryStringBuilder().appendParam("fields", fields).toString();
            url = USER_URL_TEMPLATE.buildWithQuery(this.getAPI().getBaseURL(), queryString, this.getID());
        } else {
            url = USER_URL_TEMPLATE.build(this.getAPI().getBaseURL(), this.getID());
        }
        BoxJSONRequest request = new BoxJSONRequest(this.getAPI(), url, "GET");
        try (BoxJSONResponse response = request.send()) {
            JsonObject jsonObject = Json.parse(response.getJSON()).asObject();
            return new Info(jsonObject);
        }
    }

    /**
     * Gets information about all of the group memberships for this user.
     * Does not support paging.
     *
     * <p>Note: This method is only available to enterprise admins.</p>
     *
     * @return a collection of information about the group memberships for this user.
     */
    public Collection<BoxGroupMembership.Info> getMemberships() {
        BoxAPIConnection api = this.getAPI();
        URL url = USER_MEMBERSHIPS_URL_TEMPLATE.build(this.getAPI().getBaseURL(), this.getID());

        BoxJSONRequest request = new BoxJSONRequest(api, url, "GET");
        try (BoxJSONResponse response = request.send()) {
            JsonObject responseJSON = Json.parse(response.getJSON()).asObject();

            int entriesCount = responseJSON.get("total_count").asInt();
            Collection<BoxGroupMembership.Info> memberships = new ArrayList<>(entriesCount);
            JsonArray entries = responseJSON.get("entries").asArray();
            for (JsonValue entry : entries) {
                JsonObject entryObject = entry.asObject();
                BoxGroupMembership membership = new BoxGroupMembership(api, entryObject.get("id").asString());
                BoxGroupMembership.Info info = membership.new Info(entryObject);
                memberships.add(info);
            }

            return memberships;
        }
    }

    /**
     * Gets information about all of the group memberships for this user as iterable with paging support.
     *
     * @param fields the fields to retrieve.
     * @return an iterable with information about the group memberships for this user.
     */
    public Iterable<BoxGroupMembership.Info> getAllMemberships(String... fields) {
        final QueryStringBuilder builder = new QueryStringBuilder();
        if (fields.length > 0) {
            builder.appendParam("fields", fields);
        }
        return () -> {
            URL url = USER_MEMBERSHIPS_URL_TEMPLATE.buildWithQuery(
                BoxUser.this.getAPI().getBaseURL(), builder.toString(), BoxUser.this.getID());
            return new BoxGroupMembershipIterator(BoxUser.this.getAPI(), url);
        };
    }

    /**
     * Adds a new email alias to this user's account.
     *
     * @param email the email address to add as an alias.
     * @return the newly created email alias.
     */
    public EmailAlias addEmailAlias(String email) {
        return this.addEmailAlias(email, false);
    }

    /**
     * Adds a new email alias to this user's account and confirms it without user interaction.
     * This functionality is only available for enterprise admins.
     *
     * @param email       the email address to add as an alias.
     * @param isConfirmed whether or not the email alias should be automatically confirmed.
     * @return the newly created email alias.
     */
    public EmailAlias addEmailAlias(String email, boolean isConfirmed) {
        URL url = EMAIL_ALIASES_URL_TEMPLATE.build(this.getAPI().getBaseURL(), this.getID());
        BoxJSONRequest request = new BoxJSONRequest(this.getAPI(), url, "POST");

        JsonObject requestJSON = new JsonObject()
            .add("email", email);

        if (isConfirmed) {
            requestJSON.add("is_confirmed", isConfirmed);
        }

        request.setBody(requestJSON.toString());
        try (BoxJSONResponse response = request.send()) {
            JsonObject responseJSON = Json.parse(response.getJSON()).asObject();
            return new EmailAlias(responseJSON);
        }
    }

    /**
     * Deletes an email alias from this user's account.
     *
     * <p>The IDs of the user's email aliases can be found by calling {@link #getEmailAliases}.</p>
     *
     * @param emailAliasID the ID of the email alias to delete.
     */
    public void deleteEmailAlias(String emailAliasID) {
        URL url = EMAIL_ALIAS_URL_TEMPLATE.build(this.getAPI().getBaseURL(), this.getID(), emailAliasID);
        BoxAPIRequest request = new BoxAPIRequest(this.getAPI(), url, "DELETE");
        request.send().close();
    }

    /**
     * Gets a collection of all the email aliases for this user.
     *
     * <p>Note that the user's primary login email is not included in the collection of email aliases.</p>
     *
     * @return a collection of all the email aliases for this user.
     */
    public Collection<EmailAlias> getEmailAliases() {
        URL url = EMAIL_ALIASES_URL_TEMPLATE.build(this.getAPI().getBaseURL(), this.getID());
        BoxJSONRequest request = new BoxJSONRequest(this.getAPI(), url, "GET");
        try (BoxJSONResponse response = request.send()) {
            JsonObject responseJSON = Json.parse(response.getJSON()).asObject();

            int totalCount = responseJSON.get("total_count").asInt();
            Collection<EmailAlias> emailAliases = new ArrayList<>(totalCount);
            JsonArray entries = responseJSON.get("entries").asArray();
            for (JsonValue value : entries) {
                JsonObject emailAliasJSON = value.asObject();
                emailAliases.add(new EmailAlias(emailAliasJSON));
            }

            return emailAliases;
        }
    }

    /**
     * Deletes a user from an enterprise account.
     *
     * @param notifyUser whether or not to send an email notification to the user that their account has been deleted.
     * @param force      whether or not this user should be deleted even if they still own files.
     */
    public void delete(boolean notifyUser, boolean force) {
        String queryString = new QueryStringBuilder()
            .appendParam("notify", String.valueOf(notifyUser))
            .appendParam("force", String.valueOf(force))
            .toString();

        performUserDelete(queryString);
    }

    /**
     * Deletes a user from an enterprise account. Uses API default values to determine if request should
     * be forced and if user should be notified.
     */
    public void delete() {
        performUserDelete("");
    }

    private void performUserDelete(String queryString) {
        URL url = USER_URL_TEMPLATE.buildWithQuery(this.getAPI().getBaseURL(), queryString, this.getID());
        BoxAPIRequest request = new BoxAPIRequest(this.getAPI(), url, "DELETE");
        request.send().close();
    }

    /**
     * Updates the information about this user with any info fields that have been modified locally.
     *
     * <p>Note: This method is only available to enterprise admins.</p>
     *
     * @param info info the updated info.
     * @param fields the fields to retrieve. Leave this out for the standard fields.
     */
    public void updateInfo(BoxUser.Info info, String... fields) {
        QueryStringBuilder builder = new QueryStringBuilder();
        if (fields.length > 0) {
            builder.appendParam("fields", fields);
        }
        URL url = USER_URL_TEMPLATE.buildWithQuery(this.getAPI().getBaseURL(), builder.toString(), this.getID());
        BoxJSONRequest request = new BoxJSONRequest(this.getAPI(), url, "PUT");
        request.setBody(info.getPendingChanges());
        try (BoxJSONResponse response = request.send()) {
            JsonObject jsonObject = Json.parse(response.getJSON()).asObject();
            info.update(jsonObject);
        }
    }

    /**
     * Moves all the owned content from within one user’s folder into a new folder in another user's account.
     * You can move folders across users as long as the you have administrative permissions and the 'source'
     * user owns the folders. Per the documentation at the link below, this will move everything from the root
     * folder, as this is currently the only mode of operation supported.
     * <p>
     * See also <a href="https://developer.box.com/en/reference/put-users-id-folders-id/">https://developer.box.com/en/reference/put-users-id-folders-id/</a>
     *
     * @param destinationUserID the user id of the user that you wish to transfer content to.
     * @return info for the newly created folder.
     */
    public BoxFolder.Info transferContent(String destinationUserID) {
        URL url = MOVE_FOLDER_TO_USER_TEMPLATE.build(this.getAPI().getBaseURL(), this.getID(), "0");
        BoxJSONRequest request = new BoxJSONRequest(this.getAPI(), url, "PUT");
        JsonObject destinationUser = new JsonObject();
        destinationUser.add("id", destinationUserID);
        JsonObject ownedBy = new JsonObject();
        ownedBy.add("owned_by", destinationUser);
        request.setBody(ownedBy.toString());
        try (BoxJSONResponse response = request.send()) {
            JsonObject responseJSON = Json.parse(response.getJSON()).asObject();
            BoxFolder movedFolder = new BoxFolder(this.getAPI(), responseJSON.get("id").asString());

            return movedFolder.new Info(responseJSON);
        }
    }

    /**
     * Writes avatar into specified {@link OutputStream}
     *
     * @param outputStream Stream where to write avatar.
     */
    public void downloadAvatar(OutputStream outputStream) {
        URL url = USER_AVATAR_TEMPLATE.build(this.getAPI().getBaseURL(), this.getID());
        BoxAPIRequest request = new BoxAPIRequest(this.getAPI(), url, "GET");
        writeStream(request.send(), outputStream);
    }

    /**
     * Retrieves the avatar of a user as an InputStream.
     *
     * @return InputStream representing the user avater.
     * @deprecated This method loads whole avatar image to memory.
     * Use {@link BoxUser#downloadAvatar(OutputStream)} to store avatar in file or memory.
     * That method uses very small buffer to store bytes in memory.
     */
    public InputStream getAvatar() {
        URL url = USER_AVATAR_TEMPLATE.build(this.getAPI().getBaseURL(), this.getID());
        BoxAPIRequest request = new BoxAPIRequest(this.getAPI(), url, "GET");
        BoxAPIResponse response = request.send();
        try (ByteArrayOutputStream outputStream = new ByteArrayOutputStream()) {
            writeStream(response, outputStream);
            return new ByteArrayInputStream(outputStream.toByteArray());
        } catch (IOException e) {
            throw new RuntimeException("Error while closing stream", e);
        }
    }

    /**
     * Upload avatar image to user account. Supported formats are JPG and PNG.
     * The image size cannot exceed 1024 * 1024 pixels or 1MB.
     *
     * @param file File containg avatar image.
     * @return Avatar creation response.
     */
    public AvatarUploadResponse uploadAvatar(File file) {
        try {
            return uploadAvatar(new FileInputStream(file), file.getName());
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Upload avatar image to user account. Supported formats are JPG and PNG.
     * The image size cannot exceed 1024 * 1024 pixels or 1MB.
     *
     * @param file             {@link File} containg avatar image.
     * @param progressListener {@link ProgressListener} set if you want to track upload progress
     * @return Avatar creation response.
     */
    public AvatarUploadResponse uploadAvatar(File file, ProgressListener progressListener) {
        try {
            return uploadAvatar(new FileInputStream(file), file.getName(), progressListener);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Upload avatar image to user account. Supported formats are JPG and PNG.
     * The image size cannot exceed 1024 * 1024 pixels or 1MB.
     *
     * @param content  {@link InputStream} containing image data
     * @param fileName file name with extention what will be used to determine content type
     * @return Avatar creation response.
     */
    public AvatarUploadResponse uploadAvatar(InputStream content, String fileName) {
        return uploadAvatar(content, fileName, null);
    }

    /**
     * Upload avatar image to user account. Supported formats are JPG and PNG.
     * The image size cannot exceed 1024 * 1024 pixels or 1MB.
     *
     * @param content          {@link InputStream} containing image data
     * @param fileName         file name with extention what will be used to determine content type
     * @param progressListener {@link ProgressListener} set if you want to track upload progress
     * @return Avatar creation response.
     */
    public AvatarUploadResponse uploadAvatar(InputStream content, String fileName, ProgressListener progressListener) {
        URL url = USER_AVATAR_TEMPLATE.build(getAPI().getBaseURL(), this.getID());
        BoxImageMultipartRequest request = new BoxImageMultipartRequest(getAPI(), url, "pic");
        request.setFile(content, fileName);

        BoxAPIResponse response = null;
        try {
            if (progressListener != null) {
                response = request.send(progressListener);
            } else {
                response = request.send();
            }

            return parseUploadAvatarResponse(response);
        } finally {
            Optional.ofNullable(response).ifPresent(BoxAPIResponse::close);
        }
    }

    /**
     * Removes avatar from user account.
     */
    public void deleteAvatar() {
        URL url = USER_AVATAR_TEMPLATE.build(getAPI().getBaseURL(), this.getID());
        BoxAPIRequest request = new BoxAPIRequest(getAPI(), url, DELETE);
        request.send().close();
    }

    private AvatarUploadResponse parseUploadAvatarResponse(BoxAPIResponse response) {
        JsonObject responseObject = Json.parse(response.bodyToString()).asObject();
        JsonObject picUrls = responseObject.get("pic_urls").asObject();
        return new AvatarUploadResponse(
            picUrls.getString("small", null),
            picUrls.getString("large", null),
            picUrls.getString("preview", null)
        );
    }

    /**
     * Enumerates the possible roles that a user can have within an enterprise.
     */
    public enum Role {
        /**
         * The user is an administrator of their enterprise.
         */
        ADMIN("admin"),

        /**
         * The user is a co-administrator of their enterprise.
         */
        COADMIN("coadmin"),

        /**
         * The user is a regular user within their enterprise.
         */
        USER("user");

        private final String jsonValue;

        Role(String jsonValue) {
            this.jsonValue = jsonValue;
        }

        static Role fromJSONValue(String jsonValue) {
            return Role.valueOf(jsonValue.toUpperCase(java.util.Locale.ROOT));
        }

        String toJSONValue() {
            return this.jsonValue;
        }
    }

    /**
     * Enumerates the possible statuses that a user's account can have.
     */
    public enum Status {
        /**
         * The user's account is active.
         */
        ACTIVE("active"),

        /**
         * The user's account is inactive.
         */
        INACTIVE("inactive"),

        /**
         * The user's account cannot delete or edit content.
         */
        CANNOT_DELETE_EDIT("cannot_delete_edit"),

        /**
         * The user's account cannot delete, edit, or upload content.
         */
        CANNOT_DELETE_EDIT_UPLOAD("cannot_delete_edit_upload");

        private final String jsonValue;

        Status(String jsonValue) {
            this.jsonValue = jsonValue;
        }

        static Status fromJSONValue(String jsonValue) {
            return Status.valueOf(jsonValue.toUpperCase(java.util.Locale.ROOT));
        }

        String toJSONValue() {
            return this.jsonValue;
        }
    }

    /**
     * Contains information about a BoxUser.
     */
    public class Info extends BoxCollaborator.Info {
        private String login;
        private Role role;
        private String language;
        private String timezone;
        private long spaceAmount;
        private long spaceUsed;
        private long maxUploadSize;
        private boolean canSeeManagedUsers;
        private boolean isSyncEnabled;
        private boolean isExternalCollabRestricted;
        private Status status;
        private String jobTitle;
        private String phone;
        private String address;
        private String avatarURL;
        private BoxNotificationEmail notificationEmail;
        private boolean isExemptFromDeviceLimits;
        private boolean isExemptFromLoginVerification;
        private boolean isPasswordResetRequired;
        private boolean isPlatformAccessOnly;
        private String externalAppUserId;
        private BoxEnterprise enterprise;
        private List<String> myTags;
        private String hostname;
        private Map<String, String> trackingCodes;

        /**
         * Constructs an empty Info object.
         */
        public Info() {
            super();
        }

        /**
         * Constructs an Info object by parsing information from a JSON string.
         *
         * @param json the JSON string to parse.
         */
        public Info(String json) {
            super(json);
        }

        Info(JsonObject jsonObject) {
            super(jsonObject);
        }

        @Override
        public BoxUser getResource() {
            return BoxUser.this;
        }

        /**
         * Gets the email address the user uses to login.
         *
         * @return the email address the user uses to login.
         */
        public String getLogin() {
            return this.login;
        }

        /**
         * Sets the email address the user uses to login. The new login must be one of the user's already confirmed
         * email aliases.
         *
         * @param login one of the user's confirmed email aliases.
         */
        public void setLogin(String login) {
            this.login = login;
            this.addPendingChange("login", login);
        }

        /**
         * Gets the user's enterprise role.
         *
         * @return the user's enterprise role.
         */
        public Role getRole() {
            return this.role;
        }

        /**
         * Sets the user's role in their enterprise.
         *
         * @param role the user's new role in their enterprise.
         */
        public void setRole(Role role) {
            this.role = role;
            this.addPendingChange("role", role.name().toLowerCase(java.util.Locale.ROOT));
        }

        /**
         * Gets the language of the user.
         *
         * @return the language of the user.
         */
        public String getLanguage() {
            return this.language;
        }

        /**
         * Sets the language of the user.
         *
         * @param language the new language of the user.
         */
        public void setLanguage(String language) {
            this.language = language;
            this.addPendingChange("language", language);
        }

        /**
         * Gets the timezone of the user.
         *
         * @return the timezone of the user.
         */
        public String getTimezone() {
            return this.timezone;
        }

        /**
         * Sets the timezone of the user.
         *
         * @param timezone the new timezone of the user.
         */
        public void setTimezone(String timezone) {
            this.timezone = timezone;
            this.addPendingChange("timezone", timezone);
        }

        /**
         * Gets the user's total available space in bytes.
         *
         * @return the user's total available space in bytes.
         */
        public long getSpaceAmount() {
            return this.spaceAmount;
        }

        /**
         * Sets the user's total available space in bytes.
         *
         * @param spaceAmount the new amount of space available to the user in bytes, or -1 for unlimited storage.
         */
        public void setSpaceAmount(long spaceAmount) {
            this.spaceAmount = spaceAmount;
            this.addPendingChange("space_amount", spaceAmount);
        }

        /**
         * Gets the amount of space the user has used in bytes.
         *
         * @return the amount of space the user has used in bytes.
         */
        public long getSpaceUsed() {
            return this.spaceUsed;
        }

        /**
         * Gets the maximum individual file size in bytes the user can have.
         *
         * @return the maximum individual file size in bytes the user can have.
         */
        public long getMaxUploadSize() {
            return this.maxUploadSize;
        }

        /**
         * Gets the user's current account status.
         *
         * @return the user's current account status.
         */
        public Status getStatus() {
            return this.status;
        }

        /**
         * Sets the user's current account status.
         *
         * @param status the user's new account status.
         */
        public void setStatus(Status status) {
            this.status = status;
            this.addPendingChange("status", status.name().toLowerCase(java.util.Locale.ROOT));
        }

        /**
         * Gets the job title of the user.
         *
         * @return the job title of the user.
         */
        public String getJobTitle() {
            return this.jobTitle;
        }

        /**
         * Sets the job title of the user.
         *
         * @param jobTitle the new job title of the user.
         */
        public void setJobTitle(String jobTitle) {
            this.jobTitle = jobTitle;
            this.addPendingChange("job_title", jobTitle);
        }

        /**
         * Gets the phone number of the user.
         *
         * @return the phone number of the user.
         */
        public String getPhone() {
            return this.phone;
        }

        /**
         * Sets the phone number of the user.
         *
         * @param phone the new phone number of the user.
         */
        public void setPhone(String phone) {
            this.phone = phone;
            this.addPendingChange("phone", phone);
        }

        /**
         * Gets the address of the user.
         *
         * @return the address of the user.
         */
        public String getAddress() {
            return this.address;
        }

        /**
         * Sets the address of the user.
         *
         * @param address the new address of the user.
         */
        public void setAddress(String address) {
            this.address = address;
            this.addPendingChange("address", address);
        }

        /**
         * Gets the URL of the user's avatar.
         *
         * @return the URL of the user's avatar.
         */
        public String getAvatarURL() {
            return this.avatarURL;
        }

        /**
         * Gets the user's alternate notification email address to which email notifications are sent.
         *
         * @return the user's notification email address.
         */
        public BoxNotificationEmail getNotificationEmail() {
            return this.notificationEmail;
        }

        /**
         * Sets the user's notification email address.
         *
         * @param notificationEmail the user's new notification email address.
         */
        public void setNotificationEmail(BoxNotificationEmail notificationEmail) {
            this.notificationEmail = notificationEmail;
        }

        /**
         * Gets the enterprise that the user belongs to.
         *
         * @return the enterprise that the user belongs to.
         */
        public BoxEnterprise getEnterprise() {
            return this.enterprise;
        }

        /**
         * Removes the user from their enterprise and converts them to a standalone free user.
         */
        public void removeEnterprise() {
            this.removeChildObject("enterprise");
            this.enterprise = null;
            this.addChildObject("enterprise", null);
        }

        /**
         * Gets whether or not the user can use Box Sync.
         *
         * @return true if the user can use Box Sync; otherwise false.
         */
        public boolean getIsSyncEnabled() {
            return this.isSyncEnabled;
        }

        /**
         * Sets whether or not the user can use Box Sync.
         *
         * @param enabled whether or not the user can use Box Sync.
         */
        public void setIsSyncEnabled(boolean enabled) {
            this.isSyncEnabled = enabled;
            this.addPendingChange("is_sync_enabled", enabled);
        }

        /**
         * Gets whether this user is allowed or not to collaborate with users outside their enterprise.
         *
         * @return true if this user is not allowed to collaborate with users outside their enterprise; otherwise false.
         */
        public boolean getIsExternalCollabRestricted() {
            return this.isExternalCollabRestricted;
        }

        /**
         * Sets whether this user is allowed or not to collaborate with users outside their enterprise.
         *
         * @param isExternalCollabRestricted whether the user is allowed to collaborate outside their enterprise.
         */
        public void setIsExternalCollabRestricted(boolean isExternalCollabRestricted) {
            this.isExternalCollabRestricted = isExternalCollabRestricted;
            this.addPendingChange("is_external_collab_restricted", isExternalCollabRestricted);
        }

        /**
         * Gets whether or not the user can see other enterprise users in their contact list.
         *
         * @return true if the user can see other enterprise users in their contact list; otherwise false.
         */
        public boolean getCanSeeManagedUsers() {
            return this.canSeeManagedUsers;
        }

        /**
         * Sets whether or not the user can see other enterprise users in their contact list.
         *
         * @param canSeeManagedUsers whether or not the user can see other enterprise users in their contact list.
         */
        public void setCanSeeManagedUsers(boolean canSeeManagedUsers) {
            this.canSeeManagedUsers = canSeeManagedUsers;
            this.addPendingChange("can_see_managed_users", canSeeManagedUsers);
        }

        /**
         * Gets whether or not the user is exempt from enterprise device limits.
         *
         * @return true if the user is exempt from enterprise device limits; otherwise false.
         */
        public boolean getIsExemptFromDeviceLimits() {
            return this.isExemptFromDeviceLimits;
        }

        /**
         * Sets whether or not the user is exempt from enterprise device limits.
         *
         * @param isExemptFromDeviceLimits whether or not the user is exempt from enterprise device limits.
         */
        public void setIsExemptFromDeviceLimits(boolean isExemptFromDeviceLimits) {
            this.isExemptFromDeviceLimits = isExemptFromDeviceLimits;
            this.addPendingChange("is_exempt_from_device_limits", isExemptFromDeviceLimits);
        }

        /**
         * Gets whether or not the user must use two-factor authentication.
         *
         * @return true if the user must use two-factor authentication; otherwise false.
         */
        public boolean getIsExemptFromLoginVerification() {
            return this.isExemptFromLoginVerification;
        }

        /**
         * Sets whether or not the user must use two-factor authentication.
         *
         * @param isExemptFromLoginVerification whether or not the user must use two-factor authentication.
         */
        public void setIsExemptFromLoginVerification(boolean isExemptFromLoginVerification) {
            this.isExemptFromLoginVerification = isExemptFromLoginVerification;
            this.addPendingChange("is_exempt_from_login_verification", isExemptFromLoginVerification);
        }

        /**
         * Gets whether or not the user is required to reset password.
         *
         * @return true if the user is required to reset password; otherwise false.
         */
        public boolean getIsPasswordResetRequired() {
            return this.isPasswordResetRequired;
        }

        /**
         * Sets whether or not the user is required to reset password.
         *
         * @param isPasswordResetRequired whether or not the user is required to reset password.
         */
        public void setIsPasswordResetRequired(boolean isPasswordResetRequired) {
            this.isPasswordResetRequired = isPasswordResetRequired;
            this.addPendingChange("is_password_reset_required", isPasswordResetRequired);
        }

        /**
         * Gets whether or not the user we are creating is an app user with Box Developer Edition.
         *
         * @return true if the new user is an app user for Box Developer Addition; otherwise false.
         */
        public boolean getIsPlatformAccessOnly() {
            return this.isPlatformAccessOnly;
        }

        /**
         * Gets the external app user id that has been set for the app user.
         *
         * @return the external app user id.
         */
        public String getExternalAppUserId() {
            return this.externalAppUserId;
        }

        /**
         * Sets the external app user id.
         *
         * @param externalAppUserId external app user id.
         */
        public void setExternalAppUserId(String externalAppUserId) {
            this.externalAppUserId = externalAppUserId;
            this.addPendingChange("external_app_user_id", externalAppUserId);
        }

        /**
         * Gets the tags for all files and folders owned by this user.
         *
         * @return the tags for all files and folders owned by this user.
         */
        public List<String> getMyTags() {
            return this.myTags;
        }

        /**
         * Gets the root (protocol, subdomain, domain) of any links that need to be generated for this user.
         *
         * @return the root (protocol, subdomain, domain) of any links that need to be generated for this user.
         */
        public String getHostname() {
            return this.hostname;
        }

        /**
         * Gets the tracking defined for each entity.
         *
         * @return a Map with tracking codes.
         */
        public Map<String, String> getTrackingCodes() {
            return this.trackingCodes;
        }

        /**
         * Allows admin to set attributes specific for a group of users.
         *
         * @param trackingCodes a Map representing the user's new tracking codes
         */
        public void setTrackingCodes(Map<String, String> trackingCodes) {
            this.trackingCodes = trackingCodes;
            this.addPendingChange("tracking_codes", toTrackingCodesJson(this.trackingCodes));
        }

        /**
         * Allows the admin to append new tracking codes to the previous existing list.
         *
         * @param name  the name or `key` of the attribute to set.
         * @param value the value of the attribute to set.
         */
        public void appendTrackingCodes(String name, String value) {
            this.getTrackingCodes().put(name, value);
            this.addPendingChange("tracking_codes", toTrackingCodesJson(this.trackingCodes));
        }

        @SuppressWarnings("checkstyle:MissingSwitchDefault")
        @Override
        protected void parseJSONMember(JsonObject.Member member) {
            super.parseJSONMember(member);

            JsonValue value = member.getValue();
            String memberName = member.getName();
            try {
                switch (memberName) {
                    case "login":
                        this.login = value.asString();
                        break;
                    case "role":
                        this.role = Role.fromJSONValue(value.asString());
                        break;
                    case "language":
                        this.language = value.asString();
                        break;
                    case "timezone":
                        this.timezone = value.asString();
                        break;
                    case "space_amount":
                        this.spaceAmount = Double.valueOf(value.toString()).longValue();
                        break;
                    case "space_used":
                        this.spaceUsed = Double.valueOf(value.toString()).longValue();
                        break;
                    case "max_upload_size":
                        this.maxUploadSize = Double.valueOf(value.toString()).longValue();
                        break;
                    case "status":
                        this.status = Status.fromJSONValue(value.asString());
                        break;
                    case "job_title":
                        this.jobTitle = value.asString();
                        break;
                    case "phone":
                        this.phone = value.asString();
                        break;
                    case "address":
                        this.address = value.asString();
                        break;
                    case "avatar_url":
                        this.avatarURL = value.asString();
                        break;
                    case "notification_email":
                        if (value.isObject()) {
                            this.notificationEmail = new BoxNotificationEmail(value.asObject());
                        } else {
                            this.notificationEmail = null;
                        }
                        break;
                    case "can_see_managed_users":
                        this.canSeeManagedUsers = value.asBoolean();
                        break;
                    case "is_sync_enabled":
                        this.isSyncEnabled = value.asBoolean();
                        break;
                    case "is_external_collab_restricted":
                        this.isExternalCollabRestricted = value.asBoolean();
                        break;
                    case "is_exempt_from_device_limits":
                        this.isExemptFromDeviceLimits = value.asBoolean();
                        break;
                    case "is_exempt_from_login_verification":
                        this.isExemptFromLoginVerification = value.asBoolean();
                        break;
                    case "is_password_reset_required":
                        this.isPasswordResetRequired = value.asBoolean();
                        break;
                    case "is_platform_access_only":
                        this.isPlatformAccessOnly = value.asBoolean();
                        break;
                    case "external_app_user_id":
                        this.externalAppUserId = value.asString();
                        break;
                    case "enterprise":
                        JsonObject jsonObject = value.asObject();
                        if (this.enterprise == null) {
                            this.enterprise = new BoxEnterprise(jsonObject);
                        } else {
                            this.enterprise.update(jsonObject);
                        }
                        break;
                    case "my_tags":
                        this.myTags = this.parseMyTags(value.asArray());
                        break;
                    case "hostname":
                        this.hostname = value.asString();
                        break;
                    case "tracking_codes":
                        this.trackingCodes = this.parseTrackingCodes(value.asArray());
                        break;
                }
            } catch (Exception e) {
                throw new BoxDeserializationException(memberName, value.toString(), e);
            }

        }

        private List<String> parseMyTags(JsonArray jsonArray) {
            List<String> myTags = new ArrayList<>(jsonArray.size());
            for (JsonValue value : jsonArray) {
                myTags.add(value.asString());
            }

            return myTags;
        }

        private Map<String, String> parseTrackingCodes(JsonArray jsonArray) {
            Map<String, String> result = new HashMap<>();
            if (jsonArray == null) {
                return null;
            }
            List<JsonValue> valuesList = jsonArray.values();
            for (JsonValue jsonValue : valuesList) {
                JsonObject object = jsonValue.asObject();
                result.put(object.get("name").asString(), object.get("value").asString());
            }
            return result;
        }
    }
}
