package com.box.sdk;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.StringTokenizer;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.github.tomakehurst.wiremock.junit.WireMockClassRule;
import org.junit.Assert;
import org.junit.ClassRule;
import org.junit.Test;
import org.junit.experimental.categories.Category;


import com.eclipsesource.json.JsonObject;

import com.github.tomakehurst.wiremock.client.WireMock;
import static com.github.tomakehurst.wiremock.client.WireMock.aResponse;
import static com.github.tomakehurst.wiremock.client.WireMock.post;
import static com.github.tomakehurst.wiremock.client.WireMock.urlPathEqualTo;

public class BoxAPIConnectionTest {

    /**
     * Wiremock
     */
    @ClassRule
    public static final WireMockClassRule WIRE_MOCK_CLASS_RULE = new WireMockClassRule(53621);

    @Test
    @Category(UnitTest.class)
    public void canRefreshWhenGivenRefreshToken() {
        final String anyClientID = "";
        final String anyClientSecret = "";
        final String anyAccessToken = "";
        final String anyRefreshToken = "";

        BoxAPIConnection api = new BoxAPIConnection(anyClientID, anyClientSecret, anyAccessToken, anyRefreshToken);

        assertThat(api.canRefresh(), is(true));
    }

    @Test
    @Category(UnitTest.class)
    public void needsRefreshWhenTokenHasExpired() {
        final String anyAccessToken = "";

        BoxAPIConnection api = new BoxAPIConnection(anyAccessToken);
        api.setExpires(-1);

        assertThat(api.needsRefresh(), is(true));
    }

    @Test
    @Category(UnitTest.class)
    public void doesNotNeedRefreshWhenTokenHasNotExpired() {
        final String anyAccessToken = "";

        BoxAPIConnection api = new BoxAPIConnection(anyAccessToken);
        api.setExpires(Long.MAX_VALUE);

        assertThat(api.needsRefresh(), is(not(true)));
    }

    @Test
    @Category(UnitTest.class)
    public void needsRefreshWhenExpiresIsZero() {
        final String anyAccessToken = "";

        BoxAPIConnection api = new BoxAPIConnection(anyAccessToken);
        api.setExpires(0);

        assertThat(api.needsRefresh(), is(true));
    }

    @Test
    @Category(UnitTest.class)
    public void interceptorReceivesSentRequest() throws MalformedURLException {
        BoxAPIConnection api = new BoxAPIConnection("");

        BoxAPIResponse fakeResponse = new BoxAPIResponse();

        RequestInterceptor mockInterceptor = mock(RequestInterceptor.class);
        when(mockInterceptor.onRequest(any(BoxAPIRequest.class))).thenReturn(fakeResponse);
        api.setRequestInterceptor(mockInterceptor);

        BoxAPIRequest request = new BoxAPIRequest(api, new URL("http://anyurl.com"), "GET");
        BoxAPIResponse response = request.send();

        assertThat(response, is(equalTo(fakeResponse)));
    }

    @Test
    @Category(UnitTest.class)
    public void restoreConnectionThatDoesNotNeedRefresh() {
        BoxAPIConnection api = new BoxAPIConnection("fake client ID", "fake client secret", "fake access token",
            "fake refresh token");
        api.setExpires(3600000L);
        api.setLastRefresh(System.currentTimeMillis());
        String state = api.save();

        final BoxAPIConnection restoredAPI = BoxAPIConnection.restore("fake client ID", "fake client secret", state);
        restoredAPI.setRequestInterceptor(new RequestInterceptor() {
            @Override
            public BoxAPIResponse onRequest(BoxAPIRequest request) {
                String tokenURLString = restoredAPI.getTokenURL().toString();
                String requestURLString = request.getUrl().toString();
                if (requestURLString.contains(tokenURLString)) {
                    fail("The connection was refreshed.");
                }

                if (requestURLString.contains("folders")) {
                    return new BoxJSONResponse() {
                        @Override
                        public String getJSON() {
                            JsonObject responseJSON = new JsonObject()
                                .add("id", "fake ID")
                                .add("type", "folder");
                            return responseJSON.toString();
                        }
                    };
                }

                fail("Unexpected request.");
                return null;
            }
        });

        assertFalse(restoredAPI.needsRefresh());
    }

