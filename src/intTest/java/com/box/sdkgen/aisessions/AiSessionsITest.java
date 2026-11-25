package com.box.sdkgen.aisessions;

import static com.box.sdkgen.commons.CommonsManager.getDefaultClient;
import static org.junit.jupiter.api.Assertions.assertThrows;

import com.box.sdkgen.client.BoxClient;
import com.box.sdkgen.managers.aisessions.GetAiSessionsV2026R0QueryParams;
import com.box.sdkgen.managers.aisessions.GetAiSessionsV2026R0QueryParamsSourceField;
import com.box.sdkgen.schemas.v2026r0.aisessionsv2026r0.AiSessionsV2026R0;
import org.junit.jupiter.api.Test;

public class AiSessionsITest {

  private static final BoxClient client = getDefaultClient();

  @Test
  public void testAiSessionsWithNonExistentIdV2026R0() {
    String aiSessionId = "123456";
    AiSessionsV2026R0 aiSessions =
        client
            .getAiSessions()
            .getAiSessionsV2026R0(
                new GetAiSessionsV2026R0QueryParams(
                    GetAiSessionsV2026R0QueryParamsSourceField.API));
    assert aiSessions.getEntries().size() >= 0;
    assertThrows(
        RuntimeException.class,
        () -> client.getAiSessions().deleteAiSessionByIdV2026R0(aiSessionId));
  }
}
