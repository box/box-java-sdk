package com.box.sdk;

import static com.github.tomakehurst.wiremock.client.WireMock.aResponse;
import static com.github.tomakehurst.wiremock.client.WireMock.post;
import static com.github.tomakehurst.wiremock.client.WireMock.postRequestedFor;
import static com.github.tomakehurst.wiremock.client.WireMock.requestMatching;
import static com.github.tomakehurst.wiremock.client.WireMock.urlPathEqualTo;
import static com.github.tomakehurst.wiremock.client.WireMock.urlPathMatching;
import static com.github.tomakehurst.wiremock.client.WireMock.verify;
import static com.github.tomakehurst.wiremock.stubbing.Scenario.STARTED;

import com.github.tomakehurst.wiremock.extension.Parameters;
import com.github.tomakehurst.wiremock.http.Request;
import com.github.tomakehurst.wiremock.http.RequestListener;
import com.github.tomakehurst.wiremock.http.RequestMethod;
import com.github.tomakehurst.wiremock.http.Response;
import com.github.tomakehurst.wiremock.junit.WireMockRule;
import com.github.tomakehurst.wiremock.matching.MatchResult;
import com.github.tomakehurst.wiremock.matching.RequestMatcherExtension;
import org.bouncycastle.util.encoders.Base64;
import org.jose4j.jwt.JwtClaims;
import org.jose4j.jwt.consumer.JwtConsumer;
import org.jose4j.jwt.consumer.JwtConsumerBuilder;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;

public class BoxDeveloperEditionAPIConnectionTest {

    @Rule
    public WireMockRule wireMockRule = new WireMockRule(53620);

    private String jtiClaim = null;

    @Test
    public void retriesWithNewJWTAssertionOnErrorResponseAndFails() {
        final String tokenPath = "/oauth2/token";
        BoxDeveloperEditionAPIConnection api = this.getBoxDeveloperEditionAPIConnection(tokenPath);

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
            Assert.assertEquals(429, e.getResponseCode());
        }
    }

    @Test
    public void retriesWithNewJWTAssertionOnErrorResponseAndSucceeds() {
        final String tokenPath = "/oauth2/token";
        final String accessToken = "mNr1FrCvOeWiGnwLL0OcTL0Lux5jbyBa";
        BoxDeveloperEditionAPIConnection api = this.getBoxDeveloperEditionAPIConnection(tokenPath);

        this.mockFirstResponse(tokenPath);

        this.wireMockRule.stubFor(requestMatching(this.getRequestMatcher(tokenPath))
            .atPriority(2)
            .inScenario("JWT Retry")
            .whenScenarioStateIs("429 sent")
            .willReturn(aResponse()
                .withStatus(200)
                .withHeader("Content-Type", "application/json")
                .withBody("{\n"
                    + "   \"access_token\": \"" + accessToken + "\",\n"
                    + "   \"expires_in\": 4169,\n"
                    + "   \"restricted_to\": [],\n"
                    + "   \"token_type\": \"bearer\"\n"
                    + "}")));

        this.mockListener();

        api.authenticate();

        verify(2, postRequestedFor(urlPathEqualTo("/oauth2/token")));
        Assert.assertEquals(accessToken, api.getAccessToken());
    }

    @Test
    public void retriesWithNewJWTAssertionOnClockSkewErrorResponseAndSucceeds() {
        final String tokenPath = "/oauth2/token";
        final String accessToken = "some_token";
        BoxDeveloperEditionAPIConnection api = this.getBoxDeveloperEditionAPIConnection(tokenPath);

        this.wireMockRule.stubFor(post(urlPathMatching(tokenPath))
            .atPriority(1)
            .inScenario("JWT Retry")
            .whenScenarioStateIs(STARTED)
            .willReturn(aResponse()
                .withStatus(400)
                .withHeader("Retry-After", "1")
                .withHeader("Date", "Sat, 18 Nov 2017 11:18:00 GMT")
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
                .withHeader("Content-Type", "application/json")
                .withBody("{\n"
                    + "   \"access_token\": \"" + accessToken + "\",\n"
                    + "   \"expires_in\": 4169,\n"
                    + "   \"restricted_to\": [],\n"
                    + "   \"token_type\": \"bearer\"\n"
                    + "}")));

        this.mockListener();

        api.authenticate();

        verify(2, postRequestedFor(urlPathEqualTo("/oauth2/token")));
        Assert.assertEquals(accessToken, api.getAccessToken());
    }

    private BoxDeveloperEditionAPIConnection getBoxDeveloperEditionAPIConnection(final String tokenPath) {
        final String baseURL = "http://localhost:53620";
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


        BoxDeveloperEditionAPIConnection api = new BoxDeveloperEditionAPIConnection("12345",
            DeveloperEditionEntityType.USER, "foo", "bar", prefs, null);
        api.setBaseURL(baseURL + "/");
        api.setTokenURL(baseURL + tokenPath);
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
        this.wireMockRule.addMockServiceRequestListener(new RequestListener() {
            @Override
            public void requestReceived(Request request, Response response) {
                try {
                    JwtClaims claims = BoxDeveloperEditionAPIConnectionTest.this
                        .getClaimsFromRequest(request);

                    if (BoxDeveloperEditionAPIConnectionTest.this.jtiClaim == null) {
                        BoxDeveloperEditionAPIConnectionTest.this.jtiClaim = claims.getJwtId();
                    }
                } catch (Exception ex) {
                    Assert.fail("Could not save JTI claim from request");
                }
            }
        });
    }
}

