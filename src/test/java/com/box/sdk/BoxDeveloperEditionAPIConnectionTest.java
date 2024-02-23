package com.box.sdk;

import static com.box.sdk.http.ContentType.APPLICATION_JSON;
import static com.github.tomakehurst.wiremock.client.WireMock.aResponse;
import static com.github.tomakehurst.wiremock.client.WireMock.post;
import static com.github.tomakehurst.wiremock.client.WireMock.postRequestedFor;
import static com.github.tomakehurst.wiremock.client.WireMock.requestMatching;
import static com.github.tomakehurst.wiremock.client.WireMock.urlPathEqualTo;
import static com.github.tomakehurst.wiremock.client.WireMock.urlPathMatching;
import static com.github.tomakehurst.wiremock.client.WireMock.verify;
import static com.github.tomakehurst.wiremock.core.WireMockConfiguration.wireMockConfig;
import static com.github.tomakehurst.wiremock.stubbing.Scenario.STARTED;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.*;

import com.github.tomakehurst.wiremock.extension.Parameters;
import com.github.tomakehurst.wiremock.http.Request;
import com.github.tomakehurst.wiremock.http.RequestMethod;
import com.github.tomakehurst.wiremock.junit.WireMockRule;
import com.github.tomakehurst.wiremock.matching.MatchResult;
import com.github.tomakehurst.wiremock.matching.RequestMatcherExtension;
import org.jose4j.jwt.JwtClaims;
import org.jose4j.jwt.consumer.JwtConsumer;
import org.jose4j.jwt.consumer.JwtConsumerBuilder;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.Mockito;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;

public class BoxDeveloperEditionAPIConnectionTest {

    @Rule
    public WireMockRule wireMockRule = new WireMockRule(wireMockConfig().dynamicHttpsPort().httpDisabled(true));

    private String jtiClaim = null;

    @Test
    public void retriesWithNewJWTAssertionOnErrorResponseAndFails() {
        final String tokenPath = "/oauth2/token";
        BoxDeveloperEditionAPIConnection api = this.getBoxDeveloperEditionAPIConnection();

        this.mockFirstResponse(tokenPath);

        this.wireMockRule.stubFor(requestMatching(this.getRequestMatcher(tokenPath))
            .atPriority(2)
            .inScenario("JWT Retry")
            .whenScenarioStateIs("429 sent")
            .willReturn(aResponse()
                .withStatus(429)
                .withHeader("Retry-After", "1")
                .withHeader("Date", "Sat, 18 Nov 2017 11:18:00 GMT")));

        this.mockListener();

        try {
            api.authenticate();
        } catch (BoxAPIException e) {
            verify(3, postRequestedFor(urlPathEqualTo("/oauth2/token")));
            assertThat(e.getResponseCode(), is(429));
        }
    }

    @Test
    public void retriesWithNewJWTAssertionOnErrorResponseAndSucceeds() {
        final String tokenPath = "/oauth2/token";
        final String accessToken = "mNr1FrCvOeWiGnwLL0OcTL0Lux5jbyBa";
        BoxDeveloperEditionAPIConnection api = this.getBoxDeveloperEditionAPIConnection();

        this.mockFirstResponse(tokenPath);

        this.wireMockRule.stubFor(requestMatching(this.getRequestMatcher(tokenPath))
            .atPriority(2)
            .inScenario("JWT Retry")
            .whenScenarioStateIs("429 sent")
            .willReturn(aResponse()
                .withStatus(200)
                .withHeader("Content-Type", APPLICATION_JSON)
                .withBody(responseWithToken(accessToken))));

        this.mockListener();

        api.authenticate();

        verify(2, postRequestedFor(urlPathEqualTo("/oauth2/token")));
        assertThat(api.getAccessToken(), is(accessToken));
    }