    @Test
    @Category(UnitTest.class)
    public void getAuthorizationURLSuccess() throws Exception {
        List<String> scopes = new ArrayList<String>();
        scopes.add("root_readwrite");
        scopes.add("manage_groups");

        URL authURL = BoxAPIConnection.getAuthorizationURL("wncmz88sacf5oyaxf502dybcruqbzzy0",
                new URI("http://localhost:3000"), "test", scopes);

        Assert.assertTrue(authURL.toString().startsWith("https://account.box.com/api/oauth2/authorize"));

        StringTokenizer tokenizer = new StringTokenizer(authURL.getQuery(), "&");
        while (tokenizer.hasMoreTokens()) {
            String token = tokenizer.nextToken();
            if (token.startsWith("client_id")) {
                Assert.assertEquals(token, "client_id=wncmz88sacf5oyaxf502dybcruqbzzy0");
            } else if (token.startsWith("response_type")) {
                Assert.assertEquals(token, "response_type=code");
            } else if (token.startsWith("redirect_uri")) {
                Assert.assertEquals(token, "redirect_uri=http%3A%2F%2Flocalhost%3A3000");
            } else if (token.startsWith("state")) {
                Assert.assertEquals(token, "state=test");
            } else if (token.startsWith("scope")) {
                Assert.assertEquals(token, "scope=root_readwrite+manage_groups");
            }
        }
    }

    @Test
    @Category(IntegrationTest.class)
    public void requestIsSentNormallyWhenInterceptorReturnsNullResponse() throws MalformedURLException {
        BoxAPIConnection api = new BoxAPIConnection("");

        RequestInterceptor mockInterceptor = mock(RequestInterceptor.class);
        when(mockInterceptor.onRequest(any(BoxAPIRequest.class))).thenReturn(null);
        api.setRequestInterceptor(mockInterceptor);

        BoxAPIRequest request = new BoxAPIRequest(api, new URL("http://box.com"), "GET");
        BoxAPIResponse response = request.send();

        assertThat(response.getResponseCode(), is(200));
    }

    @Test
    @Category(IntegrationTest.class)
    public void refreshSucceeds() {
        final String originalAccessToken = TestConfig.getAccessToken();
        final String originalRefreshToken = TestConfig.getRefreshToken();
        BoxAPIConnection api = new BoxAPIConnection(TestConfig.getClientID(), TestConfig.getClientSecret(),
            originalAccessToken, originalRefreshToken);

        api.refresh();

        String actualAccessToken = api.getAccessToken();
        String actualRefreshToken = api.getRefreshToken();

        assertThat(originalRefreshToken, not(equalTo(actualRefreshToken)));
        assertThat(originalAccessToken, not(equalTo(actualAccessToken)));

        TestConfig.setAccessToken(actualAccessToken);
        TestConfig.setRefreshToken(actualRefreshToken);
    }

    @Test
    @Category(IntegrationTest.class)
    public void refreshesWhenGetAccessTokenIsCalledAndTokenHasExpired() {
        final String originalAccessToken = TestConfig.getAccessToken();
        final String originalRefreshToken = TestConfig.getRefreshToken();
        BoxAPIConnection api = new BoxAPIConnection(TestConfig.getClientID(), TestConfig.getClientSecret(),
            originalAccessToken, originalRefreshToken);
        api.setExpires(-1);

        String actualAccessToken = api.getAccessToken();
        String actualRefreshToken = api.getRefreshToken();

        assertThat(originalRefreshToken, not(equalTo(actualRefreshToken)));
        assertThat(originalAccessToken, not(equalTo(actualAccessToken)));

        TestConfig.setAccessToken(actualAccessToken);
        TestConfig.setRefreshToken(actualRefreshToken);
    }

    @Test
    @Category(IntegrationTest.class)
    public void doesNotRefreshWhenGetAccessTokenIsCalledAndTokenHasNotExpired() {
        final String originalAccessToken = TestConfig.getAccessToken();
        final String originalRefreshToken = TestConfig.getRefreshToken();
        BoxAPIConnection api = new BoxAPIConnection(TestConfig.getClientID(), TestConfig.getClientSecret(),
            originalAccessToken, originalRefreshToken);
        api.setExpires(Long.MAX_VALUE);

        String actualAccessToken = api.getAccessToken();
        String actualRefreshToken = api.getRefreshToken();

        assertThat(originalRefreshToken, equalTo(actualRefreshToken));
        assertThat(originalAccessToken, equalTo(actualAccessToken));

        TestConfig.setAccessToken(actualAccessToken);
        TestConfig.setRefreshToken(actualRefreshToken);
    }

