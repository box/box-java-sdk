package com.box.sdk;

import static com.box.sdk.BoxAPIConnection.AS_USER_HEADER;
import static com.box.sdk.BoxCCGAPIConnection.ENTERPRISE_SUBJECT_TYPE;
import static com.box.sdk.BoxCCGAPIConnection.USER_SUBJECT_TYPE;
import static com.box.sdk.http.ContentType.APPLICATION_FORM_URLENCODED;
import static com.box.sdk.http.ContentType.APPLICATION_JSON;
import static com.box.sdk.http.HttpHeaders.CONTENT_TYPE;
import static com.github.tomakehurst.wiremock.client.WireMock.aResponse;
import static com.github.tomakehurst.wiremock.client.WireMock.post;
import static com.github.tomakehurst.wiremock.client.WireMock.stubFor;
import static com.github.tomakehurst.wiremock.client.WireMock.urlEqualTo;
import static com.github.tomakehurst.wiremock.core.WireMockConfiguration.wireMockConfig;
import static com.github.tomakehurst.wiremock.stubbing.Scenario.STARTED;
import static java.lang.String.format;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThrows;

import com.github.tomakehurst.wiremock.junit.WireMockRule;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.stream.Collectors;
import org.junit.Rule;
import org.junit.Test;

/**
 * Unit tests for {@link BoxCCGAPIConnection}
 */
public class BoxCCGAPIConnectionTest {

    @Rule
    public WireMockRule wireMockRule = new WireMockRule(wireMockConfig().dynamicPort());

    @Test
    public void createsServiceAccountConnection() {
        // given
        String clientId = "some_client_id";
        String clientSecret = "some_client_secret";
        String enterpriseId = "some_enterprise_id";
        String accessToken = "access_token";
        BoxAPIConnection api =
            BoxCCGAPIConnection.applicationServiceAccountConnection(clientId, clientSecret, enterpriseId);
        api.setRequestInterceptor(request -> {
            // then
            assertThat(request.getMethod(), is("POST"));
            assertRequestHeaders(request);
            assertRequestTokenBody(
                request,
                "some_client_id",
                "some_client_secret",
                ENTERPRISE_SUBJECT_TYPE,
                "some_enterprise_id"
            );
            return new CCGAuthenticationResponse(accessToken, "4245");
        });

        // when
        api.refresh();

        assertThat(api.getAccessToken(), is(accessToken));
        assertThat(api.getExpires(), is(4245000L));
        assertThat(api.getClientID(), is(clientId));
        assertThat(api.getClientSecret(), is(clientSecret));
    }

    @Test
    public void createsUserConnection() {
        // given
        String clientId = "some_client_id";
        String clientSecret = "some_client_secret";
        String userId = "some_user_id";
        String accessToken = "access_token";
        BoxAPIConnection api =
            BoxCCGAPIConnection.userConnection(clientId, clientSecret, userId);
        api.setRequestInterceptor(request -> {
            // then
            assertThat(request.getMethod(), is("POST"));
            assertRequestHeaders(request);
            assertRequestTokenBody(
                request,
                "some_client_id",
                "some_client_secret",
                USER_SUBJECT_TYPE,
                "some_user_id"
            );
            return new CCGAuthenticationResponse(accessToken, "4245");
        });

        // when
        api.refresh();

        assertThat(api.getAccessToken(), is(accessToken));
        assertThat(api.getExpires(), is(4245000L));
        assertThat(api.getClientID(), is(clientId));
        assertThat(api.getClientSecret(), is(clientSecret));
    }

    @Test
    public void shouldRetryOnFailure() {
        // given
        String accessToken = "access_token";
        BoxAPIConnection api = createDefaultApplicationConnection();
        api.setTokenURL(format("http://localhost:%d/oauth2/token", wireMockRule.port()));
        String scenarioName = "Retry getting token";
        String stateName = "Retry";
        stubFor(post(urlEqualTo("/oauth2/token"))
            .inScenario(scenarioName)
            .whenScenarioStateIs(STARTED)
            .willReturn(aResponse()
                .withBody("{}")
                .withStatus(500)
                .withHeader(CONTENT_TYPE, APPLICATION_JSON)
            )
            .willSetStateTo(stateName));
        stubFor(post(urlEqualTo("/oauth2/token"))
            .inScenario(scenarioName)
            .whenScenarioStateIs(stateName)
            .willReturn(aResponse()
                .withBody("{\n"
                    + "    \"access_token\": \"" + accessToken + "\",\n"
                    + "    \"expires_in\": 4000,\n"
                    + "    \"restricted_to\": [],\n"
                    + "    \"token_type\": \"bearer\"\n"
                    + "}")
                .withHeader(CONTENT_TYPE, APPLICATION_JSON)
                .withStatus(200)
            )
        );

        // when
        api.refresh();

        // then
        assertThat(api.getAccessToken(), is(accessToken));
    }

