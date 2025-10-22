# Migration guide from v5 to v10 version of `box-java-sdk`

<!-- START doctoc generated TOC please keep comment here to allow auto update -->
<!-- DON'T EDIT THIS SECTION, INSTEAD RE-RUN doctoc TO UPDATE -->

- [Introduction](#introduction)
- [Installation](#installation)
  - [Maven](#maven)
  - [Gradle](#gradle)
- [Supported Environments](#supported-environments)
- [Migration Scope and Module Compatibility](#migration-scope-and-module-compatibility)

<!-- END doctoc generated TOC please keep comment here to allow auto update -->

## Introduction

Version 10 of the Box Java SDK is a modern, fully auto-generated SDK built entirely from the `com.box.sdkgen` package.  
In version 5, the SDK included two packages side by side: the manually maintained `com.box.sdk` and the generated `com.box.sdkgen`.  
Starting with version 10, the `com.box.sdk` package has been removed, and only the `com.box.sdkgen` package remains.

If you are migrating code from `com.box.sdk` to `com.box.sdkgen` package, detailed instructions are available in the dedicated  
[Migration guide: migrate from `com.box.sdk` to `com.box.sdkgen` package](./from-com-box-sdk-to-com-box-sdkgen.md).

## Installation

To install v10 version of Box Java SDK, you can use Maven or Gradle. The library is available in the
[Maven Central Repository](https://search.maven.org/artifact/com.box/box-java-sdk).

### Maven

To start using generated version of the SDK in you Maven project just bump the version of the Box Java SDK library
in `pom.xml`to 10.0.0 or higher:

```xml
<dependency>
  <groupId>com.box</groupId>
  <artifactId>box-java-sdk</artifactId>
  <version>10.0.0</version>
</dependency>
```

### Gradle

To bump a dependency in your Gradle project, bump the version used in your `build.gradle` file:

```groovy
implementation 'com.box:box-java-sdk:10.0.0'
```

## Supported Environments

Both v5 and v10 of the Box Java SDK share the same Java version requirement: Java 8 or higher.
No changes to your environment are needed when upgrading from v5 to v10.

## Migration Scope and Module Compatibility

If your project only uses the `com.box.sdkgen` package from v5, no code changes are required to migrate to v10.  
The generated `com.box.sdkgen` package is the same in both v5 and v10.

If your project still includes code that uses the legacy `com.box.sdk` module, follow the dedicated guide to update it:  
[Migration guide: migrate from `com.box.sdk` to `com.box.sdkgen` package](./from-com-box-sdk-to-com-box-sdkgen.md).