    @Test
    @Category(IntegrationTest.class)
    public void successfullySavesAndRestoresConnection() {
        final String originalAccessToken = TestConfig.getAccessToken();
        final String originalRefreshToken = TestConfig.getRefreshToken();
        BoxAPIConnection api = new BoxAPIConnection(TestConfig.getClientID(), TestConfig.getClientSecret(),
            originalAccessToken, originalRefreshToken);
        String state = api.save();

        BoxAPIConnection restoredAPI = BoxAPIConnection.restore(TestConfig.getClientID(), TestConfig.getClientSecret(),
            state);
        BoxFolder.Info rootFolderInfo = BoxFolder.getRootFolder(restoredAPI).getInfo();

        TestConfig.setAccessToken(restoredAPI.getAccessToken());
        TestConfig.setRefreshToken(restoredAPI.getRefreshToken());
    }

    @Test
    @Category(IntegrationTest.class)
    public void revokeToken() {

        String accessToken = TestConfig.getAccessToken();
        String clientID = TestConfig.getClientID();
        String clientSecret = TestConfig.getClientSecret();
        BoxAPIConnection api = new BoxAPIConnection(clientID, clientSecret, accessToken, "");

        BoxFolder.getRootFolder(api);

        api.revokeToken();

        try {
            BoxFolder.getRootFolder(api);
        } catch (BoxAPIException ex) {
            assertEquals(401, ex.getResponseCode());
        }
    }

    @Test
    @Category(UnitTest.class)
    public void revokeTokenCallsCorrectEndpoint() {

        String accessToken = "fakeAccessToken";
        String clientID = "fakeID";
        String clientSecret = "fakeSecret";

        BoxAPIConnection api = new BoxAPIConnection(clientID, clientSecret, accessToken, "");
        api.setRevokeURL("http://localhost:53621/oauth2/revoke");

        WIRE_MOCK_CLASS_RULE.stubFor(post(urlPathEqualTo("/oauth2/revoke"))
                .withRequestBody(WireMock.equalTo("token=fakeAccessToken&client_id=fakeID&client_secret=fakeSecret"))
                .willReturn(aResponse()
                        .withHeader("Content-Type", "application/json")
                        .withBody("")));

        api.revokeToken();
    }

    @Test
    @Category(IntegrationTestJWT.class)
    public void developerEditionAppAuthWorks() throws IOException {
        Reader reader = new FileReader("src/test/config/config.json");
        BoxConfig boxConfig = BoxConfig.readFrom(reader);
        IAccessTokenCache accessTokenCache = new InMemoryLRUAccessTokenCache(100);

        BoxDeveloperEditionAPIConnection api =
            BoxDeveloperEditionAPIConnection.getAppEnterpriseConnection(boxConfig, accessTokenCache);

        assertThat(api.getAccessToken(), not(equalTo(null)));

        final String name = "app user name";
        final String externalAppUserId = "login2@boz.com";
        CreateUserParams params = new CreateUserParams();
        params.setExternalAppUserId(externalAppUserId);
        BoxUser appUser = null;
        try {
            BoxUser.Info createdUserInfo = BoxUser.createAppUser(api, name, params);
            final String appUserId = createdUserInfo.getID();

            assertThat(createdUserInfo.getID(), not(equalTo(null)));
            assertThat(createdUserInfo.getName(), equalTo(name));

            appUser = new BoxUser(api, appUserId);
            assertEquals(externalAppUserId,
                appUser.getInfo(BoxUser.ALL_FIELDS).getExternalAppUserId());


            //Testing update works
            final String newName = "app user updated name";
            final String updatedExternalAppUserId = "login3@boz.com";

            createdUserInfo.setName(newName);
            createdUserInfo.setExternalAppUserId(updatedExternalAppUserId);
            appUser.updateInfo(createdUserInfo);

            assertThat(createdUserInfo.getName(), equalTo(newName));
            assertEquals(updatedExternalAppUserId,
                createdUserInfo.getResource().getInfo("external_app_user_id").getExternalAppUserId());

            //Testing getAppUsers works
            Iterable<BoxUser.Info> users = BoxUser.getAppUsersByExternalAppUserID(api,
                updatedExternalAppUserId, "external_app_user_id");
            for (BoxUser.Info userInfo : users) {
                assertEquals(updatedExternalAppUserId, userInfo.getExternalAppUserId());
            }
        } finally {
            appUser.delete(false, true);
        }
        api.refresh();
    }

