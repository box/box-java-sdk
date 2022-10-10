package com.box.sdk;

import static com.box.sdk.http.ContentType.APPLICATION_JSON;
import static com.github.tomakehurst.wiremock.client.WireMock.delete;
import static com.github.tomakehurst.wiremock.client.WireMock.get;
import static com.github.tomakehurst.wiremock.core.WireMockConfiguration.wireMockConfig;
import static java.lang.String.format;
import static org.junit.Assert.assertEquals;

import com.github.tomakehurst.wiremock.client.WireMock;
import com.github.tomakehurst.wiremock.junit.WireMockRule;
import java.util.Iterator;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

/**
 * {@link BoxDevicePin} related unit tests.
 */
public class BoxDevicePinTest {

    @Rule
    public WireMockRule wireMockRule = new WireMockRule(wireMockConfig().dynamicPort());
    private final BoxAPIConnection api = TestUtils.getAPIConnection();

    @Before
    public void setUpBaseUrl() {
        api.setMaxRetryAttempts(1);
        api.setBaseURL(format("http://localhost:%d", wireMockRule.port()));
    }

    @Test
    public void testDeleteDevicePinSendsCorrectRequest() {
        final String devicePinID = "12345";
        final String deleteDevicePinURL = "/2.0/device_pinners/" + devicePinID;

        wireMockRule.stubFor(delete(WireMock.urlPathEqualTo(deleteDevicePinURL))
            .willReturn(WireMock.aResponse()
                .withHeader("Content-Type", APPLICATION_JSON)
                .withStatus(204)));

        BoxDevicePin devicePin = new BoxDevicePin(this.api, devicePinID);
        devicePin.delete();
    }

    @Test
    public void testGetDevicePinInfoSucceeds() {
        final String devicePinID = "12345";
        final String devicePinURL = "/2.0/device_pinners/" + devicePinID;
        final String ownedByUserName = "Test User";
        final String ownedByUserLogin = "test@user.com";
        final String productName = "iPhone";

        String result = TestUtils.getFixture("BoxDevicePin/GetDevicePinInfo200");

        wireMockRule.stubFor(get(WireMock.urlPathEqualTo(devicePinURL))
            .willReturn(WireMock.aResponse()
                .withHeader("Content-Type", APPLICATION_JSON)
                .withBody(result)));

        BoxDevicePin devicePin = new BoxDevicePin(this.api, devicePinID);
        BoxDevicePin.Info devicePinInfo = devicePin.getInfo();

        assertEquals(devicePinID, devicePinInfo.getID());
        assertEquals(ownedByUserName, devicePinInfo.getOwnedBy().getName());
        assertEquals(ownedByUserLogin, devicePinInfo.getOwnedBy().getLogin());
        assertEquals(productName, devicePinInfo.getProductName());
    }

    @Test
    public void testGetAllEnterpriseDevicePinsSucceeds() {
        final String enterpriseID = "1111";
        final String getAllDevicePinsURL = "/2.0/enterprises/" + enterpriseID + "/device_pinners";
        final String firstDevicePinID = "12345";
        final String firstDevicePinProductName = "iPad";
        final String secondDevicePinOwnedByLogin = "example@user.com";

        String result = TestUtils.getFixture("BoxDevicePin/GetAllEnterpriseDevicePins200");

        wireMockRule.stubFor(get(WireMock.urlPathEqualTo(getAllDevicePinsURL))
            .willReturn(WireMock.aResponse()
                .withHeader("Content-Type", APPLICATION_JSON)
                .withBody(result)));

        Iterator<BoxDevicePin.Info> iterator = BoxDevicePin.getEnterpriceDevicePins(this.api, enterpriseID).iterator();
        BoxDevicePin.Info firstDevicePin = iterator.next();

        assertEquals(firstDevicePinID, firstDevicePin.getID());
        assertEquals(firstDevicePinProductName, firstDevicePin.getProductName());

        BoxDevicePin.Info secondDevicePin = iterator.next();

        assertEquals(secondDevicePinOwnedByLogin, secondDevicePin.getOwnedBy().getLogin());
    }
}
