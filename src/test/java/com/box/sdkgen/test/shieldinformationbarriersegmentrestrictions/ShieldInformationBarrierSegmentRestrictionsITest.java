package com.box.sdkgen.test.shieldinformationbarriersegmentrestrictions;

import static com.box.sdkgen.internal.utils.UtilsManager.getEnvVar;
import static com.box.sdkgen.internal.utils.UtilsManager.getUuid;
import static com.box.sdkgen.test.commons.CommonsManager.getDefaultClientWithUserSubject;
import static com.box.sdkgen.test.commons.CommonsManager.getOrCreateShieldInformationBarrier;
import static org.junit.jupiter.api.Assertions.assertThrows;

import com.box.sdkgen.client.BoxClient;
import com.box.sdkgen.managers.shieldinformationbarriersegmentrestrictions.CreateShieldInformationBarrierSegmentRestrictionRequestBody;
import com.box.sdkgen.managers.shieldinformationbarriersegmentrestrictions.CreateShieldInformationBarrierSegmentRestrictionRequestBodyRestrictedSegmentField;
import com.box.sdkgen.managers.shieldinformationbarriersegmentrestrictions.CreateShieldInformationBarrierSegmentRestrictionRequestBodyRestrictedSegmentTypeField;
import com.box.sdkgen.managers.shieldinformationbarriersegmentrestrictions.CreateShieldInformationBarrierSegmentRestrictionRequestBodyShieldInformationBarrierSegmentField;
import com.box.sdkgen.managers.shieldinformationbarriersegmentrestrictions.CreateShieldInformationBarrierSegmentRestrictionRequestBodyShieldInformationBarrierSegmentTypeField;
import com.box.sdkgen.managers.shieldinformationbarriersegmentrestrictions.CreateShieldInformationBarrierSegmentRestrictionRequestBodyTypeField;
import com.box.sdkgen.managers.shieldinformationbarriersegmentrestrictions.GetShieldInformationBarrierSegmentRestrictionsQueryParams;
import com.box.sdkgen.managers.shieldinformationbarriersegments.CreateShieldInformationBarrierSegmentRequestBody;
import com.box.sdkgen.schemas.shieldinformationbarrier.ShieldInformationBarrier;
import com.box.sdkgen.schemas.shieldinformationbarrierbase.ShieldInformationBarrierBase;
import com.box.sdkgen.schemas.shieldinformationbarrierbase.ShieldInformationBarrierBaseTypeField;
import com.box.sdkgen.schemas.shieldinformationbarriersegment.ShieldInformationBarrierSegment;
import com.box.sdkgen.schemas.shieldinformationbarriersegmentrestriction.ShieldInformationBarrierSegmentRestriction;
import com.box.sdkgen.schemas.shieldinformationbarriersegmentrestrictions.ShieldInformationBarrierSegmentRestrictions;
import org.junit.jupiter.api.Test;

public class ShieldInformationBarrierSegmentRestrictionsITest {

  @Test
  public void testShieldInformationBarrierSegmentRestrictions() {
    BoxClient client = getDefaultClientWithUserSubject(getEnvVar("USER_ID"));
    String enterpriseId = getEnvVar("ENTERPRISE_ID");
    ShieldInformationBarrier barrier = getOrCreateShieldInformationBarrier(client, enterpriseId);
    String barrierId = barrier.getId();
    ShieldInformationBarrierSegment segment =
        client
            .getShieldInformationBarrierSegments()
            .createShieldInformationBarrierSegment(
                new CreateShieldInformationBarrierSegmentRequestBody.Builder(
                        new ShieldInformationBarrierBase.Builder()
                            .id(barrierId)
                            .type(ShieldInformationBarrierBaseTypeField.SHIELD_INFORMATION_BARRIER)
                            .build(),
                        getUuid())
                    .description("barrier segment description")
                    .build());
    String segmentId = segment.getId();
    ShieldInformationBarrierSegment segmentToRestrict =
        client
            .getShieldInformationBarrierSegments()
            .createShieldInformationBarrierSegment(
                new CreateShieldInformationBarrierSegmentRequestBody.Builder(
                        new ShieldInformationBarrierBase.Builder()
                            .id(barrierId)
                            .type(ShieldInformationBarrierBaseTypeField.SHIELD_INFORMATION_BARRIER)
                            .build(),
                        getUuid())
                    .description("barrier segment description")
                    .build());
    String segmentToRestrictId = segmentToRestrict.getId();
    ShieldInformationBarrierSegmentRestriction segmentRestriction =
        client
            .getShieldInformationBarrierSegmentRestrictions()
            .createShieldInformationBarrierSegmentRestriction(
                new CreateShieldInformationBarrierSegmentRestrictionRequestBody.Builder(
                        new CreateShieldInformationBarrierSegmentRestrictionRequestBodyShieldInformationBarrierSegmentField
                                .Builder()
                            .id(segmentId)
                            .type(
                                CreateShieldInformationBarrierSegmentRestrictionRequestBodyShieldInformationBarrierSegmentTypeField
                                    .SHIELD_INFORMATION_BARRIER_SEGMENT)
                            .build(),
                        new CreateShieldInformationBarrierSegmentRestrictionRequestBodyRestrictedSegmentField
                                .Builder()
                            .id(segmentToRestrictId)
                            .type(
                                CreateShieldInformationBarrierSegmentRestrictionRequestBodyRestrictedSegmentTypeField
                                    .SHIELD_INFORMATION_BARRIER_SEGMENT)
                            .build())
                    .type(
                        CreateShieldInformationBarrierSegmentRestrictionRequestBodyTypeField
                            .SHIELD_INFORMATION_BARRIER_SEGMENT_RESTRICTION)
                    .build());
    String segmentRestrictionId = segmentRestriction.getId();
    assert segmentRestriction.getShieldInformationBarrierSegment().getId().equals(segmentId);
    ShieldInformationBarrierSegmentRestrictions segmentRestrictions =
        client
            .getShieldInformationBarrierSegmentRestrictions()
            .getShieldInformationBarrierSegmentRestrictions(
                new GetShieldInformationBarrierSegmentRestrictionsQueryParams(segmentId));
    assert segmentRestrictions.getEntries().size() > 0;
    ShieldInformationBarrierSegmentRestriction segmentRestrictionFromApi =
        client
            .getShieldInformationBarrierSegmentRestrictions()
            .getShieldInformationBarrierSegmentRestrictionById(segmentRestrictionId);
    assert segmentRestrictionFromApi.getId().equals(segmentRestrictionId);
    assert segmentRestrictionFromApi.getShieldInformationBarrierSegment().getId().equals(segmentId);
    assert segmentRestrictionFromApi.getRestrictedSegment().getId().equals(segmentToRestrictId);
    assert segmentRestrictionFromApi.getShieldInformationBarrier().getId().equals(barrierId);
    client
        .getShieldInformationBarrierSegmentRestrictions()
        .deleteShieldInformationBarrierSegmentRestrictionById(segmentRestrictionId);
    assertThrows(
        RuntimeException.class,
        () ->
            client
                .getShieldInformationBarrierSegmentRestrictions()
                .getShieldInformationBarrierSegmentRestrictionById(segmentRestrictionId));
    client
        .getShieldInformationBarrierSegments()
        .deleteShieldInformationBarrierSegmentById(segmentId);
    client
        .getShieldInformationBarrierSegments()
        .deleteShieldInformationBarrierSegmentById(segmentToRestrictId);
  }
}
