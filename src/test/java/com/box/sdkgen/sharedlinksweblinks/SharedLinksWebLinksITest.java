package com.box.sdkgen.sharedlinksweblinks;

import static com.box.sdkgen.commons.CommonsManager.getDefaultClient;
import static com.box.sdkgen.commons.CommonsManager.getDefaultClientWithUserSubject;
import static com.box.sdkgen.internal.utils.UtilsManager.convertToString;
import static com.box.sdkgen.internal.utils.UtilsManager.getEnvVar;
import static com.box.sdkgen.internal.utils.UtilsManager.getUuid;
import static org.junit.jupiter.api.Assertions.assertThrows;

import com.box.sdkgen.client.BoxClient;
import com.box.sdkgen.managers.sharedlinksweblinks.AddShareLinkToWebLinkQueryParams;
import com.box.sdkgen.managers.sharedlinksweblinks.AddShareLinkToWebLinkRequestBody;
import com.box.sdkgen.managers.sharedlinksweblinks.AddShareLinkToWebLinkRequestBodySharedLinkAccessField;
import com.box.sdkgen.managers.sharedlinksweblinks.AddShareLinkToWebLinkRequestBodySharedLinkField;
import com.box.sdkgen.managers.sharedlinksweblinks.FindWebLinkForSharedLinkHeaders;
import com.box.sdkgen.managers.sharedlinksweblinks.FindWebLinkForSharedLinkQueryParams;
import com.box.sdkgen.managers.sharedlinksweblinks.GetSharedLinkForWebLinkQueryParams;
import com.box.sdkgen.managers.sharedlinksweblinks.RemoveSharedLinkFromWebLinkQueryParams;
import com.box.sdkgen.managers.sharedlinksweblinks.RemoveSharedLinkFromWebLinkRequestBody;
import com.box.sdkgen.managers.sharedlinksweblinks.UpdateSharedLinkOnWebLinkQueryParams;
import com.box.sdkgen.managers.sharedlinksweblinks.UpdateSharedLinkOnWebLinkRequestBody;
import com.box.sdkgen.managers.sharedlinksweblinks.UpdateSharedLinkOnWebLinkRequestBodySharedLinkAccessField;
import com.box.sdkgen.managers.sharedlinksweblinks.UpdateSharedLinkOnWebLinkRequestBodySharedLinkField;
import com.box.sdkgen.managers.weblinks.CreateWebLinkRequestBody;
import com.box.sdkgen.managers.weblinks.CreateWebLinkRequestBodyParentField;
import com.box.sdkgen.schemas.folderfull.FolderFull;
import com.box.sdkgen.schemas.weblink.WebLink;
import org.junit.jupiter.api.Test;

public class SharedLinksWebLinksITest {

  private static final BoxClient client = getDefaultClient();

  @Test
  public void testSharedLinksWebLinks() {
    FolderFull parent = client.getFolders().getFolderById("0");
    WebLink webLink =
        client
            .getWebLinks()
            .createWebLink(
                new CreateWebLinkRequestBody.Builder(
                        "https://www.box.com",
                        new CreateWebLinkRequestBodyParentField(parent.getId()))
                    .name(getUuid())
                    .description("Weblink description")
                    .build());
    String webLinkId = webLink.getId();
    client
        .getSharedLinksWebLinks()
        .addShareLinkToWebLink(
            webLinkId,
            new AddShareLinkToWebLinkRequestBody.Builder()
                .sharedLink(
                    new AddShareLinkToWebLinkRequestBodySharedLinkField.Builder()
                        .access(AddShareLinkToWebLinkRequestBodySharedLinkAccessField.OPEN)
                        .password("Secret123@")
                        .build())
                .build(),
            new AddShareLinkToWebLinkQueryParams("shared_link"));
    WebLink webLinkFromApi =
        client
            .getSharedLinksWebLinks()
            .getSharedLinkForWebLink(
                webLinkId, new GetSharedLinkForWebLinkQueryParams("shared_link"));
    assert convertToString(webLinkFromApi.getSharedLink().getAccess()).equals("open");
    String userId = getEnvVar("USER_ID");
    BoxClient userClient = getDefaultClientWithUserSubject(userId);
    WebLink webLinkFromSharedLinkPassword =
        userClient
            .getSharedLinksWebLinks()
            .findWebLinkForSharedLink(
                new FindWebLinkForSharedLinkQueryParams(),
                new FindWebLinkForSharedLinkHeaders(
                    String.join(
                        "",
                        "shared_link=",
                        webLinkFromApi.getSharedLink().getUrl(),
                        "&shared_link_password=Secret123@")));
    assert webLinkId.equals(webLinkFromSharedLinkPassword.getId());
    assertThrows(
        RuntimeException.class,
        () ->
            userClient
                .getSharedLinksWebLinks()
                .findWebLinkForSharedLink(
                    new FindWebLinkForSharedLinkQueryParams(),
                    new FindWebLinkForSharedLinkHeaders(
                        String.join(
                            "",
                            "shared_link=",
                            webLinkFromApi.getSharedLink().getUrl(),
                            "&shared_link_password=incorrectPassword"))));
    WebLink updatedWebLink =
        client
            .getSharedLinksWebLinks()
            .updateSharedLinkOnWebLink(
                webLinkId,
                new UpdateSharedLinkOnWebLinkRequestBody.Builder()
                    .sharedLink(
                        new UpdateSharedLinkOnWebLinkRequestBodySharedLinkField.Builder()
                            .access(
                                UpdateSharedLinkOnWebLinkRequestBodySharedLinkAccessField
                                    .COLLABORATORS)
                            .build())
                    .build(),
                new UpdateSharedLinkOnWebLinkQueryParams("shared_link"));
    assert convertToString(updatedWebLink.getSharedLink().getAccess()).equals("collaborators");
    client
        .getSharedLinksWebLinks()
        .removeSharedLinkFromWebLink(
            webLinkId,
            new RemoveSharedLinkFromWebLinkRequestBody.Builder().sharedLink(null).build(),
            new RemoveSharedLinkFromWebLinkQueryParams("shared_link"));
    WebLink webLinkFromApiAfterRemove =
        client
            .getSharedLinksWebLinks()
            .getSharedLinkForWebLink(
                webLinkId, new GetSharedLinkForWebLinkQueryParams("shared_link"));
    assert webLinkFromApiAfterRemove.getSharedLink() == null;
    client.getWebLinks().deleteWebLinkById(webLinkId);
  }
}
