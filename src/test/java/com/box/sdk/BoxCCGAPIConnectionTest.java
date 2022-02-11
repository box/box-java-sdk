package com.box.sdk;

import static com.box.sdk.BoxCCGAPIConnection.ENTERPRISE_SUBJECT_TYPE;
import static com.box.sdk.BoxCCGAPIConnection.USER_SUBJECT_TYPE;
import static com.box.sdk.http.ContentType.APPLICATION_FORM_URLENCODED;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.fail;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.stream.Collectors;
import org.junit.Test;

/**
 * Unit tests for {@link BoxCCGAPIConnection}
 */
public class BoxCCGAPIConnectionTest {
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
        fail("Implement me");
    }

    @Test
    public void allowsToSaveAndRestoreConnection() {
        fail("Implement me");
    }

    @Test
    public void changingToUserConnectionChangesGrantType() {
        fail("Implement me");
    }

    @Test
    public void changingToApplicationConnectionChangesGrantType() {
        fail("Implement me");
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
                "some_client_id",
                "some_client_secret",
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
            .filter(h -> h.getValue().equals(APPLICATION_FORM_URLENCODED))
            .findFirst();
        assertThat(header.isPresent(), is(true));
    }

    private static class CCGAuthenticationResponse extends BoxJSONResponse {
        private final String accessToken;
        private final String expiresIn;

        private CCGAuthenticationResponse(String accessToken, String expiresIn) {
            this.accessToken = accessToken;
            this.expiresIn = expiresIn;
        }


        @Override
        public String getJSON() {
            return "{\n" +
                "    \"access_token\": \"" + accessToken + "\",\n" +
                "    \"expires_in\": " + expiresIn + ",\n" +
                "    \"restricted_to\": [],\n" +
                "    \"token_type\": \"bearer\"\n" +
                "}";
        }
    }
}