package com.box.sdkgen.shuttle;

import static com.box.sdkgen.commons.CommonsManager.getDefaultClientWithUserSubject;
import static com.box.sdkgen.internal.utils.UtilsManager.convertToString;
import static com.box.sdkgen.internal.utils.UtilsManager.getEnvVar;
import static com.box.sdkgen.internal.utils.UtilsManager.getUuid;
import static org.junit.jupiter.api.Assertions.assertThrows;

import com.box.sdkgen.client.BoxClient;
import com.box.sdkgen.managers.shuttle.GetShuttleDiscoveredSubjectsV2026R0QueryParams;
import com.box.sdkgen.managers.shuttle.GetShuttleJobSchedulesV2026R0QueryParams;
import com.box.sdkgen.managers.shuttle.GetShuttleMappingErrorsV2026R0QueryParams;
import com.box.sdkgen.managers.shuttle.GetShuttlePathEntriesV2026R0QueryParams;
import com.box.sdkgen.managers.shuttle.GetShuttlePathEntriesV2026R0QueryParamsSideField;
import com.box.sdkgen.managers.shuttle.GetShuttlePathMappingsV2026R0QueryParams;
import com.box.sdkgen.managers.shuttle.GetShuttleReportsV2026R0QueryParams;
import com.box.sdkgen.managers.shuttle.GetShuttleRunsV2026R0QueryParams;
import com.box.sdkgen.managers.shuttle.GetShuttleSubjectMappingsV2026R0QueryParams;
import com.box.sdkgen.schemas.v2026r0.shuttleagentcredentialsv2026r0.ShuttleAgentCredentialsV2026R0;
import com.box.sdkgen.schemas.v2026r0.shuttlejobcreaterequestv2026r0.ShuttleJobCreateRequestV2026R0;
import com.box.sdkgen.schemas.v2026r0.shuttlejobcreaterequestv2026r0.ShuttleJobCreateRequestV2026R0JobTypeField;
import com.box.sdkgen.schemas.v2026r0.shuttlejobschedulecreaterequestv2026r0.ShuttleJobScheduleCreateRequestV2026R0;
import com.box.sdkgen.schemas.v2026r0.shuttlejobschedulecreaterequestv2026r0.ShuttleJobScheduleCreateRequestV2026R0RunModeField;
import com.box.sdkgen.schemas.v2026r0.shuttlejobschedulecreaterequestv2026r0.ShuttleJobScheduleCreateRequestV2026R0StateField;
import com.box.sdkgen.schemas.v2026r0.shuttlejobscheduletriggerbytimev2026r0.ShuttleJobScheduleTriggerByTimeV2026R0;
import com.box.sdkgen.schemas.v2026r0.shuttlejobscheduletriggerbytimev2026r0.ShuttleJobScheduleTriggerByTimeV2026R0RepeatField;
import com.box.sdkgen.schemas.v2026r0.shuttlejobscheduletriggerbytimev2026r0.ShuttleJobScheduleTriggerByTimeV2026R0TypeField;
import com.box.sdkgen.schemas.v2026r0.shuttlejobscheduleupdaterequestv2026r0.ShuttleJobScheduleUpdateRequestV2026R0;
import com.box.sdkgen.schemas.v2026r0.shuttlejobscheduleupdaterequestv2026r0.ShuttleJobScheduleUpdateRequestV2026R0RunModeField;
import com.box.sdkgen.schemas.v2026r0.shuttlejobsv2026r0.ShuttleJobsV2026R0;
import com.box.sdkgen.schemas.v2026r0.shuttlejobupdaterequestv2026r0.ShuttleJobUpdateRequestV2026R0;
import com.box.sdkgen.schemas.v2026r0.shuttlepathmappingaccountinputv2026r0.ShuttlePathMappingAccountInputV2026R0;
import com.box.sdkgen.schemas.v2026r0.shuttlepathmappingaccountlocationinputv2026r0.ShuttlePathMappingAccountLocationInputV2026R0;
import com.box.sdkgen.schemas.v2026r0.shuttlepathmappingaccountlocationinputv2026r0.ShuttlePathMappingAccountLocationInputV2026R0TypeField;
import com.box.sdkgen.schemas.v2026r0.shuttlepathmappingcreaterequestv2026r0.ShuttlePathMappingCreateRequestV2026R0;
import com.box.sdkgen.schemas.v2026r0.shuttlepathmappingimportcreaterequestv2026r0.ShuttlePathMappingImportCreateRequestV2026R0;
import com.box.sdkgen.schemas.v2026r0.shuttlepathmappingupdaterequestv2026r0.ShuttlePathMappingUpdateRequestV2026R0;
import com.box.sdkgen.schemas.v2026r0.shuttlereportcreaterequestv2026r0.ShuttleReportCreateRequestV2026R0;
import com.box.sdkgen.schemas.v2026r0.shuttlereportcreaterequestv2026r0.ShuttleReportCreateRequestV2026R0FormatField;
import com.box.sdkgen.schemas.v2026r0.shuttlerunsv2026r0.ShuttleRunsV2026R0;
import com.box.sdkgen.schemas.v2026r0.shuttlesubjectmappingcreaterequestv2026r0.ShuttleSubjectMappingCreateRequestV2026R0;
import com.box.sdkgen.schemas.v2026r0.shuttlesubjectmappingimportcreaterequestv2026r0.ShuttleSubjectMappingImportCreateRequestV2026R0;
import com.box.sdkgen.schemas.v2026r0.shuttlesubjectmappingupdaterequestv2026r0.ShuttleSubjectMappingUpdateRequestV2026R0;
import com.box.sdkgen.schemas.v2026r0.shuttlesystemcreaterequestv2026r0.ShuttleSystemCreateRequestV2026R0;
import com.box.sdkgen.schemas.v2026r0.shuttlesystemcreaterequestv2026r0.ShuttleSystemCreateRequestV2026R0SystemTypeField;
import com.box.sdkgen.schemas.v2026r0.shuttlesystemsv2026r0.ShuttleSystemsV2026R0;
import com.box.sdkgen.schemas.v2026r0.shuttlesystemupdaterequestv2026r0.ShuttleSystemUpdateRequestV2026R0;
import com.box.sdkgen.schemas.v2026r0.shuttlesystemv2026r0.ShuttleSystemV2026R0;
import java.util.Arrays;
import org.junit.jupiter.api.Test;

