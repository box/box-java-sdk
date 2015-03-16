package com.box.sdk;

import java.text.ParseException;
import java.util.Date;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;

import org.junit.Test;
import org.junit.experimental.categories.Category;

import com.eclipsesource.json.JsonObject;

public class BoxUserTest {

    @Test
    @Category(UnitTest.class)
    public void createEnterpriseUserSendsJSONWithLoginAndName() {
        final String login = "non-empty login";
        final String name = "non-empty name";

        final JsonObject fakeJSONResponse = new JsonObject()
            .add("type", "user")
            .add("id", "0");

        BoxAPIConnection api = new BoxAPIConnection("");
        api.setRequestInterceptor(new JSONRequestInterceptor() {
            @Override
            protected BoxAPIResponse onJSONRequest(BoxJSONRequest request, JsonObject json) {
                assertEquals(login, json.get("login").asString());
                assertEquals(name, json.get("name").asString());

                return new BoxJSONResponse() {
                    @Override
                    public String getJSON() {
                        return fakeJSONResponse.toString();
                    }
                };
            }
        });

        BoxUser.Info createdUserInfo = BoxUser.createEnterpriseUser(api, login, name);
    }

    @Test
    @Category(UnitTest.class)
    public void createEnterpriseUserSendsJSONWithAdditionalParams() {
        final String login = "non-empty login";
        final String name = "non-empty name";
        final String role = "user";
        final BoxUser.Role parsedRole = BoxUser.Role.USER;
        final String language = "non-empty language";
        final boolean isSyncEnabled = true;
        final String jobTitle = "non-empty job title";
        final String phone = "non-empty phone";
        final String address = "non-empty address";
        final long spaceAmount = 1;
        final boolean canSeeManagedUsers = true;
        final String status = "active";
        final BoxUser.Status parsedStatus = BoxUser.Status.ACTIVE;
        final String timezone = "non-empty timezone";
        final boolean isExemptFromDeviceLimits = true;
        final boolean isExemptFromLoginVerification = true;

        final JsonObject fakeJSONResponse = new JsonObject()
            .add("type", "user")
            .add("id", "0");

        BoxAPIConnection api = new BoxAPIConnection("");
        api.setRequestInterceptor(new JSONRequestInterceptor() {
            @Override
            protected BoxAPIResponse onJSONRequest(BoxJSONRequest request, JsonObject json) {
                assertEquals(login, json.get("login").asString());
                assertEquals(name, json.get("name").asString());
                assertEquals(role, json.get("role").asString());
                assertEquals(language, json.get("language").asString());
                assertEquals(isSyncEnabled, json.get("is_sync_enabled").asBoolean());
                assertEquals(jobTitle, json.get("job_title").asString());
                assertEquals(phone, json.get("phone").asString());
                assertEquals(address, json.get("address").asString());
                assertEquals(spaceAmount, json.get("space_amount").asLong());
                assertEquals(canSeeManagedUsers, json.get("can_see_managed_users").asBoolean());
                assertEquals(status, json.get("status").asString());
                assertEquals(timezone, json.get("timezone").asString());
                assertEquals(isExemptFromDeviceLimits, json.get("is_exempt_from_device_limits").asBoolean());
                assertEquals(isExemptFromLoginVerification, json.get("is_exempt_from_login_verification").asBoolean());

                return new BoxJSONResponse() {
                    @Override
                    public String getJSON() {
                        return fakeJSONResponse.toString();
                    }
                };
            }
        });

        CreateUserParams params = new CreateUserParams()
            .setRole(parsedRole)
            .setLanguage(language)
            .setIsSyncEnabled(isSyncEnabled)
            .setJobTitle(jobTitle)
            .setPhone(phone)
            .setAddress(address)
            .setSpaceAmount(spaceAmount)
            .setCanSeeManagedUsers(canSeeManagedUsers)
            .setStatus(parsedStatus)
            .setTimezone(timezone)
            .setIsExemptFromDeviceLimits(isExemptFromDeviceLimits)
            .setIsExemptFromLoginVerification(isExemptFromLoginVerification);
        BoxUser.Info createdUserInfo = BoxUser.createEnterpriseUser(api, login, name, params);
    }

