package com.box.sdkgen.test.shieldinformationbarriersegmentmembers;

import static com.box.sdkgen.internal.utils.UtilsManager.getEnvVar;
import static com.box.sdkgen.internal.utils.UtilsManager.getUuid;
import static com.box.sdkgen.test.commons.CommonsManager.getDefaultClientWithUserSubject;
import static com.box.sdkgen.test.commons.CommonsManager.getOrCreateShieldInformationBarrier;
import static org.junit.jupiter.api.Assertions.assertThrows;

import com.box.sdkgen.client.BoxClient;
import com.box.sdkgen.managers.shieldinformationbarriersegmentmembers.CreateShieldInformationBarrierSegmentMemberRequestBody;
import com.box.sdkgen.managers.shieldinformationbarriersegmentmembers.CreateShieldInformationBarrierSegmentMemberRequestBodyShieldInformationBarrierSegmentField;
import com.box.sdkgen.managers.shieldinformationbarriersegmentmembers.CreateShieldInformationBarrierSegmentMemberRequestBodyShieldInformationBarrierSegmentTypeField;
import com.box.sdkgen.managers.shieldinformationbarriersegmentmembers.GetShieldInformationBarrierSegmentMembersQueryParams;
import com.box.sdkgen.managers.shieldinformationbarriersegments.CreateShieldInformationBarrierSegmentRequestBody;
import com.box.sdkgen.schemas.shieldinformationbarrier.ShieldInformationBarrier;
import com.box.sdkgen.schemas.shieldinformationbarrierbase.ShieldInformationBarrierBase;
import com.box.sdkgen.schemas.shieldinformationbarrierbase.ShieldInformationBarrierBaseTypeField;
import com.box.sdkgen.schemas.shieldinformationbarriersegment.ShieldInformationBarrierSegment;
import com.box.sdkgen.schemas.shieldinformationbarriersegmentmember.ShieldInformationBarrierSegmentMember;
import com.box.sdkgen.schemas.shieldinformationbarriersegmentmembers.ShieldInformationBarrierSegmentMembers;
import com.box.sdkgen.schemas.userbase.UserBase;
import org.junit.jupiter.api.Test;

public class ShieldInformationBarrierSegmentMembersITest {

  @Test
  public void testShieldInformationBarrierSegmentMembers() {
    BoxClient client = getDefaultClientWithUserSubject(getEnvVar("USER_ID"));
    String enterpriseId = getEnvVar("ENTERPRISE_ID");
    ShieldInformationBarrier barrier = getOrCreateShieldInformationBarrier(client, enterpriseId);
    String barrierId = barrier.getId();
    String segmentName = getUuid();
    ShieldInformationBarrierSegment segment =
        client
            .getShieldInformationBarrierSegments()
            .createShieldInformationBarrierSegment(
                new CreateShieldInformationBarrierSegmentRequestBody(
                    new ShieldInformationBarrierBase.Builder()
                        .id(barrierId)
                        .type(ShieldInformationBarrierBaseTypeField.SHIELD_INFORMATION_BARRIER)
                        .build(),
                    segmentName));
    assert segment.getName().equals(segmentName);
    ShieldInformationBarrierSegmentMember segmentMember =
        client
            .getShieldInformationBarrierSegmentMembers()
            .createShieldInformationBarrierSegmentMember(
                new CreateShieldInformationBarrierSegmentMemberRequestBody(
                    new CreateShieldInformationBarrierSegmentMemberRequestBodyShieldInformationBarrierSegmentField
                            .Builder()
                        .id(segment.getId())
                        .type(
                            CreateShieldInformationBarrierSegmentMemberRequestBodyShieldInformationBarrierSegmentTypeField
                                .SHIELD_INFORMATION_BARRIER_SEGMENT)
                        .build(),
                    new UserBase(getEnvVar("USER_ID"))));
    assert segmentMember.getUser().getId().equals(getEnvVar("USER_ID"));
    assert segmentMember.getShieldInformationBarrierSegment().getId().equals(segment.getId());
    ShieldInformationBarrierSegmentMembers segmentMembers =
        client
            .getShieldInformationBarrierSegmentMembers()
            .getShieldInformationBarrierSegmentMembers(
                new GetShieldInformationBarrierSegmentMembersQueryParams(segment.getId()));
    assert segmentMembers.getEntries().size() > 0;
    ShieldInformationBarrierSegmentMember segmentMemberGet =
        client
            .getShieldInformationBarrierSegmentMembers()
            .getShieldInformationBarrierSegmentMemberById(segmentMember.getId());
    assert segmentMemberGet.getId().equals(segmentMember.getId());
    client
        .getShieldInformationBarrierSegmentMembers()
        .deleteShieldInformationBarrierSegmentMemberById(segmentMember.getId());
    assertThrows(
        RuntimeException.class,
        () ->
            client
                .getShieldInformationBarrierSegmentMembers()
                .getShieldInformationBarrierSegmentMemberById(segmentMember.getId()));
    client
        .getShieldInformationBarrierSegments()
        .deleteShieldInformationBarrierSegmentById(segment.getId());
  }
}
