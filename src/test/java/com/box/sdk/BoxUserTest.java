package com.box.sdk;

import java.text.ParseException;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.greaterThanOrEqualTo;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.experimental.categories.Category;

import static com.github.tomakehurst.wiremock.client.WireMock.aResponse;
import static com.github.tomakehurst.wiremock.client.WireMock.containing;
import static com.github.tomakehurst.wiremock.client.WireMock.get;
import static com.github.tomakehurst.wiremock.client.WireMock.stubFor;
import static com.github.tomakehurst.wiremock.client.WireMock.urlPathEqualTo;

import com.eclipsesource.json.JsonArray;
import com.eclipsesource.json.JsonObject;
import com.github.tomakehurst.wiremock.client.WireMock;
import com.github.tomakehurst.wiremock.junit.WireMockRule;
import com.google.common.collect.Lists;

/**
 * {@link BoxUser} related tests.
 */

public class BoxUserTest {

    /**
     * Wiremock
     */
    @Rule
    public final WireMockRule wireMockRule = new WireMockRule(8080);

    /**
     * Unit test for {@link BoxUser#getAllEnterpriseUsers(BoxAPIConnection, String, String...)}.
     */
    @Test
    @Category(UnitTest.class)
    public void getAllEnterpriseUsersRequestsCorrectFilterAndFields() {
        final String filterTerm = "login";
        final String name = "enterprise user";
        BoxAPIConnection api = new BoxAPIConnection("");
        api.setBaseURL("http://localhost:8080/");

        stubFor(get(urlPathEqualTo("/users"))
                .withQueryParam("offset", WireMock.equalTo("0"))
                .withQueryParam("limit", WireMock.equalTo("1000"))
                .withQueryParam("filter_term", WireMock.equalTo(filterTerm))
                .withQueryParam("fields", containing("name"))
                .withQueryParam("fields", containing("role"))
                .willReturn(aResponse()
                        .withHeader("Content-Type", "application/json")
                        .withBody("{\"total_count\": 1, \"offset\": 0, \"entries\":"
                                + "[{\"type\": \"user\", \"id\": \"0\", \"name\": \"" + name
                                + "\", \"role\": \"user\"}]}")));

        Iterable<BoxUser.Info> users = BoxUser.getAllEnterpriseUsers(api, filterTerm, "name", "role");
        List<BoxUser.Info> usersList = Lists.newArrayList(users);
        assertThat(usersList.size(), is(1));
        assertThat(usersList.get(0).getName(), is(equalTo(name)));
    }

    /**
     * Unit test for {@link BoxUser#getExternalUsers(BoxAPIConnection, String, String...)}.
     */
    @Test
    @Category(UnitTest.class)
    public void getExternalUsersRequestsCorrectFilterAndFields() {
        final String filterTerm = "login";
        final String name = "enterprise user";
        BoxAPIConnection api = new BoxAPIConnection("");
        api.setBaseURL("http://localhost:8080/");

        stubFor(get(urlPathEqualTo("/users"))
                .withQueryParam("offset", WireMock.equalTo("0"))
                .withQueryParam("limit", WireMock.equalTo("1000"))
                .withQueryParam("filter_term", WireMock.equalTo(filterTerm))
                .withQueryParam("fields", containing("name"))
                .withQueryParam("fields", containing("role"))
                .withQueryParam("user_type", WireMock.equalTo("external"))
                .willReturn(aResponse()
                        .withHeader("Content-Type", "application/json")
                        .withBody("{\"total_count\": 1, \"offset\": 0, \"entries\":"
                                + "[{\"type\": \"user\", \"id\": \"0\", \"name\": \"" + name
                                + "\", \"role\": \"user\"}]}")));

        Iterable<BoxUser.Info> users = BoxUser.getExternalUsers(api, filterTerm, "name", "role");
        List<BoxUser.Info> usersList = Lists.newArrayList(users);
        assertThat(usersList.size(), is(1));
        assertThat(usersList.get(0).getName(), is(equalTo(name)));
    }

