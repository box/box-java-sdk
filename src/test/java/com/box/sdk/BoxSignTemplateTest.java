package com.box.sdk;


import com.github.tomakehurst.wiremock.client.WireMock;
import com.github.tomakehurst.wiremock.junit.WireMockRule;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import java.util.Iterator;

import static com.github.tomakehurst.wiremock.core.WireMockConfiguration.wireMockConfig;
import static java.lang.String.format;
import static org.junit.Assert.assertEquals;

public class BoxSignTemplateTest {

    @Rule
    public WireMockRule wireMockRule = new WireMockRule(wireMockConfig().dynamicHttpsPort().httpDisabled(true));
    private final BoxAPIConnection api = TestUtils.getAPIConnection();

    @Before
    public void setUpBaseUrl() {
        api.setMaxRetryAttempts(1);
        api.setBaseURL(format("https://localhost:%d", wireMockRule.httpsPort()));
    }

    @Test
    public void getSignTemplateInfoSucceeds() {
        final String templateId = "93153068-5420-467b-b8ef-aaaaaaaaaaa";
        final String name = "contract.pdf";
        final String parentFolderId = "1234567890";

        final String requestUrl = "/2.0/sign_templates/" + templateId;

        String result = TestUtils.getFixture("BoxSignTemplate/GetSignTemplateInfo200");

        wireMockRule.stubFor(WireMock.get(WireMock.urlPathEqualTo(requestUrl))
            .willReturn(WireMock.aResponse()
                .withHeader("Content-Type", "application/json")
                .withBody(result)));

        BoxSignTemplate template = new BoxSignTemplate(api, templateId);
        BoxSignTemplate.Info info = template.getInfo();

        assertEquals(templateId, info.getID());
        assertEquals(name, info.getName());
        assertEquals(parentFolderId, info.getParentFolder().getID());
    }

    @Test
    public void getAllSignTemplatesSucceeds() {
        final String templateId = "93153068-5420-467b-b8ef-aaaaaaaaaaa";
        final String name = "contract.pdf";
        final String parentFolderId = "1234567890";

        final String requestUrl = "/2.0/sign_templates";
        String result = TestUtils.getFixture("BoxSignTemplate/GetAllSignTemplates200");

        wireMockRule.stubFor(WireMock.get(WireMock.urlPathEqualTo(requestUrl))
            .willReturn(WireMock.aResponse()
                .withHeader("Content-Type", "application/json")
                .withBody(result)));

        Iterator<BoxSignTemplate.Info> iterator = BoxSignTemplate.getAll(api).iterator();
        BoxSignTemplate.Info info = iterator.next();

        assertEquals(templateId, info.getID());
        assertEquals(name, info.getName());
        assertEquals(parentFolderId, info.getParentFolder().getID());
    }
}
