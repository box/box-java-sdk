# Migration guide from beta release (v0.X.Y) of the `box-java-sdk-gen` to the `box-java-sdk`

Note: This guide applies only to migrations targeting Box Java SDK v5.X.Y or v10.X.Y.
It does not apply to other major versions (e.g., v6.X, v11.X).

<!-- START doctoc generated TOC please keep comment here to allow auto update -->
<!-- DON'T EDIT THIS SECTION, INSTEAD RE-RUN doctoc TO UPDATE -->

- [Introduction](#introduction)
- [Installation](#installation)
  - [How to migrate](#how-to-migrate)
    - [Maven](#maven)
    - [Gradle](#gradle)
- [Union classes name changes](#union-classes-name-changes)
  - [How to migrate](#how-to-migrate-1)
- [Removed unused models from schemas namespace](#removed-unused-models-from-schemas-namespace)
  - [How to migrate](#how-to-migrate-2)
- [Usage](#usage)
  - [Using the Box Java SDK v10](#using-the-box-java-sdk-v10)
  - [Using the Box Java SDK v5](#using-the-box-java-sdk-v5)

<!-- END doctoc generated TOC please keep comment here to allow auto update -->

## Introduction

From the `box-java-sdk-gen` you can migrate either to v5 or v10 of the Box Java SDK.
Your choice should depend on whether you want to continue using the manually maintained SDK (Box Java SDK v4) alongside the generated one or not.

The v5 version of the Box Java SDK consolidates both the legacy SDK package `com.box.sdk` and the generated one `com.box.sdkgen`.

- If previously you were using both artifacts `box-java-sdk` v4 and `box-java-sdk-gen` v0, migrate to v5 version of the Box Java SDK which consolidates `com.box.sdk` and `com.box.sdkgen` packages.
- If you were only using the generated artifact `box-java-sdk-gen`, migrate to v10 version of the Box Java SDK which contains only the generated `com.box.sdkgen` package.

| Scenario                                     | Your current usage                                         | Recommended target | Packages included in target                           | Why this choice                                                          | Notes                                                                                |
| -------------------------------------------- | ---------------------------------------------------------- | ------------------ | ----------------------------------------------------- | ------------------------------------------------------------------------ | ------------------------------------------------------------------------------------ |
| Using both manual and generated SDK together | `com.box.sdk` v4 + `com.box.sdkgen` v0 in the same project | v5.X.Y             | `com.box.sdk` (manual) + `com.box.sdkgen` (generated) | Keep existing v4 code while adopting new features from the generated SDK | Run both modules side by side; use type aliases to avoid name conflicts if necessary |
| Using only the generated SDK                 | `com.box.sdkgen` v0 only                                   | v10.X.Y            | `com.box.sdkgen` (generated) only                     | Clean upgrade path with no legacy module; simpler dependency surface     | Best when you don’t need the manual `com.box.sdk` package                            |

## Installation

In order to start using v5 or v10 version of the Box Java SDK, you need to change the dependency in your project.
The artifact name has changed from `com.box:box-java-sdk-gen` to `com.box:box-java-sdk`.
You also need to set the version to `5.X.Y` if you are migrating to v5 or `10.X.Y` if you are migrating to v10.
You can find the latest version on [Maven Central](https://search.maven.org/artifact/com.box/box-java-sdk).

### How to migrate

#### Maven

To start using v5 or v10 version of Box Java SDK in your Maven project replace the dependency in your `pom.xml` file.

**Old (`box-java-sdk-gen-v0`)**

```xml
<dependency>
    <groupId>com.box</groupId>
    <artifactId>box-java-sdk-gen</artifactId>
    <version>0.8.0</version>
</dependency>
```

**New (`box-java-sdk-v10`)**

```xml
<dependency>
    <groupId>com.box</groupId>
    <artifactId>box-java-sdk</artifactId>
    <version>10.0.0</version>
</dependency>
```

**New (`box-java-sdk-v5`)**

```xml
<dependency>
    <groupId>com.box</groupId>
    <artifactId>box-java-sdk</artifactId>
    <version>5.0.0</version>
</dependency>
```

#### Gradle

To start using v5 or v10 version of Box Java SDK in your Gradle project replace the dependency in your `build.gradle` file.

**Old (`box-java-sdk-gen-v0`)**

```groovy
implementation 'com.box:box-java-sdk-gen:0.8.0'
```

**New (`box-java-sdk-v10`)**

```groovy
implementation 'com.box:box-java-sdk:10.0.0'
```

**New (`box-java-sdk-v5`)**

```groovy
implementation 'com.box:box-java-sdk:5.0.0'
```

## Union classes name changes

In the beta version of the `box-java-sdk-gen` our `OneOf` class names (representing unions from the OpenAPI specification)
were fully auto-generated based on the included variants. This often resulted in overly long names that were difficult
to work with in tools like Git. For example: `MetadataFieldFilterDateRangeOrMetadataFieldFilterFloatRangeOrArrayOfStringOrNumberOrString`.
Additionally, every time the new variant was added to the `OneOf`, the class name itself changed.
Starting in v10, the names of `OneOf` classes are defined directly in the specification. This ensures that they are meaningful, short, and stable over time.

### How to migrate

If your code references any of the renamed classes, replace the old name with the new one.
If you were not explicitly using the type names, no changes are needed, since only the class names changed and their behavior remains the same.

List of changed `OneOf` classes and types associated with them:

| Old name                                                                                   | New name                                                           |
| ------------------------------------------------------------------------------------------ | ------------------------------------------------------------------ |
| AiAgentAskOrAiAgentExtractOrAiAgentExtractStructuredOrAiAgentTextGen                       | AiAgent                                                            |
| AiAgentAskOrAiAgentReference                                                               | AiAskAgent                                                         |
| AiAgentExtractOrAiAgentReference                                                           | AiExtractAgent                                                     |
| AiAgentExtractStructuredOrAiAgentReference                                                 | AiExtractStructuredAgent                                           |
| AiAgentReferenceOrAiAgentTextGen                                                           | AiTextGenAgent                                                     |
| AppItemEventSourceOrEventSourceOrFileOrFolderOrGenericSourceOrUser                         | EventSourceResource                                                |
| FileBaseOrFolderBaseOrWebLinkBase                                                          | AppItemAssociatedItem                                              |
| FileFullOrFolderFull                                                                       | MetadataQueryResultItem                                            |
| FileFullOrFolderFullOrWebLink                                                              | SearchResultWithSharedLinkItem/RecentItemResource/SearchResultItem |
| FileFullOrFolderMiniOrWebLink                                                              | Item                                                               |
| FileMiniOrFolderMini                                                                       | Resource                                                           |
| FileOrFolderOrWebLink                                                                      | LegalHoldPolicyAssignedItem/CollaborationItem                      |
| FileOrFolderScope                                                                          | ResourceScope                                                      |
| FileOrFolderScopeScopeField                                                                | ResourceScopeScopeField                                            |
| FileReferenceOrFolderReferenceOrWeblinkReferenceV2025R0                                    | HubItemReferenceV2025R0                                            |
| GroupMiniOrUserCollaborations                                                              | CollaborationAccessGrantee                                         |
| IntegrationMappingPartnerItemSlackUnion                                                    | IntegrationMappingPartnerItemSlack                                 |
| IntegrationMappingPartnerItemTeamsUnion                                                    | IntegrationMappingPartnerItemTeams                                 |
| KeywordSkillCardOrStatusSkillCardOrTimelineSkillCardOrTranscriptSkillCard                  | SkillCard                                                          |
| MetadataFieldFilterDateRangeOrMetadataFieldFilterFloatRangeOrArrayOfStringOrNumberOrString | MetadataFilterValue                                                |
| SearchResultsOrSearchResultsWithSharedLinks                                                | SearchResultsResponse                                              |

Some classes were split into multiple ones depending on context.

Manager functions affected by these changes:

| Function                               | Old return type                                                      | New return type       |
| -------------------------------------- | -------------------------------------------------------------------- | --------------------- |
| AiManager.getAiAgentDefaultConfig(...) | AiAgentAskOrAiAgentExtractOrAiAgentExtractStructuredOrAiAgentTextGen | AiAgent               |
| SearchManager.searchForContent(...)    | SearchResultsOrSearchResultsWithSharedLinks                          | SearchResultsResponse |

## Removed unused models from schemas namespace

Several unused types (classes and enums) have been removed from the schemas because they were not used by any SDK functions or by the Box API.

### How to migrate

Here is the full list of removed types:

| Removed classes/enums                      |
| ------------------------------------------ |
| FileOrFolder                               |
| HubActionV2025R0                           |
| MetadataQueryIndex                         |
| MetadataQueryIndexFieldsField              |
| MetadataQueryIndexFieldsSortDirectionField |
| MetadataQueryIndexStatusField              |
| RetentionPolicyAssignmentBase              |
| RetentionPolicyAssignmentBaseTypeField     |
| SkillInvocation                            |
| SkillInvocationEnterpriseField             |
| SkillInvocationEnterpriseTypeField         |
| SkillInvocationSkillField                  |
| SkillInvocationSkillTypeField              |
| SkillInvocationStatusField                 |
| SkillInvocationStatusStateField            |
| SkillInvocationTokenField                  |
| SkillInvocationTokenReadField              |
| SkillInvocationTokenReadTokenTypeField     |
| SkillInvocationTokenWriteField             |
| SkillInvocationTokenWriteTokenTypeField    |
| SkillInvocationTypeField                   |
| WebhookInvocation                          |
| WebhookInvocationTriggerField              |
| WebhookInvocationTypeField                 |
| WorkflowFull                               |

If your code references any of these types, remove those references.

## Usage

### Using the Box Java SDK v10

After migration from `box-java-sdk-gen` to the `box-java-sdk` v10, you can still use the `com.box.sdkgen` package in the same way as before.
To access the client for interacting with the Box API, simply import `BoxClient` and any other necessary classes from the `com.box.sdkgen` package.

```java
import com.box.sdkgen.client.BoxClient;
import com.box.sdkgen.box.developertokenauth.BoxDeveloperTokenAuth;

BoxDeveloperTokenAuth auth = new BoxDeveloperTokenAuth("DEVELOPER_TOKEN");
BoxClient client = new BoxClient(auth);
client.folders.getFolderItems("0").getEntries().forEach(item -> {
        System.out.println(item.toString());
        });
```

### Using the Box Java SDK v5

After migration to Box Java SDK v5, you can use both the manual Box Java SDK package `com.box.sdk` and the generated one `com.box.sdkgen`.
You just need to import the required classes from the appropriate package depending on which SDK you intend to use.
If both packages contain classes with the same name, you can use fully qualified names to resolve any naming conflicts.

```java
import com.box.sdk.BoxConfig;
import com.box.sdk.BoxDeveloperEditionAPIConnection;

import com.box.sdk.BoxFolder;
import com.box.sdkgen.box.jwtauth.BoxJWTAuth;
import com.box.sdkgen.box.jwtauth.JWTConfig;
import com.box.sdkgen.client.BoxClient;
import com.box.sdkgen.managers.folders.UpdateFolderByIdRequestBody;
import com.box.sdkgen.schemas.folder.Folder;

import java.io.FileReader;
import java.io.Reader;

public class Main {
  public static void main(String[] args) throws Exception {

    Reader reader = new FileReader("src/example/config/config.json");
    BoxConfig boxConfig = BoxConfig.readFrom(reader);
    BoxDeveloperEditionAPIConnection api = BoxDeveloperEditionAPIConnection.getAppEnterpriseConnection(boxConfig);

    JWTConfig config = JWTConfig.fromConfigFile("src/example/config/config.json");
    BoxJWTAuth auth = new BoxJWTAuth(config);
    BoxClient client = new BoxClient(auth);

    BoxFolder rootFolder = new BoxFolder(api, "0");
    BoxFolder.Info subfolder = rootFolder.createFolder("My Subfolder");
    Folder updatedFolder = client.getFolders().updateFolderById(
            subfolder.getID(),
            new UpdateFolderByIdRequestBody.Builder().name("My Updated Subfolder").build()
    );
    System.out.println("Created folder with ID " + subfolder.getID() + " has been updated to " + updatedFolder.getName());
  }
}
```