    /**
     * Unit test for {@link BoxUser#getAllEnterpriseOrExternalUsers(BoxAPIConnection, String, String...)}.
     */
    @Test
    @Category(UnitTest.class)
    public void getAllEnterpriseOrExternalUsersRequestsCorrectFilterAndFields() {
        final String filterTerm = "login";
        final String name = "enterprise user";
        BoxAPIConnection api = new BoxAPIConnection("");
        api.setBaseURL("http://localhost:8080/");

        stubFor(get(urlPathEqualTo("/users"))
                .withQueryParam("offset", WireMock.equalTo("0"))
                .withQueryParam("limit", WireMock.equalTo("1000"))
                .withQueryParam("filter_term", WireMock.equalTo(filterTerm))
                .withQueryParam("fields", containing("name"))
                .withQueryParam("fields", containing("role"))
                .withQueryParam("user_type", WireMock.equalTo("all"))
                .willReturn(aResponse()
                        .withHeader("Content-Type", "application/json")
                        .withBody("{\"total_count\": 1, \"offset\": 0, \"entries\":"
                                + "[{\"type\": \"user\", \"id\": \"0\", \"name\": \"" + name
                                + "\", \"role\": \"user\"}]}")));

        Iterable<BoxUser.Info> users = BoxUser.getAllEnterpriseOrExternalUsers(api, filterTerm, "name", "role");
        List<BoxUser.Info> usersList = Lists.newArrayList(users);
        assertThat(usersList.size(), is(1));
        assertThat(usersList.get(0).getName(), is(equalTo(name)));
    }

    /**
     * Unit test for {@link BoxUser#createEnterpriseUser(BoxAPIConnection, String, String)}.
     */
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

    /**
     * Unit test for {@link BoxUser#createEnterpriseUser(BoxAPIConnection, String, String, CreateUserParams)}.
     */
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

    /**
     * Unit test for {@link BoxUser#createEnterpriseUser(BoxAPIConnection, String, String)}.
     */
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

    /**
     * Unit test for {@link BoxUser#updateInfo(BoxUser.Info)}.
     */
    @Test
    @Category(UnitTest.class)
    public void updateInfoSendsCorrectJSON() {
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
        final boolean isPasswordResetRequired = true;

        final JsonObject fakeJSONResponse = new JsonObject()
                .add("type", "user")
                .add("id", "0");

        BoxAPIConnection api = new BoxAPIConnection("");
        api.setRequestInterceptor(new JSONRequestInterceptor() {
            @Override
            protected BoxAPIResponse onJSONRequest(BoxJSONRequest request, JsonObject json) {
                assertTrue(json.get("enterprise").isNull());
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
                assertEquals(isPasswordResetRequired, json.get("is_password_reset_required").asBoolean());

                return new BoxJSONResponse() {
                    @Override
                    public String getJSON() {
                        return fakeJSONResponse.toString();
                    }
                };
            }
        });

        BoxUser user = new BoxUser(api, "0");
        BoxUser.Info info = user.new Info();
        info.removeEnterprise();
        info.setName(name);
        info.setRole(parsedRole);
        info.setLanguage(language);
        info.setIsSyncEnabled(isSyncEnabled);
        info.setJobTitle(jobTitle);
        info.setPhone(phone);
        info.setAddress(address);
        info.setSpaceAmount(spaceAmount);
        info.setCanSeeManagedUsers(canSeeManagedUsers);
        info.setStatus(parsedStatus);
        info.setTimezone(timezone);
        info.setIsExemptFromDeviceLimits(isExemptFromDeviceLimits);
        info.setIsExemptFromLoginVerification(isExemptFromLoginVerification);
        info.setIsPasswordResetRequired(isPasswordResetRequired);

        user.updateInfo(info);
    }

