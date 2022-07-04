# Changelog

All notable changes to this project will be documented in this file. See [standard-version](https://github.com/conventional-changelog/standard-version) for commit guidelines.

## [3.3.0](https://github.com/box/box-java-sdk/compare/v3.2.1...v3.3.0) (2022-07-01)


### New Features and Enhancements:

* Added support of Editable Shared Links ([#1064](https://github.com/box/box-java-sdk/issues/1064)) ([9b7d60c](https://github.com/box/box-java-sdk/commit/9b7d60c41fbd481465bf3f2a5877746f10849712))

### Bug Fixes:

* Fix closed stream exception in `canUpload` method ([#1067](https://github.com/box/box-java-sdk/issues/1067)) ([543f91c](https://github.com/box/box-java-sdk/commit/543f91c46dfcc9de7e61ce11cd93d472916533ac))

### [3.2.1](https://github.com/box/box-java-sdk/compare/v3.2.0...v3.2.1) (2022-06-10)


### Bug Fixes:

* Fix getting proper URL to authenticate with OAuth ([#1059](https://github.com/box/box-java-sdk/issues/1059)) ([42876b4](https://github.com/box/box-java-sdk/commit/42876b45ccdb7fa6f357186cecaba051abf1c269)), closes [#1057](https://github.com/box/box-java-sdk/issues/1057)

## [3.2.0](https://github.com/box/box-java-sdk/compare/v3.1.2...v3.2.0) (2022-05-23)


### New Features and Enhancements:

* Revamped setting base URLs ([#1042](https://github.com/box/box-java-sdk/issues/1042)) ([129baf7](https://github.com/box/box-java-sdk/commit/129baf704ced127788bb0f62ef9f4fb6a50fdc63))
* support for Avatar V2 API ([#1044](https://github.com/box/box-java-sdk/issues/1044)) ([18651d7](https://github.com/box/box-java-sdk/commit/18651d7a5b419796e3733c7582ae471d7af7ed5c))

### [3.1.2](https://github.com/box/box-java-sdk/compare/v3.1.1...v3.1.2) (2022-03-22)


### Bug Fixes:

* Allow using `As-User` header with CCG Authentication ([#1031](https://github.com/box/box-java-sdk/issues/1031)) ([b0c2389](https://github.com/box/box-java-sdk/commit/b0c238913cc1dbcecfd546a5eae68277c3c76d42))
* Fix retry logic when `Retry-After` header is present ([#1033](https://github.com/box/box-java-sdk/issues/1033)) ([05224c4](https://github.com/box/box-java-sdk/commit/05224c433d2a101a01959644674153df9542b711))

### [3.1.1](https://github.com/box/box-java-sdk/compare/v3.1.0...v3.1.1) (2022-02-28)


### Bug Fixes:

* retry `jwt` auth when error code is in error field ([#1020](https://github.com/box/box-java-sdk/issues/1020)) ([8c9d11d](https://github.com/box/box-java-sdk/commit/8c9d11d1b3556552751c9f4ac99a0f7180af97f3)), closes [#1019](https://github.com/box/box-java-sdk/issues/1019)

## [3.1.0](https://github.com/box/box-java-sdk/compare/v3.0.0...v3.1.0) (2022-02-17)


### New Features and Enhancements:

* Added support for Client Credentials Grant authentication method ([#1002](https://github.com/box/box-java-sdk/issues/1002)) ([9cfcaff](https://github.com/box/box-java-sdk/commit/9cfcaff243dbf0541409f91f9f863a207345dc47))
* API to extend disposition date on a file ([#1001](https://github.com/box/box-java-sdk/issues/1001)) ([f3f6b60](https://github.com/box/box-java-sdk/commit/f3f6b6043eec362c5a8ad9a01d6588538ca34e71))
* Deprecating `indexName` when executing metadata query ([#1000](https://github.com/box/box-java-sdk/issues/1000)) ([c20dbbf](https://github.com/box/box-java-sdk/commit/c20dbbf6a927e31cfdd7ffa71069c0897f7a0536))

### Dependency Upgrades:

* Upgrade Gradle to 7.3.3 ([#985](https://github.com/box/box-java-sdk/issues/985)) ([e4acbb1](https://github.com/box/box-java-sdk/commit/e4acbb1f0c10ccdeeee139e2566b344052680010))

## [3.0.0](https://github.com/box/box-java-sdk/compare/v2.58.0...v3.0.0) (2022-01-17)


### ⚠ BREAKING CHANGES

* Changed `BoxFileVersion` class and removed `fileVersion` field (#978)
* Removed deprecated API `BoxCollaborationWhitelist` replaced with `BoxCollaborationAllowlist`, `BoxCollaborationWhitelistExemptTarget` replaced with `BoxCollaborationAllowlistExemptTarget` (#969)
* Dropping Java 7 support (#962)
* Downgrading `bouncycastle` libraries to 1.57 (#942)

### New Features and Enhancements:

* Add `typeName` to `BoxEvent` that contains name of the event, even if it is not mapped to `BoxEvent.EventType` ([#979](https://github.com/box/box-java-sdk/issues/979)) ([b30f61f](https://github.com/box/box-java-sdk/commit/b30f61f8cc9c02a1fc4cd5eb35469749e1a16558)), closes [#968](https://github.com/box/box-java-sdk/issues/968)
* Add new optional `description` parameter to the `retention_policies` endpoint and `start_date_field` to the `retention_policy_assignments endpoint`. ([#967](https://github.com/box/box-java-sdk/issues/967)) ([0aa4ff4](https://github.com/box/box-java-sdk/commit/0aa4ff48a1e035efc9ac6aaa42f18f4c92955b7b))
* Adding `BoxFile#getVersions(String... fields)` to allow users to specify what information they want to extract. Fixes [#946](https://github.com/box/box-java-sdk/issues/946). ([#947](https://github.com/box/box-java-sdk/issues/947)) ([a2eb638](https://github.com/box/box-java-sdk/commit/a2eb63896606a6c00ccee6bd9745f4c51f8d89a2))
* Missing `eventTypes` from `BoxAPI` Documents. Fixes [#974](https://github.com/box/box-java-sdk/issues/974) ([#975](https://github.com/box/box-java-sdk/issues/975)) ([2c69360](https://github.com/box/box-java-sdk/commit/2c69360e80b1bdd6213933cf2f4da195d52c92d4))
* Removed deprecated API `BoxCollaborationWhitelist` replaced with `BoxCollaborationAllowlist`, `BoxCollaborationWhitelistExemptTarget` replaced with `BoxCollaborationAllowlistExemptTarget` ([#969](https://github.com/box/box-java-sdk/issues/969)) ([2fd4d6f](https://github.com/box/box-java-sdk/commit/2fd4d6f884410c8884c4c038687bfc8f32837b55))

### Bug Fixes:

* Changed `BoxFileVersion` class and removed `fileVersion` field ([#978](https://github.com/box/box-java-sdk/issues/978)) ([8c39451](https://github.com/box/box-java-sdk/commit/8c3945167581400043a070c2f6906ef05d3d7b85))
* Changed SDK loggers name to `"com.box.sdk"`, fixes [#638](https://github.com/box/box-java-sdk/issues/638) ([#950](https://github.com/box/box-java-sdk/issues/950)) ([443c230](https://github.com/box/box-java-sdk/commit/443c23085e55bbcaa1524c5b9e1bf852a1e2a1ce))
* Date parsing error when `BoxSignRequestPrefillTag` created with date value. ([#970](https://github.com/box/box-java-sdk/issues/970)) ([cc2c8da](https://github.com/box/box-java-sdk/commit/cc2c8da9ea7d066ae2c247c2de5ac8b8bbba9b99))
* Fix sending limit param in `EventLog` ([#977](https://github.com/box/box-java-sdk/issues/977)) ([96bdccc](https://github.com/box/box-java-sdk/commit/96bdccc9ca40ed43a6028a2b0d055d9d9a8de525))
* Fixed `NullPointerException` when empty metadata used on BoxFile or `BoxFolder` ([#918](https://github.com/box/box-java-sdk/issues/918)) ([#945](https://github.com/box/box-java-sdk/issues/945)) ([68bc3c5](https://github.com/box/box-java-sdk/commit/68bc3c578d760b7239f6d704fed9bb5a834bf52a))
* Fixes issue ([#951](https://github.com/box/box-java-sdk/issues/951)) error when deserialising sign request ([#952](https://github.com/box/box-java-sdk/issues/952)) ([070bdc5](https://github.com/box/box-java-sdk/commit/070bdc56074a1533c41f9085943d09502c79a7f4))

### Dependency Upgrades:

* Dropping Java 7 support ([#962](https://github.com/box/box-java-sdk/issues/962)) ([953ad78](https://github.com/box/box-java-sdk/commit/953ad78ac84833082439d0def1dcc63dc11ac04a))
* Downgrading `bouncycastle` libraries to 1.57 ([#942](https://github.com/box/box-java-sdk/issues/942)) ([26aaed5](https://github.com/box/box-java-sdk/commit/26aaed51fd914eaf2061da735f11830524e4cfe4))


## [2.58.0]  (2021-11-23)

### ⚠ BREAKING CHANGES

### New Features and Enhancements:
 - SDK support for new GET /events stream_type: admin_logs_streaming ([#938](https://github.com/box/box-java-sdk/pull/938))
 - Adding BoxDeveloperEditionAPIConnection#getUserConnection to indicate that we can use this connection for managed users or app users ([#940](https://github.com/box/box-java-sdk/pull/940))

### Bug Fixes:
 - Fix for deprecated enums still being used ([#931](https://github.com/box/box-java-sdk/issues/931))

## [2.57.0]  (2021-10-18)

### ⚠ BREAKING CHANGES

### New Features and Enhancements:
- Add support for marker-based paging in BoxFolder.getChildren ([#927](https://github.com/box/box-java-sdk/pull/927))
- Upgraded minimal-json to v0.9.5
- Upgraded jose4j to v0.7.9
- Adding Gradle wrapper in version 4.0.1 ([#928](https://github.com/box/box-java-sdk/pull/928))

### Bug Fixes:
- Fix for infinite recursion ([#924](https://github.com/box/box-java-sdk/pull/924))
- Fix unable to set Vanity URL on `BoxSharedLink` for BoxFile and BoxFolder ([#925](https://github.com/box/box-java-sdk/issues/925))

## [2.56.0]  (2021-08-31)

### New Features and Enhancements:
- Replace `submaster` GroupMembershipRole with `coadmin`. Replace `MASTER_INVITE_ACCEPT` and `MASTER_INVITE_REJECT` with `ADMIN_INVITE_ACCEPT` and `ADMIN_INVITE_REJECT`. ([#907](https://github.com/box/box-java-sdk/pull/907))
- Add `tracking_codes` to create User API call ([#910](https://github.com/box/box-java-sdk/pull/910))

### Bug Fixes:
- Fix `url` for `BoxFileRequest.Info` object ([#906](https://github.com/box/box-java-sdk/pull/906))
- Attempt to fix thread locking issue on refresh of access token ([#912](https://github.com/box/box-java-sdk/pull/912))

## [2.55.1]  (2021-07-30)

### Bug Fixes:
- Restore methods for Execute Metadata Query, which were removed in ([#890](https://github.com/box/box-java-sdk/pull/890)), and mark them as deprecated ([#905](https://github.com/box/box-java-sdk/pull/905))

## [2.55.0]  (2021-07-26)

NOTE: Due to the benign nature of the "breaking change" below, we decided NOT to increment the major version for this release.  There should be no customer impact due to this change.

### ⚠ BREAKING CHANGES
- Update execute metadata query to match API response ([#890](https://github.com/box/box-java-sdk/pull/890))
  - NOTE: This change removes a method without deprecating it.  It was not possible to use the method correctly at all, because the underlying service no longer supported it.

### New Features and Enhancements:
- Remove or deprecate insensitive language ([#889])(https://github.com/box/box-java-sdk/pull/889)
- Add support for `is_external_collab_restricted` parameter for User ([#896](https://github.com/box/box-java-sdk/pull/896))
- Add configurable permissions support for `GroupMembership` ([#897](https://github.com/box/box-java-sdk/pull/897))
- Add `SHIELD_JUSTIFICATION_APPROVAL` event type ([#898](https://github.com/box/box-java-sdk/pull/898))
- Add ability to get files under retention for assignment and file versions under retention for assignment ([#899](https://github.com/box/box-java-sdk/pull/899))
- Add `TASK_UPDATE`, `FILE_VERSION_RESTORE` and `ADVANCED_FOLDER_SETTINGS_UPDATE` event types ([#902](https://github.com/box/box-java-sdk/pull/902))
- Add SignAPI support ([#904](https://github.com/box/box-java-sdk/pull/904))

### Bug Fixes:
- Add setters for `BoxLegalHoldPolicy` ([#885](https://github.com/box/box-java-sdk/pull/885))
- Add setters for `BoxTaskAssignment` ([#886](https://github.com/box/box-java-sdk/pull/886))
- Add setters for Group Membership and Web Links ([#887](https://github.com/box/box-java-sdk/pull/887))
- Add setters for Webhooks ([#888](https://github.com/box/box-java-sdk/pull/888))
- Deprecate `BoxFile.getThumbnail` in favor of `BoxFile.getRepresentationContent` ([#891](https://github.com/box/box-java-sdk/pull/891))

## [2.54.0]  (2021-04-01)

### New Features and Enhancements:

- Add file request support ([#869](https://github.com/box/box-java-sdk/pull/869))

### Bug Fixes:

- Fix `BoxWeblink` deserialization ([#881](https://github.com/box/box-java-sdk/pull/881))

## [2.53.0]  (2021-01-08)

### New Features and Enhancements:

- Add offset and limit parameters to `BoxFolder.getChildren` ([#861](https://github.com/box/box-java-sdk/pull/861))

## [2.52.0]  (2020-11-24)

### New Features and Enhancements:

- Add folder lock functionality ([#856](https://github.com/box/box-java-sdk/pull/856))
- Add support for search param to get shared link items ([#855](https://github.com/box/box-java-sdk/pull/855))

### Bug Fixes:

- Fix bug with updating tracking codes ([#857](https://github.com/box/box-java-sdk/pull/857))

## [2.51.1]  (2020-11-12)

### Bug Fixes:

- Fix for cross-enterprise collaborator calls to updateMetadata on files

## [2.51.0]  (2020-10-29)

### New Features and Enhancements:

- Add support for `copyInstanceOnItemCopy` field for metadata templates ([#850](https://github.com/box/box-java-sdk/pull/850))
- Add support for more fields in `BoxCollaborator.Info` ([#843](https://github.com/box/box-java-sdk/pull/843))

### Bug Fixes:

- Update `getAllGroupsByName()` to use documented parameter ([#851](https://github.com/box/box-java-sdk/pull/851))

## [2.50.1]  (2020-08-20)
- Fix bug that occurred when downscoping a token for a Box folder ([#832](https://github.com/box/box-java-sdk/pull/832))

## [2.50.0]  (2020-07-21)
- API request creation errors are now retried with the same automatic retry logic as 429 and 5XX response errors ([#828](https://github.com/box/box-java-sdk/pull/828))

## [2.49.0]  (2020-07-17)
- Fix bug with setting the unshared at date for a shared link ([#819](https://github.com/box/box-java-sdk/pull/819))
- Add zip functionality ([#825](https://github.com/box/box-java-sdk/pull/825))
- Add `fields` parameter for metadata query ([#826](https://github.com/box/box-java-sdk/pull/826))

## [2.48.0]  (2020-06-23) 
- Add ability to get groups by name with fields option ([#789](https://github.com/box/box-java-sdk/pull/789))
- Add shared link downscoping ([#785](https://github.com/box/box-java-sdk/pull/785))
- Deprecate the use of float for Metadata values, in preference of the underlying value (double) ([#811](https://github.com/box/box-java-sdk/pull/811))
- Add iterator support for group collaborations ([#813](https://github.com/box/box-java-sdk/pull/813))
- Add ability to set the filename when uploading a new version of a file ([#810](https://github.com/box/box-java-sdk/pull/810))
- Add support for the classification field for Files and Folders ([#809](https://github.com/box/box-java-sdk/pull/809))
- Add support for setting Tracking Codes ([#766](https://github.com/box/box-java-sdk/pull/766))
- Fix issue for `getIsExternallyOwned()` for Files and Folders ([#808](https://github.com/box/box-java-sdk/pull/808))

## [2.47.0]  (2020-04-23)
- Add support for the uploader display name field for Files and File Versions ([#791](https://github.com/box/box-java-sdk/pull/791))
- Fix path parameter sanitization ([#797](https://github.com/box/box-java-sdk/pull/797))

## [2.46.0]  (2020-04-09)
- Fix retry logic ([#787](https://github.com/box/box-java-sdk/pull/787))
  - Retry for 400 `invalid_grant` error in authentication requests (Clock Skew)
  - Honor Retry-After header, if present, by waiting for the time specified in the header before retrying
  - The concept of setting / getting "Maximum API Requests" has been deprecated in favor of "Maximum API Retries" to more clearly show the number of times a request will be retried after an error response is received.
- Add ability to set expiration date for a collaboration ([#788](https://github.com/box/box-java-sdk/pull/788))
- Add path parameter sanitization ([#790](https://github.com/box/box-java-sdk/pull/790))

## [2.45.0]  (2020-04-02)
- Add preflight check before chunked uploads ([#782](https://github.com/box/box-java-sdk/pull/782))
- Check that part was successfully uploaded for large file uploads before retrying for 500 errors ([#781](https://github.com/box/box-java-sdk/pull/781))
- Fix bug with premature disconnect when renaming files and weblinks ([#779](https://github.com/box/box-java-sdk/pull/779))
- Add metadata to each item returned by a metadata query ([#778](https://github.com/box/box-java-sdk/pull/778))

## [2.44.1]  (2020-02-13)
- Fix formatting bug for Java Logger
- Improve date / time parsing for responses

## [2.44.0]  (2020-01-21)
- Fix Authentication Request Retries

## [2.43.0]  (2019-12-20)
- Throw exceptions for setMetadata on Files and Folders for non-409 errors

## [2.42.0]  (2019-12-17)
- Added Metadata Query support
- Added marker based pagination for get users methods

## [2.41.0]  (2019-10-24)
- Added enum action option for completed in Box Task class.

## [2.40.0]  (2019-10-24)
- General doc changes. 

## [2.39.0]  (2019-10-17)
- Deprecated Batch API functionality.
- Added support for [Task completion_rule](https://github.com/box/box-java-sdk/blob/main/src/main/java/com/box/sdk/BoxFile.java#L249)

## [2.38.0]  (2019-09-19)
- Added missing fields for File Version: trashed_by, restored_at, purged_at, purged_by.
- Added support for [chunked uploads with file attributes](https://github.com/box/box-java-sdk/blob/main/doc/files.md#upload-a-large-file-in-chunks-including-attributes).

## [2.37.0]  (2019-08-22)
- Added support for replace in multi-select metadata for [files](https://github.com/box/box-java-sdk/blob/main/doc/files.md#update-metadata) and
  for [folders](https://github.com/box/box-java-sdk/blob/main/doc/folders.md#update-metadata)
- Improved getting started with JWT authentication docs that can be found [here](https://github.com/box/box-java-sdk/blob/main/doc/authentication.md#server-authentication-with-jwt)

## [2.36.0]  (2019-08-01)
- Added support for [removing shared link](https://github.com/box/box-java-sdk/blob/main/src/main/java/com/box/sdk/BoxItem.java#L413) and fixed an issue with setting null for shared link field on BoxItem.
- Added support for additional fields for Box files, folders, and web links.

## [2.35.0]  (2019-07-18)
- Added support for retrieving [is_external_only field](https://github.com/box/box-java-sdk/blob/main/src/main/java/com/box/sdk/BoxFile.java#L1668) for Box Files and Folders.

## [2.34.0]  (2019-06-06)
- Added support for retrieving a [string type action for tasks](https://github.com/box/box-java-sdk/blob/main/src/main/java/com/box/sdk/BoxTask.java#L281).
  Please use getActionType() going forward instead of the deprecated getAction().

## [2.33.0]  (2019-05-23)

- Added support for [setting can_owners_invite field](https://github.com/box/box-java-sdk/blob/1ed10d7a457e44b863ec1c9b1d0d1408fb55e1e5/src/main/java/com/box/sdk/BoxFolder.java#L1272) Thank you @Band-Aid for you pull request! Greatly Appreciated.
- Fixed a bug where chunked upload was not populating the correct part size for upload part.

## [2.32.0]  (2019-04-25)
- Added support [setting metadata](https://github.com/box/box-java-sdk/blob/main/doc/folders.md#set-metadata).

## [2.31.0]  (2019-04-11)

- Added support for [sorting folder items](https://github.com/box/box-java-sdk/blob/main/doc/folders.md#get-a-folders-items) retrieved from a folder by ascending or descending order.

## [2.30.1]  (2019-04-08)

- Fixed a bug where the SDK could throw when parsing JSON containing dates using the Zulu timezone format

## [2.30.0]  (2019-04-04)
- Added `action_by` field to enterprise events stream.

## [2.29.0]  (2019-04-01)
- Added support for [sorting results returned from Box Search](https://github.com/box/box-java-sdk/blob/main/doc/search.md#search-1)
- Added ability to [attach a file description upon file upload](https://github.com/box/box-java-sdk/blob/main/doc/files.md#upload-a-file)

## [2.28.1]  (2019-03-07)
- Fixed a bug where BoxMetadataCascadePolicy.forceApply() would not return correctly.

## [2.28.0]  (2019-02-21)
- Added ability for user to [retrieve an avatar](http://opensource.box.com/box-java-sdk/javadoc/com/box/sdk/BoxUser.html#getAvatar--)) for a specified user.

## [2.27.0]  (2019-01-31)
- Added support for Metadata Classification for [File](http://opensource.box.com/box-java-sdk/javadoc/com/box/sdk/BoxFile.html#setClassification-java.lang.String...-) and [Folder](http://opensource.box.com/box-java-sdk/javadoc/com/box/sdk/BoxFolder.html#setClassification-java.lang.String...-)

## [2.26.0]  (2019-01-17)
- Added [invite_email](https://github.com/box/box-java-sdk/blob/main/src/main/java/com/box/sdk/BoxCollaboration.java#L277) field to collaboration object.
- Added [is_collaboration_restricted_to_enterprise](https://github.com/box/box-java-sdk/blob/main/src/main/java/com/box/sdk/BoxFolder.java#L1104) field to folder object.
- Added [status](https://github.com/box/box-java-sdk/blob/main/src/main/java/com/box/sdk/BoxTaskAssignment.java#L196) field to task assignment object.
- Added ability to retrieve fields for [`BoxFile#getTasks()`](http://opensource.box.com/box-java-sdk/javadoc/com/box/sdk/BoxFile.html#getTasks-java.lang.String...-)
- Fixed bug where offset based paging would not return correctly.

## [2.25.1]  (2019-01-03)
- Upgraded dependencies: jose4j to v0.5.5, and bouncycastle to v1.60

## [2.25.0]  (2018-12-13)
- Added functionality to allow [content streaming to Box through outputstream](http://opensource.box.com/box-java-sdk/javadoc/com/box/sdk/BoxFolder.html#uploadFile-com.box.UploadFileCallback-java.lang.String-). Thank you @gajarajkalburgi for the pr!

## [2.24.0]  (2018-11-16)
- Added `getOptionsObjects()` on `MetadataTemplate.Field` which returns both key and type.
- Added functionality for [`BoxItem#getType()`](http://opensource.box.com/box-java-sdk/javadoc/com/box/sdk/BoxItem.html#getType--) for BoxItem.
- Added functionality for [`BoxAPIConnection#BoxGlobalSettings()`](http://opensource.box.com/box-java-sdk/javadoc/com/box/sdk/BoxAPIConnection.html#setConnectTimeout-java.lang.String-)
and [`BoxAPIConnection#BoxGlobalSettings()`](http://opensource.box.com/box-java-sdk/javadoc/com/box/sdk/BoxAPIConnection.html#getConnectTimeout--) as well as getting and reading the timeout for the connection.
- Added functionality for [`BoxGlobalSettings#getMaxRequestAttempts()`](http://opensource.box.com/box-java-sdk/javadoc/com/box/sdk/BoxGlobalSettings.html#getMaxRequestAttempts--)
and [`BoxGlobalSettings#setMaxRquestAttempts()`](http://opensource.box.com/box-java-sdk/javadoc/com/box/sdk/BoxGlobalSettings.html#setMaxRequestAttempts-java.lang.Integer-)
- Fixed a bug where [`BoxLegalHoldPolicy#create()`](http://opensource.box.com/box-java-sdk/javadoc/com/box/sdk/BoxLegalHoldPolicy.html#create-com.box.BoxAPIConnection-java.lang.String-) was not setting the correct legal hold policy duration.

## [2.23.2]  (2018-09-27)
- Fixed a bug where the specified headers for batch requests were not being sent.

## [2.23.1]  (2018-09-13)
- Fixed a bug where too many TCP connections were being opened. Thank you @pmatte1 for implementing this fix!

## [2.23.0]  (2018-08-23)
- Added support for [Metadata Cascade Policy](https://github.com/box/box-java-sdk/blob/main/doc/folders.md#create-cascade-policy-on-folder)

## [2.22.0]  (2018-08-09)
- Deprecated the [moveFolderToUser()](https://github.com/box/box-java-sdk/blob/main/src/main/java/com/box/sdk/BoxUser.java#L455) for Box Users. We encourage users to
use [transferContent](https://github.com/box/box-java-sdk/blob/main/src/main/java/com/box/sdk/BoxUser.java#L482) going forward because idiomatically it is more correct.

## [2.21.0]  (2018-07-05)
- Added functionality to allow users to [set passwords on shared links](https://github.com/box/box-java-sdk/pull/623) for Box files, folders, and web links.
- Fixed wrong redirect for two links in the `Getting Started` and `Quick Test` section of the README.

## [2.20.2]  (2018-06-28)
- Fixed a bug where customers had issues with large file uploads because they fail to parse the Retry-After header from the
commit response. Reason being headers storage/lookup was case sensitive.

## [2.20.1]  (2018-06-04)
- Added better exception handling for JSON parse in response exception.
- Fixed a bug where uploadNewVersion() was returning an empty object.

## [2.20.0]  (2018-05-24)
- Fixed a bug where multiple As-User headers could be set.
- Added support to [test update](http://opensource.box.com/box-java-sdk/javadoc/com/box/sdk/Metadata.html#test-java.lang.String-java.util.List-) for multiselect field on metadata

## [2.19.0]  (2018-05-10)
- Added support for enterprise admins with Box Zones purchased to have support for [Box Storage Policies and Box Storage Policy Assignments](./doc/storage_policy)
- Added support to allow users to work with [multiselect metadata](./doc/files.md#get-metadata)
- Added `getLogin()` method for the "login" field on the "accessible by" for BoxCollaboration.Info class.


## [2.18.0]  (2018-04-30)
- Fixed a bug where the the private key password should be passed into `setPrivateKetPassword()` instead of the private key. A big thank you to [breach10ck](https://github.com/breach10ck)
for their pull request!
- Added an additional check to ensure that the [request properties on the request object is not null in the `toString()` method](https://github.com/box/box-java-sdk/pull/595)
- Added support to [fetch the content of the generated representation](./doc/files.md#get-representation-content) after it has been generated
- Improved error messages for API response errors to allow for better debugging.

## [2.17.0]  (2018-04-10)
- Added support for assigning [Retention Policies to Metadata Templates](./doc/retention_policies.md#create-retention-policy-assignment)

## [2.16.1]  (2018-03-29)

- Added `CONTENT_ACCESS` to event type enum

## [2.16.0]  (2018-03-22)
- Added support for [user tracking codes](http://opensource.box.com/box-java-sdk/javadoc/com/box/sdk/BoxUser.Info.html#getTrackingCodes--) on the user object.
- Fixed a bug where JWT authentication would fail due to improper date parsing.
- Added support for setting custom headers on API connection. This allow for setting [As-User support](./doc/overview.md#as-user)
and [suppressing notifications](./doc/overview.md/suppressing-notifications) support.
- Changed default JWT expiration window to reduce chances of error.

## [2.15.0]  (2018-03-12)
- Added support for retrieving a [metadata template by ID](./doc/metadata_template.md#get-by-id)
- Added support for allowing the user to [retrieve specific Collaboration fields on a Collaboration object](./doc/collaborations.md#get-a-collaborations-information)

## [2.14.1]  (2018-03-01)

- Reduced the number of API calls that the `EventStream` makes to fetch new events, which should
help users who are running into rate limit issues.
- Force support for TLSv1.1 or higher when available to improve the security of connections to the Box API
- Add randomized jitter to the exponential backoff algorithm used by the SDK to improve the success rate
of retried requests.

## [2.14.0]  (2018-02-15)

- Added support for getting and setting the `can_view_path` field on a collaboration object.
- Added support for getting and setting the `tags` field on files and folders.

## [2.13.0]  (2018-02-07)

- Fixed an issue where all types of metadata values were being coerced to Strings.  This change deprecates
`Metadata#get()` in favor of type-specific methods like `Metadata#getFloat()` or a generic `Metadata#getValue()`,
which returns a `JsonValue` object that represents any JSON type.  See the [file metadata](./doc/files.md#get-metadata)
or [folder metadata](./doc/folders.md#get-metadata) documentation for more information.

## [2.12.0]  (2018-02-01)

- Fixed ability to notify users or groups regarding [file collaboration](https://github.com/box/box-java-sdk/blob/main/doc/files.md#share-a-file) or [folder collaboration](https://github.com/box/box-java-sdk/blob/main/doc/folders.md#share-a-folder)
- Added [OAuth2 token creation event types](https://github.com/box/box-java-sdk/blob/main/src/main/java/com/box/sdk/BoxEvent.java#L747)
- Added support for [inviting a user to another Box Enterprise](http://opensource.box.com/box-java-sdk/javadoc/com/box/sdk/BoxInvite.html)
- Fixed an [OutOfMemory error in large file upload by capping the maximum number of parts that are uploaded concurrently](https://github.com/box/box-java-sdk/pull/543)


## [2.11.0]  (2018-01-25)

- [uploadLargeFile now returns a BoxFile object](https://github.com/box/box-java-sdk/pull/524)
- [Fixed chunked upload for Box Files greater than 2GB](https://github.com/box/box-java-sdk/pull/531)
- [Perform modified retry on JWT auth for when the local clock and the Box Server clock are not aligned as well as if the JWT ID has already been consumed](https://github.com/box/box-java-sdk/pull/523)
- BoxFolder.search has been deprecated in favor of [BoxSearch.searchRange](https://github.com/box/box-java-sdk/blob/86b82f2be3c57e3b89ae150b5f237d410e2d4900/doc/search.md)

## [2.10.0]  (2018-01-11)

- [Add optional is_confirmed paramater for adding user email alias](https://github.com/box/box-java-sdk/pull/499)
- Added support for [Metadata Template Delete](./doc/metadata_template#delete-a-metadata-template)

## [2.9.0]  (2018-01-04)

- Added option to pass file [SHA-1 hash for upload integrity](https://github.com/box/box-java-sdk/blob/main/doc/files.md#upload-a-file)
- Added support for [Terms of Service](./doc/terms_of_service) endpoint
- Fixed missing [webhook triggers](https://github.com/box/box-java-sdk/pull/497)
- Fixed missing [event types for events enum](https://github.com/box/box-java-sdk/pull/500)
- Added [modified_at timestamp to BoxComment.Info](https://github.com/box/box-java-sdk/pull/499)
- Added support for [Collaboration Whitelists](./doc/collaboration_whitelists) endpoint

## [2.8.2]  (2017-10-05)

- Added additional check for `PrivateKeyInfo` in `BoxDeveloperEditionApiConnection`

## [2.8.1]  (2017-10-05)

- Added ability to [set connect and read timeout globally](https://github.com/box/box-java-sdk/pull/459)

## [2.8.0]  (2017-09-07)

- Added method for getting file representations
- Changes to Representation object

## [2.7.0]  (2017-08-30)

- Added support for [Representations](https://github.com/box/box-java-sdk/blob/main/src/main/java/com/box/sdk/BoxFile.java#L445) endpoint

## [2.6.0]  (2017-08-28)

- Added support for [Batch](https://github.com/box/box-java-sdk/blob/575861fad0b3e67d432b5d5955d1e760b3f6444e/README.md#batchrequestexample)
- Added support for [Unified Metadata](./doc/folders#get-metadata-using-unified-metadata-api)

## [2.5.0]  (2017-07-28)

- Added support for [Recent Items](https://github.com/box/box-java-sdk/blob/main/src/main/java/com/box/sdk/BoxRecents.java#L1) endpoint
- Added support [Get All Groups By Name](https://github.com/box/box-java-sdk/blob/a1833950c18139fd9cbb4d8ee61d310c7bbedadf/src/main/java/com/box/sdk/BoxGroup.java#L143) endpoint
- Added support for [Token Exchange](https://github.com/box/box-java-sdk/blob/main/src/main/java/com/box/sdk/BoxAPIConnection.java#L634)

## [2.4.0]  (2017-05-02)

- Support for multiput upload. New methods in BoxFolder and BoxFile support multiput upload for better performance and reliability for large files.
- Single file collaborations. The BoxFile class now supports sharing individual files.
- Automatic configuration for JWT auth. The Box Developer console now lets you download a JSON file of your JWT app configuration settings. You can import this file into the Java SDK to easily configure your app.

## [2.3.0]  (2017-01-12)

New API Endpoints:

[Legal Holds](https://github.com/box/box-java-sdk/blob/main/doc/legal_holds.md)
[Retention Policies](https://github.com/box/box-java-sdk/blob/main/doc/retention_policies.md)
[Create Metadata Template](https://github.com/box/box-java-sdk/blob/main/doc/metadata_template.md#create-metadata-template)
[Get All Metadata on File](https://github.com/box/box-java-sdk/blob/main/doc/files.md#get-all-metadata-on-file)
[Get All Metadata on Folder](https://github.com/box/box-java-sdk/blob/main/doc/folders.md#get-all-metadata-on-folder)
[Get Enterprise Metadata Templates](https://github.com/box/box-java-sdk/blob/main/doc/metadata_template.md#get-enterprise-metadata-templates)
[Update Group](https://github.com/box/box-java-sdk/blob/main/doc/groups.md#update-a-group)
[Watermarking](https://github.com/box/box-java-sdk/blob/main/doc/watermarking.md)
[Webhooks V2](https://github.com/box/box-java-sdk/blob/main/doc/webhooks.md)
[WebLinks](https://github.com/box/box-java-sdk/blob/main/doc/weblinks.md)
[Collections](https://github.com/box/box-java-sdk/blob/main/doc/collections.md)
[BoxGroupMembership with for Paging](https://github.com/box/box-java-sdk/blob/main/doc/groups.md)
[Enterprise Device Pins](https://github.com/box/box-java-sdk/blob/86b82f2be3c57e3b89ae150b5f237d410e2d4900/doc/devices.md)

New Features:

Transactional Authentication. Support for Box's new Transactional Auth APIs.
Upload file versions with SHA1. A file's SHA1 can be passed in to BoxFile.uploadVersion(...) when uploading new versions.
Get effective_access for shared links. The effective_access field is accessible through BoxSharedLink. getEffectiveAccess().
Added additional Event Types. The TASK_ASSIGNMENT_COMPLETE, TASK_ASSIGNMENT_UPDATE, TASK_CREATE, COMMENT_DELETE types are now included in the BoxEvent class.

## [2.1.0]  (2016-02-22)

This release includes improvements to token caching for App Users and support for additional API endpoints.

New Features:

    - App Users token caching. A token cache can now be specified in BoxDeveloperEditionAPIConnection. This allows for improved performance when using App Users authentication.
    - Support for retrieving download URLs. The BoxFile.getDownloadURL() method allows for retrieving a direct download URL to a file.
    - File thumbnails. The BoxFile.getThumbnail() method allows for downloading the [Thumbnail](https://github.com/box/box-java-sdk/blob/main/doc/files.md#get-thumbnail) for a file.

Bug Fixes:

    - Getting info for a file could crash when there's no preview. Previously, an exception would be thrown if BoxFile.getInfo  (BoxFile.ALL_FIELDS) was called and the file didn't have a preview available.
