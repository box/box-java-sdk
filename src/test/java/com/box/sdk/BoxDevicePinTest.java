package com.box.sdk;

import java.io.IOException;
import java.text.ParseException;
import java.util.Date;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.lang.Iterable;

import com.github.tomakehurst.wiremock.client.WireMock;
import com.github.tomakehurst.wiremock.junit.WireMockRule;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.experimental.categories.Category;

import static com.github.tomakehurst.wiremock.client.WireMock.aResponse;
import static com.github.tomakehurst.wiremock.client.WireMock.delete;
import static com.github.tomakehurst.wiremock.client.WireMock.deleteRequestedFor;
import static com.github.tomakehurst.wiremock.client.WireMock.equalToJson;
import static com.github.tomakehurst.wiremock.client.WireMock.post;
import static com.github.tomakehurst.wiremock.client.WireMock.postRequestedFor;
import static com.github.tomakehurst.wiremock.client.WireMock.stubFor;
import static com.github.tomakehurst.wiremock.client.WireMock.urlEqualTo;
import static com.github.tomakehurst.wiremock.client.WireMock.verify;

import com.eclipsesource.json.JsonObject;

/**
 * {@link BoxDevicePin} related unit tests.
 */
public class BoxDevicePinTest {

    /**
     * Wiremock
     */
    @Rule
    public WireMockRule wireMockRule = new WireMockRule(53620);
    private BoxAPIConnection api = TestConfig.getAPIConnection();

    @Test
    @Category(UnitTest.class)
    public void testDeleteDevicePinSendsCorrectRequest() {
        final String devicePinID = "12345";
        final String deleteDevicePinURL = "/device_pinners/" + devicePinID;

        this.wireMockRule.stubFor(WireMock.delete(WireMock.urlPathEqualTo(deleteDevicePinURL))
                .willReturn(WireMock.aResponse()
                        .withHeader("Content-Type", "application/json")
                        .withStatus(204)));

        BoxDevicePin devicePin = new BoxDevicePin(api, devicePinID);
        devicePin.delete();
    }

    @Test
    @Category(UnitTest.class)
    public void testGetDevicePinInfoSucceeds() throws IOException{
        String result = "";
        final String devicePinID = "12345";
        final String devicePinURL = "/device_pinners/" + devicePinID;
        final String ownedByUserName = "Test User";
        final String ownedByUserLogin = "test@user.com";
        final String productName = "iPhone";

        result = TestConfig.getFixture("BoxDevicePin/GetDevicePinInfo200");

        this.wireMockRule.stubFor(WireMock.get(WireMock.urlPathEqualTo(devicePinURL))
                .willReturn(WireMock.aResponse()
                        .withHeader("Content-Type", "application/json")
                        .withBody(result)));

        BoxDevicePin devicePin = new BoxDevicePin(api, devicePinID);
        BoxDevicePin.Info devicePinInfo = devicePin.getInfo();

        Assert.assertEquals(devicePinID, devicePinInfo.getID());
        Assert.assertEquals(ownedByUserName, devicePinInfo.getOwnedBy().getName());
        Assert.assertEquals(ownedByUserLogin, devicePinInfo.getOwnedBy().getLogin());
        Assert.assertEquals(productName, devicePinInfo.getProductName());
    }

    @Test
    @Category(UnitTest.class)
    public void testGetAllEnterpriseDevicePinsSucceeds() throws IOException{
        String result = "";
        final String enterpriseID = "1111";
        final String getAllDevicePinsURL = "/enterprises/" + enterpriseID + "/device_pinners";
        final String firstDevicePinID = "12345";
        final String firstDevicePinProductName = "iPad";
        final String secondDevicePinOwnedByLogin = "example@user.com";

        result = TestConfig.getFixture("BoxDevicePin/GetAllEnterpriseDevicePins200");

        this.wireMockRule.stubFor(WireMock.get(WireMock.urlPathEqualTo(getAllDevicePinsURL))
                .willReturn(WireMock.aResponse()
                        .withHeader("Content-Type", "application/json")
                        .withBody(result)));

        Iterator<BoxDevicePin.Info> iterator = BoxDevicePin.getEnterpriceDevicePins(api, enterpriseID).iterator();
        BoxDevicePin.Info firstDevicePin = iterator.next();

        Assert.assertEquals(firstDevicePinID, firstDevicePin.getID());
        Assert.assertEquals(firstDevicePinProductName, firstDevicePin.getProductName());

        BoxDevicePin.Info secondDevicePin = iterator.next();

        Assert.assertEquals(secondDevicePinOwnedByLogin, secondDevicePin.getOwnedBy().getLogin());
    }
}