public class ShuttleITest {

  private static final BoxClient client = getDefaultClientWithUserSubject(getEnvVar("USER_ID"));

  @Test
  public void testCreateGetUpdateListDeleteShuttleSystemV2026R0() {
    String systemName = getUuid();
    ShuttleSystemV2026R0 createdSystem =
        client
            .getShuttle()
            .createShuttleSystemV2026R0(
                new ShuttleSystemCreateRequestV2026R0.Builder(systemName)
                    .systemType(ShuttleSystemCreateRequestV2026R0SystemTypeField.WINDOWS)
                    .build());
    assert createdSystem.getName().equals(systemName);
    assert createdSystem.getSystemType().equals("windows");
    assert convertToString(createdSystem.getType()).equals("shuttle_system");
    ShuttleSystemV2026R0 systemFromApi =
        client.getShuttle().getShuttleSystemByIdV2026R0(createdSystem.getId());
    assert systemFromApi.getId().equals(createdSystem.getId());
    assert systemFromApi.getName().equals(systemName);
    String updatedSystemName = getUuid();
    ShuttleSystemV2026R0 updatedSystem =
        client
            .getShuttle()
            .updateShuttleSystemByIdV2026R0(
                createdSystem.getId(),
                new ShuttleSystemUpdateRequestV2026R0.Builder().name(updatedSystemName).build());
    assert updatedSystem.getName().equals(updatedSystemName);
    ShuttleSystemsV2026R0 systems = client.getShuttle().getShuttleSystemsV2026R0();
    assert systems.getEntries().size() > 0;
    ShuttleAgentCredentialsV2026R0 credentials =
        client.getShuttle().createShuttleSystemIssueRegistrationTokenV2026R0(createdSystem.getId());
    assert !(credentials.getRegistrationToken().equals(""));
    client.getShuttle().deleteShuttleSystemByIdV2026R0(createdSystem.getId());
    assertThrows(
        RuntimeException.class,
        () -> client.getShuttle().getShuttleSystemByIdV2026R0(createdSystem.getId()));
  }

