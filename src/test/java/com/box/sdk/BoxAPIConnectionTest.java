package com.box.sdk;

import static com.github.tomakehurst.wiremock.client.WireMock.aResponse;
import static com.github.tomakehurst.wiremock.client.WireMock.post;
import static com.github.tomakehurst.wiremock.client.WireMock.urlPathEqualTo;
import static com.github.tomakehurst.wiremock.core.WireMockConfiguration.wireMockConfig;
import static java.lang.String.format;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.Matchers.startsWith;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.fail;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.box.sdk.BoxAPIConnection.ResourceLinkType;
import com.eclipsesource.json.JsonObject;
import com.github.tomakehurst.wiremock.client.WireMock;
import com.github.tomakehurst.wiremock.junit.WireMockRule;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;

public class BoxAPIConnectionTest {
    /**
     * Wiremock
     */
    @Rule
    public WireMockRule wireMockRule = new WireMockRule(wireMockConfig().dynamicPort());

    @Test
    public void canRefreshWhenGivenRefreshToken() {
        final String anyClientID = "";
        final String anyClientSecret = "";
        final String anyAccessToken = "";
        final String anyRefreshToken = "";

        BoxAPIConnection api = new BoxAPIConnection(anyClientID, anyClientSecret, anyAccessToken, anyRefreshToken);

        assertThat(api.canRefresh(), is(true));
    }

    @Test
    public void needsRefreshWhenTokenHasExpired() {
        final String anyAccessToken = "";

        BoxAPIConnection api = new BoxAPIConnection(anyAccessToken);
        api.setExpires(-1);

        assertThat(api.needsRefresh(), is(true));
    }

    @Test
    public void doesNotNeedRefreshWhenTokenHasNotExpired() {
        final String anyAccessToken = "";

        BoxAPIConnection api = new BoxAPIConnection(anyAccessToken);
        api.setExpires(Long.MAX_VALUE);

        assertThat(api.needsRefresh(), is(not(true)));
    }

    @Test
    public void needsRefreshWhenExpiresIsZero() {
        final String anyAccessToken = "";

        BoxAPIConnection api = new BoxAPIConnection(anyAccessToken);
        api.setExpires(0);

        assertThat(api.needsRefresh(), is(true));
    }

    @Test
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
    public void restoreConnectionThatDoesNotNeedRefresh() {
        BoxAPIConnection api = new BoxAPIConnection("fake client ID", "fake client secret", "fake access token",
            "fake refresh token");
        api.setExpires(3600000L);
        api.setLastRefresh(System.currentTimeMillis());
        String state = api.save();

        final BoxAPIConnection restoredAPI = BoxAPIConnection.restore("fake client ID", "fake client secret", state);
        restoredAPI.setRequestInterceptor(request -> {
            String tokenURLString = restoredAPI.getTokenURL();
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
        });

        assertFalse(restoredAPI.needsRefresh());
    }

    @Test
    public void getDefaultAuthorizationURLSuccess() throws Exception {
        List<String> scopes = new ArrayList<>();
        scopes.add("root_readwrite");
        scopes.add("manage_groups");

        URL authURL = BoxAPIConnection.getAuthorizationURL("wncmz88sacf5oyaxf502dybcruqbzzy0",
            new URI("http://localhost:3000"), "test", scopes);

        String query = authURL.getQuery();
        assertThat(query, containsString("client_id=wncmz88sacf5oyaxf502dybcruqbzzy0"));
        assertThat(query, containsString("response_type=code"));
        assertThat(query, containsString("redirect_uri=http%3A%2F%2Flocalhost%3A3000"));
        assertThat(query, containsString("state=test"));
        assertThat(query, containsString("scope=root_readwrite+manage_groups"));
    }

    @Test
    public void getAuthorizationURLSuccess() throws Exception {
        List<String> scopes = new ArrayList<>();
        scopes.add("root_readwrite");
        scopes.add("manage_groups");

        BoxAPIConnection api = new BoxAPIConnection("wncmz88sacf5oyaxf502dybcruqbzzy0", "some_secret");
        api.setBaseAuthorizationURL("https://account.my-box.com/api");

        URL authURL = api.getAuthorizationURL(new URI("http://localhost:3000"), "test", scopes);

        assertThat(authURL.toString(), startsWith("https://account.my-box.com/api/oauth2/authorize"));

        String query = authURL.getQuery();
        assertThat(query, containsString("client_id=wncmz88sacf5oyaxf502dybcruqbzzy0"));
        assertThat(query, containsString("response_type=code"));
        assertThat(query, containsString("redirect_uri=http%3A%2F%2Flocalhost%3A3000"));
        assertThat(query, containsString("state=test"));
        assertThat(query, containsString("scope=root_readwrite+manage_groups"));
    }

