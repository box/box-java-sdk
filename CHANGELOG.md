# Changelog

##2.26.0
- Added [invite_email](https://github.com/box/box-java-sdk/blob/master/src/main/java/com/box/sdk/BoxCollaboration.java#L277) field to collaboration object.
- Added [is_collaboration_restricted_to_enterprise](https://github.com/box/box-java-sdk/blob/master/src/main/java/com/box/sdk/BoxFolder.java#L1104) field to folder object.
- Added [status](https://github.com/box/box-java-sdk/blob/master/src/main/java/com/box/sdk/BoxTaskAssignment.java#L196) field to task assignment object.
- Added ability to retrieve fields for [`BoxFile#getTasks()`](http://opensource.box.com/box-java-sdk/javadoc/com/box/sdk/BoxFile.html#getTasks-java.lang.String...-)
- Fixed bug where offset based paging would not return correctly.


## 2.25.0
- Added functionality to allow [content streaming to Box through outputstream](http://opensource.box.com/box-java-sdk/javadoc/com/box/sdk/BoxFolder.html#uploadFile-com.box.UploadFileCallback-java.lang.String-). Thank you @gajarajkalburgi for the pr!

## 2.24.0
- Added `getOptionsObjects()` on `MetadataTemplate.Field` which returns both key and type.
- Added functionality for [`BoxItem#getType()`](http://opensource.box.com/box-java-sdk/javadoc/com/box/sdk/BoxItem.html#getType--) for BoxItem.
- Added functionality for [`BoxAPIConnection#BoxGlobalSettings()`](http://opensource.box.com/box-java-sdk/javadoc/com/box/sdk/BoxAPIConnection.html#setConnectTimeout-java.lang.String-)
and [`BoxAPIConnection#BoxGlobalSettings()`](http://opensource.box.com/box-java-sdk/javadoc/com/box/sdk/BoxAPIConnection.html#getConnectTimeout--) as well as getting and reading the timeout for the connection.
- Added functionality for [`BoxGlobalSettings#getMaxRequestAttempts()`](http://opensource.box.com/box-java-sdk/javadoc/com/box/sdk/BoxGlobalSettings.html#getMaxRequestAttempts--)
and [`BoxGlobalSettings#setMaxRquestAttempts()`](http://opensource.box.com/box-java-sdk/javadoc/com/box/sdk/BoxGlobalSettings.html#setMaxRequestAttempts-java.lang.Integer-)
- Fixed a bug where [`BoxLegalHoldPolicy#create()`](http://opensource.box.com/box-java-sdk/javadoc/com/box/sdk/BoxLegalHoldPolicy.html#create-com.box.BoxAPIConnection-java.lang.String-) was not setting the correct legal hold policy duration.

## 2.23.2
- Fixed a bug where the specified headers for batch requests were not being sent.

## 2.23.1
- Fixed a bug where too many TCP connections were being opened. Thank you @pmatte1 for implementing this fix! 

## 2.23.0
- Added support for [Metadata Cascade Policy](https://github.com/box/box-java-sdk/blob/master/doc/folders.md#create-cascade-policy-on-folder)

## 2.22.0
- Deprecated the [moveFolderToUser()](https://github.com/box/box-java-sdk/blob/master/src/main/java/com/box/sdk/BoxUser.java#L455) for Box Users. We encourage users to
use [transferContent](https://github.com/box/box-java-sdk/blob/master/src/main/java/com/box/sdk/BoxUser.java#L482) going forward because idiomatically it is more correct.

## 2.21.0
- Added functionality to allow users to [set passwords on shared links](https://github.com/box/box-java-sdk/pull/623) for Box files, folders, and web links. 
- Fixed wrong redirect for two links in the `Getting Started` and `Quick Test` section of the README.

## 2.20.2
- Fixed a bug where customers had issues with large file uploads because they fail to parse the Retry-After header from the 
commit response. Reason being headers storage/lookup was case sensitive. 

## 2.20.1
- Added better exception handling for JSON parse in response exception.
- Fixed a bug where uploadNewVersion() was returning an empty object. 

## 2.20.0
- Fixed a bug where multiple As-User headers could be set. 
- Added support to [test update](http://opensource.box.com/box-java-sdk/javadoc/com/box/sdk/Metadata.html#test-java.lang.String-java.util.List-) for multiselect field on metadata

## 2.19.0
- Added support for enterprise admins with Box Zones purchased to have support for [Box Storage Policies and Box Storage Policy Assignments](./doc/storage_policy)
- Added support to allow users to work with [multiselect metadata](./doc/files.md#get-metadata)
- Added `getLogin()` method for the "login" field on the "accessible by" for BoxCollaboration.Info class.


## 2.18.0
- Fixed a bug where the the private key password should be passed into `setPrivateKetPassword()` instead of the private key. A big thank you to [breach10ck](https://github.com/breach10ck)
for their pull request! 
- Added an additional check to ensure that the [request properties on the request object is not null in the `toString()` method](https://github.com/box/box-java-sdk/pull/595)
- Added support to [fetch the content of the generated representation](./doc/files.md#get-representation-content) after it has been generated
- Improved error messages for API response errors to allow for better debugging. 

## 2.17.0
- Added support for assigning [Retention Policies to Metadata Templates](./doc/retention_policies.md#create-retention-policy-assignment)

## 2.16.1

- Added `CONTENT_ACCESS` to event type enum

## 2.16.0
- Added support for [user tracking codes](http://opensource.box.com/box-java-sdk/javadoc/com/box/sdk/BoxUser.Info.html#getTrackingCodes--) on the user object. 
- Fixed a bug where JWT authentication would fail due to improper date parsing. 
- Added support for setting custom headers on API connection. This allow for setting [As-User support](./doc/overview.md#as-user)
and [suppressing notifications](./doc/overview.md/suppressing-notifications) support.
- Changed default JWT expiration window to reduce chances of error. 

## 2.15.0
- Added support for retrieving a [metadata template by ID](./doc/metadata_template.md#get-by-id)
- Added support for allowing the user to [retrieve specific Collaboration fields on a Collaboration object](./doc/collaborations.md#get-a-collaborations-information)

## 2.14.1

- Reduced the number of API calls that the `EventStream` makes to fetch new events, which should
help users who are running into rate limit issues.
- Force support for TLSv1.1 or higher when available to improve the security of connections to the Box API
- Add randomized jitter to the exponential backoff algorithm used by the SDK to improve the success rate
of retried requests.

## 2.14.0

- Added support for getting and setting the `can_view_path` field on a collaboration object.
- Added support for getting and setting the `tags` field on files and folders.

## 2.13.0

- Fixed an issue where all types of metadata values were being coerced to Strings.  This change deprecates
`Metadata#get()` in favor of type-specific methods like `Metadata#getFloat()` or a generic `Metadata#getValue()`,
which returns a `JsonValue` object that represents any JSON type.  See the [file metadata](./doc/files.md#get-metadata)
or [folder metadata](./doc/folders.md#get-metadata) documentation for more information.

## 2.12.0

- Fixed ability to notify users or groups regarding [file collaboration](https://github.com/box/box-java-sdk/blob/master/doc/files.md#share-a-file) or [folder collaboration](https://github.com/box/box-java-sdk/blob/master/doc/folders.md#share-a-folder)
- Added [OAuth2 token creation event types](https://github.com/box/box-java-sdk/blob/master/src/main/java/com/box/sdk/BoxEvent.java#L747)
- Added support for [inviting a user to another Box Enterprise](http://opensource.box.com/box-java-sdk/javadoc/com/box/sdk/BoxInvite.html)
- Fixed an [OutOfMemory error in large file upload by capping the maximum number of parts that are uploaded concurrently](https://github.com/box/box-java-sdk/pull/543)


## 2.11.0

- [uploadLargeFile now returns a BoxFile object](https://github.com/box/box-java-sdk/pull/524)
- [Fixed chunked upload for Box Files greater than 2GB](https://github.com/box/box-java-sdk/pull/531)
- [Perform modified retry on JWT auth for when the local clock and the Box Server clock are not aligned as well as if the JWT ID has already been consumed](https://github.com/box/box-java-sdk/pull/523)
- BoxFolder.search has been deprecated in favor of [BoxSearch.searchRange](https://github.com/box/box-java-sdk/blob/86b82f2be3c57e3b89ae150b5f237d410e2d4900/doc/search.md)

## 2.10.0

- [Add optional is_confirmed paramater for adding user email alias](https://github.com/box/box-java-sdk/pull/499)
- Added support for [Metadata Template Delete](./doc/metadata_template#delete-a-metadata-template)

## 2.9.0

- Added option to pass file [SHA-1 hash for upload integrity](https://github.com/box/box-java-sdk/blob/master/doc/files.md#upload-a-file)
- Added support for [Terms of Service](./doc/terms_of_service) endpoint
- Fixed missing [webhook triggers](https://github.com/box/box-java-sdk/pull/497)
- Fixed missing [event types for events enum](https://github.com/box/box-java-sdk/pull/500)
- Added [modified_at timestamp to BoxComment.Info](https://github.com/box/box-java-sdk/pull/499)
- Added support for [Collaboration Whitelists](./doc/collaboration_whitelists) endpoint

## 2.8.1

- Added ability to [set connect and read timeout globally](https://github.com/box/box-java-sdk/pull/459)

## 2.7.0

- Added support for [Representations](https://github.com/box/box-java-sdk/blob/master/src/main/java/com/box/sdk/BoxFile.java#L445) endpoint

## 2.6.0

- Added support for [Batch](https://github.com/box/box-java-sdk/blob/575861fad0b3e67d432b5d5955d1e760b3f6444e/README.md#batchrequestexample)

- Added support for [Unified Metadata](./doc/folders#get-metadata-using-unified-metadata-api)

## 2.5.0

- Added support for [Recent Items](https://github.com/box/box-java-sdk/blob/master/src/main/java/com/box/sdk/BoxRecents.java#L1) endpoint
- Added support [Get All Groups By Name](https://github.com/box/box-java-sdk/blob/a1833950c18139fd9cbb4d8ee61d310c7bbedadf/src/main/java/com/box/sdk/BoxGroup.java#L143) endpoint
- Added support for [Token Exchange](https://github.com/box/box-java-sdk/blob/master/src/main/java/com/box/sdk/BoxAPIConnection.java#L634)


## 2.4.0

- Support for multiput upload. New methods in BoxFolder and BoxFile support multiput upload for better performance and reliability for large files.
- Single file collaborations. The BoxFile class now supports sharing individual files.
- Automatic configuration for JWT auth. The Box Developer console now lets you download a JSON file of your JWT app configuration settings. You can import this file into the Java SDK to easily configure your app.

## 2.3.0

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

## 2.1.0

This release includes improvements to token caching for App Users and support for additional API endpoints.

New Features:

    - App Users token caching. A token cache can now be specified in BoxDeveloperEditionAPIConnection. This allows for improved performance when using App Users authentication.
    - Support for retrieving download URLs. The BoxFile.getDownloadURL() method allows for retrieving a direct download URL to a file.
    - File thumbnails. The BoxFile.getThumbnail() method allows for downloading the [Thumbnail](https://github.com/box/box-java-sdk/blob/master/doc/files.md#get-thumbnail) for a file.

Bug Fixes:

    - Getting info for a file could crash when there's no preview. Previously, an exception would be thrown if BoxFile.getInfo  (BoxFile.ALL_FIELDS) was called and the file didn't have a preview available.