    @Test
    public void retriesWithNewJWTAssertionOnClockSkewErrorResponseAndSucceeds() {
        final String tokenPath = "/oauth2/token";
        final String accessToken = "some_token";
        BoxDeveloperEditionAPIConnection api = this.getBoxDeveloperEditionAPIConnection();

        this.wireMockRule.stubFor(post(urlPathMatching(tokenPath))
            .atPriority(1)
            .inScenario("JWT Retry")
            .whenScenarioStateIs(STARTED)
            .willReturn(aResponse()
                .withStatus(400)
                .withHeader("Retry-After", "1")
                .withHeader("Date", "Sat, 18 Nov 2017 11:18:00 GMT")
                .withHeader("Content-Type", APPLICATION_JSON)
                .withBody("{\n"
                    + "   \"type\": \"error\",\n"
                    + "   \"status\": 400,\n"
                    + "   \"code\": \"invalid_grant\",\n"
                    + "   \"message\": \"Current date time must be before the expiration"
                    + " date time listed in the 'exp' claim.\"\n"
                    + "}"))
            .willSetStateTo("400 sent"));

        this.wireMockRule.stubFor(requestMatching(this.getRequestMatcher(tokenPath))
            .atPriority(2)
            .inScenario("JWT Retry")
            .whenScenarioStateIs("400 sent")
            .willReturn(aResponse()
                .withStatus(200)
                .withHeader("Content-Type", APPLICATION_JSON)
                .withBody(responseWithToken(accessToken))));

        this.mockListener();

        api.authenticate();

        verify(2, postRequestedFor(urlPathEqualTo("/oauth2/token")));
        assertThat(api.getAccessToken(), is(accessToken));
    }

    @Test
    public void retriesWithWhenJtiClaimIsDuplicated() {
        final String tokenPath = "/oauth2/token";
        final String accessToken = "some_token";
        BoxDeveloperEditionAPIConnection api = this.getBoxDeveloperEditionAPIConnection();

        this.wireMockRule.stubFor(post(urlPathMatching(tokenPath))
            .inScenario("Retry when JTI fails")
            .whenScenarioStateIs(STARTED)
            .willReturn(aResponse()
                .withStatus(400)
                .withHeader("Content-Type", APPLICATION_JSON)
                .withBody("{"
                    + "\"error\":\"invalid_grant\","
                    + "\"error_description\":\"Please check the 'jti' claim. A unique 'jti' value is required.\""
                    + "}"
                ))
            .willSetStateTo("400 error recieved"));

        this.wireMockRule.stubFor(post(urlPathMatching(tokenPath))
            .inScenario("Retry when JTI fails")
            .whenScenarioStateIs("400 error recieved")
            .willReturn(aResponse()
                .withStatus(200)
                .withHeader("Content-Type", APPLICATION_JSON)
                .withBody(responseWithToken(accessToken)
                )));

        api.authenticate();
        verify(2, postRequestedFor(urlPathEqualTo("/oauth2/token")));
        assertThat(api.getAccessToken(), is(accessToken));
    }

