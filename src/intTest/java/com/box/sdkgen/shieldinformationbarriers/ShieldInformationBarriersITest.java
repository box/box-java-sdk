package com.box.sdkgen.shieldinformationbarriers;

import static com.box.sdkgen.commons.CommonsManager.getDefaultClientWithUserSubject;
import static com.box.sdkgen.commons.CommonsManager.getOrCreateShieldInformationBarrier;
import static com.box.sdkgen.internal.utils.UtilsManager.convertToString;
import static com.box.sdkgen.internal.utils.UtilsManager.getEnvVar;
import static org.junit.jupiter.api.Assertions.assertThrows;

import com.box.sdkgen.client.BoxClient;
import com.box.sdkgen.managers.shieldinformationbarriers.UpdateShieldInformationBarrierStatusRequestBody;
import com.box.sdkgen.managers.shieldinformationbarriers.UpdateShieldInformationBarrierStatusRequestBodyStatusField;
import com.box.sdkgen.schemas.shieldinformationbarrier.ShieldInformationBarrier;
import com.box.sdkgen.schemas.shieldinformationbarriers.ShieldInformationBarriers;
import org.junit.jupiter.api.Test;

public class ShieldInformationBarriersITest {

  @Test
  public void testShieldInformationBarriers() {
    BoxClient client = getDefaultClientWithUserSubject(getEnvVar("USER_ID"));
    String enterpriseId = getEnvVar("ENTERPRISE_ID");
    ShieldInformationBarrier barrier = getOrCreateShieldInformationBarrier(client, enterpriseId);
    assert convertToString(barrier.getStatus()).equals("draft");
    assert convertToString(barrier.getType()).equals("shield_information_barrier");
    assert barrier.getEnterprise().getId().equals(enterpriseId);
    assert convertToString(barrier.getEnterprise().getType()).equals("enterprise");
    String barrierId = barrier.getId();
    ShieldInformationBarrier barrierFromApi =
        client.getShieldInformationBarriers().getShieldInformationBarrierById(barrierId);
    assert barrierFromApi.getId().equals(barrierId);
    ShieldInformationBarriers barriers =
        client.getShieldInformationBarriers().getShieldInformationBarriers();
    assert barriers.getEntries().size() == 1;
    assertThrows(
        RuntimeException.class,
        () ->
            client
                .getShieldInformationBarriers()
                .updateShieldInformationBarrierStatus(
                    new UpdateShieldInformationBarrierStatusRequestBody(
                        barrierId,
                        UpdateShieldInformationBarrierStatusRequestBodyStatusField.DISABLED)));
  }
}