    @Test
    @Category(IntegrationTestJWT.class)
    public void developerEditionAppUserWorks() throws IOException {
        Reader reader = new FileReader("src/test/config/config.json");
        BoxConfig boxConfig = BoxConfig.readFrom(reader);
        IAccessTokenCache accessTokenCache = new InMemoryLRUAccessTokenCache(100);

        BoxDeveloperEditionAPIConnection appAuthConnection =
            BoxDeveloperEditionAPIConnection.getAppEnterpriseConnection(boxConfig, accessTokenCache);

        final String name = "app user name two";
        BoxUser.Info createdUserInfo = BoxUser.createAppUser(appAuthConnection, name);
        final String appUserId = createdUserInfo.getID();

        BoxDeveloperEditionAPIConnection api = BoxDeveloperEditionAPIConnection.getAppUserConnection(appUserId,
            boxConfig, accessTokenCache);
        BoxUser appUser = new BoxUser(api, appUserId);

        assertThat(api.getAccessToken(), not(equalTo(null)));

        BoxUser.Info info = appUser.getInfo();

        assertThat(info.getID(), equalTo(appUserId));
        assertThat(info.getName(), equalTo(name));

        api.refresh();

        BoxUser appUserFromAdmin = new BoxUser(appAuthConnection, appUserId);
        appUserFromAdmin.delete(false, true);
    }

    @Test
    @Category(IntegrationTestJWT.class)
    public void appUsersAutomaticallyPaginatesCorrectly() throws IOException {
        Reader reader = new FileReader("src/test/config/config.json");
        BoxConfig boxConfig = BoxConfig.readFrom(reader);
        IAccessTokenCache accessTokenCache = new InMemoryLRUAccessTokenCache(100);

        BoxDeveloperEditionAPIConnection api =
            BoxDeveloperEditionAPIConnection.getAppEnterpriseConnection(boxConfig, accessTokenCache);

        assertThat(api.getAccessToken(), not(equalTo(null)));

        long timestamp = Calendar.getInstance().getTimeInMillis();
        final String name = "appUsersAutomaticallyPaginate ";
        final String externalAppUserId = "@example.com";

        // Must be greater than the default response page size (100) to ensure a second page of results
        final int totalCreatedTestAppUsers = 105;

        // Create Test App Users
        System.out.println("Creating Test App Users");
        for (int i = 1; i <= totalCreatedTestAppUsers; i++) {
            CreateUserParams params = new CreateUserParams();
            params.setExternalAppUserId(timestamp + "_" + i + externalAppUserId);
            BoxUser.Info createdUserInfo = BoxUser.createAppUser(api, name + timestamp + " " + i, params);
        }

        // Get App Users
        Iterable<BoxUser.Info> users = BoxUser.getAppUsersByExternalAppUserID(api,
            null, "external_app_user_id", "name");

        // Iterate over the returned App Users
        int totalReturnedTestAppUsers = 0;
        BoxUser appUser = null;
        System.out.println("Cleanup (delete) Test App Users");
        for (BoxUser.Info userInfo : users) {
            System.out.println(userInfo.getName());
            appUser = new BoxUser(api, userInfo.getID());

            // Count the Test App Users just created.
            if (userInfo.getName().startsWith(name + timestamp)) {
                totalReturnedTestAppUsers++;
            }

            // Delete the Test App Users just created.
            // Deletes Test App Users created in previous test run, as well.
            if (userInfo.getName().startsWith(name)) {
                appUser.delete(false, true);
            }
        }

        assertEquals(totalCreatedTestAppUsers, totalReturnedTestAppUsers);
        api.refresh();
    }