    @Test
    public void usesCustomDecryptorClassImplementation() throws NoSuchAlgorithmException, InvalidKeySpecException {
        final String tokenPath = "/oauth2/token";
        final String accessToken = "mNr1FrCvOeWiGnwLL0OcTL0Lux5jbyBa";
        // This is freshly-generated private key, which is not used for a real Box account.
        // It is safe to use in this unit test.
        String decryptedPrivateKey = "MIIEvgIBADANBgkqhkiG9w0BAQEFAASCBKgwggSkAgEAAoIBAQC5fehGf7yDfLTDewsBcPyJSIIH1IB"
                + "z3a7etHWCLEkUria6fVMltD/SMrqpOYL5ayFQEP3pzP0pskBD/uGFXCDfuPJ4SToHgaJYHIyw56YZcwqO6+T/3pWpQ54kuxUZH+"
                + "ojkTLaJAyMPCiXCNTQD6UDYDHaYQLXafJYh3GWzQVTqXJmCwjv7CD22O0cWkQNH1pelnTQ6ZSvn9osSgnUV51CVlziC05T6"
                + "LxgVd0UhiBa1HdTI3D7X5SbXx4bFs1x/qyqJCNQX4J7sB9jUtKl0s6SX+UN5j59tbDCE/x0t4OOpetSECWkVxRMX5R6CezVj"
                + "Us+3PnNMCjXxGrb3DnB6P0dAgMBAAECggEAFyTHg2xSqBE6OJ20jNR9Hd/nIXT5JfvF4tGfS8OcxrDH8kLKygyIXgCoW47qc"
                + "ZZVTLkiBTbna3lrHVDC8LHDBEb+MdXpIKCjEd1WDIiKp+g7rANwyiAKilj+dVTGWCEsRI3MS31t911WLyoR63fYPeiVr8qk4"
                + "R29+B/GI2unO33VXNQj4lF81jvHdqgIQkYaY37nQSGx7MHamDscDLHvWKzrvY4sjidS4KgmVzkVmbMBwHvY6asoGhkZWTtir"
                + "u0rJpirvHaGJAuWZjfVCaZkT4XAKloOrsZiMmAYCzG5xLy4EiB68zHFmQ8V/zLcMCSlofC9tmvp9NQHPY7cSUFzYQKBgQDqC"
                + "5j+SFFx56gz63gazYKseGIfkFHbQFOulVdV6cSDuIjvZtQJ2PMaIv8i5tpGlIJsPiOqoJU0NI1/aGrPFtFUpNXChMaPWiBdm"
                + "GDwJKUO8EWsHYk1sm0z7Nf4tfuPAV/kH75wsOMlcQ5qrtYU3rrcjfEIpILtG/VTja+37OAZmQKBgQDK5FaoHb8W4hanZQ6my"
                + "AG39iF2Z9qITKWyvZ8yh3zGvhvO5RGR7KPjrCp1cnizNfmF+q6lJxmXXowijdTaL7opaVayCJt3ewvgatr9uhT8Vz6kgfeA7"
                + "O+7dIRaTQB8+YTeMFRdlRnuVGx2JLbyUycIJCb3mmWHGTL9lW0ZWyHaJQKBgQDHOBIFuLci9uZ1M2Tro60sc9hKN8WFlI7ml"
                + "5ZcufydhrGA3o10yGe+ArYcFlcMJxORYZ9oeQIoCue64L2yAyEyJJET34NIuJW+NZumLfsV6S3VINsPiw5rWZpIyVcU1j2yZ"
                + "9bqA5eF4mM8KhBueVyjqmrWSXpsrBS6B2vgalAjWQKBgBtx0asCAxQ0Vv4jtFypF1psB9C9cZkYTR2lesBaBW3Yz2goIj1L9"
                + "ktYwZGLf3o2Zd9SrocWh+aq2mfeKZmt9Q+e+SQx992snkmoCqFhp28O2iFklzcwValUtIaGffdpxShM/0x9W7maX+WHR9v1l"
                + "YULZt39W5hvty8IJG7WnfilAoGBAM6SeUI0xN30tWV5r1cLCG4d+THzqGjZCisCiL2/QN9cWKhtan5Z0+EZd7YkPQpFPw7+R"
                + "CDL/zrR6RMV0ZhLjxMwVwbameaHoYKYKzTP8rGYwrVFWDNeGh9arn9UdeF/CTfwBiYDtHSjHdVOUW3KYb6VnYuFG+uog0uOB"
                + "5uEg9Z5";
        byte[] privateKeyBytes = Base64.decode(decryptedPrivateKey);
        PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(privateKeyBytes);
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        PrivateKey privateKey = keyFactory.generatePrivate(keySpec);

        IPrivateKeyDecryptor decryptorMock = mock(IPrivateKeyDecryptor.class);

        when(decryptorMock.decryptPrivateKey(anyString(), eq("testkey"))).thenReturn(privateKey);
        BoxDeveloperEditionAPIConnection api = this.getBoxDeveloperEditionAPIConnection(decryptorMock);

        this.mockFirstResponse(tokenPath);

        this.wireMockRule.stubFor(requestMatching(this.getRequestMatcher(tokenPath))
                .atPriority(2)
                .inScenario("JWT Retry")
                .whenScenarioStateIs("429 sent")
                .willReturn(aResponse()
                        .withStatus(200)
                        .withHeader("Content-Type", APPLICATION_JSON)
                        .withBody(responseWithToken(accessToken))));

        this.mockListener();

        api.authenticate();

        Mockito.verify(decryptorMock, times(2)).decryptPrivateKey(anyString(), eq("testkey"));
        assertThat(api.getAccessToken(), is(accessToken));
    }