    @Test
    public void revokeTokenCallsCorrectEndpoint() {

        String accessToken = "fakeAccessToken";
        String clientID = "fakeID";
        String clientSecret = "fakeSecret";

        BoxAPIConnection api = new BoxAPIConnection(clientID, clientSecret, accessToken, "");
        api.setRevokeURL(format("http://localhost:%d/oauth2/revoke", wireMockRule.port()));

        wireMockRule.stubFor(post(urlPathEqualTo("/oauth2/revoke"))
            .withRequestBody(WireMock.equalTo("token=fakeAccessToken&client_id=fakeID&client_secret=fakeSecret"))
            .willReturn(aResponse()
                .withHeader("Content-Type", "application/json")
                .withBody("{}")));

        api.revokeToken();
    }

    @Test
    public void getLowerScopedTokenRefreshesTheTokenIfNeededbyCallingGetAccessToken() {
        BoxAPIConnection api = mock(BoxAPIConnection.class);

        List<String> scopes = new ArrayList<>();
        scopes.add("item_preview");

        when(api.getTokenURL()).thenReturn("https://api.box.com/oauth2/token");
        when(api.getLowerScopedToken(scopes, null)).thenCallRealMethod();
        try {
            api.getLowerScopedToken(scopes, null);
        } catch (RuntimeException e) {
            //Ignore it
        }
        verify(api).getAccessToken();
    }

    @Test
    public void checkAllResourceLinkTypes() {
        this.getResourceLinkTypeFromURLString(
            "https://api.box.com/2.0/files/1234567890", ResourceLinkType.APIEndpoint);
        this.getResourceLinkTypeFromURLString(
            "https://example.box.com/s/qwertyuiop1234567890asdfghjkl", ResourceLinkType.SharedLink);
        this.getResourceLinkTypeFromURLString(
            "https://example.app.box.com/notes/09876321?s=zxcvm123458asdf", ResourceLinkType.SharedLink);
        this.getResourceLinkTypeFromURLString(
            null, ResourceLinkType.Unknown);
        this.getResourceLinkTypeFromURLString(
            "", ResourceLinkType.Unknown);
        this.getResourceLinkTypeFromURLString(
            "qwertyuiopasdfghjklzxcvbnm1234567890", ResourceLinkType.Unknown);
    }

    private void getResourceLinkTypeFromURLString(String resource, ResourceLinkType resourceLinkType) {
        BoxAPIConnection api = mock(BoxAPIConnection.class);
        when(api.determineResourceLinkType(resource))
            .thenCallRealMethod();
        ResourceLinkType actualResourceLinkType = api.determineResourceLinkType(resource);
        assertEquals(actualResourceLinkType, resourceLinkType);
    }

    @Test
    public void addingCustomHeadersWorks() {

        final String targetHeader = "As-User";
        final String targetHeaderValue = "12345";

        BoxAPIConnection api = new BoxAPIConnection("");
        api.setCustomHeader(targetHeader, targetHeaderValue);

        api.setRequestInterceptor(request -> {
            boolean isHeaderPresent = false;
            List<BoxAPIRequest.RequestHeader> headers = request.getHeaders();
            for (BoxAPIRequest.RequestHeader header : headers) {
                if (header.getKey().equals(targetHeader) && header.getValue().equals(targetHeaderValue)) {
                    isHeaderPresent = true;
                    break;
                }
            }
            Assert.assertTrue(isHeaderPresent);
            return new BoxJSONResponse() {
                @Override
                public String getJSON() {
                    return "{\"type\":\"file\",\"id\":\"98765\"}";
                }
            };
        });

        BoxFile file = new BoxFile(api, "98765");
        file.getInfo();
    }


