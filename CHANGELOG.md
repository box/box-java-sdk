# Changelog
##2.11.0

- Add updated file version upload endpoint(#524)
- Fixed chunked upload for Box Files greater than 2GB(#531)
- Perform modified retry on JWT auth(#523)

##2.10.0

- Add optional is_confirmed paramater for adding user email alias(#499)
- Added support for [Metadata Template Delete](./doc/metadata_template#delete-a-metadata-template)

##2.9.0

- Added option to pass file SHA-1 hash for upload integrity(#502)
- Added support for [Terms of Service](./doc/terms_of_service) endpoint
- Fixed missing webhook triggers(#497)
- Fixed missing event types for events enum(#500)
- Added modified_at timestamp to BoxComment.Info(#501)
- Added support for [Collaboration Whitelists](./doc/collaboration_whitelists) endpoint

##2.8.1

- Added ability to set connect and read timeout globally(#459)

##2.7.0

- Added support for [Representations](https://github.com/box/box-java-sdk/blob/master/src/main/java/com/box/sdk/BoxFile.java#L445) endpoint

##2.6.0

- Added support for [Batch](https://github.com/box/box-java-sdk/blob/575861fad0b3e67d432b5d5955d1e760b3f6444e/README.md#batchrequestexample)

- Added support for [Unifited Metadata](./doc/folders#get-metadata-using-unified-metadata-api)

##2.5.0

- Added support for [Recent Items](https://github.com/box/box-java-sdk/blob/master/src/main/java/com/box/sdk/BoxRecents.java#L1) endpoint
- Added support [Get All Groups By Name](https://github.com/box/box-java-sdk/blob/a1833950c18139fd9cbb4d8ee61d310c7bbedadf/src/main/java/com/box/sdk/BoxGroup.java#L143) endpoint
- Added support for [Token Exchange](https://github.com/box/box-java-sdk/blob/master/src/main/java/com/box/sdk/BoxAPIConnection.java#L634)


##2.4.0

- Support for multiput upload. New methods in BoxFolder and BoxFile support multiput upload for better performance and reliability for large files.
- Single file collaborations. The BoxFile class now supports sharing individual files.
- Automatic configuration for JWT auth. The Box Developer console now lets you download a JSON file of your JWT app configuration settings. You can import this file into the Java SDK to easily configure your app.

##2.3.0

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

##2.1.0

This release includes improvements to token caching for App Users and support for additional API endpoints.

New Features:

    - App Users token caching. A token cache can now be specified in BoxDeveloperEditionAPIConnection. This allows for improved performance when using App Users authentication.
    - Support for retrieving download URLs. The BoxFile.getDownloadURL() method allows for retrieving a direct download URL to a file.
    - File thumbnails. The BoxFile.getThumbnail() method allows for downloading the [Thumbnail](https://github.com/box/box-java-sdk/blob/master/doc/files.md#get-thumbnail) for a file.

Bug Fixes:

    - Getting info for a file could crash when there's no preview. Previously, an exception would be thrown if BoxFile.getInfo  (BoxFile.ALL_FIELDS) was called and the file didn't have a preview available.