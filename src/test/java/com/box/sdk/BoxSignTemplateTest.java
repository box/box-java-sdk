package com.box.sdk;

import static com.github.tomakehurst.wiremock.core.WireMockConfiguration.wireMockConfig;
import static java.lang.String.format;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import com.github.tomakehurst.wiremock.client.WireMock;
import com.github.tomakehurst.wiremock.junit.WireMockRule;
import java.util.Iterator;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

public class BoxSignTemplateTest {

  @Rule
  public WireMockRule wireMockRule =
      new WireMockRule(wireMockConfig().dynamicHttpsPort().httpDisabled(true));

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
    final String emailMessage = "Please sign this document.\n\nKind regards,\n\nSomeone";
    final String emailSubject = "Someone has requested your signature on a document";
    final String sourceFileId = "1234567890";
    final String sourceFileVersionId = "1234567890";
    final String signerEmail1 = "user1@mail.com";
    final String signerEmail2 = "user2@mail.com";
    final BoxSignTemplateSigner.BoxSignTemplateSignerInputType signerInputType =
        BoxSignTemplateSigner.BoxSignTemplateSignerInputType.Signature;
    final String signerInputDocumentId = "2fdf9003-d798-40ee-be7f-aaaaaaaaaaa";
    final BoxSignTemplateSigner.BoxSignTemplateSignerInputContentType signerInputContentType =
        BoxSignTemplateSigner.BoxSignTemplateSignerInputContentType.Signature;
    final String readySignLinkUrl =
        "https://app.box.com/sign/ready-sign-link/59917816-c12b-4ef6-8f1d-aaaaaaaaaaa";
    final String readySignLinkName = "contract.pdf";
    final String readySignLinkInstructions = "Hello";
    final String readySignLinkFolderId = "1234567890";
    final boolean readySignLinkIsNotificationDisabled = true;
    final boolean readySignLinkIsActive = true;
    final String customBrandingCompanyName = "Corporation inc.";
    final String customBrandingLogoUri =
        "data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAA\nAAEAAAABCAQAAAC1HAwCAAAAC0lEQVR42mNk+A\n"
            + "8AAQUBAScY42YAAAAASUVORK5CYII=";
    final String customBrandingColor = "9E5E6F";
    final String customBrandingEmailFooterText = "Contact email email@mail.com";
    final Integer daysValid = 0;
    final int additionalInfoNonEditableLength = 0;
    final int additionalInfoRequiredSignersLength = 2;

    final String requestUrl = "/2.0/sign_templates/" + templateId;

    String result = TestUtils.getFixture("BoxSignTemplate/GetSignTemplateInfo200");

    wireMockRule.stubFor(
        WireMock.get(WireMock.urlPathEqualTo(requestUrl))
            .willReturn(
                WireMock.aResponse()
                    .withHeader("Content-Type", "application/json")
                    .withBody(result)));

    BoxSignTemplate template = new BoxSignTemplate(api, templateId);
    BoxSignTemplate.Info info = template.getInfo();

    assertEquals(templateId, info.getID());
    assertEquals(name, info.getName());
    assertEquals(parentFolderId, info.getParentFolder().getID());
    assertEquals(emailMessage, info.getEmailMessage());
    assertEquals(emailSubject, info.getEmailSubject());
    assertEquals(sourceFileId, info.getSourceFiles().get(0).getID());
    assertEquals(sourceFileVersionId, info.getSourceFiles().get(0).getVersion().getID());
    assertEquals(signerEmail1, info.getSigners().get(0).getEmail());
    assertEquals(signerEmail2, info.getSigners().get(1).getEmail());
    assertEquals(signerInputType, info.getSigners().get(1).getInputs().get(0).getType());
    assertEquals(
        signerInputDocumentId, info.getSigners().get(1).getInputs().get(0).getDocumentId());
    assertEquals(
        signerInputContentType, info.getSigners().get(1).getInputs().get(0).getContentType());
    assertEquals(readySignLinkUrl, info.getReadySignLink().getUrl());
    assertEquals(readySignLinkName, info.getReadySignLink().getName());
    assertEquals(readySignLinkInstructions, info.getReadySignLink().getInstructions());
    assertEquals(readySignLinkFolderId, info.getReadySignLink().getFolderID());
    assertEquals(
        readySignLinkIsNotificationDisabled, info.getReadySignLink().getIsNofiticationDisabled());
    assertEquals(readySignLinkIsActive, info.getReadySignLink().getIsActive());
    assertEquals(customBrandingCompanyName, info.getCustomBranding().getCompanyName());
    assertEquals(customBrandingLogoUri, info.getCustomBranding().getLogoUri());
    assertEquals(customBrandingColor, info.getCustomBranding().getBrandingColor());
    assertEquals(customBrandingEmailFooterText, info.getCustomBranding().getEmailFooterText());
    assertEquals(daysValid, info.getDaysValid());
    assertEquals(additionalInfoNonEditableLength, info.getAdditionalInfo().getNonEditable().size());
    assertEquals(
        additionalInfoRequiredSignersLength,
        info.getAdditionalInfo().getRequired().getSigners().size());
    assertTrue(info.getAreFilesLocked());
    assertTrue(info.getAreFieldsLocked());
    assertTrue(info.getAreEmailSettingsLocked());
    assertFalse(info.getAreOptionsLocked());
    assertFalse(info.getAreRecipientsLocked());
  }

  @Test
  public void getAllSignTemplatesSucceeds() {
    final String templateId = "93153068-5420-467b-b8ef-aaaaaaaaaaa";
    final String name = "contract.pdf";
    final String parentFolderId = "1234567890";

    final String requestUrl = "/2.0/sign_templates";
    String result = TestUtils.getFixture("BoxSignTemplate/GetAllSignTemplates200");

    wireMockRule.stubFor(
        WireMock.get(WireMock.urlPathEqualTo(requestUrl))
            .willReturn(
                WireMock.aResponse()
                    .withHeader("Content-Type", "application/json")
                    .withBody(result)));

    Iterator<BoxSignTemplate.Info> iterator = BoxSignTemplate.getAll(api).iterator();
    BoxSignTemplate.Info info = iterator.next();

    assertEquals(templateId, info.getID());
    assertEquals(name, info.getName());
    assertEquals(parentFolderId, info.getParentFolder().getID());
  }
}