    /**
     * Unit test for {@link BoxUser#getEmailAliases()}.
     */
    @Test
    @Category(UnitTest.class)
    public void getEmailAliasesParsesAllFieldsCorrectly() {
        final int totalCount = 2;

        final String id1 = "1";
        final boolean isConfirmed1 = true;
        final String email1 = "login@box.com";

        final String id2 = "2";
        final boolean isConfirmed2 = false;
        final String email2 = "unconfirmed-login@box.com";

        JsonObject fakeJSONResponse = new JsonObject()
                .add("total_count", totalCount)
                .add("entries", new JsonArray()
                        .add(new JsonObject()
                                .add("type", "email_alias")
                                .add("id", id1)
                                .add("is_confirmed", isConfirmed1)
                                .add("email", email1))
                        .add(new JsonObject()
                                .add("type", "email_alias")
                                .add("id", id2)
                                .add("is_confirmed", isConfirmed2)
                                .add("email", email2)));

        BoxAPIConnection api = new BoxAPIConnection("");
        api.setRequestInterceptor(JSONRequestInterceptor.respondWith(fakeJSONResponse));

        BoxUser user = new BoxUser(api, "0");
        Collection<EmailAlias> emailAliases = user.getEmailAliases();
        assertEquals(fakeJSONResponse.get("total_count").asInt(), emailAliases.size());
        for (EmailAlias emailAlias : emailAliases) {
            if (emailAlias.getID().equals(id1)) {
                assertEquals(isConfirmed1, emailAlias.getIsConfirmed());
                assertEquals(email1, emailAlias.getEmail());
            } else if (emailAlias.getID().equals(id2)) {
                assertEquals(isConfirmed2, emailAlias.getIsConfirmed());
                assertEquals(email2, emailAlias.getEmail());
            } else {
                fail("An unexpected email alias was returned.");
            }
        }
    }

    /**
     * Unit test for {@link BoxUser#addEmailAlias(String)}.
     */
    @Test
    @Category(UnitTest.class)
    public void addEmailAliasSendsCorrectJSON() {
        final String email = "login@box.com";

        BoxAPIConnection api = new BoxAPIConnection("");
        api.setRequestInterceptor(new JSONRequestInterceptor() {
            @Override
            protected BoxAPIResponse onJSONRequest(BoxJSONRequest request, JsonObject json) {
                assertEquals(email, json.get("email").asString());

                return new BoxJSONResponse() {
                    @Override
                    public String getJSON() {
                        return "{}";
                    }
                };
            }
        });

        BoxUser user = new BoxUser(api, "0");
        user.addEmailAlias(email);
    }

    /**
     * Unit test for {@link BoxUser#getInfo(String...)}
     */
    @Test
    @Category(UnitTest.class)
    public void testGetInfoSendsCorrectRequestWithoutParams() {
        BoxAPIConnection api = new BoxAPIConnection("");
        api.setRequestInterceptor(new RequestInterceptor() {
            @Override
            public BoxAPIResponse onRequest(BoxAPIRequest request) {
                Assert.assertEquals("https://api.box.com/2.0/users/0",
                        request.getUrl().toString());
                return new BoxJSONResponse() {
                    @Override
                    public String getJSON() {
                        return "{\"id\": \"0\"}";
                    }
                };
            }
        });

        BoxUser user = new BoxUser(api, "0");
        user.getInfo();
    }

    /**
     * Unit test for {@link BoxUser#getInfo(String...)}
     */
    @Test
    @Category(UnitTest.class)
    public void testGetInfoSendsCorrectRequestWithFields() {
        BoxAPIConnection api = new BoxAPIConnection("");
        api.setRequestInterceptor(new RequestInterceptor() {
            @Override
            public BoxAPIResponse onRequest(BoxAPIRequest request) {
                Assert.assertEquals("https://api.box.com/2.0/users/0?fields=name%2Cstatus",
                        request.getUrl().toString());
                return new BoxJSONResponse() {
                    @Override
                    public String getJSON() {
                        return "{\"id\": \"0\"}";
                    }
                };
            }
        });

        BoxUser user = new BoxUser(api, "0");
        user.getInfo("name", "status");
    }

