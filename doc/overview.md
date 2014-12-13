SDK Overview
============

This guide covers the basics behind the various components of the Box Java SDK.
It's also recommended that you take a look at [the
documentation](https://developers.box.com/docs/) for the Box API.

* [Authentication](#authentication)
* [Resource Types](#resource-types)
* [Requests and Responses](#requests-and-responses)
* [Error Handling](#error-handling)

Authentication
--------------

The first step in using the SDK is always authenticating and connecting to the
API. The SDK does this through the `BoxAPIConnection` class. This class
represents an authenticated connection to a specific version of the Box API. It
is responsible for things such as:

* Storing authentication information.
* Automatically refreshing tokens.
* Configuring rate-limiting, number of retry attempts and other connection
  settings.

You can also create more than one `BoxAPIConnection`. For example, you can have
a connection for each user if your application supports multiple user accounts.

See the [Authentication guide](authentication.md) for details on how to create
and use `BoxAPIConnection`.

Resource Types
--------------

Resources types are the classes you'll use the most. Things like `BoxFile`,
`BoxFolder`, `BoxUser`, etc. are all resource types. A resource always has an ID
and an associated API connection. Instantiating and using a resource type is
simple:

```java
// Print the name of the folder with ID "1234".
BoxFolder folder = new BoxFolder(api, "1234")
BoxFolder.Info info = folder.getInfo();
System.out.println(info.getName());
```

A resource type will always have the same API connection as the type that
instantiated it. For example, `creator` will have the same API connection that
`folder` does.

```java
BoxFolder folder = new BoxFolder(api, "1234")
BoxFolder.Info info = folder.getInfo();

// This BoxUser has the same BoxAPIConnection as "folder".
BoxUser creator = info.getCreatedBy();
```

Requests and Responses
----------------------

All communication with Box's API is done through `BoxAPIRequest` and
`BoxAPIResponse` (or their subclasses). These classes handle all the dirty work
of setting appropriate headers, handling errors, and sending/receiving data.

You generally won't need to use these classes directly, as the resource types
are easier and cover most use-cases. However, these classes are extremely
flexible and can be used if you need to make custom API calls.

Here's an example using `BoxAPIRequest` and `BoxJSONResponse` that gets a list
of items with some custom fields:

```java
BoxAPIConnection api = new BoxAPIConnection("token");
URL url = new URL("https://api.box.com/2.0/folders/0/items?fields=name,created_at")
BoxAPIRequest request = new BoxAPIRequest(api, url, "GET");
BoxJSONResponse response = (BoxJSONResponse) request.send();
String json = response.getJSON();
```

Error Handling
--------------

Unless otherwise noted, the classes and methods in the SDK can throw an
unchecked [`BoxAPIException`][api-exception] if an error occurs. This includes
network errors or error statuses returned by the API.

You should be aware of this when using the SDK so that your code can catch any
errors that might happen when communicating with Box.

If the error was due to a general networking error (for example, if the network
connection was lost), the `BoxAPIException` will contain the underlying
`IOException` as its cause.

If the error was due to an API error, the `BoxAPIException` will contain the
response code and body returned by the API.

```java
try {
    BoxAPIConnection api = new BoxAPIConnection("token");
    BoxFolder rootFolder = BoxFolder.getRootFolder(api);
} catch (BoxAPIException e) {
    // Log the response code and the error message returned by the API.
    System.err.format("The API returned the error code: %d\n\n%s",
        e.getResponseCode(), e.getResponse());
}
```

[api-exception]: http://opensource.box.com/box-java-sdk/javadoc/com/box/sdk/BoxAPIException.html