  @Test
  public void testShuttleJobsWithNonExistentIdV2026R0() {
    String resourceId = "123456";
    ShuttleJobsV2026R0 jobs = client.getShuttle().getShuttleJobsV2026R0();
    assert jobs.getEntries().size() >= 0;
    assertThrows(
        RuntimeException.class,
        () ->
            client
                .getShuttle()
                .createShuttleJobV2026R0(
                    new ShuttleJobCreateRequestV2026R0(
                        getUuid(),
                        ShuttleJobCreateRequestV2026R0JobTypeField.TRANSFER,
                        resourceId)));
    assertThrows(
        RuntimeException.class, () -> client.getShuttle().getShuttleJobByIdV2026R0(resourceId));
    assertThrows(
        RuntimeException.class,
        () ->
            client
                .getShuttle()
                .updateShuttleJobByIdV2026R0(
                    resourceId,
                    new ShuttleJobUpdateRequestV2026R0.Builder().name(getUuid()).build()));
    assertThrows(
        RuntimeException.class, () -> client.getShuttle().deleteShuttleJobByIdV2026R0(resourceId));
    assertThrows(
        RuntimeException.class, () -> client.getShuttle().createShuttleJobStartV2026R0(resourceId));
    assertThrows(
        RuntimeException.class, () -> client.getShuttle().createShuttleJobStopV2026R0(resourceId));
    assertThrows(
        RuntimeException.class,
        () -> client.getShuttle().createShuttleJobPrepareMigrationV2026R0(resourceId));
  }

  @Test
  public void testShuttleRunsWithNonExistentIdV2026R0() {
    String resourceId = "123456";
    ShuttleRunsV2026R0 runs =
        client.getShuttle().getShuttleRunsV2026R0(new GetShuttleRunsV2026R0QueryParams(resourceId));
    assert runs.getEntries().size() >= 0;
    assertThrows(
        RuntimeException.class, () -> client.getShuttle().getShuttleRunByIdV2026R0(resourceId));
  }

  @Test
  public void testShuttleMappingsWithNonExistentIdV2026R0() {
    String resourceId = "123456";
    assertThrows(
        RuntimeException.class,
        () ->
            client
                .getShuttle()
                .getShuttlePathMappingsV2026R0(
                    new GetShuttlePathMappingsV2026R0QueryParams(resourceId)));
    assertThrows(
        RuntimeException.class,
        () -> client.getShuttle().getShuttlePathMappingByIdV2026R0(resourceId));
    assertThrows(
        RuntimeException.class,
        () ->
            client
                .getShuttle()
                .updateShuttlePathMappingByIdV2026R0(
                    resourceId,
                    new ShuttlePathMappingUpdateRequestV2026R0.Builder()
                        .isPathSkipped(true)
                        .build()));
    assertThrows(
        RuntimeException.class,
        () -> client.getShuttle().deleteShuttlePathMappingByIdV2026R0(resourceId));
    assertThrows(
        RuntimeException.class,
        () ->
            client
                .getShuttle()
                .createShuttlePathMappingImportV2026R0(
                    new ShuttlePathMappingImportCreateRequestV2026R0(resourceId, resourceId)));
    assertThrows(
        RuntimeException.class,
        () ->
            client
                .getShuttle()
                .getShuttleSubjectMappingsV2026R0(
                    new GetShuttleSubjectMappingsV2026R0QueryParams(resourceId)));
    assertThrows(
        RuntimeException.class,
        () ->
            client
                .getShuttle()
                .createShuttleSubjectMappingV2026R0(
                    new ShuttleSubjectMappingCreateRequestV2026R0(resourceId, resourceId)));
    assertThrows(
        RuntimeException.class,
        () -> client.getShuttle().getShuttleSubjectMappingByIdV2026R0(resourceId));
    assertThrows(
        RuntimeException.class,
        () ->
            client
                .getShuttle()
                .updateShuttleSubjectMappingByIdV2026R0(
                    resourceId,
                    new ShuttleSubjectMappingUpdateRequestV2026R0.Builder()
                        .isSubjectSkipped(true)
                        .build()));
    assertThrows(
        RuntimeException.class,
        () -> client.getShuttle().deleteShuttleSubjectMappingByIdV2026R0(resourceId));
    assertThrows(
        RuntimeException.class,
        () ->
            client
                .getShuttle()
                .createShuttleSubjectMappingImportV2026R0(
                    new ShuttleSubjectMappingImportCreateRequestV2026R0(resourceId, resourceId)));
    assertThrows(
        RuntimeException.class,
        () ->
            client
                .getShuttle()
                .getShuttleDiscoveredSubjectsV2026R0(
                    new GetShuttleDiscoveredSubjectsV2026R0QueryParams(resourceId)));
    assertThrows(
        RuntimeException.class,
        () ->
            client
                .getShuttle()
                .getShuttlePathEntriesV2026R0(
                    new GetShuttlePathEntriesV2026R0QueryParams(
                        resourceId, GetShuttlePathEntriesV2026R0QueryParamsSideField.SOURCE)));
    assertThrows(
        RuntimeException.class,
        () ->
            client
                .getShuttle()
                .getShuttleMappingErrorsV2026R0(
                    new GetShuttleMappingErrorsV2026R0QueryParams(resourceId)));
  }

