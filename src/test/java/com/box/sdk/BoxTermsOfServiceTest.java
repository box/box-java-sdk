package com.box.sdk;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.text.ParseException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import com.eclipsesource.json.JsonObject;
import com.github.tomakehurst.wiremock.client.WireMock;
import com.github.tomakehurst.wiremock.junit.WireMockRule;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

public class BoxTermsOfServiceTest {

    @Rule
    public final WireMockRule wireMockRule = new WireMockRule(53620);
    private BoxAPIConnection api = TestConfig.getAPIConnection();

    @RunWith(Parameterized.class)
    public static class EnumValueCheckerTosType {
        private String inputString;
        private BoxTermsOfService.TermsOfServiceType expectedResult;

        public EnumValueCheckerTosType(String inputString, BoxTermsOfService.TermsOfServiceType expectedResult) {
            this.inputString = inputString;
            this.expectedResult = expectedResult;
        }

        @Parameterized.Parameters
        public static List<Object[]> enumValues() {
            return Arrays.asList(new Object[][] {
                    {"managed", BoxTermsOfService.TermsOfServiceType.MANAGED},
                    {"external", BoxTermsOfService.TermsOfServiceType.EXTERNAL}
            });
        }

        @Test
        public void termsOfServiceTypeTest() {
            assertEquals(this.expectedResult,
                    BoxTermsOfService.TermsOfServiceType.fromTosType(this.inputString));
        }
    }

    @RunWith(Parameterized.class)
    public static class EnumValueCheckerTosStatus {
        private String inputString;
        private BoxTermsOfService.TermsOfServiceStatus expectedResult;

        public EnumValueCheckerTosStatus(String inputString, BoxTermsOfService.TermsOfServiceStatus expectedResult) {
            this.inputString = inputString;
            this.expectedResult = expectedResult;
        }

        @Parameterized.Parameters
        public static List<Object[]> enumValues() {
            return Arrays.asList(new Object[][] {
                    {"enabled", BoxTermsOfService.TermsOfServiceStatus.ENABLED},
                    {"disabled", BoxTermsOfService.TermsOfServiceStatus.DISABLED}
            });
        }

        @Test
        public void termsOfServiceStatusTest() {
            assertEquals(this.expectedResult,
                    BoxTermsOfService.TermsOfServiceStatus.fromStatus(this.inputString));
        }
    }

    @Test
    @Category(IntegrationTest.class)
    public void getTermsOfServiceInfoSucceeds() {
        final String tosType = "terms_of_service";
        final String tosID = "2778";

        BoxAPIConnection api = new BoxAPIConnection(TestConfig.getAccessToken());
        BoxTermsOfService termsOfService = new BoxTermsOfService(api, "2778");
        BoxTermsOfService.Info tosInfo = termsOfService.getInfo();

        assertNotNull(tosInfo);
        assertEquals(tosType, tosInfo.getType());
        assertEquals(tosID, tosInfo.getID());
    }

    @Test
    @Category(IntegrationTest.class)
    public void getAllTermsOfServicesWithNoParamSucceeds() {
        final String tosType = "terms_of_service";

        BoxAPIConnection api = new BoxAPIConnection(TestConfig.getAccessToken());
        List<BoxTermsOfService.Info> termsOfServicesInfo = BoxTermsOfService.getAllTermsOfServices(api);

        for (BoxTermsOfService.Info info: termsOfServicesInfo) {
            assertNotNull(info);
            assertNotNull(info.getEnterprise());
            assertEquals(tosType, info.getType());
        }
    }

    @Test
    @Category(IntegrationTest.class)
    public void getAllTermsOfServicesWithParamSucceeds() {
        final String type = "terms_of_service";
        final BoxTermsOfService.TermsOfServiceType tosType = BoxTermsOfService.TermsOfServiceType.MANAGED;

        BoxAPIConnection api = new BoxAPIConnection(TestConfig.getAccessToken());
        List<BoxTermsOfService.Info> termsOfServicesInfo = BoxTermsOfService.getAllTermsOfServices(api,
                BoxTermsOfService.TermsOfServiceType.MANAGED);

        for (BoxTermsOfService.Info info: termsOfServicesInfo) {
            assertNotNull(info);
            assertNotNull(info.getEnterprise());
            assertEquals(tosType, info.getTosType());
            assertEquals(type, info.getType());
        }
    }

    @Test
    @Category(IntegrationTest.class)
    public void updateTermsOfServiceInfoSucceeds() {
        BoxAPIConnection api = new BoxAPIConnection(TestConfig.getAccessToken());
        BoxTermsOfService.TermsOfServiceStatus status = BoxTermsOfService.TermsOfServiceStatus.ENABLED;
        final String tosID = "2778";
        String newText = "This is a new text";

        BoxTermsOfService termsOfService = new BoxTermsOfService(api, "2778");
        BoxTermsOfService.Info info = termsOfService.new Info();

        info.setText(newText);
        info.setStatus(status);
        termsOfService.updateInfo(info);

        assertEquals(newText, info.getText());
        assertEquals(status, info.getStatus());
        assertEquals(tosID, info.getID());
    }

    @Test
    @Category(UnitTest.class)
    public void testGetAllTermsOfServicesSucceeds() throws IOException{
        String result = "";
        final String tosURL = "/terms_of_services";
        final String firstTosID = "12345";
        final String firstEnterpriseID = "1111";
        final String secondTosID = "42343";
        final String secondEnterpriseID = "2222";

        result = TestConfig.getFixture("BoxTermsOfService/GetAllTermsOfServices200");

        this.wireMockRule.stubFor(WireMock.get(WireMock.urlPathEqualTo(tosURL))
           .willReturn(WireMock.aResponse()
                   .withHeader("Content-Type", "application/json")
                   .withBody(result)));

        List<BoxTermsOfService.Info> termsOfServices = BoxTermsOfService.getAllTermsOfServices(api);
        BoxTermsOfService.Info firstTermsOfService = termsOfServices.get(0);

        Assert.assertEquals(firstTosID, firstTermsOfService.getID());
        Assert.assertEquals(firstEnterpriseID, firstTermsOfService.getEnterprise().getID());

        BoxTermsOfService.Info secondTermsOfService = termsOfServices.get(1);

        Assert.assertEquals(secondTosID, secondTermsOfService.getID());
        Assert.assertEquals(secondEnterpriseID, secondTermsOfService.getEnterprise().getID());
    }

    @Test
    @Category(UnitTest.class)
    public void testGetATermsOfServiceInfoSucceeds() throws IOException {
        String result = "";
        final String tosID = "12345";
        final String tosURL = "/terms_of_services/" + tosID;
        final String enterpriseID = "1111";
        final String tosType = "managed";

        result = TestConfig.getFixture("BoxTermsOfService/GetATermsOfServiceInfo200");

        this.wireMockRule.stubFor(WireMock.get(WireMock.urlPathEqualTo(tosURL))
           .willReturn(WireMock.aResponse()
                   .withHeader("Content-Type", "application/json")
                   .withBody(result)));

        BoxTermsOfService termsOfService = new BoxTermsOfService(api, tosID);
        BoxTermsOfService.Info tosInfo = termsOfService.getInfo();

        Assert.assertEquals(tosID, tosInfo.getID());
        Assert.assertEquals(enterpriseID, tosInfo.getEnterprise().getID());
        Assert.assertEquals(BoxTermsOfService.TermsOfServiceType.MANAGED, tosInfo.getTosType());
    }
}