    @Test
    public void removingCustomHeadersWorks() {

        final String targetHeader = "As-User";
        final String targetHeaderValue = "12345";

        BoxAPIConnection api = new BoxAPIConnection("");
        api.setCustomHeader(targetHeader, targetHeaderValue);
        api.removeCustomHeader(targetHeader);

        api.setRequestInterceptor(request -> {
            boolean isHeaderPresent = false;
            List<BoxAPIRequest.RequestHeader> headers = request.getHeaders();
            for (BoxAPIRequest.RequestHeader header : headers) {
                if (header.getKey().equals(targetHeader) && header.getValue().equals(targetHeaderValue)) {
                    isHeaderPresent = true;
                    break;
                }
            }
            assertFalse(isHeaderPresent);
            return new BoxJSONResponse() {
                @Override
                public String getJSON() {
                    return "{\"type\":\"file\",\"id\":\"98765\"}";
                }
            };
        });

        BoxFile file = new BoxFile(api, "98765");
        file.getInfo();
    }

    @Test
    public void asUserAddsAsUserHeader() {

        final String userID = "12345";

        BoxAPIConnection api = new BoxAPIConnection("");
        api.asUser(userID);

        api.setRequestInterceptor(request -> {
            boolean isHeaderPresent = false;
            List<BoxAPIRequest.RequestHeader> headers = request.getHeaders();
            for (BoxAPIRequest.RequestHeader header : headers) {
                if (header.getKey().equals("As-User") && header.getValue().equals(userID)) {
                    isHeaderPresent = true;
                    break;
                }
            }
            Assert.assertTrue(isHeaderPresent);
            return new BoxJSONResponse() {
                @Override
                public String getJSON() {
                    return "{\"type\":\"file\",\"id\":\"98765\"}";
                }
            };
        });

        BoxFile file = new BoxFile(api, "98765");
        file.getInfo();
    }

    @Test
    public void asSelfRemovesAsUserHeader() {

        final String userID = "12345";

        BoxAPIConnection api = new BoxAPIConnection("");
        api.asUser(userID);
        api.asSelf();

        api.setRequestInterceptor(request -> {
            boolean isHeaderPresent = false;
            List<BoxAPIRequest.RequestHeader> headers = request.getHeaders();
            for (BoxAPIRequest.RequestHeader header : headers) {
                if (header.getKey().equals("As-User") && header.getValue().equals(userID)) {
                    isHeaderPresent = true;
                    break;
                }
            }
            assertFalse(isHeaderPresent);
            return new BoxJSONResponse() {
                @Override
                public String getJSON() {
                    return "{\"type\":\"file\",\"id\":\"98765\"}";
                }
            };
        });

        BoxFile file = new BoxFile(api, "98765");
        file.getInfo();
    }

    @Test
    public void suppressNotificationsAddsBoxNotificationsHeader() {

        BoxAPIConnection api = new BoxAPIConnection("");
        api.suppressNotifications();

        api.setRequestInterceptor(request -> {
            boolean isHeaderPresent = false;
            List<BoxAPIRequest.RequestHeader> headers = request.getHeaders();
            for (BoxAPIRequest.RequestHeader header : headers) {
                if (header.getKey().equals("Box-Notifications") && header.getValue().equals("off")) {
                    isHeaderPresent = true;
                    break;
                }
            }
            Assert.assertTrue(isHeaderPresent);
            return new BoxJSONResponse() {
                @Override
                public String getJSON() {
                    return "{\"type\":\"file\",\"id\":\"98765\"}";
                }
            };
        });

        BoxFile file = new BoxFile(api, "98765");
        file.getInfo();
    }

    @Test
    public void enableNotificationsRemovesBoxNotificationsHeader() {

        BoxAPIConnection api = new BoxAPIConnection("");
        api.suppressNotifications();
        api.enableNotifications();

        api.setRequestInterceptor(request -> {
            boolean isHeaderPresent = false;
            List<BoxAPIRequest.RequestHeader> headers = request.getHeaders();
            for (BoxAPIRequest.RequestHeader header : headers) {
                if (header.getKey().equals("Box-Notifications") && header.getValue().equals("off")) {
                    isHeaderPresent = true;
                    break;
                }
            }
            assertFalse(isHeaderPresent);
            return new BoxJSONResponse() {
                @Override
                public String getJSON() {
                    return "{\"type\":\"file\",\"id\":\"98765\"}";
                }
            };
        });

        BoxFile file = new BoxFile(api, "98765");
        file.getInfo();
    }