    @Test
    @Category(IntegrationTestJWT.class)
    public void appUsersManuallyPaginatesCorrectly() throws IOException {
        Reader reader = new FileReader("src/test/config/config.json");
        BoxConfig boxConfig = BoxConfig.readFrom(reader);
        IAccessTokenCache accessTokenCache = new InMemoryLRUAccessTokenCache(100);

        BoxDeveloperEditionAPIConnection api =
            BoxDeveloperEditionAPIConnection.getAppEnterpriseConnection(boxConfig, accessTokenCache);

        assertThat(api.getAccessToken(), not(equalTo(null)));

        final long timestamp = Calendar.getInstance().getTimeInMillis();
        final String name = "appUsersManuallyPaginate ";
        final String externalAppUserId = "@example.com";

        // Must be greater than defaultNetworkResponsePageSize to ensure a second page of results
        final int totalCreatedTestAppUsers = 105;

        // Must be equal to default response limit (usually 100)
        final int defaultNetworkResponsePageSize = 100;

        // Create Test App Users
        System.out.println("Creating Test App Users");
        for (int i = 1; i <= totalCreatedTestAppUsers; i++) {
            CreateUserParams params = new CreateUserParams();
            params.setExternalAppUserId(timestamp + "_" + i + externalAppUserId);
            BoxUser.Info createdUserInfo = BoxUser.createAppUser(api, name + timestamp + " " + i, params);
        }

        // Get App Users
        BoxResourceIterable<BoxUser.Info> users =
            (BoxResourceIterable<BoxUser.Info>) BoxUser.getAppUsersByExternalAppUserID(
                api,
                null,
                true,
                null,
                "external_app_user_id", "name");

        // Grab the marker to be able to resume iterating at some point in the future...
        String marker = users.getNextMarker();

        // As we iterate over the page of results, pageCursor is used to keep track
        // of the current item in the page, so that iteration can be picked up where you left
        // off later on.
        int pageCursor = 0;

        int totalReturnedTestAppUsers = 0;
        BoxUser appUser = null;

        // Count App Users and stop iterating once we get to the last item on the first page of results
        System.out.println("===FIRST  PAGE===");
        for (BoxUser.Info userInfo : users) {
            System.out.println(userInfo.getName());
            appUser = new BoxUser(api, userInfo.getID());

            // Count the Test App Users just created.
            if (userInfo.getName().startsWith(name + timestamp)) {
                totalReturnedTestAppUsers++;
            }

            // Stop iterating once we get to the last item on the page of results.
            // If we were to keep iterating in this loop, then BoxResourceIterable would
            // automatically make the request to get the next page of results.
            if (pageCursor == defaultNetworkResponsePageSize - 1) {
                break;
            }

            pageCursor++;
        }

        // Manually get the second page of results, by passing the nextMarker from the last response.
        // This simulates the scenario where we no longer have the users collection, so we need to
        // make a new request, using the preserved marker value which points to the page containing
        // the next item in the collection.
        users = (BoxResourceIterable<BoxUser.Info>) BoxUser.getAppUsersByExternalAppUserID(api,
                    null, true, marker, "external_app_user_id", "name");

        // We're now starting with the first item on the second page of results,
        // so we reset pageCursor
        pageCursor = 0;

        // Continue counting App Users
        // If there are more pages of results, this loop uses the automatic pagination by continuously
        // retrieving the next item and letting BoxResourceIterable determine and manage getting new
        // pages.
        System.out.println("===SECOND PAGE===");
        for (BoxUser.Info userInfo : users) {
            System.out.println(userInfo.getName());
            appUser = new BoxUser(api, userInfo.getID());

            // Count the users just created.
            if (userInfo.getName().startsWith(name + timestamp)) {
                totalReturnedTestAppUsers++;
            }

            pageCursor++;
        }

        // Get App Users for post-test clean up
        users = (BoxResourceIterable<BoxUser.Info>) BoxUser.getAppUsersByExternalAppUserID(api,
                    null, true, null, "external_app_user_id", "name");

        // Clean up
        System.out.println("Cleanup (delete) Test App Users");
        for (BoxUser.Info userInfo : users) {
            appUser = new BoxUser(api, userInfo.getID());

            // Deletes users created in previous test run, as well.
            if (userInfo.getName().startsWith(name)) {
                appUser.delete(false, true);
            }
        }

        assertEquals(totalCreatedTestAppUsers, totalReturnedTestAppUsers);
        api.refresh();
    }