    /**
     * Unit test for {@link BoxUser#getInfo(String...)}
     */
    @Test
    @Category(UnitTest.class)
    public void testGetInfoParseAllFieldsCorrectly() throws ParseException {
        final String id = "10543463";
        final String name = "Arielle Frey";
        final String login = "ariellefrey@box.com";
        final Date createdAt = BoxDateFormat.parse("2011-01-07T12:37:09-08:00");
        final Date modifiedAt = BoxDateFormat.parse("2014-05-30T10:39:47-07:00");
        final String language = "en";
        final String timezone = "America/Los_Angeles";
        final long spaceAmount = 10737418240L;
        final long spaceUsed = 558732L;
        final long maxUploadSize = 5368709120L;
        final BoxUser.Status status = BoxUser.Status.ACTIVE;
        final String jobTitle = "";
        final String phone = "";
        final String address = "";
        final String avatarURL = "https://blosserdemoaccount.app.box.com/api/avatar/large/10543465";

        final JsonObject fakeJSONResponse = JsonObject.readFrom("{\n"
                + "    \"type\": \"user\",\n"
                + "    \"id\": \"10543463\",\n"
                + "    \"name\": \"Arielle Frey\",\n"
                + "    \"login\": \"ariellefrey@box.com\",\n"
                + "    \"created_at\": \"2011-01-07T12:37:09-08:00\",\n"
                + "    \"modified_at\": \"2014-05-30T10:39:47-07:00\",\n"
                + "    \"language\": \"en\",\n"
                + "    \"timezone\": \"America/Los_Angeles\",\n"
                + "    \"space_amount\": 10737418240,\n"
                + "    \"space_used\": 558732,\n"
                + "    \"max_upload_size\": 5368709120,\n"
                + "    \"status\": \"active\",\n"
                + "    \"job_title\": \"\",\n"
                + "    \"phone\": \"\",\n"
                + "    \"address\": \"\",\n"
                + "    \"avatar_url\": \"https://blosserdemoaccount.app.box.com/api/avatar/large/10543465\"\n"
                + "}");

        BoxAPIConnection api = new BoxAPIConnection("");
        api.setRequestInterceptor(JSONRequestInterceptor.respondWith(fakeJSONResponse));

        BoxUser user = new BoxUser(api, id);
        BoxUser.Info info = user.getInfo();
        Assert.assertEquals(id, info.getID());
        Assert.assertEquals(name, info.getName());
        Assert.assertEquals(login, info.getLogin());
        Assert.assertEquals(createdAt, info.getCreatedAt());
        Assert.assertEquals(modifiedAt, info.getModifiedAt());
        Assert.assertEquals(language, info.getLanguage());
        Assert.assertEquals(timezone, info.getTimezone());
        Assert.assertEquals(spaceAmount, info.getSpaceAmount());
        Assert.assertEquals(spaceUsed, info.getSpaceUsed());
        Assert.assertEquals(maxUploadSize, info.getMaxUploadSize());
        Assert.assertEquals(status, info.getStatus());
        Assert.assertEquals(jobTitle, info.getJobTitle());
        Assert.assertEquals(phone, info.getPhone());
        Assert.assertEquals(address, info.getAddress());
        Assert.assertEquals(avatarURL, info.getAvatarURL());

    }

    /**
     * Unit test for {@link BoxUser#getCurrentUser(BoxAPIConnection)}
     */
    @Test
    @Category(UnitTest.class)
    public void testGetCurrentUserSendsCorrectRequest() {
        BoxAPIConnection api = new BoxAPIConnection("");
        api.setRequestInterceptor(new RequestInterceptor() {
            @Override
            public BoxAPIResponse onRequest(BoxAPIRequest request) {
                Assert.assertEquals("https://api.box.com/2.0/users/me",
                        request.getUrl().toString());
                return new BoxJSONResponse() {
                    @Override
                    public String getJSON() {
                        return "{\"id\": \"0\"}";
                    }
                };
            }
        });

        BoxUser.getCurrentUser(api);
    }

    /**
     * Unit test for {@link BoxUser#getCurrentUser(BoxAPIConnection)}
     */
    @Test
    @Category(UnitTest.class)
    public void testGetCurrentUserParseAllFieldsCorrectly() {
        final String id = "17738362";

        final JsonObject fakeJSONResponse = JsonObject.readFrom("{\n"
                + "    \"type\": \"user\",\n"
                + "    \"id\": \"17738362\",\n"
                + "    \"name\": \"sean rose\",\n"
                + "    \"login\": \"sean@box.com\",\n"
                + "    \"created_at\": \"2012-03-26T15:43:07-07:00\",\n"
                + "    \"modified_at\": \"2012-12-12T11:34:29-08:00\",\n"
                + "    \"language\": \"en\",\n"
                + "    \"space_amount\": 5368709120,\n"
                + "    \"space_used\": 2377016,\n"
                + "    \"max_upload_size\": 262144000,\n"
                + "    \"status\": \"active\",\n"
                + "    \"job_title\": \"Employee\",\n"
                + "    \"phone\": \"5555555555\",\n"
                + "    \"address\": \"555 Office Drive\",\n"
                + "    \"avatar_url\": \"https://app.box.com/api/avatar/large/17738362\"\n"
                + "}");

        BoxAPIConnection api = new BoxAPIConnection("");
        api.setRequestInterceptor(JSONRequestInterceptor.respondWith(fakeJSONResponse));

        BoxUser user = BoxUser.getCurrentUser(api);
        Assert.assertEquals(id, user.getID());

    }