    private static String responseWithToken(String accessToken) {
        return "{\n"
            + "   \"access_token\": \"" + accessToken + "\",\n"
            + "   \"expires_in\": 4169,\n"
            + "   \"restricted_to\": [],\n"
            + "   \"token_type\": \"bearer\"\n"
            + "}";
    }

    private BoxDeveloperEditionAPIConnection getBoxDeveloperEditionAPIConnection() {
        return getBoxDeveloperEditionAPIConnection(null);
    }

    private BoxDeveloperEditionAPIConnection getBoxDeveloperEditionAPIConnection(IPrivateKeyDecryptor decryptor) {
        final String baseURL = "https://localhost:" + wireMockRule.httpsPort();
        final int expectedNumRetryAttempts = 2;

        JWTEncryptionPreferences prefs = new JWTEncryptionPreferences();
        prefs.setEncryptionAlgorithm(EncryptionAlgorithm.RSA_SHA_256);
        // @NOTE(mwiller) 2018-01-16: These are freshly-generated keys which are not used for a real Box
        // account.  They are safe to use in this unit test.
        String privateKey = "LS0tLS1CRUdJTiBSU0EgUFJJVkFURSBLRVktLS0tLQpQcm9jLVR5cGU6IDQsRU5DUllQVEVECkRFSy1JbmZvOiBB"
            + "RVMtMjU2LUNCQywxMTYwMDM3MUZFNkIzMjBFNzVEOTE0NEE5OEIwMDU4MgoKMDYwRUVDdW5QVWQxMmlScXEwZnhQU1BLZWh6VGtocH"
            + "p4UnJobklLYXlhcUcxS2Uzc1RWdHJGdW0xdEdoNWNrNwpBZFphMnVhSkhna1ZTS0hqSldwUTMwUnZnUjlvK2wyS2FRNnZpKzRaRHRm"
            + "UVYyMmNnblIyb0hBWHM4a1hzdU1ECk04QUhqUW85TWpqV1dxZGQ0cFlyYys5eDBxakVaTXQ1dzdyWW9mVkRmdTgxVVVkZi80UFhtOG"
            + "RLY2ZaYkRQSHkKVGxEaUhycFRTWFdqZFZiWnJqYUZXM3lPK2M3bkY2MmV6MTNIUDRhcmhoeTB3TnRYNVRYUUtTQ01tRHQ2SDkxKwp0"
            + "VWxYd29kek1Hbk1oYUN0QmcvblRZUks5VkVkbFBJUlRpaUlpV0t5V2NmeDNDMU1yQkJVakkvaUttUHpuNXFRCitJbjVScFVOcWphWU"
            + "5taExPQmp0NC9LRy9zM2JrUEZzd0lpUVY4eFI0ditLMjdZcUxNQWNWMytpL3k5cXNFY0cKSkNnNS9vZmJyanZ1TXFXNDVyY2dKckc4"
            + "YVRNTkdMUzZqbTZ4ZkQ5Ym9IajQxNUQ4cTVFZEl2cDhCeFRrcGNucApOV0k3MXZIY2poWGJxNEhXZ1FqbE9hb2wzL2FJR2srb084VU"
            + "w0b3FSaDFyR09pVkNjcENqTkltWkNiakJSVTVzCitQWFRvakdPSGd4V3BwQ2MrajVwWFpZNjUvenVzVHJaTy8yT2hrSTg5Sm4xcUp2"
            + "K0xFVnpENGsyRWQvcmo1ZG0KOVVsSEJJbWlVakpONTNpRHFTMFFpYk9sYXNDR3kwZW51N0lxL244a1FBUG9nOEZvRjBDcVh4cC8zM3"
            + "NYK1JIMgpodHdjcVlsOXNkNVFFUWlSNnBXclQwSDdLdk1KWGtqaGp1bWthMzVsMCs3Y1FDUXR1Tm9HUm8yei8rUDNJaCtPCmd5eGpW"
            + "ZmRvaEtNalQ3UU52M3RJbytyazZDNzFxR0w5eDM0bkNGY2t4Z0dqeS84OEwwMkt2ZVR4YlR2WVJVZTYKRTZZSGFUdDY2QUNmYmpNaU"
            + "p6WjVaYjlSUXNGM0IvYkU2cndFODFMc2tMUWlYRFZGaG8wQmp6dlVDUGF6R1d6YgpwSWhsZkhNQ1k4N3VkMGE5NkdNU2FjSlJJc3pZ"
            + "SlQyelFiYUVtVDJKcmJZK2p6NGRoSjhXaVNWYXZTSG5xQWdTClZpRXg2SUtSOHI0L2F1d2hlRGpoaWRrWXdVTXlsUFlVRXpqT00raD"
            + "c2RFZmaWNVRVBWWUtRSFlFTWNiNWo1OFcKY0xiSGNPeWN0MElXem81REF1aFIrMzFFMGFHY2hSbUJuRGd3RkFvY3hvWStHMlRDdkpQ"
            + "WFpYUml0cHM2YjdrQQpmd3pCci9BRUhCY0xLWGZjRXhraHprVlkvbGx0cjB6TkZVRWNEQkhkdmlsb0JQZjZVQXF0THBSTkRVa1hYZn"
            + "M1Cm0xcWtka3lBakp3UllZdjR3QTFDU0ptVExVVHpYOWZCcjlyc3EyM2VvV2JtN0FwT2FYSjRPdnM4VS9HUnV2c0wKbXRaMk1ST2lG"
            + "K1paTVhRNUdlTEkzS2pVcE1KdXlBeXU2UkZITVF2TWlZN2UrUTJvSTB1S1g0WEZYYWhpYUthTwpCZnhONWI3NlRBSFByUGJMVFhqN2"
            + "pOL0FnbVJFQUw4TUFWbEZDMUNpV0I0YzhqV0RLUVcrUWpZQ0tDY1ZDeXltClh1UW5QUjZ0U09NWGY5Z0pQZ0F1dnFHb1lVZHJNZ1h2"
            + "S0xZdjlRS3dJdUNMcmlxU2VQeksvMWR3QXFrSEZpQlMKUzRhSE5nQ2NYWkplcUtHZmlodTAvTnVXK0dFYzNucVFaSCs3cHpoZ29jMW"
            + "gwRU5SNVl0KzUyam54aitqa3diNQpUN1cvOVdGeXc4Y2lkNVkvaWt3UTBhbHl6RUNkZ2lod2l0ZFR6NysrNGZMakFVQklQOCt5RU5v"
            + "NHhsc1F3Y1dBCjU5VWd6ZmlMNlprS3FUc2JBUm5HQW84NFBGQ0ZpQnQySnM2ZEQ1SFJGTUFHRHpVR3ZkOEk3bWtTdnBIN0RpRVQKYU"
            + "pLRWpxK3FtMnZXQ21hVHVmSitkSmY3K1hyMU5xUGFFZEhkMDh3cVVZVE9nMEtjSlVvT2lTcGVQL2hWQkpXSQotLS0tLUVORCBSU0Eg"
            + "UFJJVkFURSBLRVktLS0tLQo=";
        prefs.setPrivateKey(new String(Base64.decode(privateKey)));
        prefs.setPrivateKeyPassword("testkey");
        prefs.setPublicKeyID("abcdefg");
        if (decryptor != null) {
            prefs.setPrivateKeyDecryptor(decryptor);
        }

        BoxDeveloperEditionAPIConnection api = new BoxDeveloperEditionAPIConnection("12345",
            DeveloperEditionEntityType.USER, "foo", "bar", prefs, null);
        api.configureSslCertificatesValidation(new TrustAllTrustManager(), new AcceptAllHostsVerifier());
        api.setBaseURL(baseURL + "/");
        api.setMaxRetryAttempts(expectedNumRetryAttempts);

        return api;
    }

