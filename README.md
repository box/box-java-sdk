[![Project Status](http://opensource.box.com/badges/active.svg)](http://opensource.box.com/badges)

Box Java SDK
============

The Box Java SDK for interacting with the
[Box Content API](https://developers.box.com/docs/).

Quickstart
----------

The SDK can be obtained by adding it as a [maven dependency]
(http://opensource.box.com/box-java-sdk/), cloning the source into your project,
or by downloading one of the precompiled JARs from the [releases page on GitHub]
(https://github.com/box/box-java-sdk/releases).

If you use the JAR, you'll also need to include several dependencies:

1. [minimal-json v0.9.1](https://github.com/ralfstx/minimal-json)  
   Maven: `com.eclipsesource.minimal-json:minimal-json:0.9.1`
2. [jose4j v0.4.4](https://bitbucket.org/b_c/jose4j/wiki/Home)  
   Maven: `org.bitbucket.b_c:jose4j:0.4.4`
3. [bouncycastle bcprov-jdk15on v1.52](http://mvnrepository.com/artifact/org.bouncycastle/bcprov-jdk15on)  
   Maven: `org.bouncycastle:bcprov-jdk15on:1.52`
4. [bouncycastle bcpkix-jdk15on v1.52](http://mvnrepository.com/artifact/org.bouncycastle/bcpkix-jdk15on)  
   Maven: `org.bouncycastle:bcpkix-jdk15on:1.52`
5. [Java Cryptography Extension (JCE) Unlimited Strength Jurisdiction Policy Files 7](http://www.oracle.com/technetwork/java/javase/downloads/jce-7-download-432124.html)  
   If you don't install this, you'll get an exception about key length. This is not a Box thing, this is a U.S. Government requirement concerning strong encryption.

Here is a simple example of how to authenticate with the API using a developer
token and then print the ID and name of each item in your root folder.

```java
BoxAPIConnection api = new BoxAPIConnection("developer-token");
BoxFolder rootFolder = BoxFolder.getRootFolder(api);
for (BoxItem.Info itemInfo : rootFolder) {
    System.out.format("[%s] %s\n", itemInfo.getID(), itemInfo.getName());
}
```

For more details on how to get started, check out the [overview
guide](doc/overview.md). It has a short explanation of how the SDK works and how
you can get started using it.

### Sample Project

A sample project can be found in `src/example`. This project will output your
name and a list of the files and folders in your root directory.

To run the project, first provide a developer token in
`src/example/java/com/box/sdk/example/Main.java`. You can obtain a developer
token from your application's [developer
console](https://cloud.app.box.com/developers/services).

```java
public final class Main {
    private static final String DEVELOPER_TOKEN = "<YOUR_DEVELOPER_TOKEN>";

    // ...
}
```

Then just invoke `gradle runExample` to run the example!

Building
--------

The SDK uses Gradle for its build system. Running `gradle build` from the root
of the repository will compile, lint, and test the SDK.

```bash
$ gradle build
```

The SDK also includes integration tests which make real API calls, and therefore
are run separately from unit tests. Integration tests should be run against a
test account since they create and delete data. To run the integration tests,
remove the `.template` extension from
`src/test/config/config.properties.template` and fill in your test account's
information. Then run:

```bash
$ gradle integrationTest
```

Documentation
-------------

You can find guides and tutorials in the `doc` directory.

* [Javadocs](http://box.github.io/box-java-sdk/javadoc/com/box/sdk/package-summary.html)
* [Overview](doc/overview.md)
* [Authentication](doc/authentication.md)
* [Files](doc/files.md)
* [Folders](doc/folders.md)
* [Comments](doc/comments.md)
* [Collaborations](doc/collaborations.md)
* [Events](doc/events.md)
* [Search](doc/search.md)
* [Users](doc/users.md)
* [Groups](doc/groups.md)
* [Tasks](doc/tasks.md)

Javadocs are generated when `gradle javadoc` is run and can be found in
`build/doc/javadoc`.

Copyright and License
---------------------

Copyright 2015 Box, Inc. All rights reserved.

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

   http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