    @Test
    @Category(UnitTest.class)
    public void getLowerScopedTokenWithNullResource() {
        BoxAPIConnection api = mock(BoxAPIConnection.class);

        List<String> scopes = new ArrayList<String>();
        scopes.add("item_preview");
        String resource = null;

        when(api.getTokenURL()).thenReturn("https://api.box.com/oauth2/token");
        when(api.getLowerScopedToken(scopes, resource)).thenCallRealMethod();
        try {
            api.getLowerScopedToken(scopes, resource);
        } catch (RuntimeException e) {
            //Ignore it
        }
        verify(api).getAccessToken();
    }

    @Test
    @Category(UnitTest.class)
    public void getLowerScopedTokenForAPIEndpointResource() {
        BoxAPIConnection api = mock(BoxAPIConnection.class);

        List<String> scopes = new ArrayList<String>();
        scopes.add("item_preview");
        String APIEndpointResource = "https://api.box.com/2.0/files/135906984991";

        when(api.getTokenURL()).thenReturn("https://api.box.com/oauth2/token");
        when(api.getLowerScopedToken(scopes, APIEndpointResource)).thenCallRealMethod();
        try {
            api.getLowerScopedToken(scopes, APIEndpointResource);
        } catch (RuntimeException e) {
            //Ignore it
        }
        verify(api).getAccessToken();
    }

    @Test
    @Category(UnitTest.class)
    public void getLowerScopedTokenForSharedLinkResource() {
        BoxAPIConnection api = mock(BoxAPIConnection.class);

        List<String> scopes = new ArrayList<String>();
        scopes.add("item_preview");

        String sharedLinkResource = "https://rungaia.box.com/s/68c1cewvxas7orqmobakg17o61bfrkcu";

        when(api.getTokenURL()).thenReturn("https://api.box.com/oauth2/token");
        when(api.getLowerScopedToken(scopes, sharedLinkResource)).thenCallRealMethod();
        try {
            api.getLowerScopedToken(scopes, sharedLinkResource);
        } catch (RuntimeException e) {
            //Ignore it
        }
        verify(api).getAccessToken();
    }

    @Test
    @Category(UnitTest.class)
    public void getLowerScopedTokenForNoteSharedLinkResource() {
        BoxAPIConnection api = mock(BoxAPIConnection.class);

        List<String> scopes = new ArrayList<String>();
        scopes.add("item_preview");
        String noteSharedLinkResource = "https://rungaia.app.box.com/notes/643001418459?s=68c1cewvxas7orqmobakg17o61bfrkcu\n";

        when(api.getTokenURL()).thenReturn("https://api.box.com/oauth2/token");
        when(api.getLowerScopedToken(scopes, noteSharedLinkResource)).thenCallRealMethod();
        try {
            api.getLowerScopedToken(scopes, noteSharedLinkResource);
        } catch (RuntimeException e) {
            //Ignore it
        }
        verify(api).getAccessToken();
    }

    @Test
    @Category(IntegrationTestJWT.class)
    public void getLowerScopedTokenWorks() throws IOException {
        Reader reader = new FileReader("src/test/config/config.json");
        BoxConfig boxConfig = BoxConfig.readFrom(reader);
        IAccessTokenCache accessTokenCache = new InMemoryLRUAccessTokenCache(100);

        BoxDeveloperEditionAPIConnection api =
                BoxDeveloperEditionAPIConnection.getAppEnterpriseConnection(boxConfig, accessTokenCache);

        String originalToken = api.getAccessToken();

        String resource = null;
        List<String> scopes = new ArrayList<String>();
        scopes.add("item_preview");
        scopes.add("item_content_upload");

        ScopedToken newToken = api.getLowerScopedToken(scopes, resource);
        assertThat(newToken, notNullValue());
        assertNotEquals(newToken.getAccessToken(), originalToken);
    }

