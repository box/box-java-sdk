package com.box.sdk;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.Assert;
import org.junit.Test;
import org.junit.experimental.categories.Category;

import com.eclipsesource.json.JsonObject;

public class BoxAPIConnectionTest {
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
    public void getAuthorizetionURLSuccess() throws Exception {
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
    @Category(IntegrationTestJWT.class)
    public void developerEditionAppAuthWorks() {
        final String enterpriseId = TestConfig.getEnterpriseID();
        final String clientId = TestConfig.getClientID();
        final String clientSecret = TestConfig.getClientSecret();
        final String privateKey = TestConfig.getPrivateKey();
        final String privateKeyPassword = TestConfig.getPrivateKeyPassword();
        final String publicKeyID = TestConfig.getPublicKeyID();

        JWTEncryptionPreferences encryptionPref = new JWTEncryptionPreferences();
        encryptionPref.setPrivateKey(privateKey);
        encryptionPref.setPrivateKeyPassword(privateKeyPassword);
        encryptionPref.setPublicKeyID(publicKeyID);
        encryptionPref.setEncryptionAlgorithm(EncryptionAlgorithm.RSA_SHA_256);

        IAccessTokenCache accessTokenCache = new InMemoryLRUAccessTokenCache(100);

        BoxDeveloperEditionAPIConnection api = BoxDeveloperEditionAPIConnection.getAppEnterpriseConnection(enterpriseId,
            clientId, clientSecret, encryptionPref, accessTokenCache);

        assertThat(api.getAccessToken(), not(equalTo(null)));

        final String name = "app user name";
        BoxUser.Info createdUserInfo = BoxUser.createAppUser(api, name);
        final String appUserId = createdUserInfo.getID();

        assertThat(createdUserInfo.getID(), not(equalTo(null)));
        assertThat(createdUserInfo.getName(), equalTo(name));

        BoxUser appUser = new BoxUser(api, appUserId);

        final String newName = "app user updated name";
        createdUserInfo.setName(newName);
        appUser.updateInfo(createdUserInfo);

        assertThat(createdUserInfo.getName(), equalTo(newName));

        appUser.delete(false, true);

        api.refresh();
    }

    @Test
    @Category(IntegrationTestJWT.class)
    public void developerEditionAppUserWorks() {
        final String enterpriseId = TestConfig.getEnterpriseID();
        final String clientId = TestConfig.getClientID();
        final String clientSecret = TestConfig.getClientSecret();
        final String privateKey = TestConfig.getPrivateKey();
        final String privateKeyPassword = TestConfig.getPrivateKeyPassword();
        final String publicKeyID = TestConfig.getPublicKeyID();

        JWTEncryptionPreferences encryptionPref = new JWTEncryptionPreferences();
        encryptionPref.setPrivateKey(privateKey);
        encryptionPref.setPrivateKeyPassword(privateKeyPassword);
        encryptionPref.setPublicKeyID(publicKeyID);
        encryptionPref.setEncryptionAlgorithm(EncryptionAlgorithm.RSA_SHA_256);

        IAccessTokenCache accessTokenCache = new InMemoryLRUAccessTokenCache(100);

        BoxDeveloperEditionAPIConnection appAuthConnection = BoxDeveloperEditionAPIConnection
            .getAppEnterpriseConnection(enterpriseId, clientId, clientSecret, encryptionPref, accessTokenCache);

        final String name = "app user name two";
        BoxUser.Info createdUserInfo = BoxUser.createAppUser(appAuthConnection, name);
        final String appUserId = createdUserInfo.getID();

        BoxDeveloperEditionAPIConnection api = BoxDeveloperEditionAPIConnection.getAppUserConnection(appUserId,
            clientId, clientSecret, encryptionPref, accessTokenCache);
        BoxUser appUser = new BoxUser(api, appUserId);

        assertThat(api.getAccessToken(), not(equalTo(null)));

        BoxUser.Info info = appUser.getInfo();

        assertThat(info.getID(), equalTo(appUserId));
        assertThat(info.getName(), equalTo(name));

        api.refresh();

        BoxUser appUserFromAdmin = new BoxUser(appAuthConnection, appUserId);
        appUserFromAdmin.delete(false, true);
    }
}
