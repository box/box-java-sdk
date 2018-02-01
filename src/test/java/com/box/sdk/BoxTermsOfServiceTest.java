package com.box.sdk;

import java.util.Arrays;
import java.util.List;
import java.text.ParseException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import com.eclipsesource.json.JsonObject;
import org.junit.Assert;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

public class BoxTermsOfServiceTest {

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
    @Category(UnitTest.class)
    public void testCreateTermsOfServiceInfoParseAllFieldsCorrectly() throws ParseException {
        final String type = "terms_of_service";
        final String id = "2778";
        final BoxTermsOfService.TermsOfServiceStatus status = BoxTermsOfService.TermsOfServiceStatus.DISABLED;
        final String text = "new updated text";
        final BoxTermsOfService.TermsOfServiceType tosType = BoxTermsOfService.TermsOfServiceType.MANAGED;

        final JsonObject fakeJSONResponse = JsonObject.readFrom("{\n"
                + "    \"type\": \"terms_of_service\",\n"
                + "    \"id\": \"2778\",\n"
                + "    \"status\": \"disabled\",\n"
                + "    \"enterprise\": {\n"
                + "        \"type\": \"enterprise\",\n"
                + "        \"id\": \"11111\",\n"
                + "        \"name\": \"Test\"\n"
                + "    },\n"
                + "    \"tos_type\": \"managed\",\n"
                + "    \"text\": \"new updated text\",\n"
                + "    \"created_at\": \"2013-05-16T15:27:57-07:00\",\n"
                + "    \"modified_at\": \"2013-05-16T15:27:57-07:00\"\n"
                + "}");

        BoxAPIConnection api = new BoxAPIConnection("");
        api.setRequestInterceptor(JSONRequestInterceptor.respondWith(fakeJSONResponse));
        BoxTermsOfService.Info termsOfService = BoxTermsOfService.create(api,
                BoxTermsOfService.TermsOfServiceStatus.DISABLED,
                BoxTermsOfService.TermsOfServiceType.MANAGED, text);

        Assert.assertEquals(text, termsOfService.getText());
        Assert.assertEquals(status, termsOfService.getStatus());
        Assert.assertEquals(tosType, termsOfService.getTosType());
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
}