    @Test
    @Category(UnitTest.class)
    public void createEnterpriseUserParsesAllFieldsCorrectly() throws ParseException {
        final String type = "user";
        final String id = "0";
        final String login = "non-empty login";
        final String name = "non-empty name";
        final String createdAt = "2015-03-15T18:02:06-07:00";
        final Date parsedCreatedAt = new Date(1426467726000L);
        final String modifiedAt = "2015-03-15T18:02:06-07:00";
        final Date parsedModifiedAt = new Date(1426467726000L);
        final String language = "non-empty language";
        final String timezone = "non-empty timezone";
        final long spaceAmount = 1;
        final long spaceUsed = 1;
        final long maxUploadSize = 1;
        final String status = "active";
        final BoxUser.Status parsedStatus = BoxUser.Status.ACTIVE;
        final String jobTitle = "non-empty job title";
        final String phone = "non-empty phone";
        final String address = "non-empty address";
        final String avatarURL = "https://app.box.com/api/avatar/large/0";

        final JsonObject fakeJSONResponse = new JsonObject()
            .add("type", "user")
            .add("id", "0")
            .add("name", name)
            .add("login", login)
            .add("created_at", createdAt)
            .add("modified_at", modifiedAt)
            .add("language", language)
            .add("timezone", timezone)
            .add("space_amount", spaceAmount)
            .add("space_used", spaceUsed)
            .add("max_upload_size", maxUploadSize)
            .add("status", status)
            .add("job_title", jobTitle)
            .add("phone", phone)
            .add("address", address)
            .add("avatar_url", avatarURL);

        BoxAPIConnection api = new BoxAPIConnection("");
        api.setRequestInterceptor(JSONRequestInterceptor.respondWith(fakeJSONResponse));

        BoxUser.Info createdUserInfo = BoxUser.createEnterpriseUser(api, login, name);
        assertEquals(name, createdUserInfo.getName());
        assertEquals(login, createdUserInfo.getLogin());
        assertEquals(parsedCreatedAt, createdUserInfo.getCreatedAt());
        assertEquals(parsedModifiedAt, createdUserInfo.getModifiedAt());
        assertEquals(language, createdUserInfo.getLanguage());
        assertEquals(timezone, createdUserInfo.getTimezone());
        assertEquals(spaceAmount, createdUserInfo.getSpaceAmount());
        assertEquals(spaceUsed, createdUserInfo.getSpaceUsed());
        assertEquals(maxUploadSize, createdUserInfo.getMaxUploadSize());
        assertEquals(parsedStatus, createdUserInfo.getStatus());
        assertEquals(jobTitle, createdUserInfo.getJobTitle());
        assertEquals(phone, createdUserInfo.getPhone());
        assertEquals(address, createdUserInfo.getAddress());
        assertEquals(avatarURL, createdUserInfo.getAvatarURL());
    }

    @Test
    @Category(IntegrationTest.class)
    public void getCurrentUserInfoIsCorrect() throws InterruptedException {
        final String expectedName = "Java SDK";
        BoxAPIConnection api = new BoxAPIConnection(TestConfig.getAccessToken());
        BoxUser user = BoxUser.getCurrentUser(api);
        BoxUser.Info info = user.getInfo();

        assertThat(info.getName(), equalTo(expectedName));
    }

    @Test
    @Category(IntegrationTest.class)
    public void createAndDeleteEnterpriseUserSucceeds() {
        TestConfig.setLogLevel("FINE");
        BoxAPIConnection api = new BoxAPIConnection(TestConfig.getAccessToken());
        final String login = "login@box.com";
        final String name = "non-empty name";

        BoxUser.Info createdUserInfo = BoxUser.createEnterpriseUser(api, login, name);

        assertNotNull(createdUserInfo.getID());
        assertEquals(name, createdUserInfo.getName());
        assertEquals(login, createdUserInfo.getLogin());

        createdUserInfo.getResource().delete(false, false);
    }
}