    /**
     * Unit test for {@link BoxUser#delete(boolean, boolean)}
     */
    @Test
    @Category(UnitTest.class)
    public void testDeleteUserSendsCorrectRequest() {
        BoxAPIConnection api = new BoxAPIConnection("");
        api.setRequestInterceptor(new RequestInterceptor() {
            @Override
            public BoxAPIResponse onRequest(BoxAPIRequest request) {
                Assert.assertEquals("https://api.box.com/2.0/users/0?notify=true&force=true",
                        request.getUrl().toString());
                return new BoxJSONResponse() {
                    @Override
                    public String getJSON() {
                        return "{\"id\": \"0\"}";
                    }
                };
            }
        });

        BoxUser user = new BoxUser(api, "0");
        user.delete(true, true);
    }

    /**
     * Unit test for {@link BoxUser#deleteEmailAlias(String)}
     */
    @Test
    @Category(UnitTest.class)
    public void testDeleteEmailAliasSendsCorrectRequest() {
        BoxAPIConnection api = new BoxAPIConnection("");
        api.setRequestInterceptor(new RequestInterceptor() {
            @Override
            public BoxAPIResponse onRequest(BoxAPIRequest request) {
                Assert.assertEquals("https://api.box.com/2.0/users/0/email_aliases/1",
                        request.getUrl().toString());
                return new BoxJSONResponse() {
                    @Override
                    public String getJSON() {
                        return "{\"id\": \"0\"}";
                    }
                };
            }
        });

        BoxUser user = new BoxUser(api, "0");
        user.deleteEmailAlias("1");
    }

    /**
     * Unit test for {@link BoxUser#moveFolderToUser(String)}
     */
    @Test
    @Category(UnitTest.class)
    public void testMoveFolderToUserSendsCorrectJson() {
        final String ownedByID = "0";

        BoxAPIConnection api = new BoxAPIConnection("");
        api.setRequestInterceptor(new JSONRequestInterceptor() {
            @Override
            protected BoxAPIResponse onJSONRequest(BoxJSONRequest request, JsonObject json) {
                Assert.assertEquals("https://api.box.com/2.0/users/1/folders/0",
                        request.getUrl().toString());
                Assert.assertEquals(ownedByID, json.get("owned_by").asObject().get("id").asString());
                return new BoxJSONResponse() {
                    @Override
                    public String getJSON() {
                        return "{\"id\": \"0\"}";
                    }
                };
            }
        });

        BoxUser user = new BoxUser(api, "0");
        user.moveFolderToUser("1");

    }

