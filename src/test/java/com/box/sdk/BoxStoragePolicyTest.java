package com.box.sdk;

import static com.box.sdk.http.ContentType.APPLICATION_JSON;
import static com.github.tomakehurst.wiremock.client.WireMock.aResponse;
import static com.github.tomakehurst.wiremock.client.WireMock.get;
import static com.github.tomakehurst.wiremock.client.WireMock.urlEqualTo;
import static com.github.tomakehurst.wiremock.core.WireMockConfiguration.wireMockConfig;
import static java.lang.String.format;
import static org.junit.Assert.assertEquals;

import com.github.tomakehurst.wiremock.junit.WireMockRule;
import java.util.Iterator;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

/**
 * {@link BoxStoragePolicy} related tests.
 */
public class BoxStoragePolicyTest {

    @Rule
    public WireMockRule wireMockRule = new WireMockRule(wireMockConfig().dynamicHttpsPort().httpDisabled(true));
    BoxAPIConnection api = TestUtils.getAPIConnection();

    @Before
    public void setUpBaseUrl() {
        api.setMaxRetryAttempts(1);
        api.setBaseURL(format("https://localhost:%d", wireMockRule.httpsPort()));
    }

    @Test
    public void testGetInfoParseAllFieldsCorrectly() {
        final String storagePolicyID = "11";
        final String storagePolicyName = "AWS Frankfurt / AWS Dublin with in region Uploads/Downloads/Previews";

        String result = TestUtils.getFixture("BoxStoragePolicy/Get_A_Storage_Policy_200");

        wireMockRule.stubFor(get(urlEqualTo("/2.0/storage_policies/" + storagePolicyID))
            .willReturn(aResponse()
                .withHeader("Content-Type", APPLICATION_JSON)
                .withBody(result)));

        BoxStoragePolicy storagePolicy = new BoxStoragePolicy(this.api, storagePolicyID);
        BoxStoragePolicy.Info storagePolicyInfo = storagePolicy.getInfo();
        assertEquals(storagePolicyID, storagePolicyInfo.getID());
        assertEquals(storagePolicyName, storagePolicyInfo.getStoragePolicyName());
    }

    @Test
    public void testGetStoragePoliciesParseAllFieldsCorrectly() {
        final String firstStoragePolicyID = "11";
        final String firstStoragePolicyName = "AWS Montreal / AWS Dublin";
        final String secondStoragePolicyID = "22";
        final String secondStoragePolicyName = "AWS Frankfurt / AWS Dublin with in region Uploads/Downloads/Previews";

        String result = TestUtils.getFixture("BoxStoragePolicy/Get_All_Storage_Policies_200");

        wireMockRule.stubFor(get(urlEqualTo("/2.0/storage_policies?limit=100"))
            .willReturn(aResponse()
                .withHeader("Content-Type", APPLICATION_JSON)
                .withBody(result)));

        Iterator<BoxStoragePolicy.Info> storagePolcies = BoxStoragePolicy.getAll(this.api).iterator();
        BoxStoragePolicy.Info firstStoragePolicyInfo = storagePolcies.next();
        assertEquals(firstStoragePolicyID, firstStoragePolicyInfo.getID());
        assertEquals(firstStoragePolicyName, firstStoragePolicyInfo.getStoragePolicyName());

        BoxStoragePolicy.Info secondStoragePolicyInfo = storagePolcies.next();
        assertEquals(secondStoragePolicyID, secondStoragePolicyInfo.getID());
        assertEquals(secondStoragePolicyName, secondStoragePolicyInfo.getStoragePolicyName());
    }
}
