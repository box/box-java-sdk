package com.box.sdk;

import static com.box.sdk.BoxAPIConnection.DEFAULT_HOSTNAME_VERIFIER;
import static com.box.sdk.BoxAPIConnection.DEFAULT_TRUST_MANAGER;
import static com.box.sdk.http.ContentType.APPLICATION_JSON;
import static com.github.tomakehurst.wiremock.client.WireMock.aResponse;
import static com.github.tomakehurst.wiremock.client.WireMock.equalTo;
import static com.github.tomakehurst.wiremock.client.WireMock.matching;
import static com.github.tomakehurst.wiremock.client.WireMock.post;
import static com.github.tomakehurst.wiremock.client.WireMock.urlPathEqualTo;
import static com.github.tomakehurst.wiremock.core.WireMockConfiguration.wireMockConfig;
import static com.github.tomakehurst.wiremock.stubbing.Scenario.STARTED;
import static java.lang.String.format;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.Matchers.startsWith;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.fail;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.box.sdk.BoxAPIConnection.ResourceLinkType;
import com.eclipsesource.json.JsonObject;
import com.github.tomakehurst.wiremock.WireMockServer;
import com.github.tomakehurst.wiremock.junit.WireMockRule;
import java.net.InetSocketAddress;
import java.net.MalformedURLException;
import java.net.Proxy;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import javax.net.ssl.SSLHandshakeException;
import javax.net.ssl.SSLPeerUnverifiedException;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;

public class BoxAPIConnectionTest {
  @Rule
  // we do not use HTTPS mock as wiremock ha trouble proxying HTTPS traffic
  public WireMockRule wireMockRule = new WireMockRule(wireMockConfig().dynamicPort());

  @Rule
  public WireMockRule wireMockHttpsRule =
      new WireMockRule(wireMockConfig().dynamicHttpsPort().httpDisabled(true));

  private final String accessToken = "fakeToken";
  private final String refreshToken = "fake refresh token";
  private final String clientId = "fakeID";
  private final String clientSecret = "fakeSecret";

  @Test
  public void canRefreshWhenGivenRefreshToken() {
    final String anyClientID = "";
    final String anyClientSecret = "";
    final String anyAccessToken = "";
    final String anyRefreshToken = "";

    BoxAPIConnection api =
        new BoxAPIConnectionForTests(anyClientID, anyClientSecret, anyAccessToken, anyRefreshToken);

    assertThat(api.canRefresh(), is(true));
  }

  @Test
  public void needsRefreshWhenTokenHasExpired() {
    final String anyAccessToken = "";

    BoxAPIConnection api = new BoxAPIConnectionForTests(anyAccessToken);
    api.setExpires(-1);

    assertThat(api.needsRefresh(), is(true));
  }

  @Test
  public void doesNotNeedRefreshWhenTokenHasNotExpired() {
    final String anyAccessToken = "";

    BoxAPIConnection api = new BoxAPIConnectionForTests(anyAccessToken);
    api.setExpires(Long.MAX_VALUE);

    assertThat(api.needsRefresh(), is(not(true)));
  }

  @Test
  public void needsRefreshWhenExpiresIsZero() {
    final String anyAccessToken = "";

    BoxAPIConnection api = new BoxAPIConnectionForTests(anyAccessToken);
    api.setExpires(0);

    assertThat(api.needsRefresh(), is(true));
  }

  @Test
  public void interceptorReceivesSentRequest() throws MalformedURLException {
    BoxAPIConnection api = new BoxAPIConnectionForTests("");

    BoxAPIResponse fakeResponse = new BoxAPIResponse();

    RequestInterceptor mockInterceptor = mock(RequestInterceptor.class);
    when(mockInterceptor.onRequest(any(BoxAPIRequest.class))).thenReturn(fakeResponse);
    api.setRequestInterceptor(mockInterceptor);

    BoxAPIRequest request = new BoxAPIRequest(api, new URL("http://anyurl.com"), "GET");

    try (BoxAPIResponse response = request.send()) {
      assertThat(response, is(equalTo(fakeResponse)));
    }
  }

