[![Project Status](http://opensource.box.com/badges/active.svg)](http://opensource.box.com/badges)

Box Java SDK
============

**This SDK is currently in beta and is subject to change.**

This is the beta for the next major version of the Box Java SDK. The [previous
version](https://github.com/box/box-java-sdk-v2) will be deprecated after this
new version is released.






Quickstart
----------

The SDK can be obtained by either cloning the source into your project, or by
downloading one of the precompiled JARs from the [releases page on GitHub]
(https://gitenterprise.inside-box.net/Box/box-java-sdk/releases).

If you use the JAR, you'll also need to include [minimal-json v0.9.1]
(https://github.com/ralfstx/minimal-json) - which is the SDK's only dependency.
You can get minimal-json from maven with `com.eclipsesource.minimal-json:minimal-json:0.9.1`.

Here is a simple example of how to authenticate with the API using a developer
token and then print the ID and name of each item in your root folder.

```java
BoxAPIConnection api = new BoxAPIConnection("developer-token");
BoxFolder rootFolder = BoxFolder.getRootFolder(api);
for (BoxItem.Info itemInfo : rootFolder) {
    System.out.format("[%d] %s\n", itemInfo.getID(), itemInfo.getName());
}
```

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

Copyright 2014 Box, Inc. All rights reserved.

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

   http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
