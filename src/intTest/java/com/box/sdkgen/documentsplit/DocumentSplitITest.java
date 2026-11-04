package com.box.sdkgen.documentsplit;

import static com.box.sdkgen.commons.CommonsManager.createNewFolder;
import static com.box.sdkgen.commons.CommonsManager.getDefaultClient;
import static com.box.sdkgen.commons.CommonsManager.uploadNewFile;
import static com.box.sdkgen.internal.utils.UtilsManager.convertToString;
import static com.box.sdkgen.internal.utils.UtilsManager.getUuid;

import com.box.sdkgen.client.BoxClient;
import com.box.sdkgen.managers.documentsplit.GetDocumentSplitsV2026R0QueryParams;
import com.box.sdkgen.managers.folders.DeleteFolderByIdQueryParams;
import com.box.sdkgen.schemas.filefull.FileFull;
import com.box.sdkgen.schemas.folderfull.FolderFull;
import com.box.sdkgen.schemas.v2026r0.documentsplitcreaterequestmanualv2026r0.DocumentSplitCreateRequestManualV2026R0;
import com.box.sdkgen.schemas.v2026r0.documentsplitcreaterequestmanualv2026r0.DocumentSplitCreateRequestManualV2026R0SplitTypeField;
import com.box.sdkgen.schemas.v2026r0.documentsplitcreaterequestv2026r0.DocumentSplitCreateRequestV2026R0;
import com.box.sdkgen.schemas.v2026r0.documentsplitcreaterequestv2026r0.DocumentSplitCreateRequestV2026R0InputSourceField;
import com.box.sdkgen.schemas.v2026r0.documentsplitcreateresponsev2026r0.DocumentSplitCreateResponseV2026R0;
import com.box.sdkgen.schemas.v2026r0.documentsplitpagerangev2026r0.DocumentSplitPageRangeV2026R0;
import com.box.sdkgen.schemas.v2026r0.documentsplitsv2026r0.DocumentSplitsV2026R0;
import com.box.sdkgen.schemas.v2026r0.documentsplitv2026r0.DocumentSplitV2026R0;
import java.util.Arrays;
import org.junit.jupiter.api.Test;

public class DocumentSplitITest {

  private static final BoxClient client = getDefaultClient();

  @Test
  public void testCreateDocumentSplitV2026R0() {
    FileFull file = uploadNewFile();
    FolderFull folder = createNewFolder();
    String externalId = getUuid();
    DocumentSplitCreateResponseV2026R0 createdDocumentSplit =
        client
            .getDocumentSplit()
            .createDocumentSplitV2026R0(
                new DocumentSplitCreateRequestV2026R0.Builder(
                        file.getId(),
                        folder.getId(),
                        DocumentSplitCreateRequestV2026R0InputSourceField.API,
                        new DocumentSplitCreateRequestManualV2026R0.Builder(
                                Arrays.asList(new DocumentSplitPageRangeV2026R0(1, 1)))
                            .splitType(DocumentSplitCreateRequestManualV2026R0SplitTypeField.MANUAL)
                            .build())
                    .externalId(externalId)
                    .build());
    assert !(createdDocumentSplit.getId().equals(""));
    assert convertToString(createdDocumentSplit.getType()).equals("document_split");
    DocumentSplitV2026R0 documentSplit =
        client.getDocumentSplit().getDocumentSplitByIdV2026R0(createdDocumentSplit.getId());
    assert documentSplit.getId().equals(createdDocumentSplit.getId());
    assert convertToString(documentSplit.getType()).equals("document_split");
    assert documentSplit.getSourceFile().getId().equals(file.getId());
    assert documentSplit.getDestinationFolder().getId().equals(folder.getId());
    assert convertToString(documentSplit.getInputSource()).equals("api");
    assert !(convertToString(documentSplit.getStatus()).equals(""));
    assert documentSplit.getExternalId().equals(externalId);
    client
        .getFolders()
        .deleteFolderById(
            folder.getId(), new DeleteFolderByIdQueryParams.Builder().recursive(true).build());
    client.getFiles().deleteFileById(file.getId());
  }

  @Test
  public void testGetDocumentSplitsV2026R0() {
    DocumentSplitsV2026R0 documentSplits =
        client
            .getDocumentSplit()
            .getDocumentSplitsV2026R0(
                new GetDocumentSplitsV2026R0QueryParams.Builder().limit(10L).build());
    assert documentSplits.getEntries().size() >= 0;
  }
}
