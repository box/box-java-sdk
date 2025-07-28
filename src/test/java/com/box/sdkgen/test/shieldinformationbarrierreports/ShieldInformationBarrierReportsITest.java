package com.box.sdkgen.test.shieldinformationbarrierreports;

import static com.box.sdkgen.internal.utils.UtilsManager.convertToString;
import static com.box.sdkgen.internal.utils.UtilsManager.getEnvVar;
import static com.box.sdkgen.test.commons.CommonsManager.getDefaultClientWithUserSubject;
import static com.box.sdkgen.test.commons.CommonsManager.getOrCreateShieldInformationBarrier;

import com.box.sdkgen.client.BoxClient;
import com.box.sdkgen.managers.shieldinformationbarrierreports.GetShieldInformationBarrierReportsQueryParams;
import com.box.sdkgen.schemas.shieldinformationbarrier.ShieldInformationBarrier;
import com.box.sdkgen.schemas.shieldinformationbarrierbase.ShieldInformationBarrierBase;
import com.box.sdkgen.schemas.shieldinformationbarrierbase.ShieldInformationBarrierBaseTypeField;
import com.box.sdkgen.schemas.shieldinformationbarrierreference.ShieldInformationBarrierReference;
import com.box.sdkgen.schemas.shieldinformationbarrierreport.ShieldInformationBarrierReport;
import com.box.sdkgen.schemas.shieldinformationbarrierreports.ShieldInformationBarrierReports;
import org.junit.jupiter.api.Test;

public class ShieldInformationBarrierReportsITest {

  @Test
  public void testShieldInformationBarrierReports() {
    BoxClient client = getDefaultClientWithUserSubject(getEnvVar("USER_ID"));
    String enterpriseId = getEnvVar("ENTERPRISE_ID");
    ShieldInformationBarrier barrier = getOrCreateShieldInformationBarrier(client, enterpriseId);
    assert convertToString(barrier.getStatus()).equals("draft");
    assert convertToString(barrier.getType()).equals("shield_information_barrier");
    assert barrier.getEnterprise().getId().equals(enterpriseId);
    assert convertToString(barrier.getEnterprise().getType()).equals("enterprise");
    String barrierId = barrier.getId();
    ShieldInformationBarrierReports existingReports =
        client
            .getShieldInformationBarrierReports()
            .getShieldInformationBarrierReports(
                new GetShieldInformationBarrierReportsQueryParams(barrierId));
    if (existingReports.getEntries().size() > 0) {
      return;
    }
    ShieldInformationBarrierReport createdReport =
        client
            .getShieldInformationBarrierReports()
            .createShieldInformationBarrierReport(
                new ShieldInformationBarrierReference.Builder()
                    .shieldInformationBarrier(
                        new ShieldInformationBarrierBase.Builder()
                            .id(barrierId)
                            .type(ShieldInformationBarrierBaseTypeField.SHIELD_INFORMATION_BARRIER)
                            .build())
                    .build());
    assert createdReport
        .getShieldInformationBarrier()
        .getShieldInformationBarrier()
        .getId()
        .equals(barrierId);
    ShieldInformationBarrierReport retrievedReport =
        client
            .getShieldInformationBarrierReports()
            .getShieldInformationBarrierReportById(createdReport.getId());
    assert retrievedReport.getId().equals(createdReport.getId());
    ShieldInformationBarrierReports retrievedReports =
        client
            .getShieldInformationBarrierReports()
            .getShieldInformationBarrierReports(
                new GetShieldInformationBarrierReportsQueryParams(barrierId));
    assert retrievedReports.getEntries().size() > 0;
  }
}