    @Test
    @Category(UnitTest.class)
    public void addingCustomHeadersWorks() {

        final String targetHeader = "As-User";
        final String targetHeaderValue = "12345";

        BoxAPIConnection api = new BoxAPIConnection("");
        api.setCustomHeader(targetHeader, targetHeaderValue);

        api.setRequestInterceptor(new RequestInterceptor() {
            @Override
            public BoxAPIResponse onRequest(BoxAPIRequest request) {
                boolean isHeaderPresent = false;
                List<BoxAPIRequest.RequestHeader> headers = request.getHeaders();
                for (BoxAPIRequest.RequestHeader header : headers) {
                    if (header.getKey().equals(targetHeader) && header.getValue().equals(targetHeaderValue)) {
                        isHeaderPresent = true;
                    }
                }
                Assert.assertTrue(isHeaderPresent);
                return new BoxJSONResponse() {
                    @Override
                    public String getJSON() {
                        return "{\"type\":\"file\",\"id\":\"98765\"}";
                    }
                };
            }
        });

        BoxFile file = new BoxFile(api, "98765");
        file.getInfo();
    }


    @Test
    @Category(UnitTest.class)
    public void removingCustomHeadersWorks() {

        final String targetHeader = "As-User";
        final String targetHeaderValue = "12345";

        BoxAPIConnection api = new BoxAPIConnection("");
        api.setCustomHeader(targetHeader, targetHeaderValue);
        api.removeCustomHeader(targetHeader);

        api.setRequestInterceptor(new RequestInterceptor() {
            @Override
            public BoxAPIResponse onRequest(BoxAPIRequest request) {
                boolean isHeaderPresent = false;
                List<BoxAPIRequest.RequestHeader> headers = request.getHeaders();
                for (BoxAPIRequest.RequestHeader header : headers) {
                    if (header.getKey().equals(targetHeader) && header.getValue().equals(targetHeaderValue)) {
                        isHeaderPresent = true;
                    }
                }
                Assert.assertFalse(isHeaderPresent);
                return new BoxJSONResponse() {
                    @Override
                    public String getJSON() {
                        return "{\"type\":\"file\",\"id\":\"98765\"}";
                    }
                };
            }
        });

        BoxFile file = new BoxFile(api, "98765");
        file.getInfo();
    }

    @Test
    @Category(UnitTest.class)
    public void asUserAddsAsUserHeader() {

        final String userID = "12345";

        BoxAPIConnection api = new BoxAPIConnection("");
        api.asUser(userID);

        api.setRequestInterceptor(new RequestInterceptor() {
            @Override
            public BoxAPIResponse onRequest(BoxAPIRequest request) {
                boolean isHeaderPresent = false;
                List<BoxAPIRequest.RequestHeader> headers = request.getHeaders();
                for (BoxAPIRequest.RequestHeader header : headers) {
                    if (header.getKey().equals("As-User") && header.getValue().equals(userID)) {
                        isHeaderPresent = true;
                    }
                }
                Assert.assertTrue(isHeaderPresent);
                return new BoxJSONResponse() {
                    @Override
                    public String getJSON() {
                        return "{\"type\":\"file\",\"id\":\"98765\"}";
                    }
                };
            }
        });

        BoxFile file = new BoxFile(api, "98765");
        file.getInfo();
    }

    @Test
    @Category(UnitTest.class)
    public void asSelfRemovesAsUserHeader() {

        final String userID = "12345";

        BoxAPIConnection api = new BoxAPIConnection("");
        api.asUser(userID);
        api.asSelf();

        api.setRequestInterceptor(new RequestInterceptor() {
            @Override
            public BoxAPIResponse onRequest(BoxAPIRequest request) {
                boolean isHeaderPresent = false;
                List<BoxAPIRequest.RequestHeader> headers = request.getHeaders();
                for (BoxAPIRequest.RequestHeader header : headers) {
                    if (header.getKey().equals("As-User") && header.getValue().equals(userID)) {
                        isHeaderPresent = true;
                    }
                }
                Assert.assertFalse(isHeaderPresent);
                return new BoxJSONResponse() {
                    @Override
                    public String getJSON() {
                        return "{\"type\":\"file\",\"id\":\"98765\"}";
                    }
                };
            }
        });

        BoxFile file = new BoxFile(api, "98765");
        file.getInfo();
    }

