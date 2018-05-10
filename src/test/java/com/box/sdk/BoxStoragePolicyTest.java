package com.box.sdk;

import java.io.IOException;
import java.util.Iterator;

import com.github.tomakehurst.wiremock.junit.WireMockClassRule;
import org.junit.Assert;
import org.junit.ClassRule;
import org.junit.Test;
import org.junit.experimental.categories.Category;

import static com.github.tomakehurst.wiremock.client.WireMock.*;

/**
 * {@link BoxStoragePolicy} related tests.
 */
public class BoxStoragePolicyTest {

    @ClassRule
    public static final WireMockClassRule WIRE_MOCK_CLASS_RULE = new WireMockClassRule(53621);
    BoxAPIConnection api = TestConfig.getAPIConnection();

    @Test
    @Category(UnitTest.class)
    public void testGetInfoParseAllFieldsCorrectly() throws IOException {
        String result = "";
        final String storagePolicyID = "11";
        final String storagePolicyName = "AWS Frankfurt / AWS Dublin with in region Uploads/Downloads/Previews";

        result = TestConfig.getFixture("BoxStoragePolicy/Get_A_Storage_Policy_200");

        WIRE_MOCK_CLASS_RULE.stubFor(get(urlEqualTo("/storage_policies/" + storagePolicyID))
                .willReturn(aResponse()
                        .withHeader("Content-Type", "application/json")
                        .withBody(result)));

        BoxStoragePolicy storagePolicy = new BoxStoragePolicy(this.api, storagePolicyID);
        BoxStoragePolicy.Info storagePolicyInfo = storagePolicy.getInfo();
        Assert.assertEquals(storagePolicyID, storagePolicyInfo.getID());
        Assert.assertEquals(storagePolicyName, storagePolicyInfo.getStoragePolicyName());
    }

    @Test
    @Category(UnitTest.class)
    public void testGetStoragePoliciesParseAllFieldsCorrectly() throws IOException {
        String result = "";
        final String storagePolicyType = "storage_policy";
        final String firstStoragePolicyID = "11";
        final String firstStoragePolicyName = "AWS Montreal / AWS Dublin";
        final String secondStoragePolicyID = "22";
        final String secondStoragePolicyName = "AWS Frankfurt / AWS Dublin with in region Uploads/Downloads/Previews";

        result = TestConfig.getFixture("BoxStoragePolicy/Get_All_Storage_Policies_200");

        WIRE_MOCK_CLASS_RULE.stubFor(get(urlEqualTo("/storage_policies?limit=100"))
                .willReturn(aResponse()
                        .withHeader("Content-Type", "application/json")
                        .withBody(result)));

        Iterator<BoxStoragePolicy.Info> storagePolcies = BoxStoragePolicy.getAll(this.api).iterator();
        BoxStoragePolicy.Info firstStoragePolicyInfo = storagePolcies.next();
        Assert.assertEquals(firstStoragePolicyID, firstStoragePolicyInfo.getID());
        Assert.assertEquals(firstStoragePolicyName, firstStoragePolicyInfo.getStoragePolicyName());

        BoxStoragePolicy.Info secondStoragePolicyInfo = storagePolcies.next();
        Assert.assertEquals(secondStoragePolicyID, secondStoragePolicyInfo.getID());
        Assert.assertEquals(secondStoragePolicyName, secondStoragePolicyInfo.getStoragePolicyName());
    }
}