    @Test
    public void requestToStringWorksInsideRequestInterceptor() {

        BoxAPIConnection api = new BoxAPIConnection("");
        api.setRequestInterceptor(request -> {
            String reqString = request.toString();
            Assert.assertTrue(reqString.length() > 0);
            return new BoxJSONResponse() {
                @Override
                public String getJSON() {
                    return "{\"type\":\"file\",\"id\":\"98765\"}";
                }
            };
        });

        new BoxFile(api, "98765").getInfo();
    }

    @Test
    public void shouldUseGlobalMaxRetries() {

        int defaultMaxRetries = BoxGlobalSettings.getMaxRetryAttempts();
        int newMaxRetries = defaultMaxRetries + 5;
        BoxGlobalSettings.setMaxRetryAttempts(newMaxRetries);

        BoxAPIConnection api = new BoxAPIConnection("");
        assertEquals(newMaxRetries, api.getMaxRetryAttempts());

        // Set back the original number to not interfere with other test cases
        BoxGlobalSettings.setMaxRetryAttempts(defaultMaxRetries);
    }

    @Test
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

    @Test
    public void allowsToSaveAndRestoreApplicationConnection() throws URISyntaxException {
        // given
        String accessToken = "access_token";
        String clientId = "some_client_id";
        String clientSecret = "some_client_secret";
        String refreshToken = "some_refresh_token";
        BoxAPIConnection api = new BoxAPIConnection(clientId, clientSecret, accessToken, refreshToken);
        api.setRequestInterceptor(
            request -> new BoxAPIConnectionTest.AuthenticationResponse(accessToken, refreshToken, "4245")
        );

        // when
        api.refresh();
        String savedConnection = api.save();
        BoxAPIConnection restoredApi = BoxAPIConnection.restore(clientId, clientSecret, savedConnection);

        // then
        assertThat(api.getAccessToken(), is(restoredApi.getAccessToken()));
        assertThat(api.getLastRefresh(), is(restoredApi.getLastRefresh()));
        assertThat(api.getExpires(), is(restoredApi.getExpires()));
        assertThat(api.getUserAgent(), is(restoredApi.getUserAgent()));
        assertThat(api.getTokenURL(), is(restoredApi.getTokenURL()));
        assertThat(api.getRevokeURL(), is(restoredApi.getRevokeURL()));
        assertThat(
            api.getAuthorizationURL(new URI("https://redirect.me"), "test", new ArrayList<>()),
            is(restoredApi.getAuthorizationURL(new URI("https://redirect.me"), "test", new ArrayList<>()))
        );
        assertThat(api.getBaseURL(), is(restoredApi.getBaseURL()));
        assertThat(api.getBaseAppUrl(), is(restoredApi.getBaseAppUrl()));
        assertThat(api.getBaseUploadURL(), is(restoredApi.getBaseUploadURL()));
        assertThat(api.getAutoRefresh(), is(restoredApi.getAutoRefresh()));
        assertThat(api.getMaxRetryAttempts(), is(restoredApi.getMaxRetryAttempts()));
    }

    @Test
    public void restoresProperUrlsFromOldVersions() {
        // given
        String accessToken = "access_token";
        String clientId = "some_client_id";
        String clientSecret = "some_client_secret";
        String refreshToken = "some_refresh_token";
        BoxAPIConnection api = new BoxAPIConnection(clientId, clientSecret, accessToken, refreshToken);
        api.setBaseURL("https://api.box.com/2.0/");
        api.setBaseUploadURL("https://upload.box.com/api/2.0/");
        String savedConnection = api.save();

        // when
        BoxAPIConnection restoredApi = BoxAPIConnection.restore(clientId, clientSecret, savedConnection);

        // then
        assertThat("https://api.box.com/2.0/", is(restoredApi.getBaseURL()));
        assertThat("https://upload.box.com/api/2.0/", is(restoredApi.getBaseUploadURL()));
    }

