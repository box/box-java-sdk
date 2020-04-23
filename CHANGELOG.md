# Changelog

## 2.47.0 [2020-04-23]
- Add support for the uploader display name field for Files and File Versions ([#791](https://github.com/box/box-java-sdk/pull/791))
- Fix path parameter sanitization ([#797](https://github.com/box/box-java-sdk/pull/797))

## 2.46.0 [2020-04-09]
- Fix retry logic ([#787](https://github.com/box/box-java-sdk/pull/787))
  - Retry for 400 `invalid_grant` error in authentication requests (Clock Skew)
  - Honor Retry-After header, if present, by waiting for the time specified in the header before retrying
  - The concept of setting / getting "Maximum API Requests" has been deprecated in favor of "Maximum API Retries" to more clearly show the number of times a request will be retried after an error response is received.
- Add ability to set expiration date for a collaboration ([#788](https://github.com/box/box-java-sdk/pull/788))
- Add path parameter sanitization ([#790](https://github.com/box/box-java-sdk/pull/790))

## 2.45.0 [2020-04-02]
- Add preflight check before chunked uploads ([#782](https://github.com/box/box-java-sdk/pull/782))
- Check that part was successfully uploaded for large file uploads before retrying for 500 errors ([#781](https://github.com/box/box-java-sdk/pull/781))
- Fix bug with premature disconnect when renaming files and weblinks ([#779](https://github.com/box/box-java-sdk/pull/779))
- Add metadata to each item returned by a metadata query ([#778](https://github.com/box/box-java-sdk/pull/778))

## 2.44.1 [2020-02-13]
- Fix formatting bug for Java Logger
- Improve date / time parsing for responses

## 2.44.0 [2020-01-21]
- Fix Authentication Request Retries

## 2.43.0 [2019-12-20]
- Throw exceptions for setMetadata on Files and Folders for non-409 errors

## 2.42.0 [2019-12-17]
- Added Metadata Query support
- Added marker based pagination for get users methods

## 2.41.0 [2019-10-24]
- Added enum action option for completed in Box Task class.

## 2.40.0 [2019-10-24]
- General doc changes. 

## 2.39.0 [2019-10-17]
- Deprecated Batch API functionality.
- Added support for [Task completion_rule](https://github.com/box/box-java-sdk/blob/master/src/main/java/com/box/sdk/BoxFile.java#L249)

## 2.38.0 [2019-09-19]
- Added missing fields for File Version: trashed_by, restored_at, purged_at, purged_by.
- Added support for [chunked uploads with file attributes](https://github.com/box/box-java-sdk/blob/master/doc/files.md#upload-a-large-file-in-chunks-including-attributes).

## 2.37.0 [2019-08-22]
- Added support for replace in multi-select metadata for [files](https://github.com/box/box-java-sdk/blob/master/doc/files.md#update-metadata) and
  for [folders](https://github.com/box/box-java-sdk/blob/master/doc/folders.md#update-metadata)
- Improved getting started with JWT authentication docs that can be found [here](https://github.com/box/box-java-sdk/blob/master/doc/authentication.md#server-authentication-with-jwt)

## 2.36.0 [2019-08-01]
- Added support for [removing shared link](https://github.com/box/box-java-sdk/blob/master/src/main/java/com/box/sdk/BoxItem.java#L413) and fixed an issue with setting null for shared link field on BoxItem.
- Added support for additional fields for Box files, folders, and web links.

## 2.35.0 [2019-07-18]
- Added support for retrieving [is_external_only field](https://github.com/box/box-java-sdk/blob/master/src/main/java/com/box/sdk/BoxFile.java#L1668) for Box Files and Folders.

## 2.34.0 [2019-06-06]
- Added support for retrieving a [string type action for tasks](https://github.com/box/box-java-sdk/blob/master/src/main/java/com/box/sdk/BoxTask.java#L281).
  Please use getActionType() going forward instead of the deprecated getAction().

## 2.33.0 [2019-05-23]

- Added support for [setting can_owners_invite field](https://github.com/box/box-java-sdk/blob/1ed10d7a457e44b863ec1c9b1d0d1408fb55e1e5/src/main/java/com/box/sdk/BoxFolder.java#L1272) Thank you @Band-Aid for you pull request! Greatly Appreciated.
- Fixed a bug where chunked upload was not populating the correct part size for upload part.

## 2.32.0 [2019-04-25]
- Added support [setting metadata](https://github.com/box/box-java-sdk/blob/master/doc/folders.md#set-metadata).

## 2.31.0 [2019-04-11]

- Added support for [sorting folder items](https://github.com/box/box-java-sdk/blob/master/doc/folders.md#get-a-folders-items) retrieved from a folder by ascending or descending order.

## 2.30.1 [2019-04-08]

- Fixed a bug where the SDK could throw when parsing JSON containing dates using the Zulu timezone format

## 2.30.0 [2019-04-04]
- Added `action_by` field to enterprise events stream.

## 2.29.0 [2019-04-01]
- Added support for [sorting results returned from Box Search](https://github.com/box/box-java-sdk/blob/master/doc/search.md#search-1)
- Added ability to [attach a file description upon file upload](https://github.com/box/box-java-sdk/blob/master/doc/files.md#upload-a-file)

## 2.28.1 [2019-03-07]
- Fixed a bug where BoxMetadataCascadePolicy.forceApply() would not return correctly.

## 2.28.0 [2019-02-21]
- Added ability for user to [retrieve an avatar](http://opensource.box.com/box-java-sdk/javadoc/com/box/sdk/BoxUser.html#getAvatar--)) for a specified user.

## 2.27.0 [2019-01-31]
- Added support for Metadata Classification for [File](http://opensource.box.com/box-java-sdk/javadoc/com/box/sdk/BoxFile.html#setClassification-java.lang.String...-) and [Folder](http://opensource.box.com/box-java-sdk/javadoc/com/box/sdk/BoxFolder.html#setClassification-java.lang.String...-)

## 2.26.0 [2019-01-17]
- Added [invite_email](https://github.com/box/box-java-sdk/blob/master/src/main/java/com/box/sdk/BoxCollaboration.java#L277) field to collaboration object.
- Added [is_collaboration_restricted_to_enterprise](https://github.com/box/box-java-sdk/blob/master/src/main/java/com/box/sdk/BoxFolder.java#L1104) field to folder object.
- Added [status](https://github.com/box/box-java-sdk/blob/master/src/main/java/com/box/sdk/BoxTaskAssignment.java#L196) field to task assignment object.
- Added ability to retrieve fields for [`BoxFile#getTasks()`](http://opensource.box.com/box-java-sdk/javadoc/com/box/sdk/BoxFile.html#getTasks-java.lang.String...-)
- Fixed bug where offset based paging would not return correctly.

## 2.25.1 [2019-01-03]
- Upgraded dependencies: jose4j to v0.5.5, and bouncycastle to v1.60

## 2.25.0 [2018-12-13]
- Added functionality to allow [content streaming to Box through outputstream](http://opensource.box.com/box-java-sdk/javadoc/com/box/sdk/BoxFolder.html#uploadFile-com.box.UploadFileCallback-java.lang.String-). Thank you @gajarajkalburgi for the pr!

## 2.24.0 [2018-11-16]
- Added `getOptionsObjects()` on `MetadataTemplate.Field` which returns both key and type.
- Added functionality for [`BoxItem#getType()`](http://opensource.box.com/box-java-sdk/javadoc/com/box/sdk/BoxItem.html#getType--) for BoxItem.
- Added functionality for [`BoxAPIConnection#BoxGlobalSettings()`](http://opensource.box.com/box-java-sdk/javadoc/com/box/sdk/BoxAPIConnection.html#setConnectTimeout-java.lang.String-)
and [`BoxAPIConnection#BoxGlobalSettings()`](http://opensource.box.com/box-java-sdk/javadoc/com/box/sdk/BoxAPIConnection.html#getConnectTimeout--) as well as getting and reading the timeout for the connection.
- Added functionality for [`BoxGlobalSettings#getMaxRequestAttempts()`](http://opensource.box.com/box-java-sdk/javadoc/com/box/sdk/BoxGlobalSettings.html#getMaxRequestAttempts--)
and [`BoxGlobalSettings#setMaxRquestAttempts()`](http://opensource.box.com/box-java-sdk/javadoc/com/box/sdk/BoxGlobalSettings.html#setMaxRequestAttempts-java.lang.Integer-)
- Fixed a bug where [`BoxLegalHoldPolicy#create()`](http://opensource.box.com/box-java-sdk/javadoc/com/box/sdk/BoxLegalHoldPolicy.html#create-com.box.BoxAPIConnection-java.lang.String-) was not setting the correct legal hold policy duration.

## 2.23.2 [2018-09-27]
- Fixed a bug where the specified headers for batch requests were not being sent.

## 2.23.1 [2018-09-13]
- Fixed a bug where too many TCP connections were being opened. Thank you @pmatte1 for implementing this fix!

## 2.23.0 [2018-08-23]
- Added support for [Metadata Cascade Policy](https://github.com/box/box-java-sdk/blob/master/doc/folders.md#create-cascade-policy-on-folder)

## 2.22.0 [2018-08-09]
- Deprecated the [moveFolderToUser()](https://github.com/box/box-java-sdk/blob/master/src/main/java/com/box/sdk/BoxUser.java#L455) for Box Users. We encourage users to
use [transferContent](https://github.com/box/box-java-sdk/blob/master/src/main/java/com/box/sdk/BoxUser.java#L482) going forward because idiomatically it is more correct.

## 2.21.0 [2018-07-05]
- Added functionality to allow users to [set passwords on shared links](https://github.com/box/box-java-sdk/pull/623) for Box files, folders, and web links.
- Fixed wrong redirect for two links in the `Getting Started` and `Quick Test` section of the README.

## 2.20.2 [2018-06-28]
- Fixed a bug where customers had issues with large file uploads because they fail to parse the Retry-After header from the
commit response. Reason being headers storage/lookup was case sensitive.

## 2.20.1 [2018-06-04]
- Added better exception handling for JSON parse in response exception.
- Fixed a bug where uploadNewVersion() was returning an empty object.

## 2.20.0 [2018-05-24]
- Fixed a bug where multiple As-User headers could be set.
- Added support to [test update](http://opensource.box.com/box-java-sdk/javadoc/com/box/sdk/Metadata.html#test-java.lang.String-java.util.List-) for multiselect field on metadata

## 2.19.0 [2018-05-10]
- Added support for enterprise admins with Box Zones purchased to have support for [Box Storage Policies and Box Storage Policy Assignments](./doc/storage_policy)
- Added support to allow users to work with [multiselect metadata](./doc/files.md#get-metadata)
- Added `getLogin()` method for the "login" field on the "accessible by" for BoxCollaboration.Info class.


## 2.18.0 [2018-04-30]
- Fixed a bug where the the private key password should be passed into `setPrivateKetPassword()` instead of the private key. A big thank you to [breach10ck](https://github.com/breach10ck)
for their pull request!
- Added an additional check to ensure that the [request properties on the request object is not null in the `toString()` method](https://github.com/box/box-java-sdk/pull/595)
- Added support to [fetch the content of the generated representation](./doc/files.md#get-representation-content) after it has been generated
- Improved error messages for API response errors to allow for better debugging.

## 2.17.0 [2018-04-10]
- Added support for assigning [Retention Policies to Metadata Templates](./doc/retention_policies.md#create-retention-policy-assignment)

## 2.16.1 [2018-03-29]

- Added `CONTENT_ACCESS` to event type enum

## 2.16.0 [2018-03-22]
- Added support for [user tracking codes](http://opensource.box.com/box-java-sdk/javadoc/com/box/sdk/BoxUser.Info.html#getTrackingCodes--) on the user object.
- Fixed a bug where JWT authentication would fail due to improper date parsing.
- Added support for setting custom headers on API connection. This allow for setting [As-User support](./doc/overview.md#as-user)
and [suppressing notifications](./doc/overview.md/suppressing-notifications) support.
- Changed default JWT expiration window to reduce chances of error.

## 2.15.0 [2018-03-12]
- Added support for retrieving a [metadata template by ID](./doc/metadata_template.md#get-by-id)
- Added support for allowing the user to [retrieve specific Collaboration fields on a Collaboration object](./doc/collaborations.md#get-a-collaborations-information)

## 2.14.1 [2018-03-01]

- Reduced the number of API calls that the `EventStream` makes to fetch new events, which should
help users who are running into rate limit issues.
- Force support for TLSv1.1 or higher when available to improve the security of connections to the Box API
- Add randomized jitter to the exponential backoff algorithm used by the SDK to improve the success rate
of retried requests.

## 2.14.0 [2018-02-15]

- Added support for getting and setting the `can_view_path` field on a collaboration object.
- Added support for getting and setting the `tags` field on files and folders.

## 2.13.0 [2018-02-07]

- Fixed an issue where all types of metadata values were being coerced to Strings.  This change deprecates
`Metadata#get()` in favor of type-specific methods like `Metadata#getFloat()` or a generic `Metadata#getValue()`,
which returns a `JsonValue` object that represents any JSON type.  See the [file metadata](./doc/files.md#get-metadata)
or [folder metadata](./doc/folders.md#get-metadata) documentation for more information.

## 2.12.0 [2018-02-01]

- Fixed ability to notify users or groups regarding [file collaboration](https://github.com/box/box-java-sdk/blob/master/doc/files.md#share-a-file) or [folder collaboration](https://github.com/box/box-java-sdk/blob/master/doc/folders.md#share-a-folder)
- Added [OAuth2 token creation event types](https://github.com/box/box-java-sdk/blob/master/src/main/java/com/box/sdk/BoxEvent.java#L747)
- Added support for [inviting a user to another Box Enterprise](http://opensource.box.com/box-java-sdk/javadoc/com/box/sdk/BoxInvite.html)
- Fixed an [OutOfMemory error in large file upload by capping the maximum number of parts that are uploaded concurrently](https://github.com/box/box-java-sdk/pull/543)


## 2.11.0 [2018-01-25]

- [uploadLargeFile now returns a BoxFile object](https://github.com/box/box-java-sdk/pull/524)
- [Fixed chunked upload for Box Files greater than 2GB](https://github.com/box/box-java-sdk/pull/531)
- [Perform modified retry on JWT auth for when the local clock and the Box Server clock are not aligned as well as if the JWT ID has already been consumed](https://github.com/box/box-java-sdk/pull/523)
- BoxFolder.search has been deprecated in favor of [BoxSearch.searchRange](https://github.com/box/box-java-sdk/blob/86b82f2be3c57e3b89ae150b5f237d410e2d4900/doc/search.md)

## 2.10.0 [2018-01-11]

- [Add optional is_confirmed paramater for adding user email alias](https://github.com/box/box-java-sdk/pull/499)
- Added support for [Metadata Template Delete](./doc/metadata_template#delete-a-metadata-template)

## 2.9.0 [2018-01-04]

- Added option to pass file [SHA-1 hash for upload integrity](https://github.com/box/box-java-sdk/blob/master/doc/files.md#upload-a-file)
- Added support for [Terms of Service](./doc/terms_of_service) endpoint
- Fixed missing [webhook triggers](https://github.com/box/box-java-sdk/pull/497)
- Fixed missing [event types for events enum](https://github.com/box/box-java-sdk/pull/500)
- Added [modified_at timestamp to BoxComment.Info](https://github.com/box/box-java-sdk/pull/499)
- Added support for [Collaboration Whitelists](./doc/collaboration_whitelists) endpoint

## 2.8.2 [2017-10-05]

- Added additional check for `PrivateKeyInfo` in `BoxDeveloperEditionApiConnection`

## 2.8.1 [2017-10-05]

- Added ability to [set connect and read timeout globally](https://github.com/box/box-java-sdk/pull/459)

## 2.8.0 [2017-09-07]

- Added method for getting file representations
- Changes to Representation object

## 2.7.0 [2017-08-30]

- Added support for [Representations](https://github.com/box/box-java-sdk/blob/master/src/main/java/com/box/sdk/BoxFile.java#L445) endpoint

## 2.6.0 [2017-08-28]

- Added support for [Batch](https://github.com/box/box-java-sdk/blob/575861fad0b3e67d432b5d5955d1e760b3f6444e/README.md#batchrequestexample)
- Added support for [Unified Metadata](./doc/folders#get-metadata-using-unified-metadata-api)

## 2.5.0 [2017-07-28]

- Added support for [Recent Items](https://github.com/box/box-java-sdk/blob/master/src/main/java/com/box/sdk/BoxRecents.java#L1) endpoint
- Added support [Get All Groups By Name](https://github.com/box/box-java-sdk/blob/a1833950c18139fd9cbb4d8ee61d310c7bbedadf/src/main/java/com/box/sdk/BoxGroup.java#L143) endpoint
- Added support for [Token Exchange](https://github.com/box/box-java-sdk/blob/master/src/main/java/com/box/sdk/BoxAPIConnection.java#L634)

## 2.4.0 [2017-05-02]

- Support for multiput upload. New methods in BoxFolder and BoxFile support multiput upload for better performance and reliability for large files.
- Single file collaborations. The BoxFile class now supports sharing individual files.
- Automatic configuration for JWT auth. The Box Developer console now lets you download a JSON file of your JWT app configuration settings. You can import this file into the Java SDK to easily configure your app.

## 2.3.0 [2017-01-12]

New API Endpoints:

[Legal Holds](https://github.com/box/box-java-sdk/blob/master/doc/legal_holds.md)
[Retention Policies](https://github.com/box/box-java-sdk/blob/master/doc/retention_policies.md)
[Create Metadata Template](https://github.com/box/box-java-sdk/blob/master/doc/metadata_template.md#create-metadata-template)
[Get All Metadata on File](https://github.com/box/box-java-sdk/blob/master/doc/files.md#get-all-metadata-on-file)
[Get All Metadata on Folder](https://github.com/box/box-java-sdk/blob/master/doc/folders.md#get-all-metadata-on-folder)
[Get Enterprise Metadata Templates](https://github.com/box/box-java-sdk/blob/master/doc/metadata_template.md#get-enterprise-metadata-templates)
[Update Group](https://github.com/box/box-java-sdk/blob/master/doc/groups.md#update-a-group)
[Watermarking](https://github.com/box/box-java-sdk/blob/master/doc/watermarking.md)
[Webhooks V2](https://github.com/box/box-java-sdk/blob/master/doc/webhooks.md)
[WebLinks](https://github.com/box/box-java-sdk/blob/master/doc/weblinks.md)
[Collections](https://github.com/box/box-java-sdk/blob/master/doc/collections.md)
[BoxGroupMembership with for Paging](https://github.com/box/box-java-sdk/blob/master/doc/groups.md)
[Enterprise Device Pins](https://github.com/box/box-java-sdk/blob/86b82f2be3c57e3b89ae150b5f237d410e2d4900/doc/devices.md)

New Features:

Transactional Authentication. Support for Box's new Transactional Auth APIs.
Upload file versions with SHA1. A file's SHA1 can be passed in to BoxFile.uploadVersion(...) when uploading new versions.
Get effective_access for shared links. The effective_access field is accessible through BoxSharedLink. getEffectiveAccess().
Added additional Event Types. The TASK_ASSIGNMENT_COMPLETE, TASK_ASSIGNMENT_UPDATE, TASK_CREATE, COMMENT_DELETE types are now included in the BoxEvent class.

## 2.1.0 [2016-02-22]

This release includes improvements to token caching for App Users and support for additional API endpoints.

New Features:

    - App Users token caching. A token cache can now be specified in BoxDeveloperEditionAPIConnection. This allows for improved performance when using App Users authentication.
    - Support for retrieving download URLs. The BoxFile.getDownloadURL() method allows for retrieving a direct download URL to a file.
    - File thumbnails. The BoxFile.getThumbnail() method allows for downloading the [Thumbnail](https://github.com/box/box-java-sdk/blob/master/doc/files.md#get-thumbnail) for a file.

Bug Fixes:

    - Getting info for a file could crash when there's no preview. Previously, an exception would be thrown if BoxFile.getInfo  (BoxFile.ALL_FIELDS) was called and the file didn't have a preview available.
