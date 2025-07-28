package com.box.sdkgen.test.hubs;

import static com.box.sdkgen.internal.utils.UtilsManager.convertToString;
import static com.box.sdkgen.internal.utils.UtilsManager.getEnvVar;
import static com.box.sdkgen.internal.utils.UtilsManager.getUuid;
import static com.box.sdkgen.test.commons.CommonsManager.getDefaultClientWithUserSubject;
import static org.junit.jupiter.api.Assertions.assertThrows;

import com.box.sdkgen.client.BoxClient;
import com.box.sdkgen.managers.hubs.GetEnterpriseHubsV2025R0QueryParams;
import com.box.sdkgen.managers.hubs.GetEnterpriseHubsV2025R0QueryParamsDirectionField;
import com.box.sdkgen.managers.hubs.GetHubsV2025R0QueryParams;
import com.box.sdkgen.managers.hubs.GetHubsV2025R0QueryParamsDirectionField;
import com.box.sdkgen.schemas.v2025r0.hubcopyrequestv2025r0.HubCopyRequestV2025R0;
import com.box.sdkgen.schemas.v2025r0.hubcreaterequestv2025r0.HubCreateRequestV2025R0;
import com.box.sdkgen.schemas.v2025r0.hubsv2025r0.HubsV2025R0;
import com.box.sdkgen.schemas.v2025r0.hubupdaterequestv2025r0.HubUpdateRequestV2025R0;
import com.box.sdkgen.schemas.v2025r0.hubv2025r0.HubV2025R0;
import org.junit.jupiter.api.Test;

public class HubsITest {

  private static final BoxClient client = getDefaultClientWithUserSubject(getEnvVar("USER_ID"));

  @Test
  public void testCreateUpdateGetAndDeleteHubs() {
    String hubTitle = getUuid();
    String hubDescription = "new Hub description";
    HubV2025R0 createdHub =
        client
            .getHubs()
            .createHubV2025R0(
                new HubCreateRequestV2025R0.Builder(hubTitle).description(hubDescription).build());
    assert createdHub.getTitle().equals(hubTitle);
    assert createdHub.getDescription().equals(hubDescription);
    assert convertToString(createdHub.getType()).equals("hubs");
    String hubId = createdHub.getId();
    HubsV2025R0 usersHubs =
        client
            .getHubs()
            .getHubsV2025R0(
                new GetHubsV2025R0QueryParams.Builder()
                    .scope("all")
                    .sort("name")
                    .direction(GetHubsV2025R0QueryParamsDirectionField.ASC)
                    .build());
    assert usersHubs.getEntries().size() > 0;
    HubsV2025R0 enterpriseHubs =
        client
            .getHubs()
            .getEnterpriseHubsV2025R0(
                new GetEnterpriseHubsV2025R0QueryParams.Builder()
                    .sort("name")
                    .direction(GetEnterpriseHubsV2025R0QueryParamsDirectionField.ASC)
                    .build());
    assert enterpriseHubs.getEntries().size() > 0;
    HubV2025R0 hubById = client.getHubs().getHubByIdV2025R0(hubId);
    assert hubById.getId().equals(hubId);
    assert hubById.getTitle().equals(hubTitle);
    assert hubById.getDescription().equals(hubDescription);
    assert convertToString(hubById.getType()).equals("hubs");
    String newHubTitle = getUuid();
    String newHubDescription = "updated Hub description";
    HubV2025R0 updatedHub =
        client
            .getHubs()
            .updateHubByIdV2025R0(
                hubId,
                new HubUpdateRequestV2025R0.Builder()
                    .title(newHubTitle)
                    .description(newHubDescription)
                    .build());
    assert updatedHub.getTitle().equals(newHubTitle);
    assert updatedHub.getDescription().equals(newHubDescription);
    client.getHubs().deleteHubByIdV2025R0(hubId);
    assertThrows(RuntimeException.class, () -> client.getHubs().deleteHubByIdV2025R0(hubId));
  }

  @Test
  public void copyHub() {
    String hubTitle = getUuid();
    String hubDescription = "new Hub description";
    HubV2025R0 createdHub =
        client
            .getHubs()
            .createHubV2025R0(
                new HubCreateRequestV2025R0.Builder(hubTitle).description(hubDescription).build());
    String copiedHubTitle = getUuid();
    String copiedHubDescription = "copied Hub description";
    HubV2025R0 copiedHub =
        client
            .getHubs()
            .copyHubV2025R0(
                createdHub.getId(),
                new HubCopyRequestV2025R0.Builder()
                    .title(copiedHubTitle)
                    .description(copiedHubDescription)
                    .build());
    assert !(copiedHub.getId().equals(createdHub.getId()));
    assert copiedHub.getTitle().equals(copiedHubTitle);
    assert copiedHub.getDescription().equals(copiedHubDescription);
    client.getHubs().deleteHubByIdV2025R0(createdHub.getId());
    client.getHubs().deleteHubByIdV2025R0(copiedHub.getId());
  }
}