    @Test
    @Category(UnitTest.class)
    public void suppressNotificationsAddsBoxNotificationsHeader() {

        BoxAPIConnection api = new BoxAPIConnection("");
        api.suppressNotifications();

        api.setRequestInterceptor(new RequestInterceptor() {
            @Override
            public BoxAPIResponse onRequest(BoxAPIRequest request) {
                boolean isHeaderPresent = false;
                List<BoxAPIRequest.RequestHeader> headers = request.getHeaders();
                for (BoxAPIRequest.RequestHeader header : headers) {
                    if (header.getKey().equals("Box-Notifications") && header.getValue().equals("off")) {
                        isHeaderPresent = true;
                    }
                }
                Assert.assertTrue(isHeaderPresent);
                return new BoxJSONResponse() {
                    @Override
                    public String getJSON() {
                        return "{\"type\":\"file\",\"id\":\"98765\"}";
                    }
                };
            }
        });

        BoxFile file = new BoxFile(api, "98765");
        file.getInfo();
    }

    @Test
    @Category(UnitTest.class)
    public void enableNotificationsRemovesBoxNotificationsHeader() {

        BoxAPIConnection api = new BoxAPIConnection("");
        api.suppressNotifications();
        api.enableNotifications();

        api.setRequestInterceptor(new RequestInterceptor() {
            @Override
            public BoxAPIResponse onRequest(BoxAPIRequest request) {
                boolean isHeaderPresent = false;
                List<BoxAPIRequest.RequestHeader> headers = request.getHeaders();
                for (BoxAPIRequest.RequestHeader header : headers) {
                    if (header.getKey().equals("Box-Notifications") && header.getValue().equals("off")) {
                        isHeaderPresent = true;
                    }
                }
                Assert.assertFalse(isHeaderPresent);
                return new BoxJSONResponse() {
                    @Override
                    public String getJSON() {
                        return "{\"type\":\"file\",\"id\":\"98765\"}";
                    }
                };
            }
        });

        BoxFile file = new BoxFile(api, "98765");
        file.getInfo();
    }

    @Test
    @Category(UnitTest.class)
    public void requestToStringWorksInsideRequestInterceptor() {

        BoxAPIConnection api = new BoxAPIConnection("");
        api.setRequestInterceptor(new RequestInterceptor() {
            @Override
            public BoxAPIResponse onRequest(BoxAPIRequest request) {
                String reqString = request.toString();
                Assert.assertTrue(reqString.length() > 0);
                return new BoxJSONResponse() {
                    @Override
                    public String getJSON() {
                        return "{\"type\":\"file\",\"id\":\"98765\"}";
                    }
                };
            }
        });

        BoxFile.Info info = new BoxFile(api, "98765").getInfo();
    }

    @Test
    @Category(UnitTest.class)
    public void shouldUseGlobalMaxRequests() {

        int defaultMaxRequests = BoxGlobalSettings.getMaxRequestAttempts();
        int newMaxRequests = defaultMaxRequests + 5;
        BoxGlobalSettings.setMaxRequestAttempts(newMaxRequests);

        BoxAPIConnection api = new BoxAPIConnection("");
        assertEquals(newMaxRequests, api.getMaxRequestAttempts());

        // Set back the original number to not interfere with other test cases
        BoxGlobalSettings.setMaxRequestAttempts(defaultMaxRequests);
    }

    @Test
    @Category(UnitTest.class)
    public void shouldUseInstanceTimeoutSettings() throws MalformedURLException {

        int instanceConnectTimeout = BoxGlobalSettings.getConnectTimeout() + 1000;
        int instanceReadTimeout = BoxGlobalSettings.getReadTimeout() + 1000;

        BoxAPIConnection api = new BoxAPIConnection("");

        api.setConnectTimeout(instanceConnectTimeout);
        api.setReadTimeout(instanceReadTimeout);

        BoxAPIRequest req = new BoxAPIRequest(api, new URL("https://api.box.com/2.0/users/me"), "GET");

        assertEquals(instanceConnectTimeout, req.getConnectTimeout());
        assertEquals(instanceReadTimeout, req.getReadTimeout());
    }
}