  @Test
  public void restoreConnectionThatDoesNotNeedRefresh() {
    BoxAPIConnection api =
        new BoxAPIConnectionForTests(accessToken, clientSecret, accessToken, refreshToken);
    api.setExpires(3600000L);
    api.setLastRefresh(System.currentTimeMillis());
    String state = api.save();

    final BoxAPIConnection restoredAPI =
        BoxAPIConnection.restore("fake client ID", "fake client secret", state);
    restoredAPI.setRequestInterceptor(
        request -> {
          String tokenURLString = restoredAPI.getTokenURL();
          String requestURLString = request.getUrl().toString();
          if (requestURLString.contains(tokenURLString)) {
            fail("The connection was refreshed.");
          }

          if (requestURLString.contains("folders")) {
            return new BoxJSONResponse() {
              @Override
              public String getJSON() {
                JsonObject responseJSON =
                    new JsonObject().add("id", "fake ID").add("type", "folder");
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

    URL authURL =
        BoxAPIConnection.getAuthorizationURL(
            clientId, new URI("http://localhost:3000"), "test", scopes);

    String query = authURL.getQuery();
    assertThat(query, containsString("client_id=" + clientId));
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

    BoxAPIConnection api = new BoxAPIConnectionForTests(clientId, clientSecret);
    api.setBaseAuthorizationURL("https://account.my-box.com/api");

    URL authURL = api.getAuthorizationURL(new URI("http://localhost:3000"), "test", scopes);

    assertThat(authURL.toString(), startsWith("https://account.my-box.com/api/oauth2/authorize?"));

    String query = authURL.getQuery();
    assertThat(query, containsString("client_id=" + clientId));
    assertThat(query, containsString("response_type=code"));
    assertThat(query, containsString("redirect_uri=http%3A%2F%2Flocalhost%3A3000"));
    assertThat(query, containsString("state=test"));
    assertThat(query, containsString("scope=root_readwrite+manage_groups"));
  }

  @Test
  public void revokeTokenCallsCorrectEndpoint() {
    BoxAPIConnection api =
        new BoxAPIConnectionForTests(clientId, clientSecret, accessToken, refreshToken);
    api.configureSslCertificatesValidation(
        new TrustAllTrustManager(), new AcceptAllHostsVerifier());
    api.setBaseURL(baseHttpsUrl());
    api.setBaseUploadURL(baseHttpsUrl());
    api.setBaseAuthorizationURL(baseHttpsUrl());

    wireMockHttpsRule.stubFor(
        post(urlPathEqualTo("/oauth2/revoke"))
            .withRequestBody(
                equalTo(
                    String.format(
                        "token=%s&client_id=fakeID&client_secret=fakeSecret", accessToken)))
            .willReturn(aResponse().withHeader("Content-Type", APPLICATION_JSON).withBody("{}")));

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
      // Ignore it
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
        "https://example.app.box.com/notes/09876321?s=zxcvm123458asdf",
        ResourceLinkType.SharedLink);
    this.getResourceLinkTypeFromURLString(null, ResourceLinkType.Unknown);
    this.getResourceLinkTypeFromURLString("", ResourceLinkType.Unknown);
    this.getResourceLinkTypeFromURLString(
        "qwertyuiopasdfghjklzxcvbnm1234567890", ResourceLinkType.Unknown);
  }

  private void getResourceLinkTypeFromURLString(
      String resource, ResourceLinkType resourceLinkType) {
    BoxAPIConnection api = mock(BoxAPIConnection.class);
    when(api.determineResourceLinkType(resource)).thenCallRealMethod();
    ResourceLinkType actualResourceLinkType = api.determineResourceLinkType(resource);
    assertEquals(actualResourceLinkType, resourceLinkType);
  }

  @Test
  public void addingCustomHeadersWorks() {

    final String targetHeader = "As-User";
    final String targetHeaderValue = "12345";

    BoxAPIConnection api = new BoxAPIConnectionForTests("");
    api.setCustomHeader(targetHeader, targetHeaderValue);

    api.setRequestInterceptor(
        request -> {
          boolean isHeaderPresent = false;
          List<BoxAPIRequest.RequestHeader> headers = request.getHeaders();
          for (BoxAPIRequest.RequestHeader header : headers) {
            if (header.getKey().equals(targetHeader)
                && header.getValue().equals(targetHeaderValue)) {
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

    BoxAPIConnection api = new BoxAPIConnectionForTests("");
    api.setCustomHeader(targetHeader, targetHeaderValue);
    api.removeCustomHeader(targetHeader);

    api.setRequestInterceptor(
        request -> {
          boolean isHeaderPresent = false;
          List<BoxAPIRequest.RequestHeader> headers = request.getHeaders();
          for (BoxAPIRequest.RequestHeader header : headers) {
            if (header.getKey().equals(targetHeader)
                && header.getValue().equals(targetHeaderValue)) {
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

    BoxAPIConnection api = new BoxAPIConnectionForTests("");
    api.asUser(userID);

    api.setRequestInterceptor(
        request -> {
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

    BoxAPIConnection api = new BoxAPIConnectionForTests("");
    api.asUser(userID);
    api.asSelf();

    api.setRequestInterceptor(
        request -> {
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
    BoxAPIConnection api = new BoxAPIConnectionForTests("");
    api.suppressNotifications();

    api.setRequestInterceptor(
        request -> {
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
    BoxAPIConnection api = new BoxAPIConnectionForTests("");
    api.suppressNotifications();
    api.enableNotifications();

    api.setRequestInterceptor(
        request -> {
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
    BoxAPIConnection api = new BoxAPIConnectionForTests("");
    api.setRequestInterceptor(
        request -> {
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

    BoxAPIConnection api = new BoxAPIConnectionForTests("");
    assertEquals(newMaxRetries, api.getMaxRetryAttempts());

    // Set back the original number to not interfere with other test cases
    BoxGlobalSettings.setMaxRetryAttempts(defaultMaxRetries);
  }

  @Test
  public void shouldUseInstanceTimeoutSettings() throws MalformedURLException {
    int instanceConnectTimeout = BoxGlobalSettings.getConnectTimeout() + 1000;
    int instanceReadTimeout = BoxGlobalSettings.getReadTimeout() + 1000;

    BoxAPIConnection api = new BoxAPIConnectionForTests("");

    api.setConnectTimeout(instanceConnectTimeout);
    api.setReadTimeout(instanceReadTimeout);

    BoxAPIRequest req = new BoxAPIRequest(api, new URL("https://api.box.com/2.0/users/me"), "GET");

    assertEquals(instanceConnectTimeout, req.getConnectTimeout());
    assertEquals(instanceReadTimeout, req.getReadTimeout());
  }

  @Test
  public void allowsToSaveAndRestoreApplicationConnection() throws URISyntaxException {
    // given
    BoxAPIConnection api =
        new BoxAPIConnectionForTests(clientId, clientSecret, accessToken, refreshToken);
    api.setRequestInterceptor(
        request ->
            new BoxAPIConnectionTest.AuthenticationResponse(accessToken, refreshToken, "4245"));

    // when
    api.refresh();
    String savedConnection = api.save();
    BoxAPIConnection restoredApi =
        BoxAPIConnection.restore(clientId, clientSecret, savedConnection);

    // then
    assertThat(api.getAccessToken(), is(restoredApi.getAccessToken()));
    assertThat(api.getLastRefresh(), is(restoredApi.getLastRefresh()));
    assertThat(api.getExpires(), is(restoredApi.getExpires()));
    assertThat(api.getUserAgent(), is(restoredApi.getUserAgent()));
    assertThat(api.getTokenURL(), is(restoredApi.getTokenURL()));
    assertThat(api.getRevokeURL(), is(restoredApi.getRevokeURL()));
    assertThat(
        api.getAuthorizationURL(new URI("https://redirect.me"), "test", new ArrayList<>()),
        is(
            restoredApi.getAuthorizationURL(
                new URI("https://redirect.me"), "test", new ArrayList<>())));
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
    BoxAPIConnection restoredApi =
        BoxAPIConnection.restore(clientId, clientSecret, savedConnection);

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
    BoxAPIConnection restoredApi =
        BoxAPIConnection.restore(clientId, clientSecret, savedConnection);

    // then
    assertThat("https://api.box.com/2.0/", is(restoredApi.getBaseURL()));
    assertThat("https://upload.box.com/api/2.0/", is(restoredApi.getBaseUploadURL()));
  }

  @Test
  public void restoresWithEmptyRefreshToken() {
    // given
    String clientId = "some_client_id";
    String clientSecret = "some_client_secret";
    String state =
        "{\"accessToken\":\"some-access-token\","
            + "\"refreshToken\":null,"
            + "\"lastRefresh\":1672759898468,"
            + "\"expires\":3829000,"
            + "\"userAgent\":\"Box Java SDK v3.8.1 (Java 17.0.4)\","
            + "\"tokenURL\":null,"
            + "\"revokeURL\":null,"
            + "\"baseURL\":\"https://api.box.com/\","
            + "\"baseUploadURL\":\"https://upload.box.com/api/\","
            + "\"authorizationURL\":\"https://account.box.com/api/\","
            + "\"autoRefresh\":true,"
            + "\"maxRetryAttempts\":5}";

    // when
    BoxAPIConnection restoredApi = BoxAPIConnection.restore(clientId, clientSecret, state);

    // then
    assertThat("some-access-token", is(restoredApi.getAccessToken()));
    assertNull(restoredApi.getRefreshToken());
    assertThat("https://api.box.com/2.0/", is(restoredApi.getBaseURL()));
    assertThat("https://upload.box.com/api/2.0/", is(restoredApi.getBaseUploadURL()));
  }

  @Test
  public void restoresProperUrlsWhenDeprecatedUrlsAreSet() {
    // given
    String clientId = "some_client_id";
    String clientSecret = "some_client_secret";

    String savedConnection =
        "{"
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
    BoxAPIConnection restoredApi =
        BoxAPIConnection.restore(clientId, clientSecret, savedConnection);

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

    String savedConnection =
        "{"
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
    BoxAPIConnection restoredApi =
        BoxAPIConnection.restore(clientId, clientSecret, savedConnection);

    // then
    assertThat("https://api.box.com/2.0/", is(restoredApi.getBaseURL()));
    assertThat("https://upload.box.com/api/2.0/", is(restoredApi.getBaseUploadURL()));
  }

  @Test
  public void restoresDefaultMaxRequestAttemptsWhenMissing() {
    // given
    String accessToken = "access_token";
    String clientId = "some_client_id";
    String clientSecret = "some_client_secret";
    String refreshToken = "some_refresh_token";

    String savedConnection =
        "{"
            + "\"accessToken\":\"access_token\","
            + "\"refreshToken\":\"some_refresh_token\","
            + "\"lastRefresh\":0,"
            + "\"expires\":0,"
            + "\"userAgent\":\"Box Java SDK v3.8.0 (Java 1.8.0_345)\","
            + "\"baseURL\":\"https://api.box.com/\","
            + "\"authorizationURL\":\"https://account.box.com/api/\","
            + "\"autoRefresh\":true"
            + "}";
    // when
    BoxAPIConnection restoredApi =
        BoxAPIConnection.restore(clientId, clientSecret, savedConnection);

    // then
    assertThat(BoxGlobalSettings.getMaxRetryAttempts(), is(restoredApi.getMaxRetryAttempts()));
  }

  @Test
  public void successfullyRestoresConnectionWithDeprecatedSettings() {
    String restoreState = TestUtils.getFixture("BoxAPIConnection/State");
    String restoreStateDeprecated = TestUtils.getFixture("BoxAPIConnection/StateDeprecated");

    BoxAPIConnection api = BoxAPIConnection.restore(clientId, clientSecret, restoreState);
    String savedStateAPI = api.save();

    BoxAPIConnection deprecatedAPI =
        BoxAPIConnection.restore(clientId, clientSecret, restoreStateDeprecated);
    String savedStateAPIDeprecated = deprecatedAPI.save();

    assertEquals(api.getMaxRetryAttempts(), deprecatedAPI.getMaxRetryAttempts());
    assertEquals(savedStateAPI, savedStateAPIDeprecated);
  }

  @Test
  public void setBaseUrls() {
    BoxAPIConnection api =
        new BoxAPIConnectionForTests(clientId, clientSecret, accessToken, refreshToken);
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
  public void canOverrideTokenUrlWithBaseUrl() {
    BoxAPIConnection api =
        new BoxAPIConnectionForTests(clientId, clientSecret, accessToken, refreshToken);

    api.setBaseURL("https://my-base.url");

    assertThat(api.getTokenURL(), is("https://my-base.url/oauth2/token"));
  }

  @Test
  public void canOverrideRevokeUrlWithBaseUrl() {
    BoxAPIConnection api =
        new BoxAPIConnectionForTests(clientId, clientSecret, accessToken, refreshToken);

    api.setBaseURL("https://my-base.url");

    assertThat(api.getRevokeURL(), is("https://my-base.url/oauth2/revoke"));
  }

  @Test
  public void allowsToSaveAndRestoreApplicationConnectionWithBaseUrlSet()
      throws URISyntaxException {
    // given
    BoxAPIConnection api =
        new BoxAPIConnectionForTests(clientId, clientSecret, accessToken, refreshToken);
    api.setBaseURL("https://my-base.url");
    api.setBaseUploadURL("https://my-base-upload.url");
    api.setBaseAuthorizationURL("https://my-authorization.url");
    api.setRequestInterceptor(
        request ->
            new BoxAPIConnectionTest.AuthenticationResponse("access_token", refreshToken, "4245"));

    // when
    api.refresh();
    String savedConnection = api.save();
    BoxAPIConnection restoredApi =
        BoxAPIConnection.restore(clientId, clientSecret, savedConnection);

    // then
    assertThat(api.getBaseURL(), is(restoredApi.getBaseURL()));
    assertThat(api.getBaseAppUrl(), is(restoredApi.getBaseAppUrl()));
    assertThat(api.getBaseUploadURL(), is(restoredApi.getBaseUploadURL()));
    assertThat(api.getRevokeURL(), is(restoredApi.getRevokeURL()));
    assertThat(api.getTokenURL(), is(restoredApi.getTokenURL()));
    assertThat(
        api.getAuthorizationURL(new URI("https://my.redirect"), "test", new ArrayList<>()),
        is(
            restoredApi.getAuthorizationURL(
                new URI("https://my.redirect"), "test", new ArrayList<>())));
  }

  @Test
  public void usesCorrectTokenUrlToAuthenticateWhenBaseUrlIsSet() {
    String code = "fakeCode";

    BoxAPIConnection api = new BoxAPIConnectionForTests(clientId, clientSecret);
    api.configureSslCertificatesValidation(
        new TrustAllTrustManager(), new AcceptAllHostsVerifier());
    api.setAutoRefresh(false);
    api.setBaseURL(baseHttpsUrl());
    mockAndAssertAuthentication(clientId, clientSecret, code);

    api.authenticate(code);

    assertThat(api.getAccessToken(), is("access-token"));
    assertThat(api.getRefreshToken(), is("refresh-token"));
  }

  @Test
  public void usesCorrectTokenToAuthenticateUrlWhenBaseUrlIsSet() {
    String code = "fakeCode";

    BoxAPIConnection api = new BoxAPIConnectionForTests(clientId, clientSecret);
    api.configureSslCertificatesValidation(
        new TrustAllTrustManager(), new AcceptAllHostsVerifier());
    api.setAutoRefresh(false);
    api.setBaseURL(baseHttpsUrl());
    mockAndAssertAuthentication(clientId, clientSecret, code);

    api.authenticate(code);

    assertThat(api.getAccessToken(), is("access-token"));
    assertThat(api.getRefreshToken(), is("refresh-token"));
  }

  @Test
  public void usesCorrectTokenUrlToRevokeWhenBaseUrlIsSet() {
    BoxAPIConnection api =
        new BoxAPIConnectionForTests(clientId, clientSecret, accessToken, refreshToken);
    api.configureSslCertificatesValidation(
        new TrustAllTrustManager(), new AcceptAllHostsVerifier());
    api.setBaseURL(baseHttpsUrl());

    mockAndAssertRevoke(accessToken, clientId, clientSecret);

    api.revokeToken();
  }

  @Test
  public void checkThatTheProxyIsUsed() {
    // given and then
    // we do not use HTTPS mock as wiremock ha trouble proxying HTTPS traffic
    wireMockRule.stubFor(
        post(urlPathEqualTo("/oauth2/token"))
            .willReturn(
                aResponse()
                    .withHeader("Content-Type", APPLICATION_JSON)
                    .withBody(
                        "{\"refresh_token\":\"refresh-token\", \"access_token\":\"access-token\", \"expires_in\": 1}")));
    BoxAPIConnection api =
        new BoxAPIConnectionForTests(clientId, clientSecret, accessToken, refreshToken);
    String serverLocation = baseHttpUrl();
    api.setBaseURL(serverLocation);
    WireMockServer proxyWireMockServer = new WireMockServer(wireMockConfig().dynamicPort());
    proxyWireMockServer.start();
    proxyWireMockServer.stubFor(
        post(urlPathEqualTo("/oauth2/token"))
            .withHeader("Host", equalTo("localhost:" + wireMockRule.port()))
            .willReturn(aResponse().proxiedFrom(serverLocation)));

    try {
      // when
      Proxy proxy =
          new Proxy(
              Proxy.Type.HTTP, new InetSocketAddress("127.0.0.1", proxyWireMockServer.port()));
      api.setProxy(proxy);
      api.authenticate("fake code");
    } finally {
      proxyWireMockServer.stop();
    }
  }

  @Test
  public void shouldAddProxyAuthentication() {
    // given and then
    // we do not use HTTPS mock as wiremock ha trouble proxying HTTPS traffic
    wireMockRule.stubFor(
        post(urlPathEqualTo("/oauth2/token"))
            .willReturn(
                aResponse()
                    .withHeader("Content-Type", APPLICATION_JSON)
                    .withBody(
                        "{\"refresh_token\":\"refresh-token\", \"access_token\":\"access-token\", \"expires_in\": 1}")));
    String serverLocation = baseHttpUrl();
    BoxAPIConnection api =
        new BoxAPIConnectionForTests(clientId, clientSecret, accessToken, refreshToken);
    api.setBaseURL(serverLocation);
    WireMockServer proxyWireMockServer = new WireMockServer(wireMockConfig().dynamicPort());
    proxyWireMockServer.start();
    proxyWireMockServer.stubFor(
        post(urlPathEqualTo("/oauth2/token"))
            .inScenario("Proxy Authorization")
            .whenScenarioStateIs(STARTED)
            .willReturn(aResponse().withStatus(407))
            .willSetStateTo("AUTHORIZATION REQUESTED"));
    // host must direct to actual server that should respond
    proxyWireMockServer.stubFor(
        post(urlPathEqualTo("/oauth2/token"))
            .inScenario("Proxy Authorization")
            .whenScenarioStateIs("AUTHORIZATION REQUESTED")
            .withHeader("Proxy-Authorization", matching("Basic .+"))
            .willReturn(aResponse().proxiedFrom(serverLocation)));

    try {
      // when
      Proxy proxy =
          new Proxy(
              Proxy.Type.HTTP, new InetSocketAddress("127.0.0.1", proxyWireMockServer.port()));
      api.setProxy(proxy);
      api.setProxyBasicAuthentication("fakeProxyUsername", "fakeProxyPassword");
      api.authenticate("fake code");
    } finally {
      proxyWireMockServer.stop();
    }
  }

  @Test
  public void shouldUseProvidedAuthenticator() {
    // given and then
    wireMockRule.stubFor(
        post(urlPathEqualTo("/oauth2/token"))
            .willReturn(
                aResponse()
                    .withHeader("Content-Type", APPLICATION_JSON)
                    .withBody(
                        "{\"refresh_token\":\"refresh-token\", \"access_token\":\"access-token\", \"expires_in\": 1}")));
    String serverLocation = baseHttpUrl();
    BoxAPIConnection api =
        new BoxAPIConnectionForTests(clientId, clientSecret, accessToken, refreshToken);
    api.setBaseURL(serverLocation);
    WireMockServer proxyWireMockServer = new WireMockServer(wireMockConfig().dynamicPort());
    proxyWireMockServer.start();
    proxyWireMockServer.stubFor(
        post(urlPathEqualTo("/oauth2/token"))
            .inScenario("Proxy Authorization")
            .whenScenarioStateIs(STARTED)
            .willReturn(aResponse().withStatus(407))
            .willSetStateTo("AUTHORIZATION REQUESTED"));
    // host must direct to actual server that should respond
    proxyWireMockServer.stubFor(
        post(urlPathEqualTo("/oauth2/token"))
            .inScenario("Proxy Authorization")
            .whenScenarioStateIs("AUTHORIZATION REQUESTED")
            .withHeader("Proxy-Authorization", equalTo("My custom authenticator"))
            .willReturn(aResponse().proxiedFrom(serverLocation)));

    try {
      // when
      Proxy proxy =
          new Proxy(
              Proxy.Type.HTTP, new InetSocketAddress("127.0.0.1", proxyWireMockServer.port()));
      api.setProxy(proxy);
      api.setProxyAuthenticator(
          (route, response) ->
              response
                  .request()
                  .newBuilder()
                  .addHeader("Proxy-Authorization", "My custom authenticator")
                  .build());
      api.authenticate("fake code");
    } finally {
      proxyWireMockServer.stop();
    }
  }

  @Test
  public void failsByDefaultWhenUsingSelfSignedCertificates() {
    BoxAPIConnection api = new BoxAPIConnectionForTests("");
    api.configureSslCertificatesValidation(DEFAULT_TRUST_MANAGER, DEFAULT_HOSTNAME_VERIFIER);
    api.setBaseURL(baseHttpsUrl());

    try {
      api.authenticate("fake code");
    } catch (Exception e) {
      Throwable cause = e.getCause();
      assertThat(cause.getClass(), is(SSLHandshakeException.class));
      assertThat(
          cause.getMessage(),
          containsString("unable to find valid certification path to requested target"));
    }
  }

  @Test
  public void failsByDefaultWhenUsingSelfSignedCertificatesThatPointToLocalhost() {
    BoxAPIConnection api = new BoxAPIConnectionForTests("");
    api.setBaseURL(baseHttpsUrl());
    api.configureSslCertificatesValidation(new TrustAllTrustManager(), DEFAULT_HOSTNAME_VERIFIER);

    try {
      api.authenticate("fake code");
    } catch (Exception e) {
      Throwable cause = e.getCause();
      assertThat(cause.getClass(), is(SSLPeerUnverifiedException.class));
      assertThat(cause.getMessage(), containsString("Hostname localhost not verified"));
    }
  }

  @Test
  public void canBeConfiguredToUseSelfSignedCertificates() {
    // given and then
    wireMockHttpsRule.stubFor(
        post(urlPathEqualTo("/oauth2/token"))
            .willReturn(
                aResponse()
                    .withHeader("Content-Type", APPLICATION_JSON)
                    .withBody(
                        "{\"refresh_token\":\"refresh-token\","
                            + " \"access_token\":\"access-token-from-mock\","
                            + " \"expires_in\": 1}")));

    String serverLocation = baseHttpsUrl();
    BoxAPIConnection api = new BoxAPIConnectionForTests("");
    api.setBaseURL(serverLocation);

    api.configureSslCertificatesValidation(
        new TrustAllTrustManager(), new AcceptAllHostsVerifier());

    api.authenticate("fake code");
    assertThat(api.getAccessToken(), is("access-token-from-mock"));
  }

  private String baseHttpsUrl() {
    return format("https://localhost:%d", wireMockHttpsRule.httpsPort());
  }

  private String baseHttpUrl() {
    return format("http://localhost:%d", wireMockRule.port());
  }

  private void mockAndAssertRevoke(String token, String clientId, String clientSecret) {
    wireMockHttpsRule.stubFor(
        post(urlPathEqualTo("/oauth2/revoke"))
            .withRequestBody(
                equalTo(
                    format(
                        "token=%s&client_id=%s&client_secret=%s", token, clientId, clientSecret)))
            .willReturn(
                aResponse()
                    .withHeader("Content-Type", APPLICATION_JSON)
                    .withBody(
                        "{\"refresh_token\":\"refresh-token\", \"access_token\":\"access-token\", \"expires_in\": 1}")));
  }

  private void mockAndAssertAuthentication(String clientId, String clientSecret, String code) {
    wireMockHttpsRule.stubFor(
        post(urlPathEqualTo("/oauth2/token"))
            .withRequestBody(
                equalTo(
                    format(
                        "grant_type=authorization_code&code=%s&client_id=%s&client_secret=%s",
                        code, clientId, clientSecret)))
            .willReturn(
                aResponse()
                    .withHeader("Content-Type", APPLICATION_JSON)
                    .withBody(
                        "{\"refresh_token\":\"refresh-token\", \"access_token\":\"access-token\", \"expires_in\": 1}")));
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
          + "    \"access_token\": \""
          + accessToken
          + "\",\n"
          + "    \"refresh_token\": \""
          + refreshToken
          + "\",\n"
          + "    \"expires_in\": "
          + expiresIn
          + ",\n"
          + "    \"restricted_to\": [],\n"
          + "    \"token_type\": \"bearer\"\n"
          + "}";
    }
  }
}
