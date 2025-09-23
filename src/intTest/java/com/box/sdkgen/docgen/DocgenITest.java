package com.box.sdkgen.docgen;

import static com.box.sdkgen.commons.CommonsManager.createNewFolder;
import static com.box.sdkgen.commons.CommonsManager.getDefaultClient;
import static com.box.sdkgen.commons.CommonsManager.uploadNewFile;
import static com.box.sdkgen.internal.utils.UtilsManager.convertToString;
import static com.box.sdkgen.internal.utils.UtilsManager.entryOf;
import static com.box.sdkgen.internal.utils.UtilsManager.mapOf;

import com.box.sdkgen.client.BoxClient;
import com.box.sdkgen.managers.docgen.GetDocgenJobsV2025R0QueryParams;
import com.box.sdkgen.schemas.filefull.FileFull;
import com.box.sdkgen.schemas.folderfull.FolderFull;
import com.box.sdkgen.schemas.v2025r0.docgenbatchbasev2025r0.DocGenBatchBaseV2025R0;
import com.box.sdkgen.schemas.v2025r0.docgenbatchcreaterequestv2025r0.DocGenBatchCreateRequestV2025R0;
import com.box.sdkgen.schemas.v2025r0.docgenbatchcreaterequestv2025r0.DocGenBatchCreateRequestV2025R0DestinationFolderField;
import com.box.sdkgen.schemas.v2025r0.docgendocumentgenerationdatav2025r0.DocGenDocumentGenerationDataV2025R0;
import com.box.sdkgen.schemas.v2025r0.docgenjobfullv2025r0.DocGenJobFullV2025R0;
import com.box.sdkgen.schemas.v2025r0.docgenjobsfullv2025r0.DocGenJobsFullV2025R0;
import com.box.sdkgen.schemas.v2025r0.docgenjobsv2025r0.DocGenJobsV2025R0;
import com.box.sdkgen.schemas.v2025r0.docgenjobv2025r0.DocGenJobV2025R0;
import com.box.sdkgen.schemas.v2025r0.docgentemplatebasev2025r0.DocGenTemplateBaseV2025R0;
import com.box.sdkgen.schemas.v2025r0.docgentemplatecreaterequestv2025r0.DocGenTemplateCreateRequestV2025R0;
import com.box.sdkgen.schemas.v2025r0.filereferencev2025r0.FileReferenceV2025R0;
import java.util.Arrays;
import org.junit.jupiter.api.Test;

public class DocgenITest {

  private static final BoxClient client = getDefaultClient();

  @Test
  public void testDocgenBatchAndJobs() {
    FileFull uploadedFile = uploadNewFile();
    FolderFull folder = createNewFolder();
    DocGenTemplateBaseV2025R0 createdDocgenTemplate =
        client
            .getDocgenTemplate()
            .createDocgenTemplateV2025R0(
                new DocGenTemplateCreateRequestV2025R0(
                    new FileReferenceV2025R0(uploadedFile.getId())));
    DocGenBatchBaseV2025R0 docgenBatch =
        client
            .getDocgen()
            .createDocgenBatchV2025R0(
                new DocGenBatchCreateRequestV2025R0(
                    new FileReferenceV2025R0(uploadedFile.getId()),
                    "api",
                    new DocGenBatchCreateRequestV2025R0DestinationFolderField(folder.getId()),
                    "pdf",
                    Arrays.asList(
                        new DocGenDocumentGenerationDataV2025R0(
                            "test", mapOf(entryOf("abc", "xyz"))))));
    assert !(docgenBatch.getId().equals(""));
    assert convertToString(docgenBatch.getType()).equals("docgen_batch");
    DocGenJobsV2025R0 docgenBatchJobs =
        client.getDocgen().getDocgenBatchJobByIdV2025R0(docgenBatch.getId());
    assert docgenBatchJobs.getEntries().size() >= 1;
    assert !(docgenBatchJobs.getEntries().get(0).getId().equals(""));
    assert convertToString(docgenBatchJobs.getEntries().get(0).getType()).equals("docgen_job");
    assert docgenBatchJobs.getEntries().get(0).getOutputType().equals("pdf");
    assert !(convertToString(docgenBatchJobs.getEntries().get(0).getStatus()).equals(""));
    assert docgenBatchJobs
        .getEntries()
        .get(0)
        .getTemplateFile()
        .getId()
        .equals(uploadedFile.getId());
    assert docgenBatchJobs.getEntries().get(0).getBatch().getId().equals(docgenBatch.getId());
    DocGenJobsFullV2025R0 docgenJobs =
        client
            .getDocgen()
            .getDocgenJobsV2025R0(
                new GetDocgenJobsV2025R0QueryParams.Builder().limit(10000L).build());
    assert docgenJobs.getEntries().size() >= 1;
    assert !(docgenJobs.getEntries().get(0).getBatch().getId().equals(""));
    assert !(docgenJobs.getEntries().get(0).getCreatedBy().getId().equals(""));
    assert !(docgenJobs.getEntries().get(0).getEnterprise().getId().equals(""));
    assert !(docgenJobs.getEntries().get(0).getId().equals(""));
    assert !(docgenJobs.getEntries().get(0).getOutputType().equals(""));
    assert !(docgenJobs.getEntries().get(0).getSource().equals(""));
    assert !(convertToString(docgenJobs.getEntries().get(0).getStatus()).equals(""));
    assert convertToString(docgenJobs.getEntries().get(0).getTemplateFile().getType())
        .equals("file");
    assert !(docgenJobs.getEntries().get(0).getTemplateFile().getId().equals(""));
    assert convertToString(docgenJobs.getEntries().get(0).getTemplateFileVersion().getType())
        .equals("file_version");
    assert !(docgenJobs.getEntries().get(0).getTemplateFileVersion().getId().equals(""));
    assert convertToString(docgenJobs.getEntries().get(0).getType()).equals("docgen_job");
    int indexOfItem = docgenJobs.getEntries().size() - 1;
    DocGenJobFullV2025R0 docgenJobItemFromList = docgenJobs.getEntries().get(indexOfItem);
    DocGenJobV2025R0 docgenJob =
        client.getDocgen().getDocgenJobByIdV2025R0(docgenJobItemFromList.getId());
    assert !(docgenJob.getBatch().getId().equals(""));
    assert !(docgenJob.getId().equals(""));
    assert !(docgenJob.getOutputType().equals(""));
    assert !(convertToString(docgenJob.getStatus()).equals(""));
    assert convertToString(docgenJob.getTemplateFile().getType()).equals("file");
    assert !(docgenJob.getTemplateFile().getId().equals(""));
    assert convertToString(docgenJob.getTemplateFileVersion().getType()).equals("file_version");
    assert !(docgenJob.getTemplateFileVersion().getId().equals(""));
    assert convertToString(docgenJob.getType()).equals("docgen_job");
    client.getFolders().deleteFolderById(folder.getId());
    client.getFiles().deleteFileById(uploadedFile.getId());
  }
}