    private void mockFirstResponse(String tokenPath) {
        this.wireMockRule.stubFor(post(urlPathMatching(tokenPath))
            .atPriority(1)
            .inScenario("JWT Retry")
            .whenScenarioStateIs(STARTED)
            .willReturn(aResponse()
                .withStatus(429)
                .withHeader("Retry-After", "1")
                .withHeader("Date", "Sat, 18 Nov 2017 11:18:00 GMT"))
            .willSetStateTo("429 sent"));
    }

    private RequestMatcherExtension getRequestMatcher(final String tokenPath) {
        return new RequestMatcherExtension() {
            @Override
            public MatchResult match(Request request, Parameters parameters) {
                if (!request.getMethod().equals(RequestMethod.POST) || !request.getUrl()
                    .equals(tokenPath)) {
                    return MatchResult.noMatch();
                }

                Assert.assertNotNull("JTI should be saved from previous request",
                    BoxDeveloperEditionAPIConnectionTest.this.jtiClaim);

                try {
                    JwtClaims claims = BoxDeveloperEditionAPIConnectionTest.this
                        .getClaimsFromRequest(request);
                    String jti = claims.getJwtId();
                    long expTimestamp = claims.getExpirationTime().getValue();

                    Assert.assertNotEquals("JWT should have a new timestamp", 1511003910L,
                        expTimestamp);
                    Assert.assertNotEquals("JWT should have a new jti claim",
                        BoxDeveloperEditionAPIConnectionTest.this.jtiClaim, jti);

                } catch (Exception ex) {
                    Assert.fail("Could not parse JWT when request is retried: " + ex.getMessage());
                }

                return MatchResult.exactMatch();
            }
        };
    }

