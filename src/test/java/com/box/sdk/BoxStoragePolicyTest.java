package com.box.sdk;

import static com.github.tomakehurst.wiremock.client.WireMock.aResponse;
import static com.github.tomakehurst.wiremock.client.WireMock.get;
import static com.github.tomakehurst.wiremock.client.WireMock.urlEqualTo;
import static org.junit.Assert.assertEquals;

import com.github.tomakehurst.wiremock.junit.WireMockClassRule;
import java.io.IOException;
import java.util.Iterator;
import org.junit.ClassRule;
import org.junit.Test;

/**
 * {@link BoxStoragePolicy} related tests.
 */
public class BoxStoragePolicyTest {

    @ClassRule
    public static final WireMockClassRule WIRE_MOCK_CLASS_RULE = new WireMockClassRule(53621);
    BoxAPIConnection api = TestConfig.getAPIConnection();

    @Test
    public void testGetInfoParseAllFieldsCorrectly() throws IOException {
        final String storagePolicyID = "11";
        final String storagePolicyName = "AWS Frankfurt / AWS Dublin with in region Uploads/Downloads/Previews";

        String result = TestConfig.getFixture("BoxStoragePolicy/Get_A_Storage_Policy_200");

        WIRE_MOCK_CLASS_RULE.stubFor(get(urlEqualTo("/storage_policies/" + storagePolicyID))
            .willReturn(aResponse()
                .withHeader("Content-Type", "application/json")
                .withBody(result)));

        BoxStoragePolicy storagePolicy = new BoxStoragePolicy(this.api, storagePolicyID);
        BoxStoragePolicy.Info storagePolicyInfo = storagePolicy.getInfo();
        assertEquals(storagePolicyID, storagePolicyInfo.getID());
        assertEquals(storagePolicyName, storagePolicyInfo.getStoragePolicyName());
    }

    @Test
    public void testGetStoragePoliciesParseAllFieldsCorrectly() throws IOException {
        final String firstStoragePolicyID = "11";
        final String firstStoragePolicyName = "AWS Montreal / AWS Dublin";
        final String secondStoragePolicyID = "22";
        final String secondStoragePolicyName = "AWS Frankfurt / AWS Dublin with in region Uploads/Downloads/Previews";

        String result = TestConfig.getFixture("BoxStoragePolicy/Get_All_Storage_Policies_200");

        WIRE_MOCK_CLASS_RULE.stubFor(get(urlEqualTo("/storage_policies?limit=100"))
            .willReturn(aResponse()
                .withHeader("Content-Type", "application/json")
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