    /**
     * Unit test for {@link BoxUser#moveFolderToUser(String)}
     */
    @Test
    @Category(UnitTest.class)
    public void testMoveFolderToUserParseAllFieldsCorrectly() throws ParseException {
        final String id = "11446498";
        final String sequenceID = "1";
        final String etag = "1";
        final String name = "Pictures";
        final Date createdAt = BoxDateFormat.parse("2012-12-12T10:53:43-08:00");
        final Date modifiedAt = BoxDateFormat.parse("2012-12-12T10:53:43-08:00");
        final String description = "Some pictures I took";
        final long size = 629644;
        final String pathID = "0";
        final String pathSequenceID = null;
        final String pathEtag = null;
        final String pathName = "All Files";
        final String createdByID = "17738362";
        final String createdByName = "sean rose";
        final String createdByLogin = "sean@box.com";
        final String modifiedByID = "17738362";
        final String modifiedByName = "sean rose";
        final String modifiedByLogin = "sean@box.com";
        final String ownedByID = "17738362";
        final String ownedByName = "sean rose";
        final String ownedByLogin = "sean@box.com";
        final String url = "https://www.box.com/s/vspke7y05sb214wjokpk";
        final String downloadURL = null;
        final String vanityUrl = null;
        final boolean isPasswordEnabled = false;
        final Date unsharedAt = null;
        final long downloadCount = 0;
        final long previewCount = 0;
        final BoxSharedLink.Access access = BoxSharedLink.Access.OPEN;
        final boolean canDownload = true;
        final boolean canPreview = true;
        final BoxUploadEmail.Access folderUploadEmailAccess = BoxUploadEmail.Access.OPEN;
        final String folderUploadEmail = "upload.Picture.k13sdz1@u.box.com";
        final String parentID = "0";
        final String parentSequenceID = null;
        final String parentEtag = null;
        final String parentName = "All Files";
        final String itemStatus = "active";
        final JsonObject fakeJSONResponse = JsonObject.readFrom("{\n"
                + "    \"type\": \"folder\",\n"
                + "    \"id\": \"11446498\",\n"
                + "    \"sequence_id\": \"1\",\n"
                + "    \"etag\": \"1\",\n"
                + "    \"name\": \"Pictures\",\n"
                + "    \"created_at\": \"2012-12-12T10:53:43-08:00\",\n"
                + "    \"modified_at\": \"2012-12-12T10:53:43-08:00\",\n"
                + "    \"description\": \"Some pictures I took\",\n"
                + "    \"size\": 629644,\n"
                + "    \"path_collection\": {\n"
                + "        \"total_count\": 1,\n"
                + "        \"entries\": [\n"
                + "            {\n"
                + "                \"type\": \"folder\",\n"
                + "                \"id\": \"0\",\n"
                + "                \"sequence_id\": null,\n"
                + "                \"etag\": null,\n"
                + "                \"name\": \"All Files\"\n"
                + "            }\n"
                + "        ]\n"
                + "    },\n"
                + "    \"created_by\": {\n"
                + "        \"type\": \"user\",\n"
                + "        \"id\": \"17738362\",\n"
                + "        \"name\": \"sean rose\",\n"
                + "        \"login\": \"sean@box.com\"\n"
                + "    },\n"
                + "    \"modified_by\": {\n"
                + "        \"type\": \"user\",\n"
                + "        \"id\": \"17738362\",\n"
                + "        \"name\": \"sean rose\",\n"
                + "        \"login\": \"sean@box.com\"\n"
                + "    },\n"
                + "    \"owned_by\": {\n"
                + "        \"type\": \"user\",\n"
                + "        \"id\": \"17738362\",\n"
                + "        \"name\": \"sean rose\",\n"
                + "        \"login\": \"sean@box.com\"\n"
                + "    },\n"
                + "    \"shared_link\": {\n"
                + "        \"url\": \"https://www.box.com/s/vspke7y05sb214wjokpk\",\n"
                + "        \"download_url\": null,\n"
                + "        \"vanity_url\": null,\n"
                + "        \"is_password_enabled\": false,\n"
                + "        \"unshared_at\": null,\n"
                + "        \"download_count\": 0,\n"
                + "        \"preview_count\": 0,\n"
                + "        \"access\": \"open\",\n"
                + "        \"permissions\": {\n"
                + "            \"can_download\": true,\n"
                + "            \"can_preview\": true\n"
                + "        }\n"
                + "    },\n"
                + "    \"folder_upload_email\": {\n"
                + "        \"access\": \"open\",\n"
                + "        \"email\": \"upload.Picture.k13sdz1@u.box.com\"\n"
                + "    },\n"
                + "    \"parent\": {\n"
                + "        \"type\": \"folder\",\n"
                + "        \"id\": \"0\",\n"
                + "        \"sequence_id\": null,\n"
                + "        \"etag\": null,\n"
                + "        \"name\": \"All Files\"\n"
                + "    },\n"
                + "    \"item_status\": \"active\"\n"
                + "}");
        BoxAPIConnection api = new BoxAPIConnection("");
        api.setRequestInterceptor(JSONRequestInterceptor.respondWith(fakeJSONResponse));
        BoxUser user = new BoxUser(api, id);
        BoxFolder.Info folder = user.moveFolderToUser("0");
        Assert.assertEquals(id, folder.getID());
        Assert.assertEquals(sequenceID, folder.getSequenceID());
        Assert.assertEquals(etag, folder.getEtag());
        Assert.assertEquals(name, folder.getName());
        Assert.assertEquals(createdAt, folder.getCreatedAt());
        Assert.assertEquals(modifiedAt, folder.getModifiedAt());
        Assert.assertEquals(description, folder.getDescription());
        Assert.assertEquals(size, folder.getSize());
        Assert.assertEquals(pathID, folder.getPathCollection().get(0).getID());
        Assert.assertEquals(pathSequenceID, folder.getPathCollection().get(0).getSequenceID());
        Assert.assertEquals(pathEtag, folder.getPathCollection().get(0).getEtag());
        Assert.assertEquals(pathName, folder.getPathCollection().get(0).getName());
        Assert.assertEquals(createdByID, folder.getCreatedBy().getID());
        Assert.assertEquals(createdByName, folder.getCreatedBy().getName());
        Assert.assertEquals(createdByLogin, folder.getCreatedBy().getLogin());
        Assert.assertEquals(modifiedByID, folder.getModifiedBy().getID());
        Assert.assertEquals(modifiedByName, folder.getModifiedBy().getName());
        Assert.assertEquals(modifiedByLogin, folder.getModifiedBy().getLogin());
        Assert.assertEquals(ownedByID, folder.getOwnedBy().getID());
        Assert.assertEquals(ownedByName, folder.getOwnedBy().getName());
        Assert.assertEquals(ownedByLogin, folder.getOwnedBy().getLogin());
        Assert.assertEquals(url, folder.getSharedLink().getURL());
        Assert.assertEquals(downloadURL, folder.getSharedLink().getDownloadURL());
        Assert.assertEquals(vanityUrl, folder.getSharedLink().getVanityURL());
        Assert.assertEquals(isPasswordEnabled, folder.getSharedLink().getIsPasswordEnabled());
        Assert.assertEquals(unsharedAt, folder.getSharedLink().getUnsharedDate());
        Assert.assertEquals(downloadCount, folder.getSharedLink().getDownloadCount());
        Assert.assertEquals(previewCount, folder.getSharedLink().getPreviewCount());
        Assert.assertEquals(access, folder.getSharedLink().getAccess());
        Assert.assertEquals(canDownload, folder.getSharedLink().getPermissions().getCanDownload());
        Assert.assertEquals(canPreview, folder.getSharedLink().getPermissions().getCanPreview());
        Assert.assertEquals(folderUploadEmailAccess, folder.getUploadEmail().getAccess());
        Assert.assertEquals(folderUploadEmail, folder.getUploadEmail().getEmail());
        Assert.assertEquals(parentID, folder.getParent().getID());
        Assert.assertEquals(parentSequenceID, folder.getParent().getSequenceID());
        Assert.assertEquals(parentEtag, folder.getParent().getEtag());
        Assert.assertEquals(parentName, folder.getParent().getName());
        Assert.assertEquals(itemStatus, folder.getItemStatus());
    }