    private JwtClaims getClaimsFromRequest(Request request) throws Exception {

        // Get the JWT out of the request body
        String body = request.getBodyAsString();
        String[] tokens = body.split("&");
        String jwt = null;
        for (String s : tokens) {
            String[] parts = s.split("=");
            if (parts[0] != null && parts[0].equals("assertion") && parts[1] != null) {
                jwt = parts[1];
            }
        }
        if (jwt == null) {
            throw new Exception("No jwt assertion found in request body");
        }

        // Parse out the JWT to verify the claims
        JwtConsumer jwtConsumer = new JwtConsumerBuilder()
            .setSkipSignatureVerification()
            .setSkipAllValidators()
            .build();
        return jwtConsumer.processToClaims(jwt);
    }

    private void mockListener() {
        this.wireMockRule.addMockServiceRequestListener((request, response) -> {
            try {
                JwtClaims claims = BoxDeveloperEditionAPIConnectionTest.this
                    .getClaimsFromRequest(request);

                if (BoxDeveloperEditionAPIConnectionTest.this.jtiClaim == null) {
                    BoxDeveloperEditionAPIConnectionTest.this.jtiClaim = claims.getJwtId();
                }
            } catch (Exception ex) {
                Assert.fail("Could not save JTI claim from request");
            }
        });
    }
}

