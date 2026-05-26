package com.box.sdkgen.notes;

import static com.box.sdkgen.commons.CommonsManager.getDefaultClientWithUserSubject;
import static com.box.sdkgen.internal.utils.UtilsManager.convertToString;
import static com.box.sdkgen.internal.utils.UtilsManager.getEnvVar;
import static com.box.sdkgen.internal.utils.UtilsManager.getUuid;

import com.box.sdkgen.client.BoxClient;
import com.box.sdkgen.schemas.filefull.FileFull;
import com.box.sdkgen.schemas.v2026r0.folderreferencev2026r0.FolderReferenceV2026R0;
import com.box.sdkgen.schemas.v2026r0.notesconvertrequestbodyv2026r0.NotesConvertRequestBodyV2026R0;
import com.box.sdkgen.schemas.v2026r0.notesconvertrequestbodyv2026r0.NotesConvertRequestBodyV2026R0ContentFormatField;
import com.box.sdkgen.schemas.v2026r0.notesconvertresponsev2026r0.NotesConvertResponseV2026R0;
import org.junit.jupiter.api.Test;

public class NotesITest {

  private static final BoxClient client = getDefaultClientWithUserSubject(getEnvVar("USER_ID"));

  @Test
  public void testConvertMarkdownToBoxNote() {
    String noteName = getUuid();
    String markdownContent = "# Heading\n\nSome text";
    NotesConvertResponseV2026R0 response =
        client
            .getNotes()
            .createNoteConvertV2026R0(
                new NotesConvertRequestBodyV2026R0.Builder(
                        markdownContent, new FolderReferenceV2026R0("0"), noteName)
                    .contentFormat(NotesConvertRequestBodyV2026R0ContentFormatField.MARKDOWN)
                    .build());
    assert !(response.getId().equals(""));
    assert convertToString(response.getType()).equals("file");
    FileFull file = client.getFiles().getFileById(response.getId());
    assert file.getName().equals(String.join("", noteName, ".boxnote"));
    assert file.getParent().getId().equals("0");
    client.getFiles().deleteFileById(response.getId());
  }
}