    @Test
    public void restoresProperUrlsWhenNotSet() {
        // given
        String accessToken = "access_token";
        String clientId = "some_client_id";
        String clientSecret = "some_client_secret";
        String refreshToken = "some_refresh_token";
        BoxAPIConnection api = new BoxAPIConnection(clientId, clientSecret, accessToken, refreshToken);
        api.setBaseURL("https://api.box.com/2.0/");
        String savedConnection = api.save();

        // when
        BoxAPIConnection restoredApi = BoxAPIConnection.restore(clientId, clientSecret, savedConnection);

        // then
        assertThat("https://api.box.com/2.0/", is(restoredApi.getBaseURL()));
        assertThat("https://upload.box.com/api/2.0/", is(restoredApi.getBaseUploadURL()));
    }

    @Test
    public void restoresProperUrlsWhenDeprecatedUrlsAreSet() {
        // given
        String clientId = "some_client_id";
        String clientSecret = "some_client_secret";

        String savedConnection = "{"
            + "\"accessToken\":\"access_token\","
            + "\"refreshToken\":\"some_refresh_token\","
            + "\"lastRefresh\":0,"
            + "\"expires\":0,"
            + "\"userAgent\":\"Box Java SDK v3.8.0 (Java 1.8.0_345)\","
            + "\"tokenURL\":\"https://api.box.com/token\","
            + "\"revokeURL\":\"https://api.box.com/revoke\","
            + "\"baseURL\":\"https://api.box.com/\","
            + "\"baseUploadURL\":\"https://upload.box.com/api/\","
            + "\"authorizationURL\":\"https://account.box.com/api/\","
            + "\"autoRefresh\":true,\"maxRetryAttempts\":5"
            + "}";
        // when
        BoxAPIConnection restoredApi = BoxAPIConnection.restore(clientId, clientSecret, savedConnection);

        // then
        assertThat("https://api.box.com/token", is(restoredApi.getTokenURL()));
        assertThat("https://api.box.com/revoke", is(restoredApi.getRevokeURL()));
    }

    @Test
    public void restoresProperUrlsWhenSomeAreMissing() {
        // given
        String accessToken = "access_token";
        String clientId = "some_client_id";
        String clientSecret = "some_client_secret";
        String refreshToken = "some_refresh_token";

        String savedConnection = "{"
            + "\"accessToken\":\"access_token\","
            + "\"refreshToken\":\"some_refresh_token\","
            + "\"lastRefresh\":0,"
            + "\"expires\":0,"
            + "\"userAgent\":\"Box Java SDK v3.8.0 (Java 1.8.0_345)\","
            + "\"baseURL\":\"https://api.box.com/\","
            + "\"authorizationURL\":\"https://account.box.com/api/\","
            + "\"autoRefresh\":true,\"maxRetryAttempts\":5"
            + "}";
        // when
        BoxAPIConnection restoredApi = BoxAPIConnection.restore(clientId, clientSecret, savedConnection);

        // then
        assertThat("https://api.box.com/2.0/", is(restoredApi.getBaseURL()));
        assertThat("https://upload.box.com/api/2.0/", is(restoredApi.getBaseUploadURL()));
    }

    @Test
    public void successfullyRestoresConnectionWithDeprecatedSettings() {
        String restoreState = TestUtils.getFixture("BoxAPIConnection/State");
        String restoreStateDeprecated = TestUtils.getFixture("BoxAPIConnection/StateDeprecated");

        BoxAPIConnection api =
            BoxAPIConnection.restore("some_client_id", "some_client_secret", restoreState);
        String savedStateAPI = api.save();

        BoxAPIConnection deprecatedAPI =
            BoxAPIConnection.restore("some_client_id", "some_client_secret", restoreStateDeprecated);
        String savedStateAPIDeprecated = deprecatedAPI.save();

        assertEquals(api.getMaxRetryAttempts(), deprecatedAPI.getMaxRetryAttempts());
        assertEquals(savedStateAPI, savedStateAPIDeprecated);
    }