    @Test
    public void allowsToSaveAndRestoreApplicationConnection() {
        // given
        String accessToken = "access_token";
        String clientId = "some_client_id";
        String clientSecret = "some_client_secret";
        String enterpriseId = "some_enterprise_id";
        BoxCCGAPIConnection api =
            BoxCCGAPIConnection.applicationServiceAccountConnection(clientId, clientSecret, enterpriseId);
        api.setRequestInterceptor(request -> new CCGAuthenticationResponse(accessToken, "4245"));

        // when
        api.refresh();
        String savedConnection = api.save();
        BoxCCGAPIConnection restoredApi = BoxCCGAPIConnection.restore(clientId, clientSecret, savedConnection);

        // then
        assertThat(api.getAccessToken(), is(restoredApi.getAccessToken()));
        assertThat(api.getLastRefresh(), is(restoredApi.getLastRefresh()));
        assertThat(api.getExpires(), is(restoredApi.getExpires()));
        assertThat(api.getUserAgent(), is(restoredApi.getUserAgent()));
        assertThat(api.getTokenURL(), is(restoredApi.getTokenURL()));
        assertThat(api.getBaseURL(), is(restoredApi.getBaseURL()));
        assertThat(api.getBaseAppUrl(), is(restoredApi.getBaseAppUrl()));
        assertThat(api.getBaseUploadURL(), is(restoredApi.getBaseUploadURL()));
        assertThat(api.getAutoRefresh(), is(restoredApi.getAutoRefresh()));
        assertThat(api.getMaxRetryAttempts(), is(restoredApi.getMaxRetryAttempts()));
        assertThat(api.isUserConnection(), is(restoredApi.isUserConnection()));
    }

    @Test
    public void allowsToSaveAndRestoreUserConnection() {
        // given
        String accessToken = "access_token";
        String clientId = "some_client_id";
        String clientSecret = "some_client_secret";
        String userId = "some_user_id";
        BoxCCGAPIConnection api =
            BoxCCGAPIConnection.userConnection(clientId, clientSecret, userId);
        api.setRequestInterceptor(request -> new CCGAuthenticationResponse(accessToken, "4245"));

        // when
        api.refresh();
        String savedConnection = api.save();
        BoxAPIConnection restoredApi = BoxCCGAPIConnection.restore(clientId, clientSecret, savedConnection);

        restoredApi.setRequestInterceptor(request -> {
            // then
            assertThat(request.getMethod(), is("POST"));
            assertRequestHeaders(request);
            assertRequestTokenBody(
                request,
                clientId,
                clientSecret,
                USER_SUBJECT_TYPE,
                userId
            );
            return new CCGAuthenticationResponse(accessToken, "4245");
        });

        // when
        restoredApi.refresh();
    }

    @Test
    public void applicationConnectionAllowsToSetAsUserHeader() {
        // given
        String accessToken = "access_token";
        BoxAPIConnection api = createDefaultApplicationConnection();
        String userId = "some_user_id";
        api.asUser(userId);
        api.setRequestInterceptor(request -> {
            // then
            Optional<BoxAPIRequest.RequestHeader> header = request.getHeaders().stream()
                .filter(h -> h.getKey().equals(AS_USER_HEADER))
                .findFirst();
            assertThat(header.isPresent(), is(true));
            assertThat(header.get().getValue(), is(userId));
            return new CCGAuthenticationResponse(accessToken, "4245");
        });

        // when
        api.refresh();
    }

    @Test
    public void applicationConnectionAllowsToRemoveAsUserHeader() {
        // given
        String accessToken = "access_token";
        BoxAPIConnection api = createDefaultApplicationConnection();
        api.asUser("some_user_id");
        api.asSelf();
        api.setRequestInterceptor(request -> {
            // then
            Optional<BoxAPIRequest.RequestHeader> header = request.getHeaders().stream()
                .filter(h -> h.getKey().equals(AS_USER_HEADER))
                .findFirst();
            assertThat(header.isPresent(), is(false));
            return new CCGAuthenticationResponse(accessToken, "4245");
        });

        // when
        api.refresh();
    }


