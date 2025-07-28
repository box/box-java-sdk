package com.box.sdkgen.test.shieldinformationbarriersegments;

import static com.box.sdkgen.internal.utils.UtilsManager.convertToString;
import static com.box.sdkgen.internal.utils.UtilsManager.getEnvVar;
import static com.box.sdkgen.internal.utils.UtilsManager.getUuid;
import static com.box.sdkgen.test.commons.CommonsManager.getDefaultClientWithUserSubject;
import static com.box.sdkgen.test.commons.CommonsManager.getOrCreateShieldInformationBarrier;
import static org.junit.jupiter.api.Assertions.assertThrows;

import com.box.sdkgen.client.BoxClient;
import com.box.sdkgen.managers.shieldinformationbarriersegments.CreateShieldInformationBarrierSegmentRequestBody;
import com.box.sdkgen.managers.shieldinformationbarriersegments.GetShieldInformationBarrierSegmentsQueryParams;
import com.box.sdkgen.managers.shieldinformationbarriersegments.UpdateShieldInformationBarrierSegmentByIdRequestBody;
import com.box.sdkgen.schemas.shieldinformationbarrier.ShieldInformationBarrier;
import com.box.sdkgen.schemas.shieldinformationbarrierbase.ShieldInformationBarrierBase;
import com.box.sdkgen.schemas.shieldinformationbarrierbase.ShieldInformationBarrierBaseTypeField;
import com.box.sdkgen.schemas.shieldinformationbarriersegment.ShieldInformationBarrierSegment;
import com.box.sdkgen.schemas.shieldinformationbarriersegments.ShieldInformationBarrierSegments;
import org.junit.jupiter.api.Test;

public class ShieldInformationBarrierSegmentsITest {

  @Test
  public void testShieldInformationBarrierSegments() {
    BoxClient client = getDefaultClientWithUserSubject(getEnvVar("USER_ID"));
    String enterpriseId = getEnvVar("ENTERPRISE_ID");
    ShieldInformationBarrier barrier = getOrCreateShieldInformationBarrier(client, enterpriseId);
    String barrierId = barrier.getId();
    String segmentName = getUuid();
    String segmentDescription = "barrier segment description";
    ShieldInformationBarrierSegment segment =
        client
            .getShieldInformationBarrierSegments()
            .createShieldInformationBarrierSegment(
                new CreateShieldInformationBarrierSegmentRequestBody.Builder(
                        new ShieldInformationBarrierBase.Builder()
                            .id(barrierId)
                            .type(ShieldInformationBarrierBaseTypeField.SHIELD_INFORMATION_BARRIER)
                            .build(),
                        segmentName)
                    .description(segmentDescription)
                    .build());
    assert segment.getName().equals(segmentName);
    ShieldInformationBarrierSegments segments =
        client
            .getShieldInformationBarrierSegments()
            .getShieldInformationBarrierSegments(
                new GetShieldInformationBarrierSegmentsQueryParams(barrierId));
    assert segments.getEntries().size() > 0;
    String segmentId = segment.getId();
    ShieldInformationBarrierSegment segmentFromApi =
        client
            .getShieldInformationBarrierSegments()
            .getShieldInformationBarrierSegmentById(segmentId);
    assert convertToString(segmentFromApi.getType()).equals("shield_information_barrier_segment");
    assert segmentFromApi.getId().equals(segmentId);
    assert segmentFromApi.getName().equals(segmentName);
    assert segmentFromApi.getDescription().equals(segmentDescription);
    assert segmentFromApi.getShieldInformationBarrier().getId().equals(barrierId);
    String updatedSegmentDescription = "updated barrier segment description";
    ShieldInformationBarrierSegment updatedSegment =
        client
            .getShieldInformationBarrierSegments()
            .updateShieldInformationBarrierSegmentById(
                segmentId,
                new UpdateShieldInformationBarrierSegmentByIdRequestBody.Builder()
                    .description(updatedSegmentDescription)
                    .build());
    assert updatedSegment.getDescription().equals(updatedSegmentDescription);
    client
        .getShieldInformationBarrierSegments()
        .deleteShieldInformationBarrierSegmentById(segmentId);
    assertThrows(
        RuntimeException.class,
        () ->
            client
                .getShieldInformationBarrierSegments()
                .getShieldInformationBarrierSegmentById(segmentId));
  }
}