    @Test
    public void setBaseUrls() {
        BoxAPIConnection api = new BoxAPIConnection(
            "some_client_id", "some_client_secret", "some_access_token", "some_refresh_token"
        );
        String baseURL = "https://my-base.url";
        String baseUploadURL = "https://my-base-upload.url";
        String baseAppURL = "https://my-base-app.url";
        api.setBaseURL(baseURL);
        api.setBaseUploadURL(baseUploadURL);
        api.setBaseAppUrl(baseAppURL);

        assertThat(api.getBaseURL(), is(baseURL + "/2.0/"));
        assertThat(api.getBaseUploadURL(), is(baseUploadURL + "/2.0/"));
        assertThat(api.getBaseAppUrl(), is(baseAppURL));
        assertThat(api.getRevokeURL(), is(baseURL + "/oauth2/revoke"));
        assertThat(api.getTokenURL(), is(baseURL + "/oauth2/token"));
    }

    @Test
    public void canOverrideTokenUrl() {
        BoxAPIConnection api = new BoxAPIConnection(
            "some_client_id", "some_client_secret", "some_access_token", "some_refresh_token"
        );

        api.setBaseURL("https://my-base.url");
        String tokenURL = "https://my-token.url";
        api.setTokenURL(tokenURL);

        assertThat(api.getTokenURL(), is("https://my-token.url"));
    }

    @Test
    public void canOverrideRevokeUrl() {
        BoxAPIConnection api = new BoxAPIConnection(
            "some_client_id", "some_client_secret", "some_access_token", "some_refresh_token"
        );

        api.setBaseURL("https://my-base.url");
        String myRevokeURL = "https://my-revoke.url";
        api.setRevokeURL(myRevokeURL);

        assertThat(api.getRevokeURL(), is("https://my-revoke.url"));
    }

    @Test
    public void allowsToSaveAndRestoreApplicationConnectionWithBaseUrlSet() throws URISyntaxException {
        // given
        String refreshToken = "some_refresh_token";
        BoxAPIConnection api = new BoxAPIConnection(
            "some_client_id", "some_client_secret", "access_token", refreshToken
        );
        api.setBaseURL("https://my-base.url");
        api.setBaseUploadURL("https://my-base-upload.url");
        api.setBaseAuthorizationURL("https://my-authorization.url");
        api.setRevokeURL("https://my-revoke.url");
        api.setTokenURL("https://my-token.url");
        api.setRequestInterceptor(
            request -> new BoxAPIConnectionTest.AuthenticationResponse("access_token", refreshToken, "4245")
        );

        // when
        api.refresh();
        String savedConnection = api.save();
        BoxAPIConnection restoredApi =
            BoxAPIConnection.restore("some_client_id", "some_client_secret", savedConnection);

        // then
        assertThat(api.getBaseURL(), is(restoredApi.getBaseURL()));
        assertThat(api.getBaseAppUrl(), is(restoredApi.getBaseAppUrl()));
        assertThat(api.getBaseUploadURL(), is(restoredApi.getBaseUploadURL()));
        assertThat(api.getRevokeURL(), is(restoredApi.getRevokeURL()));
        assertThat(api.getTokenURL(), is(restoredApi.getTokenURL()));
        assertThat(
            api.getAuthorizationURL(new URI("https://my.redirect"), "test", new ArrayList<>()),
            is(restoredApi.getAuthorizationURL(new URI("https://my.redirect"), "test", new ArrayList<>()))
        );
    }

    @Test
    public void usesCorrectTokenUrlToAuthenticateWhenBaseUrlIsSet() {
        String code = "fakeCode";
        String clientID = "fakeID";
        String clientSecret = "fakeSecret";

        BoxAPIConnection api = new BoxAPIConnection(clientID, clientSecret);
        api.setAutoRefresh(false);
        api.setBaseURL(format("http://localhost:%d", wireMockRule.port()));
        mockAndAssertAuthentication(clientID, clientSecret, code);

        api.authenticate(code);

        assertThat(api.getAccessToken(), is("access-token"));
        assertThat(api.getRefreshToken(), is("refresh-token"));
    }

    @Test
    public void usesCorrectTokenToAuthenticateUrlWhenTokenUrlIsSet() {
        String code = "fakeCode";
        String clientID = "fakeID";
        String clientSecret = "fakeSecret";

        BoxAPIConnection api = new BoxAPIConnection(clientID, clientSecret);
        api.setAutoRefresh(false);
        api.setTokenURL(format("http://localhost:%d/oauth2/token", wireMockRule.port()));
        mockAndAssertAuthentication(clientID, clientSecret, code);

        api.authenticate(code);

        assertThat(api.getAccessToken(), is("access-token"));
        assertThat(api.getRefreshToken(), is("refresh-token"));
    }