    @Test
    public void userConnectionDoesNotAllowToSetAsUserHeader() {
        // given
        BoxAPIConnection api = createDefaultUserConnection();

        // expect
        assertThrows("Cannot set As-User header connection created for user.",
            IllegalStateException.class,
            () -> api.asUser("some_user_id")
        );
    }

    @Test
    public void userConnectionDoesNotAllowToRemoveAsUserHeader() {
        // given
        BoxAPIConnection api = createDefaultUserConnection();

        // expect
        assertThrows("Cannot remove As-User header connection created for user.",
            IllegalStateException.class,
            api::asSelf
        );
    }

    @Test
    public void allowsToRevokeToken() {
        // given
        String clientId = "some_client_id";
        String clientSecret = "some_client_secret";
        String userId = "some_user_id";
        String accessToken = "access_token";
        AtomicBoolean wasTokenRevoked = new AtomicBoolean(false);
        BoxAPIConnection api =
            BoxCCGAPIConnection.userConnection(clientId, clientSecret, userId);
        api.setAccessToken("");
        api.setRequestInterceptor(request -> {
            if (request.getUrl().toString().contains("token")) {
                return new CCGAuthenticationResponse(accessToken, "4245");
            }
            // then
            wasTokenRevoked.set(true);
            assertThat(request.getMethod(), is("POST"));
            assertRevokeTokenBody(
                request,
                clientId,
                clientSecret,
                accessToken
            );
            return new BoxAPIResponse() {
                @Override
                public int getResponseCode() {
                    return 200;
                }
            };
        });

        // when
        api.refresh();
        api.revokeToken();

        //then
        assertThat(wasTokenRevoked.get(), is(true));
    }

    private BoxAPIConnection createDefaultUserConnection() {
        return BoxCCGAPIConnection
            .userConnection("some_client_id", "some_client_secret", "some_user_id");
    }

    private BoxAPIConnection createDefaultApplicationConnection() {
        return BoxCCGAPIConnection
            .applicationServiceAccountConnection("some_client_id", "some_client_secret", "some_enterprise_id");
    }

    private void assertRequestTokenBody(
        BoxAPIRequest request, String clientId, String clientSecret, String subjectType, String subjectId
    ) {
        List<String> body = new BufferedReader(new InputStreamReader(request.getBody(), StandardCharsets.UTF_8))
            .lines()
            .flatMap(s -> Arrays.stream(s.split("&")))
            .collect(Collectors.toList());
        assertThat(body, hasSize(5));
        assertThat(body, containsInAnyOrder(
            "grant_type=client_credentials",
            "client_id=" + clientId,
            "client_secret=" + clientSecret,
            "box_subject_type=" + subjectType,
            "box_subject_id=" + subjectId

        ));
    }

    private void assertRevokeTokenBody(BoxAPIRequest request, String clientId, String clientSecret, String token) {
        List<String> body = new BufferedReader(new InputStreamReader(request.getBody(), StandardCharsets.UTF_8))
            .lines()
            .flatMap(s -> Arrays.stream(s.split("&")))
            .collect(Collectors.toList());
        assertThat(body, hasSize(3));
        assertThat(body, containsInAnyOrder(
            "client_id=" + clientId,
            "client_secret=" + clientSecret,
            "token=" + token
        ));
    }

    private void assertRequestHeaders(BoxAPIRequest request) {
        Optional<BoxAPIRequest.RequestHeader> header = request.getHeaders().stream()
            .filter(h -> h.getKey().equals(CONTENT_TYPE))
            .findFirst();
        assertThat(header.isPresent(), is(true));
        assertThat(header.get().getValue(), is(APPLICATION_FORM_URLENCODED));
    }

    private static final class CCGAuthenticationResponse extends BoxJSONResponse {
        private final String accessToken;
        private final String expiresIn;

        private CCGAuthenticationResponse(String accessToken, String expiresIn) {
            this.accessToken = accessToken;
            this.expiresIn = expiresIn;
        }


        @Override
        public String getJSON() {
            return "{\n"
                + "    \"access_token\": \"" + accessToken + "\",\n"
                + "    \"expires_in\": " + expiresIn + ",\n"
                + "    \"restricted_to\": [],\n"
                + "    \"token_type\": \"bearer\"\n"
                + "}";
        }
    }
}