    /**
     * Unit test for {@link BoxUser#getAllMemberships(String...)}.
     */
    @Test
    @Category(UnitTest.class)
    public void testGetAllMembershipsSendsCorrectRequest() {
        final JsonObject fakeJSONResponse = new JsonObject()
                .add("total_count", 0)
                .add("offset", 0)
                .add("entries", new JsonArray());

        BoxAPIConnection api = new BoxAPIConnection("");
        api.setRequestInterceptor(new RequestInterceptor() {
            @Override
            public BoxAPIResponse onRequest(BoxAPIRequest request) {
                assertEquals("https://api.box.com/2.0/users/0/memberships?fields=user%2Cgroup&limit=1000&offset=0",
                        request.getUrl().toString());
                return new BoxJSONResponse() {
                    @Override
                    public String getJSON() {
                        return fakeJSONResponse.toString();
                    }
                };
            }
        });

        BoxUser user = new BoxUser(api, "0");
        Iterator<BoxGroupMembership.Info> iterator = user.getAllMemberships("user", "group").iterator();
        iterator.hasNext();
    }

    /**
     * Unit test for {@link BoxUser#getAllMemberships(String...)}.
     */
    @Test
    @Category(UnitTest.class)
    public void testGetMembershipsParseAllFieldsCorrectly() throws ParseException {
        final String id = "1560354";
        final String userID = "13130406";
        final String userName = "Alison Wonderland";
        final String userLogin = "alice@gmail.com";
        final String groupID = "119720";
        final String groupName = "family";
        final BoxGroupMembership.Role role = BoxGroupMembership.Role.MEMBER;

        final JsonObject fakeJSONResponse = JsonObject.readFrom("{\n"
                + "    \"total_count\": 1,\n"
                + "    \"entries\": [\n"
                + "        {\n"
                + "            \"type\": \"group_membership\",\n"
                + "            \"id\": \"1560354\",\n"
                + "            \"user\": {\n"
                + "                \"type\": \"user\",\n"
                + "                \"id\": \"13130406\",\n"
                + "                \"name\": \"Alison Wonderland\",\n"
                + "                \"login\": \"alice@gmail.com\"\n"
                + "            },\n"
                + "            \"group\": {\n"
                + "                \"type\": \"group\",\n"
                + "                \"id\": \"119720\",\n"
                + "                \"name\": \"family\"\n"
                + "            },\n"
                + "            \"role\": \"member\"\n"
                + "        }\n"
                + "    ],\n"
                + "    \"limit\": 100,\n"
                + "    \"offset\": 0\n"
                + "}");

        BoxAPIConnection api = new BoxAPIConnection("");
        api.setRequestInterceptor(JSONRequestInterceptor.respondWith(fakeJSONResponse));
        BoxUser user = new BoxUser(api, "0");
        Iterator<BoxGroupMembership.Info> iterator = user.getAllMemberships().iterator();
        BoxGroupMembership.Info info = iterator.next();
        assertEquals(id, info.getID());
        assertEquals(userID, info.getUser().getID());
        assertEquals(userName, info.getUser().getName());
        assertEquals(userLogin, info.getUser().getLogin());
        assertEquals(groupID, info.getGroup().getID());
        assertEquals(groupName, info.getGroup().getName());
        assertEquals(role, info.getRole());
        assertEquals(false, iterator.hasNext());
    }