  @Test
  public void testShuttleReportsWithNonExistentIdV2026R0() {
    String resourceId = "123456";
    assertThrows(
        RuntimeException.class,
        () ->
            client
                .getShuttle()
                .getShuttleReportsV2026R0(new GetShuttleReportsV2026R0QueryParams(resourceId)));
    assertThrows(
        RuntimeException.class,
        () ->
            client
                .getShuttle()
                .createShuttleReportV2026R0(
                    new ShuttleReportCreateRequestV2026R0(
                        resourceId,
                        ShuttleReportCreateRequestV2026R0FormatField.CSV,
                        Arrays.asList("summary"))));
    assertThrows(
        RuntimeException.class, () -> client.getShuttle().getShuttleReportByIdV2026R0(resourceId));
  }

  @Test
  public void testShuttleJobSchedulesWithNonExistentIdV2026R0() {
    String resourceId = "123456";
    assertThrows(
        RuntimeException.class,
        () ->
            client
                .getShuttle()
                .getShuttleJobSchedulesV2026R0(
                    new GetShuttleJobSchedulesV2026R0QueryParams(resourceId)));
    assertThrows(
        RuntimeException.class,
        () -> client.getShuttle().getShuttleJobScheduleByIdV2026R0(resourceId));
    assertThrows(
        RuntimeException.class,
        () ->
            client
                .getShuttle()
                .updateShuttleJobScheduleByIdV2026R0(
                    resourceId,
                    new ShuttleJobScheduleUpdateRequestV2026R0.Builder()
                        .runMode(ShuttleJobScheduleUpdateRequestV2026R0RunModeField.SIMULATION)
                        .build()));
    assertThrows(
        RuntimeException.class,
        () -> client.getShuttle().deleteShuttleJobScheduleByIdV2026R0(resourceId));
  }

  @Test
  public void testCreateShuttleJobScheduleWithNonExistentIdV2026R0() {
    String resourceId = "123456";
    assertThrows(
        RuntimeException.class,
        () ->
            client
                .getShuttle()
                .createShuttleJobScheduleV2026R0(
                    new ShuttleJobScheduleCreateRequestV2026R0.Builder(
                            resourceId,
                            ShuttleJobScheduleCreateRequestV2026R0RunModeField.TRANSFER,
                            new ShuttleJobScheduleTriggerByTimeV2026R0.Builder(
                                    "2030-01-01T09:00:00",
                                    "America/Los_Angeles",
                                    ShuttleJobScheduleTriggerByTimeV2026R0RepeatField.DAILY)
                                .type(ShuttleJobScheduleTriggerByTimeV2026R0TypeField.BY_TIME)
                                .build())
                        .state(ShuttleJobScheduleCreateRequestV2026R0StateField.PAUSED)
                        .build()));
  }

  @Test
  public void testCreateShuttlePathMappingWithNonExistentIdV2026R0() {
    String resourceId = "123456";
    assertThrows(
        RuntimeException.class,
        () ->
            client
                .getShuttle()
                .createShuttlePathMappingV2026R0(
                    new ShuttlePathMappingCreateRequestV2026R0(
                        resourceId,
                        new ShuttlePathMappingAccountLocationInputV2026R0.Builder(
                                "/finance",
                                new ShuttlePathMappingAccountInputV2026R0.Builder()
                                    .email("user@example.com")
                                    .build())
                            .type(ShuttlePathMappingAccountLocationInputV2026R0TypeField.ACCOUNT)
                            .build())));
  }
}
