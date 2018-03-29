package com.box.sdk;

import com.github.tomakehurst.wiremock.http.RequestListener;
import com.github.tomakehurst.wiremock.http.RequestMethod;
import com.github.tomakehurst.wiremock.http.Response;
import junit.framework.AssertionFailedError;
import org.jose4j.jwt.JwtClaims;
import org.jose4j.jwt.consumer.JwtConsumer;
import org.jose4j.jwt.consumer.JwtConsumerBuilder;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.experimental.categories.Category;

import com.github.tomakehurst.wiremock.junit.WireMockRule;

import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static com.github.tomakehurst.wiremock.stubbing.Scenario.STARTED;

import com.github.tomakehurst.wiremock.extension.Parameters;
import com.github.tomakehurst.wiremock.http.Request;
import com.github.tomakehurst.wiremock.matching.MatchResult;
import com.github.tomakehurst.wiremock.matching.RequestMatcherExtension;

/**
 *
 */
public class BoxDeveloperEditionAPIConnectionTest {


    /**
     * Wiremock
     */
    @Rule
    public WireMockRule wireMockRule = new WireMockRule(53620);

    private String jtiClaim = null;

    @Test
    @Category(UnitTest.class)
    public void retriesWithNewJWTAssertionOnErrorResponse() {

        final String baseURL = "http://localhost:53620";
        final String tokenPath = "/oauth2/token";
        final String accessToken = "mNr1FrCvOeWiGnwLL0OcTL0Lux5jbyBa";

        JWTEncryptionPreferences prefs = new JWTEncryptionPreferences();
        prefs.setEncryptionAlgorithm(EncryptionAlgorithm.RSA_SHA_256);
        // @NOTE(mwiller) 2018-01-16: These are freshly-generated keys which are not used for a real Box
        // account.  They are safe to use in this unit test.
        prefs.setPrivateKey("-----BEGIN RSA PRIVATE KEY-----\n"
                + "Proc-Type: 4,ENCRYPTED\n"
                + "DEK-Info: AES-256-CBC,11600371FE6B320E75D9144A98B00582\n"
                + "\n"
                + "060EECunPUd12iRqq0fxPSPKehzTkhpzxRrhnIKayaqG1Ke3sTVtrFum1tGh5ck7\n"
                + "AdZa2uaJHgkVSKHjJWpQ30RvgR9o+l2KaQ6vi+4ZDtfQV22cgnR2oHAXs8kXsuMD\n"
                + "M8AHjQo9MjjWWqdd4pYrc+9x0qjEZMt5w7rYofVDfu81UUdf/4PXm8dKcfZbDPHy\n"
                + "TlDiHrpTSXWjdVbZrjaFW3yO+c7nF62ez13HP4arhhy0wNtX5TXQKSCMmDt6H91+\n"
                + "tUlXwodzMGnMhaCtBg/nTYRK9VEdlPIRTiiIiWKyWcfx3C1MrBBUjI/iKmPzn5qQ\n"
                + "+In5RpUNqjaYNmhLOBjt4/KG/s3bkPFswIiQV8xR4v+K27YqLMAcV3+i/y9qsEcG\n"
                + "JCg5/ofbrjvuMqW45rcgJrG8aTMNGLS6jm6xfD9boHj415D8q5EdIvp8BxTkpcnp\n"
                + "NWI71vHcjhXbq4HWgQjlOaol3/aIGk+oO8UL4oqRh1rGOiVCcpCjNImZCbjBRU5s\n"
                + "+PXTojGOHgxWppCc+j5pXZY65/zusTrZO/2OhkI89Jn1qJv+LEVzD4k2Ed/rj5dm\n"
                + "9UlHBImiUjJN53iDqS0QibOlasCGy0enu7Iq/n8kQAPog8FoF0CqXxp/33sX+RH2\n"
                + "htwcqYl9sd5QEQiR6pWrT0H7KvMJXkjhjumka35l0+7cQCQtuNoGRo2z/+P3Ih+O\n"
                + "gyxjVfdohKMjT7QNv3tIo+rk6C71qGL9x34nCFckxgGjy/88L02KveTxbTvYRUe6\n"
                + "E6YHaTt66ACfbjMiJzZ5Zb9RQsF3B/bE6rwE81LskLQiXDVFho0BjzvUCPazGWzb\n"
                + "pIhlfHMCY87ud0a96GMSacJRIszYJT2zQbaEmT2JrbY+jz4dhJ8WiSVavSHnqAgS\n"
                + "ViEx6IKR8r4/auwheDjhidkYwUMylPYUEzjOM+h76DVficUEPVYKQHYEMcb5j58W\n"
                + "cLbHcOyct0IWzo5DAuhR+31E0aGchRmBnDgwFAocxoY+G2TCvJPXZXRitps6b7kA\n"
                + "fwzBr/AEHBcLKXfcExkhzkVY/lltr0zNFUEcDBHdviloBPf6UAqtLpRNDUkXXfs5\n"
                + "m1qkdkyAjJwRYYv4wA1CSJmTLUTzX9fBr9rsq23eoWbm7ApOaXJ4Ovs8U/GRuvsL\n"
                + "mtZ2MROiF+ZZMXQ5GeLI3KjUpMJuyAyu6RFHMQvMiY7e+Q2oI0uKX4XFXahiaKaO\n"
                + "BfxN5b76TAHPrPbLTXj7jN/AgmREAL8MAVlFC1CiWB4c8jWDKQW+QjYCKCcVCyym\n"
                + "XuQnPR6tSOMXf9gJPgAuvqGoYUdrMgXvKLYv9QKwIuCLriqSePzK/1dwAqkHFiBS\n"
                + "S4aHNgCcXZJeqKGfihu0/NuW+GEc3nqQZH+7pzhgoc1h0ENR5Yt+52jnxj+jkwb5\n"
                + "T7W/9WFyw8cid5Y/ikwQ0alyzECdgihwitdTz7++4fLjAUBIP8+yENo4xlsQwcWA\n"
                + "59UgzfiL6ZkKqTsbARnGAo84PFCFiBt2Js6dD5HRFMAGDzUGvd8I7mkSvpH7DiET\n"
                + "aJKEjq+qm2vWCmaTufJ+dJf7+Xr1NqPaEdHd08wqUYTOg0KcJUoOiSpeP/hVBJWI\n"
                + "-----END RSA PRIVATE KEY-----\n");
        prefs.setPrivateKeyPassword("testkey");
        prefs.setPublicKeyID("abcdefg");


        BoxDeveloperEditionAPIConnection api = new BoxDeveloperEditionAPIConnection("12345",
                DeveloperEditionEntityType.USER, "foo", "bar", prefs, null);
        api.setBaseURL(baseURL + "/");
        api.setTokenURL(baseURL + tokenPath);
        api.setMaxRequestAttempts(1);

        final String[] jtiClaims = new String[1];


        this.wireMockRule.stubFor(post(urlPathMatching(tokenPath))
            .atPriority(1)
            .inScenario("JWT Retry")
            .whenScenarioStateIs(STARTED)
            .willReturn(aResponse()
                .withStatus(429)
                .withHeader("Retry-After", "1")
                .withHeader("Date", "Sat, 18 Nov 2017 11:18:00 GMT"))
            .willSetStateTo("429 sent"));


        this.wireMockRule.stubFor(requestMatching(new RequestMatcherExtension() {
            @Override
            public MatchResult match(Request request, Parameters parameters) {
                if (!request.getMethod().equals(RequestMethod.POST) || !request.getUrl().equals(tokenPath)) {
                    return MatchResult.noMatch();
                }

                Assert.assertNotNull("JTI should be saved from previous request",
                        BoxDeveloperEditionAPIConnectionTest.this.jtiClaim);

                try {
                    JwtClaims claims = BoxDeveloperEditionAPIConnectionTest.this.getClaimsFromRequest(request);
                    String jti = claims.getJwtId();
                    long expTimestamp = claims.getExpirationTime().getValue();

                    Assert.assertEquals("JWT should have the expected timestamp", 1511003910L, expTimestamp);
                    Assert.assertNotEquals("JWT should have a new jti claim",
                            BoxDeveloperEditionAPIConnectionTest.this.jtiClaim, jti);

                } catch (AssertionFailedError ex) {
                    throw ex;
                } catch (Exception ex) {
                    Assert.fail("Could not parse JWT when request is retried: " + ex.getMessage());
                }

                return MatchResult.exactMatch();
            }
        })
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

        this.wireMockRule.addMockServiceRequestListener(new RequestListener() {
            @Override
            public void requestReceived(Request request, Response response) {
                try {
                    JwtClaims claims = BoxDeveloperEditionAPIConnectionTest.this.getClaimsFromRequest(request);

                    if (BoxDeveloperEditionAPIConnectionTest.this.jtiClaim == null) {
                        BoxDeveloperEditionAPIConnectionTest.this.jtiClaim = claims.getJwtId();
                    }
                } catch (Exception ex) {
                    Assert.fail("Could not save JTI claim from request");
                }
            }
        });

        api.authenticate();

        verify(2, postRequestedFor(urlPathEqualTo("/oauth2/token")));
        Assert.assertEquals(accessToken, api.getAccessToken());
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

}
