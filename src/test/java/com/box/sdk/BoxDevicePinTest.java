package com.box.sdk;

import java.io.IOException;
import java.util.Iterator;

import com.github.tomakehurst.wiremock.client.WireMock;
import com.github.tomakehurst.wiremock.junit.WireMockClassRule;
import org.junit.Assert;
import org.junit.ClassRule;
import org.junit.Test;
import org.junit.experimental.categories.Category;

/**
 * {@link BoxDevicePin} related unit tests.
 */
public class BoxDevicePinTest {

    /**
     * Wiremock
     */
    @ClassRule
    public static final WireMockClassRule WIRE_MOCK_CLASS_RULE = new WireMockClassRule(53621);
    private BoxAPIConnection api = TestConfig.getAPIConnection();

    @Test
    @Category(UnitTest.class)
    public void testDeleteDevicePinSendsCorrectRequest() {
        final String devicePinID = "12345";
        final String deleteDevicePinURL = "/device_pinners/" + devicePinID;

        WIRE_MOCK_CLASS_RULE.stubFor(WireMock.delete(WireMock.urlPathEqualTo(deleteDevicePinURL))
                .willReturn(WireMock.aResponse()
                        .withHeader("Content-Type", "application/json")
                        .withStatus(204)));

        BoxDevicePin devicePin = new BoxDevicePin(this.api, devicePinID);
        devicePin.delete();
    }

    @Test
    @Category(UnitTest.class)
    public void testGetDevicePinInfoSucceeds() throws IOException {
        String result = "";
        final String devicePinID = "12345";
        final String devicePinURL = "/device_pinners/" + devicePinID;
        final String ownedByUserName = "Test User";
        final String ownedByUserLogin = "test@user.com";
        final String productName = "iPhone";

        result = TestConfig.getFixture("BoxDevicePin/GetDevicePinInfo200");

        WIRE_MOCK_CLASS_RULE.stubFor(WireMock.get(WireMock.urlPathEqualTo(devicePinURL))
                .willReturn(WireMock.aResponse()
                        .withHeader("Content-Type", "application/json")
                        .withBody(result)));

        BoxDevicePin devicePin = new BoxDevicePin(this.api, devicePinID);
        BoxDevicePin.Info devicePinInfo = devicePin.getInfo();

        Assert.assertEquals(devicePinID, devicePinInfo.getID());
        Assert.assertEquals(ownedByUserName, devicePinInfo.getOwnedBy().getName());
        Assert.assertEquals(ownedByUserLogin, devicePinInfo.getOwnedBy().getLogin());
        Assert.assertEquals(productName, devicePinInfo.getProductName());
    }

    @Test
    @Category(UnitTest.class)
    public void testGetAllEnterpriseDevicePinsSucceeds() throws IOException {
        String result = "";
        final String enterpriseID = "1111";
        final String getAllDevicePinsURL = "/enterprises/" + enterpriseID + "/device_pinners";
        final String firstDevicePinID = "12345";
        final String firstDevicePinProductName = "iPad";
        final String secondDevicePinOwnedByLogin = "example@user.com";

        result = TestConfig.getFixture("BoxDevicePin/GetAllEnterpriseDevicePins200");

        WIRE_MOCK_CLASS_RULE.stubFor(WireMock.get(WireMock.urlPathEqualTo(getAllDevicePinsURL))
                .willReturn(WireMock.aResponse()
                        .withHeader("Content-Type", "application/json")
                        .withBody(result)));

        Iterator<BoxDevicePin.Info> iterator = BoxDevicePin.getEnterpriceDevicePins(this.api, enterpriseID).iterator();
        BoxDevicePin.Info firstDevicePin = iterator.next();

        Assert.assertEquals(firstDevicePinID, firstDevicePin.getID());
        Assert.assertEquals(firstDevicePinProductName, firstDevicePin.getProductName());

        BoxDevicePin.Info secondDevicePin = iterator.next();

        Assert.assertEquals(secondDevicePinOwnedByLogin, secondDevicePin.getOwnedBy().getLogin());
    }
}