    @Test
    public void usesTokenUrlOverBaseUrlToAuthenticate() {
        String code = "fakeCode";
        String clientID = "fakeID";
        String clientSecret = "fakeSecret";

        BoxAPIConnection api = new BoxAPIConnection(clientID, clientSecret);
        api.setAutoRefresh(false);
        api.setTokenURL(format("http://localhost:%d/oauth2/token", wireMockRule.port()));
        api.setBaseURL("not_an_url");
        mockAndAssertAuthentication(clientID, clientSecret, code);

        api.authenticate(code);

        assertThat(api.getAccessToken(), is("access-token"));
        assertThat(api.getRefreshToken(), is("refresh-token"));
    }

    @Test
    public void usesCorrectTokenUrlToRevokeWhenBaseUrlIsSet() {
        String token = "fakeToken";
        String clientID = "fakeID";
        String clientSecret = "fakeSecret";

        BoxAPIConnection api = new BoxAPIConnection(clientID, clientSecret, token, null);
        api.setBaseURL(format("http://localhost:%d", wireMockRule.port()));
        mockAndAssertRevoke(token, clientID, clientSecret);

        api.revokeToken();
    }

    @Test
    public void usesCorrectTokenUrlToRevokeWhenRevokeUrlIsSet() {
        String token = "fakeToken";
        String clientID = "fakeID";
        String clientSecret = "fakeSecret";

        BoxAPIConnection api = new BoxAPIConnection(clientID, clientSecret, token, null);
        api.setRevokeURL(format("http://localhost:%d/oauth2/revoke", wireMockRule.port()));
        mockAndAssertRevoke(token, clientID, clientSecret);

        api.revokeToken();
    }

    @Test
    public void usesRevokeUrlOverBaseUrlToRevoke() {
        String token = "fakeToken";
        String clientID = "fakeID";
        String clientSecret = "fakeSecret";

        BoxAPIConnection api = new BoxAPIConnection(clientID, clientSecret, token, null);
        api.setRevokeURL(format("http://localhost:%d/oauth2/revoke", wireMockRule.port()));
        api.setBaseURL("not_an_url");
        mockAndAssertRevoke(token, clientID, clientSecret);

        api.revokeToken();
    }

    private void mockAndAssertRevoke(String token, String clientID, String clientSecret) {
        wireMockRule.stubFor(post(urlPathEqualTo("/oauth2/revoke"))
            .withRequestBody(WireMock.equalTo(
                format(
                    "token=%s&client_id=%s&client_secret=%s", token, clientID, clientSecret
                )
            ))
            .willReturn(aResponse()
                .withHeader("Content-Type", "application/json")
                .withBody(
                    "{\"refresh_token\":\"refresh-token\", \"access_token\":\"access-token\", \"expires_in\": 1}"
                )));
    }

    private void mockAndAssertAuthentication(String clientId, String clientSecret, String code) {
        wireMockRule.stubFor(post(urlPathEqualTo("/oauth2/token"))
            .withRequestBody(WireMock.equalTo(
                format(
                    "grant_type=authorization_code&code=%s&client_id=%s&client_secret=%s", code, clientId, clientSecret
                )
            ))
            .willReturn(aResponse()
                .withHeader("Content-Type", "application/json")
                .withBody(
                    "{\"refresh_token\":\"refresh-token\", \"access_token\":\"access-token\", \"expires_in\": 1}"
                )));
    }

    private static final class AuthenticationResponse extends BoxJSONResponse {
        private final String accessToken;
        private final String refreshToken;
        private final String expiresIn;

        private AuthenticationResponse(String accessToken, String refreshToken, String expiresIn) {
            this.accessToken = accessToken;
            this.refreshToken = refreshToken;
            this.expiresIn = expiresIn;
        }


        @Override
        public String getJSON() {
            return "{\n"
                + "    \"access_token\": \"" + accessToken + "\",\n"
                + "    \"refresh_token\": \"" + refreshToken + "\",\n"
                + "    \"expires_in\": " + expiresIn + ",\n"
                + "    \"restricted_to\": [],\n"
                + "    \"token_type\": \"bearer\"\n"
                + "}";
        }
    }
}
