# Migration guide from beta release (v0.8.0) of the `box-java-sdk-gen` to the v10 version of the `box-java-sdk`

<!-- START doctoc generated TOC please keep comment here to allow auto update -->
<!-- DON'T EDIT THIS SECTION, INSTEAD RE-RUN doctoc TO UPDATE -->

- [Installation](#installation)
  - [How to migrate](#how-to-migrate)
    - [Maven](#maven)
    - [Gradle](#gradle)
- [Union classes name changes](#union-classes-name-changes)
  - [How to migrate](#how-to-migrate-1)
- [Removed unused models from schemas namespace](#removed-unused-models-from-schemas-namespace)
  - [How to migrate](#how-to-migrate-2)

<!-- END doctoc generated TOC please keep comment here to allow auto update -->

## Installation

In order to start using v10 version of the Box Java SDK, you need to change the dependency in your project.
The artifact name has changed from `com.box:box-java-sdk-gen` to `com.box:box-java-sdk`.
You also need to set the version to `10.0.0` or higher. You can find the latest version on [Maven Central](https://search.maven.org/artifact/com.box/box-java-sdk).

### How to migrate

#### Maven

To start using v10 version of Box Java SDK in your Maven project replace the dependency in your `pom.xml` file.

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

#### Gradle

To start using v10 version of Box Java SDK in your Gradle project replace the dependency in your `build.gradle` file.

**Old (`box-java-sdk-gen-v0`)**

```groovy
implementation 'com.box:box-java-sdk-gen:0.8.0'
```

**New (`box-java-sdk-v10`)**

```groovy
implementation 'com.box:box-java-sdk:10.0.0'
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
