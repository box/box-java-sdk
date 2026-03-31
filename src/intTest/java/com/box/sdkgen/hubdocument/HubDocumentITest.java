package com.box.sdkgen.hubdocument;

import static com.box.sdkgen.commons.CommonsManager.getDefaultClientWithUserSubject;
import static com.box.sdkgen.internal.utils.UtilsManager.convertToString;
import static com.box.sdkgen.internal.utils.UtilsManager.getEnvVar;
import static com.box.sdkgen.internal.utils.UtilsManager.getUuid;

import com.box.sdkgen.client.BoxClient;
import com.box.sdkgen.managers.hubdocument.GetHubDocumentBlocksV2025R0QueryParams;
import com.box.sdkgen.managers.hubdocument.GetHubDocumentPagesV2025R0QueryParams;
import com.box.sdkgen.schemas.v2025r0.hubcreaterequestv2025r0.HubCreateRequestV2025R0;
import com.box.sdkgen.schemas.v2025r0.hubdocumentblockentryv2025r0.HubDocumentBlockEntryV2025R0;
import com.box.sdkgen.schemas.v2025r0.hubdocumentblocksv2025r0.HubDocumentBlocksV2025R0;
import com.box.sdkgen.schemas.v2025r0.hubdocumentpagesv2025r0.HubDocumentPagesV2025R0;
import com.box.sdkgen.schemas.v2025r0.hubdocumentpagev2025r0.HubDocumentPageV2025R0;
import com.box.sdkgen.schemas.v2025r0.hubv2025r0.HubV2025R0;
import org.junit.jupiter.api.Test;

public class HubDocumentITest {

  private static final BoxClient client = getDefaultClientWithUserSubject(getEnvVar("USER_ID"));

  @Test
  public void testGetHubDocumentPagesAndBlocks() {
    String hubTitle = getUuid();
    HubV2025R0 createdHub =
        client.getHubs().createHubV2025R0(new HubCreateRequestV2025R0(hubTitle));
    String hubId = createdHub.getId();
    HubDocumentPagesV2025R0 pages =
        client
            .getHubDocument()
            .getHubDocumentPagesV2025R0(new GetHubDocumentPagesV2025R0QueryParams(hubId));
    assert pages.getEntries().size() > 0;
    assert convertToString(pages.getType()).equals("document_pages");
    HubDocumentPageV2025R0 firstPage = pages.getEntries().get(0);
    assert convertToString(firstPage.getType()).equals("page");
    String pageId = firstPage.getId();
    HubDocumentBlocksV2025R0 blocks =
        client
            .getHubDocument()
            .getHubDocumentBlocksV2025R0(new GetHubDocumentBlocksV2025R0QueryParams(hubId, pageId));
    assert convertToString(blocks.getType()).equals("document_blocks");
    assert blocks.getEntries().size() > 0;
    HubDocumentBlockEntryV2025R0 firstBlock = blocks.getEntries().get(0);
    assert convertToString(firstBlock.getType()).equals("item_list");
    client.getHubs().deleteHubByIdV2025R0(hubId);
  }
}
