package com.box.sdkgen.devicepinners;

import static com.box.sdkgen.commons.CommonsManager.getDefaultClient;
import static com.box.sdkgen.internal.utils.UtilsManager.getEnvVar;
import static org.junit.jupiter.api.Assertions.assertThrows;

import com.box.sdkgen.client.BoxClient;
import com.box.sdkgen.managers.devicepinners.GetEnterpriseDevicePinnersQueryParams;
import com.box.sdkgen.managers.devicepinners.GetEnterpriseDevicePinnersQueryParamsDirectionField;
import com.box.sdkgen.schemas.devicepinners.DevicePinners;
import org.junit.jupiter.api.Test;

public class DevicePinnersITest {

  private static final BoxClient client = getDefaultClient();

  @Test
  public void testDevicePinners() {
    String enterpriseId = getEnvVar("ENTERPRISE_ID");
    DevicePinners devicePinners =
        client.getDevicePinners().getEnterpriseDevicePinners(enterpriseId);
    assert devicePinners.getEntries().size() >= 0;
    DevicePinners devicePinnersInDescDirection =
        client
            .getDevicePinners()
            .getEnterpriseDevicePinners(
                enterpriseId,
                new GetEnterpriseDevicePinnersQueryParams.Builder()
                    .direction(GetEnterpriseDevicePinnersQueryParamsDirectionField.DESC)
                    .build());
    assert devicePinnersInDescDirection.getEntries().size() >= 0;
    String devicePinnerId = "123456";
    assertThrows(
        RuntimeException.class,
        () -> client.getDevicePinners().getDevicePinnerById(devicePinnerId));
    assertThrows(
        RuntimeException.class,
        () -> client.getDevicePinners().deleteDevicePinnerById(devicePinnerId));
  }
}
