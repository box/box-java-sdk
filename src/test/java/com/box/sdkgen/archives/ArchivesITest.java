package com.box.sdkgen.archives;

import static com.box.sdkgen.commons.CommonsManager.getDefaultClientWithUserSubject;
import static com.box.sdkgen.internal.utils.UtilsManager.convertToString;
import static com.box.sdkgen.internal.utils.UtilsManager.getEnvVar;
import static com.box.sdkgen.internal.utils.UtilsManager.getUuid;
import static org.junit.jupiter.api.Assertions.assertThrows;

import com.box.sdkgen.client.BoxClient;
import com.box.sdkgen.managers.archives.CreateArchiveV2025R0RequestBody;
import com.box.sdkgen.managers.archives.GetArchivesV2025R0QueryParams;
import com.box.sdkgen.managers.archives.UpdateArchiveByIdV2025R0RequestBody;
import com.box.sdkgen.schemas.v2025r0.archivesv2025r0.ArchivesV2025R0;
import com.box.sdkgen.schemas.v2025r0.archivev2025r0.ArchiveV2025R0;
import org.junit.jupiter.api.Test;

public class ArchivesITest {

  private static final String userId = getEnvVar("USER_ID");

  private static final BoxClient client = getDefaultClientWithUserSubject(userId);

  @Test
  public void testArchivesCreateListDelete() {
    String archiveName = getUuid();
    String archiveDescription = "Test Archive Description";
    ArchiveV2025R0 archive =
        client
            .getArchives()
            .createArchiveV2025R0(
                new CreateArchiveV2025R0RequestBody.Builder(archiveName)
                    .description(archiveDescription)
                    .build());
    assert convertToString(archive.getType()).equals("archive");
    assert archive.getName().equals(archiveName);
    assert archive.getDescription().equals(archiveDescription);
    String newArchiveName = getUuid();
    String newArchiveDescription = "Updated Archive Description";
    ArchiveV2025R0 updatedArchive =
        client
            .getArchives()
            .updateArchiveByIdV2025R0(
                archive.getId(),
                new UpdateArchiveByIdV2025R0RequestBody.Builder()
                    .name(newArchiveName)
                    .description(newArchiveDescription)
                    .build());
    assert updatedArchive.getName().equals(newArchiveName);
    assert updatedArchive.getDescription().equals(newArchiveDescription);
    ArchivesV2025R0 archives =
        client
            .getArchives()
            .getArchivesV2025R0(new GetArchivesV2025R0QueryParams.Builder().limit(100L).build());
    assert archives.getEntries().size() > 0;
    assertThrows(
        RuntimeException.class,
        () -> client.getArchives().deleteArchiveByIdV2025R0(archive.getId()));
  }
}