    @Test
    @Category(IntegrationTest.class)
    public void getCurrentUserInfoIsCorrect() throws InterruptedException {
        final String expectedName = "Java SDK";
        BoxAPIConnection api = new BoxAPIConnection(TestConfig.getAccessToken());
        BoxUser user = BoxUser.getCurrentUser(api);
        BoxUser.Info info = user.getInfo(BoxUser.ALL_FIELDS);

        assertThat(info.getName(), equalTo(expectedName));
        assertNotNull(info.getEnterprise().getID());
    }

    @Test
    @Category(IntegrationTest.class)
    public void createAndDeleteEnterpriseUserSucceeds() {
        BoxAPIConnection api = new BoxAPIConnection(TestConfig.getAccessToken());
        // Since deleting users happens in a separate process in the backend
        // it is really an asynchronous call.  So we have to use a new user in
        // this test in case the previous user's deletion hasn't completed.
        final String login = "login2@boz.com";
        final String name = "non-empty name";

        BoxUser.Info createdUserInfo = BoxUser.createEnterpriseUser(api, login, name);

        assertNotNull(createdUserInfo.getID());
        assertEquals(name, createdUserInfo.getName());
        assertEquals(login, createdUserInfo.getLogin());

        createdUserInfo.getResource().delete(false, false);
    }

    @Test
    @Category(IntegrationTest.class)
    public void getMembershipsHasCorrectMemberships() {
        BoxAPIConnection api = new BoxAPIConnection(TestConfig.getAccessToken());
        String groupName = "[getMembershipsHasCorrectMemberships] Test Group";
        BoxUser user = BoxUser.getCurrentUser(api);
        BoxGroupMembership.Role membershipRole = BoxGroupMembership.Role.ADMIN;

        BoxGroup group = BoxGroup.createGroup(api, groupName).getResource();
        BoxGroupMembership.Info membershipInfo = group.addMembership(user, membershipRole);
        String membershipID = membershipInfo.getID();

        Collection<BoxGroupMembership.Info> memberships = user.getMemberships();

        assertThat(memberships, hasSize(greaterThanOrEqualTo(1)));
        assertThat(memberships, hasItem(Matchers.<BoxGroupMembership.Info>hasProperty("ID", equalTo(membershipID))));

        group.delete();
    }

    @Test
    @Category(IntegrationTest.class)
    public void updateInfoSucceeds() {
        BoxAPIConnection api = new BoxAPIConnection(TestConfig.getAccessToken());
        final String login = "login3@boz.com";
        final String originalName = "original name";
        final String updatedName = "updated name";

        BoxUser.Info userInfo = BoxUser.createEnterpriseUser(api, login, originalName);
        userInfo.setName(updatedName);

        BoxUser user = userInfo.getResource();
        user.updateInfo(userInfo);

        assertEquals(updatedName, userInfo.getName());

        user.delete(false, false);
    }
}
