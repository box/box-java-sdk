# ShuttleManager


- [List shuttle systems](#list-shuttle-systems)
- [Create shuttle system](#create-shuttle-system)
- [Get shuttle system by ID](#get-shuttle-system-by-id)
- [Update shuttle system](#update-shuttle-system)
- [Delete shuttle system](#delete-shuttle-system)
- [Issue registration token for Windows system](#issue-registration-token-for-windows-system)
- [List shuttle jobs](#list-shuttle-jobs)
- [Create shuttle job](#create-shuttle-job)
- [Get shuttle job by ID](#get-shuttle-job-by-id)
- [Update shuttle job](#update-shuttle-job)
- [Delete shuttle job](#delete-shuttle-job)
- [Start shuttle job](#start-shuttle-job)
- [Stop shuttle job](#stop-shuttle-job)
- [Prepare migration](#prepare-migration)
- [List shuttle runs](#list-shuttle-runs)
- [Get shuttle run by ID](#get-shuttle-run-by-id)
- [List shuttle job schedules](#list-shuttle-job-schedules)
- [Create shuttle job schedule](#create-shuttle-job-schedule)
- [Get shuttle job schedule](#get-shuttle-job-schedule)
- [Update shuttle job schedule](#update-shuttle-job-schedule)
- [Delete shuttle job schedule](#delete-shuttle-job-schedule)
- [List shuttle path mappings](#list-shuttle-path-mappings)
- [Create shuttle path mapping](#create-shuttle-path-mapping)
- [Get shuttle path mapping by ID](#get-shuttle-path-mapping-by-id)
- [Update shuttle path mapping](#update-shuttle-path-mapping)
- [Delete shuttle path mapping](#delete-shuttle-path-mapping)
- [Create shuttle path mapping import](#create-shuttle-path-mapping-import)
- [Create shuttle subject mapping import](#create-shuttle-subject-mapping-import)
- [List shuttle subject mappings](#list-shuttle-subject-mappings)
- [Create shuttle subject mapping](#create-shuttle-subject-mapping)
- [Get shuttle subject mapping by ID](#get-shuttle-subject-mapping-by-id)
- [Update shuttle subject mapping](#update-shuttle-subject-mapping)
- [Delete shuttle subject mapping](#delete-shuttle-subject-mapping)
- [List shuttle discovered subjects](#list-shuttle-discovered-subjects)
- [List shuttle path entries](#list-shuttle-path-entries)
- [List shuttle mapping errors](#list-shuttle-mapping-errors)
- [List shuttle reports](#list-shuttle-reports)
- [Create shuttle report](#create-shuttle-report)
- [Get shuttle report by ID](#get-shuttle-report-by-id)

## List shuttle systems

Returns a paginated list of shuttle systems accessible to the
authenticated user.

This operation is performed by calling function `getShuttleSystemsV2026R0`.

See the endpoint docs at
[API Reference](https://developer.box.com/reference/v2026.0/get-shuttle-systems/).

<!-- sample get_shuttle_systems_v2026.0 -->
```
client.getShuttle().getShuttleSystemsV2026R0()
```

### Arguments

- queryParams `GetShuttleSystemsV2026R0QueryParams`
  - Query parameters of getShuttleSystemsV2026R0 method
- headers `GetShuttleSystemsV2026R0Headers`
  - Headers of getShuttleSystemsV2026R0 method


### Returns

This function returns a value of type `ShuttleSystemsV2026R0`.

Returns a paginated list of shuttle systems.


## Create shuttle system

Creates a new shuttle system. Only Windows agent systems can be created
through the public API; other system types must be configured through the
Shuttle application.

This operation is performed by calling function `createShuttleSystemV2026R0`.

See the endpoint docs at
[API Reference](https://developer.box.com/reference/v2026.0/post-shuttle-systems/).

<!-- sample post_shuttle_systems_v2026.0 -->
```
client.getShuttle().createShuttleSystemV2026R0(new ShuttleSystemCreateRequestV2026R0.Builder(systemName).systemType(ShuttleSystemCreateRequestV2026R0SystemTypeField.WINDOWS).build())
```

### Arguments

- requestBody `ShuttleSystemCreateRequestV2026R0`
  - Request body of createShuttleSystemV2026R0 method
- headers `CreateShuttleSystemV2026R0Headers`
  - Headers of createShuttleSystemV2026R0 method


### Returns

This function returns a value of type `ShuttleSystemV2026R0`.

Returns the newly created shuttle system.


## Get shuttle system by ID

Returns details for a single shuttle system identified by its ID,
including its type and connection state.

This operation is performed by calling function `getShuttleSystemByIdV2026R0`.

See the endpoint docs at
[API Reference](https://developer.box.com/reference/v2026.0/get-shuttle-systems-id/).

<!-- sample get_shuttle_systems_id_v2026.0 -->
```
client.getShuttle().getShuttleSystemByIdV2026R0(createdSystem.getId())
```

### Arguments

- shuttleSystemId `String`
  - The unique identifier for the shuttle system. Example: "6a315c0754833bfc5df067bf"
- headers `GetShuttleSystemByIdV2026R0Headers`
  - Headers of getShuttleSystemByIdV2026R0 method


### Returns

This function returns a value of type `ShuttleSystemV2026R0`.

Returns the shuttle system.


## Update shuttle system

Updates the mutable properties of an existing shuttle system identified by
its ID. Only the fields supplied in the request body are changed.

This operation is performed by calling function `updateShuttleSystemByIdV2026R0`.

See the endpoint docs at
[API Reference](https://developer.box.com/reference/v2026.0/put-shuttle-systems-id/).

<!-- sample put_shuttle_systems_id_v2026.0 -->
```
client.getShuttle().updateShuttleSystemByIdV2026R0(createdSystem.getId(), new ShuttleSystemUpdateRequestV2026R0.Builder().name(updatedSystemName).build())
```

### Arguments

- shuttleSystemId `String`
  - The unique identifier for the shuttle system. Example: "6a315c0754833bfc5df067bf"
- requestBody `ShuttleSystemUpdateRequestV2026R0`
  - Request body of updateShuttleSystemByIdV2026R0 method
- headers `UpdateShuttleSystemByIdV2026R0Headers`
  - Headers of updateShuttleSystemByIdV2026R0 method


### Returns

This function returns a value of type `ShuttleSystemV2026R0`.

Returns the updated shuttle system.


## Delete shuttle system

Permanently deletes the shuttle system identified by its ID. This does not
affect content that has already been migrated.

This operation is performed by calling function `deleteShuttleSystemByIdV2026R0`.

See the endpoint docs at
[API Reference](https://developer.box.com/reference/v2026.0/delete-shuttle-systems-id/).

<!-- sample delete_shuttle_systems_id_v2026.0 -->
```
client.getShuttle().deleteShuttleSystemByIdV2026R0(createdSystem.getId())
```

### Arguments

- shuttleSystemId `String`
  - The unique identifier for the shuttle system. Example: "6a315c0754833bfc5df067bf"
- headers `DeleteShuttleSystemByIdV2026R0Headers`
  - Headers of deleteShuttleSystemByIdV2026R0 method


### Returns

This function returns a value of type `void`.

Returns an empty response when the shuttle system was successfully
deleted.


## Issue registration token for Windows system

Issues a new installation token for the Windows shuttle system identified
by its ID. Paste the returned token into the Windows agent installer to
register the agent with this system.

This operation is performed by calling function `createShuttleSystemIssueRegistrationTokenV2026R0`.

See the endpoint docs at
[API Reference](https://developer.box.com/reference/v2026.0/post-shuttle-systems-id-issue-registration-token/).

<!-- sample post_shuttle_systems_id_issue_registration_token_v2026.0 -->
```
client.getShuttle().createShuttleSystemIssueRegistrationTokenV2026R0(createdSystem.getId())
```

### Arguments

- shuttleSystemId `String`
  - The unique identifier for the shuttle system. Example: "6a315c0754833bfc5df067bf"
- headers `CreateShuttleSystemIssueRegistrationTokenV2026R0Headers`
  - Headers of createShuttleSystemIssueRegistrationTokenV2026R0 method


### Returns

This function returns a value of type `ShuttleAgentCredentialsV2026R0`.

Returns the agent installation credentials for the Windows system.


## List shuttle jobs

Returns a paginated list of shuttle jobs accessible to the authenticated
user.

This operation is performed by calling function `getShuttleJobsV2026R0`.

See the endpoint docs at
[API Reference](https://developer.box.com/reference/v2026.0/get-shuttle-jobs/).

<!-- sample get_shuttle_jobs_v2026.0 -->
```
client.getShuttle().getShuttleJobsV2026R0()
```

### Arguments

- queryParams `GetShuttleJobsV2026R0QueryParams`
  - Query parameters of getShuttleJobsV2026R0 method
- headers `GetShuttleJobsV2026R0Headers`
  - Headers of getShuttleJobsV2026R0 method


### Returns

This function returns a value of type `ShuttleJobsV2026R0`.

Returns a paginated list of shuttle jobs.


## Create shuttle job

Creates a new shuttle job that describes a migration from the given source
system into Box.

This operation is performed by calling function `createShuttleJobV2026R0`.

See the endpoint docs at
[API Reference](https://developer.box.com/reference/v2026.0/post-shuttle-jobs/).

<!-- sample post_shuttle_jobs_v2026.0 -->
```
client.getShuttle().createShuttleJobV2026R0(new ShuttleJobCreateRequestV2026R0(getUuid(), ShuttleJobCreateRequestV2026R0JobTypeField.TRANSFER, resourceId))
```

### Arguments

- requestBody `ShuttleJobCreateRequestV2026R0`
  - Request body of createShuttleJobV2026R0 method
- headers `CreateShuttleJobV2026R0Headers`
  - Headers of createShuttleJobV2026R0 method


### Returns

This function returns a value of type `ShuttleJobV2026R0`.

Returns the newly created shuttle job.


## Get shuttle job by ID

Returns details for a single shuttle job identified by its ID, including
its current state, mapping-preparation state, and configured source system.

This operation is performed by calling function `getShuttleJobByIdV2026R0`.

See the endpoint docs at
[API Reference](https://developer.box.com/reference/v2026.0/get-shuttle-jobs-id/).

<!-- sample get_shuttle_jobs_id_v2026.0 -->
```
client.getShuttle().getShuttleJobByIdV2026R0(resourceId)
```

### Arguments

- shuttleJobId `String`
  - The unique identifier for the shuttle job. Example: "6a3ba68e810d9156194530a1"
- headers `GetShuttleJobByIdV2026R0Headers`
  - Headers of getShuttleJobByIdV2026R0 method


### Returns

This function returns a value of type `ShuttleJobV2026R0`.

Returns the shuttle job.


## Update shuttle job

Updates the mutable properties of an existing shuttle job identified by its
ID. Only the fields supplied in the request body are changed.

This operation is performed by calling function `updateShuttleJobByIdV2026R0`.

See the endpoint docs at
[API Reference](https://developer.box.com/reference/v2026.0/put-shuttle-jobs-id/).

<!-- sample put_shuttle_jobs_id_v2026.0 -->
```
client.getShuttle().updateShuttleJobByIdV2026R0(resourceId, new ShuttleJobUpdateRequestV2026R0.Builder().name(getUuid()).build())
```

### Arguments

- shuttleJobId `String`
  - The unique identifier for the shuttle job. Example: "6a3ba68e810d9156194530a1"
- requestBody `ShuttleJobUpdateRequestV2026R0`
  - Request body of updateShuttleJobByIdV2026R0 method
- headers `UpdateShuttleJobByIdV2026R0Headers`
  - Headers of updateShuttleJobByIdV2026R0 method


### Returns

This function returns a value of type `ShuttleJobV2026R0`.

Returns the updated shuttle job.


## Delete shuttle job

Permanently deletes the shuttle job identified by its ID. This does not
affect content that has already been migrated.

This operation is performed by calling function `deleteShuttleJobByIdV2026R0`.

See the endpoint docs at
[API Reference](https://developer.box.com/reference/v2026.0/delete-shuttle-jobs-id/).

<!-- sample delete_shuttle_jobs_id_v2026.0 -->
```
client.getShuttle().deleteShuttleJobByIdV2026R0(resourceId)
```

### Arguments

- shuttleJobId `String`
  - The unique identifier for the shuttle job. Example: "6a3ba68e810d9156194530a1"
- headers `DeleteShuttleJobByIdV2026R0Headers`
  - Headers of deleteShuttleJobByIdV2026R0 method


### Returns

This function returns a value of type `void`.

Returns an empty response when the shuttle job was successfully deleted.


## Start shuttle job

Starts the job in the given mode. When no body is provided the job runs in
simulation mode.

This operation is performed by calling function `createShuttleJobStartV2026R0`.

See the endpoint docs at
[API Reference](https://developer.box.com/reference/v2026.0/post-shuttle-jobs-id-start/).

<!-- sample post_shuttle_jobs_id_start_v2026.0 -->
```
client.getShuttle().createShuttleJobStartV2026R0(resourceId)
```

### Arguments

- shuttleJobId `String`
  - The unique identifier for the shuttle job. Example: "6a3ba68e810d9156194530a1"
- requestBody `ShuttleJobStartRequestV2026R0`
  - Request body of createShuttleJobStartV2026R0 method
- headers `CreateShuttleJobStartV2026R0Headers`
  - Headers of createShuttleJobStartV2026R0 method


### Returns

This function returns a value of type `void`.

The shuttle job was accepted and is being started.


## Stop shuttle job

Stops a running shuttle job identified by its ID. Any work already in
progress is allowed to finish before the job transitions to a stopped
state.

This operation is performed by calling function `createShuttleJobStopV2026R0`.

See the endpoint docs at
[API Reference](https://developer.box.com/reference/v2026.0/post-shuttle-jobs-id-stop/).

<!-- sample post_shuttle_jobs_id_stop_v2026.0 -->
```
client.getShuttle().createShuttleJobStopV2026R0(resourceId)
```

### Arguments

- shuttleJobId `String`
  - The unique identifier for the shuttle job. Example: "6a3ba68e810d9156194530a1"
- headers `CreateShuttleJobStopV2026R0Headers`
  - Headers of createShuttleJobStopV2026R0 method


### Returns

This function returns a value of type `void`.

The shuttle job was accepted and is being stopped.


## Prepare migration

Validates the configured account and path mappings before a transfer. Only
relevant when account mapping is enabled.

This operation is performed by calling function `createShuttleJobPrepareMigrationV2026R0`.

See the endpoint docs at
[API Reference](https://developer.box.com/reference/v2026.0/post-shuttle-jobs-id-prepare-migration/).

<!-- sample post_shuttle_jobs_id_prepare_migration_v2026.0 -->
```
client.getShuttle().createShuttleJobPrepareMigrationV2026R0(resourceId)
```

### Arguments

- shuttleJobId `String`
  - The unique identifier for the shuttle job. Example: "6a3ba68e810d9156194530a1"
- headers `CreateShuttleJobPrepareMigrationV2026R0Headers`
  - Headers of createShuttleJobPrepareMigrationV2026R0 method


### Returns

This function returns a value of type `void`.

The migration preparation was accepted and is being processed.


## List shuttle runs

Returns a paginated list of runs for the shuttle job identified by
`job_id`.

This operation is performed by calling function `getShuttleRunsV2026R0`.

See the endpoint docs at
[API Reference](https://developer.box.com/reference/v2026.0/get-shuttle-runs/).

<!-- sample get_shuttle_runs_v2026.0 -->
```
client.getShuttle().getShuttleRunsV2026R0(new GetShuttleRunsV2026R0QueryParams(resourceId))
```

### Arguments

- queryParams `GetShuttleRunsV2026R0QueryParams`
  - Query parameters of getShuttleRunsV2026R0 method
- headers `GetShuttleRunsV2026R0Headers`
  - Headers of getShuttleRunsV2026R0 method


### Returns

This function returns a value of type `ShuttleRunsV2026R0`.

Returns a paginated list of shuttle runs.


## Get shuttle run by ID

Returns details for a single shuttle run identified by its ID, including
its mode, state, timing, and transfer statistics.

This operation is performed by calling function `getShuttleRunByIdV2026R0`.

See the endpoint docs at
[API Reference](https://developer.box.com/reference/v2026.0/get-shuttle-runs-id/).

<!-- sample get_shuttle_runs_id_v2026.0 -->
```
client.getShuttle().getShuttleRunByIdV2026R0(resourceId)
```

### Arguments

- shuttleRunId `String`
  - The unique identifier for the shuttle run, in the form `{job_id}:{run_number}`. Example: "6a3ba68e810d9156194530a1:42"
- headers `GetShuttleRunByIdV2026R0Headers`
  - Headers of getShuttleRunByIdV2026R0 method


### Returns

This function returns a value of type `ShuttleRunV2026R0`.

Returns the shuttle run.


## List shuttle job schedules

Returns a paginated list of schedules for the shuttle job identified by
`job_id`.

This operation is performed by calling function `getShuttleJobSchedulesV2026R0`.

See the endpoint docs at
[API Reference](https://developer.box.com/reference/v2026.0/get-shuttle-job-schedules/).

<!-- sample get_shuttle_job_schedules_v2026.0 -->
```
client.getShuttle().getShuttleJobSchedulesV2026R0(new GetShuttleJobSchedulesV2026R0QueryParams(resourceId))
```

### Arguments

- queryParams `GetShuttleJobSchedulesV2026R0QueryParams`
  - Query parameters of getShuttleJobSchedulesV2026R0 method
- headers `GetShuttleJobSchedulesV2026R0Headers`
  - Headers of getShuttleJobSchedulesV2026R0 method


### Returns

This function returns a value of type `ShuttleJobSchedulesV2026R0`.

Returns a paginated list of shuttle job schedules.


## Create shuttle job schedule

Creates a schedule that starts runs of the shuttle job identified by
`job_id`. The schedule starts a run either at a wall-clock time or after
another job's run meets a condition. Schedules cannot start a run in
`scan` mode.

This operation is performed by calling function `createShuttleJobScheduleV2026R0`.

See the endpoint docs at
[API Reference](https://developer.box.com/reference/v2026.0/post-shuttle-job-schedules/).

<!-- sample post_shuttle_job_schedules_v2026.0 -->
```
client.getShuttle().createShuttleJobScheduleV2026R0(new ShuttleJobScheduleCreateRequestV2026R0.Builder(resourceId, ShuttleJobScheduleCreateRequestV2026R0RunModeField.TRANSFER, new ShuttleJobScheduleTriggerByTimeV2026R0.Builder("2030-01-01T09:00:00", "America/Los_Angeles", ShuttleJobScheduleTriggerByTimeV2026R0RepeatField.DAILY).type(ShuttleJobScheduleTriggerByTimeV2026R0TypeField.BY_TIME).build()).state(ShuttleJobScheduleCreateRequestV2026R0StateField.PAUSED).build())
```

### Arguments

- requestBody `ShuttleJobScheduleCreateRequestV2026R0`
  - Request body of createShuttleJobScheduleV2026R0 method
- headers `CreateShuttleJobScheduleV2026R0Headers`
  - Headers of createShuttleJobScheduleV2026R0 method


### Returns

This function returns a value of type `ShuttleJobScheduleV2026R0`.

Returns the newly created shuttle job schedule.


## Get shuttle job schedule

Retrieves the shuttle job schedule identified by its ID.

This operation is performed by calling function `getShuttleJobScheduleByIdV2026R0`.

See the endpoint docs at
[API Reference](https://developer.box.com/reference/v2026.0/get-shuttle-job-schedules-id/).

<!-- sample get_shuttle_job_schedules_id_v2026.0 -->
```
client.getShuttle().getShuttleJobScheduleByIdV2026R0(resourceId)
```

### Arguments

- shuttleJobScheduleId `String`
  - The unique identifier for the shuttle job schedule. Example: "7c1ba68e810d9156194530b2"
- headers `GetShuttleJobScheduleByIdV2026R0Headers`
  - Headers of getShuttleJobScheduleByIdV2026R0 method


### Returns

This function returns a value of type `ShuttleJobScheduleV2026R0`.

Returns the shuttle job schedule.


## Update shuttle job schedule

Updates the mutable properties of an existing shuttle job schedule
identified by its ID. Only the fields supplied in the request body are
changed. The owning job cannot be changed.

This operation is performed by calling function `updateShuttleJobScheduleByIdV2026R0`.

See the endpoint docs at
[API Reference](https://developer.box.com/reference/v2026.0/put-shuttle-job-schedules-id/).

<!-- sample put_shuttle_job_schedules_id_v2026.0 -->
```
client.getShuttle().updateShuttleJobScheduleByIdV2026R0(resourceId, new ShuttleJobScheduleUpdateRequestV2026R0.Builder().runMode(ShuttleJobScheduleUpdateRequestV2026R0RunModeField.SIMULATION).build())
```

### Arguments

- shuttleJobScheduleId `String`
  - The unique identifier for the shuttle job schedule. Example: "7c1ba68e810d9156194530b2"
- requestBody `ShuttleJobScheduleUpdateRequestV2026R0`
  - Request body of updateShuttleJobScheduleByIdV2026R0 method
- headers `UpdateShuttleJobScheduleByIdV2026R0Headers`
  - Headers of updateShuttleJobScheduleByIdV2026R0 method


### Returns

This function returns a value of type `ShuttleJobScheduleV2026R0`.

Returns the updated shuttle job schedule.


## Delete shuttle job schedule

Permanently deletes the shuttle job schedule identified by its ID. This
does not affect runs that have already started.

This operation is performed by calling function `deleteShuttleJobScheduleByIdV2026R0`.

See the endpoint docs at
[API Reference](https://developer.box.com/reference/v2026.0/delete-shuttle-job-schedules-id/).

<!-- sample delete_shuttle_job_schedules_id_v2026.0 -->
```
client.getShuttle().deleteShuttleJobScheduleByIdV2026R0(resourceId)
```

### Arguments

- shuttleJobScheduleId `String`
  - The unique identifier for the shuttle job schedule. Example: "7c1ba68e810d9156194530b2"
- headers `DeleteShuttleJobScheduleByIdV2026R0Headers`
  - Headers of deleteShuttleJobScheduleByIdV2026R0 method


### Returns

This function returns a value of type `void`.

Returns an empty response when the shuttle job schedule was
successfully deleted.


## List shuttle path mappings

Returns a paginated list of path mappings for the shuttle job identified
by `job_id`.

This operation is performed by calling function `getShuttlePathMappingsV2026R0`.

See the endpoint docs at
[API Reference](https://developer.box.com/reference/v2026.0/get-shuttle-path-mappings/).

<!-- sample get_shuttle_path_mappings_v2026.0 -->
```
client.getShuttle().getShuttlePathMappingsV2026R0(new GetShuttlePathMappingsV2026R0QueryParams(resourceId))
```

### Arguments

- queryParams `GetShuttlePathMappingsV2026R0QueryParams`
  - Query parameters of getShuttlePathMappingsV2026R0 method
- headers `GetShuttlePathMappingsV2026R0Headers`
  - Headers of getShuttlePathMappingsV2026R0 method


### Returns

This function returns a value of type `ShuttlePathMappingsV2026R0`.

Returns a paginated list of shuttle path mappings.


## Create shuttle path mapping

Creates a path mapping that connects a source location on the migrated
system to a destination in Box for the given shuttle job.

This operation is performed by calling function `createShuttlePathMappingV2026R0`.

See the endpoint docs at
[API Reference](https://developer.box.com/reference/v2026.0/post-shuttle-path-mappings/).

<!-- sample post_shuttle_path_mappings_v2026.0 -->
```
client.getShuttle().createShuttlePathMappingV2026R0(new ShuttlePathMappingCreateRequestV2026R0(resourceId, new ShuttlePathMappingAccountLocationInputV2026R0.Builder("/finance", new ShuttlePathMappingAccountInputV2026R0.Builder().email("user@example.com").build()).type(ShuttlePathMappingAccountLocationInputV2026R0TypeField.ACCOUNT).build()))
```

### Arguments

- requestBody `ShuttlePathMappingCreateRequestV2026R0`
  - Request body of createShuttlePathMappingV2026R0 method
- headers `CreateShuttlePathMappingV2026R0Headers`
  - Headers of createShuttlePathMappingV2026R0 method


### Returns

This function returns a value of type `ShuttlePathMappingV2026R0`.

Returns the newly created shuttle path mapping.


## Get shuttle path mapping by ID

Returns details for a single shuttle path mapping identified by its ID,
including the source location and destination, if any.

This operation is performed by calling function `getShuttlePathMappingByIdV2026R0`.

See the endpoint docs at
[API Reference](https://developer.box.com/reference/v2026.0/get-shuttle-path-mappings-id/).

<!-- sample get_shuttle_path_mappings_id_v2026.0 -->
```
client.getShuttle().getShuttlePathMappingByIdV2026R0(resourceId)
```

### Arguments

- shuttlePathMappingId `String`
  - The unique identifier for the shuttle path mapping. Example: "6a3ba7a24ea17bc9461eeacd"
- headers `GetShuttlePathMappingByIdV2026R0Headers`
  - Headers of getShuttlePathMappingByIdV2026R0 method


### Returns

This function returns a value of type `ShuttlePathMappingV2026R0`.

Returns the shuttle path mapping.


## Update shuttle path mapping

Updates the mutable properties of an existing shuttle path mapping
identified by its ID. Only the fields supplied in the request body are
changed.

This operation is performed by calling function `updateShuttlePathMappingByIdV2026R0`.

See the endpoint docs at
[API Reference](https://developer.box.com/reference/v2026.0/put-shuttle-path-mappings-id/).

<!-- sample put_shuttle_path_mappings_id_v2026.0 -->
```
client.getShuttle().updateShuttlePathMappingByIdV2026R0(resourceId, new ShuttlePathMappingUpdateRequestV2026R0.Builder().isPathSkipped(true).build())
```

### Arguments

- shuttlePathMappingId `String`
  - The unique identifier for the shuttle path mapping. Example: "6a3ba7a24ea17bc9461eeacd"
- requestBody `ShuttlePathMappingUpdateRequestV2026R0`
  - Request body of updateShuttlePathMappingByIdV2026R0 method
- headers `UpdateShuttlePathMappingByIdV2026R0Headers`
  - Headers of updateShuttlePathMappingByIdV2026R0 method


### Returns

This function returns a value of type `ShuttlePathMappingV2026R0`.

Returns the updated shuttle path mapping.


## Delete shuttle path mapping

Permanently deletes the shuttle path mapping identified by its ID. This
does not affect content that has already been migrated.

This operation is performed by calling function `deleteShuttlePathMappingByIdV2026R0`.

See the endpoint docs at
[API Reference](https://developer.box.com/reference/v2026.0/delete-shuttle-path-mappings-id/).

<!-- sample delete_shuttle_path_mappings_id_v2026.0 -->
```
client.getShuttle().deleteShuttlePathMappingByIdV2026R0(resourceId)
```

### Arguments

- shuttlePathMappingId `String`
  - The unique identifier for the shuttle path mapping. Example: "6a3ba7a24ea17bc9461eeacd"
- headers `DeleteShuttlePathMappingByIdV2026R0Headers`
  - Headers of deleteShuttlePathMappingByIdV2026R0 method


### Returns

This function returns a value of type `void`.

Returns an empty response when the shuttle path mapping was
successfully deleted.


## Create shuttle path mapping import

Accepts a spreadsheet of path mappings, already stored in Box, and replaces
the job's path mappings.
A file that is not a mapping workbook, or that exceeds the size or row
limits (10 MiB Box download limit; 100,000 mapping rows), returns `400`
synchronously and leaves the job unchanged.
An accepted import always replaces every path mapping on the job. When all
rows are valid the workbook is deployed in full. When any row is invalid
the job ends with no path mappings and `mapping_state` becomes
`paths_import_rejected` until the next import or the next manual
path-mapping change. Row problems are listed by
`GET /shuttle_mapping_errors?job_id=…` with category `path_import`, messages
of the form `Row N: …`, at most 500 entries plus one overflow entry.
Validation and deployment are asynchronous after acceptance. Poll
`GET /shuttle_jobs/{shuttle_job_id}` while `mapping_state` is
`importing_paths`. Only one import may be in flight per job.

This operation is performed by calling function `createShuttlePathMappingImportV2026R0`.

See the endpoint docs at
[API Reference](https://developer.box.com/reference/v2026.0/post-shuttle-path-mapping-imports/).

<!-- sample post_shuttle_path_mapping_imports_v2026.0 -->
```
client.getShuttle().createShuttlePathMappingImportV2026R0(new ShuttlePathMappingImportCreateRequestV2026R0(resourceId, resourceId))
```

### Arguments

- requestBody `ShuttlePathMappingImportCreateRequestV2026R0`
  - Request body of createShuttlePathMappingImportV2026R0 method
- headers `CreateShuttlePathMappingImportV2026R0Headers`
  - Headers of createShuttlePathMappingImportV2026R0 method


### Returns

This function returns a value of type `void`.

The import was accepted. Validation and deployment continue
asynchronously. Poll `GET /shuttle_jobs/{shuttle_job_id}` until
`mapping_state` is no longer `importing_paths`. A rejected import ends
with `mapping_state` `paths_import_rejected` and an empty path-mapping
list; a successful import moves on to a later mapping-preparation state
such as `paths_ready` or `ready`.


## Create shuttle subject mapping import

Accepts a spreadsheet of subject (user and group) mappings, already stored
in Box, and replaces the job's subject mappings.
A file that is not a subject-mapping workbook, or that exceeds the size or
row limits (10 MiB Box download limit; 50,000 mapping rows), returns `400`
synchronously and leaves the job unchanged.
An accepted import always replaces every subject mapping on the job. When
all rows are valid the workbook is deployed in full. When any row is
invalid the job ends with no subject mappings and `mapping_state` becomes
`subjects_import_rejected` until the next import or the next manual
subject-mapping change. Row problems are listed by
`GET /shuttle_mapping_errors?job_id=…` with category `subject_import`,
messages of the form `Row N: …`, at most 500 entries plus one overflow
entry.
Validation and deployment are asynchronous after acceptance. Poll
`GET /shuttle_jobs/{shuttle_job_id}` while `mapping_state` is
`importing_subjects`. Only one import may be in flight per job. The job
must map subjects (`account_mapping` enabled) and its source scan must be
finished; otherwise the request returns `409`.

Expected workbook format (API clients cannot download the UI template):
sheets `Users` and/or `Groups` (a sheet named `Subjects` is also accepted).
Columns are `Source User <Key>`, `Target User <Key>`, `Skip` on `Users`,
and `Source Group <Key>`, `Target Group <Key>`, `Skip` on `Groups`, where
`<Key>` is the primary identifier for that role and system:
- Users: `Email` for most systems; `ID` for `SharePoint` (2007/2010/2013);
  `Name` for Windows (`windows_union`, stored as the user id).
- Groups: `Name` for most systems; `Email` for Google Drive admin groups.
Source rows for a template can be taken from
`GET /shuttle_discovered_subjects`.

This operation is performed by calling function `createShuttleSubjectMappingImportV2026R0`.

See the endpoint docs at
[API Reference](https://developer.box.com/reference/v2026.0/post-shuttle-subject-mapping-imports/).

<!-- sample post_shuttle_subject_mapping_imports_v2026.0 -->
```
client.getShuttle().createShuttleSubjectMappingImportV2026R0(new ShuttleSubjectMappingImportCreateRequestV2026R0(resourceId, resourceId))
```

### Arguments

- requestBody `ShuttleSubjectMappingImportCreateRequestV2026R0`
  - Request body of createShuttleSubjectMappingImportV2026R0 method
- headers `CreateShuttleSubjectMappingImportV2026R0Headers`
  - Headers of createShuttleSubjectMappingImportV2026R0 method


### Returns

This function returns a value of type `void`.

The import was accepted. Validation and deployment continue
asynchronously. Poll `GET /shuttle_jobs/{shuttle_job_id}` until
`mapping_state` is no longer `importing_subjects`. A rejected import
ends with `mapping_state` `subjects_import_rejected` and an empty
subject-mapping list; a successful import moves on to a later
mapping-preparation state such as `mapping_complete` or `ready`.


## List shuttle subject mappings

Returns a paginated list of subject mappings for the shuttle job identified
by `job_id`.

This operation is performed by calling function `getShuttleSubjectMappingsV2026R0`.

See the endpoint docs at
[API Reference](https://developer.box.com/reference/v2026.0/get-shuttle-subject-mappings/).

<!-- sample get_shuttle_subject_mappings_v2026.0 -->
```
client.getShuttle().getShuttleSubjectMappingsV2026R0(new GetShuttleSubjectMappingsV2026R0QueryParams(resourceId))
```

### Arguments

- queryParams `GetShuttleSubjectMappingsV2026R0QueryParams`
  - Query parameters of getShuttleSubjectMappingsV2026R0 method
- headers `GetShuttleSubjectMappingsV2026R0Headers`
  - Headers of getShuttleSubjectMappingsV2026R0 method


### Returns

This function returns a value of type `ShuttleSubjectMappingsV2026R0`.

Returns a paginated list of shuttle subject mappings.


## Create shuttle subject mapping

Creates a subject mapping that connects a discovered source subject to a
Box user or group for the given shuttle job.

This operation is performed by calling function `createShuttleSubjectMappingV2026R0`.

See the endpoint docs at
[API Reference](https://developer.box.com/reference/v2026.0/post-shuttle-subject-mappings/).

<!-- sample post_shuttle_subject_mappings_v2026.0 -->
```
client.getShuttle().createShuttleSubjectMappingV2026R0(new ShuttleSubjectMappingCreateRequestV2026R0(resourceId, resourceId))
```

### Arguments

- requestBody `ShuttleSubjectMappingCreateRequestV2026R0`
  - Request body of createShuttleSubjectMappingV2026R0 method
- headers `CreateShuttleSubjectMappingV2026R0Headers`
  - Headers of createShuttleSubjectMappingV2026R0 method


### Returns

This function returns a value of type `ShuttleSubjectMappingV2026R0`.

Returns the newly created shuttle subject mapping.


## Get shuttle subject mapping by ID

Returns details for a single shuttle subject mapping identified by its ID,
including the source subject and Box target, if any.

This operation is performed by calling function `getShuttleSubjectMappingByIdV2026R0`.

See the endpoint docs at
[API Reference](https://developer.box.com/reference/v2026.0/get-shuttle-subject-mappings-id/).

<!-- sample get_shuttle_subject_mappings_id_v2026.0 -->
```
client.getShuttle().getShuttleSubjectMappingByIdV2026R0(resourceId)
```

### Arguments

- shuttleSubjectMappingId `String`
  - The unique identifier for the shuttle subject mapping. Example: "6a3ba7b12ea17bc9461eeace"
- headers `GetShuttleSubjectMappingByIdV2026R0Headers`
  - Headers of getShuttleSubjectMappingByIdV2026R0 method


### Returns

This function returns a value of type `ShuttleSubjectMappingV2026R0`.

Returns the shuttle subject mapping.


## Update shuttle subject mapping

Updates the mutable properties of an existing shuttle subject mapping
identified by its ID. Only the fields supplied in the request body are
changed.

This operation is performed by calling function `updateShuttleSubjectMappingByIdV2026R0`.

See the endpoint docs at
[API Reference](https://developer.box.com/reference/v2026.0/put-shuttle-subject-mappings-id/).

<!-- sample put_shuttle_subject_mappings_id_v2026.0 -->
```
client.getShuttle().updateShuttleSubjectMappingByIdV2026R0(resourceId, new ShuttleSubjectMappingUpdateRequestV2026R0.Builder().isSubjectSkipped(true).build())
```

### Arguments

- shuttleSubjectMappingId `String`
  - The unique identifier for the shuttle subject mapping. Example: "6a3ba7b12ea17bc9461eeace"
- requestBody `ShuttleSubjectMappingUpdateRequestV2026R0`
  - Request body of updateShuttleSubjectMappingByIdV2026R0 method
- headers `UpdateShuttleSubjectMappingByIdV2026R0Headers`
  - Headers of updateShuttleSubjectMappingByIdV2026R0 method


### Returns

This function returns a value of type `ShuttleSubjectMappingV2026R0`.

Returns the updated shuttle subject mapping.


## Delete shuttle subject mapping

Permanently deletes the shuttle subject mapping identified by its ID.

This operation is performed by calling function `deleteShuttleSubjectMappingByIdV2026R0`.

See the endpoint docs at
[API Reference](https://developer.box.com/reference/v2026.0/delete-shuttle-subject-mappings-id/).

<!-- sample delete_shuttle_subject_mappings_id_v2026.0 -->
```
client.getShuttle().deleteShuttleSubjectMappingByIdV2026R0(resourceId)
```

### Arguments

- shuttleSubjectMappingId `String`
  - The unique identifier for the shuttle subject mapping. Example: "6a3ba7b12ea17bc9461eeace"
- headers `DeleteShuttleSubjectMappingByIdV2026R0Headers`
  - Headers of deleteShuttleSubjectMappingByIdV2026R0 method


### Returns

This function returns a value of type `void`.

Returns an empty response when the shuttle subject mapping was
successfully deleted.


## List shuttle discovered subjects

Returns a paginated list of subjects discovered on the source system for
the shuttle job identified by `job_id`.

This operation is performed by calling function `getShuttleDiscoveredSubjectsV2026R0`.

See the endpoint docs at
[API Reference](https://developer.box.com/reference/v2026.0/get-shuttle-discovered-subjects/).

<!-- sample get_shuttle_discovered_subjects_v2026.0 -->
```
client.getShuttle().getShuttleDiscoveredSubjectsV2026R0(new GetShuttleDiscoveredSubjectsV2026R0QueryParams(resourceId))
```

### Arguments

- queryParams `GetShuttleDiscoveredSubjectsV2026R0QueryParams`
  - Query parameters of getShuttleDiscoveredSubjectsV2026R0 method
- headers `GetShuttleDiscoveredSubjectsV2026R0Headers`
  - Headers of getShuttleDiscoveredSubjectsV2026R0 method


### Returns

This function returns a value of type `ShuttleDiscoveredSubjectsV2026R0`.

Returns a paginated list of discovered subjects.


## List shuttle path entries

Returns a paginated list of source or target path entries for a shuttle
job. Call with only `job_id` and `side` to list the roots, then browse one
level at a time using `path`, `path_id`, or both. Each entry includes
`path`, `path_id`, `is_directory`, and a `scope` object (`account` or
`shared_space`) so a mapping can be created from that row without extra
browse state. Valid values depend on the selected system.

This operation is performed by calling function `getShuttlePathEntriesV2026R0`.

See the endpoint docs at
[API Reference](https://developer.box.com/reference/v2026.0/get-shuttle-path-entries/).

<!-- sample get_shuttle_path_entries_v2026.0 -->
```
client.getShuttle().getShuttlePathEntriesV2026R0(new GetShuttlePathEntriesV2026R0QueryParams(resourceId, GetShuttlePathEntriesV2026R0QueryParamsSideField.SOURCE))
```

### Arguments

- queryParams `GetShuttlePathEntriesV2026R0QueryParams`
  - Query parameters of getShuttlePathEntriesV2026R0 method
- headers `GetShuttlePathEntriesV2026R0Headers`
  - Headers of getShuttlePathEntriesV2026R0 method


### Returns

This function returns a value of type `ShuttlePathEntriesV2026R0`.

Returns a paginated list of shuttle path entries.


## List shuttle mapping errors

Returns a paginated list of mapping errors for a shuttle job. Only the latest
record for each error is returned. The revision field identifies the mapping
revision in which that error was generated.

This operation is performed by calling function `getShuttleMappingErrorsV2026R0`.

See the endpoint docs at
[API Reference](https://developer.box.com/reference/v2026.0/get-shuttle-mapping-errors/).

<!-- sample get_shuttle_mapping_errors_v2026.0 -->
```
client.getShuttle().getShuttleMappingErrorsV2026R0(new GetShuttleMappingErrorsV2026R0QueryParams(resourceId))
```

### Arguments

- queryParams `GetShuttleMappingErrorsV2026R0QueryParams`
  - Query parameters of getShuttleMappingErrorsV2026R0 method
- headers `GetShuttleMappingErrorsV2026R0Headers`
  - Headers of getShuttleMappingErrorsV2026R0 method


### Returns

This function returns a value of type `ShuttleMappingErrorsV2026R0`.

Returns a paginated list of shuttle mapping errors.


## List shuttle reports

Returns a paginated list of reports generated for the shuttle run
identified by `run_id`.

This operation is performed by calling function `getShuttleReportsV2026R0`.

See the endpoint docs at
[API Reference](https://developer.box.com/reference/v2026.0/get-shuttle-reports/).

<!-- sample get_shuttle_reports_v2026.0 -->
```
client.getShuttle().getShuttleReportsV2026R0(new GetShuttleReportsV2026R0QueryParams(resourceId))
```

### Arguments

- queryParams `GetShuttleReportsV2026R0QueryParams`
  - Query parameters of getShuttleReportsV2026R0 method
- headers `GetShuttleReportsV2026R0Headers`
  - Headers of getShuttleReportsV2026R0 method


### Returns

This function returns a value of type `ShuttleReportsV2026R0`.

Returns a paginated list of shuttle reports.


## Create shuttle report

Requests asynchronous generation of a report for a shuttle run. The
report is returned immediately in `pending` or `processing` status.
Poll `GET /shuttle_reports/{shuttle_report_id}` while `status` is
`pending` or `processing`. Stop when `status` is `complete`, `failed`,
`canceled`, or `unknown`. Repeating this request with the same
parameters reuses the existing report instead of creating a duplicate.

This operation is performed by calling function `createShuttleReportV2026R0`.

See the endpoint docs at
[API Reference](https://developer.box.com/reference/v2026.0/post-shuttle-reports/).

<!-- sample post_shuttle_reports_v2026.0 -->
```
client.getShuttle().createShuttleReportV2026R0(new ShuttleReportCreateRequestV2026R0(resourceId, ShuttleReportCreateRequestV2026R0FormatField.CSV, Arrays.asList("summary")))
```

### Arguments

- requestBody `ShuttleReportCreateRequestV2026R0`
  - Request body of createShuttleReportV2026R0 method
- headers `CreateShuttleReportV2026R0Headers`
  - Headers of createShuttleReportV2026R0 method


### Returns

This function returns a value of type `ShuttleReportV2026R0`.

The report was accepted for generation. Returns the shuttle report;
generation continues asynchronously. Repeating this request with the
same parameters reuses the existing report instead of creating a
duplicate.


## Get shuttle report by ID

Returns details for a single shuttle report identified by its ID,
including generation status and, once generation finishes, the Box
output reference.

This operation is performed by calling function `getShuttleReportByIdV2026R0`.

See the endpoint docs at
[API Reference](https://developer.box.com/reference/v2026.0/get-shuttle-reports-id/).

<!-- sample get_shuttle_reports_id_v2026.0 -->
```
client.getShuttle().getShuttleReportByIdV2026R0(resourceId)
```

### Arguments

- shuttleReportId `String`
  - The unique identifier for the shuttle report. Example: "6a3ba8d14f2c91e8370b4d12"
- headers `GetShuttleReportByIdV2026R0Headers`
  - Headers of getShuttleReportByIdV2026R0 method


### Returns

This function returns a value of type `ShuttleReportV2026R0`.

Returns the shuttle report.


