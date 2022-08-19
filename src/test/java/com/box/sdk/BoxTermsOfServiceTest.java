package com.box.sdk;

import static com.github.tomakehurst.wiremock.core.WireMockConfiguration.wireMockConfig;
import static java.lang.String.format;
import static org.junit.Assert.assertEquals;

import com.github.tomakehurst.wiremock.client.WireMock;
import com.github.tomakehurst.wiremock.junit.WireMockRule;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

public class BoxTermsOfServiceTest {

    @Rule
    public WireMockRule wireMockRule = new WireMockRule(wireMockConfig().dynamicPort());
    private final BoxAPIConnection api = TestUtils.getAPIConnection();

    @Before
    public void setUpBaseUrl() {
        api.setMaxRetryAttempts(1);
        api.setBaseURL(format("http://localhost:%d", wireMockRule.port()));
    }

    @Test
    public void testGetAllTermsOfServicesSucceeds() throws IOException {
        final String tosURL = "/2.0/terms_of_services";
        final String firstTosID = "12345";
        final String firstEnterpriseID = "1111";
        final String secondTosID = "42343";
        final String secondEnterpriseID = "2222";

        String result = TestUtils.getFixture("BoxTermsOfService/GetAllTermsOfServices200");

        wireMockRule.stubFor(WireMock.get(WireMock.urlPathEqualTo(tosURL))
            .willReturn(WireMock.aResponse()
                .withHeader("Content-Type", "application/json")
                .withBody(result)));

        List<BoxTermsOfService.Info> termsOfServices = BoxTermsOfService.getAllTermsOfServices(this.api);
        BoxTermsOfService.Info firstTermsOfService = termsOfServices.get(0);

        assertEquals(firstTosID, firstTermsOfService.getID());
        assertEquals(firstEnterpriseID, firstTermsOfService.getEnterprise().getID());

        BoxTermsOfService.Info secondTermsOfService = termsOfServices.get(1);

        assertEquals(secondTosID, secondTermsOfService.getID());
        assertEquals(secondEnterpriseID, secondTermsOfService.getEnterprise().getID());
    }

    @Test
    public void testGetATermsOfServiceInfoSucceeds() throws IOException {
        final String tosID = "12345";
        final String tosURL = "/2.0/terms_of_services/" + tosID;
        final String enterpriseID = "1111";

        String result = TestUtils.getFixture("BoxTermsOfService/GetATermsOfServiceInfo200");

        wireMockRule.stubFor(WireMock.get(WireMock.urlPathEqualTo(tosURL))
            .willReturn(WireMock.aResponse()
                .withHeader("Content-Type", "application/json")
                .withBody(result)));

        BoxTermsOfService termsOfService = new BoxTermsOfService(this.api, tosID);
        BoxTermsOfService.Info tosInfo = termsOfService.getInfo();

        assertEquals(tosID, tosInfo.getID());
        assertEquals(enterpriseID, tosInfo.getEnterprise().getID());
        assertEquals(BoxTermsOfService.TermsOfServiceType.MANAGED, tosInfo.getTosType());
    }

    @RunWith(Parameterized.class)
    public static class EnumValueCheckerTosType {
        private final String inputString;
        private final BoxTermsOfService.TermsOfServiceType expectedResult;

        public EnumValueCheckerTosType(String inputString, BoxTermsOfService.TermsOfServiceType expectedResult) {
            this.inputString = inputString;
            this.expectedResult = expectedResult;
        }

        @Parameterized.Parameters
        public static List<Object[]> enumValues() {
            return Arrays.asList(new Object[][]{
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
        private final String inputString;
        private final BoxTermsOfService.TermsOfServiceStatus expectedResult;

        public EnumValueCheckerTosStatus(String inputString, BoxTermsOfService.TermsOfServiceStatus expectedResult) {
            this.inputString = inputString;
            this.expectedResult = expectedResult;
        }

        @Parameterized.Parameters
        public static List<Object[]> enumValues() {
            return Arrays.asList(new Object[][]{
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
}
