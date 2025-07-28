package com.box.sdkgen.test.fileversionlegalholds;

import static com.box.sdkgen.test.commons.CommonsManager.getDefaultClient;
import static org.junit.jupiter.api.Assertions.assertThrows;

import com.box.sdkgen.client.BoxClient;
import com.box.sdkgen.managers.fileversionlegalholds.GetFileVersionLegalHoldsQueryParams;
import com.box.sdkgen.schemas.fileversionlegalholds.FileVersionLegalHolds;
import org.junit.jupiter.api.Test;

public class FileVersionLegalHoldsITest {

  private static final BoxClient client = getDefaultClient();

  @Test
  public void testGetFileVersionLegalHolds() {
    String policyId = "1234567890";
    FileVersionLegalHolds fileVersionLegalHolds =
        client
            .getFileVersionLegalHolds()
            .getFileVersionLegalHolds(new GetFileVersionLegalHoldsQueryParams(policyId));
    int fileVersionLegalHoldsCount = fileVersionLegalHolds.getEntries().size();
    assert fileVersionLegalHoldsCount >= 0;
  }

  @Test
  public void testGetFileVersionLegalHoldById() {
    String fileVersionLegalHoldId = "987654321";
    assertThrows(
        RuntimeException.class,
        () ->
            client.getFileVersionLegalHolds().getFileVersionLegalHoldById(fileVersionLegalHoldId));
  }
}
